package main;

import fomenu.Menu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            Menu m = new Menu();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}