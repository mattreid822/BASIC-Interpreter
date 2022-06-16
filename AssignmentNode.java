/**
 * AssignmentNode Class
 * 
 * @author Matthew Reid
 */
public class AssignmentNode extends StatementNode{

	private VariableNode varNode;
	private Node valueNode;
	
	public AssignmentNode(VariableNode varNode, Node valueNode) {
		this.valueNode = valueNode;
		this.varNode = varNode;
	}
	
	public VariableNode getVarNode() {
		return varNode;
	}
	
	public Node getValueNode() {
		return valueNode;
	}
	
	@Override
	public String toString() {
		return "AssignmentNode(" + varNode.toString() + ", " + valueNode.toString() + ")";
	}

}
