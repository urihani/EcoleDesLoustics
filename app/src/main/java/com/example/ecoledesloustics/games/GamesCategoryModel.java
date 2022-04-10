package com.example.ecoledesloustics.games;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ecoledesloustics.mathematics.MathsCategoryModel;

public class GamesCategoryModel implements Parcelable {
    private long id;
    private int img;
    private boolean isTimed;
    private String category;
    private int secondsRemaining;
    private String title;

    public GamesCategoryModel(long id, int img, String title, boolean isTimed, int secondsRemaining,
                              String category) {
        this.img = img;
        this.isTimed = isTimed;
        this.category = category;
        this.id = id;
        this.secondsRemaining = secondsRemaining;
        this.title = title;
    }

    protected GamesCategoryModel(Parcel in) {
        id = in.readLong();
        img = in.readInt();
        isTimed = in.readByte() != 0;
        category = in.readString();
        title = in.readString();
        secondsRemaining = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(img);
        dest.writeByte((byte) (isTimed ? 1 : 0));
        dest.writeString(category);
        dest.writeString(title);
        dest.writeInt(secondsRemaining);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GamesCategoryModel> CREATOR = new Creator<GamesCategoryModel>() {
        @Override
        public GamesCategoryModel createFromParcel(Parcel in) {
            return new GamesCategoryModel(in);
        }

        @Override
        public GamesCategoryModel[] newArray(int size) {
            return new GamesCategoryModel[size];
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
