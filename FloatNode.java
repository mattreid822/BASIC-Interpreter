/**
 * FloatNode Class
 * 
 * @author Matthew Reid
 */
public class FloatNode extends Node{

	private float floatVar;
	
	public FloatNode(float newFloat) {
		floatVar = newFloat;
	}
	
	public float getFloat() {
		return floatVar;
	}
	
	@Override
	public String toString() {
		return "FloatNode(" + String.valueOf(floatVar) + ")";
	}

}
