create or replace trigger insert_tranzactii
after insert on system.tranzactii

DECLARE
	tranzactie system.tranzactii % rowtype;
	stoc_ramas NUMBER;

BEGIN
	
	select *
	into tranzactie
	from system.tranzactii
	where id = (select max(id) from system.tranzactii);

	select cantitate
	into stoc_ramas
	from system.produse
	where id = tranzactie.id_produs;

	UPDATE system.produse
	SET cantitate = stoc_ramas - 1
	WHERE id = tranzactie.id_produs;

EXCEPTION
	WHEN OTHERS THEN
		NULL;
END;
/