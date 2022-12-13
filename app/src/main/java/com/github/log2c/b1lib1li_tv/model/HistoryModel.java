package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryModel {
    @SerializedName("cursor")
    private CursorModel cursor;
    @SerializedName("tab")
    private List<TabModel> tab;
    @SerializedName("list")
    private List<ListModel> list;

    public CursorModel getCursor() {
        return cursor;
    }

    public void setCursor(CursorModel cursor) {
        this.cursor = cursor;
    }

    public List<TabModel> getTab() {
        return tab;
    }

    public void setTab(List<TabModel> tab) {
        this.tab = tab;
    }

    public List<ListModel> getList() {
        return list;
    }

    public void setList(List<ListModel> list) {
        this.list = list;
    }

    public static class CursorModel {
        @SerializedName("max")
        private int max;
        @SerializedName("view_at")
        private int viewAt;
        @SerializedName("business")
        private String business;
        @SerializedName("ps")
        private int ps;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getViewAt() {
            return viewAt;
        }

        public void setViewAt(int viewAt) {
            this.viewAt = viewAt;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public int getPs() {
            return ps;
        }

        public void setPs(int ps) {
            this.ps = ps;
        }
    }

    public static class TabModel {
        @SerializedName("type")
        private String type;
        @SerializedName("name")
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ListModel {
        @SerializedName("title")
        private String title;
        @SerializedName("long_title")
        private String longTitle;
        @SerializedName("cover")
        private String cover;
        @SerializedName("covers")
        private Object covers;
        @SerializedName("uri")
        private String uri;
        @SerializedName("history")
        private HistoryEntityModel history;
        @SerializedName("videos")
        private int videos;
        @SerializedName("author_name")
        private String authorName;
        @SerializedName("author_face")
        private String authorFace;
        @SerializedName("author_mid")
        private int authorMid;
        @SerializedName("view_at")
        private int viewAt;
        @SerializedName("progress")
        private int progress;
        @SerializedName("badge")
        private String badge;
        @SerializedName("show_title")
        private String showTitle;
        @SerializedName("duration")
        private int duration;
        @SerializedName("current")
        private String current;
        @SerializedName("total")
        private int total;
        @SerializedName("new_desc")
        private String newDesc;
        @SerializedName("is_finish")
        private int isFinish;
        @SerializedName("is_fav")
        private int isFav;
        @SerializedName("kid")
        private int kid;
        @SerializedName("tag_name")
        private String tagName;
        @SerializedName("live_status")
        private int liveStatus;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLongTitle() {
            return longTitle;
        }

        public void setLongTitle(String longTitle) {
            this.longTitle = longTitle;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public Object getCovers() {
            return covers;
        }

        public void setCovers(Object covers) {
            this.covers = covers;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public HistoryEntityModel getHistory() {
            return history;
        }

        public void setHistory(HistoryEntityModel history) {
            this.history = history;
        }

        public int getVideos() {
            return videos;
        }

        public void setVideos(int videos) {
            this.videos = videos;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getAuthorFace() {
            return authorFace;
        }

        public void setAuthorFace(String authorFace) {
            this.authorFace = authorFace;
        }

        public int getAuthorMid() {
            return authorMid;
        }

        public void setAuthorMid(int authorMid) {
            this.authorMid = authorMid;
        }

        public int getViewAt() {
            return viewAt;
        }

        public void setViewAt(int viewAt) {
            this.viewAt = viewAt;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public String getBadge() {
            return badge;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }

        public String getShowTitle() {
            return showTitle;
        }

        public void setShowTitle(String showTitle) {
            this.showTitle = showTitle;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getNewDesc() {
            return newDesc;
        }

        public void setNewDesc(String newDesc) {
            this.newDesc = newDesc;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public int getIsFav() {
            return isFav;
        }

        public void setIsFav(int isFav) {
            this.isFav = isFav;
        }

        public int getKid() {
            return kid;
        }

        public void setKid(int kid) {
            this.kid = kid;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(int liveStatus) {
            this.liveStatus = liveStatus;
        }

        public static class HistoryEntityModel {
            @SerializedName("oid")
            private int oid;
            @SerializedName("epid")
            private int epid;
            @SerializedName("bvid")
            private String bvid;
            @SerializedName("page")
            private int page;
            @SerializedName("cid")
            private int cid;
            @SerializedName("part")
            private String part;
            @SerializedName("business")
            private String business;
            @SerializedName("dt")
            private int dt;

            public int getOid() {
                return oid;
            }

            public void setOid(int oid) {
                this.oid = oid;
            }

            public int getEpid() {
                return epid;
            }

            public void setEpid(int epid) {
                this.epid = epid;
            }

            public String getBvid() {
                return bvid;
            }

            public void setBvid(String bvid) {
                this.bvid = bvid;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public String getBusiness() {
                return business;
            }

            public void setBusiness(String business) {
                this.business = business;
            }

            public int getDt() {
                return dt;
            }

            public void setDt(int dt) {
                this.dt = dt;
            }
        }
    }
}
