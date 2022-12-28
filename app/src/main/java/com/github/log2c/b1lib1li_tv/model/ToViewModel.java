package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToViewModel {
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
        @SerializedName("aid")
        private long aid;
        @SerializedName("videos")
        private long videos;
        @SerializedName("tid")
        private long tid;
        @SerializedName("tname")
        private String tname;
        @SerializedName("copyright")
        private long copyright;
        @SerializedName("pic")
        private String pic;
        @SerializedName("title")
        private String title;
        @SerializedName("pubdate")
        private long pubdate;
        @SerializedName("ctime")
        private long ctime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("state")
        private long state;
        @SerializedName("duration")
        private long duration;
        @SerializedName("mission_id")
        private long missionId;
        @SerializedName("rights")
        private RightsModel rights;
        @SerializedName("owner")
        private OwnerModel owner;
        @SerializedName("stat")
        private StatModel stat;
        @SerializedName("dynamic")
        private String dynamic;
        @SerializedName("dimension")
        private DimensionModel dimension;
        @SerializedName("season_id")
        private long seasonId;
        @SerializedName("short_link_v2")
        private String shortLinkV2;
        @SerializedName("first_frame")
        private String firstFrame;
        @SerializedName("pub_location")
        private String pubLocation;
        @SerializedName("page")
        private PageModel page;
        @SerializedName("count")
        private long count;
        @SerializedName("cid")
        private long cid;
        @SerializedName("progress")
        private long progress;
        @SerializedName("add_at")
        private long addAt;
        @SerializedName("bvid")
        private String bvid;
        @SerializedName("uri")
        private String uri;
        @SerializedName("viewed")
        private boolean viewed;
        @SerializedName("up_from_v2")
        private long upFromV2;

        public long getAid() {
            return aid;
        }

        public void setAid(long aid) {
            this.aid = aid;
        }

        public long getVideos() {
            return videos;
        }

        public void setVideos(long videos) {
            this.videos = videos;
        }

        public long getTid() {
            return tid;
        }

        public void setTid(long tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public long getCopyright() {
            return copyright;
        }

        public void setCopyright(long copyright) {
            this.copyright = copyright;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getPubdate() {
            return pubdate;
        }

        public void setPubdate(long pubdate) {
            this.pubdate = pubdate;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public long getMissionId() {
            return missionId;
        }

        public void setMissionId(long missionId) {
            this.missionId = missionId;
        }

        public RightsModel getRights() {
            return rights;
        }

        public void setRights(RightsModel rights) {
            this.rights = rights;
        }

        public OwnerModel getOwner() {
            return owner;
        }

        public void setOwner(OwnerModel owner) {
            this.owner = owner;
        }

        public StatModel getStat() {
            return stat;
        }

        public void setStat(StatModel stat) {
            this.stat = stat;
        }

        public String getDynamic() {
            return dynamic;
        }

        public void setDynamic(String dynamic) {
            this.dynamic = dynamic;
        }

        public DimensionModel getDimension() {
            return dimension;
        }

        public void setDimension(DimensionModel dimension) {
            this.dimension = dimension;
        }

        public long getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(long seasonId) {
            this.seasonId = seasonId;
        }

        public String getShortLinkV2() {
            return shortLinkV2;
        }

        public void setShortLinkV2(String shortLinkV2) {
            this.shortLinkV2 = shortLinkV2;
        }

        public String getFirstFrame() {
            return firstFrame;
        }

        public void setFirstFrame(String firstFrame) {
            this.firstFrame = firstFrame;
        }

        public String getPubLocation() {
            return pubLocation;
        }

        public void setPubLocation(String pubLocation) {
            this.pubLocation = pubLocation;
        }

        public PageModel getPage() {
            return page;
        }

        public void setPage(PageModel page) {
            this.page = page;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public long getCid() {
            return cid;
        }

        public void setCid(long cid) {
            this.cid = cid;
        }

        public long getProgress() {
            return progress;
        }

        public void setProgress(long progress) {
            this.progress = progress;
        }

        public long getAddAt() {
            return addAt;
        }

        public void setAddAt(long addAt) {
            this.addAt = addAt;
        }

        public String getBvid() {
            return bvid;
        }

        public void setBvid(String bvid) {
            this.bvid = bvid;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public boolean isViewed() {
            return viewed;
        }

        public void setViewed(boolean viewed) {
            this.viewed = viewed;
        }

        public long getUpFromV2() {
            return upFromV2;
        }

        public void setUpFromV2(long upFromV2) {
            this.upFromV2 = upFromV2;
        }

        public static class RightsModel {
            @SerializedName("bp")
            private long bp;
            @SerializedName("elec")
            private long elec;
            @SerializedName("download")
            private long download;
            @SerializedName("movie")
            private long movie;
            @SerializedName("pay")
            private long pay;
            @SerializedName("hd5")
            private long hd5;
            @SerializedName("no_reprint")
            private long noReprint;
            @SerializedName("autoplay")
            private long autoplay;
            @SerializedName("ugc_pay")
            private long ugcPay;
            @SerializedName("is_cooperation")
            private long isCooperation;
            @SerializedName("ugc_pay_preview")
            private long ugcPayPreview;
            @SerializedName("no_background")
            private long noBackground;
            @SerializedName("arc_pay")
            private long arcPay;
            @SerializedName("pay_free_watch")
            private long payFreeWatch;

            public long getBp() {
                return bp;
            }

            public void setBp(long bp) {
                this.bp = bp;
            }

            public long getElec() {
                return elec;
            }

            public void setElec(long elec) {
                this.elec = elec;
            }

            public long getDownload() {
                return download;
            }

            public void setDownload(long download) {
                this.download = download;
            }

            public long getMovie() {
                return movie;
            }

            public void setMovie(long movie) {
                this.movie = movie;
            }

            public long getPay() {
                return pay;
            }

            public void setPay(long pay) {
                this.pay = pay;
            }

            public long getHd5() {
                return hd5;
            }

            public void setHd5(long hd5) {
                this.hd5 = hd5;
            }

            public long getNoReprint() {
                return noReprint;
            }

            public void setNoReprint(long noReprint) {
                this.noReprint = noReprint;
            }

            public long getAutoplay() {
                return autoplay;
            }

            public void setAutoplay(long autoplay) {
                this.autoplay = autoplay;
            }

            public long getUgcPay() {
                return ugcPay;
            }

            public void setUgcPay(long ugcPay) {
                this.ugcPay = ugcPay;
            }

            public long getIsCooperation() {
                return isCooperation;
            }

            public void setIsCooperation(long isCooperation) {
                this.isCooperation = isCooperation;
            }

            public long getUgcPayPreview() {
                return ugcPayPreview;
            }

            public void setUgcPayPreview(long ugcPayPreview) {
                this.ugcPayPreview = ugcPayPreview;
            }

            public long getNoBackground() {
                return noBackground;
            }

            public void setNoBackground(long noBackground) {
                this.noBackground = noBackground;
            }

            public long getArcPay() {
                return arcPay;
            }

            public void setArcPay(long arcPay) {
                this.arcPay = arcPay;
            }

            public long getPayFreeWatch() {
                return payFreeWatch;
            }

            public void setPayFreeWatch(long payFreeWatch) {
                this.payFreeWatch = payFreeWatch;
            }
        }

        public static class OwnerModel {
            @SerializedName("mid")
            private long mid;
            @SerializedName("name")
            private String name;
            @SerializedName("face")
            private String face;

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
        }

        public static class StatModel {
            @SerializedName("aid")
            private long aid;
            @SerializedName("view")
            private long view;
            @SerializedName("danmaku")
            private long danmaku;
            @SerializedName("reply")
            private long reply;
            @SerializedName("favorite")
            private long favorite;
            @SerializedName("coin")
            private long coin;
            @SerializedName("share")
            private long share;
            @SerializedName("now_rank")
            private long nowRank;
            @SerializedName("his_rank")
            private long hisRank;
            @SerializedName("like")
            private long like;
            @SerializedName("dislike")
            private long dislike;

            public long getAid() {
                return aid;
            }

            public void setAid(long aid) {
                this.aid = aid;
            }

            public long getView() {
                return view;
            }

            public void setView(long view) {
                this.view = view;
            }

            public long getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(long danmaku) {
                this.danmaku = danmaku;
            }

            public long getReply() {
                return reply;
            }

            public void setReply(long reply) {
                this.reply = reply;
            }

            public long getFavorite() {
                return favorite;
            }

            public void setFavorite(long favorite) {
                this.favorite = favorite;
            }

            public long getCoin() {
                return coin;
            }

            public void setCoin(long coin) {
                this.coin = coin;
            }

            public long getShare() {
                return share;
            }

            public void setShare(long share) {
                this.share = share;
            }

            public long getNowRank() {
                return nowRank;
            }

            public void setNowRank(long nowRank) {
                this.nowRank = nowRank;
            }

            public long getHisRank() {
                return hisRank;
            }

            public void setHisRank(long hisRank) {
                this.hisRank = hisRank;
            }

            public long getLike() {
                return like;
            }

            public void setLike(long like) {
                this.like = like;
            }

            public long getDislike() {
                return dislike;
            }

            public void setDislike(long dislike) {
                this.dislike = dislike;
            }
        }

        public static class DimensionModel {
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

        public static class PageModel {
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
            private DimensionModelX dimension;
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

            public DimensionModelX getDimension() {
                return dimension;
            }

            public void setDimension(DimensionModelX dimension) {
                this.dimension = dimension;
            }

            public String getFirstFrame() {
                return firstFrame;
            }

            public void setFirstFrame(String firstFrame) {
                this.firstFrame = firstFrame;
            }

            public static class DimensionModelX {
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
    }
}
