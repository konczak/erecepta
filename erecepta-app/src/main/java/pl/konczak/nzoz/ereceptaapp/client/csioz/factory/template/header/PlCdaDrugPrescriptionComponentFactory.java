package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.header;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Component2;
import org.hl7.v3.POCDMT000040Component3;
import org.hl7.v3.POCDMT000040StructuredBody;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.section.PlCdaDrugPrescriptionSectionFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.section.PlCdaPayersSectionFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.2.25-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaDrugPrescriptionComponentFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final PlCdaDrugPrescriptionSectionFactory plCdaDrugPrescriptionSectionFactory;
    private final PlCdaPayersSectionFactory plCdaPayersSectionFactory;

    public POCDMT000040Component2 createPOCDMT000040Component2(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Component2 pocdmt000040Component2 = objectFactoryForHl7V3.createPOCDMT000040Component2();

        II documentHeaderComponentTemplateId = objectFactoryForHl7V3.createII();
        documentHeaderComponentTemplateId.setRoot(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowNaglowkaDokumentu.COMPONENT);

        pocdmt000040Component2.getTemplateIds()
                .add(documentHeaderComponentTemplateId);

        POCDMT000040StructuredBody pocdmt000040StructuredBody = objectFactoryForHl7V3.createPOCDMT000040StructuredBody();

        POCDMT000040Component3 pocdmt000040Component3ForPlCdaDrugPrescriptionSection = plCdaDrugPrescriptionSectionFactory
                .createPOCDMT000040Component3(createEReceptaDlaLekGotowyInput);
        pocdmt000040StructuredBody.getComponents()
                .add(pocdmt000040Component3ForPlCdaDrugPrescriptionSection);

        POCDMT000040Component3 pocdmt000040Component3ForPlCdaPayersSection = plCdaPayersSectionFactory
                .createPocdmt000040Component3ForPlCdaPayersSection(createEReceptaDlaLekGotowyInput);
        pocdmt000040StructuredBody.getComponents()
                .add(pocdmt000040Component3ForPlCdaPayersSection);

        pocdmt000040Component2.setStructuredBody(pocdmt000040StructuredBody);

        return pocdmt000040Component2;
    }
}
