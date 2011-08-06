package qlisp;

public class Multiply implements Command {

    public String get(String[] args) {
	double retval = 0;

	if (args == null || args.length < 2) {
	    System.out.println(this.getClass()+" requires at least " +
			       "2 arguments.");
	    System.exit(1);
	}
	for(int i = 0;i < args.length;i++) {
	    String s = args[i];
	    try {
		if (i == 0) {
		    retval = Double.parseDouble(s);
		    continue;
		}
		retval *= Double.parseDouble(s);
	    }
	    catch (NumberFormatException e) {
		System.out.println(s + " is not a valid double value.");
		System.exit(1);
	    }
	}
	return String.valueOf(retval);
    }
}

