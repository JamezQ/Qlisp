package qlisp;

import java.util.Scanner;

/**
 * This file starts the interpreter for my test language.
 *
 * @author James McClain
 */

public class Start {

    /**
     * Explicit default constructor. Does nothing.
     */
    public Start() {
    }

    public static void main(String[] args) {
	Scanner get = new Scanner(System.in);
	while (true) {
	    System.out.print("> ");
	    String line = get.nextLine();
	    if (line.equals("q")) {
		break;
	    }
	    Lexer lex = new Lexer(line);
	    for(String word = lex.next();
		word != null;
		word = lex.next()) {
		
		System.out.println(word);
	    }

	}
    }
}
