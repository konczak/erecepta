package pl.konczak.nzoz.ereceptaapp.client.csioz.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * xsd/coreschemas/voc.xsd:578
 * https://www.hl7.org/fhir/v3/ActClass/cs.html
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ActClassUnifiedCode {

    ACT("ACT"),
    ACCOMMODATION("ACCM"),
    ACCOUNT("ACCT"),
    ACCESSION("ACSN"),
    FINANCIAL_ADJUDICATION("ADJUD"),
    CONSENT("CONS"),
    CONTAINER_REGISTRATION("CONTREG"),
    CLINICAL_TRIAL_TIMEPOINT_EVENT("CTTEVENT"),
    DISCIPLINARY_ACTION("DISPACT"),
    ENCOUNTER("ENC"),
    INCIDENT("INC"),
    inform("INFRM"),
    INVOICE_ELEMENT("INVE"),
    WORKING_LIST("LIST"),
    MONITORING_PROGRAM("MPROT"),
    CARE_PROVISION("PCPR"),
    PROCEDURE("PROC"),
    REGISTRATION("REG"),
    review("REV"),
    // substance administration - The act of introducing or otherwise applying a substance to the subject.
    SUBSTANCE_ADMINISTRATION("SBADM"),
    SPECIMEN_TREATMENT("SPCTRT"),
    SUBSTITUTION("SUBST"),
    TRANSPORTATION("TRNS"),
    VERIFICATION("VERIF"),
    FINANCIAL_TRANSACTION("XACT");

    private final String code;
}
