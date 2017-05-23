PYTHON = python
VIEWER = less

.PHONY: prob1 run

prob1 : prob1.py
	chmod u+x prob1.py
	$(PYTHON) prob1.py

run :	prob1.py
	chmod u+x prob1.py
	$(PYTHON) prob1.py
