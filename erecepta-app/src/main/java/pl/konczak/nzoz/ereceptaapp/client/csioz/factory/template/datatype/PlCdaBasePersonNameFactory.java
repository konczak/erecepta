package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.ENXP;
import org.hl7.v3.EnFamily;
import org.hl7.v3.EnGiven;
import org.hl7.v3.EnPrefix;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.PN;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.MedicineDoctor;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Patient;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.7.2-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaBasePersonNameFactory {

    private final ObjectFactory objectFactoryForHl7V3;

    public PN createPN(final Patient patient) {
        EnGiven enGiven = createEnGiven(patient.getFirstName());
        EnFamily enFamily = createEnFamily(patient.getLastName());

        return createPNFrom(enGiven, enFamily);
    }

    public PN createPN(final MedicineDoctor medicineDoctor) {
        EnPrefix enPrefix = createEnPrefix(medicineDoctor.getPrefix());
        EnGiven enGiven = createEnGiven(medicineDoctor.getFirstName());
        EnFamily enFamily = createEnFamily(medicineDoctor.getLastName());

        return createPNFrom(enPrefix, enGiven, enFamily);
    }

    private PN createPNFrom(final ENXP... enxps) {
        PN personName = objectFactoryForHl7V3.createPN();

        List<? extends JAXBElement<? extends ENXP>> personNameElements = Stream.of(enxps)
                .map(this::wrapIntoJAXBElement)
                .collect(Collectors.toList());

        personName.getContent()
                .addAll(personNameElements);

        return personName;

    }

    private EnPrefix createEnPrefix(final String prefix) {
        EnPrefix enPrefix = objectFactoryForHl7V3.createEnPrefix();
        enPrefix.setValue(prefix);
        return enPrefix;
    }

    private EnGiven createEnGiven(final String firstName) {
        EnGiven enGiven = objectFactoryForHl7V3.createEnGiven();
        enGiven.setValue(firstName);
        return enGiven;
    }

    private EnFamily createEnFamily(final String lastName) {
        EnFamily enFamily = objectFactoryForHl7V3.createEnFamily();
        enFamily.setValue(lastName);
        return enFamily;
    }

    private JAXBElement<? extends ENXP> wrapIntoJAXBElement(final ENXP enxp) {
        if (enxp instanceof EnGiven) {
            return objectFactoryForHl7V3.createENGiven((EnGiven) enxp);
        }

        if (enxp instanceof EnFamily) {
            return objectFactoryForHl7V3.createENFamily((EnFamily) enxp);
        }

        if (enxp instanceof EnPrefix) {
            return objectFactoryForHl7V3.createENPrefix((EnPrefix) enxp);
        }
        throw new UnsupportedOperationException("Ups, seems that wrapping of ENXP <" + enxp.getClass().getSimpleName() + "> is not supported yet");
    }
}
