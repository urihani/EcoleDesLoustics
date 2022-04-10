package com.example.ecoledesloustics.quiz;

import com.example.ecoledesloustics.quiz.QuizQuestionsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QuizGameModel {

    private List<QuizQuestionsModel> questions;
    private int numberCorrect;
    private int numberIncorrect;
    private QuizQuestionsModel currentQuestion;
    private HashMap<String, List<String>> quizdata;
    private int totalQuestions;
    private String category;
    int i = 0;

    public QuizGameModel(HashMap<String, List<String>> quizData, int totalQuestions,
                  String category){
        questions = new ArrayList<>();
        numberCorrect = 0;
        numberIncorrect = 0;
        this.quizdata = quizData;
        this.totalQuestions = totalQuestions;
        this.category = category;

        Iterator<Map.Entry<String, List<String>>> itr = quizData.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<String, List<String>> entry = itr.next();
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            questions.add(new QuizQuestionsModel(entry.getKey(), entry.getValue(), category));
        }
    }

    public void makeNewQuestion() {
        if (!questions.isEmpty() && i <= questions.size()){
            String question = questions.get(i).getQuestion();
            List<String> answers = questions.get(i).getAnswers();
            currentQuestion = new QuizQuestionsModel(question, answers, category);
            i++;
        }
    }

    public boolean checkAnswer(String submittedAnswer) {
        boolean isCorrect;

        String[] correctAnswer = currentQuestion.getAnswer().split("\\)");

        if (correctAnswer[0].equals(submittedAnswer)) {
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

    public List<QuizQuestionsModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestionsModel> questions) {
        this.questions = questions;
    }

    public QuizQuestionsModel getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuizQuestionsModel currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public HashMap<String, List<String>> getQuizdata() {
        return quizdata;
    }

    public void setQuizdata(HashMap<String, List<String>> quizdata) {
        this.quizdata = quizdata;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
