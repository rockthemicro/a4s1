class A inherits IO {

	init() : SELF_TYPE {
		self
	};

	a : A <- method1();

	method1() : SELF_TYPE {
		{
			print_name();
			self.print_name();
			self@A.print_name();
			out_string("\n");

			self;
		}
	};

	print_name() : Object {
		let
			printer : IO <- new IO
		in
		{
			printer.out_string("Litera A\n");
		}
	};
};

class B inherits A {

	print_name() : Object {
		let
			printer : IO <- new IO
		in
		{
			printer.out_string("Litera B\n");
		}
	};
};

class C inherits B {

};

class Main inherits IO {

	a0 : A <- (new A).init();
	a : A <- (new B).init();
	b : B <- (new B).init();
	c : C <- (new C).init();

	main() : Object {
		{
			out_string("merge\n");
			a.method1();
			a.print_name();

			out_string("\nb.method: \n");
			b.method1();
			b.print_name();

			out_string("\nc.method: \n");
			c.method1();
			c.print_name();
		}
	};
};
