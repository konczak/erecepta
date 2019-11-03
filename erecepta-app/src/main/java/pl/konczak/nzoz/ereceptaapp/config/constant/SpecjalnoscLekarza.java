package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.21-2014-06-06T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SpecjalnoscLekarza {

    ALERGOLOGIA("0731", "alergologia"),
    ANESTEZJOLOGIA_I_INTENSYWNA_TERAPIA("0701", "anestezjologia i intensywna terapia"),
    ANGIOLOGIA("0732", "angiologia"),
    AUDIOLOGIA_I_FONIATRIA("0733", "audiologia i foniatria"),
    BALNEOLOGIA_I_MEDYCYNA_FIZYKALNA("0734", "balneologia i medycyna fizykalna"),
    CHIRURGIA_DZIECIECA("0702", "chirurgia dziecięca"),
    CHIRURGIA_KLATKI_PIERSIOWEJ("0735", "chirurgia klatki piersiowej"),
    CHIRURGIA_NACZYNIOWA("0736", "chirurgia naczyniowa"),
    CHIRURGIA_OGOLNA("0703", "chirurgia ogólna"),
    CHIRURGIA_ONKOLOGICZNA("0737", "chirurgia onkologiczna"),
    CHIRURGIA_PLASTYCZNA("0738", "chirurgia plastyczna"),
    CHIRURGIA_SZCZEKOWO_TWARZOWA("0704", "chirurgia szczękowo-twarzowa"),
    CHOROBY_PLUC("0739", "choroby płuc"),
    CHOROBY_PLUC_DZIECI("0792", "choroby płuc dzieci"),
    CHOROBY_WEWNETRZNE("0705", "choroby wewnętrzne"),
    CHOROBY_ZAKAZNE("0706", "choroby zakaźne"),
    DERMATOLOGIA_I_WENEROLOGIA("0707", "dermatologia i wenerologia"),
    DIABETOLOGIA("0740", "diabetologia"),
    DIAGNOSTYKA_LABORATORYJNA("0708", "diagnostyka laboratoryjna"),
    ENDOKRYNOLOGIA("0741", "endokrynologia"),
    ENDOKRYNOLOGIA_GINEKOLOGICZNA_I_ROZRODCZOSC("0799", "endokrynologia ginekologiczna i rozrodczość"),
    ENDOKRYNOLOGIA_I_DIABETOLOGIA_DZIECIECA("0796", "endokrynologia i diabetologia dziecięca"),
    EPIDEMIOLOGIA("0710", "epidemiologia"),
    FARMAKOLOGIA_KLINICZNA("0742", "farmakologia kliniczna"),
    GASTROENTEROLOGIA("0743", "gastroenterologia"),
    GASTROENTEROLOGIA_DZIECIECA("0797", "gastroenterologia dziecięca"),
    GENETYKA_KLINICZNA("0709", "genetyka kliniczna"),
    GERIATRIA("0744", "geriatria"),
    GINEKOLOGIA_ONKOLOGICZNA("0787", "ginekologia onkologiczna"),
    HEMATOLOGIA("0745", "hematologia"),
    HIPERTENSJOLOGIA("0788", "hipertensjologia"),
    IMMUNOLOGIA_KLINICZNA("0746", "immunologia kliniczna"),
    INTENSYWNA_TERAPIA("0801", "intensywna terapia"),
    KARDIOCHIRURGIA("0747", "kardiochirurgia"),
    KARDIOLOGIA("0748", "kardiologia"),
    KARDIOLOGIA_DZIECIECA("0762", "kardiologia dziecięca"),
    MEDYCYNA_LOTNICZA("0793", "medycyna lotnicza"),
    MEDYCYNA_MORSKA_I_TROPIKALNA("0794", "medycyna morska i tropikalna"),
    MEDYCYNA_NUKLEARNA("0749", "medycyna nuklearna"),
    MEDYCYNA_PALIATYWNA("0750", "medycyna paliatywna"),
    MEDYCYNA_PRACY("0711", "medycyna pracy"),
    MEDYCYNA_RATUNKOWA("0712", "medycyna ratunkowa"),
    MEDYCYNA_RODZINNA("0713", "medycyna rodzinna"),
    MEDYCYNA_SADOWA("0714", "medycyna sądowa"),
    MEDYCYNA_SPORTOWA("0751", "medycyna sportowa"),
    MIKROBIOLOGIA_LEKARSKA("0716", "mikrobiologia lekarska"),
    NEFROLOGIA("0752", "nefrologia"),
    NEFROLOGIA_DZIECIECA("0798", "nefrologia dziecięca"),
    NEONATOLOGIA("0753", "neonatologia"),
    NEUROCHIRURGIA("0717", "neurochirurgia"),
    NEUROLOGIA("0718", "neurologia"),
    NEUROLOGIA_DZIECIECA("0763", "neurologia dziecięca"),
    NEUROPATOLOGIA("0789", "neuropatologia"),
    OKULISTYKA("0719", "okulistyka"),
    ONKOLOGIA_I_HEMATOLOGIA_DZIECIECA("0755", "onkologia i hematologia dziecięca"),
    ONKOLOGIA_KLINICZNA("0754", "onkologia kliniczna"),
    ORTOPEDIA_I_TRAUMATOLOGIA_NARZADU_RUCHU("0720", "ortopedia i traumatologia narządu ruchu"),
    OTORYNOLARYNGOLOGIA("0721", "otorynolaryngologia"),
    OTORYNOLARYNGOLOGIA_DZIECIECA("0790", "otorynolaryngologia dziecięca"),
    PATOMORFOLOGIA("0722", "patomorfologia"),
    PEDIATRIA("0723", "pediatria"),
    PEDIATRIA_METABOLICZNA("0795", "pediatria metaboliczna"),
    PERINATOLOGIA("0800", "perinatologia"),
    POLOZNICTWO_I_GINEKOLOGIA("0724", "położnictwo i ginekologia"),
    PSYCHIATRIA("0725", "psychiatria"),
    PSYCHIATRIA_DZIECI_I_MLODZIEZY("0756", "psychiatria dzieci i młodzieży"),
    RADIOLOGIA_I_DIAGNOSTYKA_OBRAZOWA("0726", "radiologia i diagnostyka obrazowa"),
    RADIOTERAPIA_ONKOLOGICZNA("0727", "radioterapia onkologiczna"),
    REHABILITACJA_MEDYCZNA("0728", "rehabilitacja medyczna"),
    REUMATOLOGIA("0757", "reumatologia"),
    SEKSUOLOGIA("0758", "seksuologia"),
    TOKSYKOLOGIA_KLINICZNA("0759", "toksykologia kliniczna"),
    TRANSFUZJOLOGIA_KLINICZNA("0760", "transfuzjologia kliniczna"),
    TRANSPLANTOLOGIA_KLINICZNA("0761", "transplantologia kliniczna"),
    UROLOGIA("0729", "urologia"),
    UROLOGIA_DZIECIECA("0791", "urologia dziecięca"),
    ZDROWIE_PUBLICZNE("0730", "zdrowie publiczne");

    private final String code;
    private final String displayName;

}
