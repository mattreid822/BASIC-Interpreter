import java.util.ArrayList;

/**
 * PrintNode Class
 * 
 * @author Matthew Reid
 */
public class PrintNode extends StatementNode{

	private ArrayList<Node> printStatement;

	public PrintNode(ArrayList<Node> printStatement) {
		this.printStatement = printStatement;
	}


	public ArrayList<Node> getNodeList() {
		return printStatement;
	}

	@Override
	public String toString() {
		String result = "PRINT: ";
		for(Node n : printStatement) {
			if(n != printStatement.get(0))
				result += ", ";
			result += n.toString();
		}
		result += " END PRINT";
		return result;
	}
}


