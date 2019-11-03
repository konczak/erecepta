package pl.konczak.nzoz.ereceptaapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public org.hl7.v3.ObjectFactory objectFactoryForHl7V3() {
        return new org.hl7.v3.ObjectFactory();
    }

    @Bean
    public pl.gov.csioz.xsd.extpl.r2.ObjectFactory objectFactoryForExtPl() {
        return new pl.gov.csioz.xsd.extpl.r2.ObjectFactory();
    }

    @Bean
    public ihe.pharm.ObjectFactory objectFactoryForIhePharm() {
        return new ihe.pharm.ObjectFactory();
    }

}
