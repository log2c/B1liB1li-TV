package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

public class PagelistModel {
    @SerializedName("cid")
    private int cid;
    @SerializedName("page")
    private int page;
    @SerializedName("from")
    private String from;
    @SerializedName("part")
    private String part;
    @SerializedName("duration")
    private int duration;
    @SerializedName("vid")
    private String vid;
    @SerializedName("weblink")
    private String weblink;
    @SerializedName("dimension")
    private DimensionModel dimension;
    @SerializedName("first_frame")
    private String firstFrame;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public DimensionModel getDimension() {
        return dimension;
    }

    public void setDimension(DimensionModel dimension) {
        this.dimension = dimension;
    }

    public String getFirstFrame() {
        return firstFrame;
    }

    public void setFirstFrame(String firstFrame) {
        this.firstFrame = firstFrame;
    }

    public static class DimensionModel {
        /**
         * width : 3840
         * height : 1920
         * rotate : 0
         */

        @SerializedName("width")
        private int width;
        @SerializedName("height")
        private int height;
        @SerializedName("rotate")
        private int rotate;

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

        public int getRotate() {
            return rotate;
        }

        public void setRotate(int rotate) {
            this.rotate = rotate;
        }
    }
}
