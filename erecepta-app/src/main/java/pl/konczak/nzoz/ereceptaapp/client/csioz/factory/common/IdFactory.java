package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class IdFactory {

    private final ObjectFactory objectFactoryForHl7V3;

    public II create(final String root, final String extension) {
        Assert.hasLength(root, "ID root OID cannot be null or empty");
        Assert.hasLength(extension, "ID extension cannot be null or empty");
        return createId(root, extension);
    }

    public II create(final String root, final String extension, final boolean displayable) {
        Assert.hasLength(root, "ID root OID cannot be null or empty");
        Assert.hasLength(extension, "ID extension cannot be null or empty");
        II id = createId(root, extension);
        id.setDisplayable(displayable);

        return id;
    }

    private II createId(final String root, final String extension) {
        II id = objectFactoryForHl7V3.createII();

        id.setRoot(root);
        id.setExtension(extension);

        return id;
    }
}
