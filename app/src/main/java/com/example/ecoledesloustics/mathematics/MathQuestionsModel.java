package com.example.ecoledesloustics.mathematics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathQuestionsModel {
    private int firstNumber;
    private int secondNumber;
    private int answer;
    private int[] answerArray;
    private int answerPosition;
    private int firstUpperLimit;
    private int secondUpperLimit;
    private String category;
    private String questionPhrase;

    MathQuestionsModel(int firstUpperLimit, int secondUpperLimit, String category) {
        this.firstUpperLimit = firstUpperLimit;
        this.secondUpperLimit = secondUpperLimit;
        this.category = category;
        Random randomGenerator = new Random();
        this.generateNumbers();

        this.compute();

        // we want the position of the answer to change randomly
        this.answerPosition = randomGenerator.nextInt(4);
        this.answerArray = new int[]{0, 1, 2, 3};

        this.answerArray[0] = this.answer + 1;
        this.answerArray[1] = this.answer + 10;
        this.answerArray[2] = this.answer - 5;
        this.answerArray[3] = this.answer - 2;

        this.answerArray = shuffleArray(this.answerArray);

        this.answerArray[answerPosition] = answer;
    }

    private void generateNumbers() {
        Random randomGenerator = new Random();
        this.firstNumber = randomGenerator.nextInt(firstUpperLimit);
        this.secondNumber = randomGenerator.nextInt(secondUpperLimit);
        if (firstNumber < secondNumber) {
            int tmp;
            tmp = firstNumber;
            setFirstNumber(secondNumber);
            setSecondNumber(tmp);
        }
    }

    private void compute() {
        switch (this.category) {
            case "Multiplications":
                this.answer = this.firstNumber * this.secondNumber;
                this.questionPhrase = firstNumber + " x " + secondNumber;
                break;
            case "Multiplications chronométrées":
                this.answer = this.firstNumber * this.secondNumber;
                this.questionPhrase = firstNumber + " x " + secondNumber;
                break;
            case "Additions":
                this.answer = this.firstNumber + this.secondNumber;
                this.questionPhrase = firstNumber + " + " + secondNumber;
                break;
            case "Additions chronométrées":
                this.answer = this.firstNumber + this.secondNumber;
                this.questionPhrase = firstNumber + " + " + secondNumber;
                break;
            case "Soustractions":
                this.answer = this.firstNumber - this.secondNumber;
                this.questionPhrase = firstNumber + " - " + secondNumber;
                break;
            case "Soustractions chronométrées":
                this.answer = this.firstNumber - this.secondNumber;
                this.questionPhrase = firstNumber + " - " + secondNumber;
                break;
            default:
                break;
        }
    }

    private int[] shuffleArray(int[] answerArray) {
        int index, temp;
        Random randomGenerator = new Random();

        for (int i = answerArray.length - 1; i > 0; i--) {
            index = randomGenerator.nextInt(i + 1);
            temp = answerArray[index];
            answerArray[index] = answerArray[i];
            answerArray[i] = temp;
        }
        return answerArray;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
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

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}
