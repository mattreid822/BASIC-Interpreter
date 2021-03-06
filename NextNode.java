/**
 *  NextNode Class
 * 
 * @author Matthew Reid
 */

public class NextNode extends StatementNode{

	private VariableNode variable;

	public NextNode(VariableNode variable) {
		this.variable = variable;
	}

	public VariableNode getVariable() {
		return variable;
	}

	@Override
	public String toString() {
		return "NextNode(" + variable.toString() + ")";
	}
}



