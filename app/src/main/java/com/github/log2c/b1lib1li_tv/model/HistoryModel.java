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
        private long max;
        @SerializedName("view_at")
        private long viewAt;
        @SerializedName("business")
        private String business;
        @SerializedName("ps")
        private long ps;

        public long getMax() {
            return max;
        }

        public void setMax(long max) {
            this.max = max;
        }

        public long getViewAt() {
            return viewAt;
        }

        public void setViewAt(long viewAt) {
            this.viewAt = viewAt;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public long getPs() {
            return ps;
        }

        public void setPs(long ps) {
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
        private long videos;
        @SerializedName("author_name")
        private String authorName;
        @SerializedName("author_face")
        private String authorFace;
        @SerializedName("author_mid")
        private long authorMid;
        @SerializedName("view_at")
        private long viewAt;
        @SerializedName("progress")
        private long progress;
        @SerializedName("badge")
        private String badge;
        @SerializedName("show_title")
        private String showTitle;
        @SerializedName("duration")
        private long duration;
        @SerializedName("current")
        private String current;
        @SerializedName("total")
        private long total;
        @SerializedName("new_desc")
        private String newDesc;
        @SerializedName("is_finish")
        private long isFinish;
        @SerializedName("is_fav")
        private long isFav;
        @SerializedName("kid")
        private long kid;
        @SerializedName("tag_name")
        private String tagName;
        @SerializedName("live_status")
        private long liveStatus;

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

        public long getVideos() {
            return videos;
        }

        public void setVideos(long videos) {
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

        public long getAuthorMid() {
            return authorMid;
        }

        public void setAuthorMid(long authorMid) {
            this.authorMid = authorMid;
        }

        public long getViewAt() {
            return viewAt;
        }

        public void setViewAt(long viewAt) {
            this.viewAt = viewAt;
        }

        public long getProgress() {
            return progress;
        }

        public void setProgress(long progress) {
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

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public String getNewDesc() {
            return newDesc;
        }

        public void setNewDesc(String newDesc) {
            this.newDesc = newDesc;
        }

        public long getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(long isFinish) {
            this.isFinish = isFinish;
        }

        public long getIsFav() {
            return isFav;
        }

        public void setIsFav(long isFav) {
            this.isFav = isFav;
        }

        public long getKid() {
            return kid;
        }

        public void setKid(long kid) {
            this.kid = kid;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public long getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(long liveStatus) {
            this.liveStatus = liveStatus;
        }

        public static class HistoryEntityModel {
            @SerializedName("oid")
            private long oid;
            @SerializedName("epid")
            private long epid;
            @SerializedName("bvid")
            private String bvid;
            @SerializedName("page")
            private long page;
            @SerializedName("cid")
            private long cid;
            @SerializedName("part")
            private String part;
            @SerializedName("business")
            private String business;
            @SerializedName("dt")
            private long dt;

            public long getOid() {
                return oid;
            }

            public void setOid(long oid) {
                this.oid = oid;
            }

            public long getEpid() {
                return epid;
            }

            public void setEpid(long epid) {
                this.epid = epid;
            }

            public String getBvid() {
                return bvid;
            }

            public void setBvid(String bvid) {
                this.bvid = bvid;
            }

            public long getPage() {
                return page;
            }

            public void setPage(long page) {
                this.page = page;
            }

            public long getCid() {
                return cid;
            }

            public void setCid(long cid) {
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

            public long getDt() {
                return dt;
            }

            public void setDt(long dt) {
                this.dt = dt;
            }
        }
    }
}
