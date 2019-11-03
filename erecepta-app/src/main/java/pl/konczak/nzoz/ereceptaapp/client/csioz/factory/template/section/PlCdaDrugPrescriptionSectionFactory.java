package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.section;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.CE;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Component3;
import org.hl7.v3.POCDMT000040Entry;
import org.hl7.v3.POCDMT000040Section;
import org.hl7.v3.StrucDocBr;
import org.hl7.v3.StrucDocContent;
import org.hl7.v3.StrucDocParagraph;
import org.hl7.v3.StrucDocText;
import pl.konczak.nzoz.ereceptaapp.config.constant.Loinc;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.CodeFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry.PlCdaDrugPrescriptionEntryFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.PrescribedDrug;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.3.4-2018-06-30T000000.html
 */
@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaDrugPrescriptionSectionFactory {

    private static final String RECEPTA_SECTION_TITLE = "Rp.";

    private final ObjectFactory objectFactoryForHl7V3;
    private final PlCdaDrugPrescriptionEntryFactory plCdaDrugPrescriptionEntryFactory;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;
    private final CodeFactory codeFactory;

    public POCDMT000040Component3 createPOCDMT000040Component3(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        PrescribedDrug prescribedDrug = createEReceptaDlaLekGotowyInput.getPrescribedDrug();
        POCDMT000040Component3 pocdmt000040Component3 = objectFactoryForHl7V3.createPOCDMT000040Component3();

        POCDMT000040Section pocdmt000040Section = objectFactoryForHl7V3.createPOCDMT000040Section();

        Collection<II> templateIds = templateIdFactory.createMultiple(
                Oid.Ihe.PRESCRIPTION_SECTION_CONTENT_MODULE,
                Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaSekcjiDokumentow.DRUG_PRESCRIPTION_SECTION
        );
        pocdmt000040Section.getTemplateIds()
                .addAll(templateIds);

        II idSekcjiZaleceniaLekuUUslugodawcy = idFactory.create(Oid.IdentyfikatoryPrzedsiebiorstw.WezelUslugodawcy.IDENTYFIKATORY_SEKCJI_ZALECENIA_LEKU_W_RECEPCIE_U_USLUGODAWCY, prescribedDrug.getId());
        // TODO probably should rather work system of sub IDs - have one ID for prescribed drug and small suffix for each section
        pocdmt000040Section.setId(idSekcjiZaleceniaLekuUUslugodawcy);

        Loinc prescriptionList = Loinc.PRESCRIPTION_LIST;
        CE loincPrescriptionCode = codeFactory
                .create(Oid.SlownikiIZbioryWartosci.EXTERNAL_LOINC, prescriptionList.getCode(), "LOINC", prescriptionList.getDisplayName());
        pocdmt000040Section.setCode(loincPrescriptionCode);

        pocdmt000040Section.setTitle(RECEPTA_SECTION_TITLE);

        StrucDocText strucDocText = createStrucDocText(createEReceptaDlaLekGotowyInput);
        pocdmt000040Section.setText(strucDocText);

        // this entry is selectable and depends on drug type - lek gotowy, lek recepturowy, wyrob medyczny
        POCDMT000040Entry pocdmt000040Entry = plCdaDrugPrescriptionEntryFactory.createPocdmt000040Entry(createEReceptaDlaLekGotowyInput);
        pocdmt000040Section.getEntries()
                .add(pocdmt000040Entry);

        pocdmt000040Component3.setSection(pocdmt000040Section);

        return pocdmt000040Component3;
    }

    private StrucDocText createStrucDocText(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        PrescribedDrug prescribedDrug = createEReceptaDlaLekGotowyInput.getPrescribedDrug();
        StrucDocText strucDocText = new StrucDocText();

        StrucDocParagraph strucDocParagraph = new StrucDocParagraph();
        strucDocParagraph.setID("SBADM_PRESCRIBED_DRUG");

        String drugName = createDrugName(prescribedDrug);

        strucDocParagraph.getContent()
                .add(drugName);

        strucDocParagraph.getContent()
                .add(new StrucDocBr());

        String quantityAndType = createQuantityAndType(prescribedDrug);

        strucDocParagraph.getContent()
                .add(quantityAndType);

        strucDocParagraph.getContent()
                .add(objectFactoryForHl7V3.createStrucDocBr());

        strucDocParagraph.getContent()
                .add(prescribedDrug.getDawkaStosowania());

        strucDocParagraph.getContent()
                .add(objectFactoryForHl7V3.createStrucDocBr());

        StrucDocContent strucDocContent = objectFactoryForHl7V3.createStrucDocContent();
        strucDocContent.setID("ACT_DISCOUNT");
        strucDocContent.getContent()
                .add("Odpłatność: " + createEReceptaDlaLekGotowyInput.getPoziomOdplatnosciZaLeki().getCode());

        strucDocParagraph.getContent()
                .add(strucDocContent);

        strucDocText.getContent()
                .add(strucDocParagraph);

        return strucDocText;
    }

    // TODO this method duplicates with pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry.PlCdaMedicineOrSpecialFoodFactory.createDrugName
    // while for compliance with code for automates it should be extract to common
    private String createDrugName(final PrescribedDrug prescribedDrug) {
        // nazwa produktu, moc i postać farmaceutyczna, rozdzielanych spacją
        // Instrukcja stosowania PIK HL7 CDA_20181130_v_1.3.1.pdf page 132
        // ex. Polocard 150mg tabletk
        // but there is case for 'Dane opakowania' where it should be 'nazwa produktu, moc oraz w nawiasie liczba dawek i postać'
        // ex. Enarenal 5mg (60 tabl.)

        return prescribedDrug.getNazwaProduktuLeczniczego()
                + " "
                + prescribedDrug.getMoc()
                + " "
                + prescribedDrug.getPostacFarmaceutyczna();
    }

    private String createQuantityAndType(final PrescribedDrug prescribedDrug) {
        log.warn("It won't work for every type of prescribed drug");

        return prescribedDrug.getPackageCount()
                + " op. po "
                + prescribedDrug.getZawartoscOpakowania();
    }
}
