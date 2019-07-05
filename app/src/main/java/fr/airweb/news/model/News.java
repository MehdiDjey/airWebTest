package fr.airweb.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class News implements Parcelable {
    @SerializedName("nid")
    private  int nid;

    @SerializedName("type")
    private  String  type;

    @SerializedName("title")
    private  String title;

    @SerializedName("picture")
    private  String picture;

    @SerializedName("content")
    private  String content;

    @SerializedName("dateformated")
    private  String dateformated;

    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                ", content='" + content + '\'' +
                ", dateformated='" + dateformated + '\'' +
                '}';
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateformated() {
        return dateformated;
    }

    public void setDateformated(String dateformated) {
        this.dateformated = dateformated;
    }

    protected News(Parcel in) {
        nid = in.readInt();
        type = in.readString();
        title = in.readString();
        picture = in.readString();
        content = in.readString();
        dateformated = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nid);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(picture);
        dest.writeString(content);
        dest.writeString(dateformated);
    }
}
