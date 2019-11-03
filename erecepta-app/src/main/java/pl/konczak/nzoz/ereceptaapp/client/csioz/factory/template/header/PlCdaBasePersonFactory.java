package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.PN;
import org.hl7.v3.POCDMT000040Person;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype.PlCdaBasePersonNameFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicineDoctor;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.1-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaBasePersonFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final PlCdaBasePersonNameFactory plCdaBasePersonNameFactory;

    public POCDMT000040Person createPOCDMT000040Person(final MedicineDoctor medicineDoctor) {
        POCDMT000040Person pocdmt000040Person = objectFactoryForHl7V3.createPOCDMT000040Person();

        II assignedPersonTemplateId = templateIdFactory.create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.BASE_PERSON);
        pocdmt000040Person.getTemplateIds()
                .add(assignedPersonTemplateId);

        PN name = plCdaBasePersonNameFactory.createPN(medicineDoctor);

        pocdmt000040Person.getNames()
                .add(name);

        return pocdmt000040Person;
    }

}
