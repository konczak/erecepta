package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.16-2014-06-06T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PoziomOdplatnosciZaLeki {

    BEZPLATNY("B", "bezpłatne"),
    RYCZALT("R", "ryczałt"),
    PLATNE_30_PROCENT("30%", "30% limitu"),
    PLATNE_50_PROCENT("50%", "50% limitu"),
    PLATNE_100_PROCENT("100%", "pełnopłatne");

    private final String code;
    private final String displayName;
}
