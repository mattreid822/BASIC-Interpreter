import java.util.ArrayList;

/**
 * Parser Class
 * 
 * @author Matthew Reid
 */
public class Parser {

	private ArrayList<Token> tokens;

	//used to make sure RETURN statements are the only one on the line by checking if it the first node
	int initialTokenListSize;

	public Parser(ArrayList<Token> tokenArray) {
		this.tokens = tokenArray;
		initialTokenListSize = tokens.size();
	}

	public Node parse() throws Exception {
		return statements();
	}

	private Node statements() throws Exception {
		ArrayList<StatementNode> statementsNodeList = new ArrayList<StatementNode>();
		Node temp;
		while((temp = statement()) != null)
			statementsNodeList.add((StatementNode) temp);
		return new StatementsNode(statementsNodeList);
	}


	private Node statement() throws Exception {
		if(tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER)) {
			return assignment();
		}
		else if(matchAndRemove(Token.TokenType.PRINT) != null) {	
			return printStatement(new ArrayList<Node>());
		}
		else if(matchAndRemove(Token.TokenType.DATA) != null) {	
			return dataStatement(new ArrayList<Node>());
		}
		else if(matchAndRemove(Token.TokenType.READ) != null) {	
			return readStatement(new ArrayList<VariableNode>());
		}
		else if(matchAndRemove(Token.TokenType.INPUT) != null) {	
			return inputStatement(new ArrayList<Node>());
		}
		else if(matchAndRemove(Token.TokenType.GOSUB) != null) {	
			return gosubStatement();
		}
		else if(matchAndRemove(Token.TokenType.FOR) != null) {	
			return forStatement();
		}
		else if(matchAndRemove(Token.TokenType.NEXT) != null) {	
			return nextStatement();
		}
		else if(matchAndRemove(Token.TokenType.IF) != null) {	
			return ifStatement();	
		}
		else if(tokens.get(0).getTokenType().equals(Token.TokenType.LABEL)) {	
			return labeledStatement(matchAndRemove(Token.TokenType.LABEL).getValue());
		}
		else if(matchAndRemove(Token.TokenType.RETURN) != null) {	
			if(!(tokens.size() == 1) || initialTokenListSize > 2)
				throw new Exception("RETURN statement: Cannot have other statements on the same line");
			return new ReturnNode();
		}
		return null;
	}

	private Node ifStatement() throws Exception {
		BooleanOperationNode booleanExpression = null;
		
		booleanExpression = booleanOperationStatement();
		if(booleanExpression == null)
			throw new Exception("IF statement:Invalid Boolean Statement");
		if(matchAndRemove(Token.TokenType.THEN) != null) {
			if(tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER)) {
				return new IfNode(booleanExpression, matchAndRemove(Token.TokenType.IDENTIFIER).getValue());
			}
		}
		throw new Exception("IF statement:Invalid THEN statement");
	}

	private BooleanOperationNode booleanOperationStatement() throws Exception {
		Node firstExpression = expression();
		Node secondExpression;
		if(firstExpression == null)
			throw new Exception("BOOLEAN statement: Invalid first expression");
		Token.TokenType operator = tokens.get(0).getTokenType();
		switch(operator){
		case GREATERTHAN:
			break;
		case GREATERTHANOREQUALTO:
			break;
		case LESSTHAN:
			break;
		case LESSTHANOREQUALTO:
			break;
		case NOTEQUALS:
			break;
		case EQUALS:
			break;
		default: 
			return null;
		}
		Token returnOperator = matchAndRemove(operator);
		secondExpression = expression();
		if(secondExpression == null)
			throw new Exception("BOOLEAN statement: Invalid second expression");
		return new BooleanOperationNode(firstExpression, returnOperator, secondExpression);
	}

	private Node nextStatement() throws Exception {
		if(!tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER))
			throw new Exception("NEXT statement: Identifier not found");
		VariableNode variableNode = new VariableNode(matchAndRemove(Token.TokenType.IDENTIFIER).getValue());

		return new NextNode(variableNode);
	}

	private Node forStatement() throws Exception {
		int initialValue;
		int exitConditionValue;
		if(!tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER))
			throw new Exception("FOR statement is incorrectly formatted");
		VariableNode loopVariable = new VariableNode(matchAndRemove(Token.TokenType.IDENTIFIER).getValue());
		if(matchAndRemove(Token.TokenType.EQUALS) != null) {
			initialValue = Integer.parseInt(matchAndRemove(Token.TokenType.NUMBER).getValue());
			if(matchAndRemove(Token.TokenType.TO) != null) {
				exitConditionValue = Integer.parseInt(matchAndRemove(Token.TokenType.NUMBER).getValue());
				return new ForNode(loopVariable, initialValue, exitConditionValue);
			}
		}
		throw new Exception("FOR statement is incorrectly formatted");
	}

	private Node gosubStatement() throws Exception {
		if(!tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER))
			throw new Exception("GOSUB statement: Identifier not found");
		VariableNode variable = new VariableNode(matchAndRemove(Token.TokenType.IDENTIFIER).getValue());
		return new GosubNode(variable);
	}


	private Node labeledStatement(String lableValue) throws Exception {
		StatementNode statementNode = (StatementNode) statement();
		if(statementNode == null)
			throw new Exception("Label Statement: Invalid statement following");
		return new LabeledStatementNode(lableValue, statementNode);
	}

	public Node printStatement(ArrayList<Node> printList) throws Exception {
		return new PrintNode(printList(printList));
	}

	public ArrayList<Node> printList(ArrayList<Node> printList) throws Exception {
		Node temp;
		temp = expression();
		while(matchAndRemove(Token.TokenType.COMMA) != null) {
			printList.add(temp);
			temp = expression();
		}
		printList.add(temp);
		return printList;

	}

	public Node assignment() throws Exception {
		Node assignment = new VariableNode(matchAndRemove(Token.TokenType.IDENTIFIER).getValue());
		if(matchAndRemove(Token.TokenType.EQUALS) != null) {
			return new AssignmentNode((VariableNode) assignment, expression());
		}

		return null;

	}

	public Node dataStatement(ArrayList<Node> data) throws Exception {
		Node temp;
		while(tokens.get(0).getTokenType().equals(Token.TokenType.NUMBER) 
				|| tokens.get(0).getTokenType().equals(Token.TokenType.STRING)) {
			temp = expression();
			data.add(temp);
			if(matchAndRemove(Token.TokenType.COMMA) == null)
				return new DataNode(data);
		}
		return new DataNode(data);
	}

	public Node readStatement(ArrayList<VariableNode> variables) throws Exception {
		Node temp;
		while(tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER)) {
			temp = expression();
			variables.add((VariableNode) temp);
			if(matchAndRemove(Token.TokenType.COMMA) == null)
				return new ReadNode(variables);
		}
		return new ReadNode(variables);

	}


	public Node inputStatement(ArrayList<Node> inputList) throws Exception {
		Node temp;
		if(tokens.get(0).getTokenType().equals(Token.TokenType.STRING)) {
			temp = expression();
			inputList.add(temp);
		}
		if(matchAndRemove(Token.TokenType.COMMA) == null)
			while(tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER)) {
				temp = expression();
				inputList.add(temp);
				if(matchAndRemove(Token.TokenType.COMMA) == null)
					return new InputNode(inputList);
			}
		return new InputNode(inputList);

	}


	//looks at the token list. If the first token in the list matches that type, it returns it, otherwise it returns null
	private Token matchAndRemove(Token.TokenType tokenType){
		if(!tokens.get(0).getTokenType().equals(tokenType)) 
			return null;
		else {
			Token returnToken = tokens.get(0);
			//System.out.println(returnToken.getValue());
			tokens.remove(0);
			return returnToken;
		}
	}

	public Node factor() throws Exception{
		Token num = matchAndRemove(Token.TokenType.NUMBER);

		if(num != null) {
			if(num.getValue().contains("."))
				return new FloatNode(Float.parseFloat(num.getValue()));
			else
				return new IntegerNode(Integer.parseInt(num.getValue()));
		}

		if(matchAndRemove(Token.TokenType.LPAREN) != null) {
			Node expr = expression();
			if(expr == null)
				throw new Exception("Found left paren with no expression");
			if(matchAndRemove(Token.TokenType.RPAREN) != null)
				return expr;
			throw new Exception("Found left paren and expression but no right paren");
		}

		if(tokens.get(0).getTokenType().equals(Token.TokenType.IDENTIFIER)) {
			return new VariableNode(matchAndRemove(Token.TokenType.IDENTIFIER).getValue());
		}
		else if(tokens.get(0).getTokenType().equals(Token.TokenType.STRING)) {
			return new StringNode(matchAndRemove(Token.TokenType.STRING).getValue());
		}

		return null;
	}


	public Node expression() throws Exception {

		//System.out.println(tokens.get(0).getTokenType());
		Node function = checkFunctionNode();
		if(function != null) {
			return function;
		}
		
		Node soFar = term();

		if(soFar == null)
			return null;

		return GetRightOfExpression(soFar);
	}

	private Node checkFunctionNode() throws Exception {
		Token.TokenType temp = tokens.get(0).getTokenType();
		switch(temp){
		case RANDOM:
			break;
		case LEFT$:
			break;
		case RIGHT$:
			break;
		case MID$:
			break;
		case NUM$:
			break;
		case VAL:
			break;
		case VALPERCENT:
			break;
		default: 
			return null;
		}
		matchAndRemove(temp);
		return function(temp);
	}

	private Node function(Token.TokenType functionName) throws Exception {
		ArrayList<Node> paramList = new ArrayList<Node>();
		if(matchAndRemove(Token.TokenType.LPAREN) != null) {
			Node temp;
			temp = expression();
			while(matchAndRemove(Token.TokenType.COMMA) != null) {
				paramList.add(temp);
				temp = expression();
			}
			if(temp != null)
				paramList.add(temp);
			if(matchAndRemove(Token.TokenType.RPAREN) != null) {
				return new FunctionNode(functionName, paramList);
			}
		}
		throw new Exception("FUNCTION: Incorrectly formatted");
	}

	public Node GetRightOfExpression(Node left) throws Exception {

		Token operation;
		if(tokens.get(0).getTokenType().equals(Token.TokenType.PLUS)) {
			operation = matchAndRemove(Token.TokenType.PLUS);
		}
		else
			operation = matchAndRemove(Token.TokenType.MINUS);
		if(operation == null)
			return left;
		Node secondTerm = term();
		if(secondTerm == null)
			throw new Exception("Found a + or -, but no term on the right hand side");

		//if the type of operation is plus make the new mathOpNode as add and if not make it subtract
		MathOpNode op = new MathOpNode(operation.getTokenType() == Token.TokenType.PLUS ? MathOpNode.MathOpType.ADD : MathOpNode.MathOpType.SUBTRACT,
				left, secondTerm);
		return GetRightOfExpression(op);

	}

	public Node term() throws Exception{
		Node soFar = factor();
		return soFar == null ? null : GetRightOfTerm(soFar);
	}

	public Node GetRightOfTerm(Node left) throws Exception {

		Token operation;
		if(tokens.get(0).getTokenType().equals(Token.TokenType.TIMES))
			operation = matchAndRemove(Token.TokenType.TIMES);
		else
			operation = matchAndRemove(Token.TokenType.DIVIDE);
		if(operation == null)
			return left;

		Node secondFactor = factor();
		if (secondFactor == null)
			throw new Exception("Found a * or /, but no term on the right hand side");

		//if the type of operation is times make the new mathOpNode as multiply and if not make it divide
		MathOpNode op = new MathOpNode(operation.getTokenType() == Token.TokenType.TIMES ? MathOpNode.MathOpType.MULTIPLY : MathOpNode.MathOpType.DIVIDE,
				left, secondFactor);
		return GetRightOfTerm(op);

	}

}
