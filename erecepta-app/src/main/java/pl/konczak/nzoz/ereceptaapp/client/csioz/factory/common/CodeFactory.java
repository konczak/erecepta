package pl.konczak.nzoz.ereceptaapp.client.csioz.factory.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hl7.v3.CE;
import org.hl7.v3.ObjectFactory;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class CodeFactory {

    private final ObjectFactory objectFactoryForHl7V3;

    public CE create(final String codeSystem, final String code) {
        Assert.hasLength(codeSystem, "Code codeSystem cannot be null or empty");
        Assert.hasLength(code, "Code 'code' cannot be null or empty");
        CE ce = objectFactoryForHl7V3.createCE();
        ce.setCodeSystem(codeSystem);
        ce.setCode(code);
        return ce;
    }

    public CE create(final String codeSystem, final String code, final String displayName) {
        Assert.hasLength(codeSystem, "Code codeSystem cannot be null or empty");
        Assert.hasLength(code, "Code 'code' cannot be null or empty");
        Assert.hasLength(displayName, "Code displayName cannot be null or empty");
        CE ce = objectFactoryForHl7V3.createCE();
        ce.setCodeSystem(codeSystem);
        ce.setCode(code);
        ce.setDisplayName(displayName);
        return ce;
    }

    public CE create(final String codeSystem, final String code, final String codeSystemName, final String displayName) {
        Assert.hasLength(codeSystem, "Code codeSystem cannot be null or empty");
        Assert.hasLength(code, "Code 'code' cannot be null or empty");
        Assert.hasLength(codeSystemName, "Code codeSystemName cannot be null or empty");
        Assert.hasLength(displayName, "Code displayName cannot be null or empty");
        CE ce = objectFactoryForHl7V3.createCE();
        ce.setCodeSystem(codeSystem);
        ce.setCode(code);
        ce.setCodeSystemName(codeSystemName);
        ce.setDisplayName(displayName);
        return ce;
    }
}
