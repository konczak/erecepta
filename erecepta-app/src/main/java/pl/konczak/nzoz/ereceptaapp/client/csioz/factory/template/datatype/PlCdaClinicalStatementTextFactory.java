package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.ED;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.TEL;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.7.3-2018-06-30T000000.html
 */
@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaClinicalStatementTextFactory {

    private final ObjectFactory objectFactoryForHl7V3;

    public ED createTextForVisualReference(final String prefix, final String suffix) {
        ED ed = objectFactoryForHl7V3.createED();
        TEL tel = objectFactoryForHl7V3.createTEL();
        tel.setValue("#" + prefix + "_" + suffix);
        ed.setReference(tel);
        return ed;
    }
}
