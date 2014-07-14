# for the calculator program

Winddow.class: Window.java Drawing.class Parser.class
	javac Window.java
	
Drawing.class: Drawing.java Parser.class
	javac Drawing.java
	
Parser.class: Parser.java
	javac Parser.java

clean:
	rm -f *.class
