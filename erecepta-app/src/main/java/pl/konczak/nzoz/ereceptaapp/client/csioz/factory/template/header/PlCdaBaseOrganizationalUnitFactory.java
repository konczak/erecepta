package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.AD;
import org.hl7.v3.II;
import org.hl7.v3.ON;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Organization;
import org.hl7.v3.POCDMT000040OrganizationPartOf;
import org.hl7.v3.TEL;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.TelcomType;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype.PlCdaBaseAddrFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicalFacility;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.17-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaBaseOrganizationalUnitFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;
    private final PlCdaBaseAddrFactory plCdaBaseAddrFactory;

    public POCDMT000040Organization createPOCDMT000040Organization(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        MedicalFacility medicalFacility = createEReceptaDlaLekGotowyInput.getMedicalFacility();
        POCDMT000040Organization pocdmt000040Organization = objectFactoryForHl7V3.createPOCDMT000040Organization();

        II plCdaBaseOrganizationalUnitTemplateId = templateIdFactory.create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.BASE_ORGANIZATIONAL_UNIT);

        pocdmt000040Organization.getTemplateIds()
                .add(plCdaBaseOrganizationalUnitTemplateId);

        II idJednostkiOrganizacyjnej = idFactory.create(
                Oid.IdentyfikatoryPrzedsiebiorstw.RPWDL_PODMIOT_CZ_I_I_V_KODU_RESORTOWEGO,
                medicalFacility.getNumerKsiegiRejestrowejRpwdlJednostkiOrganizacyjne() + "-" + medicalFacility.getKodIdentyfikujacyJednostke(),
                true
        );

        pocdmt000040Organization.getIds()
                .add(idJednostkiOrganizacyjnej);

        ON organizationName = createOrganizationName(medicalFacility);

        pocdmt000040Organization.getNames()
                .add(organizationName);

        TEL tel = createTel(medicalFacility);

        pocdmt000040Organization.getTelecoms()
                .add(tel);

        AD ad = plCdaBaseAddrFactory.createAD(medicalFacility.getAddress());

        pocdmt000040Organization.getAddrs()
                .add(ad);

        POCDMT000040OrganizationPartOf pocdmt000040OrganizationPartOf = createPocdmt000040OrganizationPartOf(medicalFacility);
        pocdmt000040Organization.setAsOrganizationPartOf(pocdmt000040OrganizationPartOf);

        return pocdmt000040Organization;
    }

    private ON createOrganizationName(final MedicalFacility medicalFacility) {
        ON organizationName = objectFactoryForHl7V3.createON();
        organizationName.setValue(medicalFacility.getName());
        return organizationName;
    }

    private TEL createTel(final MedicalFacility medicalFacility) {
        TEL tel = objectFactoryForHl7V3.createTEL();
        tel.getUses()
                .add(TelcomType.PUBLIC.getCode());
        tel.setValue("tel:" + medicalFacility.getReceptionPhoneNumber());
        return tel;
    }

    private POCDMT000040OrganizationPartOf createPocdmt000040OrganizationPartOf(final MedicalFacility medicalFacility) {
        POCDMT000040OrganizationPartOf pocdmt000040OrganizationPartOf = objectFactoryForHl7V3.createPOCDMT000040OrganizationPartOf();

        POCDMT000040Organization pocdmt000040Organization = objectFactoryForHl7V3.createPOCDMT000040Organization();

        II regonPrzedsiebiorstwa = idFactory.create(Oid.IdentyfikatoryPrzedsiebiorstw.REGON_14_ZNAKOWY, medicalFacility.getRegon14(), true);
        pocdmt000040Organization.getIds()
                .add(regonPrzedsiebiorstwa);

        POCDMT000040OrganizationPartOf podmiotPocdmt000040OrganizationPartOf = createPodmiotPocdmt000040OrganizationPartOf(medicalFacility);

        pocdmt000040Organization.setAsOrganizationPartOf(podmiotPocdmt000040OrganizationPartOf);

        pocdmt000040OrganizationPartOf.setWholeOrganization(pocdmt000040Organization);

        return pocdmt000040OrganizationPartOf;
    }

    private POCDMT000040OrganizationPartOf createPodmiotPocdmt000040OrganizationPartOf(final MedicalFacility medicalFacility) {
        POCDMT000040OrganizationPartOf podmiotPocdmt000040OrganizationPartOf = objectFactoryForHl7V3.createPOCDMT000040OrganizationPartOf();
        POCDMT000040Organization podmiotPocdmt000040Organization = objectFactoryForHl7V3.createPOCDMT000040Organization();

        II podmiotRpwdl = idFactory.create(
                Oid.IdentyfikatoryPrzedsiebiorstw.RPWDL_PODMIOT_CZ_I_KODU_RESORTOWEGO,
                medicalFacility.getNumerKsiegiRejestrowejRpwdlPodmiotu(),
                true
        );

        podmiotPocdmt000040Organization.getIds()
                .add(podmiotRpwdl);

        podmiotPocdmt000040OrganizationPartOf.setWholeOrganization(podmiotPocdmt000040Organization);
        return podmiotPocdmt000040OrganizationPartOf;
    }
}
