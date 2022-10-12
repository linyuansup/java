package sy.c.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DBReader {
    private final BufferedReader br;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public DBReader(String path) throws IOException {
        new File(path).createNewFile();
        br = new BufferedReader(new FileReader(path));
    }

    public String read() throws IOException {
        return br.readLine();
    }
}