package pl.konczak.nzoz.ereceptaapp.client.csioz.constant;

public final class Oid {

    // 	https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.82-2018-09-30T000000.html
    public static final String POSTAC_OPAKOWANIA_LEKU = "0.4.0.127.0.16.1.1.2.1";

    public static final class Hl7 {

        // http://oidref.com/2.16.840.1.113883.1.3
        // Health Level 7 (HL7) registered Refined Message Information Models (RMIMs)
        public final static String RMIM = "2.16.840.1.113883.1.3";

        public static final String ORGANIZATION_CSIOZ = "2.16.840.1.113883.3.4424";

        public static final String ADMINISTRATIVE_GENDER = "2.16.840.1.113883.5.1";

        // http://wiki.hl7.de/index.php?title=2.16.840.1.113883.5.4
        public static final String HL_7_ACT_CODE = "2.16.840.1.113883.5.4";

        // https://www.hl7.org/fhir/v3/Confidentiality/cs.html
        public static final String CONFIDENTIALITY = "2.16.840.1.113883.5.25";

        public static final class CdaTemplates {

            public static final String SECTION_PAYERS_SECTION = "2.16.840.1.113883.10.20.1.9";

            public static final String ENTRY_AUTHORIZATION_ACTIVITY = "2.16.840.1.113883.10.20.1.19";

            public static final String ENTRY_COVERAGE_ACTIVITY = "2.16.840.1.113883.10.20.1.20";

            public static final String ENTRY_MEDICATION_ACTIVITY = "2.16.840.1.113883.10.20.1.24";

            // http://art-decor.org/decor/services/RetrieveTemplate?prefix=ccd1-&version=&language=en-US&ui=en-US&id=2.16.840.1.113883.10.20.1.26&effectiveDate=2007-04-01T00:00:00&format=html&collapsable=true
            public static final String ENTRY_POLICY_ACTIVITY = "2.16.840.1.113883.10.20.1.26";

            public static final String ENTRY_TEMPLATE_PRODUCT = "2.16.840.1.113883.10.20.1.53";
        }

    }

    // https://wiki.ihe.net/index.php/PCC_Vocabulary_Registry_and_Data_Dictionary
    public static final class Ihe {

        // IHEMedical Document - Medical Documents Specification
        public static final String MEDICAL_DOCUMENT = "1.3.6.1.4.1.19376.1.5.3.1.1.1";

        public static final String PAYERS = "1.3.6.1.4.1.19376.1.5.3.1.1.5.3.7";

        public static final String MEDICATIONS = "1.3.6.1.4.1.19376.1.5.3.1.4.7";

        // A "normal" <substanceAdministration> act that may not contain any subordinate <substanceAdministration> acts.
        public static final String MEDICATIONS_DOSE_NORMAL = "1.3.6.1.4.1.19376.1.5.3.1.4.7.1";

        public static final String PRODUCT_ENTRY = "1.3.6.1.4.1.19376.1.5.3.1.4.7.2";

        // A <substanceAdministration> act that records tapered dose information in subordinate <substanceAdministration> act.
        // TAPERED ~ DECREASING
        public static final String MEDICATIONS_DOSE_TAPERED = "1.3.6.1.4.1.19376.1.5.3.1.4.8";

        // A <substanceAdministration> act that records split dose information in subordinate <substanceAdministration> acts.
        public static final String MEDICATIONS_DOSE_SPLIT = "1.3.6.1.4.1.19376.1.5.3.1.4.9";

        // A <substanceAdministration> act that records conditional dose information in subordinate <substanceAdministration> acts.
        public static final String MEDICATIONS_DOSE_CONDITIONAL = "1.3.6.1.4.1.19376.1.5.3.1.4.10";

        // A <substanceAdministration> act that records combination medication component information in subordinate <substanceAdministration> acts.
        public static final String MEDICATIONS_DOSE_COMBINATION = "1.3.6.1.4.1.19376.1.5.3.1.4.11";

        // IHEPHARM PRE, Pharmacy Prescription IHECommunityPrescriptionContentModule
        // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.3-2018-09-30T000000.html
        public static final String COMMUNITY_PRESCRIPTION_CONTENT_MODULE = "1.3.6.1.4.1.19376.1.9.1.1.1";

        public static final String PRESCRIPTION_SECTION_CONTENT_MODULE = "1.3.6.1.4.1.19376.1.9.1.2.1";

        public static final String MEDICINE_ENTRY_CONTENT_MODULE = "1.3.6.1.4.1.19376.1.9.1.3.1";

        public static final String PRESCRIPTION_ITEM_ENTRY_CONTENT_MODULE = "1.3.6.1.4.1.19376.1.9.1.3.2";

        public static final String PHARMACY_ENTRY_TEMPLATE_CHANGED_DISPENSE_ITEM_ENTRY_CONTENT_MODULE = "1.3.6.1.4.1.19376.1.9.1.3.6";

        public static final String DRUG_PRESCRIPTION_SUPPLY_ENTRY_BASE_TEMPLATE = "1.3.6.1.4.1.19376.1.9.1.3.8";
    }


    // 2.16.840.1.113883.3.4424.1
    public static final class IdentyfikatoryOsob {

        public static final String KRAJOWE_IDETYFIKATORY_OSOB_W_PANSTWACH_UE_I_STREFY_SCHENGEN_POLSKA = "2.16.840.1.113883.3.4424.1.1.616";

        public static final String NUMERY_PRAWA_WYKONYWANIA_ZAWODU_LEKARZE_DENTYSCI_FELCZERZY = "2.16.840.1.113883.3.4424.1.6.2";
    }

    // 2.16.840.1.113883.3.4424.2
    public static final class IdentyfikatoryPrzedsiebiorstw {


        public static final String REGON_9_ZNAKOWY = "2.16.840.1.113883.3.4424.2.2.1";

        public static final String REGON_14_ZNAKOWY = "2.16.840.1.113883.3.4424.2.2.2";

        public static final String RPWDL_PODMIOT_CZ_I_KODU_RESORTOWEGO = "2.16.840.1.113883.3.4424.2.3.1";

        public static final String RPWDL_PODMIOT_CZ_I_I_V_KODU_RESORTOWEGO = "2.16.840.1.113883.3.4424.2.3.2";

        public static final String KOMORKI_ORGANIZACYJNE_CZ_I_I_VII_KODU_RESORTOWEGO = "2.16.840.1.113883.3.4424.2.3.3";


        // 2.16.840.1.113883.3.4424.2.7.{x}
        // 2.16.840.1.113883.3.4424.2.7.201
        public static final class WezelUslugodawcy {

            // Węzeł OID konta usługodawcy w P1, stosowany przez usługodawcę zarejestrowanego w P1
            // TODO if it will be different for production and test solution has to be changed
            private static final String WEZEL_USLUGODAWCY = "2.16.840.1.113883.3.4424.2.7.201";

            public static final String IDENTYFIKATORY_INSTANCJI_RECEPT_U_USLUGODAWCY = WEZEL_USLUGODAWCY + ".2.1";

            public static final String IDENTYFIKATORY_POZYCJI_RECEPTY_U_USLUGODAWCY = WEZEL_USLUGODAWCY + ".2.3";

            public static final String IDENTYFIKATORY_SEKCJI_ZALECENIA_LEKU_W_RECEPCIE_U_USLUGODAWCY = WEZEL_USLUGODAWCY + ".2.4";

            public static final String IDENTYFIKATOR_PACJENTA_W_SYSTEMIE_USLUGODAWCY = WEZEL_USLUGODAWCY + ".17.1";
        }
    }

    // 2.16.840.1.113883.3.4424.3
    public static final class IdentyfikatoryPlatnikow {

        public static final String PODMIOTY_ZOBOWIAZANE_DO_FINANSOWANIA_SWIADCZEN_ZE_SRODKOW_PUBLICZNYCH = "2.16.840.1.113883.3.4424.3.1";
    }

    // 2.16.840.1.113883.3.4424.4
    public static final class IdentyfikatoryUbezpieczycieli {

    }

    // 2.16.840.1.113883.3.4424.6
    public static final class Leki {

        public static final String IDENTYFIKATORY_LEKOW_Z_BAZY_PRODUKTOW_LECZNICZYCH_URPL_REJESTR_LEKOW_P1 = "2.16.840.1.113883.3.4424.6.1";

        public static final String IDENTYFIKATOR_GLOBALNY_ZGODNY_Z_SYSTEMEM_GS_1 = "1.3.160";
    }

    // 2.16.840.1.113883.3.4424.7
    public static final class IdentyfikatoryNadawanePrzezP1 {

        public static final String RECEPTY_IDENTYFIKATORY_ZBIOROW_WERSJI = "2.16.840.1.113883.3.4424.7.2.2";
    }

    // 2.16.840.1.113883.3.4424.8
    public static final class IdentyfikatoryDokumentowZewnetrznych {

        // P1-DS-E6-Drzewo_OID_0.5.9_20181122.xls + https://ws-int-p1.csioz.gov.pl/ [23.05.2019 Komunikat dot. budowy dok. e-recepty / kod platnika NFZ swiadczeniodawcy]
        public static final String NUMERY_UMOW_NFZ_NUMERY_UMOW_SWIADCZENIODAWCOW_Z_NFZ_ZAWIERANE_W_ODDZIALE_LODZKIM = "2.16.840.1.113883.3.4424.8.6.1.5";
    }

    // 2.16.840.1.113883.3.4424.10
    public static final class Rejestry {

    }

    // 2.16.840.1.113883.3.4424.11
    public static final class SlownikiIZbioryWartosci {

        public static final String P1_POZIOMY_ODPLATNOSCI_LEKOW = "2.16.840.1.113883.3.4424.11.1.1";

        public static final String P1_KATEGORIE_DOSTEPNOSCI_LEKOW = "2.16.840.1.113883.3.4424.11.1.25";

        public static final String P1_KLASYFIKACJA_DOKUMENTOW_WG_PROJEKTU_P1 = "2.16.840.1.113883.3.4424.11.1.32";

        public static final String EXTERNAL_UPRAWNIENIA_DODATKOWE_ZWIAZANE_Z_REFUNDACJA_LEKOW = "2.16.840.1.113883.3.4424.11.3.1";

        // values are at https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.21-2014-06-06T000000.html
        public static final String EXTERNAL_SPECJALNOSCI_LEKARSKIE = "2.16.840.1.113883.3.4424.11.3.3";

        // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.17-2014-06-06T000000.html
        public static final String EXTERNAL_ZAWODY_MEDYCZNE = "2.16.840.1.113883.3.4424.11.3.18";

        public static final String EXTERNAL_LOINC = "2.16.840.1.113883.6.1";

    }

    // 2.16.840.1.113883.3.4424.12
    public static final class PlatformaP1 {

    }

    // 2.16.840.1.113883.3.4424.13
    public static final class PolskaImplementacjaKrajowaHl7Cda {

        // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.8-2014-06-06T000000.html
        public static final String SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3 = "2.16.840.1.113883.3.4424.13.5.1";

        // 2.16.840.1.113883.3.4424.13.10.1
        public static final class WzorceCdaDokumentow {

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.3-2018-09-30T000000.html
            public static final String DRUG_PRESCRIPTION_V_1_3 = "2.16.840.1.113883.3.4424.13.10.1.3";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.26-2018-09-30T000000.html
            public static final String REIMBURSEMENT_CONFORMANT_DRUG_PRESCRIPTION = "2.16.840.1.113883.3.4424.13.10.1.26";
        }

        // 2.16.840.1.113883.3.4424.13.10.2
        public static final class WzorceCdaElementowNaglowkaDokumentu {

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.1-2018-06-30T000000.html
            public static final String BASE_PERSON = "2.16.840.1.113883.3.4424.13.10.2.1";

            public static final String LEGAL_AUTHENTICATOR = "2.16.840.1.113883.3.4424.13.10.2.6";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.17-2018-06-30T000000.html
            public static final String BASE_ORGANIZATIONAL_UNIT = "2.16.840.1.113883.3.4424.13.10.2.17";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.18-2018-06-30T000000.html
            public static final String BASE_ORGANIZATIONAL_CELL = "2.16.840.1.113883.3.4424.13.10.2.18";

            public static final String CUSTODIAN = "2.16.840.1.113883.3.4424.13.10.2.20";

            public static final String DRUG_PRESCRIPTION_RECORD_TARGET = "2.16.840.1.113883.3.4424.13.10.2.23";

            public static final String COMPONENT = "2.16.840.1.113883.3.4424.13.10.2.25";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.44-2018-06-30T000000.html
            public static final String EXT_REIMBURSEMENT_RELATED_CONTRACT = "2.16.840.1.113883.3.4424.13.10.2.44";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.79-2018-06-30T000000.html
            public static final String REIMBURSEMENT_CONFORMANT_DRUG_PRESCRIPTION_AUTHOR = "2.16.840.1.113883.3.4424.13.10.2.79";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.82-2018-09-30T000000.html
            public static final String DRUG_PRESCRIPTION_AUTHOR = "2.16.840.1.113883.3.4424.13.10.2.82";
        }

        // 2.16.840.1.113883.3.4424.13.10.3
        public static final class WzorceCdaSekcjiDokumentow {

            public static final String DRUG_PRESCRIPTION_SECTION = "2.16.840.1.113883.3.4424.13.10.3.4";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.3.69-2018-06-30T000000.html
            public static final String PAYERS_SECTION = "2.16.840.1.113883.3.4424.13.10.3.69";
        }

        // 2.16.840.1.113883.3.4424.13.10.4
        public static final class WzorceCdaElementowSekcjiDokumentu {

            public static final String DRUG_PRESCRIPTION_ENTRY = "2.16.840.1.113883.3.4424.13.10.4.3";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.51-2018-06-30T000000.html
            public static final String COVERAGE_ACTIVITY_ENTRY = "2.16.840.1.113883.3.4424.13.10.4.51";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.53-2018-06-30T000000.html
            public static final String AUTHORIZATION_ACTIVITY_ENTRY = "2.16.840.1.113883.3.4424.13.10.4.53";

            public static final String MEDICINE_OR_SPECIAL_FOOD = "2.16.840.1.113883.3.4424.13.10.4.54";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.55-2018-09-30T000000.html
            public static final String DRUG_PRESCRIPTION_SUPPLY_ENTRY = "2.16.840.1.113883.3.4424.13.10.4.55";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.57-2018-06-30T000000.html
            public static final String DRUG_PAYMENT_LEVEL = "2.16.840.1.113883.3.4424.13.10.4.57";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.59-2018-06-30T000000.html
            public static final String ENTITLEMENT_DOCUMENT = "2.16.840.1.113883.3.4424.13.10.4.59";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.61-2018-06-30T000000.html
            public static final String SPECIAL_ENTITLEMENT_POLICY_ENTRY = "2.16.840.1.113883.3.4424.13.10.4.61";
        }

        // 2.16.840.1.113883.3.4424.13.11
        public static final class TypyWartosci {

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.5-2017-10-26T000000.html
            public static final String RODZAJ_LEKU = "2.16.840.1.113883.3.4424.13.11.5";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.6-2014-06-06T000000.html
            public static final String KATEGORIA_DOSTEPNOSCI_LEKU = "2.16.840.1.113883.3.4424.13.11.6";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.7-2014-06-06T000000.html
            public static final String TRYB_WYSTAWIENIA_RECEPTY = "2.16.840.1.113883.3.4424.13.11.7";

            // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.8-2014-06-06T000000.html
            public static final String TRYB_REALIZACJI_RECEPTY = "2.16.840.1.113883.3.4424.13.11.8";
        }

    }

    // 2.16.840.1.113883.3.4424.14
    public static final class SystemyZewnetrzneDlaP1 {

    }


}
