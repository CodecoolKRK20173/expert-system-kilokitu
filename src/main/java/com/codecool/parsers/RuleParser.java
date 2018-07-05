package com.codecool.parsers;

import java.util.Arrays;
import java.util.List;

import com.codecool.containers.Answer;
import com.codecool.containers.MultipleValue;
import com.codecool.containers.Question;
import com.codecool.containers.RuleRepository;
import com.codecool.containers.SingleValue;
import com.codecool.containers.Value;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RuleParser extends XMLParser {

    private final String XML_PATH = getClass().getClassLoader().getResource("xmls/Rules.xml").getPath();

    private RuleRepository ruleRepository;
    private NodeList nodeList;

    public RuleParser() {
        this.ruleRepository = new RuleRepository();
    }

    public RuleRepository getRuleRepository() {

        loadXmlDocument(XML_PATH);
        parseXmlDocument();
        
        return this.ruleRepository;
    }

    private void parseXmlDocument() {
        this.nodeList = getDocument().getElementsByTagName("Rule");
        addRulesToRepository();
    }

    private void addRulesToRepository() {

        for (int i=0; i<this.nodeList.getLength(); i++) {

            Element ruleNode = (Element) this.nodeList.item(i);
            String id = ruleNode.getAttribute("id");

            Element questionNode = (Element) ruleNode.getElementsByTagName("Question").item(0);
            String questionText = questionNode.getTextContent();

            Answer answer = parseAnswer(ruleNode);

            Question question = new Question(id, questionText, answer);
            this.ruleRepository.addQuestion(question);
            
        }
    }

    private Answer parseAnswer(Element element) {

        Answer answer = new Answer();
        NodeList selections = element.getElementsByTagName("Selection");

        for (int i=0; i<selections.getLength(); i++) {

            Element selectionNode = (Element) selections.item(i);
            boolean selectionType = Boolean.valueOf(selectionNode.getAttribute("value"));

            Element valueNode = (Element) selectionNode.getChildNodes().item(1);
            Value value;

            if (valueNode.getNodeName().equals("SingleValue")) {

                String param = valueNode.getAttribute("value");
                value = new SingleValue(param, selectionType);

            } else {

                List<String> params = Arrays.asList(valueNode.getAttribute("value").split(","));
                value = new MultipleValue(params, selectionType);
            }

            answer.addValue(value);
        }

        return answer;
    }
}