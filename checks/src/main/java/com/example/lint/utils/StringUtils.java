package com.example.lint.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by teddylin on 18/12/2017.
 */

public class StringUtils {
    public static Matcher getMatcher(String source, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        return matcher;
    }

    public static boolean containsHanScript(String s) {
        return s.codePoints().anyMatch(
                codepoint ->
                        Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN);
    }

}
