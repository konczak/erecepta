package pl.konczak.nzoz.ereceptaapp.util;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import java.util.HashMap;
import java.util.Map;

public class MyNamespaceMapper
        extends NamespacePrefixMapper {

    private final Map<String, String> map;

    public MyNamespaceMapper() {
        this.map = new HashMap<>();

        //namespace urn:hl7-org:v3 should be main one
        this.map.put("urn:hl7-org:v3", null);
        this.map.put("http://www.csioz.gov.pl/xsd/extPL/r2", "extPL");
        this.map.put("urn:ihe:pharm", "pharm");
        this.map.put("http://www.w3.org/2000/09/xmldsig#", "ds");
        this.map.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
    }

    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        return this.map.getOrDefault(namespaceUri, suggestion);
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return this.map.keySet().toArray(new String[0]);
    }

}
