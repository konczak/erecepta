package pl.konczak.nzoz.ereceptaapp.client.csioz.factory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.gov.csioz.xsd.extpl.r2.ClinicalDocument;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.document.PlCdaReimbursementConformantDrugPrescriptionFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ClinicalDocumentFactory {

    private final PlCdaReimbursementConformantDrugPrescriptionFactory plCdaReimbursementConformantDrugPrescriptionFactory;

    public ClinicalDocument createEReceptaDlaLekGotowy(final CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput) {
        return plCdaReimbursementConformantDrugPrescriptionFactory.createClinicalDocument(createEReceptaDlaLekGotowyInput);
    }
}
