/* For LarKC */
package subl.type.core;

import subl.CommonSymbols;
import subl.Errors;
import subl.Types;
import subl.type.number.SubLFixnum;
import subl.type.symbol.SubLSymbol;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubLRegexPattern extends FromSubLisp implements SubLObject {
    public SubLRegexPattern(String patternStr, int options) {
	int newOptions = 0;
	if ((options & 0x10) != 0x0)
	    regexError("*regex-comopt-anchored* is not supported.");
	if ((options & 0x1) != 0x0)
	    newOptions |= 0x2;
	if ((options & 0x20) != 0x0)
	    regexError("*regex-compopt-dollar-endonly* is not supported.");
	if ((options & 0x4) != 0x0)
	    newOptions |= 0x20;
	if ((options & 0x8) != 0x0)
	    newOptions |= 0x4;
	if ((options & 0x2) != 0x0)
	    newOptions |= 0x8;
	if ((options & 0x40) != 0x0)
	    regexError("*regex-compopt-extra* is not supported.");
	if ((options & 0x200) != 0x0)
	    regexError("*regex-compopt-ungreedy* is not supported.");
	if ((options & 0x800) != 0x0) {
	}
	if ((options & 0x1000) != 0x0)
	    regexError("*regex-compopt-no-auto-capture* is not supported.");
	if ((options & 0x2000) != 0x0)
	    regexError("*regex-compopt-no-utf8-check* is not supported.");
	this.patternStr = patternStr;
	pattern = Pattern.compile(patternStr, newOptions);
	matcher = pattern.matcher("");
    }

    private String patternStr;
    private Pattern pattern;
    private Matcher matcher;
    public static SubLString lastRegExError;
    public static String REGEX_PATTERN_NAME = "REGEX-PATTERN";
    private static int REGEX_COMPOPT_ANCHORED = 16;
    private static int REGEX_COMPOPT_CASELESS = 1;
    private static int REGEX_COMPOPT_DOLLAR_END_ONLY = 32;
    private static int REGEX_COMPOPT_DOTALL = 4;
    private static int REGEX_COMPOPT_EXTEND = 8;
    private static int REGEX_COMPOPT_MULTILINE = 2;
    private static int REGEX_COMPOPT_EXTRA = 64;
    private static int REGEX_COMPOPT_UNGREEDY = 512;
    private static int REGEX_COMPOPT_UTF8 = 2048;
    private static int REGEX_COMPOPT_NO_AUTO_CAPTURE = 4096;
    private static int REGEX_COMPOPT_NO_UTF8_CHECK = 8192;

    @Override
    public boolean canFastHash() {
	return true;
    }

    public ArrayList<String> getAllMatches(CharSequence str, int options, int maxNumMatches) {
	if (options != 0)
	    regexError("No options supported on regex match under this implementation.");
	matcher.reset(str);
	ArrayList<String> result = new ArrayList<String>();
	int nFound = 0;
	while (nFound < maxNumMatches && matcher.find()) {
	    int start = matcher.start();
	    int end = matcher.end();
	    result.add(str.subSequence(start, end).toString());
	    ++nFound;
	    for (int i = 1, size = matcher.groupCount(); i <= size && nFound < maxNumMatches; ++nFound, ++i) {
		start = matcher.start(i);
		end = matcher.end(i);
		result.add(str.subSequence(start, end).toString());
	    }
	}
	return result;
    }

    public ArrayList<Integer> getAllMatchesOffsets(CharSequence str, int options, int maxNumMatches) {
	if (options != 0)
	    regexError("No options supported on regex match under this implementation.");
	matcher.reset(str);
	ArrayList<Integer> result = new ArrayList<Integer>();
	int nFound = 0;
	if (nFound < maxNumMatches && matcher.find()) {
	    int start = matcher.start();
	    int end = matcher.end();
	    result.add(new Integer(start));
	    result.add(new Integer(end));
	    ++nFound;
	    for (int i = 1, size = matcher.groupCount(); i <= size && nFound < maxNumMatches; ++nFound, ++i) {
		start = matcher.start(i);
		end = matcher.end(i);
		result.add(new Integer(start));
		result.add(new Integer(end));
	    }
	}
	return result;
    }

    /**
     * @param string
     */
    private void regexError(String string) {
	lastRegExError = SubLObjectFactory.makeString(string);
	Errors.error(string);
    }

    @Override
    public SubLSymbol getType() {
	return Types.$dtp_alien$;
    }

    @Override
    public SubLFixnum getTypeCode() {
	return CommonSymbols.THIRTEEN_INTEGER;
    }

	@Override
    public boolean isAtom() {
	return true;
    }

	@Override
    public boolean isRegexPattern() {
	return true;
    }

	@Override
    public SubLRegexPattern toRegexPattern() {
	return this;
    }

    @Override
    public String printObjectImpl() {
	return new String("<#" + toTypeName() + " pattern: \"" + patternStr + "\" @" + this.hashCode(0) + ">");
    }

    @Override
    public String toTypeName() {
	return "REGEX-PATTERN";
    }
}
