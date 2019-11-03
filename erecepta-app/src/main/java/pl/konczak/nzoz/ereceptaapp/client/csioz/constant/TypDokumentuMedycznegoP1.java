package pl.konczak.nzoz.ereceptaapp.client.csioz.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.35-2014-09-23T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TypDokumentuMedycznegoP1 {

    RECEPTA("04.01", "Recepta"),
    SKIEROWANIE_NA_BADANIE_LUB_LECZENIE("02.10", "Skierowanie na badanie lub leczenie"),
    SKIEROWANIE_NA_KONSULTACJE("02.11", "Skierowanie na konsultację"),
    PROSBA_O_OBJECIE_OPIEKA("02.12", "Prośba o objęcie opieką"),
    ZLECENIE_NA_ZAOPATRZENIE_W_WYROBY_MEDYCZNE("02.81", "Zlecenie na zaopatrzenie w wyroby medyczne");

    private final String code;
    private final String displayName;
}
