import java.util.ArrayList;

/**
 * DataNode Class
 * 
 * @author Matthew Reid
 */
public class DataNode extends StatementNode{

	ArrayList<Node> dataList;
	
	public DataNode(ArrayList<Node> dataList) {
		this.dataList = dataList;
	}

	
	public ArrayList<Node> getDataList() {
		return dataList;
	}
	
	@Override
	public String toString() {
		String result = "DATA: ";
		for(Node n : dataList) {
			result += n.toString() + " ";
		}
		result += " END DATA";
		return result;
	}

}
