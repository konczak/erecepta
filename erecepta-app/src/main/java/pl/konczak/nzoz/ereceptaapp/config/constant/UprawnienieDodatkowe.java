package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO jest jeszcze typ UM nie wymieniony na stronie CSIOZ
// osoba, inna niż ubezpieczona, posiadająca określone uprawnienia do bezpłatnych świadczeń opieki zdrowotnej na podstawie wiążących Rzeczypospolitą Polskę umów dwustronnych.

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.15-2017-04-26T000000.html
 * single person can have multiple UprawnienieDodatkowe but on single drug can use only one
 * <p>
 * http://www.archiwum.mz.gov.pl/leki/refundacja/recepta-na-leki-refundowane/dodatkowe-uprawnienia/
 * http://www.leksykon.com.pl/uprawnienia-dodatkowe-pacjentow-niezbdne-dokumenty-40-artykul.html
 * http://www.medicam.pl/index.php/pl/dla-pacjenta/2013-07-30-06-37-54/recepty/uprawnienia
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UprawnienieDodatkowe {

    PRACOWNICY_PRZY_AZBESCIE("AZ", "AZ"),
    NIEUBEZPIECZONY_ZATWIERDZONY_PRZEZ_BURMISTRZA_LUB_WOJTA("BW", "BW"), // osoby posiadające prawo do korzystania ze świadczeń opieki zdrowotnej na podstawie decyzji wójta (burmistrza, prezydenta) gminy właściwej ze względu na miejsce zamieszkania.
    NIEUBEZPIECZONA_W_CIAZY("CN", "CN"),
    DZIECKO_RODZICOW_NIEUBEZPIECZONYCH("DN", "DN"), // ale takie dziecko jest zawsze ubezpieczone
    INWALIDZI_WOJENNI_Z_GRUPA_INWALIDZKA("IB", "IB"),
    // TODO jest wiele pod typów A,N,F,C,K,Z,G
    OSOBA_INNA_NIZ_UBEZPIECZONA_POSIADAJACA_UPRAWNIENIA_DO_BEZPLATNYCH_SWIADCZEN("IN", "IN"), //czasami gmina wydaje zaświadczenie o ubezpieczniu (problematyczny przypadek)
    INWALIDZI_WOJENNI_BEZ_GRUPY_INWALIDZKIEJ("IW", "IW"),
    OSOBY_WYKONUJACY_OBOWIAZEK_OBRONY("PO", "PO"), // np. żołnierze
    OSOBY_WYMIENIONE_W_USTAWIE_O_POWSZECHNYM_OBOWIAZKU_OBRONY_RP("WP", "WP"), // np. członkowie rodzin żołnierzy
    ZASLUZENIE_DAWCY_KRWI_I_PRZESZCZEPU("ZK", "ZK"),
    SENIOR("S", "S");  //senior czyli 75+

    private final String code;
    private final String displayName;

}
