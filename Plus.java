package qlisp;

public class Plus implements Command {

    public String get(String[] args) {
	double retval = 0;

	if (args == null || args.length < 2) {
	    System.out.println(this.getClass()+" requires at least "+
			       "2 arguments.");
	    System.exit(1);
	}
	for(String s: args) {
	    try {
		retval += Double.parseDouble(s);
	    }
	    catch (NumberFormatException e) {
		System.out.println(s + " is not a valid double value.");
		System.exit(1);
	    }
	}
	return String.valueOf(retval);
    }
}
