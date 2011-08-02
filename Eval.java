package qlisp;

import java.util.ArrayList;

/**
 * Evaluates lisp commands.
 *
 * @author James McClain
 *
 */
public class Eval {
    /** Reference to a lexer */
    private Lexer lx;
    /**
     * Explicit default constuctor.
     */
    public Eval() {
	
    }
    /**
     * Take a lexer as a argument.
     *
     * @param lx A lexer object.
     */
    public Eval(Lexer lx) {
	this.lx = lx;
    }
    /**
     * This constructor gets a Lexer by creating a new lexer object
     * with a string as the constuctor argument.
     *
     * @param tolex A string that will be sent to the Lexer constuctor.
     * To get a Lexer reference.
     */
    public Eval(String tolex) {
	lx = new Lexer(tolex);
    }
    /**
     * Get the lexer object.
     *
     * @return A reference to the lexer.
     */
    public Lexer getLexer() {
	return lx;
    }
    /**
     * Set the lexer object.
     *
     * @param lx A lexer.
     */
    public void setLexer(Lexer lx) {
	this.lx = lx;
    }
    /**
     * Evaluates the code based on what is inside the Lexer.
     * This is currently unfinished.
     */
    public void evalLisp() {
	Commands gc = new Commands(this);
	for(String toEval = lx.next();toEval != null;toEval = lx.next()) {
	    String command = getCommand(toEval);
	    if (command == null) {
		continue;
	    }
	    Command toExec = gc.getCommand(command);
	    if (toExec == null) {
		System.out.println("No such command: "+command);
		System.exit(1);
	    }
	    

	}
    }
    /**
     * Get a command of a lisp string in the form (command arg1 arg2 ...).
     *
     * @param tilSpace A string representing a lisp command.
     *
     * @return The command, for example "+" in (+ 2 2)
     */
    public String getCommand(String tilSpace) {
	return (lx.args(tilSpace))[0];
    }

}

    

