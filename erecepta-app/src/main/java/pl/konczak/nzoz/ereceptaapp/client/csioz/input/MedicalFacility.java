package pl.konczak.nzoz.ereceptaapp.client.csioz.input;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MedicalFacility {

    // Numery księgi rejestrowej RPWDL - Podmiot (cz. I kodu resortowego)
    private final String numerKsiegiRejestrowejRpwdlPodmiotu;
    // Numery księgi rejestrowej RPWDL - Jednostki organizacyjne (cz. I i V kodu resortowego)
    private final String numerKsiegiRejestrowejRpwdlJednostkiOrganizacyjne; // I
    private final String kodIdentyfikujacyJednostke;    // V
    // Numery księgi rejestrowej RPWDL - Komórki organizacyjne (cz. I i VII kodu resortowego)
    private final String numerKsiegiRejestrowejRpwdlKomorkiOrganizacyjne;   // I
    private final String kodIdentyfikujacyKomorke;  // VII
    private final String regon9;
    private final String regon14;
    private final String name;
    private final Address address;
    private final String receptionPhoneNumber;

}
