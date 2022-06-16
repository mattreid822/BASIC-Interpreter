import java.util.ArrayList;

/**
 * ReadNode Class
 * 
 * @author Matthew Reid
 */
public class ReadNode extends StatementNode{

	private ArrayList<VariableNode> varNodeList;
	
	public ReadNode(ArrayList<VariableNode> varNodeList) {
		this.varNodeList = varNodeList;
	}

	
	public ArrayList<VariableNode> getvarNodeList() {
		return varNodeList;
	}
	
	@Override
	public String toString() {
		String result = "READ: ";
		for(Node n : varNodeList) {
			result += n.toString() + " ";
		}
		result += " END READ";
		return result;
	}

}
