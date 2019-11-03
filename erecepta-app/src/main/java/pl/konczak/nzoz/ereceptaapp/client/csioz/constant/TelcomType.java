package pl.konczak.nzoz.ereceptaapp.client.csioz.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * https://www.hl7.org/fhir/v3/AddressUse/cs.html
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TelcomType {

    HOME("H", "domowy"),
    HOME_PRIMARY("HP", "domowy"),
    VACATION_HOME("HV", "podczas urlopu"),
    WORK_PLACE("WP", "służbowy"),
    DIRECT("DIR", "służbowy bezpośredni"),
    PUBLIC("PUB", "recepcja"),
    TEMPORARY("TMP", "tymczasowy"),
    EMERGENCY_CONTACT("EC", "w nagłych przypadkach"),
    MOBILE_CONTACT("MC", "komórkowy");

    private final String code;
    private final String displayValue;

}
