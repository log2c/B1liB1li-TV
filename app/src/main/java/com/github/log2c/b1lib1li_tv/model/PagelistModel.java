package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

public class PagelistModel {
    @SerializedName("cid")
    private long cid;
    @SerializedName("page")
    private long page;
    @SerializedName("from")
    private String from;
    @SerializedName("part")
    private String part;
    @SerializedName("duration")
    private long duration;
    @SerializedName("vid")
    private String vid;
    @SerializedName("weblink")
    private String weblink;
    @SerializedName("dimension")
    private DimensionModel dimension;
    @SerializedName("first_frame")
    private String firstFrame;

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
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
        private long width;
        @SerializedName("height")
        private long height;
        @SerializedName("rotate")
        private long rotate;

        public long getWidth() {
            return width;
        }

        public void setWidth(long width) {
            this.width = width;
        }

        public long getHeight() {
            return height;
        }

        public void setHeight(long height) {
            this.height = height;
        }

        public long getRotate() {
            return rotate;
        }

        public void setRotate(long rotate) {
            this.rotate = rotate;
        }
    }
}
