package com.adamwilson.common;

import junit.framework.TestCase;

public class CsvUtilTest extends TestCase {

    private final String ONE   = "ONE";
    private final String TWO   = "TWO";
    private final String THREE = "THREE";
    private final String FOUR  = "FOUR";
    private final String COMMA = ",";

    public void testToCsv() throws Exception {

        assertNull(CsvUtil.toCsv(null));

        assertNull(CsvUtil.toCsv(""));
        assertNull(CsvUtil.toCsv(" "));

        assertEquals(ONE, CsvUtil.toCsv(ONE));
        assertEquals(ONE, CsvUtil.toCsv(ONE, " "));

        final String SOURCE = ONE + COMMA + TWO + COMMA + THREE + COMMA + FOUR;
        final String RESULT = CsvUtil.toCsv(ONE, TWO, THREE, FOUR);

        assertNotNull(RESULT);
        assertFalse(RESULT.isEmpty());
        assertEquals(SOURCE, RESULT);
    }

}