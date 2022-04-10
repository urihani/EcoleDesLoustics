package com.example.ecoledesloustics.scores;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;

@Entity(tableName = "scores")
public class ScoresTrackerModel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    @ColumnInfo(name = "user_id")
    private long userId;

    @NonNull
    @ColumnInfo(name = "math_completed")
    private ArrayList<Long> mathCompleted;

    @NonNull
    @ColumnInfo(name = "culture_completed")
    private ArrayList<Long> cultureCompleted;

    @NonNull
    @ColumnInfo(name = "geography_completed")
    private ArrayList<Long> geographyCompleted;

    @NonNull
    @ColumnInfo(name = "logic_completed")
    private ArrayList<Long> gamesCompleted;

    @NonNull
    @ColumnInfo(name = "total_math_exercises")
    private int totalOfMathExercises;

    @NonNull
    @ColumnInfo(name = "total_culture_exercises")
    private int totalOfCultureExercises;

    @NonNull
    @ColumnInfo(name = "total_geography_exercises")
    private int totalOfGeographyExercises;

    @NonNull
    @ColumnInfo(name = "total_logic_exercises")
    private int totalOfGamesExercises;

    @NonNull
    @ColumnInfo(name = "math_progress")
    private int mathProgress;

    @NonNull
    @ColumnInfo(name = "culture_progress")
    private int cultureProgress;

    @NonNull
    @ColumnInfo(name = "geography_progress")
    private int geographyProgress;

    @NonNull
    @ColumnInfo(name = "logic_progress")
    private int gamesProgress;

    public ScoresTrackerModel(long userId) {
        this.userId = userId;

        this.mathProgress = 0;
        this.cultureProgress = 0;
        this.geographyProgress = 0;
        this.gamesProgress = 0;

        this.totalOfMathExercises = 0;
        this.totalOfCultureExercises = 0;
        this.totalOfGeographyExercises = 0;
        this.totalOfGamesExercises = 0;

        mathCompleted = new ArrayList<>();
        cultureCompleted = new ArrayList<>();
        geographyCompleted = new ArrayList<>();
        gamesCompleted = new ArrayList<>();
    }

    protected ScoresTrackerModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        userId = in.readLong();

        totalOfMathExercises = in.readInt();
        totalOfCultureExercises = in.readInt();
        totalOfGeographyExercises = in.readInt();
        totalOfGamesExercises = in.readInt();

        mathProgress = in.readInt();
        cultureProgress = in.readInt();
        geographyProgress = in.readInt();
        gamesProgress = in.readInt();

        mathCompleted = (ArrayList<Long>) in.readSerializable();
        cultureCompleted = (ArrayList<Long>) in.readSerializable();
        geographyCompleted = (ArrayList<Long>) in.readSerializable();
        gamesCompleted = (ArrayList<Long>) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeLong(userId);

        dest.writeInt(totalOfMathExercises);
        dest.writeInt(totalOfCultureExercises);
        dest.writeInt(totalOfGeographyExercises);
        dest.writeInt(totalOfGamesExercises);

        dest.writeInt(mathProgress);
        dest.writeInt(cultureProgress);
        dest.writeInt(geographyProgress);
        dest.writeInt(gamesProgress);

        dest.writeSerializable(this.mathCompleted);
        dest.writeSerializable(this.cultureCompleted);
        dest.writeSerializable(this.geographyCompleted);
        dest.writeSerializable(this.gamesCompleted);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScoresTrackerModel> CREATOR = new Creator<ScoresTrackerModel>() {
        @Override
        public ScoresTrackerModel createFromParcel(Parcel in) {
            return new ScoresTrackerModel(in);
        }

        @Override
        public ScoresTrackerModel[] newArray(int size) {
            return new ScoresTrackerModel[size];
        }
    };

    public void computeMathScore(){
        int mathIncrementValue = 100 / totalOfMathExercises;
        if (totalOfMathExercises <= 0){
            mathProgress = 0;
        } else {
            if (mathCompleted.size() == totalOfMathExercises){
                mathProgress = 100;
            } else {
                mathProgress += mathIncrementValue;
            }
        }
    }

    public void computeCultureScore(){
        int cultureIncrementValue = 100 / totalOfCultureExercises;
        if (totalOfCultureExercises <= 0){
            cultureProgress = 0;
        } else {
            if (cultureCompleted.size() == totalOfCultureExercises){
                cultureProgress = 100;
            } else {
                cultureProgress += cultureIncrementValue;
            }
        }
    }

    public void computeGeographyScore(){
        int geographyIncrementValue = 100 / totalOfGeographyExercises;
        if (totalOfGeographyExercises <= 0){
            geographyProgress = 0;
        } else {
            if (geographyCompleted.size() == totalOfGeographyExercises){
                geographyProgress = 100;
            } else {
                geographyProgress += geographyIncrementValue;
            }
        }
    }

    public void computeGamesScore(){
        int logicIncrementValue = 100 / totalOfGamesExercises;
        if (totalOfGamesExercises <= 0){
            gamesProgress = 0;
        } else {
            if (gamesCompleted.size() == totalOfGamesExercises){
                gamesProgress = 100;
            } else {
                gamesProgress += logicIncrementValue;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @NonNull
    public ArrayList<Long> getMathCompleted() {
        return mathCompleted;
    }

    public void setMathCompleted(@NonNull ArrayList<Long> mathCompleted) {
        this.mathCompleted = mathCompleted;
    }

    public int getTotalOfMathExercises() {
        return totalOfMathExercises;
    }

    public void setTotalOfMathExercises(int totalOfMathExercises) {
        this.totalOfMathExercises = totalOfMathExercises;
    }

    public int getMathProgress() {
        return mathProgress;
    }

    public void setMathProgress(int mathProgress) {
        this.mathProgress = mathProgress;
    }

    @NonNull
    public ArrayList<Long> getCultureCompleted() {
        return cultureCompleted;
    }

    public void setCultureCompleted(@NonNull ArrayList<Long> cultureCompleted) {
        this.cultureCompleted = cultureCompleted;
    }

    public int getTotalOfCultureExercises() {
        return totalOfCultureExercises;
    }

    public void setTotalOfCultureExercises(int totalOfCultureExercises) {
        this.totalOfCultureExercises = totalOfCultureExercises;
    }

    public int getCultureProgress() {
        return cultureProgress;
    }

    public void setCultureProgress(int cultureProgress) {
        this.cultureProgress = cultureProgress;
    }

    @NonNull
    public ArrayList<Long> getGeographyCompleted() {
        return geographyCompleted;
    }

    public void setGeographyCompleted(@NonNull ArrayList<Long> geographyCompleted) {
        this.geographyCompleted = geographyCompleted;
    }

    @NonNull
    public ArrayList<Long> getGamesCompleted() {
        return gamesCompleted;
    }

    public void setGamesCompleted(@NonNull ArrayList<Long> gamesCompleted) {
        this.gamesCompleted = gamesCompleted;
    }

    public int getTotalOfGeographyExercises() {
        return totalOfGeographyExercises;
    }

    public void setTotalOfGeographyExercises(int totalOfGeographyExercises) {
        this.totalOfGeographyExercises = totalOfGeographyExercises;
    }

    public int getTotalOfGamesExercises() {
        return totalOfGamesExercises;
    }

    public void setTotalOfGamesExercises(int totalOfGamesExercises) {
        this.totalOfGamesExercises = totalOfGamesExercises;
    }

    public int getGeographyProgress() {
        return geographyProgress;
    }

    public void setGeographyProgress(int geographyProgress) {
        this.geographyProgress = geographyProgress;
    }

    public int getGamesProgress() {
        return gamesProgress;
    }

    public void setGamesProgress(int gamesProgress) {
        this.gamesProgress = gamesProgress;
    }
}
