package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040AssignedCustodian;
import org.hl7.v3.POCDMT000040Custodian;
import org.hl7.v3.POCDMT000040CustodianOrganization;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.20-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaP1BaseCustodianFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;

    public POCDMT000040Custodian createPOCDMT000040Custodian() {
        POCDMT000040Custodian pocdmt000040Custodian = objectFactoryForHl7V3.createPOCDMT000040Custodian();

        II custodianTemplateId = templateIdFactory
                .create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.CUSTODIAN);
        pocdmt000040Custodian.getTemplateIds()
                .add(custodianTemplateId);

        POCDMT000040AssignedCustodian pocdmt000040AssignedCustodian = objectFactoryForHl7V3.createPOCDMT000040AssignedCustodian();
        POCDMT000040CustodianOrganization pocdmt000040CustodianOrganization = objectFactoryForHl7V3.createPOCDMT000040CustodianOrganization();

        II csiozOrganizationId = createId();

        pocdmt000040CustodianOrganization.getIds()
                .add(csiozOrganizationId);

        pocdmt000040AssignedCustodian.setRepresentedCustodianOrganization(pocdmt000040CustodianOrganization);

        pocdmt000040Custodian.setAssignedCustodian(pocdmt000040AssignedCustodian);

        return pocdmt000040Custodian;
    }

    private II createId() {
        // TODO consider how to move this code to IdFactory nicely

        II csiozOrganizationId = objectFactoryForHl7V3.createII();

        csiozOrganizationId.setRoot(Oid.Hl7.ORGANIZATION_CSIOZ);
        csiozOrganizationId.setDisplayable(false);
        csiozOrganizationId.setAssigningAuthorityName("CSIOZ");

        return csiozOrganizationId;
    }
}
