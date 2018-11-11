#include <stdio.h> 
#include <time.h> 
#include <rpc/rpc.h> 

#include "load.h" 

#define EXIT			\
	do {			\
		result = -1;	\
		goto error;	\
	} while(0);

#define FGETS_CONVERT(file, line, nr, err)			\
	do {							\
		err = 0;					\
		if (fgets(line, sizeof(line), f) == NULL) {	\
			err = -1;				\
		}						\
								\
		nr = strtol(line, NULL, 10);			\
		if (nr == 0 && strcmp(line, "0") != 0) {	\
			err = -1;				\
		}						\
	} while (0);

void send_requests(CLIENT *handle, FILE *f) {

	char line[50];
	int input_size = 0;
	long int nr = 0;
	int err = 0;
	int i = 0;
	pair *response = NULL;

	FGETS_CONVERT(f, line, input_size, err);
	if (err != 0) {
		fprintf(stderr, "Could not obtain first number in input file\n");
		return;
	}

	/* reads all numbers from f, sends them to the server and outputs result */
	for (i = 0; i < input_size; i++) {
		FGETS_CONVERT(f, line, nr, err);
		if (err != 0) {
			fprintf(stderr, "Error converting the number with the index %d\n", i);
			continue;
		}

		response = get_pair_1(&nr, handle);
		if (response == NULL) {
			fprintf(stderr, "Error with the request sent with parameter %ld\n", nr);
			continue;
		}

		if (response->b != 0) {
			fprintf(stdout, "YES ");
		} else {
			fprintf(stdout, "NO ");
		}

		fprintf(stdout, "%ld %ld\n", response->a, response->b);
	}
}

int main(int argc, char *argv[]) {
	int result = 0;
	FILE *f = NULL;
	CLIENT *handle;

	if (argc != 3) {
		fprintf(stderr, "Please enter use it as ./client server_name input_file\n");
		EXIT;
	}

	handle = clnt_create(argv[1], LOAD_PROG, LOAD_VERS, "tcp");

	if(handle == NULL) {
		fprintf(stderr, "Error while opening client handle\n");
		EXIT;
	}

	f = fopen(argv[2], "r");
	if (f == NULL) {
		EXIT;
	}

	send_requests(handle, f);

error:

	if (f != NULL) {
		fclose(f);
	}

	return result;
}
