/**
 * BooleanOperationNode Class
 * 
 * @author Matthew Reid
 */
public class BooleanOperationNode extends StatementNode{

	private Node firstExpression;
	private Node secondExpression;
	private Token operator;
	
	
	public BooleanOperationNode(Node firstExpression, Token operator, Node secondExpression) {
		this.firstExpression = firstExpression;
		this.secondExpression = secondExpression;
		this.operator = operator;
	}
	
	public Node getFirstExpression() {
		return firstExpression;
	}
	
	public Node getSecondExpression() {
		return secondExpression;
	}
	
	public Token getOperator() {
		return operator;
	}
	
	
	@Override
	public String toString() {
		return "BooleanOperationNode(" + firstExpression.toString() +  " " + operator + " " + secondExpression.toString() +")";
	}

}
