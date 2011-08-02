package qlisp;

//import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Used to test functionality as I go.
 *
 * @author James McClain
 */
public class Test {
    public static void main(String[] args) {
	Scanner get = new Scanner(System.in);
	String lispCode;
	do {
	    System.out.print("Qlisp >>> ");
	    lispCode = get.nextLine();
      	    Lexer lx = new Lexer(lispCode);
	    // lx.next();
	    // for (String s : lx.args() )
	    // 	System.out.println(s);
	    // continue;
	    Commands cm = new Commands();
	    System.out.println(evalLisp(lx,cm));
	} while (! "q".equals(lispCode));
	
	
    }

    public static String evalLisp(Lexer lx,Commands cm) {
	for(String toEval = lx.next();toEval != null;toEval = lx.next()) {
	    String[] gotArgs = lx.args();
	    Command command = cm.getCommand(gotArgs[0]);
	    
	    if (command == null) {
		System.out.println(gotArgs[0]+" : Not a valid command");
		//		System.exit(1);
		return null;
	    }

	    String[] args = new String[gotArgs.length-1];
	    
	     for (int i = 0;i < gotArgs.length;i++) {
		 String arg = gotArgs[i];
		 if (i == 0) {
		     continue;
		 }

		 if ('(' == arg.charAt(0) ) {
		     arg = evalLisp(arg,lx,cm);
		 }
		 args[i-1] = arg;
	     }
	     if (args.length == 0) {
		 return command.get(null);
	     }
	     return command.get(args);
	}
	return null;
    }
    public static String evalLisp(String code,Lexer lx,Commands cm) {
	String[] gotArgs = lx.args(code);
	Command command = cm.getCommand(gotArgs[0]);
	if (command == null) {
		System.out.println(gotArgs[0]+" : Not a valid command");
		System.exit(1);
	    }

	String[] args = new String[gotArgs.length-1];
	
	for (int i = 0;i < gotArgs.length;i++) {
	    String arg = gotArgs[i];
	    if (i == 0) {
		continue;
	    }

	    if ('(' == arg.charAt(0) ) {
		arg = evalLisp(arg,lx,cm);
	    }
	    args[i-1] = arg;
	}
	if (args.length == 0) {
	    return command.get(null);
	}
	return command.get(args);
    }
    

}
