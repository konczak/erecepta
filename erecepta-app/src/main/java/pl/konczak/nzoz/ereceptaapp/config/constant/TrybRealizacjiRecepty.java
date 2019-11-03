package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.8-2014-06-06T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TrybRealizacjiRecepty {

    ZWYKLY("Z", "Zwykły"),
    IMPORT("I", "Import docelowy");

    private final String code;
    private final String displayName;
}
