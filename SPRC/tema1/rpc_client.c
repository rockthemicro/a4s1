/**
	Sisteme de programe pentru retele de calculatoare
	
	Copyright (C) 2008 Ciprian Dobre & Florin Pop
	Univerity Politehnica of Bucharest, Romania

	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
 */

#include <stdio.h> 
#include <time.h> 
#include <rpc/rpc.h> 

#include "load.h" 

/*
 * Reads a line from 'f' and tries to convert it to a long int;
 *
 * @param f input stream
 * @param err output variable to indicate an error:
 * 	0 - no error
 * 	1 - EOF has been reached in 'f'
 * 	2 - could not convert string to long
 * @return converted number
 */
long int get_int_from_stream(FILE *f, int *err) {
	char line[100];
	*err = 0;
	long int result = 0;

	if (fgets(line, 100, f) == NULL) {
		*err = 1;
		return result;
	}

	result = strtol(line, NULL, 10);

	if (result == 0 && strcmp(line, "0") != 0) {
		*err = 2;
		return result;
	}

	return result;
}

int main(int argc, char *argv[]) {

	if (argc != 3) {
		fprintf(stderr, "Incorrect usage. It should be ./client server_ip file_name");
		return -1;
	}

	/* variabila clientului */
	CLIENT *handle;

	var *res;
	long int nr1 = 0;
	long int nr2 = 0;
	struct rpc_err err;
	int error = 0, i = 0;

	FILE *f = fopen(argv[2], "rt");
	if (f == NULL) {
		fprintf(stderr, "Could not open file %s", argv[2]);
		return -1;
	}

	handle = clnt_create(
		argv[1],		/* numele masinii unde se afla server-ul */
		LOAD_PROG,		/* numele programului disponibil pe server */
		LOAD_VERS,		/* versiunea programului */
		"tcp");			/* tipul conexiunii client-server */

	if(handle == NULL) {
		fprintf(stderr, "Could not open client handle");
		fclose(f);
		return -1;
	}

	nr1 = get_int_from_stream(f, &error);
	if (error == 1 || error == 2) {
		fprintf(stderr, "Input file is empty or first number could not be converted to int");
		fclose(f);
		return -1;
	}

	for (i = 0; i < nr1; i++) {
		nr2 = get_int_from_stream(f, &error);
		if (error != 0) continue;

		res = get_prime_1(&nr2, handle); 

		if (res == NULL) {
			handle->cl_ops->cl_geterr(handle, &err);
			printf("EROARE\n");
		} else {
			printf("%s %ld %ld\n", res->resp, res->p, res->q);
		}
	}

	fclose(f);
	return 0;
}
