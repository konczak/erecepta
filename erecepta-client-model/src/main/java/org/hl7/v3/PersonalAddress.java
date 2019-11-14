package org.hl7.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extPL.personalAddress")
public class PersonalAddress {

    @XmlElement
    private String country;
    @XmlElement
    private PostalCode postalCode;
    @XmlElement
    private String city;
    @XmlElement
    private String streetName;
    @XmlElement
    private String houseNumber;
    @XmlElement
    private String unitID;
    @XmlElement
    private String censusTract;

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final PostalCode postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(final String unitID) {
        this.unitID = unitID;
    }

    public String getCensusTract() {
        return censusTract;
    }

    public void setCensusTract(final String censusTract) {
        this.censusTract = censusTract;
    }
}
