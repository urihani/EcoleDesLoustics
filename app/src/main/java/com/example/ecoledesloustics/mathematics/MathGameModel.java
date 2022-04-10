package com.example.ecoledesloustics.mathematics;

import java.util.ArrayList;
import java.util.List;

public class MathGameModel {
    private List<MathQuestionsModel> questions;
    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestions;
    private int firstUpperLimit;
    private int secondUpperLimit;
    private int score;
    private String category;
    private MathQuestionsModel currentQuestion;

    MathGameModel(int firstUpperLimit, int secondUpperLimit, int totalQuestions, String category) {
        questions = new ArrayList<>();
        numberCorrect = 0;
        numberIncorrect = 0;
        this.firstUpperLimit = firstUpperLimit;
        this.secondUpperLimit = secondUpperLimit;
        this.totalQuestions = totalQuestions;
        this.category = category;
        currentQuestion = new MathQuestionsModel(firstUpperLimit, secondUpperLimit, category);
    }

    public void makeNewQuestion() {
        currentQuestion = new MathQuestionsModel(firstUpperLimit, secondUpperLimit, category);
        questions.add(currentQuestion);
    }

    public boolean checkAnswer(int submittedAnswer) {
        boolean isCorrect;

        if (currentQuestion.getAnswer() == submittedAnswer) {
            numberCorrect++;
            isCorrect = true;
        } else {
            numberIncorrect++;
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean checkEndGame(){
        return (numberIncorrect + numberCorrect) >= totalQuestions;
    }

    public boolean checkWin(){
        return numberIncorrect == 0;
    }

    public List<MathQuestionsModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<MathQuestionsModel> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public MathQuestionsModel getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(MathQuestionsModel currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getFirstUpperLimit() {
        return firstUpperLimit;
    }

    public void setFirstUpperLimit(int firstUpperLimit) {
        this.firstUpperLimit = firstUpperLimit;
    }

    public int getSecondUpperLimit() {
        return secondUpperLimit;
    }

    public void setSecondUpperLimit(int secondUpperLimit) {
        this.secondUpperLimit = secondUpperLimit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
