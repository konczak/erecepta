package pl.konczak.nzoz.ereceptaapp.client.csioz.input;

import lombok.Builder;
import lombok.Getter;
import pl.konczak.nzoz.ereceptaapp.config.constant.KategoriaDostepnosciLeku;
import pl.konczak.nzoz.ereceptaapp.config.constant.Nfz;
import pl.konczak.nzoz.ereceptaapp.config.constant.PoziomOdplatnosciZaLeki;
import pl.konczak.nzoz.ereceptaapp.config.constant.RodzajLeku;
import pl.konczak.nzoz.ereceptaapp.config.constant.TrybRealizacjiRecepty;
import pl.konczak.nzoz.ereceptaapp.config.constant.TrybWystawieniaRecepty;
import pl.konczak.nzoz.ereceptaapp.config.constant.UprawnienieDodatkowe;

import java.time.LocalDateTime;

@Builder
@Getter
public class CreateEReceptaDlaLekGotowyInput {

    // in sample 24 characters long has passed SoapUI; it had only [0-9] characters
    private final String setId;
    // keep it exactly 22 characters long; it can be only [A-Z0-9]
    private final String id;
    private final LocalDateTime createdAt;
    private final Patient patient;
    private final MedicineDoctor medicineDoctor;
    private final MedicalFacility medicalFacility;
    private final PrescribedDrug prescribedDrug;
    // TODO this has to be reevaluated - single patient can have multiple ones; also maybe it should apply per drug not per prescription
    private final UprawnienieDodatkowe uprawnienieDodatkowe;
    // TODO probably in near future will need better handling for UprawnienieDodatkowe type + document
    private final String documentConfirmOfUprawnieniaDodatkowe;
    private final String numerUmowyUslugodawcyZNfz;
    private final Nfz nfzWithUmowaUslugodawcy;
    private final KategoriaDostepnosciLeku kategoriaDostepnosciLeku;
    private final RodzajLeku rodzajLeku;
    private final TrybWystawieniaRecepty trybWystawieniaRecepty;
    private final TrybRealizacjiRecepty trybRealizacjiRecepty;
    private final PoziomOdplatnosciZaLeki poziomOdplatnosciZaLeki;

}
