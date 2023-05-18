package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavourListModel {
    @SerializedName("count")
    private long count;
    @SerializedName("list")
    private List<ListModel> list;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<ListModel> getList() {
        return list;
    }

    public void setList(List<ListModel> list) {
        this.list = list;
    }

    public static class ListModel {
        @SerializedName("id")
        private long id;
        @SerializedName("fid")
        private long fid;
        @SerializedName("mid")
        private long mid;
        @SerializedName("attr")
        private long attr;
        @SerializedName("title")
        private String title;
        @SerializedName("fav_state")
        private long favState;
        @SerializedName("media_count")
        private long mediaCount;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getFid() {
            return fid;
        }

        public void setFid(long fid) {
            this.fid = fid;
        }

        public long getMid() {
            return mid;
        }

        public void setMid(long mid) {
            this.mid = mid;
        }

        public long getAttr() {
            return attr;
        }

        public void setAttr(long attr) {
            this.attr = attr;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getFavState() {
            return favState;
        }

        public void setFavState(long favState) {
            this.favState = favState;
        }

        public long getMediaCount() {
            return mediaCount;
        }

        public void setMediaCount(long mediaCount) {
            this.mediaCount = mediaCount;
        }
    }
}
