/**
 * Token Class
 * 
 * @author Matthew Reid
 */
public class Token {

	public enum TokenType { 
		PLUS, MINUS, TIMES, DIVIDE, EQUALS, NUMBER, GREATERTHAN, GREATERTHANOREQUALTO, LESSTHAN, LESSTHANOREQUALTO, NOTEQUALS, LPAREN, RPAREN, STRING, WORD, EndOfLine, PRINT, IDENTIFIER,
		LABEL, COMMA, READ, DATA, INPUT, GOSUB, FOR, NEXT, RETURN, TO, IF, THEN, RANDOM, LEFT$, RIGHT$, MID$, NUM$, VAL, VALPERCENT
	}

	private TokenType token;
	private String value;


	public Token(TokenType number, String value) {
		this.token = number;
		this.value = value;
	}

	public String getValue() {
		return value;
	}	

	public TokenType getTokenType() {
		return token;
	}

	@Override
	public String toString() {
		if(token.toString().equals("NUMBER"))
			return "NUMBER(" + value + ")";
		
		return token.toString();
	}
}



