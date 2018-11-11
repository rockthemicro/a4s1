struct pair {
	long int a;
	long int b;
};

program LOAD_PROG {
	version LOAD_VERS {
		pair GET_PAIR(long int client_input) = 1;
	} = 1;
} = 1;
