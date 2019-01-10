drop table system.tranzactii;
drop table system.livrari;
drop table system.localitati;
drop table system.curieri;

create table system.localitati (
	id NUMBER PRIMARY KEY,
	nume VARCHAR2(30) NOT NULL
	);

create table system.curieri (
	id NUMBER PRIMARY KEY,
	nume VARCHAR2(30) NOT NULL
	);

create table system.livrari (
	id NUMBER PRIMARY KEY,
	id_curier NUMBER NOT NULL,
	id_localitate NUMBER NOT NULL,
	taxa NUMBER NOT NULL,
	CONSTRAINT FK_curier FOREIGN KEY (id_curier)
	REFERENCES system.curieri(id),
	CONSTRAINT FK_localitate FOREIGN KEY (id_localitate)
	REFERENCES system.localitati(id)
	);

create table system.tranzactii (
	id NUMBER PRIMARY KEY,
	id_curier NUMBER NOT NULL,
	id_localitate NUMBER NOT NULL,
	id_produs NUMBER NOT NULL,
	client_nume VARCHAR2(30) NOT NULL,
	client_prenume VARCHAR2(30) NOT NULL,
	client_telefon VARCHAR2(30) NOT NULL,
	adresa_livrare VARCHAR2(100) NOT NULL,
	CONSTRAINT FK_curier_tranzactie FOREIGN KEY (id_curier)
	REFERENCES system.curieri(id),
	CONSTRAINT FK_localitate_tranzactie FOREIGN KEY (id_localitate)
	REFERENCES system.localitati(id)
	);


drop table system.laptopuri;
drop table system.telefoane;
drop table system.monitoare;

drop table system.poze_produse;
drop table system.produse;
drop table system.categorii;

create table system.categorii (
	id NUMBER PRIMARY KEY,
	nume VARCHAR2(50) NOT NULL
	);

create table system.produse (
	id NUMBER PRIMARY KEY,
	nume VARCHAR2(50) NOT NULL,
	description VARCHAR2(1000),
	pret NUMBER NOT NULL,
	url_poza_profil VARCHAR2(3000),
	cantitate NUMBER,
	id_categorie NUMBER,
	CONSTRAINT FK_categorie FOREIGN KEY (id_categorie)
	REFERENCES system.categorii(id)
	);

create table system.poze_produse (
	id NUMBER PRIMARY KEY,
	id_produs NUMBER NOT NULL,
	url_poza VARCHAR2(3000),
	CONSTRAINT FK_produs FOREIGN KEY (id_produs)
	REFERENCES system.produse(id)
	);



create table system.laptopuri (
	id NUMBER PRIMARY KEY,
	id_produs NUMBER NOT NULL,
	procesor_producator VARCHAR2(50),
	procesor_nuclee NUMBER,
	procesor_frecventa_mhz NUMBER,
	display_diagonala NUMBER,
	display_rezolutie_x NUMBER,
	display_rezolutie_y NUMBER,
	ram_capacitate_gb NUMBER,
	ram_frecventa_mhz NUMBER,
	hdd_capacitate_gb NUMBER,
	hdd_frecventa_rpm NUMBER,
	CONSTRAINT FK_laptop FOREIGN KEY (id_produs)
	REFERENCES system.produse(id)
	);

create table system.telefoane (
	id NUMBER PRIMARY KEY,
	id_produs NUMBER NOT NULL,
	procesor_model VARCHAR2(50),
	procesor_nuclee NUMBER,
	sistem_de_operare VARCHAR2(50),
	ram_capacitate_gb NUMBER,
	memorie_capacitate_gb NUMBER,
	display_tip VARCHAR2(50),
	display_rezolutie_x NUMBER,
	display_rezolutie_y NUMBER,
	CONSTRAINT FK_telefon FOREIGN KEY (id_produs)
	REFERENCES system.produse(id)
	);

create table system.monitoare (
	id NUMBER PRIMARY KEY,
	id_produs NUMBER NOT NULL,
	display_diagonala NUMBER,
	display_rezolutie_x NUMBER,
	display_rezolutie_y NUMBER,
	display_frecventa_hz NUMBER,
	porturi_hdmi NUMBER,
	porturi_vga NUMBER,
	porturi_displayport NUMBER,
	latenta NUMBER,
	CONSTRAINT FK_monitor FOREIGN KEY (id_produs)
	REFERENCES system.produse(id)
	);

