PYTHON = python
VIEWER = less

.PHONY: problem1 problem2 problem3 run view 

problem1:
	chmod u+x problem1.py 
	./problem1.py

problem2:
	chmod u+x problem2.py 
	./problem2.py

problem3:
	chmod u+x problem3.py 
	./problem3.py
	gnuplot < plot-instructions

run : problem1 problem2 problem3 view
	
view : problem1.py problem2.py problem3.py
	- $(VIEWER) problem1.py 
	- $(VIEWER) problem2.py 
	- $(VIEWER) problem3.py