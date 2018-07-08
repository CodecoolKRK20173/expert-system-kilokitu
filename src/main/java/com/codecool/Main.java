package com.codecool;

import com.codecool.parsers.FactParser;
import com.codecool.parsers.RuleParser;

public class Main {
    
    public static void main(String[] args) {
        View view = new View();
        String factsXmlPath = Main.class.getResource("/xmls/Facts.xml").getPath();
        String rulesXmlPath = Main.class.getResource("/xmls/Rules.xml").getPath();
        ESProvider esProvider = new ESProvider(new FactParser(factsXmlPath), new RuleParser(rulesXmlPath));

        view.print("\nWelcome to computer hardware advisor. Please answer some questions.\n");
        esProvider.collectAnsers();
        String output = esProvider.evaluate();
        if (output.equals(""))
            output = "Nothing has been found.";

        view.print("My best advice is to buy:");
        view.print(output);
    }
}