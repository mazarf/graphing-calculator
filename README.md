graphing-calculator
===================

So far I have a parser that uses the "shunting-yard" algorithm
to parse a string expression. I'm having trouble with unary operators
though...

Update 7/5:
Parser now works as well as I want it to. It can read unary negation in any
context.  However, both powers and negation are left-associative.  Syntax errors
will result in the program crashing... sometimes.  I think I'll just keep this a
static class now.

7/14:

To build class files, type make.
To build an executable jar file, type make jar.

Strange bug surfaced where some circles are drawn in the wrong place when f(x) is too high (and only sometimes). Started when I switched to Ubuntu 14.04.
