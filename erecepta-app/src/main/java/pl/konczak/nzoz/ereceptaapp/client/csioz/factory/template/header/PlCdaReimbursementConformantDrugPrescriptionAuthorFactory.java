package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.CE;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Author;
import org.hl7.v3.POCDMT000040Organization;
import org.hl7.v3.POCDMT000040Person;
import org.hl7.v3.TS;
import pl.gov.csioz.xsd.extpl.r2.AssignedAuthor;
import pl.gov.csioz.xsd.extpl.r2.BoundedByParticipation;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.config.constant.SpecjalnoscLekarza;
import pl.konczak.nzoz.ereceptaapp.config.constant.ZawodMedyczny;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicineDoctor;
import pl.konczak.nzoz.ereceptaapp.util.DateTimeUtil;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.79-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaReimbursementConformantDrugPrescriptionAuthorFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;
    private final PlCdaBasePersonFactory plCdaBasePersonFactory;
    private final PlCdaBaseOrganizationalUnitFactory plCdaBaseOrganizationalUnitFactory;
    private final pl.gov.csioz.xsd.extpl.r2.ObjectFactory objectFactoryForExtPl;
    private final PlExtReimbursementRelatedContractFactory plExtReimbursementRelatedContractFactory;

    public POCDMT000040Author createPOCDMT000040Author(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Author pocdmt000040Author = objectFactoryForHl7V3.createPOCDMT000040Author();

        II plCdaDrugPrescriptionAuthorTemplateId = templateIdFactory.create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.REIMBURSEMENT_CONFORMANT_DRUG_PRESCRIPTION_AUTHOR);
        pocdmt000040Author.getTemplateIds()
                .add(plCdaDrugPrescriptionAuthorTemplateId);

        CE functionCode = createFunctionCode(createEReceptaDlaLekGotowyInput);
        pocdmt000040Author.setFunctionCode(functionCode);

        TS time = creatTime(createEReceptaDlaLekGotowyInput);
        pocdmt000040Author.setTime(time);

        AssignedAuthor assignedAuthor = createAssignedAuthor(createEReceptaDlaLekGotowyInput);
        pocdmt000040Author.setAssignedAuthor(assignedAuthor);

        return pocdmt000040Author;
    }

    private CE createFunctionCode(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        CE functionCode = objectFactoryForHl7V3.createCE();
        ZawodMedyczny zawodMedyczny = createEReceptaDlaLekGotowyInput.getMedicineDoctor().getZawodMedyczny();
        functionCode.setCode(zawodMedyczny.getCode());
        functionCode.setCodeSystem(Oid.SlownikiIZbioryWartosci.EXTERNAL_ZAWODY_MEDYCZNE);
        functionCode.setDisplayName(zawodMedyczny.getDisplayName());
        return functionCode;
    }

    private TS creatTime(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        TS effectiveTime = objectFactoryForHl7V3.createTS();
        LocalDateTime createdAt = createEReceptaDlaLekGotowyInput.getCreatedAt();
        String createdAtString = DateTimeUtil.formatAsBasicDate(createdAt);
        effectiveTime.setValue(createdAtString);
        return effectiveTime;
    }

    private AssignedAuthor createAssignedAuthor(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        AssignedAuthor assignedAuthor = objectFactoryForExtPl.createAssignedAuthor();

        MedicineDoctor medicineDoctor = createEReceptaDlaLekGotowyInput.getMedicineDoctor();

        II id = idFactory.create(
                Oid.IdentyfikatoryOsob.NUMERY_PRAWA_WYKONYWANIA_ZAWODU_LEKARZE_DENTYSCI_FELCZERZY,
                medicineDoctor.getNumerPrawaWykonywaniaZawodu(),
                true
        );
        assignedAuthor.getIds()
                .add(id);

        CE code = objectFactoryForHl7V3.createCE();
        SpecjalnoscLekarza specjalnoscLekarza = medicineDoctor.getSpecjalnoscLekarza();

        code.setCode(specjalnoscLekarza.getCode());
        code.setCodeSystem(Oid.SlownikiIZbioryWartosci.EXTERNAL_SPECJALNOSCI_LEKARSKIE);
        code.setDisplayName(specjalnoscLekarza.getDisplayName());
        assignedAuthor.setCode(code);

        POCDMT000040Person pocdmt000040Person = plCdaBasePersonFactory.createPOCDMT000040Person(createEReceptaDlaLekGotowyInput.getMedicineDoctor());

        assignedAuthor.setAssignedPerson(pocdmt000040Person);

        POCDMT000040Organization pocdmt000040Organization = plCdaBaseOrganizationalUnitFactory.createPOCDMT000040Organization(createEReceptaDlaLekGotowyInput);
        assignedAuthor.setRepresentedOrganization(pocdmt000040Organization);

        BoundedByParticipation boundedByParticipation = plExtReimbursementRelatedContractFactory.createBoundedByParticipation(createEReceptaDlaLekGotowyInput);
        assignedAuthor.setBoundedBy(boundedByParticipation);

        return assignedAuthor;
    }
}
