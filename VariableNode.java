/**
 * VariableNode Class
 * 
 * @author Matthew Reid
 */
public class VariableNode extends Node{

	private String variableName;
	
	public VariableNode(String varName) {
		this.variableName = varName;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	@Override
	public String toString() {
		return "Variable(" + variableName + ")";
	}

}
