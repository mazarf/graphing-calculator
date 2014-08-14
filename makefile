# for the calculator program

Window.class: Window.java Drawing.class Parser.class
	javac Window.java
	
Drawing.class: Drawing.java Parser.class
	javac Drawing.java
	
Parser.class: Parser.java
	javac Parser.java

calculator.jar: Window.class Drawing.class Parser.class
	jar cef Window calculator.jar Window.class 'Window$$1.class' 'Window$$2.class' Drawing.class Parser.class

clean:
	rm -f *.class
