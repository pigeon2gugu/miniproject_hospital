package com.line;

import com.dbexecise.domain.User;
import com.line.parser.Parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void insert() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, "4");
        ps.setString(2, "Haneul");
        ps.setString(3, "1123");

        ps.executeUpdate(); //ctrl + enter
        ps.close();
        conn.close();
    }

    public User select(String id) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        ps.close();
        conn.close();
        return user;
    }

}
