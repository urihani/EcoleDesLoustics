package com.example.ecoledesloustics.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuizQuestionsModel {
    private String question;
    private List<String> answers;
    private String category;
    private String answer;
    private String[] answerArray;
    private ArrayList<String> lettersList;
    String[] lettersArray;
    private int answerPosition;
    private int i = 0;

    public QuizQuestionsModel(String question, List<String> answers, String category) {
        this.question = question;
        this.answers = answers;
        this.category = category;

        // we want the position of the answer to change randomly
        this.answerArray = new String[]{"", "", "", ""};
        this.lettersArray = new String[]{"", "", "", ""};

        answer = answers.get(0);
        this.answerArray[0] = answers.get(0);
        this.answerArray[1] = answers.get(1);
        this.answerArray[2] = answers.get(2);
        this.answerArray[3] = answers.get(3);

        this.answerArray = shuffleArray(this.answerArray);

        // join letters with answer
        lettersList = new ArrayList<>();
        lettersList.add("A");
        lettersList.add("B");
        lettersList.add("C");
        lettersList.add("D");
        for (int i = 0; i < answerArray.length; i++) {
            if (answerArray[i].equals(answer)){
                answer = lettersList.get(i) + ") " + answerArray[i];
            }
            answerArray[i] = lettersList.get(i) + ") " + answerArray[i];
            lettersArray[i] = lettersList.get(i);
        }
    }

    private String[] shuffleArray(String[] answerArray) {
        int index;
        String temp;
        Random randomGenerator = new Random();

        for (int i = answerArray.length - 1; i > 0; i--) {
            index = randomGenerator.nextInt(i + 1);
            temp = answerArray[index];
            answerArray[index] = answerArray[i];
            answerArray[i] = temp;
        }
        return answerArray;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(String[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public ArrayList<String> getLettersList() {
        return lettersList;
    }

    public void setLettersList(ArrayList<String> lettersList) {
        this.lettersList = lettersList;
    }

    public String[] getLettersArray() {
        return lettersArray;
    }

    public void setLettersArray(String[] lettersArray) {
        this.lettersArray = lettersArray;
    }
}
