package pl.konczak.nzoz.ereceptaapp.client.csioz.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.hl7.org/fhir/v3/ActCode/cs.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ActCode {

    PUBLIC_HEALTH_CARE("PUBLICPOL", "public healthcare");

    private final String code;
    private final String displayValue;
}
