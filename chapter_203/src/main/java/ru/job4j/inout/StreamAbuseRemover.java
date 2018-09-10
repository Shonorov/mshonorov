package ru.job4j.inout;

import java.io.*;

/**
 * Removes words from stream.
 */
public class StreamAbuseRemover {

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (Reader reader = new InputStreamReader(in)) {
            String word = "";
            while(reader.ready()) {
                char temp = (char)reader.read();
                if (!reader.ready()) {
                    word += temp;
                    out.write(word.getBytes());
                } else if (temp != ' ') {
                    word += temp;
                }
                else {
                    for (String s : abuse) {
                        if (word.equals(s)) {
                            word = "";
                            break;
                        }
                    }
                    out.write(word.getBytes());
                    if (word.length() != 0) {
                        out.write(' ');
                    }
                    word = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
