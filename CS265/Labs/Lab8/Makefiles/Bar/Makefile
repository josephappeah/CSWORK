cc = gcc
FLAGS = 

.PHONY : clean run

run : blah
	-./blah

blah : main.o bar.o
	$(cc) $(FLAGS) main.o bar.o -o blah

main.o : main.c bar.h
	$(cc) $(FLAGS) -c main.c

bar.o : bar.c bar.h
	$(cc) $(FLAGS) -c bar.c

clean :
	-\rm *.o 
	-\rm blah
