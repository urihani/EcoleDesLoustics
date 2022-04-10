package com.example.ecoledesloustics.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

public class DashBoardModel implements Parcelable {
    private int img;
    private String title;
    private int progress;

    public DashBoardModel(int img, String title, int progress) {
        this.img = img;
        this.title = title;
        this.progress = progress;
    }

    protected DashBoardModel(Parcel in) {
        img = in.readInt();
        title = in.readString();
        progress = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img);
        dest.writeString(title);
        dest.writeInt(progress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DashBoardModel> CREATOR = new Creator<DashBoardModel>() {
        @Override
        public DashBoardModel createFromParcel(Parcel in) {
            return new DashBoardModel(in);
        }

        @Override
        public DashBoardModel[] newArray(int size) {
            return new DashBoardModel[size];
        }
    };

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
