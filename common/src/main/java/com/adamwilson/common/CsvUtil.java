package com.adamwilson.common;

public class CsvUtil {

    public static final String toCsv(String... values) {

        if (values == null) return null;
        if (values.length == 1) {
            return isValid(values[0]) ? values[0] : null;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < values.length; i++) {

            final String s = values[i];
            if (!isValid(s)) continue;

            if (i > 0 && i < values.length)
                builder.append(',');

            builder.append(s);
        }

        final String result = builder.toString().trim();
        if (result.trim().isEmpty()) return null;

        return builder.toString();
    }

    private static boolean isValid(String s) {
        if (s == null) return false;
        s = s.trim();
        return !s.isEmpty();
    }

}
