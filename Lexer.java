import java.util.ArrayList;
import java.util.HashMap;

/**
 * Lexer Class
 * The Lexer class contains a lex method that accepts a single string and returns a collection (array or list) of Tokens. 
 * The lex method uses one or more state machines to iterate over the input string and create appropriate Tokens. 
 * @author Matthew Reid
 */
public class Lexer {

	private static HashMap<String, Token.TokenType> knownWords = new HashMap<String, Token.TokenType>();
	static{
		knownWords.put("PRINT", Token.TokenType.PRINT);
		knownWords.put("DATA", Token.TokenType.DATA);
		knownWords.put("INPUT", Token.TokenType.INPUT);
		knownWords.put("READ", Token.TokenType.READ);
		knownWords.put("GOSUB", Token.TokenType.GOSUB);
		knownWords.put("FOR", Token.TokenType.FOR);
		knownWords.put("NEXT", Token.TokenType.NEXT);
		knownWords.put("RETURN", Token.TokenType.RETURN);
		knownWords.put("TO", Token.TokenType.TO);
		knownWords.put("IF", Token.TokenType.IF);
		knownWords.put("THEN", Token.TokenType.THEN);
		knownWords.put("RANDOM", Token.TokenType.RANDOM);
		knownWords.put("LEFT$", Token.TokenType.LEFT$);
		knownWords.put("RIGHT$", Token.TokenType.RIGHT$);
		knownWords.put("MID$", Token.TokenType.MID$);
		knownWords.put("NUM$", Token.TokenType.NUM$);
		knownWords.put("VAL", Token.TokenType.VAL);
		knownWords.put("VAL%", Token.TokenType.VALPERCENT);
	}

	/**
	 * getWordTokenType method
	 * uses a token as a parameter and uses the value to search the knownWords hashmap
	 * returns the token type that corresponds with the string value from the token parameter "searchtoken"
	 * @param a token object
	 * @return a token type
	 */
	private Token.TokenType getWordTokenType(Token searchToken) {
		if(knownWords.containsKey(searchToken.getValue()))
			return knownWords.get(searchToken.getValue());
		else
			return Token.TokenType.IDENTIFIER;
	}

	/**
	 * Lexer method
	 * accepts a single string and returns a collection (array or list) of Tokens and
	 * uses one or more state machines to iterate over the input string and create appropriate Tokens. 
	 * @param a string that is to be parsed through the lexer
	 * @return a token arraylist
	 * @throws an Exception when a character other than a number or other appropriate character
	 */
	public ArrayList<Token> lex (String inputString) throws Exception {
		String state = "getting new token";
		char currentChar = 0;
		Token tempToken = null;
		ArrayList<Token> result = new ArrayList<Token>(inputString.length() + 1); // + 1 is for the end of line token
		
		for(int i = 0; i < inputString.length(); i++) {
			currentChar = inputString.charAt(i);

			//Added state "getting decimal number or negative" that makes it so that 1 number cannot have 2 decimal points or negative signs
			if(state == "getting decimal number or negative") {
				if (currentChar >= '0' && currentChar <= '9') {
					//after checking the number continues, create a new temp token and concatenate the value of the last one
					tempToken = new Token(Token.TokenType.NUMBER, tempToken.getValue() + Character.toString(currentChar));
				}
				//added to allow spaces and tabs in between numbers
				else if(currentChar == ' ' || currentChar == '	') {
				}
				else { 
					result.add(tempToken);
					state = "getting new token";
				}
			}

			if(state == "getting number") {
				if (currentChar >= '0' && currentChar <= '9' || currentChar == '.') {
					if(currentChar == '.')
						state = "getting decimal number or negative";
					//after checking the number continues, create a new temp token and concatenate the value of the last one
					tempToken = new Token(Token.TokenType.NUMBER, tempToken.getValue() + Character.toString(currentChar));
				}
				//added to allow spaces and tabs in between numbers
				else if(currentChar == ' ' || currentChar == '	') {
				}
				else { 
					//since we did not add the number last loop we know there is an empty space in the array, this prevents overlapping
					result.add(tempToken);
					state = "getting new token";
				}
			}

			if(state == "getting word") {
				if(currentChar == '$' || currentChar == '%') {
					tempToken = new Token(Token.TokenType.WORD, tempToken.getValue() + Character.toString(currentChar));
					result.add(new Token(getWordTokenType(tempToken), tempToken.getValue()));
					state = "getting new token";
					//reiterates loop to prevent invalid character exception from $ or %
					continue;
				}
				else if(currentChar == ':') {
					result.add(new Token(Token.TokenType.LABEL, tempToken.getValue() + Character.toString(currentChar)));
					state = "getting new token";
					//reiterates loop to prevent invalid character exception from $ or %
					continue;
				}
				else if (!Character.isLetter(currentChar)) {
					tempToken = new Token(getWordTokenType(tempToken), tempToken.getValue());
					result.add(tempToken);
					state = "getting new token";
				}
				else{ 
					//add a character to the tempToken by creating a new token with the same value plus the new character
					tempToken = new Token(Token.TokenType.WORD, tempToken.getValue() + Character.toString(currentChar));
				}

			}

			if(state == "getting string") {
				if (currentChar == '"' ) {
					result.add(tempToken);
					state = "getting new token";
					//if this iteration of the loop keeps going it will start a new string with the closing quote, continue goes to the next iteration of the loop
					continue;
				}
				else { 
					//add a character to the tempToken by creating a new token with the same value plus the new character
					tempToken = new Token(Token.TokenType.STRING, tempToken.getValue() + Character.toString(currentChar));
				}
			}

			if(state == "getting lessthan or notequals") {
				if (currentChar == '=') {
					result.add(new Token(Token.TokenType.LESSTHANOREQUALTO, null));
					state = "getting new token";
					//if this iteration of the loop keeps going it will pick up the equals sign, continue goes to the next iteration of the loop
					continue;
				}
				else if (currentChar == '>') {
					result.add(new Token(Token.TokenType.NOTEQUALS, null));
					state = "getting new token";
					continue;
				}	
				else { 
					result.add(new Token(Token.TokenType.LESSTHAN, null));
					state = "getting new token";
				}
			}

			if(state == "getting greaterthan") {
				if (currentChar == '=') {
					result.add(new Token(Token.TokenType.GREATERTHANOREQUALTO, null));
					state = "getting new token";
					//if this iteration of the loop keeps going it will pick up the equals sign, continue goes to the next iteration of the loop
					continue;
				}
				else { 
					result.add(new Token(Token.TokenType.GREATERTHAN, null));
					state = "getting new token";
				}
			}

			if(state == "getting new token") {
				if(currentChar == ' ' || currentChar == '	') {
				}
				else if(currentChar == '+') {
					result.add(new Token(Token.TokenType.PLUS, null));
				}
				else if(currentChar == '-') { //in the pdf there were 2 different minus signs, not sure if I was supposed to differentiate
					result.add(new Token(Token.TokenType.MINUS, null));
				}
				else if(currentChar == '*') {
					result.add(new Token(Token.TokenType.TIMES, null));
				}
				else if(currentChar == '/') {
					result.add(new Token(Token.TokenType.DIVIDE, null));
				}
				else if(currentChar == '=') {
					result.add(new Token(Token.TokenType.EQUALS, null));
				}
				else if(currentChar == '(') {
					result.add(new Token(Token.TokenType.LPAREN, null));
				}
				else if(currentChar == ')') {
					result.add(new Token(Token.TokenType.RPAREN, null));
				}
				else if(currentChar == ',') {
					result.add(new Token(Token.TokenType.COMMA, null));
				}
				else if (currentChar == '<' || currentChar == '>') {
					if(currentChar == '<')
						state = "getting lessthan or notequals";
					else 
						state = "getting greaterthan";
				}
				else if (currentChar >= '0' && currentChar <= '9' || currentChar == '.' || currentChar == '-') {
					tempToken = new Token(Token.TokenType.NUMBER, Character.toString(currentChar));
					if(currentChar == '.')
						state = "getting decimal number or negative";
					else 
						state = "getting number";
				}
				else if (Character.isLetter(currentChar)) {
					tempToken = new Token(Token.TokenType.WORD, Character.toString(currentChar));
					state = "getting word";

				}
				else if (currentChar == '"') {
					tempToken = new Token(Token.TokenType.STRING, "");
					state = "getting string";

				}
				else
					throw new Exception("Invalid Character.");

			}

		}
		//if the loop ends while in a state other than getting new token, this code block will emit the token that was being processed based on which state it was in
		if(state == "getting number" || state == "getting decimal number or negative" || state == "getting string")
			result.add(tempToken);
		else if(state == "getting word")
			result.add(new Token(getWordTokenType(tempToken), tempToken.getValue()));
		else if(state == "getting lessthan or notequals")
			result.add(new Token(Token.TokenType.LESSTHAN, null));
		else if(state == "getting greaterthan")
			result.add(new Token(Token.TokenType.GREATERTHAN, null));

		//EndOfLine token
		result.add(new Token(Token.TokenType.EndOfLine, Character.toString(currentChar)));
		return result;
	}

}