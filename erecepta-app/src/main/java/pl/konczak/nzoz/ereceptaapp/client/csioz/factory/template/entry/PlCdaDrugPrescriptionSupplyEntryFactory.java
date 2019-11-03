package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.ActClassSupply;
import org.hl7.v3.BL;
import org.hl7.v3.CE;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.POCDMT000040Act;
import org.hl7.v3.POCDMT000040EntryRelationship;
import org.hl7.v3.POCDMT000040LabeledDrug;
import org.hl7.v3.POCDMT000040ManufacturedProduct;
import org.hl7.v3.POCDMT000040Product;
import org.hl7.v3.POCDMT000040Supply;
import org.hl7.v3.PQ;
import org.hl7.v3.SXCMTS;
import org.hl7.v3.XActRelationshipEntryRelationship;
import org.hl7.v3.XDocumentSubstanceMood;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common.TemplateIdFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.PrescribedDrug;
import pl.konczak.nzoz.ereceptaapp.util.DateTimeUtil;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.4.55-2018-09-30T000000.html
 */
@Slf4j
@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlCdaDrugPrescriptionSupplyEntryFactory {

    private final ObjectFactory objectFactoryForHl7V3;
    private final TemplateIdFactory templateIdFactory;
    private final PlCdaDrugPaymentLevelFactory plCdaDrugPaymentLevelFactory;

    public POCDMT000040EntryRelationship createPocdmt000040EntryRelationship(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        PrescribedDrug prescribedDrug = createEReceptaDlaLekGotowyInput.getPrescribedDrug();
        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = objectFactoryForHl7V3.createPOCDMT000040EntryRelationship();

        // this value is fixed
        pocdmt000040EntryRelationship.setTypeCode(XActRelationshipEntryRelationship.COMP);

        POCDMT000040Supply pocdmt000040Supply = objectFactoryForHl7V3.createPOCDMT000040Supply();
        // those 2 values are fixed
        pocdmt000040Supply.setClassCode(ActClassSupply.SPLY);
        pocdmt000040Supply.setMoodCode(XDocumentSubstanceMood.RQO);

        Collection<II> templateIds = templateIdFactory.createMultiple(
                Oid.Ihe.DRUG_PRESCRIPTION_SUPPLY_ENTRY_BASE_TEMPLATE,
                Oid.PolskaImplementacjaKrajowaHl7Cda.WzorceCdaElementowSekcjiDokumentu.DRUG_PRESCRIPTION_SUPPLY_ENTRY
        );
        pocdmt000040Supply.getTemplateIds()
                .addAll(templateIds);

        // TODO probably this has to be more complex when period of from-to will be different for drug supply time
        // Instrukcja stosowania PIK HL7 CDA_20181130_v_1.3.1.pdf page 59
        // effectiveTime means "data od"
        SXCMTS effectiveTime = objectFactoryForHl7V3.createSXCMTS();
        effectiveTime.setValue(DateTimeUtil.formatAsBasicDate(createEReceptaDlaLekGotowyInput.getCreatedAt()));
        pocdmt000040Supply.getEffectiveTimes()
                .add(effectiveTime);

        BL independentInd = objectFactoryForHl7V3.createBL();
        // this is fixed
        independentInd.setValue(false);
        pocdmt000040Supply.setIndependentInd(independentInd);

        PQ pq = createQuantity(prescribedDrug);
        pocdmt000040Supply.setQuantity(pq);

        POCDMT000040Product pocdmt000040Product = createPocdmt000040Product(prescribedDrug);
        pocdmt000040Supply.setProduct(pocdmt000040Product);

        POCDMT000040EntryRelationship pocdmt000040EntryRelationshipWithActPoziomOdplatnosciLeku = createActWithPoziomOdplatnosciLeku(createEReceptaDlaLekGotowyInput);
        pocdmt000040Supply.getEntryRelationships()
                .add(pocdmt000040EntryRelationshipWithActPoziomOdplatnosciLeku);

        pocdmt000040EntryRelationship.setSupply(pocdmt000040Supply);

        return pocdmt000040EntryRelationship;
    }

    private PQ createQuantity(final PrescribedDrug prescribedDrug) {
        // TODO confirm this - is this quantity of prescribed packages?

        // Instrukcja stosowania PIK HL7 CDA_20181130_v_1.3.1.pdf page 59
        // quantity - ilość leku do wydania pacjentowi, przy czym ilość należy różnie interpretować w zależności od występowania elementu product i rodzaju recepty.
        // Wartość value posiada typ real, a więc można tu wskazać 1,5 opakowania lub 1,5 tabletki - patrz opis elementu product.
        // W przypadku recept na lek gotowy lub ŚSSPŻ nie należy stosować atrybutu unit, nie przewiduje się recept typu „proszę wydać 50 gram mleka w proszku”
        // (było to wprost zabronione przed wersją IG 1.3, zasadę tę należy jednak stosować także w przypadku recept zgodnych z IHE PRE).
        // W przypadku recept na lek recepturowy, atrybut unit pozwala na umieszczenie jednostki, np. mililitrów lub gramów

        // ale

        // product - opcjonalne wskazanie (klasa udział) konkretnego opakowania leku do wydania.
        // Opakowanie, w tym ilość leku do wydania, identyfikowane jest kodem GS1 (EAN) z tego opakowania.
        // Uwaga, jeżeli element product podano, wskazując tym samym wielkość opakowania leku, wartość quantity dotyczy ilości opakowań do wydania pacjentowi.
        // Jeżeli elementu product nie podano, wartość quantity dotyczy ilości leku zapisanej w wyrażeniu klinicznym substanceAdministration.
        // Z merytorycznego punktu widzenia warto zauważyć, że udział zapisany w supply elementem product jest tożsamy z udziałem zapisanym w substanceAdministration elementem consumable

        PQ pq = objectFactoryForHl7V3.createPQ();
        pq.setValue(prescribedDrug.getPackageQuantity());
        return pq;
    }

    private POCDMT000040Product createPocdmt000040Product(final PrescribedDrug prescribedDrug) {
        POCDMT000040Product pocdmt000040Product = objectFactoryForHl7V3.createPOCDMT000040Product();

        POCDMT000040ManufacturedProduct pocdmt000040ManufacturedProduct = objectFactoryForHl7V3.createPOCDMT000040ManufacturedProduct();

        POCDMT000040LabeledDrug pocdmt000040LabeledDrug = objectFactoryForHl7V3.createPOCDMT000040LabeledDrug();

        CE code = objectFactoryForHl7V3.createCE();
        code.setCode(prescribedDrug.getEanOfPackage());
        code.setCodeSystem(Oid.Leki.IDENTYFIKATOR_GLOBALNY_ZGODNY_Z_SYSTEMEM_GS_1);
        code.setCodeSystemName("GS1");
        code.setDisplayName(prescribedDrug.getNazwaProduktuLeczniczego());

        pocdmt000040LabeledDrug.setCode(code);

        pocdmt000040ManufacturedProduct.setManufacturedLabeledDrug(pocdmt000040LabeledDrug);

        pocdmt000040Product.setManufacturedProduct(pocdmt000040ManufacturedProduct);

        return pocdmt000040Product;
    }

    private POCDMT000040EntryRelationship createActWithPoziomOdplatnosciLeku(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        POCDMT000040EntryRelationship pocdmt000040EntryRelationship = objectFactoryForHl7V3.createPOCDMT000040EntryRelationship();
        pocdmt000040EntryRelationship.setTypeCode(XActRelationshipEntryRelationship.COMP);

        POCDMT000040Act pocdmt000040Act = plCdaDrugPaymentLevelFactory.createPocdmt000040Act(createEReceptaDlaLekGotowyInput);
        pocdmt000040EntryRelationship.setAct(pocdmt000040Act);

        return pocdmt000040EntryRelationship;
    }

}
