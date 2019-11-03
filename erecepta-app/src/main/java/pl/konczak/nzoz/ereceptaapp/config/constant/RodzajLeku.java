package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.5-2017-10-26T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RodzajLeku {

    GOTOWY("G", "Lek gotowy"),
    RECEPTUROWY("R", "Lek recepturowy"),
    SRODEK_SPOZYWCZY_SPECJALNEGO_PRZEZNACZENIA_ZYWIENIOWEGO("S", "Środek spożywczy specjalnego przeznaczenia żywieniowego"),
    WYROB_MEDYCZNY("W", "Wyrób medyczny");

    private final String code;
    private final String displayName;
}
