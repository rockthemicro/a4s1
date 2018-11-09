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

#include "uthash/uthash.h"

struct is_prime {
	long int id;
	unsigned char is_prime;
	UT_hash_handle hh;
};

struct is_prime *prime_global_hash = NULL;

struct even_nr_decomp {
	long int id;
	long int a;
	long int b;
	UT_hash_handle hh;
};

struct even_nr_decomp *decomp_global_hash = NULL;

/*
 * variant of the classic O(sqrt(N)) algorithm; it uses the fact that a prime number
 * (except 2 and 3) is of form 6k - 1 or 6k + 1 and looks only at divisors of this
 * form
 */
long int is_prime(long int n) {
	long int i = 5, w = 2;

	if (n == 1)
		return 0;

	if (n == 2)
		return 1;

	if (n == 3)
		return 1;

	if (n % 2 == 0)
		return 0;

	if (n % 3 == 0)
		return 0;

	while (i * i <= n) {
		if (n % i == 0)
			return 0;

		i += w;
		w = 6 - w;
	}

	return 1;
}

var * get_prime_1_svc(long int * nr, struct svc_req *cl) {
	static var result;
	long int nr_recv = *nr;
	printf("procesam %ld", nr_recv);

	memset(&result, 0, sizeof(result));

	nr_recv = nr_recv < 0 ? (-nr_recv) : nr_recv;
	if (nr_recv % 2 == 1) {
		struct is_prime *is_prime_p = NULL;
		HASH_FIND_INT(prime_global_hash, &nr_recv, is_prime_p);

		if (is_prime_p != NULL) {

			if (is_prime_p->is_prime == 0) {
				sprintf(result.resp, "NO");
				result.p = nr_recv;
				result.q = 0;
			} else {
				sprintf(result.resp, "YES");
				result.p = nr_recv;
				result.q = 1;
			}
		} else {
			is_prime_p = calloc(1, sizeof(*is_prime_p));
			if (is_prime_p == NULL) {
				fprintf(stderr, "No memory\n");
				return NULL;
			}

			is_prime_p->id = nr_recv;

			if (is_prime(nr_recv) == 0) {
				sprintf(result.resp, "NO");
				result.p = nr_recv;
				result.q = 0;

				is_prime_p->is_prime = 0;
			} else {
				sprintf(result.resp, "YES");
				result.p = nr_recv;
				result.q = 1;

				is_prime_p->is_prime = 1;
			}

			HASH_ADD_INT(prime_global_hash, id, is_prime_p);
		}
	} else {
		sprintf(result.resp, "YES");

		struct even_nr_decomp *even_nr_decomp_p = NULL;
		HASH_FIND_INT(decomp_global_hash, &nr_recv, even_nr_decomp_p);

		if (nr_recv == 2) {
			result.p = 1;
			result.q = 1;

		} else if (even_nr_decomp_p != NULL) {
			result.p = even_nr_decomp_p->a;
			result.q = even_nr_decomp_p->b;

		} else if (nr_recv != 0){
			long int start = nr_recv - 1;
			even_nr_decomp_p = calloc(1, sizeof(*even_nr_decomp_p));
			if (even_nr_decomp_p == NULL) {
				fprintf(stderr, "No memory\n");
				return NULL;
			}

			even_nr_decomp_p->id = nr_recv;
			while (1) {
				if (is_prime(start) == 1) {
					if (is_prime(nr_recv - start) == 1) {
						result.p = start;
						result.q = nr_recv - start;

						even_nr_decomp_p->a = result.p;
						even_nr_decomp_p->b = result.q;
						HASH_ADD_INT(decomp_global_hash, id,
								even_nr_decomp_p);
						break;
					}
				}

				start -= 2;
			}
		}
	}

	if (nr_recv == -(*nr)) {
		result.p = -result.p;
		result.q = -result.q;
	}

	return &result;
}
