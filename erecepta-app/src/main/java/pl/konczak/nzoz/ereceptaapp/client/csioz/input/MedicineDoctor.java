package pl.konczak.nzoz.ereceptaapp.client.csioz.input;

import lombok.Builder;
import lombok.Getter;
import pl.konczak.nzoz.ereceptaapp.config.constant.SpecjalnoscLekarza;
import pl.konczak.nzoz.ereceptaapp.config.constant.ZawodMedyczny;

@Builder
@Getter
public class MedicineDoctor {

    private final String numerPrawaWykonywaniaZawodu;
    private final String prefix;
    private final String firstName;
    private final String lastName;
    private final SpecjalnoscLekarza specjalnoscLekarza;
    private final ZawodMedyczny zawodMedyczny;
}
