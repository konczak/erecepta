package pl.konczak.nzoz.ereceptaapp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.gov.csioz.p1.erecepta.ws.v20170510.ObslugaReceptyWS;
import pl.gov.csioz.p1.erecepta.ws.v20170510.ZapisPakietuReceptRequest;
import pl.gov.csioz.p1.kontekst.mt.v20170510.KontekstMT;
import pl.gov.csioz.xsd.extpl.r2.ClinicalDocument;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.ClinicalDocumentFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.KontekstMTFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.factory.ZapisPakietuReceptRequestFactory;
import pl.konczak.nzoz.ereceptaapp.client.csioz.input.CreateEReceptaDlaLekGotowyInput;
import pl.konczak.nzoz.ereceptaapp.util.SampleCreateEReceptaDlaLekGotowyInputFactory;
import pl.konczak.nzoz.ereceptaapp.util.XmlDeserializer;
import pl.konczak.nzoz.ereceptaapp.util.XmlSerializer;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:cxf.xml")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class EreceptaApplication
        implements CommandLineRunner {

    private final SampleCreateEReceptaDlaLekGotowyInputFactory getCreateEReceptaDlaLekGotowyInput;
    private final ClinicalDocumentFactory clinicalDocumentFactory;
    private final ObslugaReceptyWS obslugaReceptyWSclient;
    private final KontekstMTFactory kontekstMTFactory;
    private final ZapisPakietuReceptRequestFactory zapisPakietuReceptRequestFactory;
    private final XmlSerializer xmlSerializer;
    private final XmlDeserializer xmlDeserializer;

    public static void main(String[] args) {
        SpringApplication.run(EreceptaApplication.class, args);
    }

    public void run(final String... args) throws Exception {
        CreateEReceptaDlaLekGotowyInput createEReceptaDlaLekGotowyInput = getCreateEReceptaDlaLekGotowyInput.createSampleCreateEReceptaDlaLekGotowyInput();
        ClinicalDocument clinicalDocument = clinicalDocumentFactory.createEReceptaDlaLekGotowy(createEReceptaDlaLekGotowyInput);

        this.xmlSerializer.clinicalDocumentToXml(clinicalDocument);

        Path path = Paths.get("D:\\programowanie\\zarnow\\nzoz\\e-recepty\\erecepta\\erecepta-app\\src\\main\\resources\\recepta.xml");

        this.xmlDeserializer.xmlToClinicalDocument(path.toFile());
        //TODO obtain prepared ClinicalDocument as XML, sign as lekarz and put as part of request body

        if (1 == 1) {
            return;
        }

        KontekstMT kontekstMT = kontekstMTFactory.create();
        ZapisPakietuReceptRequest zapisPakietuRecept = zapisPakietuReceptRequestFactory.create();

        obslugaReceptyWSclient.zapisPakietuRecept(zapisPakietuRecept, kontekstMT);
    }








}
