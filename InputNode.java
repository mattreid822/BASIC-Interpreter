import java.util.ArrayList;

/**
 * InputNode Class
 * 
 * @author Matthew Reid
 */
public class InputNode extends StatementNode{

	private ArrayList<Node> nodeList;
	
	public InputNode(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}
	
	@Override
	public String toString() {
		String result = "INPUT: ";
		for(Node n : nodeList) {
			result += n.toString() + " ";
		}
		result += " END INPUT";
		return result;
	}
}
