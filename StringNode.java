/**
 * StringNode Class
 * 
 * @author Matthew Reid
 */
public class StringNode extends Node{

	private String stringValue;
	
	public StringNode(String stringValue) {
		this.stringValue = stringValue;
	}

	
	public String getString() {
		return stringValue;
	}
	
	@Override
	public String toString() {
		return "STRING(" + stringValue + ")";
	}

}
