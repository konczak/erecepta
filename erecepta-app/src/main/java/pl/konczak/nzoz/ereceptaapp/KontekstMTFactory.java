package pl.konczak.nzoz.ereceptaapp;

import pl.gov.csioz.p1.kontekst.mt.v20170510.AtrybutMT;
import pl.gov.csioz.p1.kontekst.mt.v20170510.KontekstMT;

import org.springframework.stereotype.Component;

@Component
public class KontekstMTFactory {

    public KontekstMT create() {
        KontekstMT kontekstMT = new KontekstMT();

        kontekstMT.getAtrybut().add(createIdPodmiotuOidRoot());
        kontekstMT.getAtrybut().add(createIdPodmiotuOidExt());
        kontekstMT.getAtrybut().add(createIdUzytkownikaOidRoot());
        kontekstMT.getAtrybut().add(createIdUzytkownikaOidExt());
        kontekstMT.getAtrybut().add(createIdMiejscaPracyOidRoot());
        kontekstMT.getAtrybut().add(createIdMiejscaPracyOidExt());
        kontekstMT.getAtrybut().add(createRolaBiznesowa());

        return kontekstMT;
    }

    private AtrybutMT createIdPodmiotuOidRoot() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idPodmiotuOidRoot");
        atrybutMT.getWartosc().add("2.16.840.1.113883.3.4424.2.3.1");
        return atrybutMT;
    }

    private AtrybutMT createIdPodmiotuOidExt() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idPodmiotuOidExt");
        atrybutMT.getWartosc().add("000000926578");
        return atrybutMT;
    }

    private AtrybutMT createIdUzytkownikaOidRoot() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idUzytkownikaOidRoot");
        atrybutMT.getWartosc().add("2.16.840.1.113883.3.4424.1.6.2");
        return atrybutMT;
    }

    private AtrybutMT createIdUzytkownikaOidExt() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idUzytkownikaOidExt");
        atrybutMT.getWartosc().add("7391208");
        return atrybutMT;
    }

    private AtrybutMT createIdMiejscaPracyOidRoot() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idMiejscaPracyOidRoot");
        atrybutMT.getWartosc().add("2.16.840.1.113883.3.4424.2.3.2");
        return atrybutMT;
    }

    private AtrybutMT createIdMiejscaPracyOidExt() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idMiejscaPracyOidExt");
        atrybutMT.getWartosc().add("4");
        return atrybutMT;
    }

    private AtrybutMT createRolaBiznesowa() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:rolaBiznesowa");
        atrybutMT.getWartosc().add("LEKARZ_LEK_DENTYSTA_FELCZER");
        return atrybutMT;
    }
}
