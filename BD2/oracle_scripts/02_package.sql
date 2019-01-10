create or replace package system.comenzi as

	function adauga_laptop(
		nume VARCHAR2,
		description VARCHAR2,
		pret NUMBER,
		url_poza_profil VARCHAR2,
		cantitate NUMBER,
		id_categorie NUMBER,
		procesor_producator VARCHAR2,
		procesor_nuclee NUMBER,
		procesor_frecventa_mhz NUMBER,
		display_diagonala NUMBER,
		display_rezolutie_x NUMBER,
		display_rezolutie_y NUMBER,
		ram_capacitate_gb NUMBER,
		ram_frecventa_mhz NUMBER,
		hdd_capacitate_gb NUMBER,
		hdd_frecventa_rpm NUMBER) return NUMBER;

	function adauga_telefon(
		nume VARCHAR2,
		description VARCHAR2,
		pret NUMBER,
		url_poza_profil VARCHAR2,
		cantitate NUMBER,
		id_categorie NUMBER,
		procesor_model VARCHAR2,
		procesor_nuclee NUMBER,
		sistem_de_operare VARCHAR2,
		ram_capacitate_gb NUMBER,
		memorie_capacitate_gb NUMBER,
		display_tip VARCHAR2,
		display_rezolutie_x NUMBER,
		display_rezolutie_y NUMBER) return NUMBER;

	function adauga_monitor(nume VARCHAR2,
		description VARCHAR2,
		pret NUMBER,
		url_poza_profil VARCHAR2,
		cantitate NUMBER,
		id_categorie NUMBER,
		display_diagonala NUMBER,
		display_rezolutie_x NUMBER,
		display_rezolutie_y NUMBER,
		display_frecventa_hz NUMBER,
		porturi_hdmi NUMBER,
		porturi_vga NUMBER,
		porturi_displayport NUMBER,
		latenta NUMBER) return NUMBER;

	function adauga_tranzactie(
		id_curier NUMBER,
		id_localitate NUMBER,
		id_produs NUMBER,
		client_nume VARCHAR2,
		client_prenume VARCHAR2,
		client_telefon VARCHAR2,
		adresa_livrare VARCHAR2) return NUMBER;

	procedure obtine_curieri(localitate in VARCHAR2, curieri out sys_refcursor);

	procedure obtine_categorii(categorii out sys_refcursor);
	procedure obtine_laptopuri(laptopuri out sys_refcursor);
	procedure obtine_telefoane(telefoane out sys_refcursor);
	procedure obtine_monitoare(monitoare out sys_refcursor);
end comenzi;
/

create or replace package body system.comenzi as
	function adauga_laptop(
		nume VARCHAR2,
		description VARCHAR2,
		pret NUMBER,
		url_poza_profil VARCHAR2,
		cantitate NUMBER,
		id_categorie NUMBER,
		procesor_producator VARCHAR2,
		procesor_nuclee NUMBER,
		procesor_frecventa_mhz NUMBER,
		display_diagonala NUMBER,
		display_rezolutie_x NUMBER,
		display_rezolutie_y NUMBER,
		ram_capacitate_gb NUMBER,
		ram_frecventa_mhz NUMBER,
		hdd_capacitate_gb NUMBER,
		hdd_frecventa_rpm NUMBER)
	
	return NUMBER
	is
		max_id NUMBER;
		max_id_laptop NUMBER;
	BEGIN
		select count(id)
		into max_id
		from system.produse;

		select count(id)
		into max_id_laptop
		from system.laptopuri;

		INSERT INTO system.produse
		VALUES (
		    max_id + 1,
		    nume,
		    description,
		    pret,
		    url_poza_profil,
		    cantitate,
		    id_categorie
			);

		INSERT INTO system.laptopuri
		VALUES (
		    max_id_laptop + 1,
		    max_id + 1,
		    procesor_producator,
		    procesor_nuclee,
		    procesor_frecventa_mhz,
		    display_diagonala,
		    display_rezolutie_x,
		    display_rezolutie_y,
		    ram_capacitate_gb,
		    ram_frecventa_mhz,
		    hdd_capacitate_gb,
		    hdd_frecventa_rpm
			);

		return max_id + 1;

	END adauga_laptop;




	function adauga_telefon(
		nume VARCHAR2,
		description VARCHAR2,
		pret NUMBER,
		url_poza_profil VARCHAR2,
		cantitate NUMBER,
		id_categorie NUMBER,
		procesor_model VARCHAR2,
		procesor_nuclee NUMBER,
		sistem_de_operare VARCHAR2,
		ram_capacitate_gb NUMBER,
		memorie_capacitate_gb NUMBER,
		display_tip VARCHAR2,
		display_rezolutie_x NUMBER,
		display_rezolutie_y NUMBER)

	return NUMBER
	is
		max_id NUMBER;
		max_id_telefon NUMBER;

	BEGIN
		select count(id)
		into max_id
		from system.produse;

		select count(id)
		into max_id_telefon
		from system.telefoane;

		INSERT INTO system.produse
		VALUES (
		    max_id + 1,
		    nume,
		    description,
		    pret,
		    url_poza_profil,
		    cantitate,
		    id_categorie
			);

		INSERT INTO system.telefoane
		VALUES (
		    max_id_telefon + 1,
		    max_id + 1,
		    procesor_model,
		    procesor_nuclee,
		    sistem_de_operare,
		    ram_capacitate_gb,
		    memorie_capacitate_gb,
		    display_tip,
		    display_rezolutie_x,
		    display_rezolutie_y
			);

		return max_id + 1;
	END adauga_telefon;





	function adauga_monitor(nume VARCHAR2,
		description VARCHAR2,
		pret NUMBER,
		url_poza_profil VARCHAR2,
		cantitate NUMBER,
		id_categorie NUMBER,
		display_diagonala NUMBER,
		display_rezolutie_x NUMBER,
		display_rezolutie_y NUMBER,
		display_frecventa_hz NUMBER,
		porturi_hdmi NUMBER,
		porturi_vga NUMBER,
		porturi_displayport NUMBER,
		latenta NUMBER)

	return NUMBER
	is
		max_id NUMBER;
		max_id_monitor NUMBER;

	BEGIN
		select count(id)
		into max_id
		from system.produse;

		select count(id)
		into max_id_monitor
		from system.monitoare;

		INSERT INTO system.produse
		VALUES (
		    max_id + 1,
		    nume,
		    description,
		    pret,
		    url_poza_profil,
		    cantitate,
		    id_categorie
			);

		INSERT INTO system.monitoare
		VALUES (
		    max_id_monitor + 1,
		    max_id + 1,
		    display_diagonala,
		    display_rezolutie_x,
		    display_rezolutie_y,
		    display_frecventa_hz,
		    porturi_hdmi,
		    porturi_vga,
		    porturi_displayport,
		    latenta
		);

		return max_id + 1;

	END adauga_monitor;

	function adauga_tranzactie(
		id_curier NUMBER,
		id_localitate NUMBER,
		id_produs NUMBER,
		client_nume VARCHAR2,
		client_prenume VARCHAR2,
		client_telefon VARCHAR2,
		adresa_livrare VARCHAR2)
	return NUMBER
	is
		id_max NUMBER;
	BEGIN
		select count(id)
		into id_max
		from system.tranzactii;

		INSERT INTO system.tranzactii
		VALUES(
			id_max + 1,
			id_curier,
			id_localitate,
			id_produs,
			client_nume,
			client_prenume,
			client_telefon,
			adresa_livrare
			);

		return id_max + 1;
	END adauga_tranzactie;


	procedure obtine_curieri(localitate in VARCHAR2, curieri out sys_refcursor)
	is
	BEGIN
		open curieri for
			select *
			from system.livrari
			where id_localitate = (select id from system.localitati where nume = localitate);
	END obtine_curieri;

	procedure obtine_categorii(categorii out sys_refcursor)
	is
	BEGIN
		open categorii for
			select *
			from system.categorii;
	END obtine_categorii;

	procedure obtine_laptopuri(laptopuri out sys_refcursor)
	is
		categ_id NUMBER;
	BEGIN
		select id
		into categ_id
		from system.categorii
		where nume = 'laptopuri';

		open laptopuri for
			select *
			from system.produse
			where id_categorie = categ_id;
	END obtine_laptopuri;

	procedure obtine_telefoane(telefoane out sys_refcursor)
	is
		categ_id NUMBER;
	BEGIN
		select id
		into categ_id
		from system.categorii
		where nume = 'telefoane';

		open telefoane for
			select *
			from system.produse
			where id_categorie = categ_id;
	END obtine_telefoane;

	procedure obtine_monitoare(monitoare out sys_refcursor)
	is
		categ_id NUMBER;
	BEGIN
		select id
		into categ_id
		from system.categorii
		where nume = 'monitoare';

		open monitoare for
			select *
			from system.produse
			where id_categorie = categ_id;
	END obtine_monitoare;

end comenzi;
/
