package com.github.log2c.b1lib1li_tv.model;

import java.util.List;

public class VideoViewModel {
    private String bvid;
    private int aid;
    private int videos;
    private int tid;
    private String tname;
    private int copyright;
    private String pic;
    private String title;
    private int pubdate;
    private int ctime;
    private String desc;
    private int state;
    private int duration;
    private RightsModel rights;
    private OwnerModel owner;
    private StatModel stat;
    private String dynamic;
    private int cid;
    private DimensionModel dimension;
    private Object premiere;
    private int teenage_mode;
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

    public int getPubdate() {
        return pubdate;
    }

    public void setPubdate(int pubdate) {
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
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

    public int getTeenage_mode() {
        return teenage_mode;
    }

    public void setTeenage_mode(int teenage_mode) {
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
        private int bp;
        private int elec;
        private int download;
        private int movie;
        private int pay;
        private int hd5;
        private int no_reprint;
        private int autoplay;
        private int ugc_pay;
        private int is_cooperation;
        private int ugc_pay_preview;
        private int no_background;
        private int clean_mode;
        private int is_stein_gate;
        private int is_360;
        private int no_share;
        private int arc_pay;
        private int free_watch;

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

        public int getNo_reprint() {
            return no_reprint;
        }

        public void setNo_reprint(int no_reprint) {
            this.no_reprint = no_reprint;
        }

        public int getAutoplay() {
            return autoplay;
        }

        public void setAutoplay(int autoplay) {
            this.autoplay = autoplay;
        }

        public int getUgc_pay() {
            return ugc_pay;
        }

        public void setUgc_pay(int ugc_pay) {
            this.ugc_pay = ugc_pay;
        }

        public int getIs_cooperation() {
            return is_cooperation;
        }

        public void setIs_cooperation(int is_cooperation) {
            this.is_cooperation = is_cooperation;
        }

        public int getUgc_pay_preview() {
            return ugc_pay_preview;
        }

        public void setUgc_pay_preview(int ugc_pay_preview) {
            this.ugc_pay_preview = ugc_pay_preview;
        }

        public int getNo_background() {
            return no_background;
        }

        public void setNo_background(int no_background) {
            this.no_background = no_background;
        }

        public int getClean_mode() {
            return clean_mode;
        }

        public void setClean_mode(int clean_mode) {
            this.clean_mode = clean_mode;
        }

        public int getIs_stein_gate() {
            return is_stein_gate;
        }

        public void setIs_stein_gate(int is_stein_gate) {
            this.is_stein_gate = is_stein_gate;
        }

        public int getIs_360() {
            return is_360;
        }

        public void setIs_360(int is_360) {
            this.is_360 = is_360;
        }

        public int getNo_share() {
            return no_share;
        }

        public void setNo_share(int no_share) {
            this.no_share = no_share;
        }

        public int getArc_pay() {
            return arc_pay;
        }

        public void setArc_pay(int arc_pay) {
            this.arc_pay = arc_pay;
        }

        public int getFree_watch() {
            return free_watch;
        }

        public void setFree_watch(int free_watch) {
            this.free_watch = free_watch;
        }
    }

    public static class OwnerModel {
        private int mid;
        private String name;
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
        private int aid;
        private int view;
        private int danmaku;
        private int reply;
        private int favorite;
        private int coin;
        private int share;
        private int now_rank;
        private int his_rank;
        private int like;
        private int dislike;
        private String evaluation;
        private String argue_msg;

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

        public int getNow_rank() {
            return now_rank;
        }

        public void setNow_rank(int now_rank) {
            this.now_rank = now_rank;
        }

        public int getHis_rank() {
            return his_rank;
        }

        public void setHis_rank(int his_rank) {
            this.his_rank = his_rank;
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
        private int width;
        private int height;
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
            private int type;
            private String id_str;
            private int ai_type;
            private int ai_status;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getId_str() {
                return id_str;
            }

            public void setId_str(String id_str) {
                this.id_str = id_str;
            }

            public int getAi_type() {
                return ai_type;
            }

            public void setAi_type(int ai_type) {
                this.ai_type = ai_type;
            }

            public int getAi_status() {
                return ai_status;
            }

            public void setAi_status(int ai_status) {
                this.ai_status = ai_status;
            }

            public AuthorModel getAuthor() {
                return author;
            }

            public void setAuthor(AuthorModel author) {
                this.author = author;
            }

            public static class AuthorModel {
                private int mid;
                private String name;
                private String sex;
                private String face;
                private String sign;
                private int rank;
                private int birthday;
                private int is_fake_account;
                private int is_deleted;
                private int in_reg_audit;
                private int is_senior_member;

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

                public int getRank() {
                    return rank;
                }

                public void setRank(int rank) {
                    this.rank = rank;
                }

                public int getBirthday() {
                    return birthday;
                }

                public void setBirthday(int birthday) {
                    this.birthday = birthday;
                }

                public int getIs_fake_account() {
                    return is_fake_account;
                }

                public void setIs_fake_account(int is_fake_account) {
                    this.is_fake_account = is_fake_account;
                }

                public int getIs_deleted() {
                    return is_deleted;
                }

                public void setIs_deleted(int is_deleted) {
                    this.is_deleted = is_deleted;
                }

                public int getIn_reg_audit() {
                    return in_reg_audit;
                }

                public void setIn_reg_audit(int in_reg_audit) {
                    this.in_reg_audit = in_reg_audit;
                }

                public int getIs_senior_member() {
                    return is_senior_member;
                }

                public void setIs_senior_member(int is_senior_member) {
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
            private int aid;
            private int type;
            private String desc;
            private int weekly_recommend_num;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getWeekly_recommend_num() {
                return weekly_recommend_num;
            }

            public void setWeekly_recommend_num(int weekly_recommend_num) {
                this.weekly_recommend_num = weekly_recommend_num;
            }
        }
    }

    public static class DescV2Model {
        private String raw_text;
        private int type;
        private int biz_id;

        public String getRaw_text() {
            return raw_text;
        }

        public void setRaw_text(String raw_text) {
            this.raw_text = raw_text;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getBiz_id() {
            return biz_id;
        }

        public void setBiz_id(int biz_id) {
            this.biz_id = biz_id;
        }
    }

    public static class PagesModel {
        private int cid;
        private int page;
        private String from;
        private String part;
        private int duration;
        private String vid;
        private String weblink;
        private DimensionModelX dimension;
        private String first_frame;

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

        public String getFirst_frame() {
            return first_frame;
        }

        public void setFirst_frame(String first_frame) {
            this.first_frame = first_frame;
        }

        public static class DimensionModelX {
            private int width;
            private int height;
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
