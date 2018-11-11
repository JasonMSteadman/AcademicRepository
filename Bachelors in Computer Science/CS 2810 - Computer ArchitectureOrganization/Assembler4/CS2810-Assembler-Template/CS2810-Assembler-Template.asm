TITLE CS2810 Assembler Template

; Student Name:  Jason Steadman
; Assignment Due Date:  10/26/2017

INCLUDE Irvine32.inc
.data
	;--------- Enter Data Here
	vSemester byte "CS2810 Fall Semester 2017", 0
	vProject byte "Assembler Assignment #4", 0
	vName byte "Jason Steadman", 0
	vPrompt byte "Enter FAT16 file date in hex: ", 0
	vDate byte  "----, ----", 0
	vSuffix byte "--stndrdthththththththththththththththththstndrdthththththththst", 0
	VMonth byte "January ",0,"89","February ",0,"9","March ",0,"6789","April ",0,"6789",
				"May ",0,"456789","June ",0,"56789","July ",0,"56789","August ",0,"789",
				"September ",0,"October ",0,"89","November ",0,"9","December ",0,"9"
	;Need a 0 to terminate, like C-strings
.code
main PROC
	;--------- Enter Code Below Here

	call clrscr

	;---------Row---------
	mov dh, 4
	;-------column--------
	mov dl, 33
	call gotoxy
	;--offset means an address--
	mov EDX, offset vSemester
	call writestring

	;Project
	mov dh, 5
	mov dl, 33
	call gotoxy
	mov EDX, offset vProject
	call writestring

	;Name
	mov dh, 6
	mov dl, 33
	call gotoxy
	mov EDX, offset vName
	call writestring

	;Prompt
	mov dh, 8
	mov dl, 33
	call gotoxy
	mov EDX, offset vPrompt
	call writestring

	call readhex
	ror ax, 8
	mov ecx, eax		;Copy input

	mov dh, 10
	mov dl, 33
	call gotoxy

	;-----Year------ 
	;-----Decode year-----
	ror ax, 9
	and ax, 7Fh
	add ax, 1980d
	;-----------------------------------

	xor dx, dx		;0 dx for 16 bit div, dx,ax / bx
	mov bx, 1000
	div bx
	add al, 30h
	mov byte ptr [vDate + 6], al

	mov ax, dx
	xor dx, dx
	mov bx, 100
	div bx
	add al, 30h
	mov byte ptr [vDate + 7], al

	mov ax, dx
	mov bl, 10
	div bl
	add ax, 3030h
	mov word ptr [vDate + 8], ax

	;-----Day-----
	;-----Decode day-----
	mov eax, ecx
	and ax, 1Fh
	;-------------------------

	;-----Find suffix-----
	shl eax, 1		;size (2bytes)
	mov edx, offset vSuffix
	add edx, eax
	mov bx, word ptr [edx]
	mov word ptr [vDate + 2], bx
	

	;-----Find day-----
	shr eax, 1	;div by 2
	mov bl, 10
	div bl
	add eax, 3030h
	mov word ptr [vDate], ax

	;-----Month-----
	;-----Decode month---
	mov eax, ecx
	ror ax, 5
	and ax, 0Fh
	;------------------

	;-----Find month-----
	sub eax, 1
	mov edx, eax
	mov eax, 11
	mul edx
	mov edx, offset vMonth
	add edx, eax
	call writestring
	
	call Display
	call TheEnd
	

	Display:

		mov al, byte ptr [vDate]
		sub al, 30h
		cmp al, 0h
		jz Comp

		mov EDX, offset vDate
		call writestring

		ret
	Comp:
		mov EDX, offset (vDate + 1)
		call writestring
		ret

	TheEnd:
		mov dh, 11
		mov dl, 33
		call gotoxy

		xor ecx, ecx
		call ReadChar

	exit
main ENDP

END main