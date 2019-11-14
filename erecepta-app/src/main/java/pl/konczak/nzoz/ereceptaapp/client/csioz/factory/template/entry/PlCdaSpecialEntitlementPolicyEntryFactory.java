package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
import org.hl7.v3.POCDMT000040Reference;
import org.hl7.v3.ParticipationPhysicalPerformer;
import org.hl7.v3.TEL;
import org.hl7.v3.XActClassDocumentEntryAct;
import org.hl7.v3.XActRelationshipEntryRelationship;
import org.hl7.v3.XDocumentActMood;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.config.constant.UprawnienieDodatkowe;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Patient;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.61-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaSpecialEntitlementPolicyEntryFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;
    private final PlCdaAuthorizationActivityEntryFactory plCdaAuthorizationActivityEntryFactory;
    private final PlCdaEntitlementDocumentFactory plCdaEntitlementDocumentFactory;

    public POCDMT000040Act createPocdmt000040Act(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Act pocdmt000040Act = objectFactoryForHl7V3.createPOCDMT000040Act();

        // fixed values
        pocdmt000040Act.setClassCode(XActClassDocumentEntryAct.ACT);
        pocdmt000040Act.setMoodCode(XDocumentActMood.EVN);

        Collection<II> templateIds = templateIdFactory
                .createMultiple(Oid.Hl7.CdaTemplates.ENTRY_POLICY_ACTIVITY, Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.SPECIAL_ENTITLEMENT_POLICY_ENTRY);

        pocdmt000040Act.getTemplateIds()
                .addAll(templateIds);

        II id = objectFactoryForHl7V3.createII();
        id.getNullFlavors()
                .add("NA");
        pocdmt000040Act.getIds()
                .add(id);

        CD code = createCode(createEReceptaDlaLekGotowyInput);
        pocdmt000040Act.setCode(code);

        ED text = createTextReference(createEReceptaDlaLekGotowyInput);
        pocdmt000040Act.setText(text);

        CS statusCode = objectFactoryForHl7V3.createCS();
        statusCode.setCode("completed");
        pocdmt000040Act.setStatusCode(statusCode);

        POCDMT000040Performer2 pocdmt000040Performer2 = createPocdmt000040Performer2(createEReceptaDlaLekGotowyInput);
        pocdmt000040Act.getPerformers()
                .add(pocdmt000040Performer2);

        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = objectFactoryForHl7V3.createPOCDMT000040EntryRelationship();

        // fixed
        pocdmt000040EntryRelationship.setTypeCode(XActRelationshipEntryRelationship.REFR);

        POCDMT000040Act pocdmt000040ActOfEntryRelationship = plCdaAuthorizationActivityEntryFactory.createPocdmt000040Act(createEReceptaDlaLekGotowyInput);
        pocdmt000040EntryRelationship.setAct(pocdmt000040ActOfEntryRelationship);

        pocdmt000040Act.getEntryRelationships()
                .add(pocdmt000040EntryRelationship);

        POCDMT000040Reference pocdmt000040Reference = plCdaEntitlementDocumentFactory.createPOCDMT000040Reference(createEReceptaDlaLekGotowyInput);
        pocdmt000040Act.getReferences()
                .add(pocdmt000040Reference);

        return pocdmt000040Act;
    }

    private CD createCode(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        CD cd = objectFactoryForHl7V3.createCD();
        cd.setCode(pl.konczak.nzoz.ereceptaapp.client.csioz.constant.ActCode.PUBLIC_HEALTH_CARE.getCode());
        cd.setCodeSystem(Oid.Hl7.HL_7_ACT_CODE);

        CR qualifier = objectFactoryForHl7V3.createCR();
        CV name = objectFactoryForHl7V3.createCV();
        // TODO find out what are the other values then RLUD
        // those 3 values are hardcode in template
        name.setCode("RLUD");
        name.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        name.setDisplayName("Refundacja leków wynikająca z uprawnień dodatkowych");
        qualifier.setName(name);

        UprawnienieDodatkowe uprawnienieDodatkowe = createEReceptaDlaLekGotowyInput.getUprawnienieDodatkowe();
        CD code = objectFactoryForHl7V3.createCD();
        code.setCode(uprawnienieDodatkowe.getCode());
        code.setCodeSystem(Oid.SlownikiIZbioryWartosci.EXTERNAL_UPRAWNIENIA_DODATKOWE_ZWIAZANE_Z_REFUNDACJA_LEKOW);
        code.setDisplayName(uprawnienieDodatkowe.getCode());
        qualifier.setValue(code);

        cd.getQualifiers()
                .add(qualifier);

        return cd;
    }

    private ED createTextReference(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        // TODO take a closer look here - why input is not used?
        ED text = objectFactoryForHl7V3.createED();
        TEL tel = objectFactoryForHl7V3.createTEL();
        tel.setValue("#ACT_UPRAWNIENIA_DODATKOWE");
        text.setReference(tel);
        return text;
    }

    private POCDMT000040Performer2 createPocdmt000040Performer2(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        Patient patient = createEReceptaDlaLekGotowyInput.getPatient();
        POCDMT000040Performer2 pocdmt000040Performer2 = objectFactoryForHl7V3.createPOCDMT000040Performer2();

        pocdmt000040Performer2.setTypeCode(ParticipationPhysicalPerformer.PRF);

        POCDMT000040AssignedEntity pocdmt000040AssignedEntity = objectFactoryForHl7V3.createPOCDMT000040AssignedEntity();

        II id = idFactory.create(
                Oid.IdentyfikatoryPlatnikow.PODMIOTY_ZOBOWIAZANE_DO_FINANSOWANIA_SWIADCZEN_ZE_SRODKOW_PUBLICZNYCH,
                patient.getAssignedTo().getCode(),
                true);

        pocdmt000040AssignedEntity.getIds()
                .add(id);

        pocdmt000040Performer2.setAssignedEntity(pocdmt000040AssignedEntity);

        return pocdmt000040Performer2;
    }
}
