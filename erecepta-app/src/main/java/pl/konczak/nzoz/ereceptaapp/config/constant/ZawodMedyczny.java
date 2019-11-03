package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.37-2017-09-30T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ZawodMedyczny {

    LEKARZ("LEK", "Lekarz"),
    FELCZER("FEL", "Felczer"),
    LEKARZ_DENTYSTA("LEKD", "Lekarz dentysta"),
    PIELEGNIARKA("PIEL", "Pielęgniarka"),
    POLOZNA("POL", "Położna"),
    FARMACEUTA("FARM", "Farmaceuta"),
    DIAGNOSTA_LABORATORYJNY("DLAB", "Diagnosta laboratoryjny"),
    TECHNIK_FARMACEUTYCZNY("TFARM", "Technik farmaceutyczny");

    private final String code;
    private final String displayName;
}
