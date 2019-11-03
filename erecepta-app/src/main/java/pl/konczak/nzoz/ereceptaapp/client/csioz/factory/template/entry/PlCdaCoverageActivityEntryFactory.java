package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.CD;
import org.hl7.v3.CS;
import org.hl7.v3.ED;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Act;
import org.hl7.v3.POCDMT000040EntryRelationship;
import org.hl7.v3.XActClassDocumentEntryAct;
import org.hl7.v3.XActRelationshipEntryRelationship;
import org.hl7.v3.XDocumentActMood;
import pl.konczak.nzoz.ereceptaapp.config.constant.Loinc;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.CodeFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.datatype.PlCdaClinicalStatementTextFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import java.util.Collection;

import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaCoverageActivityEntryFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final PlCdaSpecialEntitlementPolicyEntryFactory plCdaSpecialEntitlementPolicyEntryFactory;
    private final CodeFactory codeFactory;
    private final PlCdaClinicalStatementTextFactory plCdaClinicalStatementTextFactory;

    public POCDMT000040Act createPOCDMT000040Act(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040Act pocdmt000040Act = objectFactoryForHl7V3.createPOCDMT000040Act();
        // both values are fixed
        pocdmt000040Act.setClassCode(XActClassDocumentEntryAct.ACT);
        pocdmt000040Act.setMoodCode(XDocumentActMood.DEF);

        Collection<II> templateIds = templateIdFactory
                .createMultiple(Oid.Hl7.CdaTemplates.ENTRY_COVERAGE_ACTIVITY, Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.COVERAGE_ACTIVITY_ENTRY);
        pocdmt000040Act.getTemplateIds()
                .addAll(templateIds);

        II id = objectFactoryForHl7V3.createII();
        id.getNullFlavors()
                .add("NA");
        pocdmt000040Act.getIds()
                .add(id);

        Loinc paymentSources = Loinc.PAYMENT_SOURCES;
        CD code = codeFactory.create(Oid.SlownikiIZbioryWartosci.EXTERNAL_LOINC, paymentSources.getCode(), paymentSources.getDisplayName());
        pocdmt000040Act.setCode(code);

        // TODO keep this reference ID synchronized with visual text
        //  should it be ACT_UPRAWNIENIA_DODATKOWE? like in:
        //  pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry.PlCdaSpecialEntitlementPolicyEntryFactory.createTextReference
        ED text = plCdaClinicalStatementTextFactory.createTextForVisualReference("ACT", "2");
        pocdmt000040Act.setText(text);

        CS statusCode = objectFactoryForHl7V3.createCS();
        // fixed value
        statusCode.setCode("completed");
        pocdmt000040Act.setStatusCode(statusCode);

        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = createPocdmt000040EntryRelationship(createEReceptaDlaLekGotowyInput);
        pocdmt000040Act.getEntryRelationships()
                .add(pocdmt000040EntryRelationship);

        return pocdmt000040Act;
    }

    private POCDMT000040EntryRelationship createPocdmt000040EntryRelationship(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = objectFactoryForHl7V3.createPOCDMT000040EntryRelationship();

        // it is fixed
        pocdmt000040EntryRelationship.setTypeCode(XActRelationshipEntryRelationship.COMP);

        POCDMT000040Act pocdmt000040Act = plCdaSpecialEntitlementPolicyEntryFactory.createPocdmt000040Act(createEReceptaDlaLekGotowyInput);
        pocdmt000040EntryRelationship.setAct(pocdmt000040Act);

        return pocdmt000040EntryRelationship;
    }
}
