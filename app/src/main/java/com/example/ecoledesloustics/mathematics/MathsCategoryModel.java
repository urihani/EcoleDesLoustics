package com.example.ecoledesloustics.mathematics;

import android.os.Parcel;
import android.os.Parcelable;

public class MathsCategoryModel implements Parcelable {
    private long id;
    private int img;
    private int firstUpperLimit;
    private int secondUpperLimit;
    private int totalQuestions;
    private boolean isTimed;
    private String category;
    private int secondsRemaining;

    public MathsCategoryModel(long id, int img, int firstUpperLimit, int secondUpperLimit,
                              int totalQuestions, boolean isTimed, int secondsRemaining
            , String category) {
        this.img = img;
        this.firstUpperLimit = firstUpperLimit;
        this.secondUpperLimit = secondUpperLimit;
        this.totalQuestions = totalQuestions;
        this.isTimed = isTimed;
        this.category = category;
        this.id = id;
        this.secondsRemaining = secondsRemaining;
    }

    protected MathsCategoryModel(Parcel in) {
        id = in.readLong();
        img = in.readInt();
        firstUpperLimit = in.readInt();
        secondUpperLimit = in.readInt();
        totalQuestions = in.readInt();
        secondsRemaining = in.readInt();
        isTimed = in.readByte() != 0;
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(img);
        dest.writeInt(firstUpperLimit);
        dest.writeInt(secondUpperLimit);
        dest.writeInt(totalQuestions);
        dest.writeInt(secondsRemaining);
        dest.writeByte((byte) (isTimed ? 1 : 0));
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MathsCategoryModel> CREATOR = new Creator<MathsCategoryModel>() {
        @Override
        public MathsCategoryModel createFromParcel(Parcel in) {
            return new MathsCategoryModel(in);
        }

        @Override
        public MathsCategoryModel[] newArray(int size) {
            return new MathsCategoryModel[size];
        }
    };

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSecondsRemaining() {
        return secondsRemaining;
    }

    public void setSecondsRemaining(int secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }
}
