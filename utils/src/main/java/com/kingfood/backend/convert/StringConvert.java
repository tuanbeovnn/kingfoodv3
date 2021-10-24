package com.kingfood.backend.convert;

import java.util.Locale;


public class StringConvert {

    /**
     * convertUpperCaseStringName
     *
     * @param input :
     * @return :
     */
    public static String convertUpperCaseStringName(String input) {
        String[] words = input.split("\\s");
        String[] result = new String[words.length];
        int index = 0;
        for (String str : words) {
            String first = str.substring(0 , 1).toUpperCase(Locale.ROOT);
            String after = str.substring(1).toLowerCase(Locale.ROOT);
            result[index] = first + after;
            index++;
        }
        return String.join(" ", result);
    }
}
