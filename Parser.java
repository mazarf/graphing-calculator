
import java.util.*; // stacks

/**
 * Parser static class used to evaluate an infix expression contained in a string.
 **/
class Parser {
	//public static void main(String args[]) {
		//Scanner s = new Scanner(System.in);
		//String st = "";
		//while(!st.equals("q")) {
			//st = s.nextLine();
			//System.out.println(evaluate(st));
		//}
	//}
	
	public static double evaluate(String expression) {
		return evaluate(expression, 0.0);
	}
	
	public static double evaluate(String expression, double variable) {
		// uses shunting-yard algorithm
		Stack<Double> numbers = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();
		
		expression = expression.replaceAll("\\s+", ""); // internet, remove spaces
		
		for(int i = 0; i < expression.length(); i++) {
			char currentValue = expression.charAt(i);
			if(Character.isDigit(currentValue) || currentValue == '.') {
				// process digit, account for offset
				double[] resultAndOffset = processDigit(expression, i);
				numbers.push(resultAndOffset[0]);
				i = i + (int)resultAndOffset[1] - 1;
			}
			else if(currentValue == 'x') {
				// replace with value, otherwise treat as a number
				numbers.push(variable);
				
			}
			else if(isOperator(currentValue)) {
				// process operator
				if(currentValue == '-' && (i == 0 || isOperator(expression.charAt(i - 1))))
					currentValue = '~'; // unary negative operator
				processOperator(numbers, operators, currentValue);
				
			}
			else {
				System.out.println("Error: item not recognized: " + currentValue);
				return Double.NaN;
			}
		} // end for
		
		// cleanup
		while(!operators.empty() && operators.peek() != '(') {
				double op2 = numbers.pop();
				double op1 = (operators.peek() == '~') ? 0 : numbers.pop();
				numbers.push(operate(op1, op2, operators.pop()));
		}
		
		if(!operators.empty()) // last parentheses
			operators.pop();
			
		if(!operators.empty() && operators.peek() == '~') // last negation
			numbers.push(operate(0, numbers.pop(), operators.pop()));
			
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
		if(operator == '(') {
			operators.push(operator);
		} 
		//else if(numbers.size() == 1) { // handles the corner case of a '-' at the beginning
		//	if(!operators.empty() && operators.peek() == '~') {
		//		double op1 = numbers.pop();
		//		numbers.push(operate(0.0, op1, operators.pop()));
		//	}
		//	if(operator != ')')
		//		operators.push(operator);
		//}
		else {
			while(!operators.empty() && operators.peek() != '('  && 
					priority(operator) <= priority(operators.peek())) {	
					double op2 = numbers.pop();
					double op1 = (operators.peek() == '~') ? 0 : numbers.pop();
					numbers.push(operate(op1, op2, operators.pop()));
			}
			
			if(!numbers.empty() && !operators.empty() && operators.peek() == '(')
			{
				operators.pop();
				if(!operators.empty() && operators.peek() == '~')
				{
					// handle negatives in front of parentheses
					double op1 = numbers.pop();
					numbers.push(operate(0, op1, operators.pop()));
				}
			}
			
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
			case '~': 
				return -op2;
			default:
				return Double.NaN; // error
			}
	}
	
	
	private static boolean isOperator(char c) {
		return c == '+' ||
				c == '-' ||
				c == '*' ||
				c == '/' ||
				c == '^' ||
				c == '(' ||
				c == ')' ||
				c == '~'; // unary negation
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
			case '~':
				return 4;
			case '^':
				return 5;
			default: // error
				return -1;
		} // switch
	} // priority()
	
} // Parser
