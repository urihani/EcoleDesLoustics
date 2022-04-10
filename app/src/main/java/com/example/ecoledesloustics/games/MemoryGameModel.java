package com.example.ecoledesloustics.games;

import static com.example.ecoledesloustics.R.drawable.ic_animal0;
import static com.example.ecoledesloustics.R.drawable.ic_animal1;
import static com.example.ecoledesloustics.R.drawable.ic_animal2;
import static com.example.ecoledesloustics.R.drawable.ic_animal3;
import static com.example.ecoledesloustics.R.drawable.ic_animal4;
import static com.example.ecoledesloustics.R.drawable.ic_animal5;
import static com.example.ecoledesloustics.R.drawable.ic_fruit0;
import static com.example.ecoledesloustics.R.drawable.ic_fruit1;
import static com.example.ecoledesloustics.R.drawable.ic_fruit2;
import static com.example.ecoledesloustics.R.drawable.ic_fruit3;
import static com.example.ecoledesloustics.R.drawable.ic_fruit4;
import static com.example.ecoledesloustics.R.drawable.ic_fruit5;
import static com.example.ecoledesloustics.R.drawable.ic_music0;
import static com.example.ecoledesloustics.R.drawable.ic_music1;
import static com.example.ecoledesloustics.R.drawable.ic_music2;
import static com.example.ecoledesloustics.R.drawable.ic_music3;
import static com.example.ecoledesloustics.R.drawable.ic_music4;
import static com.example.ecoledesloustics.R.drawable.ic_music5;
import static com.example.ecoledesloustics.R.drawable.ic_robot0;
import static com.example.ecoledesloustics.R.drawable.ic_robot1;
import static com.example.ecoledesloustics.R.drawable.ic_robot2;
import static com.example.ecoledesloustics.R.drawable.ic_robot3;
import static com.example.ecoledesloustics.R.drawable.ic_robot4;
import static com.example.ecoledesloustics.R.drawable.ic_robot5;
import static com.example.ecoledesloustics.R.drawable.ic_space0;
import static com.example.ecoledesloustics.R.drawable.ic_space1;
import static com.example.ecoledesloustics.R.drawable.ic_space2;
import static com.example.ecoledesloustics.R.drawable.ic_space3;
import static com.example.ecoledesloustics.R.drawable.ic_space4;
import static com.example.ecoledesloustics.R.drawable.ic_space5;
import static com.example.ecoledesloustics.R.drawable.ic_vegetable0;
import static com.example.ecoledesloustics.R.drawable.ic_vegetable1;
import static com.example.ecoledesloustics.R.drawable.ic_vegetable2;
import static com.example.ecoledesloustics.R.drawable.ic_vegetable3;
import static com.example.ecoledesloustics.R.drawable.ic_vegetable4;
import static com.example.ecoledesloustics.R.drawable.ic_vegetable5;

import java.util.Random;

public class MemoryGameModel {
    private Integer[] images;
    private String title;
    private int firstImage;
    private int secondImage;
    private int numberCorrect = 0;
    private final int totalMoves = 6;

    public MemoryGameModel(String title) {
        this.title = title;
        this.images = makeImagesArray();
    }

    public boolean isCorrect() {
        return firstImage == secondImage;
    }

    public void addGoodAnswer() {
        numberCorrect++;
    }

    public boolean checkWin() {
        return numberCorrect == totalMoves;
    }

    public Integer[] makeImagesArray() {
        if (title.equals("Robots")) {
            images = new Integer[]{ic_robot0, ic_robot1, ic_robot2, ic_robot3, ic_robot4,
                    ic_robot5, ic_robot0, ic_robot1, ic_robot2, ic_robot3, ic_robot4,
                    ic_robot5};
        } else if (title.equals("Animaux")) {
            images = new Integer[]{ic_animal0, ic_animal1, ic_animal2, ic_animal3, ic_animal4,
                    ic_animal5, ic_animal0, ic_animal1, ic_animal2, ic_animal3, ic_animal4,
                    ic_animal5};
        } else if (title.equals("Légumes")) {
            images = new Integer[]{ic_vegetable0, ic_vegetable1, ic_vegetable2, ic_vegetable3, ic_vegetable4,
                    ic_vegetable5, ic_vegetable0, ic_vegetable1, ic_vegetable2, ic_vegetable3, ic_vegetable4,
                    ic_vegetable5};
        } else if (title.equals("Fruits")) {
            images = new Integer[]{ic_fruit0, ic_fruit1, ic_fruit2, ic_fruit3, ic_fruit4,
                    ic_fruit5, ic_fruit0, ic_fruit1, ic_fruit2, ic_fruit3, ic_fruit4,
                    ic_fruit5};
        } else if (title.equals("Space")) {
            images = new Integer[]{ic_space0, ic_space1, ic_space2, ic_space3, ic_space4,
                    ic_space5, ic_space0, ic_space1, ic_space2, ic_space3, ic_space4,
                    ic_space5};
        } else if (title.equals("Music")) {
            images = new Integer[]{ic_music0, ic_music1, ic_music2, ic_music3, ic_music4,
                    ic_music5, ic_music0, ic_music1, ic_music2, ic_music3, ic_music4,
                    ic_music5};
        }
        return shuffleArray(images);
    }

    private Integer[] shuffleArray(Integer[] images) {
        int index;
        Integer temp;
        Random randomGenerator = new Random();

        for (int i = images.length - 1; i > 0; i--) {
            index = randomGenerator.nextInt(i + 1);
            temp = images[index];
            images[index] = images[i];
            images[i] = temp;
        }
        return images;
    }

    public Integer[] getImages() {
        return images;
    }

    public void setImages(Integer[] images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(int firstImage) {
        this.firstImage = firstImage;
    }

    public int getSecondImage() {
        return secondImage;
    }

    public void setSecondImage(int secondImage) {
        this.secondImage = secondImage;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getTotalMoves() {
        return totalMoves;
    }
}
