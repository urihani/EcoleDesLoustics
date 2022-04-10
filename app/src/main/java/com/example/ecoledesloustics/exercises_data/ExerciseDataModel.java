package com.example.ecoledesloustics.exercises_data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "exercise", indices = {@Index(value = {"culture_ecology_questions"},unique = true)})
public class ExerciseDataModel {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "culture_ecology_questions")
    private HashMap<String, List<String>> cultureEcologyQuestions;

    @NonNull
    @ColumnInfo(name = "geography1_questions")
    private HashMap<String, List<String>> geography1Questions;

    public ExerciseDataModel(){
        cultureEcologyQuestions = new HashMap<>();
        makeCultureEcologyQuestions();

        geography1Questions = new HashMap<>();
        makeGeography1Questions();
    }

    private void makeCultureEcologyQuestions() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Ceux qui viennent des producteurs de ma région");
        answers1.add("Ceux qui viennent d'Europe");
        answers1.add("Ceux qui viennent d'Asie");
        answers1.add("Je ne sais pas");
        List<String> answers2 = new ArrayList<>();
        answers2.add("Prendre une douche rapide");
        answers2.add("Ne plus jamais me laver");
        answers2.add("Me plonger dans un bain");
        answers2.add("Je ne sais pas");
        List<String> answers3 = new ArrayList<>();
        answers3.add("A éteindre toutes les lumières");
        answers3.add("A les laisser allumées");
        answers3.add("A ouvrir la fenêtre et monter le chauffage");
        answers3.add("Je ne sais pas");
        List<String> answers4 = new ArrayList<>();
        answers4.add("J'essaye de le réparer");
        answers4.add("J'en demande un autre pour le remplacer");
        answers4.add("Je le jette par la fenêtre");
        answers4.add("Je ne sais pas");
        List<String> answers5 = new ArrayList<>();
        answers5.add("A pied");
        answers5.add("En voiture avec papa ou maman");
        answers5.add("En bus non électrique");
        answers5.add("Je ne sais pas");
        cultureEcologyQuestions.put("Je pars faire des courses avec mes parents pour acheter des fruits, lesquels vais-je privilégier ?", answers1);
        cultureEcologyQuestions.put("Je rentre de l'école et du sport et j'ai bien transpiré. Pour économiser l'eau, dois-je :", answers2);
        cultureEcologyQuestions.put("Quand je sors d'une pièce le soir, à la maison, à quoi dois-penser ?", answers3);
        cultureEcologyQuestions.put("J'ai cassé un de mes jouets, que dois-je faire ?", answers4);
        cultureEcologyQuestions.put("Pour aller à l'école, quel est le moyen le plus écolo ?", answers5);
    }

    private void makeGeography1Questions() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Ceux qui viennent des producteurs de ma région");
        answers1.add("Ceux qui viennent d'Europe");
        answers1.add("Ceux qui viennent d'Asie");
        answers1.add("Je ne sais pas");
        List<String> answers2 = new ArrayList<>();
        answers2.add("Prendre une douche rapide");
        answers2.add("Ne plus jamais me laver");
        answers2.add("Me plonger dans un bain");
        answers2.add("Je ne sais pas");
        List<String> answers3 = new ArrayList<>();
        answers3.add("A éteindre toutes les lumières");
        answers3.add("A les laisser allumées");
        answers3.add("A ouvrir la fenêtre et monter le chauffage");
        answers3.add("Je ne sais pas");
        List<String> answers4 = new ArrayList<>();
        answers4.add("J'essaye de le réparer");
        answers4.add("J'en demande un autre pour le remplacer");
        answers4.add("Je le jette par la fenêtre");
        answers4.add("Je ne sais pas");
        List<String> answers5 = new ArrayList<>();
        answers5.add("A pied");
        answers5.add("En voiture avec papa ou maman");
        answers5.add("En bus non électrique");
        answers5.add("Je ne sais pas");
        geography1Questions.put("Je pars faire des courses avec mes parents pour acheter des fruits, lesquels vais-je privilégier ?", answers1);
        geography1Questions.put("Je rentre de l'école et du sport et j'ai bien transpiré. Pour économiser l'eau, dois-je :", answers2);
        geography1Questions.put("Quand je sors d'une pièce le soir, à la maison, à quoi dois-penser ?", answers3);
        geography1Questions.put("J'ai cassé un de mes jouets, que dois-je faire ?", answers4);
        geography1Questions.put("Pour aller à l'école, quel est le moyen le plus écolo ?", answers5);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public HashMap<String, List<String>> getCultureEcologyQuestions() {
        return cultureEcologyQuestions;
    }

    public void setCultureEcologyQuestions(@NonNull HashMap<String, List<String>> cultureEcologyQuestions) {
        this.cultureEcologyQuestions = cultureEcologyQuestions;
    }

    @NonNull
    public HashMap<String, List<String>> getGeography1Questions() {
        return geography1Questions;
    }

    public void setGeography1Questions(@NonNull HashMap<String, List<String>> geography1Questions) {
        this.geography1Questions = geography1Questions;
    }
}
