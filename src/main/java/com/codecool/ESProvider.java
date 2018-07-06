package com.codecool;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.codecool.containers.Fact;
import com.codecool.containers.FactRepository;
import com.codecool.containers.Question;
import com.codecool.containers.RuleRepository;
import com.codecool.parsers.FactParser;
import com.codecool.parsers.RuleParser;

public class ESProvider {

    private FactRepository factRepository;
    private RuleRepository ruleRepository;
    private Map<String, Boolean> userAnswers;
    private Question question;
    private Fact fact;
    private View view;

    public ESProvider(FactParser factParser, RuleParser ruleParser) {
        this.factRepository = factParser.getFactRepository();
        this.ruleRepository = ruleParser.getRuleRepository();
        view = new View();
    }

    public void collectAnsers() {

        this.userAnswers = new HashMap<>();
        Iterator<Question> questionIterator = this.ruleRepository.getIterator();
        boolean userAnswer;

        while (questionIterator.hasNext()) {

            this.question = questionIterator.next();
            view.print(this.question.getQuestion());
            userAnswer = getUserAnswer();
            view.print("");
            this.userAnswers.put(this.question.getId(), userAnswer);
        }
    }

    private boolean getUserAnswer() {

        boolean answer = false;
        boolean answerCorrect = false;

        while (!answerCorrect) {

            try {

                String input = view.getInput();
                answer = this.question.getEvaluatedAnswer(input);
                answerCorrect = true;

            } catch (InputMismatchException e) {

                view.print("Try another answer.");
            }
        }

        return answer;
    }

    public String evaluate() {
        
        StringBuilder builder = new StringBuilder();
        Iterator<Fact> factIter = this.factRepository.getIterator();
        

        while (factIter.hasNext()) {

            this.fact = factIter.next();

            if (!allAnswersMatchFact(this.fact.getIdSet()))
                continue;

            builder.append(this.fact.getDescription());
            builder.append("\n");
        }

        return builder.toString();
    }

    private boolean allAnswersMatchFact(Set<String> ids) {

        for (String id: ids) {
            if (this.fact.getValueById(id) != this.userAnswers.get(id))
                return false;
        }

        return true;
    }
}