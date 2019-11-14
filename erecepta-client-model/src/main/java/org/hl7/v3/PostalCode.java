package org.hl7.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extPL.postalCode")
public class PostalCode {

    @XmlAttribute(name = "type", namespace = "http://www.w3.org/2001/XMLSchema-instance")
    private final String type = "extPL:adxp.postalCode";

    @XmlAttribute
    private String postCity;

    @XmlValue
    private String value;

    public String getType() {
        return type;
    }

    public String getPostCity() {
        return postCity;
    }

    public void setPostCity(final String postCity) {
        this.postCity = postCity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
