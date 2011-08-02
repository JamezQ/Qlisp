package qlisp;

import java.util.HashMap;

/**
 * Currently holds commands and macros.
 *
 * @author James McClain
 *
 */
public class Commands {
    /** A hashmap dictionary of the commands */
    private HashMap<String,Command> commandList = new HashMap<String,Command>();
    /** A reference to an evaluator object */
    private Eval eval;
    
    /**
     * This class should not be initiated with a default constuctor.
     * Except for testing purposes.
     */
    public Commands() {
	commandList.put("+",new Plus());
	commandList.put("-",new Minus());
	commandList.put("print",new Print());
    }

    /**
     * Get a reference to the commands with a Eval object for them to use.
     *
     * @param eval A reference to an Qlisp evaluator.
     */
    public Commands(Eval eval) {
	this.eval = eval;
	commandList.put("+",new Plus());
	commandList.put("-",new Minus());
	commandList.put("print",new Print());
    }
    /**
     * Get a reference to a command.
     * With it you can you the .get method that all commands
     * must have with the argument get(String[] args). With args being
     * all the arguments to the command besides the command itself.
     *
     * @param key The name of the command in plaintext.
     */
    public Command getCommand(String key) {
	return commandList.get(key);
    }
	
    
}
