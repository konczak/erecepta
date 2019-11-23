package pl.konczak.nzoz.ereceptaapp.util;

import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Address;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Gender;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicalFacility;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicineDoctor;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Patient;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.PrescribedDrug;
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

        Address rpwdlKomorkaAddress = Address.builder()
                .country("Polska")
                .city("Warszawa")
                .street("ul. Dubois")
                .houseNumber("5A")
                .postCode("00-184")
                .build();

        MedicalFacility medicalFacility = MedicalFacility.builder()
                .rpwdlKomorkaAddress(rpwdlKomorkaAddress)
                .receptionPhoneNumber("22-1111123")
                .rpwdlPodmiotNumerKsiegiRejestrowej("000000926578")
                .rpwdlRegonPodmiotu9Znakow("840947835")
                .rpwdlRegonZakladuMedycznego14Znakow("84094783500004")
                .rpwdlKomorkaName("Ogólna izba przyjęć")
                .rpwdlKomorkaKodIdentyfikujacyKomorke("001")
                .build();

        PrescribedDrug prescribedDrug = PrescribedDrug.builder()
                .id("PRESCRIBED_DRUG_ID_12345")
                .validFrom(now.toLocalDate())
                .validTo(now.plusDays(30).toLocalDate())
                .idInRejestrProduktowLeczniczych("100173074")
                .eanOfPackage("05909990017454")
                .postacOpakowaniaLeku(PostacOpakowaniaLeku.TABLET_CONTAINER)
                .packageQuantity("1")
                .nazwaProduktuLeczniczego("Ramistad 5")
                .moc("5 mg")
                .postacFarmaceutyczna("tabletki")
                .zawartoscOpakowania("28 szt. (2 blist.po 14 szt.)")
                .packageCount("1")
                // TODO this is quite questionable
                .dawkaStosowania("D.S. 2 x 1 tabl.")
                .build();

        return CreateEReceptaDlaLekGotowyInput.builder()
                .setId("123456789012345678901235")
                .id("1234567890ABCDEFGHIJKM")
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
