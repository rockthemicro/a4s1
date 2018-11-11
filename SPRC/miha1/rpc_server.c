#include <stdio.h> 
#include <time.h> 
#include <rpc/rpc.h> 
#include <assert.h>

#include "load.h"

#include "uthash/uthash.h"

/* data structure for working with uthash hashtable */
typedef struct {
	long int id;
	char is_prime;
	UT_hash_handle hh;
} hash_prime_t;

/* table holding information about odd numbers' prime property */
hash_prime_t *primes_hashtable = NULL;

/* global variable sent as a response to all client requests */
pair response;

/* classic sqrt(n) prime algorithm (with a few more base cases) */
unsigned char prime(long int n) {
	long int i = 7;

	if (n == 1)
		return 1;

	if (n == 2)
		return 1;

	if (n == 3)
		return 1;

	if (n == 5)
		return 1;

	if (n % 2 == 0)
		return 0;

	if (n % 3 == 0)
		return 0;

	if (n % 5 == 0)
		return 0;

	while (i * i <= n) {
		if (n % i == 0)
			return 0;

		i++;
	}

	return 1;
}

/*
 * tries to find information about 'number' in the 'primes_hashtable' hashtable
 * if it doesn't find any, it creates information and adds it to 'primes_hashtable'
 */
hash_prime_t *find_or_create_prime_info(long int number) {
	hash_prime_t *elem = NULL;
	HASH_FIND_INT(primes_hashtable, &number, elem);

	if (elem == NULL) {
		elem = malloc(sizeof(*elem));
		assert(elem != NULL);

		elem->id = number;
		elem->is_prime = prime(number);

		HASH_ADD_INT(primes_hashtable, id, elem);
	}

	return elem;
}

pair * get_pair_1_svc(long int * client_input, struct svc_req *cl) {
	long int input = *client_input;
	hash_prime_t *prime_info1 = NULL;
	hash_prime_t *prime_info2 = NULL;
	long int i = 0;

	response.a = 0;
	response.b = 0;

	if (input <= 0) {
		return &response;
	}

	if (input % 2 == 1) {
		prime_info1 = find_or_create_prime_info(input);
		if (prime_info1->is_prime == 1) {
			response.a = input;
			response.b = 1;
		} else {
			response.a = input;
			response.b = 0;
		}
	} else {
		i = input - 1;
		while (i >= 1) {
			prime_info1 = find_or_create_prime_info(i);
			if (prime_info1->is_prime == 1) {
				prime_info2 = find_or_create_prime_info(input - i);
				if (prime_info2->is_prime == 1) {
					response.a = i;
					response.b = input - i;

					break;
				}
			}

			i -= 2;
		}
	}

	return &response;
}
