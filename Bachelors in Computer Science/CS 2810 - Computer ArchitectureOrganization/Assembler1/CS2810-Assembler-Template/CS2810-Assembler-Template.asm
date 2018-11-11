TITLE CS2810 Assembler Template

; Student Name:  Jason Steadman
; Assignment Due Date:  10/05/2017

INCLUDE Irvine32.inc
.data
	;--------- Enter Data Here
	vSemester byte "CS2810 Fall Semester 2017", 0
	vProject byte "Assembler Assignment #1", 0
	vName byte "Jason Steadman", 0
	;Need a 0 to terminate, like C-strings
.code
main PROC
	;--------- Enter Code Below Here

	call clrscr

	;---------Row---------
	mov dh, 2
	;-------column--------
	mov dl, 12
	call gotoxy
	;--offset means an address--
	mov EDX, offset vSemester
	call writestring

	;Project
	mov dh, 3
	mov dl, 12
	call gotoxy
	mov EDX, offset vProject
	call writestring

	;Name
	mov dh, 4
	mov dl, 12
	call gotoxy
	mov EDX, offset vName
	call writestring

	;To clean up the "press any key""
	mov dh, 5
	mov dl, 12
	call gotoxy

	xor ecx, ecx
	call ReadChar

	exit
main ENDP

END main