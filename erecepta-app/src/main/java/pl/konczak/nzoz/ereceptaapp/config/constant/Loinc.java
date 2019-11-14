package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Complete Loincs dictionary CSV is ~64MB big and contains 20+ fields per record.
 *
 * https://loinc.org/
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Loinc {

    PAYMENT_SOURCES("48768-6", "Payment sources"),

    PRESCRIPTION_LIST("57828-6", "Prescriptions"),

    PRESCRIPTION_FOR_MEDICATION("57833-6", "Prescription for medication");

    private final String code;
    private final String displayName;
}
