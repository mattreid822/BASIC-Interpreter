/**
 *  GosubNode Class
 * 
 * @author Matthew Reid
 */

public class GosubNode extends StatementNode{

	private VariableNode variable;

	public GosubNode(VariableNode variable) {
		this.variable = variable;
	}

	public VariableNode getVariable() {
		return variable;
	}

	@Override
	public String toString() {
		return "GosubNode(" + variable.toString() + ")";
	}
}



