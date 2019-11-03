package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.CS;
import org.hl7.v3.ED;
import org.hl7.v3.II;
import org.hl7.v3.IVLINT;
import org.hl7.v3.IVLTS;
import org.hl7.v3.IVXBTS;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Consumable;
import org.hl7.v3.POCDMT000040Entry;
import org.hl7.v3.POCDMT000040EntryRelationship;
import org.hl7.v3.POCDMT000040ManufacturedProduct;
import org.hl7.v3.POCDMT000040Material;
import org.hl7.v3.POCDMT000040SubstanceAdministration;
import org.hl7.v3.XDocumentSubstanceMood;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.ActClassUnifiedCode;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype.PlCdaClinicalStatementTextFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.PrescribedDrug;
import pl.konczak.nzoz.ereceptaapp.util.DateTimeUtil;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.3-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaDrugPrescriptionEntryFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;
    private final PlCdaClinicalStatementTextFactory plCdaClinicalStatementTextFactory;
    private final PlCdaMedicineOrSpecialFoodFactory plCdaMedicineOrSpecialFoodFactory;
    private final PlCdaDrugPrescriptionSupplyEntryFactory plCdaDrugPrescriptionSupplyEntryFactory;

    public POCDMT000040Entry createPocdmt000040Entry(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        PrescribedDrug prescribedDrug = createEReceptaDlaLekGotowyInput.getPrescribedDrug();
        POCDMT000040Entry pocdmt000040Entry = objectFactoryForHl7V3.createPOCDMT000040Entry();

        POCDMT000040SubstanceAdministration pocdmt000040SubstanceAdministration = objectFactoryForHl7V3.createPOCDMT000040SubstanceAdministration();
        pocdmt000040SubstanceAdministration.getClassCodes()
                .add(ActClassUnifiedCode.SUBSTANCE_ADMINISTRATION.getCode());
        pocdmt000040SubstanceAdministration.setMoodCode(XDocumentSubstanceMood.INT);

        Collection<II> templateIds = createTemplateIds();
        pocdmt000040SubstanceAdministration.getTemplateIds()
                .addAll(templateIds);

        // this ID requires mandatory position - however PL IG allows only for single drug prescription in one ClinicalDocument so it will be always "-1"
        II idPozycjiRecepty = idFactory.create(Oid.IdentyfikatoryPrzedsiebiorstw.WezelUslugodawcy.IDENTYFIKATORY_POZYCJI_RECEPTY_U_USLUGODAWCY, createEReceptaDlaLekGotowyInput.getUniqueId() + "-1");
        pocdmt000040SubstanceAdministration.getIds()
                .add(idPozycjiRecepty);

        ED text = plCdaClinicalStatementTextFactory.createTextForVisualReference("SBADM", "PRESCRIBED_DRUG");
        pocdmt000040SubstanceAdministration.setText(text);

        CS statusCode = objectFactoryForHl7V3.createCS();
        statusCode.setCode("completed");
        pocdmt000040SubstanceAdministration.setStatusCode(statusCode);

        IVLTS prescriptionValidPeriod = createPrescriptionValidPeriod(prescribedDrug);
        pocdmt000040SubstanceAdministration.getEffectiveTimes()
                .add(prescriptionValidPeriod);

        // TODO this indicates repeat count on prescription in future need to support more cases
        IVLINT repeatNumber = objectFactoryForHl7V3.createIVLINT();
        repeatNumber.setValue(prescribedDrug.getRepeatNumber());
        pocdmt000040SubstanceAdministration.setRepeatNumber(repeatNumber);

        POCDMT000040Consumable pocdmt000040Consumable = createPocdmt000040Consumable(prescribedDrug);
        pocdmt000040SubstanceAdministration.setConsumable(pocdmt000040Consumable);

        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = plCdaDrugPrescriptionSupplyEntryFactory
                .createPocdmt000040EntryRelationship(createEReceptaDlaLekGotowyInput);
        pocdmt000040SubstanceAdministration.getEntryRelationships()
                .add(pocdmt000040EntryRelationship);

        // TODO do we need here other entryRelationship using:
        // pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry.PlCdaDrugPrescriptionSupplySubstitutionEntryFactory ?

        pocdmt000040Entry.setSubstanceAdministration(pocdmt000040SubstanceAdministration);

        return pocdmt000040Entry;
    }

    private Collection<II> createTemplateIds() {
        // templateId is selectable
        return templateIdFactory.createMultiple(
                Oid.Ihe.PRESCRIPTION_ITEM_ENTRY_CONTENT_MODULE,
                Oid.Hl7.CdaTemplates.ENTRY_MEDICATION_ACTIVITY,
                Oid.Ihe.MEDICATIONS,
                Oid.Ihe.PHARMACY_ENTRY_TEMPLATE_CHANGED_DISPENSE_ITEM_ENTRY_CONTENT_MODULE,
                Oid.Ihe.MEDICATIONS_DOSE_NORMAL,
                Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.DRUG_PRESCRIPTION_ENTRY
        );
    }

    private IVLTS createPrescriptionValidPeriod(final PrescribedDrug prescribedDrug) {
        IVXBTS validFrom = objectFactoryForHl7V3.createIVXBTS();
        validFrom.setValue(DateTimeUtil.formatAsBasicDate(prescribedDrug.getValidFrom()));

        // TODO validTo can be null - and by default means 30 days (confirm that is compliant to law regulations)
        IVXBTS validTo = objectFactoryForHl7V3.createIVXBTS();
        validTo.setValue(DateTimeUtil.formatAsBasicDate(prescribedDrug.getValidTo()));

        IVLTS prescriptionValidPeriod = objectFactoryForHl7V3.createIVLTS();
        prescriptionValidPeriod.setLow(validFrom);
        prescriptionValidPeriod.setHigh(validTo);
        return prescriptionValidPeriod;
    }

    private POCDMT000040Consumable createPocdmt000040Consumable(final PrescribedDrug prescribedDrug) {
        POCDMT000040Consumable pocdmt000040Consumable = objectFactoryForHl7V3.createPOCDMT000040Consumable();

        POCDMT000040ManufacturedProduct pocdmt000040ManufacturedProduct = objectFactoryForHl7V3.createPOCDMT000040ManufacturedProduct();

        Collection<II> templateIds = templateIdFactory.createMultiple(Oid.Hl7.CdaTemplates.ENTRY_TEMPLATE_PRODUCT, Oid.Ihe.PRODUCT_ENTRY);

        pocdmt000040ManufacturedProduct.getTemplateIds()
                .addAll(templateIds);

        POCDMT000040Material pocdmt000040Material = plCdaMedicineOrSpecialFoodFactory.createPocdmt000040Material(prescribedDrug);
        pocdmt000040ManufacturedProduct.setManufacturedMaterial(pocdmt000040Material);

        pocdmt000040Consumable.setManufacturedProduct(pocdmt000040ManufacturedProduct);

        return pocdmt000040Consumable;
    }

}
