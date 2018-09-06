package ru.job4j.inout;

import java.io.IOException;
import java.io.InputStream;

/**
 * Finds even number in the InputStream.
 */
public class EvenFindStream {

    /**
     * Check if stream has even number.
     * @param in input stream.
     * @return true if if stream has even number.
     * @throws IOException .
     */
    public boolean isNumber(InputStream in) throws IOException {
        boolean result = false;
        while (in.available() > 0) {
            int temp = in.read();
            if (temp % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }
}
