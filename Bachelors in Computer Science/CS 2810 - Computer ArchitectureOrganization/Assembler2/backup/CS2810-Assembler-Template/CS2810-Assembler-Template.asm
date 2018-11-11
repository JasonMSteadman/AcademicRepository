TITLE CS2810 Assembler Template

; Student Name:  Jason Steadman
; Assignment Due Date:  10/12/2017

INCLUDE Irvine32.inc
.data
	;--------- Enter Data Here
	vSemester byte "CS2810 Fall Semester 2017", 0
	vProject byte "Assembler Assignment #2", 0
	vName byte "Jason Steadman", 0
	vPrompt byte "Please enter a FAT16 file time in hex:  ",0
	vTimeField byte "--:--:--",0

	;Need a 0 to terminate, like C-strings
.code
main PROC
	;--------- Enter Code Below Here

	call clrscr

	mov dh, 15		;Row
	mov dl, 20		;column
	call gotoxy
	
	mov EDX, offset vSemester		;offset means an address
	call writestring

	;--Project--
	mov dh, 16
	mov dl, 20
	call gotoxy
	mov EDX, offset vProject
	call writestring

	;--Name--
	mov dh, 17
	mov dl, 20
	call gotoxy
	mov EDX, offset vName
	call writestring

	;--Computer:"blink, blink, blink", User"WHAT DO YOU WANT FROM ME?!?!"--
	mov dh, 19
	mov dl, 20
	call gotoxy
	mov EDX, offset vPrompt
	call writestring

	call readhex		;Get user input
	ror ax, 8			;Little endian reverse
	mov ecx, eax		;Copy ax

	;--Hours--
	shr ax, 11			;Position bits
	and ax, 001Fh		;Bit mask

	mov bl, 10		
	div bl	
	add ax, 3030h		;Converting to ASCII (*30h)
	mov word ptr vTimeField, ax			;Moving 2 bytes aka a word into vTimeField

	;--Minutes--
	mov ax , cx			;Start with original entry
	shr ax , 5
	and ax , 003Fh

	mov bl, 10
	div bl
	add ax, 3030h
	mov word ptr (vTimeField + 3), ax

	;--Seconds--
	mov ax , cx			
	and ax , 001Fh

	add ax , ax		;Times two
	mov bl, 10
	div bl
	add ax, 3030h
	mov word ptr (vTimeField + 6), ax

	;--Out put--
	mov dh, 20		;Row
	mov dl, 20		;column
	call gotoxy
	
	mov EDX, offset vTimeField		
	call writestring

	;--Clear up "Press any key"--
	mov dh, 21
	mov dl, 20
	call gotoxy

	xor ecx, ecx
	call ReadChar

	exit
main ENDP
END main