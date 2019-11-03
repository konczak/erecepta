package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.1.11.10282-2014-03-26T000000.html
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ParticipationSignature {

    INTENDED("I", "intended"),
    SIGNED("S", "signed"),
    REQUIRED("X", "required");

    private final String code;
    private final String displayName;

}
