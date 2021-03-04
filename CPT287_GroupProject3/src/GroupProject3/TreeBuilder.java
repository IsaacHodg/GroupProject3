package GroupProject3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class TreeBuilder {

	//See below
	private static Pattern digits = Pattern.compile("\\d+");
	
	//used later to check if a string is also a number
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return digits.matcher(strNum).matches();
	}
	
	//method and hashmap to get order of precedence
	public static Map<String, Integer> getOperatorMap() {
		Map<String, Integer> operators = new HashMap<>();
		operators.put("^", 7);
		operators.put("*", 6);
		operators.put("/", 6);
		operators.put("%", 6);
		operators.put("+", 5);
		operators.put("-", 5);
		operators.put(">", 4);
		operators.put(">=", 4);
		operators.put("<", 4);
		operators.put("<=", 4);
		operators.put("==", 3);
		operators.put("!=", 3);
		operators.put("&&", 2);
		operators.put("||", 1);
		return operators;
	}
	
	public static BTNode<String> infixToTree(String infixE) {
		/*Tweaked code from last project
		Uses Operator Map to convert infix to postfix
		*/
		Map<String, Integer> precedence = getOperatorMap();
		Stack<String> stack = new Stack<>();
		StringBuilder postfixExp = new StringBuilder();
		char[] tokens = infixE.toCharArray();
		for (int i = 0; i < tokens.length; i++) { 
            if (tokens[i] == ' ')
                continue;
            else if (Character.isDigit(tokens[i]))  {
            	StringBuffer buf = new StringBuffer();
                
                // When there are more than one digits in number
                while (i < tokens.length && Character.isDigit(tokens[i]))
                	buf.append(tokens[i++]);

				postfixExp.append(Integer.parseInt(buf.toString())).append(' ');
				i--;
			}
            // Push opening brace operator's stack
            else if (tokens[i] == '(') { stack.push(String.valueOf(tokens[i])); }
            // Close brace
            else if (tokens[i] == ')') {
				while (!stack.peek().equals("(")) {
					postfixExp.append(stack.pop()).append(' ');
				}
				stack.pop();
            }
            // Current token is an operator.
            else {
            	StringBuffer buf = new StringBuffer();
            	buf.append(tokens[i]);
            	if (String.valueOf(tokens[i+1]).equals("=") ||
            		String.valueOf(tokens[i+1]).equals("&") ||
            		String.valueOf(tokens[i+1]).equals("|")) { buf.append(tokens[++i]); }
            	
				while (!stack.isEmpty() && !stack.peek().equals("(") &&
					precedence.get(buf.toString()) <= precedence.get(stack.peek())) {
					postfixExp.append(stack.pop()).append(' ');
				}
				stack.push(String.valueOf(buf));
			}
        }
		while(!stack.isEmpty()) {postfixExp.append(stack.pop()).append(' ');}

		//inspired by Wikipedia page "Binary Expression Tree"
		String[] ops = postfixExp.toString().split("\\s+");
		Stack<BTNode<String>> nodeStack = new Stack<>();
		for (String token : ops) {
			if (isNumeric(token)) {
				BTNode<String> newNode = new BTNode<String>(token);
				nodeStack.push(newNode);
			} else {
				BTNode<String> T1 = nodeStack.pop();
				BTNode<String> T2 = nodeStack.pop();
				BTNode<String> T0 = new BTNode<String>(token, T2, T1);
				nodeStack.push(T0);
			}
		}
		return nodeStack.pop();
	}
	
	
}
