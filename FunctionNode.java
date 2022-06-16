import java.util.ArrayList;

/**
 * FunctionNode Class
 * 
 * @author Matthew Reid
 */
public class FunctionNode extends StatementNode{

	private Token.TokenType functionName;
	private ArrayList<Node> parameterList;
	
	public FunctionNode(Token.TokenType functionName, ArrayList<Node> parameterList) {
		this.functionName = functionName;
		this.parameterList = parameterList;
	}
	
	public String getFunctionName() {
		return functionName.toString();
	}
	
	public ArrayList<Node> getParameterList() {
		return parameterList;
	}
	
	@Override
	public String toString() {
		String result = "FunctionNode: " + functionName.toString() + "(";
		for(Node n : parameterList) {
			if(n != parameterList.get(0))
				result += ", ";
			result += n.toString();
		}
		result += ")";
		return result;
	}

}
