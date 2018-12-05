# Suma elementelor unui vector.

# Zona de date
.data

# Vectorul pentru care calculam suma elementelor.
# .word depune un cuvant de 32 de biti la adresa curenta.
# Cuvintele din enumerarea de mai jos sunt depuse la adrese multiplu de 4
# consecutive, de forma vector, vector + 4, vector + 8 etc.
vector:
    .word 1, 2, 3, 4, 5

floats:
	.float 2.33, 43.3, 3411.54, 10.3, 21312.443, 2.3

maxim:
	.float 0.0

# Numarul de elemente din vector.
N:
    .word 4

NR:
	.word 65

str:
	.asciiz "Hello World!\n"

unu:
	.asciiz "1\n"

doi:
	.asciiz "2\n"

small:
	.asciiz "Small value\n"

large:
	.asciiz "Large value\n"

EX3NR:
	.word 20

SPACE:
	.asciiz " "

EOL:
	.asciiz "\n"

# Zona de cod
.text

# Eticheta main este obligatorie.
# Utilizam urmatorii registri:
# a0 - suma finala
# t0 - nr de elemente
# t1 - adresa elementului curent
# t2 - elementul curent
main:

	li $v0, 4	# 4 e codul lui print_str
	la $a0, str	# a0 va fi argumentul lui syscall
	syscall

    lw $t0 N            # Numarul de elemente
    li $a0 0            # Initializarea sumei la 0
    la $t1 vector       # Adresa primului element, utilizand la (load address)
loop:
    lw $t2 0($t1)       # Elementul curent
    add $a0 $a0 $t2     # Adaugarea lui la suma
    addiu $t0 $t0 -1    # Decrementarea numarului de elemente ramase
    addiu $t1 $t1 4     # Avans la adresa urmatorului element
    bgtz $t0 loop       # Reluare bucla daca a ramas cel putin un element
                        # bgtz = branch if greater than zero


# exercitiul 1-2

	li $v0 4
	la $a0 unu
	syscall


	lw $t0 NR
	ble $t0 64 skip_large # branch on less or equal to 64 to skip_large
	

	li $v0 4
	la $a0 large
	syscall
	jr eo1


skip_large:

	li $v0 4
	la $a0 small
	syscall
	jr eo1	

eo1:			# end of 1
	li $v0 4
	la $a0 doi
	syscall

# end of exercitiul 1-2


# exercitiul 3

	li $t0 0		# load immediate
	lw $a1 EX3NR		# load word

while_loop:
	bgt $t0 $a1 exit_while_loop	# jump la exit daca $to e mai mare decat a0
					# iar a0 este 20 (limita superioara)
	li $v0 1
	move $a0 $t0
	syscall

	li $v0 4
	la $a0 SPACE
	syscall

	addiu $t0 $t0 1
	jr while_loop

exit_while_loop:
	li $v0 4
	la $a0 EOL
	syscall

# end of exercitiul 3


# exercitiul 4

	li $t0 0		# load immediate
	lw $a1 EX3NR		# load word

# bagam elementele 0-20 pe stiva
while_loop2:
	bgt $t0 $a1 exit_while_loop2	# jump la exit daca $to e mai mare decat a0
					# iar a0 este 20 (limita superioara)
	subu $sp $sp 4
	sw $t0 ($sp)

	addiu $t0 $t0 1
	jr while_loop2

exit_while_loop2:

	li $t0 0		# load immediate
	lw $a1 EX3NR		# load word

# scoatem elemente 20-0 de pe stiva si le printam, cu spatiu dupa
while_loop3:
	bgt $t0 $a1 exit_while_loop3	# jump la exit daca $to e mai mare decat a0
					# iar a0 este 20 (limita superioara)

	# ce e pe stiva
	li $v0 1
	lw $a0 ($sp)
	syscall

	# spatiu
	li $v0 4
	la $a0 SPACE
	syscall

	addiu $sp $sp 4

	addiu $t0 $t0 1
	jr while_loop3

exit_while_loop3:


	li $v0 4
	la $a0 EOL
	syscall

# end of exercitiul 4


# exercitiul 5

	la $a2 floats
	l.s $f12 ($a2)

	addiu $a2 $a2 4

	li $t0 0		# load immediate
	li $a1 4


while_loop4:
	bgt $t0 $a1 exit_while_loop4

	l.s $f14 ($a2)
	c.le.s $f14 $f12
	bc1t skip_assign

	l.s $f12 ($a2)

skip_assign:

	addiu $t0 $t0 1
	addiu $a2 $a2 4

	jr while_loop4

exit_while_loop4:

	# print float
	li $v0 2
	syscall

	# print \n
	li $v0 4
	la $a0 EOL
	syscall

# end of exercitiul 5





    jr $ra              # Salt la adresa de revenire
                        # jr = jump register, $ra = return address
