
/**
 * MathOpNode Class
 * 
 * @author Matthew Reid
 */
public class MathOpNode extends Node{

	public enum MathOpType { 
		ADD, SUBTRACT, MULTIPLY, DIVIDE
	}
	
	private MathOpType mathOp;
	private Node leftNode;
	private Node rightNode;


	public MathOpNode(MathOpType type, Node left, Node right) {
		mathOp = type;
		leftNode = left;
		rightNode = right;
	}

	public MathOpType getMathOpType() {
		return mathOp;
	}
	
	public Node getLeft() {
		return leftNode;
	}

	public Node getRight() {
		return rightNode;
	}
	
	@Override
	public String toString() {
		return "(MathOpNode(" + mathOp + " " + leftNode + ", " + rightNode + ")";
	}

}