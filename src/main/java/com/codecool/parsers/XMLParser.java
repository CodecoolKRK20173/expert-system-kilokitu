package com.codecool.parsers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

abstract class XMLParser {

    private Document document;

    void loadXmlDocument(String xmlPath) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        this.document = dBuilder.parse(new File(xmlPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Document getDocument() {
        return this.document;
    }
}