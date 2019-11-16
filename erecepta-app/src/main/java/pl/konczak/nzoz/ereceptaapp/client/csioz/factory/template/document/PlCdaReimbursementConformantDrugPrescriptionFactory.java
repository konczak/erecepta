package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.II;
import org.hl7.v3.INT;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Author;
import org.hl7.v3.POCDMT000040Component2;
import org.hl7.v3.POCDMT000040Custodian;
import org.hl7.v3.POCDMT000040InfrastructureRootTypeId;
import org.hl7.v3.POCDMT000040LegalAuthenticator;
import org.hl7.v3.POCDMT000040RecordTarget;
import org.hl7.v3.TS;
import pl.gov.csioz.xsd.extpl.r2.ClinicalDocument;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.CodeForDictionaryTranslationAndAutoAnalysisFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header.PlCdaBaseLegalAuthenticatorFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header.PlCdaDrugPrescriptionComponentFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header.PlCdaDrugPrescriptionRecordTargetFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header.PlCdaP1BaseCustodianFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header.PlCdaReimbursementConformantDrugPrescriptionAuthorFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.util.DateTimeUtil;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.26-2018-09-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaReimbursementConformantDrugPrescriptionFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final pl.gov.csioz.xsd.extpl.r2.ObjectFactory objectFactoryForExtPl;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;
    private final CodeForDictionaryTranslationAndAutoAnalysisFactory codeForDictionaryTranslationAndAutoAnalysisFactory;
    private final PlCdaDrugPrescriptionRecordTargetFactory plCdaDrugPrescriptionRecordTargetFactory;
    private final PlCdaReimbursementConformantDrugPrescriptionAuthorFactory plCdaReimbursementConformantDrugPrescriptionAuthorFactory;
    private final PlCdaP1BaseCustodianFactory plCdaP1BaseCustodianFactory;
    private final PlCdaBaseLegalAuthenticatorFactory plCdaBaseLegalAuthenticatorFactory;
    private final PlCdaDrugPrescriptionComponentFactory createPocdmt000040Component2ForDocumentBody;

    public ClinicalDocument createClinicalDocument(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        ClinicalDocument clinicalDocument = objectFactoryForExtPl.createClinicalDocument();

        // probably identifies purpose of document
        POCDMT000040InfrastructureRootTypeId pocdmt000040InfrastructureRootTypeId = createPocdmt000040InfrastructureRootTypeId();
        clinicalDocument.setTypeId(pocdmt000040InfrastructureRootTypeId);

        Collection<II> templateIds = createTemplateIds();
        clinicalDocument.getTemplateIds()
                .addAll(templateIds);

        II identyfikatoryInstancjiReceptyUUslugodawcy = idFactory
                .create(Oid.IdentyfikatoryPrzedsiebiorstw.WezelUslugodawcy.IDENTYFIKATORY_INSTANCJI_RECEPT_U_USLUGODAWCY, createEReceptaDlaLekGotowyInput.getId());
        clinicalDocument.setId(identyfikatoryInstancjiReceptyUUslugodawcy);

        CE codeForDictionaryTranslationAndDisplay = codeForDictionaryTranslationAndAutoAnalysisFactory.createCodeForDictionaryTranslationAndAutoAnalysis(createEReceptaDlaLekGotowyInput);
        clinicalDocument.setCode(codeForDictionaryTranslationAndDisplay);

        // TODO this should be synchronized with ClinicalDocument.code.translations[0].@displayName ?
        clinicalDocument.setTitle("Recepta");

        TS effectiveTime = creatEffectiveTime(createEReceptaDlaLekGotowyInput);
        clinicalDocument.setEffectiveTime(effectiveTime);

        CE confidentialityCode = createConfidentialityCode();
        clinicalDocument.setConfidentialityCode(confidentialityCode);

        CS languageCode = createLanguageCode();
        clinicalDocument.setLanguageCode(languageCode);

        II setId = idFactory
                .create(Oid.IdentyfikatoryNadawanePrzezP1.RECEPTY_IDENTYFIKATORY_ZBIOROW_WERSJI, createEReceptaDlaLekGotowyInput.getSetId());
        clinicalDocument.setSetId(setId);

        INT versionNumber = createVersionNumber();
        clinicalDocument.setVersionNumber(versionNumber);

        POCDMT000040RecordTarget patientRecordTarget = plCdaDrugPrescriptionRecordTargetFactory.createPOCDMT000040RecordTarget(createEReceptaDlaLekGotowyInput);
        clinicalDocument.getRecordTargets()
                .add(patientRecordTarget);

        POCDMT000040Author pocdmt000040Author = plCdaReimbursementConformantDrugPrescriptionAuthorFactory.createPOCDMT000040Author(createEReceptaDlaLekGotowyInput);
        clinicalDocument.getAuthors()
                .add(pocdmt000040Author);

        POCDMT000040Custodian pocdmt000040Custodian = plCdaP1BaseCustodianFactory.createPOCDMT000040Custodian();
        clinicalDocument.setCustodian(pocdmt000040Custodian);

        POCDMT000040LegalAuthenticator pocdmt000040LegalAuthenticator = plCdaBaseLegalAuthenticatorFactory.createPOCDMT000040LegalAuthenticator(createEReceptaDlaLekGotowyInput);
        clinicalDocument.setLegalAuthenticator(pocdmt000040LegalAuthenticator);

        POCDMT000040Component2 pocdmt000040Component2 = createPocdmt000040Component2ForDocumentBody.createPOCDMT000040Component2(createEReceptaDlaLekGotowyInput);
        clinicalDocument.setComponent(pocdmt000040Component2);

        return clinicalDocument;
    }

    private POCDMT000040InfrastructureRootTypeId createPocdmt000040InfrastructureRootTypeId() {
        POCDMT000040InfrastructureRootTypeId pocdmt000040InfrastructureRootTypeId = objectFactoryForHl7V3.createPOCDMT000040InfrastructureRootTypeId();
        pocdmt000040InfrastructureRootTypeId.setRoot(Oid.Hl7.RMIM);
        pocdmt000040InfrastructureRootTypeId.setExtension("POCD_HD000040");

        return pocdmt000040InfrastructureRootTypeId;
    }

    private Collection<II> createTemplateIds() {
        Collection<II> templateIds = templateIdFactory.createMultiple(Oid.Ihe.COMMUNITY_PRESCRIPTION_CONTENT_MODULE, Oid.Ihe.MEDICAL_DOCUMENT);

        II ii3 = objectFactoryForHl7V3.createII();
        ii3.setRoot(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaDokumentow.REIMBURSEMENT_CONFORMANT_DRUG_PRESCRIPTION);
        // matches IG 1.3 (recepta v1.3.1)
        // when new version patch version will be released it has to be changed
        // when major/minor version will change OID has to be changed as well
        ii3.setExtension("1.3.1");
        templateIds.add(ii3);

        return templateIds;
    }

    private TS creatEffectiveTime(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        TS effectiveTime = objectFactoryForHl7V3.createTS();
        LocalDateTime createdAt = createEReceptaDlaLekGotowyInput.getCreatedAt();
        String createdAtString = DateTimeUtil.formatAsBasicDate(createdAt);
        effectiveTime.setValue(createdAtString);
        return effectiveTime;
    }

    private CE createConfidentialityCode() {
        CE confidentialityCode = objectFactoryForHl7V3.createCE();
        // https://www.hl7.org/fhir/v3/Confidentiality/cs.html
        // it is fixed for erecepta and N means: Privacy metadata indicating that the information is typical, non-stigmatizing health information,
        // which presents typical risk of harm if disclosed without authorization.
        confidentialityCode.setCode("N");
        confidentialityCode.setCodeSystem(Oid.Hl7.CONFIDENTIALITY);
        return confidentialityCode;
    }

    private CS createLanguageCode() {
        // template: https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.3-2018-09-30T000000.html
        // stands that it is forced to be pl-PL, but IG stands that it can be en-US as well for foreign patient
        CS languageCode = objectFactoryForHl7V3.createCS();
        languageCode.setCode("pl-PL");
        return languageCode;
    }

    private INT createVersionNumber() {
        INT versionNumber = objectFactoryForHl7V3.createINT();
        // always 1 for first version of document - incremented with updates and corrections
        versionNumber.setValue(BigInteger.ONE);
        return versionNumber;
    }
}
