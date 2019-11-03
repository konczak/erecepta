package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.6-2014-06-06T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum KategoriaDostepnosciLeku {

    DOSTEPNY_BEZ_RECEPTY("OTC", "OTC"),
    DOSTEPNY_NA_ZLECENIE_LEKARZA("Rp", "Rp"),
    DOSTEPNY_NA_ZLECENIE_LEKARZA_ZAWIERAJACY_SRODKI_ODURZAJACE_LUB_PSYCHOTROPOWE("Rpw", "Rpw"),
    DOSTEPNY_NA_ZLECENIE_LEKARZA_DO_ZASTRZEZONEGO_STOSOWANIA("Rpz", "Rpz"),
    DOSTEPNY_W_LECZNICTWIE_ZAMKNIETYM("Lz", "Lz");

    private final String code;
    private final String displayName;

}
