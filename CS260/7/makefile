PYTHON = python
VIEWER = less

.PHONY: prob1 prob2 view

prob1 : set.py
	chmod u+x test-set.py
	 ./test-set.py

prob2 : prob2.py
	 ./prob2.py

view : set.py prob2.py
	- $(VIEWER) set.py
	- $(VIEWER) prob2.py

prob2-view : prob2.py
	- $(VIEWER) prob2.py
