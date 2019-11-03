package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.AD;
import org.hl7.v3.ADXP;
import org.hl7.v3.AdxpCensusTract;
import org.hl7.v3.AdxpCity;
import org.hl7.v3.AdxpCountry;
import org.hl7.v3.AdxpHouseNumber;
import org.hl7.v3.AdxpStreetName;
import org.hl7.v3.AdxpUnitID;
import org.hl7.v3.ObjectFactory;
import pl.gov.csioz.xsd.extpl.r2.AdxpPostalCode;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.Address;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.7.1-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaBaseAddrFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final pl.gov.csioz.xsd.extpl.r2.ObjectFactory objectFactoryForExtPl;

    public AD createAD(final Address address) {
        AD ad = objectFactoryForHl7V3.createAD();

        AdxpCountry country = objectFactoryForHl7V3.createAdxpCountry();
        country.setValue(address.getCountry());
        // TODO
        // actually postal code marshals as
        // <postalCode xsi:type="extPL:adxp.postalCode" xsi:type="extPL:adxp.postalCode" postCity="Piaseczno">03-134</postalCode>
        // what probably is fine but has to be checked in CSIOZ that will be accepted

        AdxpPostalCode postalCode = objectFactoryForExtPl.createAdxpPostalCode();
        postalCode.setPostCity(address.getPostOffice());
        postalCode.setValue(address.getPostCode());
        AdxpCity city = objectFactoryForHl7V3.createAdxpCity();
        city.setValue(address.getCity());
        AdxpStreetName streetName = objectFactoryForHl7V3.createAdxpStreetName();
        streetName.setValue(address.getStreet());
        AdxpHouseNumber houseNumber = objectFactoryForHl7V3.createAdxpHouseNumber();
        houseNumber.setValue(address.getHouseNumber());
        // TODO ensure that unitID is about apartment
        AdxpUnitID unitID = null;
        if (address.getApartmentNumber() != null) {
            unitID = objectFactoryForHl7V3.createAdxpUnitID();
            unitID.setValue(address.getApartmentNumber());
        }
        AdxpCensusTract censusTract = objectFactoryForHl7V3.createAdxpCensusTract();
        censusTract.setValue("TERYT SIMC: " + address.getCensusTractId());

        List<JAXBElement<? extends ADXP>> addressElements = Stream
                .of(
                        objectFactoryForHl7V3.createADCountry(country),
                        new JAXBElement<>(new QName("urn:hl7-org:v3", "postalCode"), AdxpPostalCode.class, AD.class, postalCode),
                        objectFactoryForHl7V3.createADCity(city),
                        objectFactoryForHl7V3.createADStreetName(streetName),
                        objectFactoryForHl7V3.createADHouseNumber(houseNumber),
                        unitID == null ? null : objectFactoryForHl7V3.createADUnitID(unitID),
                        objectFactoryForHl7V3.createADCensusTract(censusTract)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        ad.getContent()
                .addAll(addressElements);

        return ad;
    }
}
