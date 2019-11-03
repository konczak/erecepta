package pl.konczak.nzoz.ereceptaapp.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Nfz {

    DOLNOSLASKI("01", "Dolnośląski Oddział Narodowego Funduszu Zdrowia we Wrocławiu"),
    KUJAWSKO_POMORSKI("02", "Kujawsko-Pomorski Oddział Narodowego Funduszu Zdrowia w Bydgoszczy"),
    LUBELSKI("03", "Lubelski Oddział Narodowego Funduszu Zdrowia w Lublinie"),
    LUBUSKI("04", "Lubuski Oddział Narodowego Funduszu Zdrowia w Zielonej Górze"),
    LODZKI("05", "Łódzki Oddział Narodowego Funduszu Zdrowia w Łodzi"),
    MALOPOLSKI("06", "Małopolski Oddział Narodowego Funduszu Zdrowia w Krakowie"),
    MAZOWIECKI("07", "Mazowiecki Oddział Narodowego Funduszu Zdrowia w Warszawie"),
    OPOLSKI("08", "Opolski Oddział Narodowego Funduszu Zdrowia w Opolu"),
    PODKARPACKI("09", "Podkarpacki Oddział Narodowego Funduszu Zdrowia w Rzeszowie"),
    PODLASKI("10", "Podlaski Oddział Narodowego Funduszu Zdrowia w Białymstoku"),
    POMORSKI("11", "Pomorski Oddział Narodowego Funduszu Zdrowia w Gdańsku"),
    SLASKI("12", "Śląski Oddział Narodowego Funduszu Zdrowia w Katowicach"),
    SWIETOKRZYSKI("13", "Świętokrzyski Oddział Narodowego Funduszu Zdrowia w Kielcach"),
    WARMINSKO_MAZURSKI("14", "Warmińsko-Mazurski Oddział Narodowego Funduszu Zdrowia w Olsztynie"),
    WIELKOPOLSKI("15", "Wielkopolski Oddział Narodowego Funduszu Zdrowia w Poznaniu"),
    ZACHODNIOPOMORSKI("16", "Zachodniopomorski Oddział Narodowego Funduszu Zdrowia w Szczecinie");

    private final String code;
    private final String displayValue;


}
