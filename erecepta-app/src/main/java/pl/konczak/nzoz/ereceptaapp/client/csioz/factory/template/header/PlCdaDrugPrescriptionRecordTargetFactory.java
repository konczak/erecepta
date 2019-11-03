package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.AD;
import org.hl7.v3.CE;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.PN;
import org.hl7.v3.POCDMT000040Patient;
import org.hl7.v3.POCDMT000040PatientRole;
import org.hl7.v3.POCDMT000040RecordTarget;
import org.hl7.v3.TS;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype.PlCdaBaseAddrFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype.PlCdaBasePersonNameFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Gender;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Patient;
import pl.konczak.nzoz.ereceptaapp.util.DateTimeUtil;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.23-2018-09-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaDrugPrescriptionRecordTargetFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;
    private final PlCdaBaseAddrFactory plCdaBaseAddrFactory;
    private final PlCdaBasePersonNameFactory plCdaBasePersonNameFactory;

    public POCDMT000040RecordTarget createPOCDMT000040RecordTarget(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040RecordTarget patientTargetRecord = objectFactoryForHl7V3.createPOCDMT000040RecordTarget();

        II templateId = templateIdFactory.create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.DRUG_PRESCRIPTION_RECORD_TARGET);
        patientTargetRecord.getTemplateIds()
                .add(templateId);

        POCDMT000040PatientRole pocdmt000040PatientRole = createPatientRole(createEReceptaDlaLekGotowyInput);
        patientTargetRecord.setPatientRole(pocdmt000040PatientRole);

        return patientTargetRecord;
    }

    private POCDMT000040PatientRole createPatientRole(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040PatientRole patientRole = objectFactoryForHl7V3.createPOCDMT000040PatientRole();
        Patient patient = createEReceptaDlaLekGotowyInput.getPatient();

        II idInUslugodawcaSystem = idFactory.create(Oid.IdentyfikatoryPrzedsiebiorstw.WezelUslugodawcy.IDENTYFIKATOR_PACJENTA_W_SYSTEMIE_USLUGODAWCY, patient.getId(), false);

        // TODO this has to be customizable for different cases like foreigners, newborns and multiple pregnancy child
        II displayablePatientId = idFactory
                .create(Oid.IdentyfikatoryOsob.KRAJOWE_IDETYFIKATORY_OSOB_W_PANSTWACH_UE_I_STREFY_SCHENGEN_POLSKA, patient.getPesel(), true);

        List<II> ids = patientRole.getIds();
        ids.add(idInUslugodawcaSystem);
        ids.add(displayablePatientId);

        AD address = plCdaBaseAddrFactory.createAD(patient.getHomeAddress());
        patientRole.getAddrs()
                .add(address);

        patientRole.setPatient(createPatient(patient));

        return patientRole;
    }

    private POCDMT000040Patient createPatient(final Patient patient) {
        POCDMT000040Patient pocdmt000040Patient = objectFactoryForHl7V3.createPOCDMT000040Patient();

        List<PN> names = pocdmt000040Patient.getNames();
        PN personName = plCdaBasePersonNameFactory.createPN(patient);

        names.add(personName);

        CE administrativeGenderCode = createAdministrativeGenderCode(patient);
        pocdmt000040Patient.setAdministrativeGenderCode(administrativeGenderCode);

        TS birthTime = objectFactoryForHl7V3.createTS();
        birthTime.setValue(DateTimeUtil.formatAsBasicDate(patient.getBirthAt()));
        pocdmt000040Patient.setBirthTime(birthTime);

        return pocdmt000040Patient;
    }

    private CE createAdministrativeGenderCode(final Patient patient) {
        CE administrativeGenderCode = objectFactoryForHl7V3.createCE();

        administrativeGenderCode.setCode(Gender.FEMALE == patient.getGender() ? "F" : "M");
        administrativeGenderCode.setCodeSystem(Oid.Hl7.ADMINISTRATIVE_GENDER);

        return administrativeGenderCode;
    }
}
