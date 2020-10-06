package com.dicoding.android.fundamental.githubuserapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Responses implements Parcelable {
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Pojogithub> items;

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    public int getTotalCount(){
        return totalCount;
    }

    public void setIncompleteResults(boolean incompleteResults){
        this.incompleteResults = incompleteResults;
    }

    public boolean isIncompleteResults(){
        return incompleteResults;
    }

    public void setItems(List<Pojogithub> items){
        this.items = items;
    }

    public List<Pojogithub> getItems(){
        return items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalCount);
        dest.writeByte(this.incompleteResults ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.items);
    }

    public Responses() {
    }

    protected Responses(Parcel in) {
        this.totalCount = in.readInt();
        this.incompleteResults = in.readByte() != 0;
        this.items = in.createTypedArrayList(Pojogithub.CREATOR);
    }

    public static final Parcelable.Creator<Responses> CREATOR = new Parcelable.Creator<Responses>() {
        @Override
        public Responses createFromParcel(Parcel source) {
            return new Responses(source);
        }

        @Override
        public Responses[] newArray(int size) {
            return new Responses[size];
        }
    };
}
