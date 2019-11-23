package pl.konczak.nzoz.ereceptaapp;

import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.DSSException;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.token.DSSPrivateKeyEntry;
import eu.europa.esig.dss.token.Pkcs12SignatureToken;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.signature.XAdESService;

import java.io.IOException;
import java.security.KeyStore;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * How to sign using MS-CAPI.
 */
@Component
public class SignXmlXades {

    public void sign(final String inputFile, final String outputFile) throws DSSException, IOException {
        // GET document to be signed -
        // Return DSSDocument toSignDocument
        DSSDocument toSignDocument = new FileDocument(inputFile);


        // Creation of MS-CAPI signature token
        try (Pkcs12SignatureToken signingToken = new Pkcs12SignatureToken(
                "D:\\programowanie\\zarnow\\nzoz\\e-recepty\\docs\\KOMPLET_DANYCH_SZPL_NR_106\\Adam106 Leczniczy.p12",
                new KeyStore.PasswordProtection("UXG9DxASCm".toCharArray())
        )) {

            List<DSSPrivateKeyEntry> list = signingToken.getKeys();
            // Chose the right private key entry from store.
            // The index will depend of the number of the certificates on your card.
            System.out.println(list.size());
            list.stream()
                    .forEach(dssPrivateKeyEntry -> System.out.println(dssPrivateKeyEntry.getCertificate()));
            DSSPrivateKeyEntry privateKey = list.get(0);

            // Preparing parameters for the PAdES signature
            XAdESSignatureParameters parameters = new XAdESSignatureParameters();
            // We choose the level of the signature (-B, -T, -LT, -LTA).
            parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
            // We choose the type of the signature packaging (ENVELOPING, DETACHED).
            parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
            // We set the digest algorithm to use with the signature algorithm. You must use the
            // same parameter when you invoke the method sign on the token.
            parameters.setDigestAlgorithm(DigestAlgorithm.SHA256);

            // We set the signing certificate
            parameters.setSigningCertificate(privateKey.getCertificate());
            // We set the certificate chain
            parameters.setCertificateChain(privateKey.getCertificateChain());

            // Create common certificate verifier
            CommonCertificateVerifier commonCertificateVerifier = new CommonCertificateVerifier();
            // Create CAdES xadesService for signature
            XAdESService xadesService = new XAdESService(commonCertificateVerifier);

            // Get the SignedInfo segment that need to be signed.
            ToBeSigned dataToSign = xadesService.getDataToSign(toSignDocument, parameters);

            // This function obtains the signature value for signed information using the
            // private key and specified algorithm
            DigestAlgorithm digestAlgorithm = parameters.getDigestAlgorithm();
            SignatureValue signatureValue = signingToken.sign(dataToSign, digestAlgorithm, privateKey);

            // We invoke the xadesService to sign the document with the signature value obtained in
            // the previous step.
            DSSDocument signedDocument = xadesService.signDocument(toSignDocument, parameters, signatureValue);

            // save the signed document on the filesystem
            signedDocument.save(outputFile);
        }
    }
}
