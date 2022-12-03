package com.doanoop;

import java.nio.file.Paths;

import ThuVien.Main;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(Paths.get("result.csv").toAbsolutePath());
        Main.runApp(new String[] { "1" });
    }
}
