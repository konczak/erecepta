package pl.konczak.nzoz.ereceptaapp.client.csioz.input;

import lombok.Builder;
import lombok.Getter;
import pl.konczak.nzoz.ereceptaapp.config.constant.PostacOpakowaniaLeku;

import java.math.BigInteger;
import java.time.LocalDate;

@Builder
@Getter
public class PrescribedDrug {

    private final String id;
    private final LocalDate validFrom;
    private final LocalDate validTo;
    // TODO this indicates repeat count on prescription - temporarily hardcode it but in future needs to be extended
    private final BigInteger repeatNumber = BigInteger.ONE;
    private final PostacOpakowaniaLeku postacOpakowaniaLeku;
    // TODO package quantity has to be read from package - if there is package with 56 pils - packageQuantity = 56
    private final String packageQuantity;

    // new ones from Rejestr Produktow Leczniczych
    private final String idInRejestrProduktowLeczniczych;
    private final String nazwaProduktuLeczniczego;
    private final String moc;
    private final String postacFarmaceutyczna;
    private final String eanOfPackage;
    private final String zawartoscOpakowania;
    // TODO could be number instead of String?
    private final String packageCount;

    private final String dawkaStosowania;

}
