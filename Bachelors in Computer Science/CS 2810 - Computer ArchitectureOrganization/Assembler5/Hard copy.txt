TITLE CS2810 Assembler Template

; Student Name:  Jason Steadman
; Assignment Due Date:  12/03/2017

INCLUDE Irvine32.inc
.data
	;--------- Enter Data Here
	vSemester byte "CS2810 Fall Semester 2017", 0
	vProject byte "Assembler Assignment #5", 0
	vName byte "Jason Steadman", 0
	vCR byte 13,10,0
	vHigh byte " is too high.",0
	vLow byte " is too low.",0
	vVictoryDance byte " is correct!",0
	vPlayMore byte "Would you like to play again?  (1 for yes or 0 for no)",0
	vGuess byte "Guess a number between 0 and 100: ",0
	vGuessAgain byte "Guess again: ",0
	vBye byte "Thanks for the fun semester!",0

.code
main PROC
	;--------- Enter Code Below Here
	Start:
		call clrscr

		;---------Row---------
		mov dh, 4
		;-------column--------
		mov dl, 0
		call gotoxy
		;--offset means an address--
		mov EDX, offset vSemester
		call writestring

		;Project
		mov dh, 5
		mov dl, 0
		call gotoxy
		mov EDX, offset vProject
		call writestring

		;Name
		mov dh, 6
		mov dl, 0
		call gotoxy
		mov EDX, offset vName
		call writestring

		call Randomize
		mov eax, 101
		call RandomRange
		mov ecx, eax
		mov dh, 8
		mov dl, 0
		call gotoxy
		mov edx, offset vGuess
		call WriteString

	Game:
		call ReadDec
		cmp eax, ecx
		jg TooHigh
		jl TooLow
		jmp PlayAgain

	TooHigh:
		mov edx, offset vCR
		mov edx, offset vHigh
		call WriteDec
		call WriteString
		mov edx, offset vCR
		call WriteString
		mov edx, offset vGuessAgain
		call WriteString
		jmp Game

	TooLow:
		mov edx, offset vCR
		mov edx, offset vLow
		call WriteDec
		call WriteString
		mov edx, offset vCR
		call WriteString
		mov edx, offset vGuessAgain
		call WriteString
		jmp Game

	PlayAgain:
		mov edx, offset vCR
		mov edx, offset vVictoryDance
		call WriteDec
		call WriteString
		mov edx, offset vCR
		call WriteString
		mov edx, offset vPlayMore
		call WriteString
		mov edx, offset vCR
		call WriteString
		call ReadDec
		cmp eax, 0
		jz TheEnd
		jmp Start

	TheEnd:
		mov edx, offset vBye
		call WriteString
		mov edx, offset vCR
		call WriteString
		xor ecx, ecx
		call ReadChar

	exit
main ENDP

END main