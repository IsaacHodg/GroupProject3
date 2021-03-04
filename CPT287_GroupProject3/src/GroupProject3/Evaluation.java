package GroupProject3;

public class Evaluation {
	// Calculations for each operator
	public static int calculate(String op, int a, int b)
    {
        switch (op)
        {
        case "<":
            if (a < b) { return 1; }
            else { return 0; }
		case ">":
			if (a > b) { return 1; }
            else { return 0; }
        case "<=":
        	if (a <= b) { return 1; }
            else { return 0; }
        case ">=":
        	if (a >= b) { return 1; }
            else { return 0; }
        case "%":
        	return (a % b);
        case "^":
        	return (int)Math.pow(a, b);
        case "+":
            return a + b;
        case "-":
            return a - b;
        case "*":
            return a * b;
        case "/":
        	//Exception for dividing by 0
            if (b == 0) {
                throw new 
                UnsupportedOperationException("Cannot divide by zero"); 
            }
            return a / b;
        case "==":
        	if (a == b) { return 1; }
            else { return 0; }
        case "!=":
        	if (a != b) { return 1; }
            else { return 0; }
		case "&&":
			if (a != 0 && b != 0) { return 1; }
            else { return 0; }
        case "||":
        	if (a != 0 || b != 0) { return 1; }
            else { return 0; }
        default:
        	return 0;
        }
    }
	
	//Evaluates tree recursively
	public static int evaluateTree(BTNode<String> tree) {
		int leftOp = 0, rightOp = 0;
		//If left tree has children, tries to evaluate that tree assuming the root is an operator
		if (tree.left.left != null){
			leftOp = evaluateTree(tree.left);
		}
		//If left tree doesn't have children, takes the value of the tree as is
		else {
			leftOp = Integer.valueOf(tree.left.data);
		}
		//If right tree has children, tries to evaluate that tree assuming the root is an operator
		if (tree.right.right != null){
			rightOp = evaluateTree(tree.right);
		}
		//If right tree doesn't have children, takes the value of the tree as is
		else {
			rightOp = Integer.valueOf(tree.right.data);
		}
		//Calculates the tree
		return calculate(tree.data, leftOp, rightOp);
	}
}
