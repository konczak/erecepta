package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.II;
import org.hl7.v3.ObjectFactory;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class TemplateIdFactory {

    private final ObjectFactory objectFactoryForHl7V3;

    public II create(final String root) {
        Assert.hasLength(root, "Template root OID cannot be null or empty");
        II templateId = objectFactoryForHl7V3.createII();
        templateId.setRoot(root);
        return templateId;
    }

    public II create(final String root, final String extension) {
        Assert.hasLength(root, "Template root OID cannot be null or empty");
        Assert.hasLength(extension, "Template extension cannot be null or empty");
        II templateId = objectFactoryForHl7V3.createII();
        templateId.setRoot(root);
        return templateId;
    }

    public Collection<II> createMultiple(final String... roots) {
        return Stream.of(roots)
                .map(this::create)
                .collect(Collectors.toList());
    }
}
