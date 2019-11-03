package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.XRoleClassCoverage;
import pl.gov.csioz.xsd.extpl.r2.BoundedByParticipation;
import pl.gov.csioz.xsd.extpl.r2.BoundingParticipation;
import pl.gov.csioz.xsd.extpl.r2.ReimbursementRelatedContract;
import pl.gov.csioz.xsd.extpl.r2.Reimburser;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.44-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlExtReimbursementRelatedContractFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final pl.gov.csioz.xsd.extpl.r2.ObjectFactory objectFactoryForExtPl;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;

    public BoundedByParticipation createBoundedByParticipation(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        BoundedByParticipation boundedByParticipation = objectFactoryForExtPl.createBoundedByParticipation();

        // fixed
        boundedByParticipation.setTypeCode("PART");

        II templateId = templateIdFactory
                .create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.EXT_REIMBURSEMENT_RELATED_CONTRACT);
        boundedByParticipation.getTemplateIds()
                .add(templateId);

        ReimbursementRelatedContract reimbursementRelatedContract = createReimbursementRelatedContract(createEReceptaDlaLekGotowyInput);
        boundedByParticipation.setReimbursementRelatedContract(reimbursementRelatedContract);

        return boundedByParticipation;
    }

    private ReimbursementRelatedContract createReimbursementRelatedContract(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        ReimbursementRelatedContract reimbursementRelatedContract = objectFactoryForExtPl.createReimbursementRelatedContract();

        // both are fixed
        reimbursementRelatedContract.getClassCodes()
                .add("CNTRCT");
        reimbursementRelatedContract.getMoodCodes()
                .add("EVN");

        // TODO for NZOZ Zarnow OID for NFZ lodzkie will be just enough - but for more complex cases it should be configurable
        II idNrUmowyUslugodawdcyZNfz = idFactory.create(
                Oid.IdentyfikatoryDokumentowZewnetrznych.NUMERY_UMOW_NFZ_NUMERY_UMOW_SWIADCZENIODAWCOW_Z_NFZ_ZAWIERANE_W_ODDZIALE_LODZKIM,
                createEReceptaDlaLekGotowyInput.getNumerUmowyUslugodawcyZNfz(),
                false
        );
        reimbursementRelatedContract.setId(idNrUmowyUslugodawdcyZNfz);

        BoundingParticipation boundingParticipation = objectFactoryForExtPl.createBoundingParticipation();
        // fixed
        boundingParticipation.setTypeCode("PART");

        Reimburser reimburser = objectFactoryForExtPl.createReimburser();
        // fixed
        reimburser.getClassCodes()
                .add(XRoleClassCoverage.UNDWRT.toString());

        II nfzId = idFactory.create(
                Oid.IdentyfikatoryPlatnikow.PODMIOTY_ZOBOWIAZANE_DO_FINANSOWANIA_SWIADCZEN_ZE_SRODKOW_PUBLICZNYCH,
                createEReceptaDlaLekGotowyInput.getNfzWithUmowaUslugodawcy().getCode(),
                true
        );
        reimburser.setId(nfzId);

        boundingParticipation.setReimburser(reimburser);


        reimbursementRelatedContract.setBounding(boundingParticipation);

        return reimbursementRelatedContract;
    }

}
