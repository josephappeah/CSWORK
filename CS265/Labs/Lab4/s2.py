#!/usr/bin/python




f = file('students.csv', 'r')
 
lines = f.readlines()

for l in lines:
	l.strip( ' \t\n' )
	
	total=0.0
	NumGrades=0.0
	for i in l.split(','): 
		
		if(i.isdigit()):
			total += float(i) 
			NumGrades += 1.0
			
	print l.split(",")[0], total/NumGrades
	