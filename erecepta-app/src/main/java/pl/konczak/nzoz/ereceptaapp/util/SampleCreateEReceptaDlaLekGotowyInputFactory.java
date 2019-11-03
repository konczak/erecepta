package pl.konczak.nzoz.ereceptaapp.util;

import pl.konczak.nzoz.ereceptaapp.config.constant.KategoriaDostepnosciLeku;
import pl.konczak.nzoz.ereceptaapp.config.constant.Nfz;
import pl.konczak.nzoz.ereceptaapp.config.constant.PostacOpakowaniaLeku;
import pl.konczak.nzoz.ereceptaapp.config.constant.PoziomOdplatnosciZaLeki;
import pl.konczak.nzoz.ereceptaapp.config.constant.RodzajLeku;
import pl.konczak.nzoz.ereceptaapp.config.constant.SpecjalnoscLekarza;
import pl.konczak.nzoz.ereceptaapp.config.constant.TrybRealizacjiRecepty;
import pl.konczak.nzoz.ereceptaapp.config.constant.TrybWystawieniaRecepty;
import pl.konczak.nzoz.ereceptaapp.config.constant.UprawnienieDodatkowe;
import pl.konczak.nzoz.ereceptaapp.config.constant.ZawodMedyczny;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Address;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Gender;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicalFacility;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicineDoctor;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Patient;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.PrescribedDrug;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class SampleCreateEReceptaDlaLekGotowyInputFactory {

    public CreateEReceptaDlaLekGotowyInput createSampleCreateEReceptaDlaLekGotowyInput() {
        // temporarily anchor now to specific date for test and comparision purpose
        LocalDateTime now = LocalDateTime.now();
        // LocalDateTime now = LocalDateTime.of(2013, 4, 12, 12, 0, 0, 0);

        Address patientHomeAddress = Address.builder()
                .country("Polska")
                .city("Warszawa")
                .street("Odkryta")
                .houseNumber("41")
                .apartmentNumber("12")
                .postCode("03-134")
                .postOffice("Piaseczno")
                .censusTractId("987654")
                .build();

        Patient patient = Patient.builder()
                .id("PACJENT_ID_1111")
                .pesel("12300509785")
                .birthAt(LocalDate.of(1962, 9, 15))
                .gender(Gender.MALE)
                .firstName("ANNA")
                .lastName("KASINA")
                .homeAddress(patientHomeAddress)
                .assignedTo(Nfz.MAZOWIECKI)
                .build();

        MedicineDoctor medicineDoctor = MedicineDoctor.builder()
                .numerPrawaWykonywaniaZawodu("7391208")
                .prefix("lek.")
                .firstName("Adam106")
                .lastName("Leczniczy")
                .specjalnoscLekarza(SpecjalnoscLekarza.MEDYCYNA_RODZINNA)
                .zawodMedyczny(ZawodMedyczny.LEKARZ)
                .build();

        Address medicalFacilityAddress = Address.builder()
                .country("Polska")
                .city("Warszawa")
                .street("ul. Dubois")
                .houseNumber("5A")
                .apartmentNumber("12")
                .postCode("00-184")
                // TODO occured that organization address does not have neither post office and censusTract
                .postOffice("Warszawa")
                .censusTractId("1464011")
                .build();

        MedicalFacility medicalFacility = MedicalFacility.builder()
                .regon9("840947835")
                .regon14("84094783500004")
                .name("Poradnia POZ")
                .address(medicalFacilityAddress)
                .receptionPhoneNumber("22-1111123")
                .numerKsiegiRejestrowejRpwdlPodmiotu("000000926578")
                .numerKsiegiRejestrowejRpwdlJednostkiOrganizacyjne("84094782100004")
                .kodIdentyfikujacyJednostke("01")
                .numerKsiegiRejestrowejRpwdlKomorkiOrganizacyjne("84094782100004")
                .kodIdentyfikujacyKomorke("001")
                .build();

        PrescribedDrug prescribedDrug = PrescribedDrug.builder()
                .id("PRESCRIBED_DRUG_ID_12345")
                .validFrom(now.toLocalDate())
                .validTo(now.plusDays(30).toLocalDate())
                .idInRejestrProduktowLeczniczych("100021973")
                .eanOfPackage("05909990014958")
                .postacOpakowaniaLeku(PostacOpakowaniaLeku.TABLET_CONTAINER)
                .packageQuantity("1")
                .nazwaProduktuLeczniczego("Enarenal")
                .moc("5 mg")
                .postacFarmaceutyczna("tabletki")
                .zawartoscOpakowania("60 tabl.")
                .packageCount("1")
                // TODO this is quite questionable
                .dawkaStosowania("D.S. 2 x 1 tabl.")
                .build();

        return CreateEReceptaDlaLekGotowyInput.builder()
                .id("RECEPTA_ID_1234")
                .version("v1")
                .createdAt(now)
                .patient(patient)
                .medicineDoctor(medicineDoctor)
                .medicalFacility(medicalFacility)
                .prescribedDrug(prescribedDrug)
                .uprawnienieDodatkowe(UprawnienieDodatkowe.ZASLUZENIE_DAWCY_KRWI_I_PRZESZCZEPU)
                .documentConfirmOfUprawnieniaDodatkowe("Nr leg.: 238/2015")
                .numerUmowyUslugodawcyZNfz("07-20048-33")
                .nfzWithUmowaUslugodawcy(Nfz.MAZOWIECKI)
                .kategoriaDostepnosciLeku(KategoriaDostepnosciLeku.DOSTEPNY_NA_ZLECENIE_LEKARZA)
                .rodzajLeku(RodzajLeku.GOTOWY)
                .trybWystawieniaRecepty(TrybWystawieniaRecepty.ZWYKLA)
                .trybRealizacjiRecepty(TrybRealizacjiRecepty.ZWYKLY)
                .poziomOdplatnosciZaLeki(PoziomOdplatnosciZaLeki.RYCZALT)
                .build();
    }
}
