package qlisp;

public class Print implements Command {

    public String get(String[] args) {
	if (args == null) {
	    System.out.println();
	}
	else {
	    for(String s: args) {
		System.out.println(s);
	    }

	}
	return "";
    }
}
