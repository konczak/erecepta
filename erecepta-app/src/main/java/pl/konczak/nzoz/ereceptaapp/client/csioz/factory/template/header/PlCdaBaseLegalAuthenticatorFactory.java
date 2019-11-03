package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040AssignedEntity;
import org.hl7.v3.POCDMT000040LegalAuthenticator;
import org.hl7.v3.TS;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.config.constant.ParticipationSignature;
import pl.konczak.nzoz.ereceptaapp.config.constant.ZawodMedyczny;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.IdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicineDoctor;
import pl.konczak.nzoz.ereceptaapp.util.DateTimeUtil;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.6-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaBaseLegalAuthenticatorFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final IdFactory idFactory;

    public POCDMT000040LegalAuthenticator createPOCDMT000040LegalAuthenticator(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        MedicineDoctor medicineDoctor = createEReceptaDlaLekGotowyInput.getMedicineDoctor();

        POCDMT000040LegalAuthenticator pocdmt000040LegalAuthenticator = objectFactoryForHl7V3.createPOCDMT000040LegalAuthenticator();

        II legalAuthenticatorTemplateId = templateIdFactory
                .create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.LEGAL_AUTHENTICATOR);
        pocdmt000040LegalAuthenticator.getTemplateIds()
                .add(legalAuthenticatorTemplateId);

        TS time = createTs(createEReceptaDlaLekGotowyInput);

        pocdmt000040LegalAuthenticator.setTime(time);

        CS signatureCode = objectFactoryForHl7V3.createCS();
        // we can keep this hardcode as will always sign document
        signatureCode.setCode(ParticipationSignature.SIGNED.getCode());

        pocdmt000040LegalAuthenticator.setSignatureCode(signatureCode);

        POCDMT000040AssignedEntity pocdmt000040AssignedEntity = createPocdmt000040AssignedEntity(medicineDoctor);

        pocdmt000040LegalAuthenticator.setAssignedEntity(pocdmt000040AssignedEntity);

        return pocdmt000040LegalAuthenticator;
    }

    private TS createTs(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        // TODO there are few options of time - like date-time, time, period - probably factory would be a good idea
        TS time = objectFactoryForHl7V3.createTS();

        LocalDateTime createdAt = createEReceptaDlaLekGotowyInput.getCreatedAt();
        String createdAtString = DateTimeUtil.formatAsBasicDate(createdAt);
        time.setValue(createdAtString);

        return time;
    }

    private POCDMT000040AssignedEntity createPocdmt000040AssignedEntity(final MedicineDoctor medicineDoctor) {
        POCDMT000040AssignedEntity pocdmt000040AssignedEntity = objectFactoryForHl7V3.createPOCDMT000040AssignedEntity();

        II numerPrawaWykonywaniaZawoduId = idFactory
                .create(Oid.IdentyfikatoryOsob.NUMERY_PRAWA_WYKONYWANIA_ZAWODU_LEKARZE_DENTYSCI_FELCZERZY, medicineDoctor.getNumerPrawaWykonywaniaZawodu(), true);
        pocdmt000040AssignedEntity.getIds()
                .add(numerPrawaWykonywaniaZawoduId);

        CE code = objectFactoryForHl7V3.createCE();
        ZawodMedyczny zawodMedyczny = medicineDoctor.getZawodMedyczny();

        code.setCode(zawodMedyczny.getCode());
        code.setCodeSystem(Oid.SlownikiIZbioryWartosci.EXTERNAL_ZAWODY_MEDYCZNE);
        code.setDisplayName(zawodMedyczny.getDisplayName());

        pocdmt000040AssignedEntity.setCode(code);
        return pocdmt000040AssignedEntity;
    }
}
