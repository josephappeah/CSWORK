cc=gcc
OBJ=o
EXE=main

$(EXE) : main.$(OBJ) foo.$(OBJ)
	$(cc) -o $@ main.$(OBJ) foo.$(OBJ)

foo.$(OBJ) : foo.c foo.h
	$(cc) -c foo.c

main.$(OBJ) : main.c foo.h
	$(cc) -c main.c

.PHONY : clean hello

hello :
	@ echo Hello.  My name is $${USER}

clean :
	- \rm *.$(OBJ)
	- \rm main

count :
	for (( i=0; i<10; ++i )) ;\
	do \
		echo $${i} ;\
	done
