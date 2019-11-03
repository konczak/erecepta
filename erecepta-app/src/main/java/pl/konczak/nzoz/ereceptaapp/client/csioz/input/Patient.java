package pl.konczak.nzoz.ereceptaapp.client.csioz.input;

import lombok.Builder;
import lombok.Getter;
import pl.konczak.nzoz.ereceptaapp.config.constant.Nfz;

import java.time.LocalDate;

@Builder
@Getter
public class Patient {

    private final String id;
    private final String pesel;
    private final LocalDate birthAt;
    private final Gender gender;
    private final String firstName;
    private final String lastName;
    private final Address homeAddress;
    private final Nfz assignedTo;
}
