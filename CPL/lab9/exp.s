.data

EOL:
	.asciiz "\n"

.text

main:
	li $v0 1	# print int
	li $a0 65
	syscall

	li $v0 4	# print str
	la $a0 EOL
	syscall

	li $v0 9	# sbrk
	move $a0 $zero	# sbrk(0)
	syscall		# $v0 = sbrk(0)

	move $a0 $v0	# $a0 = $v0 = sbrk(0)
	li $v0 1	# print int
	syscall

	li $v0 4	# print str
	la $a0 EOL
	syscall

	jr $ra
