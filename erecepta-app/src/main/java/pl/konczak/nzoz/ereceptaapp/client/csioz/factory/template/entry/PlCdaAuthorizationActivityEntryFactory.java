package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.CD;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Act;
import org.hl7.v3.POCDMT000040Consumable;
import org.hl7.v3.POCDMT000040EntryRelationship;
import org.hl7.v3.POCDMT000040ManufacturedProduct;
import org.hl7.v3.POCDMT000040Material;
import org.hl7.v3.POCDMT000040SubstanceAdministration;
import org.hl7.v3.XActClassDocumentEntryAct;
import org.hl7.v3.XActRelationshipEntryRelationship;
import org.hl7.v3.XDocumentActMood;
import org.hl7.v3.XDocumentSubstanceMood;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.ActClassUnifiedCode;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.53-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaAuthorizationActivityEntryFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;

    public POCDMT000040Act createPocdmt000040Act(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Act pocdmt000040Act = objectFactoryForHl7V3.createPOCDMT000040Act();

        // both values are only required but "have to" particular values
        pocdmt000040Act.setClassCode(XActClassDocumentEntryAct.ACT);
        pocdmt000040Act.setMoodCode(XDocumentActMood.EVN);

        Collection<II> templateIds = templateIdFactory
                .createMultiple(Oid.Hl7.CdaTemplates.ENTRY_AUTHORIZATION_ACTIVITY, Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.AUTHORIZATION_ACTIVITY_ENTRY);

        pocdmt000040Act.getTemplateIds()
                .addAll(templateIds);

        II id = objectFactoryForHl7V3.createII();
        id.getNullFlavors()
                .add("NA");

        pocdmt000040Act.getIds()
                .add(id);

        CD code = objectFactoryForHl7V3.createCD();
        code.getNullFlavors()
                .add("NA");
        pocdmt000040Act.setCode(code);

        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = createPocdmt000040EntryRelationship(createEReceptaDlaLekGotowyInput);

        pocdmt000040Act.getEntryRelationships()
                .add(pocdmt000040EntryRelationship);
        return pocdmt000040Act;
    }

    private POCDMT000040EntryRelationship createPocdmt000040EntryRelationship(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = objectFactoryForHl7V3.createPOCDMT000040EntryRelationship();

        // fixed
        pocdmt000040EntryRelationship.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
        POCDMT000040SubstanceAdministration pocdmt000040SubstanceAdministration = objectFactoryForHl7V3.createPOCDMT000040SubstanceAdministration();

        // TODO check those 2 values - sample does not define whether they are fixed - they are just taken from sample (maybe rest is also invalid?)
        pocdmt000040SubstanceAdministration.getClassCodes()
                .add(ActClassUnifiedCode.SUBSTANCE_ADMINISTRATION.getCode());
        pocdmt000040SubstanceAdministration.setMoodCode(XDocumentSubstanceMood.PRMS);

        // this ID requires mandatory position - however PL IG allows only for single drug prescription in one ClinicalDocument so it will be always "-1"
        II id = idFactory.create(Oid.IdentyfikatoryPrzedsiebiorstw.WezelUslugodawcy.IDENTYFIKATORY_POZYCJI_RECEPTY_U_USLUGODAWCY, createEReceptaDlaLekGotowyInput.getUniqueId() + "-1", false);

        pocdmt000040SubstanceAdministration.getIds()
                .add(id);

        POCDMT000040Consumable pocdmt000040Consumable = objectFactoryForHl7V3.createPOCDMT000040Consumable();
        POCDMT000040ManufacturedProduct pocdmt000040ManufacturedProduct = objectFactoryForHl7V3.createPOCDMT000040ManufacturedProduct();
        POCDMT000040Material pocdmt000040Material = objectFactoryForHl7V3.createPOCDMT000040Material();
        pocdmt000040Material.getNullFlavors()
                .add("NA");
        pocdmt000040ManufacturedProduct.setManufacturedMaterial(pocdmt000040Material);
        pocdmt000040Consumable.setManufacturedProduct(pocdmt000040ManufacturedProduct);

        pocdmt000040SubstanceAdministration.setConsumable(pocdmt000040Consumable);

        pocdmt000040EntryRelationship.setSubstanceAdministration(pocdmt000040SubstanceAdministration);
        return pocdmt000040EntryRelationship;
    }
}
