/*
Created: 15.11.2020
Modified: 27.11.2020
Model: Operator Telekomunikacyjny
Database: Oracle 11g Release 2
*/


-- Create tables section -------------------------------------------------

-- Table Klienci

CREATE TABLE Klienci(
  Nr_klietna Integer NOT NULL,
  Numer_kontaktu Varchar2(12 ) NOT NULL,
  Adres_email Varchar2(30 ) NOT NULL,
  Data_zalozenia_konta Date NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Klienci

CREATE INDEX IX_Klient_ma_adres ON Klienci (Nr_adresu)
/

-- Add keys for table Klienci

ALTER TABLE Klienci ADD CONSTRAINT Klient_PK PRIMARY KEY (Nr_klietna)
/

-- Table Operatorzy

CREATE TABLE Operatorzy(
  Nr_operatora Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Data_zalozenia Date NOT NULL,
  Kraj_centrali Varchar2(30 ) NOT NULL,
  NIP Varchar2(12 ) NOT NULL
)
/

-- Add keys for table Operatorzy

ALTER TABLE Operatorzy ADD CONSTRAINT Operator_PK PRIMARY KEY (Nr_operatora)
/

-- Table Pracownicy

CREATE TABLE Pracownicy(
  Nr_pracownika Integer NOT NULL,
  Imie Varchar2(30 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  Plec Char(1 ) NOT NULL
        CHECK (Plec IN ('K','M')),
  Nr_konta Varchar2(15 ) NOT NULL,
  Nr_operatora Integer NOT NULL,
  Nr_adresu Integer NOT NULL,
  Nr_stanowiska Integer NOT NULL,
  Nr_siedziby Integer NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_Zatrudnia ON Pracownicy (Nr_operatora)
/

CREATE INDEX IX_Pracownik_ma_adres ON Pracownicy (Nr_adresu)
/

CREATE INDEX IX_Pracownik_ma_stanowisko ON Pracownicy (Nr_stanowiska)
/

CREATE INDEX IX_Zapewnia_stanowisko_pracy ON Pracownicy (Nr_siedziby)
/

-- Add keys for table Pracownicy

ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_PK PRIMARY KEY (Nr_pracownika)
/

-- Table Osoby_prywatne

CREATE TABLE Osoby_prywatne(
  Nr_klietna Integer NOT NULL,
  PESEL Integer NOT NULL,
  Imie Varchar2(30 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Plec Char(1 ) NOT NULL
        CHECK (Plec IN ('K','M')),
  Data_urodzin Date NOT NULL
)
/

-- Add keys for table Osoby_prywatne

ALTER TABLE Osoby_prywatne ADD CONSTRAINT Osoba_PK PRIMARY KEY (Nr_klietna)
/

-- Table Firmy

CREATE TABLE Firmy(
  Nr_klietna Integer NOT NULL,
  Nazwa_firmy Varchar2(100 ) NOT NULL,
  NIP Varchar2(12 ) NOT NULL
)
/

-- Add keys for table Firmy

ALTER TABLE Firmy ADD CONSTRAINT Firma_PK PRIMARY KEY (Nr_klietna)
/

-- Table Siedziby

CREATE TABLE Siedziby(
  Nr_siedziby Integer NOT NULL,
  Nr_operatora Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Siedziby

CREATE INDEX IX_W_posiadaniu ON Siedziby (Nr_operatora)
/

CREATE INDEX IX_Ma_siedziba ON Siedziby (Nr_adresu)
/

-- Add keys for table Siedziby

ALTER TABLE Siedziby ADD CONSTRAINT Sidziba_PK PRIMARY KEY (Nr_siedziby)
/

-- Table Uslugi

CREATE TABLE Uslugi(
  Nr_uslugi Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Koszt Float(2) NOT NULL,
  Nr_operatora Integer NOT NULL
)
/

-- Create indexes for table Uslugi

CREATE INDEX Pracownik_ma_stanowisko ON Uslugi (Nr_operatora)
/

-- Add keys for table Uslugi

ALTER TABLE Uslugi ADD CONSTRAINT Unique_Identifier11 PRIMARY KEY (Nr_uslugi)
/

-- Table Telewizje

CREATE TABLE Telewizje(
  Nr_uslugi Integer NOT NULL,
  Jakosc_obrazu Varchar2(30 ) NOT NULL,
  Obsluga_HDR Char(1 ) NOT NULL
)
/

-- Add keys for table Telewizje

ALTER TABLE Telewizje ADD CONSTRAINT Unique_Identifier12 PRIMARY KEY (Nr_uslugi)
/

-- Table Internety

CREATE TABLE Internety(
  Nr_uslugi Integer NOT NULL,
  Pub_adres_IP Varchar2(15 ) NOT NULL,
  Przepustowosc_pobierania Integer NOT NULL,
  Przepustowosc_wysylania Integer NOT NULL,
  Limit_pakietu Char(1 ) NOT NULL,
  Wielkosc_limitu Integer
)
/

-- Add keys for table Internety

ALTER TABLE Internety ADD CONSTRAINT Unique_Identifier13 PRIMARY KEY (Nr_uslugi)
/

-- Table Telefony

CREATE TABLE Telefony(
  Nr_uslugi Integer NOT NULL,
  Nr_telefonu Varchar2(12 ) NOT NULL,
  Limit_minut Char(1 ) NOT NULL,
  Wielkosc_limitu_minut Integer,
  Limit_sms Char(1 ) NOT NULL,
  Wielkosc_limit_sms Integer
)
/

-- Add keys for table Telefony

ALTER TABLE Telefony ADD CONSTRAINT Unique_Identifier14 PRIMARY KEY (Nr_uslugi)
/

-- Table Pracownik_Usluga

CREATE TABLE Pracownik_Usluga(
  Nr_pracownika Integer NOT NULL,
  Nr_uslugi Integer NOT NULL
)
/

-- Table Klient_Us³uga

CREATE TABLE Klient_Us³uga(
  Nr_klietna Integer NOT NULL,
  Nr_uslugi Integer NOT NULL
)
/

-- Table Pracownik_Klient

CREATE TABLE Pracownik_Klient(
  Nr_pracownika Integer NOT NULL,
  Nr_klietna Integer NOT NULL
)
/

-- Table Wynagrodzenia

CREATE TABLE Wynagrodzenia(
  Nr_wynagrodzenia Integer NOT NULL,
  Data_wynagrodzenia Date NOT NULL,
  Kwota_podstawowa Number(8,2) NOT NULL,
  Kwota_dodatkowa Number(8,2),
  Nr_pracownika Integer NOT NULL
)
/

-- Create indexes for table Wynagrodzenia

CREATE INDEX IX_Ma_wynagrodzenie ON Wynagrodzenia (Nr_pracownika)
/

-- Add keys for table Wynagrodzenia

ALTER TABLE Wynagrodzenia ADD CONSTRAINT PK_Wynagrodzenia PRIMARY KEY (Nr_wynagrodzenia)
/

-- Table and Columns comments section

COMMENT ON COLUMN Wynagrodzenia.Nr_wynagrodzenia IS 'Unikatowy identyfikator wynagrodzenia'
/
COMMENT ON COLUMN Wynagrodzenia.Data_wynagrodzenia IS 'Data wyp³aty'
/
COMMENT ON COLUMN Wynagrodzenia.Kwota_podstawowa IS 'Kwota podstawowa'
/
COMMENT ON COLUMN Wynagrodzenia.Kwota_dodatkowa IS 'Kwota dodatkowa'
/

-- Table Wlasciciele

CREATE TABLE Wlasciciele(
  Nr_wlasciela Integer NOT NULL,
  Imie Varchar2(30 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Nr_operatora Integer NOT NULL
)
/

-- Create indexes for table Wlasciciele

CREATE INDEX IX_Ma_wlasciciela ON Wlasciciele (Nr_operatora)
/

-- Add keys for table Wlasciciele

ALTER TABLE Wlasciciele ADD CONSTRAINT PK_Wlasciciele PRIMARY KEY (Nr_wlasciela)
/

-- Table and Columns comments section

COMMENT ON COLUMN Wlasciciele.Nr_wlasciela IS 'Unikalny indentyfikator w³aœciela'
/
COMMENT ON COLUMN Wlasciciele.Imie IS 'Imiê w³aœciela'
/
COMMENT ON COLUMN Wlasciciele.Nazwisko IS 'Nazwisko w³aœciela'
/

-- Table Adresy

CREATE TABLE Adresy(
  Nr_adresu Integer NOT NULL,
  Miasto Varchar2(30 ) NOT NULL,
  Ulica Varchar2(30 ) NOT NULL,
  Nr_budynku Varchar2(5 ) NOT NULL,
  Nr_lokalu Varchar2(10 ),
  Kod_poczty Char(6 ) NOT NULL,
  Kraj Varchar2(30 ) NOT NULL
)
/

-- Add keys for table Adresy

ALTER TABLE Adresy ADD CONSTRAINT PK_Adresy PRIMARY KEY (Nr_adresu)
/

-- Table and Columns comments section

COMMENT ON COLUMN Adresy.Nr_budynku IS 'Nr budynku w adresie'
/

-- Table Kierownicy

CREATE TABLE Kierownicy(
  Nr_kierownika Integer NOT NULL,
  Imie Varchar2(30 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Nr_siedziby Integer NOT NULL
)
/

-- Create indexes for table Kierownicy

CREATE INDEX IX_Ma_kierownika ON Kierownicy (Nr_siedziby)
/

-- Add keys for table Kierownicy

ALTER TABLE Kierownicy ADD CONSTRAINT PK_Kierownicy PRIMARY KEY (Nr_kierownika)
/

-- Table and Columns comments section

COMMENT ON COLUMN Kierownicy.Nr_kierownika IS 'Unikalny indentyfikator kierownika'
/
COMMENT ON COLUMN Kierownicy.Imie IS 'Imiê w³aœciela'
/
COMMENT ON COLUMN Kierownicy.Nazwisko IS 'Nazwisko w³aœciela'
/

-- Table Kanaly_telewizyjne

CREATE TABLE Kanaly_telewizyjne(
  Nr_kanalu Integer NOT NULL,
  Typ_kanalu Varchar2(20 ) NOT NULL
        CHECK (Typ_kanalu IN ('Informacyjny','Film','Sport','Muzyka','Dzieciecy','Ogólno_dostepne')),
  HD Char(1 ) NOT NULL,
  Nr_us³ugi Integer NOT NULL
)
/

-- Create indexes for table Kanaly_telewizyjne

CREATE INDEX IX_Zawiera_kanaly ON Kanaly_telewizyjne (Nr_us³ugi)
/

-- Add keys for table Kanaly_telewizyjne

ALTER TABLE Kanaly_telewizyjne ADD CONSTRAINT PK_Kanaly_telewizyjne PRIMARY KEY (Nr_kanalu)
/

-- Table and Columns comments section

COMMENT ON COLUMN Kanaly_telewizyjne.Typ_kanalu IS 'Typ kana³u telewizyjnego'
/
COMMENT ON COLUMN Kanaly_telewizyjne.HD IS 'Czy jest jakoœci HD'
/

-- Table Stanowiska

CREATE TABLE Stanowiska(
  Nr_stanowiska Integer NOT NULL,
  Opis Varchar2(400 ) NOT NULL,
  Uprawnienia_SAP Char(1 ) NOT NULL,
  Poziom_uprawnien Char(20 ) NOT NULL
        CHECK (Poziom_uprawnien IN ('1','2','3')),
  Poziom_pomocy Integer
        CHECK (Poziom_pomocy IN ('1','2','3'))
)
/

-- Add keys for table Stanowiska

ALTER TABLE Stanowiska ADD CONSTRAINT PK_Stanowiska PRIMARY KEY (Nr_stanowiska)
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE Pracownicy ADD CONSTRAINT Zatrudnia FOREIGN KEY (Nr_operatora) REFERENCES Operatorzy (Nr_operatora)
/



ALTER TABLE Siedziby ADD CONSTRAINT W_posiadaniu FOREIGN KEY (Nr_operatora) REFERENCES Operatorzy (Nr_operatora)
/



ALTER TABLE Wynagrodzenia ADD CONSTRAINT Ma_wynagrodzenie FOREIGN KEY (Nr_pracownika) REFERENCES Pracownicy (Nr_pracownika)
/



ALTER TABLE Wlasciciele ADD CONSTRAINT Ma_wlasciela FOREIGN KEY (Nr_operatora) REFERENCES Operatorzy (Nr_operatora)
/



ALTER TABLE Siedziby ADD CONSTRAINT Sziedziba_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Kierownicy ADD CONSTRAINT Ma_kierownika FOREIGN KEY (Nr_siedziby) REFERENCES Siedziby (Nr_siedziby)
/



ALTER TABLE Klienci ADD CONSTRAINT Klient_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Kanaly_telewizyjne ADD CONSTRAINT Zawiera_kanaly FOREIGN KEY (Nr_us³ugi) REFERENCES Telewizje (Nr_uslugi)
/



ALTER TABLE Uslugi ADD CONSTRAINT Oferuje_usluge FOREIGN KEY (Nr_operatora) REFERENCES Operatorzy (Nr_operatora)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_ma_stanowisko FOREIGN KEY (Nr_stanowiska) REFERENCES Stanowiska (Nr_stanowiska)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Zapewnia_stanowisko_pracy FOREIGN KEY (Nr_siedziby) REFERENCES Siedziby (Nr_siedziby)
/







select * from klienci;
DESCRIBE klienci;