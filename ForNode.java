/**
 *  ForNode Class
 * 
 * @author Matthew Reid
 */

public class ForNode extends StatementNode{

	private VariableNode loopVariable;
	private int initialValue;
	private int exitConditionValue;
	private int increment = 1;

	public ForNode(VariableNode loopVariable, int initialValue, int exitConditionValue) {
		this.loopVariable = loopVariable;
		this.initialValue = initialValue;
		this.exitConditionValue = exitConditionValue;
	}

	public VariableNode LoopVariable() {
		return loopVariable;
	}
	
	public int initialValue() {
		return initialValue;
	}
	
	public int exitConditionValue() {
		return exitConditionValue;
	}
	
	public int increment() {
		return increment;
	}

	@Override
	public String toString() {
		return "ForNode(" + loopVariable.toString() + ", initial value of " + initialValue + ", goes to " + 
			exitConditionValue + ", increment of 1)";
	}
}



