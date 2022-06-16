import java.util.ArrayList;

/**
 * StatementsNode Class
 * 
 * @author Matthew Reid
 */
public class StatementsNode extends StatementNode{

	private ArrayList<StatementNode> statementsNodeList;

	public StatementsNode(ArrayList<StatementNode> statementsNodeList) {
		this.statementsNodeList = statementsNodeList;
	}

	public ArrayList<StatementNode> getStatementsNodeList() {
		return statementsNodeList;
	}

	@Override
	public String toString() {
		if(statementsNodeList.size() == 0)
			return "";
		String result = "StatementsNode(";
		for(StatementNode s : statementsNodeList) {
			result +=  s.toString();
		}
		return result + ")";
	}

}
