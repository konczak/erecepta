package pl.konczak.nzoz.ereceptaapp.client.csioz.factory;

import org.hl7.v3.CD;
import org.hl7.v3.CE;
import org.hl7.v3.CR;
import org.hl7.v3.CV;
import org.hl7.v3.ObjectFactory;
import pl.konczak.nzoz.ereceptaapp.config.constant.KategoriaDostepnosciLeku;
import pl.konczak.nzoz.ereceptaapp.config.constant.Loinc;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.config.constant.RodzajLeku;
import pl.konczak.nzoz.ereceptaapp.config.constant.TrybRealizacjiRecepty;
import pl.konczak.nzoz.ereceptaapp.config.constant.TrybWystawieniaRecepty;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.TypDokumentuMedycznegoP1;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CodeForDictionaryTranslationAndAutoAnalysisFactory {

    private final ObjectFactory objectFactoryForHl7V3;

    public CodeForDictionaryTranslationAndAutoAnalysisFactory() {
        this.objectFactoryForHl7V3 = new ObjectFactory();
    }

    public CE createCodeForDictionaryTranslationAndAutoAnalysis(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        CE codeForDictionaryTranslationAndAutoAnalysis = objectFactoryForHl7V3.createCE();
        codeForDictionaryTranslationAndAutoAnalysis.setCode(Loinc.PRESCRIPTION_FOR_MEDICATION.getCode());
        codeForDictionaryTranslationAndAutoAnalysis.setCodeSystem(Oid.SlownikiIZbioryWartosci.EXTERNAL_LOINC);
        codeForDictionaryTranslationAndAutoAnalysis.setCodeSystemName("LOINC");
        codeForDictionaryTranslationAndAutoAnalysis.setDisplayName("Prescription for medication Document");

        CD translation = objectFactoryForHl7V3.createCD();
        TypDokumentuMedycznegoP1 typDokumentuMedycznegoP1 = TypDokumentuMedycznegoP1.RECEPTA;
        // https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.35-2014-09-23T000000.html
        translation.setCode(typDokumentuMedycznegoP1.getCode());
        translation.setCodeSystem(Oid.SlownikiIZbioryWartosci.P1_KLASYFIKACJA_DOKUMENTOW_WG_PROJEKTU_P1);
        translation.setCodeSystemName("KLAS_DOK_P1");
        // TODO this should be synchronized with ClinicalDocument.title ?
        translation.setDisplayName(typDokumentuMedycznegoP1.getDisplayName());

        List<CR> qualifiers = translation.getQualifiers();
        qualifiers.add(createConceptRoleForKDLEK(createEReceptaDlaLekGotowyInput));
        qualifiers.add(createConceptRoleForRLEK(createEReceptaDlaLekGotowyInput));
        qualifiers.add(createConceptRoleForTWREC(createEReceptaDlaLekGotowyInput));
        qualifiers.add(createConceptRoleForTRREC(createEReceptaDlaLekGotowyInput));

        codeForDictionaryTranslationAndAutoAnalysis.getTranslations()
                .add(translation);

        return codeForDictionaryTranslationAndAutoAnalysis;
    }

    private CR createConceptRoleForKDLEK(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        CR conceptRoleForKDLEK = objectFactoryForHl7V3.createCR();

        // TODO it will depend on KDLEK from input
        // <qualifier>
        //     <name code="KDLEK" codeSystem="2.16.840.1.113883.3.4424.13.5.1" codeSystemName="PolskieKlasyfikatoryHL7v3" displayName="Kategoria dostępności leku"/>
        //     <value code="Rp" codeSystem="2.16.840.1.113883.3.4424.11.1.25"/>
        // </qualifier>

        CV codedValue = objectFactoryForHl7V3.createCV();
        codedValue.setCode("KDLEK");
        codedValue.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        codedValue.setCodeSystemName("PolskieKlasyfikatoryHL7v3");
        codedValue.setDisplayName("Kategoria dostępności leku");

        CD codedDescription = objectFactoryForHl7V3.createCD();
        KategoriaDostepnosciLeku kategoriaDostepnosciLeku = createEReceptaDlaLekGotowyInput.getKategoriaDostepnosciLeku();

        codedDescription.setCode(kategoriaDostepnosciLeku.getCode());
        codedDescription.setCodeSystem(Oid.SlownikiIZbioryWartosci.P1_KATEGORIE_DOSTEPNOSCI_LEKOW);

        conceptRoleForKDLEK.setName(codedValue);
        conceptRoleForKDLEK.setValue(codedDescription);

        return conceptRoleForKDLEK;
    }

    private CR createConceptRoleForRLEK(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        // TODO it will depend on RLEK from input
        // <qualifier>
        //     <name code="RLEK" codeSystem="2.16.840.1.113883.3.4424.13.5.1" codeSystemName="PolskieKlasyfikatoryHL7v3" displayName="Rodzaj leku"/>
        //     <value code="G" codeSystem="2.16.840.1.113883.3.4424.13.5.1" displayName="Lek gotowy"/>
        // </qualifier>
        CR conceptRoleForRLEK = objectFactoryForHl7V3.createCR();

        CV codedValue = objectFactoryForHl7V3.createCV();
        codedValue.setCode("RLEK");
        codedValue.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        codedValue.setCodeSystemName("PolskieKlasyfikatoryHL7v3");
        codedValue.setDisplayName("Rodzaj leku");

        CD codedDescription = objectFactoryForHl7V3.createCD();
        RodzajLeku rodzajLeku = createEReceptaDlaLekGotowyInput.getRodzajLeku();

        codedDescription.setCode(rodzajLeku.getCode());
        // WARN logically this code system is wrong but in sample it is like that and server rejects different values
        codedDescription.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        codedDescription.setDisplayName(rodzajLeku.getDisplayName());

        conceptRoleForRLEK.setName(codedValue);
        conceptRoleForRLEK.setValue(codedDescription);

        return conceptRoleForRLEK;
    }

    private CR createConceptRoleForTWREC(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        // TODO it will depend on TWREC from input
        // <qualifier>
        //     <name code="TWREC" codeSystem="2.16.840.1.113883.3.4424.13.5.1" codeSystemName="PolskieKlasyfikatoryHL7v3" displayName="Tryb wystawienia recepty"/>
        //     <value code="Z" codeSystem="2.16.840.1.113883.3.4424.13.5.1" displayName="Zwykła"/>
        // </qualifier>
        CR conceptRoleForTWREC = objectFactoryForHl7V3.createCR();

        CV codedValue = objectFactoryForHl7V3.createCV();
        codedValue.setCode("TWREC");
        codedValue.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        codedValue.setCodeSystemName("PolskieKlasyfikatoryHL7v3");
        codedValue.setDisplayName("Tryb wystawienia recepty");

        CD codedDescription = objectFactoryForHl7V3.createCD();
        TrybWystawieniaRecepty trybWystawieniaRecepty = createEReceptaDlaLekGotowyInput.getTrybWystawieniaRecepty();

        codedDescription.setCode(trybWystawieniaRecepty.getCode());
        // WARN logically this code system is wrong but in sample it is like that and server rejects different values
        codedDescription.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        codedDescription.setDisplayName(trybWystawieniaRecepty.getDisplayName());

        conceptRoleForTWREC.setName(codedValue);
        conceptRoleForTWREC.setValue(codedDescription);
        return conceptRoleForTWREC;
    }

    private CR createConceptRoleForTRREC(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        // TODO it will depend on TRREC from input - on the other hand most of it is "Fixed" and only value is dynamic
        // <qualifier>
        //     <name code="TRREC" codeSystem="2.16.840.1.113883.3.4424.13.5.1" codeSystemName="PolskieKlasyfikatoryHL7v3" displayName="Tryb realizacji recepty"/>
        //     <value code="Z" codeSystem="2.16.840.1.113883.3.4424.13.5.1" displayName="Zwykły"/>
        // </qualifier>
        CR conceptRoleForTRREC = objectFactoryForHl7V3.createCR();

        CV codedValue = objectFactoryForHl7V3.createCV();
        codedValue.setCode("TRREC");
        codedValue.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        codedValue.setCodeSystemName("PolskieKlasyfikatoryHL7v3");
        codedValue.setDisplayName("Tryb realizacji recepty");

        CD codedDescription = objectFactoryForHl7V3.createCD();
        TrybRealizacjiRecepty trybRealizacjiRecepty = createEReceptaDlaLekGotowyInput.getTrybRealizacjiRecepty();

        codedDescription.setCode(trybRealizacjiRecepty.getCode());
        // WARN logically this code system is wrong but in sample it is like that and server rejects different values
        codedDescription.setCodeSystem(Oid.PolskaImplementacjaKrajowaHl7Cda.SYSTEMY_KODOWANIA_POLSKIE_KLASYFIKATORY_HL7_V3);
        codedDescription.setDisplayName(trybRealizacjiRecepty.getDisplayName());

        conceptRoleForTRREC.setName(codedValue);
        conceptRoleForTRREC.setValue(codedDescription);

        return conceptRoleForTRREC;
    }
}
