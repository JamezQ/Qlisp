package qlisp;

import java.util.ArrayList;

/**
 * This is the Lexer for my language, it will give the next word of input.
 *
 * @author James McClain
 */
public class Lexer {
    /** Holds the input for my language */
    private String[] text;
    /** Holds the place in the input array */
    private int place = 0;
    /**
     * Explicit default constructor. Does nothing
     */
    public Lexer() {
    }
    /**
     * Sets the text to process.
     *
     * @param text The command String.
     */
    public Lexer(String text) {
	this.text = parseLisp(text);
    }
    /**
     * Parse a lisp String. Separating the parts.
     *
     * @param lispCode Some lisp code.
     * 
     * @return An String array.
     */
    public String[] parseLisp(String lispCode) {	    
	
	ArrayList<String> tokens = new ArrayList<String>();
	/** Start of substring */
	int a = -1;
	/** End of substring */
	int b = -1;
	/** Depth of parens */
	int paren = 0;
	
	for (int i = 0;i < lispCode.length();i++) {
	    if ('(' == lispCode.charAt(i)) {
		if (paren == 0) {
		    a = i;
		}
		paren += 1;
	    }
	    if (')' == lispCode.charAt(i)) {
		paren -= 1;
		if (paren == 0) {
		    if (a != -1) {
			b = i;
		    }
		}
	    }
	    if (b != -1) {
		tokens.add(lispCode.substring(a,b+1));
		a = -1;
		b = -1;
	    }
	    
	}
	String[] returnString = new String[tokens.size()];
	for(int i = 0;i < returnString.length;i++) {
	    returnString[i] = tokens.get(i);
	}
	return returnString;
    }
    /**
     * Next eval.
     *
     * @return The next place needed to be evaled.
     */
    public String next() {
	if (place >= text.length) {
	    return null;
	}
	return text[place++];
    }
    /**
     * Get the arguments for a function, including the function name.
     * The String to test is got from the current lexer.
     * 
     * @return String array of arguments, arg 0 being the function name.
     */
    public String[] args() {
	return args(text[place-1]);
    }
    /**
     * Get the arguments for a function, including the function name.
     * 
     * @param testString The String to look for arguments.
     * This must be in parens, call parseLisp to get the right form.
     * 
     * @return String array of arguments, arg 0 being the function name.
     */
    public String[] args(String testString) {
	if (testString == null) {
	    return null;
	}
	ArrayList<String> al = new ArrayList<String>();
	int b = -1;
	for (int i = 1; i < testString.length(); i++) {
	    if ('(' == testString.charAt(i)) {
		b = nextParen(testString.substring(i));
		
		al.add(testString.substring(i,b+i+1));
		i += b;
		b = -1;		    
	    }
	    else if ('"' == testString.charAt(i)) {
		b = nextQuote(testString.substring(i));
		al.add(testString.substring(i,b+i+1));
		i += b;
		b = -1;
	    }
	    else if (' ' != testString.charAt(i)) {
		b = nextSpace(testString.substring(i));
		if (b == -1) {
		    b = nextParen(testString.substring(i));
		}
		if (b == -1) {
		    if ( i == testString.length()-1 ) {
			break;
		    }
		}
		al.add(testString.substring(i,b+i));
		i += b;
		b = -1;		    
	    }
	    else {
		continue; // I know it's not needed, I'm feeling like being
		// explicit, so sue me.
	    }
	    
	}
	if (al.size() == 0) {
	    return null;
	}
	String[] returnString = new String[al.size()];
	for(int i = 0;i < returnString.length;i++) {
	    returnString[i] = al.get(i);
	}
	return returnString;
    }
    private int nextParen(String toTest) {
	int level = 0;
	for (int i = 1;i < toTest.length();i++) {
	    if ('(' == toTest.charAt(i)) {
		level -= 1;
	    }
	    if (')' == toTest.charAt(i)) {
		if (level == 0) {
		    return i;
		}
		level += 1;
	    }
	}
	return -1;
    }
    private int nextSpace(String toTest) {	
	for (int i = 1;i < toTest.length();i++) {
	    if (' ' == toTest.charAt(i)) {
		return i;
	    }
	}
	return -1;
    }
    private int nextQuote(String toTest) {	
	for (int i = 1;i < toTest.length();i++) {
	    if ('"' == toTest.charAt(i)) {
		if ('\\' == toTest.charAt(i-1)) {
		    continue;
		}
		return i;
	    }
	}
	return -1;
    }
    
}
