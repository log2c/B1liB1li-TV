package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

public class RelationTagModel {
    @SerializedName("tagid")
    private int tagId;
    @SerializedName("name")
    private String name;
    @SerializedName("count")
    private int count;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
