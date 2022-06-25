package unita.util;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import util.HashTool;

public class TestHashTool extends TestCase {
    HashTool hashTool;
    String string;
    String encString;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        hashTool = new HashTool();
        string = "test";
        encString = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"; //test in SHA256
    }

    @Test
    public void testHashSHA256() {
        String encStringTested = hashTool.hashSHA256(string);
        assertEquals(encString, encStringTested);
    }
}
