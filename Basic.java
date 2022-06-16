import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Basic Class
 * reads a file, calls the lex and parse methods and prints results
 * @author Matthew Reid
 */
public class Basic {

	public static void main(String[] args) throws Exception {

		try {
			//Check if there is 1 argument. If not throw an exception.
			if(args.length != 1)
				throw new Exception("None or more than 1 arguments.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

		//Convert string argument from main method to a path that can be used with the readAllLines method
		Path filePath = Paths.get(args[0]);
		List<String> input = Files.readAllLines(filePath);
		Lexer Lexer = new Lexer();
		ArrayList<Token> lexerTokens;
		Parser parser;

		//loop through lines read in from input
		for(int i = 0; i < input.size(); i++) {
			try {
				lexerTokens = Lexer.lex(input.get(i));
				parser = new Parser(lexerTokens);
				System.out.print(parser.parse().toString() + '\n');
			}
			//loop through each token to output
			//for(int j = 0; j < lexerTokens.size(); j++)
			//	System.out.print(lexerTokens.get(j).toString() + " ");
			//System.out.print('\n');
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

}


