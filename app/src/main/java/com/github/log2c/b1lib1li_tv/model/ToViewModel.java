package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToViewModel {
    @SerializedName("count")
    private int count;
    @SerializedName("list")
    private List<ListModel> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
        private int aid;
        @SerializedName("videos")
        private int videos;
        @SerializedName("tid")
        private int tid;
        @SerializedName("tname")
        private String tname;
        @SerializedName("copyright")
        private int copyright;
        @SerializedName("pic")
        private String pic;
        @SerializedName("title")
        private String title;
        @SerializedName("pubdate")
        private long pubdate;
        @SerializedName("ctime")
        private int ctime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("state")
        private int state;
        @SerializedName("duration")
        private int duration;
        @SerializedName("mission_id")
        private int missionId;
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
        private int seasonId;
        @SerializedName("short_link_v2")
        private String shortLinkV2;
        @SerializedName("first_frame")
        private String firstFrame;
        @SerializedName("pub_location")
        private String pubLocation;
        @SerializedName("page")
        private PageModel page;
        @SerializedName("count")
        private int count;
        @SerializedName("cid")
        private int cid;
        @SerializedName("progress")
        private int progress;
        @SerializedName("add_at")
        private int addAt;
        @SerializedName("bvid")
        private String bvid;
        @SerializedName("uri")
        private String uri;
        @SerializedName("viewed")
        private boolean viewed;
        @SerializedName("up_from_v2")
        private int upFromV2;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getVideos() {
            return videos;
        }

        public void setVideos(int videos) {
            this.videos = videos;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public int getCopyright() {
            return copyright;
        }

        public void setCopyright(int copyright) {
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

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getMissionId() {
            return missionId;
        }

        public void setMissionId(int missionId) {
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

        public int getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(int seasonId) {
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public int getAddAt() {
            return addAt;
        }

        public void setAddAt(int addAt) {
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

        public int getUpFromV2() {
            return upFromV2;
        }

        public void setUpFromV2(int upFromV2) {
            this.upFromV2 = upFromV2;
        }

        public static class RightsModel {
            @SerializedName("bp")
            private int bp;
            @SerializedName("elec")
            private int elec;
            @SerializedName("download")
            private int download;
            @SerializedName("movie")
            private int movie;
            @SerializedName("pay")
            private int pay;
            @SerializedName("hd5")
            private int hd5;
            @SerializedName("no_reprint")
            private int noReprint;
            @SerializedName("autoplay")
            private int autoplay;
            @SerializedName("ugc_pay")
            private int ugcPay;
            @SerializedName("is_cooperation")
            private int isCooperation;
            @SerializedName("ugc_pay_preview")
            private int ugcPayPreview;
            @SerializedName("no_background")
            private int noBackground;
            @SerializedName("arc_pay")
            private int arcPay;
            @SerializedName("pay_free_watch")
            private int payFreeWatch;

            public int getBp() {
                return bp;
            }

            public void setBp(int bp) {
                this.bp = bp;
            }

            public int getElec() {
                return elec;
            }

            public void setElec(int elec) {
                this.elec = elec;
            }

            public int getDownload() {
                return download;
            }

            public void setDownload(int download) {
                this.download = download;
            }

            public int getMovie() {
                return movie;
            }

            public void setMovie(int movie) {
                this.movie = movie;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }

            public int getHd5() {
                return hd5;
            }

            public void setHd5(int hd5) {
                this.hd5 = hd5;
            }

            public int getNoReprint() {
                return noReprint;
            }

            public void setNoReprint(int noReprint) {
                this.noReprint = noReprint;
            }

            public int getAutoplay() {
                return autoplay;
            }

            public void setAutoplay(int autoplay) {
                this.autoplay = autoplay;
            }

            public int getUgcPay() {
                return ugcPay;
            }

            public void setUgcPay(int ugcPay) {
                this.ugcPay = ugcPay;
            }

            public int getIsCooperation() {
                return isCooperation;
            }

            public void setIsCooperation(int isCooperation) {
                this.isCooperation = isCooperation;
            }

            public int getUgcPayPreview() {
                return ugcPayPreview;
            }

            public void setUgcPayPreview(int ugcPayPreview) {
                this.ugcPayPreview = ugcPayPreview;
            }

            public int getNoBackground() {
                return noBackground;
            }

            public void setNoBackground(int noBackground) {
                this.noBackground = noBackground;
            }

            public int getArcPay() {
                return arcPay;
            }

            public void setArcPay(int arcPay) {
                this.arcPay = arcPay;
            }

            public int getPayFreeWatch() {
                return payFreeWatch;
            }

            public void setPayFreeWatch(int payFreeWatch) {
                this.payFreeWatch = payFreeWatch;
            }
        }

        public static class OwnerModel {
            @SerializedName("mid")
            private int mid;
            @SerializedName("name")
            private String name;
            @SerializedName("face")
            private String face;

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
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
            private int aid;
            @SerializedName("view")
            private int view;
            @SerializedName("danmaku")
            private int danmaku;
            @SerializedName("reply")
            private int reply;
            @SerializedName("favorite")
            private int favorite;
            @SerializedName("coin")
            private int coin;
            @SerializedName("share")
            private int share;
            @SerializedName("now_rank")
            private int nowRank;
            @SerializedName("his_rank")
            private int hisRank;
            @SerializedName("like")
            private int like;
            @SerializedName("dislike")
            private int dislike;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public int getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(int danmaku) {
                this.danmaku = danmaku;
            }

            public int getReply() {
                return reply;
            }

            public void setReply(int reply) {
                this.reply = reply;
            }

            public int getFavorite() {
                return favorite;
            }

            public void setFavorite(int favorite) {
                this.favorite = favorite;
            }

            public int getCoin() {
                return coin;
            }

            public void setCoin(int coin) {
                this.coin = coin;
            }

            public int getShare() {
                return share;
            }

            public void setShare(int share) {
                this.share = share;
            }

            public int getNowRank() {
                return nowRank;
            }

            public void setNowRank(int nowRank) {
                this.nowRank = nowRank;
            }

            public int getHisRank() {
                return hisRank;
            }

            public void setHisRank(int hisRank) {
                this.hisRank = hisRank;
            }

            public int getLike() {
                return like;
            }

            public void setLike(int like) {
                this.like = like;
            }

            public int getDislike() {
                return dislike;
            }

            public void setDislike(int dislike) {
                this.dislike = dislike;
            }
        }

        public static class DimensionModel {
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

        public static class PageModel {
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
            private DimensionModelX dimension;
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
    }
}
