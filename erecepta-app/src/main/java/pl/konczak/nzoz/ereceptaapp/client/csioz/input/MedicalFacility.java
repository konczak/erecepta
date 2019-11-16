package pl.konczak.nzoz.ereceptaapp.client.csioz.input;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MedicalFacility {

    private final Address rpwdlKomorkaAddress;
    private final String receptionPhoneNumber;
    private final String rpwdlPodmiotNumerKsiegiRejestrowej;
    private final String rpwdlRegonPodmiotu9Znakow;
    private final String rpwdlRegonZakladuMedycznego14Znakow;
    private final String rpwdlKomorkaKodIdentyfikujacyKomorke;
    private final String rpwdlKomorkaName;

}
