package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavourDetailModel {
    @SerializedName("info")
    private InfoModel info;
    @SerializedName("medias")
    private List<MediasModel> medias;
    @SerializedName("has_more")
    private boolean hasMore;

    public InfoModel getInfo() {
        return info;
    }

    public void setInfo(InfoModel info) {
        this.info = info;
    }

    public List<MediasModel> getMedias() {
        return medias;
    }

    public void setMedias(List<MediasModel> medias) {
        this.medias = medias;
    }

    public boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public static class InfoModel {
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
        @SerializedName("cover")
        private String cover;
        @SerializedName("upper")
        private UpperModel upper;
        @SerializedName("cover_type")
        private Integer coverType;
        @SerializedName("cnt_info")
        private CntInfoModel cntInfo;
        @SerializedName("type")
        private Integer type;
        @SerializedName("intro")
        private String intro;
        @SerializedName("ctime")
        private long ctime;
        @SerializedName("mtime")
        private long mtime;
        @SerializedName("state")
        private Integer state;
        @SerializedName("fav_state")
        private Integer favState;
        @SerializedName("like_state")
        private Integer likeState;
        @SerializedName("media_count")
        private Integer mediaCount;

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public UpperModel getUpper() {
            return upper;
        }

        public void setUpper(UpperModel upper) {
            this.upper = upper;
        }

        public Integer getCoverType() {
            return coverType;
        }

        public void setCoverType(Integer coverType) {
            this.coverType = coverType;
        }

        public CntInfoModel getCntInfo() {
            return cntInfo;
        }

        public void setCntInfo(CntInfoModel cntInfo) {
            this.cntInfo = cntInfo;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getMtime() {
            return mtime;
        }

        public void setMtime(long mtime) {
            this.mtime = mtime;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Integer getFavState() {
            return favState;
        }

        public void setFavState(Integer favState) {
            this.favState = favState;
        }

        public Integer getLikeState() {
            return likeState;
        }

        public void setLikeState(Integer likeState) {
            this.likeState = likeState;
        }

        public Integer getMediaCount() {
            return mediaCount;
        }

        public void setMediaCount(Integer mediaCount) {
            this.mediaCount = mediaCount;
        }

        public static class UpperModel {
            @SerializedName("mid")
            private long mid;
            @SerializedName("name")
            private String name;
            @SerializedName("face")
            private String face;
            @SerializedName("followed")
            private boolean followed;
            @SerializedName("vip_type")
            private Integer vipType;
            @SerializedName("vip_statue")
            private Integer vipStatue;

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public boolean getFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public Integer getVipType() {
                return vipType;
            }

            public void setVipType(Integer vipType) {
                this.vipType = vipType;
            }

            public Integer getVipStatue() {
                return vipStatue;
            }

            public void setVipStatue(Integer vipStatue) {
                this.vipStatue = vipStatue;
            }
        }

        public static class CntInfoModel {
            @SerializedName("collect")
            private long collect;
            @SerializedName("play")
            private long play;
            @SerializedName("thumb_up")
            private long thumbUp;
            @SerializedName("share")
            private long share;

            public long getCollect() {
                return collect;
            }

            public void setCollect(long collect) {
                this.collect = collect;
            }

            public long getPlay() {
                return play;
            }

            public void setPlay(long play) {
                this.play = play;
            }

            public long getThumbUp() {
                return thumbUp;
            }

            public void setThumbUp(long thumbUp) {
                this.thumbUp = thumbUp;
            }

            public long getShare() {
                return share;
            }

            public void setShare(long share) {
                this.share = share;
            }
        }
    }

    public static class MediasModel {
        @SerializedName("id")
        private long id;
        @SerializedName("type")
        private Integer type;
        @SerializedName("title")
        private String title;
        @SerializedName("cover")
        private String cover;
        @SerializedName("intro")
        private String intro;
        @SerializedName("page")
        private Integer page;
        @SerializedName("duration")
        private long duration;
        @SerializedName("upper")
        private InfoModel.UpperModel upper;
        @SerializedName("attr")
        private Integer attr;
        @SerializedName("cnt_info")
        private CntInfoModel cntInfo;
        @SerializedName("link")
        private String link;
        @SerializedName("ctime")
        private long ctime;
        @SerializedName("pubtime")
        private long pubtime;
        @SerializedName("fav_time")
        private long favTime;
        @SerializedName("bv_id")
        private String bvId;
        @SerializedName("bvid")
        private String bvid;
        @SerializedName("season")
        private Object season;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public InfoModel.UpperModel getUpper() {
            return upper;
        }

        public void setUpper(InfoModel.UpperModel upper) {
            this.upper = upper;
        }

        public Integer getAttr() {
            return attr;
        }

        public void setAttr(Integer attr) {
            this.attr = attr;
        }

        public CntInfoModel getCntInfo() {
            return cntInfo;
        }

        public void setCntInfo(CntInfoModel cntInfo) {
            this.cntInfo = cntInfo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getPubtime() {
            return pubtime;
        }

        public void setPubtime(long pubtime) {
            this.pubtime = pubtime;
        }

        public long getFavTime() {
            return favTime;
        }

        public void setFavTime(long favTime) {
            this.favTime = favTime;
        }

        public String getBvId() {
            return bvId;
        }

        public void setBvId(String bvId) {
            this.bvId = bvId;
        }

        public String getBvid() {
            return bvid;
        }

        public void setBvid(String bvid) {
            this.bvid = bvid;
        }

        public Object getSeason() {
            return season;
        }

        public void setSeason(Object season) {
            this.season = season;
        }

        public static class CntInfoModel {
            @SerializedName("collect")
            private long collect;
            @SerializedName("play")
            private long play;
            @SerializedName("danmaku")
            private long danmaku;

            public long getCollect() {
                return collect;
            }

            public void setCollect(long collect) {
                this.collect = collect;
            }

            public long getPlay() {
                return play;
            }

            public void setPlay(long play) {
                this.play = play;
            }

            public long getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(long danmaku) {
                this.danmaku = danmaku;
            }
        }
    }
}
