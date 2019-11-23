package pl.konczak.nzoz.ereceptaapp.client.csioz.factory;

import org.apache.cxf.helpers.IOUtils;
import pl.gov.csioz.p1.erecepta.mt.v20170510.PakietReceptMT;
import pl.gov.csioz.p1.erecepta.mt.v20170510.ReceptaMT;
import pl.gov.csioz.p1.erecepta.mt.v20170510.ReceptyMT;
import pl.gov.csioz.p1.erecepta.ws.v20170510.WeryfikacjaPakietuReceptRequest;
import pl.gov.csioz.p1.erecepta.ws.v20170510.ZapisPakietuReceptRequest;
import pl.gov.csioz.p1.wspolne.mt.v20170510.IdentyfikatorZadaniaMT;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ZapisPakietuReceptRequestFactory {

    @Value("classpath:recepta.xml")
    private Resource receptaxml;

    public ZapisPakietuReceptRequest create() throws Exception {
        ZapisPakietuReceptRequest zapisPakietuReceptRequest = new ZapisPakietuReceptRequest();

        PakietReceptMT pakietRecept = new PakietReceptMT();

        ReceptyMT recepty = new ReceptyMT();

        ReceptaMT recepta = new ReceptaMT();
        recepta.setIdentyfikatorDokumentuWPakiecie(1);
        InputStream inputStream = receptaxml.getInputStream();
        recepta.setTresc(IOUtils.readBytesFromStream(inputStream));
        recepty.getRecepta().add(recepta);

        IdentyfikatorZadaniaMT identyfikatorZadania = new IdentyfikatorZadaniaMT();
        identyfikatorZadania.setIdZadania(UUID.randomUUID().toString());

        pakietRecept.setRecepty(recepty);
        zapisPakietuReceptRequest.setPakietRecept(pakietRecept);

        return zapisPakietuReceptRequest;
    }

    public WeryfikacjaPakietuReceptRequest create(final String signedDocument) throws Exception {
        WeryfikacjaPakietuReceptRequest weryfikacjaPakietuReceptRequest = new WeryfikacjaPakietuReceptRequest();

        PakietReceptMT pakietRecept = new PakietReceptMT();

        ReceptyMT recepty = new ReceptyMT();

        ReceptaMT recepta = new ReceptaMT();
        recepta.setIdentyfikatorDokumentuWPakiecie(1);
        recepta.setTresc(signedDocument.getBytes("UTF-8"));
        recepty.getRecepta().add(recepta);

        IdentyfikatorZadaniaMT identyfikatorZadania = new IdentyfikatorZadaniaMT();
        identyfikatorZadania.setIdZadania(UUID.randomUUID().toString());

        pakietRecept.setRecepty(recepty);
        weryfikacjaPakietuReceptRequest.setPakietRecept(pakietRecept);

        return weryfikacjaPakietuReceptRequest;
    }
}
