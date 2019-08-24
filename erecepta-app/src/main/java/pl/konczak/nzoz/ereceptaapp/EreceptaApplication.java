package pl.konczak.nzoz.ereceptaapp;

import pl.gov.csioz.p1.erecepta.ws.v20170510.ObslugaReceptyWS;
import pl.gov.csioz.p1.erecepta.ws.v20170510.ZapisPakietuReceptRequest;
import pl.gov.csioz.p1.kontekst.mt.v20170510.KontekstMT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:cxf.xml")
public class EreceptaApplication
        implements CommandLineRunner {

    @Autowired
    private ObslugaReceptyWS obslugaReceptyWSclient;

    @Autowired
    private KontekstMTFactory kontekstMTFactory;

    @Autowired
    private ZapisPakietuReceptRequestFactory zapisPakietuReceptRequestFactory;

    public static void main(String[] args) {
        SpringApplication.run(EreceptaApplication.class, args);
    }


    public void run(final String... args) throws Exception {
        KontekstMT kontekstMT = kontekstMTFactory.create();
        ZapisPakietuReceptRequest zapisPakietuRecept = zapisPakietuReceptRequestFactory.create();

        obslugaReceptyWSclient.zapisPakietuRecept(zapisPakietuRecept, kontekstMT);
    }
}
