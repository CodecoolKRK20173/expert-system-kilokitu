package com.codecool;

import java.util.Scanner;

public class View {

    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine().toLowerCase();
    }

    public void print(String text) {
        System.out.println(text);
    }
}