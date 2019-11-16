# erecepta

## Project purpose

Purpose of this project is to provide working solution consuming CSIOZ ObslugaReceptyWS (aka e-recepty WS).

## Engine

Project core uses:

- Spring boot as engine,
- Apache CXF as WebService client framework.


## Build

After checkout run in project root directory:

```bash
mvn clean install
``` 

it will build all modules what includes generation client source code from WSDL.

> Note: at current stage Maven wrapper is not included - use Maven from IDE or command line.

## Run

_TODO_


## Notes

### ID
Each document ID follows rules:

- contains OID - it can be PESEL or any ID in scope of "podmiot",
- each "podmiot" has registered own root `OID 2.16.840.1.113883.3.4424.2.7.{x}` - where X is number given by CSIOZ,
- each "podmiot" has registered under root OID set of expected IDs - each ID has to be unique and it will be unique in scope of "podmiot",
- frequently ID can have 2 values - displayable and hidden. ex. PESEL can be displayable while actual patient ID in system of "podmiot" will be hidden,
- actual visual representation will be `${OID} ${displayableIdFromPodmiot}`,
- in case of creating correct for already published document ID has to be changed to preserve uniqueness,
- in general HL7 ID could be an UUID or MAC - but in PL they are forbidden and OID is only acceptable format.

Medical employee will be identified at first by `Numer Prawa Wykonywania Zawodu (NPWZ)` which still will follow ID format. 

### HL7
Documentation (ex. https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.3-2018-09-30T000000.html) contains field "Wymagalność". Here is legend:

```text
- M –mandatory, tj. obowiązkowy –wskazany  element  dokumentu,  np.  danepacjenta,  musi pojawić  się  w  dokumencie.   W   przypadku atrybutów  posiadających  wartość  domyślną,obowiązkowy atrybut nie musi być podawany w dokumencie -uznaje się go wtedy za podany z wartością domyślną;
- R –required,  tj.obowiązkowy jeżeli znany (odpowiednik słowa „powinien”)–wskazany element posiadający wartość, np. identyfikator osoby, musipojawić się w dokumencie, jeżeli wartość  jest  znana,  nawet  jeżeli  krotność  tego  nie  wymaga.  Przykładem elementu obowiązkowego z krotnością 0..1 jest  blok  narracyjny, którego można w sekcji nie podać wyłącznie, gdy z charakteru  sekcji,  wyznaczonego  kodem  elementu code, wynika, że sekcja nie posiada merytorycznie istotnej treści dla czytelnika;
- C–conditional, tj. wymagany warunkowo;
- O –optional,  tj.  opcjonalny,  wartość domyślna w szablonach otwartych dla elementów i atrybutów nie wspomnianych w IG, a dopuszczalnych przez standard;
- NP –not present, tj. nie występuje w dokumencie, nie podaje się, ew. zabroniony;
- F –fixed,   stosowane   dla wymaganej, ustalonejwartości atrybutu.Konkretna  wartość ustalona   podawana   jest   w   kolumnie  Opis,  przy  czym  jeżeli atrybutu   nie   podano   w dokumencie (dopuszczalne wyłącznie gdy krotność atrybutu wynosi 0..1), atrybut uznaje się za istniejący z wartością ustaloną jako domyślną.
```

source https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/files/Instrukcja%20stosowania%20PIK%20HL7%20CDA_20181130_v_1.3.1.pdf - 6.1.5.(d) page 88

How to diff when use number of "Jednostki organizacyjne" vs "Komórki organizacyjne" can be identified by OIDs:

```text
2.16.840.1.113883.3.4424.2.3.2	Jednostki organizacyjne (cz. I i V kodu resortowego)
2.16.840.1.113883.3.4424.2.3.3	Komórki organizacyjne (cz. I i VII kodu resortowego)
```

### TS - Point in time
Acceptable format is:

    YYYYMMDDHHMMSS.UUUU[+|-ZZzz]

however HL7 PL recommends to skip `.UUUU[+|-ZZzz]`.
There is specific type TS.Date which format is `YYYYMMDD`

### xpaths from CSIOZ
If name under xpath is prefixed with `@` it indicates that element is actually attribute. 
Same applies for docs.

### EAN and other codes
File *Rejestr_Produktow_Leczniczych_stan_na_dzien_20191009080120.xlsx* download from https://rejestrymedyczne.csioz.gov.pl/rpl.html contains:

- ID in registry,
- 14 digits EAN codes - one per package in column _Opakowania_ - if there is more packages available on market multiple EANs are available.


## Shortcuts explanations

|Shortcut   |Full                                               	|Description    |
|-----------|-------------------------------------------------------|---------------|
|IG         |Implementation guide                               	|               |
|ICNP       |International Classification of Nursing Practice   	|               |
|EKUZ       |Europejska Karta Ubezpieczenia Zdrowotnego         	|               |
|URPL       |Urząd Rejestracji Produktów Leczniczych            	|               |
|RPWDL      |rejestr podmiotów wykonujących działalność leczniczą	|               |
|LOINC      |Logical Observation Identifiers Names and Codes		|               |
|SBADM      |substance administration                               |The act of introducing or otherwise applying a substance to the subject. <br/>https://www.hl7.org/fhir/v3/ActClass/cs.html|
|RPL        |rejestr produktów leczniczych                          |               |
|RPM        |rejestr pracowników medycznych??? (vs CWPM?)|               |
|CWPM|Centralny Wykaz Pracowników Medycznych||
|CWUb|Centralny Wykaz Usługobiorców||
|CWUd|Centralny Wykaz Usługodawców||

## Terms

LOINC - is international database of standarized codes for identification medical observations.
	https://loinc.org/
	After download CSV file with all LOINC codes is 63MB big.


## TODOs

1. Cover case for drug prescription for newborns (narrow case: multiple births from one pregnancy).
1. Cover case for drug prescription for external foreigner (or just without PESEL).
1. Consider support for case where patient identity is not known.
1. Support prescription for recipe drug - page 182, 7.4.1.1.
1. Avoid hardcoding `x` of `OID 2.16.840.1.113883.3.4424.2.7.{x}`.
1. Understood why in documentation https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.3-2018-09-30T000000.html there are errors (bottom of page ex. title)
1. Understood printing of drug prescription - there is transformation to handle that - but from what it derives barcode?
1. Cover drug prescription for multiple RLEK (rodzaj leku),
	it can be "Rodzaj leku (2.16.840.1.113883.3.4424.13.11.5) z wartościami jak w przypadku recepty: G - Lek gotowy, R – Lek recepturowy, S - Środek spożywczy specjalnego przeznaczenia żywieniowego, W - wyrób medyczny;"
	answer: do tej pory tylko lek gotowy i recepturowy. Nie rozpoznawali innych typów.
1. Cover drug prescription for multiple KDLEK (Kategoria dostępności leku),
	it can be "Kategoria dostępności leku w dokumencie realizacji recepty (2.16.840.1.113883.3.4424.13.11.75) z wartościami Rp, Rpw, Rpz - znaczenie jak w przypadku kodów podawanych w receptach i Lz - do stosowania w ramach lecznictwa zamkniętego;"
	answer:
	Rp - zwykła recepta (potrzebna)
	Rpw - recepta na leki z zakresu narkotyków (potrzebna). Zwykle pisana ręcznie i wymaga podania dokładnej ilości leku w recepcie + słownie.
	Rpz „wydawane z przepisu lekarza do zastrzeżonego stosowania"
	Lz - raczej tylko leczenie szpitalne
1. What is difference from medicine doctor perspective between ["lek gotowy", "lek recepturowy", "Środek spożywczy specjalnego przeznaczenia żywieniowego", "wyrób medyczny"] mentioned in docs: 7.2.2.9 (10)-(14) pages ~127-135
	answer:
	same as above
1. Cover drug prescription for multiple TWREC (Tryb wystawienia recepty),
	it can be "Tryb wystawienia recepty (2.16.840.1.113883.3.4424.13.11.7) z wartościami Z - zwykła, F - farmaceutyczna, P - pielęgniarska i PL - pielęgniarska na zlecenie lekarza; "
	answer:
	Na ten moment żadna pięlęgniarka nie może - potrzebny jest specjalny magister z uprawnieniami. Skupiamy się na "Z".
1. Cover drug prescription for multiple TRREC (Tryb realizacji recepty),
	it can be "Tryb realizacji recepty (2.16.840.1.113883.3.4424.13.11.8) z wartościami Z - zwykły, I - import docelowy; "
	answer:
	Z - wymaga wsparcia
	"I - import docelowy" - od lat nie było, ale być może trzeba przewidzieć w późniejszej wersji. Zdarza się potrzeba ściągnięcia leku którego nie ma na rynku polskim. Proces musi być zidentyfikowany.
1. Cover drug prescription for multiple RRECP (Rodzaj recepty papierowej),
1. Cover drug prescription for multiple RLUD (Refundacja leków wynikająca z uprawnień dodatkowych),
	answer:
	S - senior czyli 75+
	IB - inwalida wojenny (wymagany nr legitymacji)
	S,IB - czyli senior i inwalida wojenny
	IW - inwalida wojskowy (różni się od wojenngo że uszkodził się w trakcie służby)  (wymagany nr legitymacji)
	ZK - honorowy krwidawca (uwaga podaje się nr legitymacji) - z przykładu "Uprawnienia dodatkowe: ZK (Nr leg.: 238/2015)"
	AZ - pracownicy przy azbeście
	PO - żołnierze - nie bywają
	IN - inni - czasami gmina wydaje zaświadczenie o ubezpieczniu (problematyczny przypadek)
	WP - dużo punktów o wojsku (patrz strona)
	http://www.archiwum.mz.gov.pl/leki/refundacja/recepta-na-leki-refundowane/dodatkowe-uprawnienia/
	Dana osoba może mieć wiele uprawnień dodatkowych. Na wizualnej części pisze się po przecinku. A jak wypełniać w sekcji dla systemów informtycznych?
1. Will CSIOZ verify that patient is still IB? Case it is possible that somebody didn't travel to city to pro-long legitimation prooving that still is IB or IW.
1. Cover drug prescription for multiple RLPO (Poziomy odpłatności leków refundowanych),
	B - bezpłatny
	R - ryczałt
	50% 
	30%
	Zadne z powyższych nie jest naturalne w przeliczaniu ceny.
	Cześć jest przypisana do leku. Cześć zależy od uprawnień dodatkowych przysługujących pacjentowi.
	http://www.archiwum.mz.gov.pl/leki/refundacja/poziomy-odplatnosci/
1. Cover drug prescription for periodic interval time of time - in what intervals patient should take drug,
	answer:
	Do tej pory nie można było okresowej na rok. Max było na 4 miesiące do przodu. Wtedy wystawiało się 4 recepty każdą z opóźnionym "czas realizacji od".
1. Can validate created XML of ex. drug prescription against given schematron? How to use it? 
1. "poziom refundacji" is not same as "poziom odpłatności" - what is difference?
	answer:
	nadal nie wiemy - moze gdy mamy lek to poziom refeundacji to kwota zniżki a poziom odpłatności ile zostaje pacjentowi do zapłąty. Ale to tylko domysły. Sprawdzić z CSIOZ.
1. What is LOINC dictionary? WHat values are in? What it describes? Search tip: "LOINC 10186-5 (Identifying information)", "LOINC 48767-8 Annotation comment"
	answer:
	mama nie wie - nie zna tego zestawu. Wydaje się że może to mieć znaczenie przy skierowaniach - a nie przy receptach.
	lekarze używają ICD-9 i ICD-10 - wydaje się istnieć mapowanie miedzy tymi systemami 
	https://pl.wikipedia.org/wiki/Centrum_System%C3%B3w_Informacyjnych_Ochrony_Zdrowia
	https://pl.wikipedia.org/wiki/SNOMED_CT
	https://www.csioz.gov.pl/interoperacyjnosc/klasyfikacje/
1. Page 153, Jednostka podmiotu wykonującego działalność leczniczą, 2.16.840.1.113883.3.4424.13.10.2.17, what is "kod resortowy podmiotu"? How many numbers there are? Mentioned are I-VII at least.
	answer:
	na przykładowej recepcie jest zestaw "KR I - 12cyfr, V - 2cyfry, VII - 3cyfry, VIII - 4cyfry" 
	UWAGA: Żarnów ma 2 komórki - lekrze POZ oraz stomatologia. Stomatologia też wypisuje recepty - czyli wsparcie dla roli DENTYSTY będzie konieczne.
	Dentysta może wypisać zwolnienie. Ale większość tego nie robi - czytaj mogą nie mieć certyfikatu!
	Dentyści też piszą recepty.
1. "Numer Prawa Wykonywania Zawodu" medicine doctor is mandatory at some place. WARN: one m.d. can have multiple NPWZ's - which one should be used when?
	answer:
	mama nigdy się nie spotkała. Specjalizacja pozostaje pod tym samym numerem.
	Może chodzi gdy był lekarz a postanowił być dentystą.
	Zapytać w CSIOZ.
1. Who has deal with NFZ? "Podmiot" or m.d.? Which one deal ID should be include in drug prescription?
	answer:
	numer umowy z NFZ jest przypięty do placówki - nie do lekarza. Wszyscy wynajęci lekarze pracują pod tą samą umową z NFZ.
1. In drug prescription there is NFZ participant required in section insurance - it has to be NFZ where patient belongs? Or where it takes treatment?
	sprawdź inne odpowiedzi - jest to bardziej skomplikowane.
1. Where in drug prescription specify RPWDL + "place" ID? (page 154) (watch out for regions!) (one m.d. in same place and room in different time has own ID)
1. In address city of post office is required if is different than actual city of residence.
	answer:
	chyba nie ma poczty w systemie - jest gmina ale to chyba nie wystarczy.
	Co gdy mamy kogos z miasta? Gdzie jest wiele kodów? Podajemy nazwę miasta czy delegatury?
	Zapytać w CSIOZ?
1. Page 182, 7.4.1.1 contains multiple rules about highlight important elements in visual concept of drug prescription. Does delivered XSL transformation covers them?
1. Who or what can be insurer other than NFZ?
	answer:
	- np. gmina,
	- nie wiadomo co z większym miastem,
	- cudzoziemcy,
	- w PL nie ma prywtnych ubezpieczycieli.
	system Michała tego nie wspiera na ten moment.
1. Cover case for e-recepta when there is no refund for drug.
	answer:
	są leki bez zniżek a wymagają recepty. Do tej pory dla leków bez recepty mama wpisywała 100% jako odplatność - czy CSIOZ to wspiera?
	WAŻNE: Gdy nie wybrano specyficznej odpłatności - nadania zniżki przez lekarza - to lek winien być na 100% zawsze.
	Czy CSIOZ chce żeby wysyłać receptę na lek "bez recepty"?
1. What is allowed flow when patient requests paper print for drug prescription?
1. RPLC ~ recepta korygująca???
	answer:
	do tej pory było dopsianie długopisem i przybiciem pięczęci. Korekty są potrzebne.
1. 7.4.1.5 business rules for drug prescription - get back to it.
1. Support multiple packages vs support for dose.
	answer:
	zdarzają się przypadki gdy pisze się nie pełne opakowania. UWAGA możliwy problem - farmaceuta zczytuje kod bar code (?EAN?) z pudełka. Czy każde pudełko ma swój indywidualny kod.
1. Support patient address - city with street vs vilage without streets. + postcode of remote post office.
1. it is mandatory to specify ID of NFZ when there is lower price available due to patient documents.
1. drug prescription requires reason of create????
1. do we need support for "zlecenie na zaopatrzenie"?
	answer:
	potrzebne wsparcie - standardowa usługa. Jest to zupełnie inny dokument niż recepty.
	Dowiedzieć sie czy jest na to szablon.
1. it is mandatory to specify xsl-stylesheet with each document - to allow identification display rules. System needs to ensure bacward compatibility in that case.
1. what is PHARMINEX? what has in scope? paid?
1. w przypadku braku prądu ZUS zezwala na posiadanie zapasowych druków zwolnień i wystawianie przy ich pomocy. Ale po powrocie prądu/Internetu należy je wprowadzić do systemu. Jak to wygląda dla erecept? Co jeżeli erecepta będzie wystawiana 3 dni później? Przecież system odrzuca wsteczne daty.
1. pacjent jest przypisany do pewnego NFZ - nie do tego w którym mieszka, ani do tego w którym jest zadeklarowany. Najpewniej do tego w którym został przypisany w momencie zamieszkania gdy powstała instytuacja NFZ. Gdzie to znaleźć? Czy CSIOZ będzie odrzucać recepty z niezgodnym NFZ z pacjentem po ich stronie?
1. understood what is POCD_HD000040 extension of 2.16.840.1.113883.1.3
1. what are TERYT TERC vs TERYT SIMC ? And how it applies for foreigns? https://pl.wikipedia.org/wiki/TERYT http://eteryt.stat.gov.pl/eTeryt/rejestr_teryt/udostepnianie_danych/baza_teryt/uzytkownicy_indywidualni/pobieranie/pobieranie.aspx?contrast=default
1. In drugs database - how to distinguish "lek gotowy" from "środek spożywczy specjalnego przeznaczenia żywieniowego"
1. Ask about interpretation of ex. http://pub.rejestrymedyczne.csioz.gov.pl/ProduktSzczegoly.aspx?id=8294 (NAR, kod ATC, numer pozwolenia)
1. GS1 vs GTIN-13 ??? GTIN = (EAN-13) and is replaced by 14-digits code GTIN https://mgr.farm/aktualnosci/urpl-zmiana-kodow-ean-13-na-gtin-14-dla-lekow/
1. What are values of XActRelationshipEntryRelationship like COMP?
1. What are values of org.hl7.v3.XDocumentSubstanceMood like RQO?
1. What are values of org.hl7.v3.ActClassSupply like SPLY?
1. Familiarize with https://wiki.ihe.net/index.php/PCC_Vocabulary_Registry_and_Data_Dictionary
1. Familiarize with http://art-decor.org/decor/services/TemplateIndex?prefix=ccd1-&language=nl-NL
1. Warn there are CE, CD, CV, CO, CS - they all seems to represent same thing - in theory depend on type particular attributes are applied.
1. There are multiple places where PL characters are polluted across code - take a closer look over that
1. Program has multiple enums with "code" and "displayName" but there are old ones which do not follow this pattern. Update them to follow. Do not use enum name as value in XML.
1. What means EDQM? 
1. TODO wsparcie dla erecept dla leków typu OTC (bez recepty) - to najpewniej szablon https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/tmp-2.16.840.1.113883.3.4424.13.10.1.3-2018-09-30T000000.html
1. Rejestr_Produktow_Leczniczych_stan_na_dzien_20191009080120.xlsx kolumna opakowanie zawiera wpis "EU/coś/coś" - co to takiego?
1. Rejestr_Produktow_Leczniczych_stan_na_dzien_20191009080120.xlsx kolumna opakowanie zawiera wpis "Skasowane" - co to takiego?
1. TODO support case for lek "NZ" = "nie zamieniać" in <text> it will have "NZ" and in code it will use pl.konczak.nzoz.ereceptaapp.client.csioz.factory.template.entry.PlCdaDrugPrescriptionSupplySubstitutionEntryFactory
1. Wsparcie dla recept na lek narkotyczny pl.konczak.nzoz.ereceptaapp.config.constant.KategoriaDostepnosciLeku.Rpw
1. Zapytać o skróty używane w nazwach plików danych testowych
1. 7.4.1.5. Reguły biznesowe specyficzne dla recept 
1. Ordered priorities to understood:
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.2-2014-06-06T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.75-2017-10-26T000000.html
    - https://en.wikipedia.org/wiki/LOINC
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.81-2018-09-30T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.16-2014-06-06T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.15-2017-04-26T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.5-2017-10-26T000000.html
1. Subjects to understood:
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.1.11.10882-2014-03-26T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.1.11.16040-2014-03-26T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.25-2014-06-06T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.64-2015-08-21T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.19-2014-06-06T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.82-2018-09-30T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.14-2014-06-06T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.21-2014-06-06T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.38-2015-08-21T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.7-2014-06-06T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.1-2017-01-02T000000.html
    - https://www.csioz.gov.pl/HL7POL-1.3.1/plcda-html-1.3.1/plcda-html-1.3.1/voc-2.16.840.1.113883.3.4424.13.11.35-2014-09-23T000000.html
    
