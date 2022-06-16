/**
 * IfNode Class
 * 
 * @author Matthew Reid
 */
public class IfNode extends StatementNode{

	private BooleanOperationNode booleanExpression;
	private String thenStatement;
	
	public IfNode(BooleanOperationNode booleanExpression, String thenStatement) {
		this.booleanExpression = booleanExpression;
		this.thenStatement = thenStatement;
	}
	
	public BooleanOperationNode getBooleanExpression() {
		return booleanExpression;
	}
	
	public String getThenStatement() {
		return thenStatement;
	}
	
	@Override
	public String toString() {
		return "IfNode Condition(" + booleanExpression.toString() + ") THEN -> " + thenStatement + " END of IfNode";
	}

}
