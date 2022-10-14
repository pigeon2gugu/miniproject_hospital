package com.line;

import com.line.parser.Parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class LineReader<T> {

    Parser<T> parser;

    public LineReader(Parser<T> parser) {
        this.parser = parser;
    }

    List<T> readLines(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
        String str;

        while ((str = br.readLine()) != null) {
            result.add(parser.parse(str));
        }

        return result;
    }

    public void createANewFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        System.out.println("파일 생성 되었나?:" + file.exists());
    }

    public void writeLines(List<String> lines, String filename) throws IOException {
        File file = new File(filename);

        try {
            BufferedWriter writer
                = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                for (String str : lines) {
                    writer.write(str);
                }
                writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("success");
    }


}
