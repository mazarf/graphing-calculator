
import java.util.*; // stacks

/**
 * Parser object used to evaluate an infix expression contained in a string.
 **/
class Parser {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		String st = "";
		while(!st.equals("q")) {
			st = s.nextLine();
			System.out.println(evaluate(st));
		}
	}
	
	public static double evaluate(String expression) {
		// uses shunting-yard algorithm
		Stack<Double> numbers = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();
		
		expression = expression.replaceAll("\\s+", ""); // internet, remove spaces
		
		for(int i = 0; i < expression.length(); i++) {
			char currentValue = expression.charAt(i);
			if(Character.isDigit(currentValue)) {
				// process digit, account for offset
				double[] resultAndOffset = processDigit(expression, i);
				//System.out.println(resultAndOffset[1]);
				numbers.push(resultAndOffset[0]);
				i = i + (int)resultAndOffset[1] - 1;
			}
			else if(currentValue == 'x') {
				// replace with value, otherwise treat as a number
			}
			else if(isOperator(currentValue)) {
				// process operator
				// if operator priority is lower than the top of stack,
				// pop until the stack is empty or the thing on top is lower 
				// priority
				
				processOperator(numbers, operators, currentValue);
				
			}
			else {
				System.out.println("Error: item not recognized: " + currentValue);
				return 0.0;
			}
		} // end for
		
		// cleanup
		while(!operators.empty() &&  numbers.size() > 1) {	
				double op2 = numbers.pop();
				double op1 = numbers.pop();
				numbers.push(operate(op1, op2, operators.pop()));
				
				if(!operators.empty() && operators.peek() == '(') {
					operators.pop();
				}
		}
		
		return numbers.peek();
	} // evaluate()
	
	
	// returns the processed digit and an offset for the loop
	private static double[] processDigit(String expression, int location) {
		int offset = 0;
		for(; location + offset < expression.length() &&
			(Character.isDigit(expression.charAt(location + offset))
			|| expression.charAt(location + offset) == '.');
			offset++);
			
		double parsedNum = Double.parseDouble(expression.substring(location, location + offset));
			
		double[] results = { parsedNum, offset };
		return results;
		
	} // processDigit()
	
	
	// operator functions
	
	private static void processOperator(Stack<Double> numbers, Stack<Character> operators, char operator) {
		if(operator == '(' || numbers.size() == 1) {
			operators.push(operator);
		} 
		else {
			while(!operators.empty() && operators.peek() != '('  && 
					priority(operator) <= priority(operators.peek())) {	
					double op2 = numbers.pop();
					double op1 = numbers.pop();
					numbers.push(operate(op1, op2, operators.peek()));
					operators.pop();
			}
			
			if(!operators.empty() && operators.peek() == '(') // get rid of left parens
				operators.pop();
			
			if(operator != ')')
				operators.push(operator);
			
		} // end if
	} // processOperator();
	
	
	private static double operate(double op1, double op2, char operator) {
		switch(operator) {
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
			case '*':
				return op1 * op2;
			case '/':
				return op1 / op2;
			case '^':
				return Math.pow(op1, op2);
			default:
				return 0.0; // error, i doubt it will happen though
			}
	}
	
	
	private static boolean isOperator(char c) {
		return c == '+' ||
				c == '-' ||
				c == '*' ||
				c == '/' ||
				c == '^' ||
				c == '(' ||
				c == ')';
	}
	
	
	// returns a value corresponding to the priority of an operator
	private static int priority(char operator) {
		switch(operator) {
			case '(':
				return 0;
			case ')':
				return 1;
			case '+':
			case '-':
				return 2;
			case '*':
			case '/':
				return 3;
			case '^':
				return 4;
			default: // error
				return -1;
		} // switch
	} // priority()
	
} // Parser
