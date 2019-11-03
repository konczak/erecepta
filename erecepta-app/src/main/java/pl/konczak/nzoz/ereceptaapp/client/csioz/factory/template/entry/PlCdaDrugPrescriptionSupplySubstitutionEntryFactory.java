package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Act;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.56-2018-06-30T000000.html
 *
 * read about it:
 * Instrukcja stosowania PIK HL7 CDA_20181130_v_1.3.1.pdf
 * page 59
 * (2) Brak zgody na wydanie zamiennika leku - 2.16.840.1.113883.3.4424.13.10.4.56
 * musi łączyć się z oznaczeniem "NZ"
 */
@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaDrugPrescriptionSupplySubstitutionEntryFactory {

    private final ObjectFactory objectFactoryForHl7V3;

    public POCDMT000040Act createPOCDMT000040Act(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Act pocdmt000040Act = objectFactoryForHl7V3.createPOCDMT000040Act();

        if (1 == 1) {
            throw new RuntimeException("Not used atm - not sure that we need it");
        }

        return pocdmt000040Act;
    }
}
