package com.github.log2c.b1lib1li_tv.model;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public class ResolutionModel implements Parcelable {
    private String id;
    private String mimeType;
    private String codecs;
    private String frameRate;
    private int width;
    private int height;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getCodecs() {
        return codecs;
    }

    public void setCodecs(String codecs) {
        this.codecs = codecs;
    }

    public String getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.mimeType);
        dest.writeString(this.codecs);
        dest.writeString(this.frameRate);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.mimeType = source.readString();
        this.codecs = source.readString();
        this.frameRate = source.readString();
        this.width = source.readInt();
        this.height = source.readInt();
    }

    public ResolutionModel() {
    }

    protected ResolutionModel(Parcel in) {
        this.id = in.readString();
        this.mimeType = in.readString();
        this.codecs = in.readString();
        this.frameRate = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Parcelable.Creator<ResolutionModel> CREATOR = new Parcelable.Creator<ResolutionModel>() {
        @Override
        public ResolutionModel createFromParcel(Parcel source) {
            return new ResolutionModel(source);
        }

        @Override
        public ResolutionModel[] newArray(int size) {
            return new ResolutionModel[size];
        }
    };
}
