package pl.konczak.nzoz.ereceptaapp.client.csioz.input;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Address {

    // probably country could be handle in better way
    private final String country;
    private final String city;
    private final String street;
    private final String houseNumber;
    private final String apartmentNumber;
    private final String postCode;
    private final String postOffice;
    private final String censusTractId;
}
