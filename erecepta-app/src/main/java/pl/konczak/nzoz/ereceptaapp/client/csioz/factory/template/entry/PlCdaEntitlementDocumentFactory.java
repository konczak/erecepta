package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040ExternalDocument;
import org.hl7.v3.POCDMT000040Reference;
import org.hl7.v3.XActRelationshipExternalReference;
import org.hl7.v3.XDocumentSubstanceMood;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.59-2018-06-30T000000.html
 */
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaEntitlementDocumentFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;

    public POCDMT000040Reference createPOCDMT000040Reference(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Reference pocdmt000040Reference = objectFactoryForHl7V3.createPOCDMT000040Reference();

        // fixed
        pocdmt000040Reference.setTypeCode(XActRelationshipExternalReference.REFR);

        POCDMT000040ExternalDocument pocdmt000040ExternalDocument = objectFactoryForHl7V3.createPOCDMT000040ExternalDocument();

        // those 2 values are required with forced content
        pocdmt000040ExternalDocument.setClassCode("DOC");
        pocdmt000040ExternalDocument.getMoodCodes()
                .add(XDocumentSubstanceMood.EVN.toString());

        II templateId = templateIdFactory.create(Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.ENTITLEMENT_DOCUMENT);
        pocdmt000040ExternalDocument.getTemplateIds()
                .add(templateId);

        pocdmt000040ExternalDocument.setText(createEReceptaDlaLekGotowyInput.getDocumentConfirmOfUprawnieniaDodatkowe());

        pocdmt000040Reference.setExternalDocument(pocdmt000040ExternalDocument);

        return pocdmt000040Reference;
    }
}
