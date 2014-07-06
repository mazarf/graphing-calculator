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
