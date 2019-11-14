package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import ihe.pharm.COCTMT230100UVContent;
import ihe.pharm.COCTMT230100UVPackagedMedicine;
import ihe.pharm.EntityClassContainer;
import ihe.pharm.PQ;
import ihe.pharm.RoleClassContent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.CE;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Material;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.config.constant.PostacOpakowaniaLeku;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.CodeFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.PrescribedDrug;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.54-2018-09-30T000000.html
 */
@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaMedicineOrSpecialFoodFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final CodeFactory codeFactory;

    public POCDMT000040Material createPocdmt000040Material(final PrescribedDrug prescribedDrug) {
        POCDMT000040Material pocdmt000040Material = objectFactoryForHl7V3.createPOCDMT000040Material();

        Collection<II> templateIds = templateIdFactory.createMultiple(Oid.Ihe.MEDICINE_ENTRY_CONTENT_MODULE, Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.MEDICINE_OR_SPECIAL_FOOD);
        pocdmt000040Material.getTemplateIds()
                .addAll(templateIds);

        String codeValue = createDrugName(prescribedDrug);

        // code is selectable
        CE code = codeFactory.create(
                Oid.Leki.IDENTYFIKATORY_LEKOW_Z_BAZY_PRODUKTOW_LECZNICZYCH_URPL_REJESTR_LEKOW_P1,
                prescribedDrug.getIdInRejestrProduktowLeczniczych(),
                codeValue
        );
        pocdmt000040Material.setCode(code);

        pocdmt000040Material.setName(codeValue);

        return pocdmt000040Material;
    }

    private String createDrugName(final PrescribedDrug prescribedDrug) {
        // nazwa produktu, moc i postać farmaceutyczna, rozdzielanych spacją
        // Instrukcja stosowania PIK HL7 CDA_20181130_v_1.3.1.pdf page 132
        // ex. Polocard 150mg tabletk
        // but there is case for 'Dane opakowania' where it should be 'nazwa produktu, moc oraz w nawiasie liczba dawek i postać'
        // ex. Enarenal 5mg (60 tabl.)

        return prescribedDrug.getNazwaProduktuLeczniczego()
                + " "
                + prescribedDrug.getMoc()
                + " "
                + prescribedDrug.getPostacFarmaceutyczna();
    }

}
