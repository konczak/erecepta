package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.82-2018-09-30T000000.html
 * 2.16.840.1.113883.3.4424.13.11.82
 * from base 0.4.0.127.0.16.1.1.2.1
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostacOpakowaniaLeku {

    ADMINISTRATION_SYSTEM("30000500", "Administration system"),
    AMPOULE("30001000", "Ampoule"),
    APPLICATOR("30002000", "Applicator"),
    BAG("30004000", "Bag"),
    BARREL("30006000", "Barrel"),
    BLISTER("30007000", "Blister"),
    BOTTLE("30008000", "Bottle"),
    BOX("30009000", "Box"),
    BRUSH("30010000", "Brush"),
    BRUSH_APPLICATOR("30011000", "Brush applicator"),
    CANNULA("30012000", "Cannula"),
    CAP("30013000", "Cap"),
    CARTRIDGE("30014000", "Cartridge"),
    CHILD_RESISTANT_CLOSURE("30015000", "Child-resistant closure"),
    CUP("30016000", "Cup"),
    DABBING_APPLICATOR("30017000", "Dabbing applicator"),
    DREDGING_APPLICATOR("30019000", "Dredging applicator"),
    DREDGING_CONTAINER("30020000", "Dredging container"),
    DROPPER_APPLICATOR("30022000", "Dropper applicator"),
    DROPPER_CONTAINER("30023000", "Dropper container"),
    FIXED_CRYOGENIC_VESSEL("30023005", "Fixed cryogenic vessel"),
    GAS_CYLINDER("30024000", "Gas cylinder"),
    HIGH_PRESSURE_TRANSDERMAL_DELIVERY_DEVICE("30025000", "High pressure transdermal delivery device"),
    IMPLANTER("30026000", "Implanter"),
    INHALER("30026500", "Inhaler"),
    IN_OVO_INJECTION_DEVICE("30027000", "In-ovo injection device"),
    INJECTION_NEEDLE("30028000", "Injection needle"),
    INJECTION_SYRINGE("30029000", "Injection syringe"),
    INTRAMAMMARY_SYRINGE("30031000", "Intramammary syringe"),
    JAR("30032000", "Jar"),
    MEASURING_DEVICE("30033000", "Measuring device"),
    MEASURING_SPOON("30034000", "Measuring spoon"),
    METERING_PUMP("30035000", "Metering pump"),
    METERING_VALVE("30036000", "Metering valve"),
    MOBILE_CRYOGENIC_VESSEL("30036005", "Mobile cryogenic vessel"),
    MOUTHPIECE("30037000", "Mouthpiece"),
    MULTIDOSE_CONTAINER("30038000", "Multidose container"),
    MULTIDOSE_CONTAINER_WITH_AIRLESS_PUMP("30039000", "Multidose container with airless pump"),
    MULTIPUNCTURER("30040000", "Multipuncturer"),
    NASAL_APPLICATOR("30041000", "Nasal applicator"),
    NEBULISER("30042000", "Nebuliser"),
    NEEDLE_APPLICATOR("30043000", "Needle applicator"),
    NOZZLE("30044000", "Nozzle"),
    ORAL_SYRINGE("30045000", "Oral syringe"),
    PIPETTE("30046000", "Pipette"),
    PIPETTE_APPLICATOR("30047000", "Pipette applicator"),
    POUR_ON_CONTAINER("30048000", "Pour-on container"),
    PRE_FILLED_GASTROENTERAL_TUBE("30049000", "Pre-filled gastroenteral tube"),
    PRE_FILLED_PEN("30050000", "Pre-filled pen"),
    PRE_FILLED_SYRINGE("30051000", "Pre-filled syringe"),
    PRESSURISED_CONTAINER("30052000", "Pressurised container"),
    PRICK_TEST_APPLICATOR("30053000", "Prick test applicator"),
    ROLL_ON_CONTAINER("30053500", "Roll-on container"),
    SACHET("30054000", "Sachet"),
    SCARIFIER("30055000", "Scarifier"),
    SCREW_CAP("30056000", "Screw cap"),
    SINGLE_DOSE_CONTAINER("30057000", "Single-dose container"),
    SPATULA("30058000", "Spatula"),
    SPOT_ON_APPLICATOR("30059000", "Spot-on applicator"),
    SPRAY_CONTAINER("30060000", "Spray container"),
    SPRAY_PUMP("30061000", "Spray pump"),
    SPRAY_VALVE("30062000", "Spray valve"),
    STAB_VACCINATOR("30063000", "Stab vaccinator"),
    STOPPER("30064000", "Stopper"),
    STRAW("30064500", "Straw"),
    STRIP("30065000", "Strip"),
    TABLET_CONTAINER("30066000", "Tablet container"),
    TUBE("30067000", "Tube"),
    VIAL("30069000", "Vial");

    private final String code;
    private final String displayName;

}
