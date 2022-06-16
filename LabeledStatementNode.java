/**
 * LabeledStatementNode Class
 * 
 * @author Matthew Reid
 */

public class LabeledStatementNode extends StatementNode{

	private StatementNode lableStatement;
	private String lable;

	public LabeledStatementNode(String lable, StatementNode lableStatement) {
		this.lableStatement = lableStatement;
		this.lable = lable;
	}

	public String getLable() {
		return lable;
	}
	
	public StatementNode getStatement() {
		return lableStatement;
	}

	@Override
	public String toString() {
		return "LabeledStatementNode(" + lableStatement.toString() + ")";
	}
}



