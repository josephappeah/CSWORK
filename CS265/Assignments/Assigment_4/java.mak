
####  Java  ########

.PHONY : build view clean

build :
	chmod +x assn4
	javac register.java

view :
	@\less register.java

clean :
	@\rm *.class

