(*
    Laborator COOL.
*)

(*
    Exercițiul 1.

    Implementați funcția fibonacci, utilizând atât varianta recursivă,
    cât și cea iterativă.
*)
class Fibo {
    fibo_rec(n : Int) : Int {
	if n <= 0 then ~1 else
        if n = 1 then 1 else
	if n = 2 then 1 else
	let a : Int <- n - 1,
	    b : Int <- n - 2 in
	(
		fibo_rec(a) + fibo_rec(b)
	)
	fi fi fi
    };

    fibo_iter(n : Int) : Int {
	if n <= 0 then ~1 else
        if n = 1 then 1 else
	if n = 2 then 1 else
		let a : Int <- 1,
		    b : Int <- 1 in
		{
			while 2 < n loop
			{
				b <- a + b;
				a <- b - a;
				n <- n - 1;
			}
			pool;

			b;
		}
	fi fi fi
    };
};
    
(*
    Exercițiul 2.

    Pornind de la ierarhia de clase implementată la curs, aferentă listelor
    (găsiți clasele List și Cons mai jos), implementați următoarele funcții
    și testați-le. Este necesară definirea lor în clasa List și supradefinirea
    în clasa Cons.

    * append: întoarce o nouă listă rezultată prin concatenarea listei curente
        (self) cu lista dată ca parametru;
    * reverse: întoarce o nouă listă cu elementele în ordine inversă.
*)

(*
    Listă omogenă cu elemente de tip Int. Clasa List constituie rădăcina
    ierarhiei de clase reprezentând liste, codificând în același timp
    o listă vidă.

    Adaptare după arhiva oficială de exemple a limbajului COOL.
*)
class List inherits IO {
    isEmpty() : Bool { true };

    -- 0, deși cod mort, este necesar pentru verificarea tipurilor
    hd() : Int { { abort(); 0; } };

    -- Similar pentru self
    tl() : List { { abort(); self; } };

    cons(h : Int) : Cons {
        new Cons.init(h, self)
    };

	append(ls : List) : List {
		ls
	};

	reverse() : List {
		self
	};

    print() : IO { out_string("\n") };

	map(m : Map) : List {
		self
	};

	filter(f : Filter) : List {
		self
	};
};

(*
    În privința vizibilității, atributele sunt implicit protejate, iar metodele,
    publice.

    Atributele și metodele utilizează spații de nume diferite, motiv pentru care
    hd și tl reprezintă nume atât de atribute, cât și de metode.
*)
class Cons inherits List {
    hd : Int;
    tl : List;

    init(h : Int, t : List) : Cons {
        {
            hd <- h;
            tl <- t;
            self;
        }
    };

    -- Supradefinirea funcțiilor din clasa List
    isEmpty() : Bool { false };

    hd() : Int { hd };

    tl() : List { tl };

	append(ls : List) : List {
		let newlist : List <- new Cons.init(self.hd(), self.tl().append(ls))
		in
		(
			newlist
		)
	};

	reverse() : List {
		self.tl().reverse().append(new List.cons(self.hd()))
	};

    print() : IO {
        {
            out_int(hd);
            out_string(" ");
            -- Mecanismul de dynamic dispatch asigură alegerea implementării
            -- corecte a metodei print.
            tl.print();
        }
    };

	map(m : Map) : List {
		let result : List <- new Cons.init(m.apply(self.hd()), self.tl().map(m))
		in (result)
	};

	filter(f : Filter) : List {
		if f.apply(self.hd()) then new Cons.init(self.hd(), self.tl().filter(f))
		else self.tl().filter(f)
		fi
	};
};

(*
    Exercițiul 3.

    Scopul este implementarea unor mecanisme similare funcționalelor
    map și filter din limbajele funcționale. map aplică o funcție pe fiecare
    element, iar filter reține doar elementele care satisfac o anumită condiție.
    Ambele întorc o nouă listă.

    Definiți clasele schelet Map, respectiv Filter, care vor include unica
    metodă apply, având tipul potrivit în fiecare clasă, și implementare
    de formă.

    Pentru a defini o funcție utilă, care adună 1 la fiecare element al listei,
    definiți o subclasă a lui Map, cu implementarea corectă a metodei apply.

    În final, definiți în cadrul ierarhiei List-Cons o metodă map, care primește
    un parametru de tipul Map.

    Definiți o subclasă a subclasei de mai sus, care, pe lângă funcționalitatea
    existentă, de incrementare cu 1 a fiecărui element, contorizează intern
    și numărul de elemente prelucrate. Utilizați static dispatch pentru apelarea
    metodei de incrementare, deja definită.

    Repetați pentru clasa Filter, cu o implementare la alegere a metodei apply.
*)

class Map {
	apply(a : Int) : Int {
		0
	};
};

class IncMap inherits Map {
	apply(a : Int) : Int {
		a + 1
	};
};

class CntIncMap inherits Map {
	cnt : Int;

	init() : CntIncMap {
		{
			cnt <- 0;
			self;
		}
	};

	cnt() : Int { cnt };

	apply(a : Int) : Int {
		{
			cnt <- cnt + 1;
			a + 1;
		}
	};
};

class Filter {
	apply (a : Int) : Bool {
		true
	};
};

class BiggerThan10Filter inherits Filter {
	apply (a : Int) : Bool {
		if 10 < a then true
		else false
		fi
	};
};

class CntBiggerThan10Filter inherits BiggerThan10Filter {
	cnt : Int;

	init() : CntBiggerThan10Filter {
		{
			cnt <- 0;
			self;
		}
	};

	cnt() : Int { cnt };

	apply (a : Int) : Bool {
		if 10 < a then true
		else false
		fi
	};
};

-- Clase de test pt tema 1
class Test {

	a : Int <- 3;
	b : Int <- a;
	c : Int <- if a < 3 then 4 else 7 fi;
	d : Int <- c <- 3;
	e : Object <- while (a < 3)
		    loop {
			3;
		    } pool;
};

-- Testați în main.
class Main inherits IO {
    main() : Object {
        let list : List <- new List.cons(1).cons(2).cons(3),
            temp : List <- list,
	    fibo : Fibo <- new Fibo,
            lista1 : List <- new List.cons(9).cons(8).cons(7),
            lista2 : List <- new List.cons(12).cons(11).cons(10),
            lista3 : List <- lista1.append(lista2),
            lista3tmp : List <- lista3,
            lista4 : List <- lista3.reverse(),
            lista4tmp : List <- lista4,
            m : CntIncMap <- new CntIncMap.init(),
            lista5 : List <- lista3.map(m),
            lista5tmp : List <- lista5,
            f : CntBiggerThan10Filter <- new CntBiggerThan10Filter.init(),
            lista6 : List <- lista3.filter(f),
            lista6tmp : List <- lista6
        in
            {
                -- Afișare utilizând o buclă while. Mecanismul de dynamic
                -- dispatch asigură alegerea implementării corecte a metodei
                -- isEmpty, din clasele List, respectiv Cons.
                while (not temp.isEmpty()) loop
                    {
                        out_int(temp.hd());
                        out_string(" ");
                        temp <- temp.tl();
                    }
                pool;
                out_string("\n");

                -- Afișare utilizând metoda din clasele pe liste.
                list.print();

		out_int(fibo.fibo_rec(6));
                out_string("\n");
		out_int(fibo.fibo_iter(4));
                out_string("\n");

                while (not lista3tmp.isEmpty()) loop
                    {
                        out_int(lista3tmp.hd());
                        out_string(" ");
                        lista3tmp <- lista3tmp.tl();
                    }
                pool;
                out_string("\n");

                while (not lista4tmp.isEmpty()) loop
                    {
                        out_int(lista4tmp.hd());
                        out_string(" ");
                        lista4tmp <- lista4tmp.tl();
                    }
                pool;
                out_string("\n");

                while (not lista5tmp.isEmpty()) loop
                    {
                        out_int(lista5tmp.hd());
                        out_string(" ");
                        lista5tmp <- lista5tmp.tl();
                    }
                pool;
                out_string("\n");
		out_int(m.cnt());
                out_string("\n");

                while (not lista6tmp.isEmpty()) loop
                    {
                        out_int(lista6tmp.hd());
                        out_string(" ");
                        lista6tmp <- lista6tmp.tl();
                    }
                pool;
                out_string("\n");
            }
    };
};
