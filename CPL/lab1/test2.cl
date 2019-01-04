
class A {

	x : SELF_TYPE <- self;

	f1() : SELF_TYPE { self };
	f2() : A { self };
};

class B inherits A {
	g() : Int { 0 };
};

class C inherits B {

	i() : Object {
		let a : A <- new B,
		    b : B <- new B
		in
			{
				a.f1();
				-- a.g(); nu merge pt ca A (clasa statica a lui a) nu are o metoda g
				-- a.f1().g(); nu merge din acelasi motiv; SELF_TYPE e evaluat la A la compile time

				b.f1();
				b.g();
				b.f1().g();

				b.f2();
				b.g();
				-- b.f2().g(); nu merge pt ca se face upcast la A, si ajungem in aceeasi situatie ca in cele de mai sus (nu vedem g in scope)

				b <- b@A.f1();
				-- b@A.g(); nu merge din acelasi motiv ca 3 randuri mai sus
				b@A.f1().g(); -- merge pt ca se face downcast inapoi la B

				x <- self.f1();
				x <- f1();
			}
	};
};

class Main inherits IO {

	main() : Object {
		{
			out_string("merge\n");
		}
	};
};
