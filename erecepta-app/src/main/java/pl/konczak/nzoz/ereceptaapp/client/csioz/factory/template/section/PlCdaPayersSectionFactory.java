package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.section;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.CE;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Act;
import org.hl7.v3.POCDMT000040Component3;
import org.hl7.v3.POCDMT000040Entry;
import org.hl7.v3.POCDMT000040Section;
import org.hl7.v3.StrucDocParagraph;
import org.hl7.v3.StrucDocText;
import pl.konczak.nzoz.ereceptaapp.config.constant.Loinc;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.CodeFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry.PlCdaCoverageActivityEntryFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Patient;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.3.69-2018-06-30T000000.html
 */
@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaPayersSectionFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final CodeFactory codeFactory;
    private final PlCdaCoverageActivityEntryFactory plCdaCoverageActivityEntryFactory;

    public POCDMT000040Component3 createPocdmt000040Component3ForPlCdaPayersSection(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Component3 pocdmt000040Component3 = objectFactoryForHl7V3.createPOCDMT000040Component3();

        POCDMT000040Section pocdmt000040Section = objectFactoryForHl7V3.createPOCDMT000040Section();

        Collection<II> templateIds = templateIdFactory
                .createMultiple(Oid.Hl7.CdaTemplates.SECTION_PAYERS_SECTION, Oid.Ihe.PAYERS, Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaSekcjiDokumentow.PAYERS_SECTION);
        pocdmt000040Section.getTemplateIds()
                .addAll(templateIds);

        CE code = codeFactory.create(Oid.SlownikiIZbioryWartosci.EXTERNAL_LOINC, Loinc.PAYMENT_SOURCES.getCode());
        pocdmt000040Section.setCode(code);

        // this value is not fixed - should it be configurable?
        pocdmt000040Section.setTitle("Dane o ubezpieczeniu i uprawnieniach");

        pocdmt000040Section.setText(createStructDoc(createEReceptaDlaLekGotowyInput));

        POCDMT000040Entry pocdmt000040Entry = createPocdmt000040Entry(createEReceptaDlaLekGotowyInput);

        pocdmt000040Section.getEntries()
                .add(pocdmt000040Entry);

        pocdmt000040Component3.setSection(pocdmt000040Section);

        return pocdmt000040Component3;
    }

    private StrucDocText createStructDoc(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        Patient patient = createEReceptaDlaLekGotowyInput.getPatient();
        StrucDocText strucDocText = objectFactoryForHl7V3.createStrucDocText();

        StrucDocParagraph strucDocParagraph = objectFactoryForHl7V3.createStrucDocParagraph();
        strucDocParagraph.setID("ACT_UPRAWNIENIA_DODATKOWE");

        // TODO should it be patient NFZ or where medical facility has deal?
        strucDocParagraph.getContent()
                .add("Oddział NFZ: " + patient.getAssignedTo().getCode());

        strucDocParagraph.getContent()
                .add(objectFactoryForHl7V3.createStrucDocBr());

        String stringBuilder = "Uprawnienia dodatkowe: "
                + createEReceptaDlaLekGotowyInput.getUprawnienieDodatkowe().getCode()
                + "("
                + createEReceptaDlaLekGotowyInput.getDocumentConfirmOfUprawnieniaDodatkowe()
                + ")";
        strucDocParagraph.getContent()
                .add(stringBuilder);

        strucDocText.getContent()
                .add(strucDocParagraph);

        return strucDocText;
    }

    private POCDMT000040Entry createPocdmt000040Entry(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Entry pocdmt000040Entry = objectFactoryForHl7V3.createPOCDMT000040Entry();

        POCDMT000040Act pocdmt000040Act = plCdaCoverageActivityEntryFactory.createPOCDMT000040Act(createEReceptaDlaLekGotowyInput);
        pocdmt000040Entry.setAct(pocdmt000040Act);

        return pocdmt000040Entry;
    }
}
