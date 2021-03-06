package com.example.ecoledesloustics.exercises_data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "exercise", indices = {@Index(value = {"culture_questions1"},unique = true)})
public class ExerciseDataModel {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "culture_questions1")
    private HashMap<String, List<String>> cultureQuestions1;

    @NonNull
    @ColumnInfo(name = "culture_questions2")
    private HashMap<String, List<String>> cultureQuestions2;

    @NonNull
    @ColumnInfo(name = "culture_questions3")
    private HashMap<String, List<String>> cultureQuestions3;

    @NonNull
    @ColumnInfo(name = "culture_questions4")
    private HashMap<String, List<String>> cultureQuestions4;

    @NonNull
    @ColumnInfo(name = "culture_questions5")
    private HashMap<String, List<String>> cultureQuestions5;

    @NonNull
    @ColumnInfo(name = "geography_questions1")
    private HashMap<String, List<String>> geographyQuestions1;

    @NonNull
    @ColumnInfo(name = "geography_questions2")
    private HashMap<String, List<String>> geographyQuestions2;

    @NonNull
    @ColumnInfo(name = "geography_questions3")
    private HashMap<String, List<String>> geographyQuestions3;

    public ExerciseDataModel(){
        cultureQuestions1 = new HashMap<>();
        makeCultureQuestions1();

        cultureQuestions2 = new HashMap<>();
        makeCultureQuestions2();

        cultureQuestions3 = new HashMap<>();
        makeCultureQuestions3();

        cultureQuestions4 = new HashMap<>();
        makeCultureQuestions4();

        cultureQuestions5 = new HashMap<>();
        makeCultureQuestions5();

        geographyQuestions1 = new HashMap<>();
        makeGeographyQuestions1();

        geographyQuestions2 = new HashMap<>();
        makeGeographyQuestions2();

        geographyQuestions3 = new HashMap<>();
        makeGeographyQuestions3();
    }

    private void makeCultureQuestions5() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Les Minions");
        answers1.add("Les Tronions");
        answers1.add("Les Choupignons");
        answers1.add("Je ne sais pas");
        cultureQuestions5.put("Comment s???appellent les camarades de Gru dans le dessin anim?? \"Moi, moche et m??chant\"", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("SuperJulie");
        answers2.add("Supergirly");
        answers2.add("SuperMimi");
        answers2.add("Je ne sais pas");
        cultureQuestions5.put("SamSam le super h??ros cosmique, est souvent accompagn?? par sa meilleure amie. Mais qui est-elle ?", answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("Petite Ourse Grise");
        answers3.add("Petite Ourse Rousse");
        answers3.add("Petite Ourse Perle");
        answers3.add("Je ne sais pas");
        cultureQuestions5.put("Qui est l'amie de Petit Ours Brun, dont il est un peu amoureux ?", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("A la bonne fourchette");
        answers4.add("Aux petits oignons");
        answers4.add("Au petit tonneau");
        answers4.add("Je ne sais pas");
        cultureQuestions5.put("Dans quel c??l??bre restaurant peut-on d??jeuner avec Tom-Tom et Nana ?", answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("Un bouclier");
        answers5.add("Un marteau");
        answers5.add("Un sabre");
        answers5.add("Je ne sais pas");
        cultureQuestions5.put("Quel accessoire compl??te le costume de Captain America ?",
                answers5);
    }

    private void makeCultureQuestions4() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Ma??tre Gims");
        answers1.add("Nekfeu");
        answers1.add("Slimane");
        answers1.add("Je ne sais pas");
        cultureQuestions4.put("Il est chanteur et a chant?? ???Sap??s comme jamais???", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("Soprano");
        answers2.add("Black M");
        answers2.add("Patrick Fiori");
        answers2.add("Je ne sais pas");
        cultureQuestions4.put("C'est un jur?? tr??s appr??ci?? de l?????mission familiale ?? The Voice " +
                        "Kids ??",
                answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("Fr??res");
        answers3.add("Cousins");
        answers3.add("P??re et fils");
        answers3.add("Je ne sais pas");
        cultureQuestions4.put("Big Flo et Oli, duo de rappeurs, sont...", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("Viita");
        answers4.add("Christine and the Queens");
        answers4.add("Clara Luciani");
        answers4.add("Je ne sais pas");
        cultureQuestions4.put("Elle accompagne le chanteur Slimane depuis quelques mois en duo..."
                , answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("Thomas Pesquet");
        answers5.add("Thomas Wiesel");
        answers5.add("Thomas Coville");
        answers5.add("Je ne sais pas");
        cultureQuestions4.put("Son voyage dans l'espace vous a fait r??v??",
                answers5);
    }

    private void makeCultureQuestions3() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("L'apparition de l'Homme sur terre");
        answers1.add("L'apparition des voitures");
        answers1.add("L'apparition des dinosaures");
        answers1.add("Je ne sais pas");
        cultureQuestions3.put("A quoi correspond la p??riode de la pr??histoire ?", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("Il y a environ 3 ?? 5 millions d'ann??es");
        answers2.add("Il y a 3000 ans");
        answers2.add("Au 13??me si??cle");
        answers2.add("Je ne sais pas");
        cultureQuestions3.put("Quand commence la pr??histoire ?", answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("Les chercheurs ??coutaient \"Lucy in the sky of diamonds\" au moment de la " +
                "d??couverte");
        answers3.add("Un bracelet en d??fense de mammouth ?? son poignet ??tait grav?? ?? son nom");
        answers3.add("La personne qui l'a d??couverte s'appelait Lucy");
        answers3.add("Je ne sais pas");
        cultureQuestions3.put("Pourquoi a t-on donn?? le nom de Lucy ?? un squelette d??couvert en Ethiopie en 1974 ?", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("Des cadavres de mammouth entiers");
        answers4.add("Des bijoux en or");
        answers4.add("Un palais russe meubl??");
        answers4.add("Je ne sais pas");
        cultureQuestions3.put("Qu'a t-on retrouv?? congel?? dans les glaces en Sib??rie ?", answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("Avec deux pierres");
        answers5.add("Avec un briquet");
        answers5.add("En soufflant tr??s fort sur de l'herbe s??che");
        answers5.add("Je ne sais pas");
        cultureQuestions3.put("Comment allumait-on le feu ?? la pr??histoire ?",
                answers5);
    }

    private void makeCultureQuestions2() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Des r??cits l??gendaires qui r??pondent ?? nos interrogations sur l'origine du monde");
        answers1.add("Une plante g??ante qui poussait au temps des dinosaures");
        answers1.add("Une maladie de mycoses qui abimait les pieds au Moyen-Age");
        answers1.add("Je ne sais pas");
        cultureQuestions2.put("C'est quoi la mythologie ?", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("Au talon");
        answers2.add("Au c??ur");
        answers2.add("A l?????il");
        answers2.add("Je ne sais pas");
        cultureQuestions2.put("Par quelle blessure Achille est-il mort ?", answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("12");
        answers3.add("5");
        answers3.add("20");
        answers3.add("Je ne sais pas");
        cultureQuestions2.put("A combien de travaux Hercule se soumet-il ?", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("Du ciel ?");
        answers4.add("Des enfers ?");
        answers4.add("Des mers ?");
        answers4.add("Je ne sais pas");
        cultureQuestions2.put("Zeus est-il le dieu...", answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("En se cachant dans un grand cheval de bois");
        answers5.add("En s'??lan??ant avec des catapultes");
        answers5.add("En se d??guisant en femmes");
        answers5.add("Je ne sais pas");
        cultureQuestions2.put("Comment les Grecs arrivent ?? entrer dans la ville imprenable de Troie ?",
                answers5);
    }

    private void makeCultureQuestions1() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Ceux qui viennent des producteurs de ma r??gion");
        answers1.add("Ceux qui viennent d'Europe");
        answers1.add("Ceux qui viennent d'Asie");
        answers1.add("Je ne sais pas");
        cultureQuestions1.put("Je pars faire des courses avec mes parents pour acheter des" +
                " fruits, lesquels vais-je privil??gier ?", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("Prendre une douche rapide");
        answers2.add("Ne plus jamais me laver");
        answers2.add("Me plonger dans un bain");
        answers2.add("Je ne sais pas");
        cultureQuestions1.put("Je rentre de l'??cole et du sport et j'ai bien transpir??. " +
                "Pour ??conomiser l'eau, dois-je :", answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("A ??teindre toutes les lumi??res");
        answers3.add("A les laisser allum??es");
        answers3.add("A ouvrir la fen??tre et monter le chauffage");
        answers3.add("Je ne sais pas");
        cultureQuestions1.put("Quand je sors d'une pi??ce le soir, ?? la maison, ?? quoi " +
                "dois-penser ?", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("J'essaye de le r??parer");
        answers4.add("J'en demande un autre pour le remplacer");
        answers4.add("Je le jette par la fen??tre");
        answers4.add("Je ne sais pas");
        cultureQuestions1.put("J'ai cass?? un de mes jouets, que dois-je faire ?", answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("A pied");
        answers5.add("En voiture avec papa ou maman");
        answers5.add("En bus non ??lectrique");
        answers5.add("Je ne sais pas");
        cultureQuestions1.put("Pour aller ?? l'??cole, quel est le moyen le plus ??colo ?",
                answers5);
    }

    private void makeGeographyQuestions3() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Berne");
        answers1.add("Gen??ve");
        answers1.add("Lausanne");
        answers1.add("Je ne sais pas");
        geographyQuestions3.put("Quelle est la capitale de la Suisse ?", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("Amsterdam");
        answers2.add("Rotterdam");
        answers2.add("Eindhoven");
        answers2.add("Je ne sais pas");
        geographyQuestions3.put("Quelle est la capitale des Pays-Bas ?", answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("Prague");
        answers3.add("Leipzig");
        answers3.add("Bratislava");
        answers3.add("Je ne sais pas");
        geographyQuestions3.put("Quelle est la capitale de la R??publique Tch??que ?", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("Kiev");
        answers4.add("Minsk");
        answers4.add("Odessa");
        answers4.add("Je ne sais pas");
        geographyQuestions3.put("Quelle est la capitale de l???Ukraine ?", answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("Chisinau");
        answers5.add("Mykola??v");
        answers5.add("Lasi");
        answers5.add("Je ne sais pas");
        geographyQuestions3.put("Quelle est la capitale de la Moldavie ?",
                answers5);
    }

    private void makeGeographyQuestions2() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Rome");
        answers1.add("Turin");
        answers1.add("Milan");
        answers1.add("Je ne sais pas");
        geographyQuestions2.put("Quelle est la capitale de l???Italie ?", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("Lisbonne");
        answers2.add("Vila Real");
        answers2.add("Porto");
        answers2.add("Je ne sais pas");
        geographyQuestions2.put("Quelle est la capitale du Portugal ?", answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("Helsinki");
        answers3.add("Stockholm");
        answers3.add("Oslo");
        answers3.add("Je ne sais pas");
        geographyQuestions2.put("Quelle est la capitale de la Finlande ?", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("Sofia");
        answers4.add("Istanbul");
        answers4.add("Bucarest");
        answers4.add("Je ne sais pas");
        geographyQuestions2.put("Quelle est la capitale de la Bulgarie ?", answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("La Valette");
        answers5.add("Ix-Xewkija");
        answers5.add("San Lawrenz");
        answers5.add("Je ne sais pas");
        geographyQuestions2.put("Quelle est la capitale de Malte ?",
                answers5);
    }

    private void makeGeographyQuestions1() {
        List<String> answers1 = new ArrayList<>();
        answers1.add("Bruxelles");
        answers1.add("Bruges");
        answers1.add("Li??ge");
        answers1.add("Je ne sais pas");
        geographyQuestions1.put("Quelle est la capitale de la Belgique ?", answers1);

        List<String> answers2 = new ArrayList<>();
        answers2.add("Berlin");
        answers2.add("Nuremberg");
        answers2.add("Hanovre");
        answers2.add("Je ne sais pas");
        geographyQuestions1.put("Quelle est la capitale de l???Allemagne ?", answers2);

        List<String> answers3 = new ArrayList<>();
        answers3.add("Londres");
        answers3.add("Manchester");
        answers3.add("Cardiff");
        answers3.add("Je ne sais pas");
        geographyQuestions1.put("Quelle est la capitale du Royaume-Uni ?", answers3);

        List<String> answers4 = new ArrayList<>();
        answers4.add("Vienne");
        answers4.add("Graz");
        answers4.add("Salzbourg");
        answers4.add("Je ne sais pas");
        geographyQuestions1.put("Quelle est la capitale de l???Autriche ?", answers4);

        List<String> answers5 = new ArrayList<>();
        answers5.add("Varsovie");
        answers5.add("Cracovie");
        answers5.add("Bratislava");
        answers5.add("Je ne sais pas");
        geographyQuestions1.put("Quelle est la capitale de la Pologne ?",
                answers5);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public HashMap<String, List<String>> getCultureQuestions1() {
        return cultureQuestions1;
    }

    public void setCultureQuestions1(@NonNull HashMap<String, List<String>> cultureQuestions1) {
        this.cultureQuestions1 = cultureQuestions1;
    }

    @NonNull
    public HashMap<String, List<String>> getCultureQuestions2() {
        return cultureQuestions2;
    }

    public void setCultureQuestions2(@NonNull HashMap<String, List<String>> cultureQuestions2) {
        this.cultureQuestions2 = cultureQuestions2;
    }

    @NonNull
    public HashMap<String, List<String>> getGeographyQuestions1() {
        return geographyQuestions1;
    }

    public void setGeographyQuestions1(@NonNull HashMap<String, List<String>> geographyQuestions1) {
        this.geographyQuestions1 = geographyQuestions1;
    }

    @NonNull
    public HashMap<String, List<String>> getGeographyQuestions2() {
        return geographyQuestions2;
    }

    public void setGeographyQuestions2(@NonNull HashMap<String, List<String>> geographyQuestions2) {
        this.geographyQuestions2 = geographyQuestions2;
    }

    @NonNull
    public HashMap<String, List<String>> getCultureQuestions3() {
        return cultureQuestions3;
    }

    public void setCultureQuestions3(@NonNull HashMap<String, List<String>> cultureQuestions3) {
        this.cultureQuestions3 = cultureQuestions3;
    }

    @NonNull
    public HashMap<String, List<String>> getCultureQuestions4() {
        return cultureQuestions4;
    }

    public void setCultureQuestions4(@NonNull HashMap<String, List<String>> cultureQuestions4) {
        this.cultureQuestions4 = cultureQuestions4;
    }

    @NonNull
    public HashMap<String, List<String>> getCultureQuestions5() {
        return cultureQuestions5;
    }

    public void setCultureQuestions5(@NonNull HashMap<String, List<String>> cultureQuestions5) {
        this.cultureQuestions5 = cultureQuestions5;
    }

    @NonNull
    public HashMap<String, List<String>> getGeographyQuestions3() {
        return geographyQuestions3;
    }

    public void setGeographyQuestions3(@NonNull HashMap<String, List<String>> geographyQuestions3) {
        this.geographyQuestions3 = geographyQuestions3;
    }
}
