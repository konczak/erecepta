package pl.konczak.nzoz.ereceptaapp.util;

import lombok.extern.slf4j.Slf4j;
import pl.gov.csioz.xsd.extpl.r2.ClinicalDocument;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class XmlSerializer {

    public String clinicalDocumentToXml(final ClinicalDocument clinicalDocument) {
        String result;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ClinicalDocument.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // To format XML
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // TODO is this required? it works without it - however Google claims that is good idea to have it
            // jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema");
            // found at https://www.javatips.net/api/elexis-3-base-master/ch.docbox.elexis/src/ch/docbox/cdach/DocboxCDA.java#
            // marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:hl7-org:v3 CDA.xsd");

            // TODO what it does?
            // found at https://www.javatips.net/api/elexis-3-base-master/ch.docbox.elexis/src/ch/docbox/cdach/DocboxCDA.java#
            // marshaller.setProperty(Marshaller.JAXB_FRAGMENT, new Boolean(true));

            //as we cannot control source code and put XmlRootElement over it we need to instruct JAXB to handle that
            JAXBElement<ClinicalDocument> jaxbElement =
                    new JAXBElement<>(new QName("urn:hl7-org:v3", "ClinicalDocument"),
                            ClinicalDocument.class,
                            clinicalDocument);

            jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());

            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml-stylesheet href=\"CDA_PL_IG_1.3.1.xsl\" type=\"text/xsl\"?>");

            StringWriter writer = new StringWriter();
            jaxbMarshaller.marshal(jaxbElement, writer);
            result = writer.toString();
        } catch (JAXBException e) {
            log.error("Failed to marshal object <{}> to XML because of JAXBException", clinicalDocument);
            throw new RuntimeException(e);
        }
        return result;
    }

}
