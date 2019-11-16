package pl.konczak.nzoz.ereceptaapp.client.csioz.factory;

import pl.gov.csioz.xsd.extpl.r2.ClinicalDocument;
import pl.konczak.nzoz.ereceptaapp.util.SampleCreateEReceptaDlaLekGotowyInputFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.util.XmlSerializer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ClinicalDocumentFactoryTest {

    @Autowired
    private ClinicalDocumentFactory clinicalDocumentFactory;

    @Autowired
    private SampleCreateEReceptaDlaLekGotowyInputFactory sampleCreateEReceptaDlaLekGotowyInputFactory;

    @Autowired
    private XmlSerializer xmlSerializer;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnSerializedClinicalDocumentEqualsToExpectedWhenMarshall() throws Exception {
        // given
        CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput = sampleCreateEReceptaDlaLekGotowyInputFactory.createSampleCreateEReceptaDlaLekGotowyInput();

        ClinicalDocument clinicalDocument = clinicalDocumentFactory.createEReceptaDlaLekGotowy(createEReceptaDlaLekGotowyInput);

        // when
        String result = xmlSerializer.clinicalDocumentToXml(clinicalDocument);

        // then
        Path path = Paths.get(ClassLoader.getSystemResource("pl/konczak/nzoz/ereceptaapp/client/csioz/factory/sample-plCdaReimbursementConformantDrugPrescription-2.xml").toURI());
        byte[] bytes = Files.readAllBytes(path);
        String expectedContent = new String(bytes);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedContent);
    }

}
