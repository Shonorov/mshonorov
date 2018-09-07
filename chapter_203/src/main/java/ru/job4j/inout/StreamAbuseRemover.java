package ru.job4j.inout;

import java.io.*;

/**
 * Removes abuse word from stream.
 */
public class StreamAbuseRemover {

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (Reader reader = new InputStreamReader(in)) {
            String word = "";
            while(reader.ready()) {
                char temp = (char)reader.read();
                if(temp != ' ') {
                    word += temp;
                } else {
                    for (String s : abuse) {
                        System.out.println(word);
                        System.out.println(s);
                        System.out.println("----------");
                        if(word.equals(s)) {
                            word = "";
                        }
                    }
                    out.write(word.getBytes());
                    out.write(' ');
                    word = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
