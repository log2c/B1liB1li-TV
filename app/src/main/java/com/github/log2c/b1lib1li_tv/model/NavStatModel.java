package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NavStatModel implements Serializable {
    private long following;
    private long follower;
    @SerializedName("dynamic_count")
    private long dynamicCount;

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getFollower() {
        return follower;
    }

    public void setFollower(long follower) {
        this.follower = follower;
    }

    public long getDynamicCount() {
        return dynamicCount;
    }

    public void setDynamicCount(long dynamicCount) {
        this.dynamicCount = dynamicCount;
    }
}
