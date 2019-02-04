    .data
    .align  2
    .globl  class_nameTab
    .globl  Int_protObj
    .globl  String_protObj
    .globl  bool_const0
    .globl  bool_const1
    .globl  Main_protObj
    .globl  _int_tag
    .globl  _string_tag
    .globl  _bool_tag
_int_tag:
    .word   2
_string_tag:
    .word   3
_bool_tag:
    .word   4
str_const0:
    .word   3
    .word   5
    .word   String_dispTab
    .word   int_const0
    .asciiz ""
    .align  2
str_const1:
    .word   3
    .word   6
    .word   String_dispTab
    .word   int_const1
    .asciiz "Object"
    .align  2
str_const2:
    .word   3
    .word   5
    .word   String_dispTab
    .word   int_const2
    .asciiz "IO"
    .align  2
str_const3:
    .word   3
    .word   5
    .word   String_dispTab
    .word   int_const3
    .asciiz "Int"
    .align  2
str_const4:
    .word   3
    .word   6
    .word   String_dispTab
    .word   int_const1
    .asciiz "String"
    .align  2
str_const5:
    .word   3
    .word   6
    .word   String_dispTab
    .word   int_const4
    .asciiz "Bool"
    .align  2
int_const0:
    .word   2
    .word   4
    .word   Int_dispTab
    .word   0
int_const1:
    .word   2
    .word   4
    .word   Int_dispTab
    .word   6
int_const2:
    .word   2
    .word   4
    .word   Int_dispTab
    .word   2
int_const3:
    .word   2
    .word   4
    .word   Int_dispTab
    .word   3
int_const4:
    .word   2
    .word   4
    .word   Int_dispTab
    .word   4
bool_const0:
    .word   4
    .word   4
    .word   Bool_dispTab
    .word   0
bool_const1:
    .word   4
    .word   4
    .word   Bool_dispTab
    .word   1
class_nameTab:
    .word   str_const1
    .word   str_const2
    .word   str_const3
    .word   str_const4
    .word   str_const5
class_objTab:
    .word   Object_protObj
    .word   Object_init
    .word   IO_protObj
    .word   IO_init
    .word   Int_protObj
    .word   Int_init
    .word   String_protObj
    .word   String_init
    .word   Bool_protObj
    .word   Bool_init
Object_protObj:
    .word   0
    .word   3
    .word   Object_dispTab

IO_protObj:
    .word   1
    .word   3
    .word   IO_dispTab

Int_protObj:
    .word   2
    .word   4
    .word   Int_dispTab
    .word   0
String_protObj:
    .word   3
    .word   5
    .word   String_dispTab
    .word   int_const0
    .asciiz ""
    .align  2
Bool_protObj:
    .word   4
    .word   4
    .word   Bool_dispTab
    .word   0
Object_dispTab:
    .word  Object.abort
    .word  Object.type_name
    .word  Object.copy
IO_dispTab:
    .word  Object.abort
    .word  Object.type_name
    .word  Object.copy
    .word  IO.out_string
    .word  IO.out_int
    .word  IO.in_string
    .word  IO.in_int
Int_dispTab:
    .word  Object.abort
    .word  Object.type_name
    .word  Object.copy
String_dispTab:
    .word  Object.abort
    .word  Object.type_name
    .word  Object.copy
    .word  String.length
    .word  String.concat
    .word  String.substr
Bool_dispTab:
    .word  Object.abort
    .word  Object.type_name
    .word  Object.copy
heap_start:
    .word   0
    .text
    .globl  Int_init
    .globl  String_init
    .globl  Bool_init
    .globl  Main_init
    .globl  Main.main
Object_init:
    addiu   $sp $sp -12
    sw      $fp 12($sp)
    sw      $s0 8($sp)
    sw      $ra 4($sp)
    addiu   $fp $sp 4
    move    $s0 $a0
    move    $a0 $s0
    lw      $fp 12($sp)
    lw      $s0 8($sp)
    lw      $ra 4($sp)
    addiu   $sp $sp 12
    jr      $ra
IO_init:
    addiu   $sp $sp -12
    sw      $fp 12($sp)
    sw      $s0 8($sp)
    sw      $ra 4($sp)
    addiu   $fp $sp 4
    move    $s0 $a0
    jal     Object_init
    move    $a0 $s0
    lw      $fp 12($sp)
    lw      $s0 8($sp)
    lw      $ra 4($sp)
    addiu   $sp $sp 12
    jr      $ra
Int_init:
    addiu   $sp $sp -12
    sw      $fp 12($sp)
    sw      $s0 8($sp)
    sw      $ra 4($sp)
    addiu   $fp $sp 4
    move    $s0 $a0
    jal     Object_init
    move    $a0 $s0
    lw      $fp 12($sp)
    lw      $s0 8($sp)
    lw      $ra 4($sp)
    addiu   $sp $sp 12
    jr      $ra
String_init:
    addiu   $sp $sp -12
    sw      $fp 12($sp)
    sw      $s0 8($sp)
    sw      $ra 4($sp)
    addiu   $fp $sp 4
    move    $s0 $a0
    jal     Object_init
    move    $a0 $s0
    lw      $fp 12($sp)
    lw      $s0 8($sp)
    lw      $ra 4($sp)
    addiu   $sp $sp 12
    jr      $ra
Bool_init:
    addiu   $sp $sp -12
    sw      $fp 12($sp)
    sw      $s0 8($sp)
    sw      $ra 4($sp)
    addiu   $fp $sp 4
    move    $s0 $a0
    jal     Object_init
    move    $a0 $s0
    lw      $fp 12($sp)
    lw      $s0 8($sp)
    lw      $ra 4($sp)
    addiu   $sp $sp 12
    jr      $ra
Main.main:
    addiu   $sp $sp -12
    sw      $fp 12($sp)
    sw      $s0 8($sp)
    sw      $ra 4($sp)
    addiu   $fp $sp 4
    move    $s0 $a0
    la      $a0 int_const0
    lw      $fp 12($sp)
    lw      $s0 8($sp)
    lw      $ra 4($sp)
    addiu   $sp $sp 12
    jr      $ra
