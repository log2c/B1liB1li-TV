package com.github.log2c.b1lib1li_tv.model;

import java.util.List;

public class VideoViewModel {
    private String bvid;
    private long aid;
    private long videos;
    private long tid;
    private String tname;
    private long copyright;
    private String pic;
    private String title;
    private long pubdate;
    private long ctime;
    private String desc;
    private long state;
    private long duration;
    private RightsModel rights;
    private OwnerModel owner;
    private StatModel stat;
    private String dynamic;
    private long cid;
    private DimensionModel dimension;
    private Object premiere;
    private long teenage_mode;
    private boolean is_chargeable_season;
    private boolean is_story;
    private boolean no_cache;
    private SubtitleModel subtitle;
    private boolean is_season_display;
    private UserGarbModel user_garb;
    private HonorReplyModel honor_reply;
    private String like_icon;
    private List<DescV2Model> desc_v2;
    private List<PagesModel> pages;

    public String getBvid() {
        return bvid;
    }

    public void setBvid(String bvid) {
        this.bvid = bvid;
    }

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

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public DimensionModel getDimension() {
        return dimension;
    }

    public void setDimension(DimensionModel dimension) {
        this.dimension = dimension;
    }

    public Object getPremiere() {
        return premiere;
    }

    public void setPremiere(Object premiere) {
        this.premiere = premiere;
    }

    public long getTeenage_mode() {
        return teenage_mode;
    }

    public void setTeenage_mode(long teenage_mode) {
        this.teenage_mode = teenage_mode;
    }

    public boolean isIs_chargeable_season() {
        return is_chargeable_season;
    }

    public void setIs_chargeable_season(boolean is_chargeable_season) {
        this.is_chargeable_season = is_chargeable_season;
    }

    public boolean isIs_story() {
        return is_story;
    }

    public void setIs_story(boolean is_story) {
        this.is_story = is_story;
    }

    public boolean isNo_cache() {
        return no_cache;
    }

    public void setNo_cache(boolean no_cache) {
        this.no_cache = no_cache;
    }

    public SubtitleModel getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(SubtitleModel subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isIs_season_display() {
        return is_season_display;
    }

    public void setIs_season_display(boolean is_season_display) {
        this.is_season_display = is_season_display;
    }

    public UserGarbModel getUser_garb() {
        return user_garb;
    }

    public void setUser_garb(UserGarbModel user_garb) {
        this.user_garb = user_garb;
    }

    public HonorReplyModel getHonor_reply() {
        return honor_reply;
    }

    public void setHonor_reply(HonorReplyModel honor_reply) {
        this.honor_reply = honor_reply;
    }

    public String getLike_icon() {
        return like_icon;
    }

    public void setLike_icon(String like_icon) {
        this.like_icon = like_icon;
    }

    public List<DescV2Model> getDesc_v2() {
        return desc_v2;
    }

    public void setDesc_v2(List<DescV2Model> desc_v2) {
        this.desc_v2 = desc_v2;
    }

    public List<PagesModel> getPages() {
        return pages;
    }

    public void setPages(List<PagesModel> pages) {
        this.pages = pages;
    }

    public static class RightsModel {
        private long bp;
        private long elec;
        private long download;
        private long movie;
        private long pay;
        private long hd5;
        private long no_reprint;
        private long autoplay;
        private long ugc_pay;
        private long is_cooperation;
        private long ugc_pay_preview;
        private long no_background;
        private long clean_mode;
        private long is_stein_gate;
        private long is_360;
        private long no_share;
        private long arc_pay;
        private long free_watch;

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

        public long getNo_reprint() {
            return no_reprint;
        }

        public void setNo_reprint(long no_reprint) {
            this.no_reprint = no_reprint;
        }

        public long getAutoplay() {
            return autoplay;
        }

        public void setAutoplay(long autoplay) {
            this.autoplay = autoplay;
        }

        public long getUgc_pay() {
            return ugc_pay;
        }

        public void setUgc_pay(long ugc_pay) {
            this.ugc_pay = ugc_pay;
        }

        public long getIs_cooperation() {
            return is_cooperation;
        }

        public void setIs_cooperation(long is_cooperation) {
            this.is_cooperation = is_cooperation;
        }

        public long getUgc_pay_preview() {
            return ugc_pay_preview;
        }

        public void setUgc_pay_preview(long ugc_pay_preview) {
            this.ugc_pay_preview = ugc_pay_preview;
        }

        public long getNo_background() {
            return no_background;
        }

        public void setNo_background(long no_background) {
            this.no_background = no_background;
        }

        public long getClean_mode() {
            return clean_mode;
        }

        public void setClean_mode(long clean_mode) {
            this.clean_mode = clean_mode;
        }

        public long getIs_stein_gate() {
            return is_stein_gate;
        }

        public void setIs_stein_gate(long is_stein_gate) {
            this.is_stein_gate = is_stein_gate;
        }

        public long getIs_360() {
            return is_360;
        }

        public void setIs_360(long is_360) {
            this.is_360 = is_360;
        }

        public long getNo_share() {
            return no_share;
        }

        public void setNo_share(long no_share) {
            this.no_share = no_share;
        }

        public long getArc_pay() {
            return arc_pay;
        }

        public void setArc_pay(long arc_pay) {
            this.arc_pay = arc_pay;
        }

        public long getFree_watch() {
            return free_watch;
        }

        public void setFree_watch(long free_watch) {
            this.free_watch = free_watch;
        }
    }

    public static class OwnerModel {
        private long mid;
        private String name;
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
        private long aid;
        private long view;
        private long danmaku;
        private long reply;
        private long favorite;
        private long coin;
        private long share;
        private long now_rank;
        private long his_rank;
        private long like;
        private long dislike;
        private String evaluation;
        private String argue_msg;

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

        public long getNow_rank() {
            return now_rank;
        }

        public void setNow_rank(long now_rank) {
            this.now_rank = now_rank;
        }

        public long getHis_rank() {
            return his_rank;
        }

        public void setHis_rank(long his_rank) {
            this.his_rank = his_rank;
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

        public String getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(String evaluation) {
            this.evaluation = evaluation;
        }

        public String getArgue_msg() {
            return argue_msg;
        }

        public void setArgue_msg(String argue_msg) {
            this.argue_msg = argue_msg;
        }
    }

    public static class DimensionModel {
        private long width;
        private long height;
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

    public static class SubtitleModel {
        private boolean allow_submit;
        private List<ListModel> list;

        public boolean isAllow_submit() {
            return allow_submit;
        }

        public void setAllow_submit(boolean allow_submit) {
            this.allow_submit = allow_submit;
        }

        public List<ListModel> getList() {
            return list;
        }

        public void setList(List<ListModel> list) {
            this.list = list;
        }

        public static class ListModel {
            private long id;
            private String lan;
            private String lan_doc;
            private boolean is_lock;
            private String subtitle_url;
            private long type;
            private String id_str;
            private long ai_type;
            private long ai_status;
            private AuthorModel author;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getLan() {
                return lan;
            }

            public void setLan(String lan) {
                this.lan = lan;
            }

            public String getLan_doc() {
                return lan_doc;
            }

            public void setLan_doc(String lan_doc) {
                this.lan_doc = lan_doc;
            }

            public boolean isIs_lock() {
                return is_lock;
            }

            public void setIs_lock(boolean is_lock) {
                this.is_lock = is_lock;
            }

            public String getSubtitle_url() {
                return subtitle_url;
            }

            public void setSubtitle_url(String subtitle_url) {
                this.subtitle_url = subtitle_url;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public String getId_str() {
                return id_str;
            }

            public void setId_str(String id_str) {
                this.id_str = id_str;
            }

            public long getAi_type() {
                return ai_type;
            }

            public void setAi_type(long ai_type) {
                this.ai_type = ai_type;
            }

            public long getAi_status() {
                return ai_status;
            }

            public void setAi_status(long ai_status) {
                this.ai_status = ai_status;
            }

            public AuthorModel getAuthor() {
                return author;
            }

            public void setAuthor(AuthorModel author) {
                this.author = author;
            }

            public static class AuthorModel {
                private long mid;
                private String name;
                private String sex;
                private String face;
                private String sign;
                private long rank;
                private long birthday;
                private long is_fake_account;
                private long is_deleted;
                private long in_reg_audit;
                private long is_senior_member;

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

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getFace() {
                    return face;
                }

                public void setFace(String face) {
                    this.face = face;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public long getRank() {
                    return rank;
                }

                public void setRank(long rank) {
                    this.rank = rank;
                }

                public long getBirthday() {
                    return birthday;
                }

                public void setBirthday(long birthday) {
                    this.birthday = birthday;
                }

                public long getIs_fake_account() {
                    return is_fake_account;
                }

                public void setIs_fake_account(long is_fake_account) {
                    this.is_fake_account = is_fake_account;
                }

                public long getIs_deleted() {
                    return is_deleted;
                }

                public void setIs_deleted(long is_deleted) {
                    this.is_deleted = is_deleted;
                }

                public long getIn_reg_audit() {
                    return in_reg_audit;
                }

                public void setIn_reg_audit(long in_reg_audit) {
                    this.in_reg_audit = in_reg_audit;
                }

                public long getIs_senior_member() {
                    return is_senior_member;
                }

                public void setIs_senior_member(long is_senior_member) {
                    this.is_senior_member = is_senior_member;
                }
            }
        }
    }

    public static class UserGarbModel {
        private String url_image_ani_cut;

        public String getUrl_image_ani_cut() {
            return url_image_ani_cut;
        }

        public void setUrl_image_ani_cut(String url_image_ani_cut) {
            this.url_image_ani_cut = url_image_ani_cut;
        }
    }

    public static class HonorReplyModel {
        private List<HonorModel> honor;

        public List<HonorModel> getHonor() {
            return honor;
        }

        public void setHonor(List<HonorModel> honor) {
            this.honor = honor;
        }

        public static class HonorModel {
            private long aid;
            private long type;
            private String desc;
            private long weekly_recommend_num;

            public long getAid() {
                return aid;
            }

            public void setAid(long aid) {
                this.aid = aid;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public long getWeekly_recommend_num() {
                return weekly_recommend_num;
            }

            public void setWeekly_recommend_num(long weekly_recommend_num) {
                this.weekly_recommend_num = weekly_recommend_num;
            }
        }
    }

    public static class DescV2Model {
        private String raw_text;
        private long type;
        private long biz_id;

        public String getRaw_text() {
            return raw_text;
        }

        public void setRaw_text(String raw_text) {
            this.raw_text = raw_text;
        }

        public long getType() {
            return type;
        }

        public void setType(long type) {
            this.type = type;
        }

        public long getBiz_id() {
            return biz_id;
        }

        public void setBiz_id(long biz_id) {
            this.biz_id = biz_id;
        }
    }

    public static class PagesModel {
        private long cid;
        private long page;
        private String from;
        private String part;
        private long duration;
        private String vid;
        private String weblink;
        private DimensionModelX dimension;
        private String first_frame;

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

        public String getFirst_frame() {
            return first_frame;
        }

        public void setFirst_frame(String first_frame) {
            this.first_frame = first_frame;
        }

        public static class DimensionModelX {
            private long width;
            private long height;
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
