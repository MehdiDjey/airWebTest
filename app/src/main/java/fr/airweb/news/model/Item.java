package fr.airweb.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Item implements Parcelable {
    @SerializedName("news")
   private ArrayList<News> news;

    @Override
    public String toString() {
        return "Item{" +
                "news=" + news +
                '}';
    }
    public int getSize() {
        return news.size();
    }
    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    protected Item(Parcel in) {
        news = in.createTypedArrayList(News.CREATOR);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(news);
    }
}
