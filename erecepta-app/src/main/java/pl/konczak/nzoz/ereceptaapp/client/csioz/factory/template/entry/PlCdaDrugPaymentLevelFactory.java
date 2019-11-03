package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.CD;
import org.hl7.v3.CR;
import org.hl7.v3.CS;
import org.hl7.v3.CV;
import org.hl7.v3.ED;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Act;
import org.hl7.v3.POCDMT000040AssignedEntity;
import org.hl7.v3.POCDMT000040EntryRelationship;
import org.hl7.v3.POCDMT000040Performer2;
import org.hl7.v3.ParticipationPhysicalPerformer;
import org.hl7.v3.XActClassDocumentEntryAct;
import org.hl7.v3.XActRelationshipEntryRelationship;
import org.hl7.v3.XDocumentActMood;
import pl.konczak.nzoz.ereceptaapp.config.constant.Loinc;
import pl.konczak.nzoz.ereceptaapp.config.constant.Nfz;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.config.constant.PoziomOdplatnosciZaLeki;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype.PlCdaClinicalStatementTextFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Patient;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.57-2018-06-30T000000.html
 */
@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaDrugPaymentLevelFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final PlCdaClinicalStatementTextFactory plCdaClinicalStatementTextFactory;
    private final IdFactory idFactory;

    public POCDMT000040Act createPocdmt000040Act(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Act pocdmt000040Act = objectFactoryForHl7V3.createPOCDMT000040Act();
        pocdmt000040Act.setClassCode(XActClassDocumentEntryAct.ACT);
        pocdmt000040Act.setMoodCode(XDocumentActMood.DEF);

        String plCdaDrugPaymentLevel = Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.DRUG_PAYMENT_LEVEL;
        II templateId = templateIdFactory.create(plCdaDrugPaymentLevel);
        pocdmt000040Act.getTemplateIds()
                .add(templateId);

        CD code = createCode();
        pocdmt000040Act.setCode(code);

        ED text = plCdaClinicalStatementTextFactory.createTextForVisualReference("ACT", "DISCOUNT");
        pocdmt000040Act.setText(text);

        CS statusCode = createStatusCode();
        pocdmt000040Act.setStatusCode(statusCode);

        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = createPocdmt000040EntryRelationship(createEReceptaDlaLekGotowyInput);
        pocdmt000040Act.getEntryRelationships()
                .add(pocdmt000040EntryRelationship);

        return pocdmt000040Act;
    }

    private CD createCode() {
        CD code = objectFactoryForHl7V3.createCD();
        Loinc paymentSources = Loinc.PAYMENT_SOURCES;
        code.setCode(paymentSources.getCode());
        code.setCodeSystem(Oid.SlownikiIZbioryWartosci.EXTERNAL_LOINC);
        code.setDisplayName(paymentSources.getDisplayName());
        return code;
    }

    private CS createStatusCode() {
        CS codeStatus = objectFactoryForHl7V3.createCS();
        codeStatus.setCode("completed");
        return codeStatus;
    }

    private POCDMT000040EntryRelationship createPocdmt000040EntryRelationship(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = objectFactoryForHl7V3.createPOCDMT000040EntryRelationship();
        pocdmt000040EntryRelationship.setTypeCode(XActRelationshipEntryRelationship.COMP);

        POCDMT000040Act pocdmt000040Act = createPocdmt000040ActNameMeBetter(createEReceptaDlaLekGotowyInput);
        pocdmt000040EntryRelationship.setAct(pocdmt000040Act);

        return pocdmt000040EntryRelationship;
    }

    private POCDMT000040Act createPocdmt000040ActNameMeBetter(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Act pocdmt000040Act = objectFactoryForHl7V3.createPOCDMT000040Act();
        pocdmt000040Act.setClassCode(XActClassDocumentEntryAct.ACT);
        pocdmt000040Act.setMoodCode(XDocumentActMood.EVN);

        CD code = objectFactoryForHl7V3.createCD();
        code.setCode("PUBLICPOL");
        code.setCodeSystem(Oid.Hl7.HL_7_ACT_CODE);

        CR qualifier = createQualifier(createEReceptaDlaLekGotowyInput);
        code.getQualifiers()
                .add(qualifier);

        pocdmt000040Act.setCode(code);

        CS statusCode = objectFactoryForHl7V3.createCS();
        statusCode.setCode("completed");
        pocdmt000040Act.setStatusCode(statusCode);

        POCDMT000040Performer2 pocdmt000040Performer2 = createPocdmt000040Performer2(createEReceptaDlaLekGotowyInput);
        pocdmt000040Act.getPerformers()
                .add(pocdmt000040Performer2);

        return pocdmt000040Act;
    }

    private CR createQualifier(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        PoziomOdplatnosciZaLeki poziomOdplatnosciZaLeki = createEReceptaDlaLekGotowyInput.getPoziomOdplatnosciZaLeki();

        CR qualifier = objectFactoryForHl7V3.createCR();

        CV name = objectFactoryForHl7V3.createCV();
        // all 4 are fixed
        name.setCode("RLPO");
        name.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        name.setCodeSystemName("PolskieKlasyfikatoryHL7v3");
        name.setDisplayName("Poziomy odpłatności leków refundowanych");

        qualifier.setName(name);

        CD value = objectFactoryForHl7V3.createCD();
        value.setCode(poziomOdplatnosciZaLeki.getCode());
        value.setCodeSystem(Oid.SlownikiIZbioryWartosci.P1_POZIOMY_ODPLATNOSCI_LEKOW);
        value.setDisplayName(poziomOdplatnosciZaLeki.getDisplayName());

        qualifier.setValue(value);

        return qualifier;
    }

    private POCDMT000040Performer2 createPocdmt000040Performer2(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        Patient patient = createEReceptaDlaLekGotowyInput.getPatient();
        Nfz assignedTo = patient.getAssignedTo();
        POCDMT000040Performer2 pocdmt000040Performer2 = objectFactoryForHl7V3.createPOCDMT000040Performer2();
        pocdmt000040Performer2.setTypeCode(ParticipationPhysicalPerformer.PRF);

        POCDMT000040AssignedEntity pocdmt000040AssignedEntity = objectFactoryForHl7V3.createPOCDMT000040AssignedEntity();

        II id = idFactory
                .create(Oid.IdentyfikatoryPlatnikow.PODMIOTY_ZOBOWIAZANE_DO_FINANSOWANIA_SWIADCZEN_ZE_SRODKOW_PUBLICZNYCH, assignedTo.getCode(), true);

        pocdmt000040AssignedEntity.getIds()
                .add(id);

        pocdmt000040Performer2.setAssignedEntity(pocdmt000040AssignedEntity);


        return pocdmt000040Performer2;
    }

}
