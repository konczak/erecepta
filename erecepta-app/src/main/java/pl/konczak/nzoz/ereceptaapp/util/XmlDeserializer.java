package pl.konczak.nzoz.ereceptaapp.util;

import lombok.extern.slf4j.Slf4j;
import pl.gov.csioz.xsd.extpl.r2.ClinicalDocument;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class XmlDeserializer {

    public JAXBElement<?> xmlToClinicalDocument(final File file) {
        JAXBElement jaxbElement;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ClinicalDocument.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            jaxbElement = (JAXBElement) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            log.error("Failed to unmarshall XML file <{}> to object because of JAXBException", file.getName());
            throw new RuntimeException(e);
        }
        return jaxbElement;
    }
}
