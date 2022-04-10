package com.example.ecoledesloustics.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizCategoryModel implements Parcelable {
    private long id;
    private int img;
    private int totalQuestions;
    private boolean isTimed;
    private String category;
    private String title;
    private int secondsRemaining;

    public QuizCategoryModel(long id, String title, int img, int totalQuestions,
                             boolean isTimed, int secondsRemaining,
                             String category) {
        this.id = id;
        this.img = img;
        this.totalQuestions = totalQuestions;
        this.isTimed = isTimed;
        this.category = category;
        this.title = title;
        this.secondsRemaining = secondsRemaining;
    }

    protected QuizCategoryModel(Parcel in) {
        id = in.readLong();
        img = in.readInt();
        totalQuestions = in.readInt();
        secondsRemaining = in.readInt();
        isTimed = in.readByte() != 0;
        category = in.readString();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(img);
        dest.writeInt(totalQuestions);
        dest.writeInt(secondsRemaining);
        dest.writeByte((byte) (isTimed ? 1 : 0));
        dest.writeString(category);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuizCategoryModel> CREATOR = new Creator<QuizCategoryModel>() {
        @Override
        public QuizCategoryModel createFromParcel(Parcel in) {
            return new QuizCategoryModel(in);
        }

        @Override
        public QuizCategoryModel[] newArray(int size) {
            return new QuizCategoryModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public boolean isTimed() {
        return isTimed;
    }

    public void setTimed(boolean timed) {
        isTimed = timed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSecondsRemaining() {
        return secondsRemaining;
    }

    public void setSecondsRemaining(int secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }
}
