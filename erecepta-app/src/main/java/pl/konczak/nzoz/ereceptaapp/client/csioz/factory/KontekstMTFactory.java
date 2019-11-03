package pl.konczak.nzoz.ereceptaapp.client.csioz.factory;

import lombok.extern.slf4j.Slf4j;
import pl.gov.csioz.p1.kontekst.mt.v20170510.AtrybutMT;
import pl.gov.csioz.p1.kontekst.mt.v20170510.KontekstMT;
import pl.gov.csioz.p1.kontekst.mt.v20170510.RolaBiznesowaMT;
import pl.konczak.nzoz.ereceptaapp.client.csioz.constant.Oid;

import org.springframework.stereotype.Component;

@Slf4j
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
        atrybutMT.getWartosc().add(Oid.IdentyfikatoryPrzedsiebiorstw.RPWDL_PODMIOT_CZ_I_KODU_RESORTOWEGO);
        return atrybutMT;
    }

    private AtrybutMT createIdPodmiotuOidExt() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idPodmiotuOidExt");
        atrybutMT.getWartosc().add("000000926578");
        log.warn("value of urn:csioz:p1:erecepta:kontekst:idPodmiotuOidExt is hardcode");
        return atrybutMT;
    }

    private AtrybutMT createIdUzytkownikaOidRoot() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idUzytkownikaOidRoot");
        atrybutMT.getWartosc().add(Oid.IdentyfikatoryOsob.NUMERY_PRAWA_WYKONYWANIA_ZAWODU_LEKARZE_DENTYSCI_FELCZERZY);
        return atrybutMT;
    }

    private AtrybutMT createIdUzytkownikaOidExt() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idUzytkownikaOidExt");
        atrybutMT.getWartosc().add("7391208");
        log.warn("value of urn:csioz:p1:erecepta:kontekst:idUzytkownikaOidExt is hardcode");
        return atrybutMT;
    }

    private AtrybutMT createIdMiejscaPracyOidRoot() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idMiejscaPracyOidRoot");
        atrybutMT.getWartosc().add(Oid.IdentyfikatoryPrzedsiebiorstw.RPWDL_PODMIOT_CZ_I_I_V_KODU_RESORTOWEGO);
        return atrybutMT;
    }

    private AtrybutMT createIdMiejscaPracyOidExt() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:idMiejscaPracyOidExt");
        log.warn("value of urn:csioz:p1:erecepta:kontekst:idMiejscaPracyOidExt is hardcode");
        atrybutMT.getWartosc().add("4");
        return atrybutMT;
    }

    private AtrybutMT createRolaBiznesowa() {
        AtrybutMT atrybutMT = new AtrybutMT();
        atrybutMT.setNazwa("urn:csioz:p1:erecepta:kontekst:rolaBiznesowa");
        atrybutMT.getWartosc().add(RolaBiznesowaMT.LEKARZ_LEK_DENTYSTA_FELCZER.toString());
        return atrybutMT;
    }
}
