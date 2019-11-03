package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO not used yet but should be

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.81-2018-09-30T000000.html
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostacDawkiLeku {

    ORAL_DROPS_SOLUTION("10101000", "Oral drops, solution"),
    ORAL_DROPS_SUSPENSION("10102000", "Oral drops, suspension"),
    ORAL_DROPS_EMULSION("10103000", "Oral drops, emulsion"),
    ORAL_LIQUID("10104000", "Oral liquid"),
    ORAL_SOLUTION("10105000", "Oral solution"),
    ORAL_SUSPENSION("10106000", "Oral suspension"),
    ORAL_EMULSION("10107000", "Oral emulsion"),
    ORAL_GEL("10108000", "Oral gel"),
    ORAL_PASTE("10109000", "Oral paste"),
    POWDER_FOR_ORAL_SOLUTION("10110000", "Powder for oral solution"),
    POWDER_FOR_ORAL_SUSPENSION("10111000", "Powder for oral suspension"),
    GRANULES_FOR_ORAL_SOLUTION("10112000", "Granules for oral solution"),
    GRANULES_FOR_ORAL_SUSPENSION("10113000", "Granules for oral suspension"),
    POWDER_AND_SOLVENT_FOR_ORAL_SOLUTION("10114000", "Powder and solvent for oral solution"),
    POWDER_AND_SOLVENT_FOR_ORAL_SUSPENSION("10115000", "Powder and solvent for oral suspension"),
    LYOPHILISATE_FOR_SUSPENSION("10116000", "Lyophilisate for suspension"),
    SYRUP("10117000", "Syrup"),
    POWDER_FOR_SYRUP("10118000", "Powder for syrup"),
    GRANULES_FOR_SYRUP("10119000", "Granules for syrup"),
    SOLUBLE_TABLET("10120000", "Soluble tablet"),
    DISPERSIBLE_TABLET("10121000", "Dispersible tablet"),
    HERBAL_TEA("10122000", "Herbal tea"),
    ORAL_POWDER("10201000", "Oral powder"),
    INSTANT_HERBAL_TEA("10202000", "Instant herbal tea"),
    EFFERVESCENT_POWDER("10203000", "Effervescent powder"),
    GRANULES("10204000", "Granules"),
    EFFERVESCENT_GRANULES("10205000", "Effervescent granules"),
    GASTRO_RESISTANT_GRANULES("10206000", "Gastro-resistant granules"),
    PROLONGED_RELEASE_GRANULES("10207000", "Prolonged-release granules"),
    MODIFIED_RELEASE_GRANULES("10208000", "Modified-release granules"),
    CACHET("10209000", "Cachet"),
    CAPSULE_HARD("10210000", "Capsule, hard"),
    CAPSULE_SOFT("10211000", "Capsule, soft"),
    GASTRO_RESISTANT_CAPSULE_HARD("10212000", "Gastro-resistant capsule, hard"),
    GASTRO_RESISTANT_CAPSULE_SOFT("10213000", "Gastro-resistant capsule, soft"),
    CHEWABLE_CAPSULE_SOFT("10214000", "Chewable capsule, soft"),
    PROLONGED_RELEASE_CAPSULE_HARD("10215000", "Prolonged-release capsule, hard"),
    PROLONGED_RELEASE_CAPSULE_SOFT("10216000", "Prolonged-release capsule, soft"),
    MODIFIED_RELEASE_CAPSULE_HARD("10217000", "Modified-release capsule, hard"),
    MODIFIED_RELEASE_CAPSULE_SOFT("10218000", "Modified-release capsule, soft"),
    TABLET("10219000", "Tablet"),
    COATED_TABLET("10220000", "Coated tablet"),
    FILM_COATED_TABLET("10221000", "Film-coated tablet"),
    EFFERVESCENT_TABLET("10222000", "Effervescent tablet"),
    ORODISPERSIBLE_TABLET("10223000", "Orodispersible tablet"),
    ORAL_LYOPHILISATE("10224000", "Oral lyophilisate"),
    GASTRO_RESISTANT_TABLET("10225000", "Gastro-resistant tablet"),
    PROLONGED_RELEASE_TABLET("10226000", "Prolonged-release tablet"),
    MODIFIED_RELEASE_TABLET("10227000", "Modified-release tablet"),
    CHEWABLE_TABLET("10228000", "Chewable tablet"),
    MEDICATED_CHEWING_GUM("10229000", "Medicated chewing-gum"),
    ORAL_GUM("10230000", "Oral gum"),
    PILLULES("10231000", "Pillules"),
    ORODISPERSIBLE_FILM("10236100", "Orodispersible film"),
    GARGLE("10301000", "Gargle"),
    CONCENTRATE_FOR_GARGLE("10302000", "Concentrate for gargle"),
    GARGLE_POWDER_FOR_SOLUTION("10303000", "Gargle, powder for solution"),
    GARGLE_TABLET_FOR_SOLUTION("10304000", "Gargle, tablet for solution"),
    OROMUCOSAL_SOLUTION("10305000", "Oromucosal solution"),
    OROMUCOSAL_SUSPENSION("10306000", "Oromucosal suspension"),
    OROMUCOSAL_DROPS("10307000", "Oromucosal drops"),
    OROMUCOSAL_SPRAY("10308000", "Oromucosal spray"),
    SUBLINGUAL_SPRAY("10309000", "Sublingual spray"),
    MOUTHWASH("10310000", "Mouthwash"),
    MOUTHWASH_TABLET_FOR_SOLUTION("10311000", "Mouthwash, tablet for solution"),
    GINGIVAL_SOLUTION("10312000", "Gingival solution"),
    OROMUCOSAL_GEL("10313000", "Oromucosal gel"),
    OROMUCOSAL_PASTE("10314000", "Oromucosal paste"),
    OROMUCOSAL_OINTMENT("10314005", "Oromucosal ointment"),
    OROMUCOSAL_CREAM("10314010", "Oromucosal cream"),
    BUCCAL_FILM("10314011", "Buccal film"),
    GINGIVAL_GEL("10315000", "Gingival gel"),
    GINGIVAL_PASTE("10316000", "Gingival paste"),
    OROMUCOSAL_CAPSULE("10317000", "Oromucosal capsule"),
    SUBLINGUAL_TABLET("10318000", "Sublingual tablet"),
    MUCO_ADHESIVE_BUCCAL_TABLET("10319000", "Muco-adhesive buccal tablet"),
    BUCCAL_TABLET("10320000", "Buccal tablet");

    private final String code;
    private final String displayName;

}
