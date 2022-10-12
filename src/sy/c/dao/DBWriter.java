package sy.c.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class DBWriter {
    private final BufferedWriter bw;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public DBWriter(String path) throws IOException {
        new File(path).createNewFile();
        bw = new BufferedWriter(new FileWriter(path));
    }

    public void write(String s) throws IOException {
        bw.write(s);
        bw.newLine();
    }
}