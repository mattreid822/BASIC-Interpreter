/**
 * IntegerNode Class
 * 
 * @author Matthew Reid
 */
public class IntegerNode extends Node{

	private int integer;
	
	public IntegerNode(int integer) {
		this.integer = integer;
	}
	
	public int getInteger() {
		return integer;
	}
	
	@Override
	public String toString() {
		return "IntegerNode(" + String.valueOf(integer) + ")";
	}

}
