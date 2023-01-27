package com.github.log2c.b1lib1li_tv.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UpFeedModel implements Serializable {
    private ListModel list;
    private PageModel page;
    private EpisodicButtonModel episodic_button;
    private Boolean is_risk;
    private long gaia_res_type;

    public ListModel getList() {
        return list;
    }

    public void setList(ListModel list) {
        this.list = list;
    }

    public PageModel getPage() {
        return page;
    }

    public void setPage(PageModel page) {
        this.page = page;
    }

    public EpisodicButtonModel getEpisodic_button() {
        return episodic_button;
    }

    public void setEpisodic_button(EpisodicButtonModel episodic_button) {
        this.episodic_button = episodic_button;
    }

    public Boolean getIs_risk() {
        return is_risk;
    }

    public void setIs_risk(Boolean is_risk) {
        this.is_risk = is_risk;
    }

    public long getGaia_res_type() {
        return gaia_res_type;
    }

    public void setGaia_res_type(long gaia_res_type) {
        this.gaia_res_type = gaia_res_type;
    }

    public static class ListModel {
        private Map<String, TListModel> tlist;
        private List<VlistModel> vlist;

        public Map<String, TListModel> getTlist() {
            return tlist;
        }

        public void setTlist(Map<String, TListModel> tlist) {
            this.tlist = tlist;
        }

        public List<VlistModel> getVlist() {
            return vlist;
        }

        public void setVlist(List<VlistModel> vlist) {
            this.vlist = vlist;
        }

        public static class TListModel {
            private long tid;
            private long count;
            private String name;

            public long getTid() {
                return tid;
            }

            public void setTid(long tid) {
                this.tid = tid;
            }

            public long getCount() {
                return count;
            }

            public void setCount(long count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class VlistModel {
            private long comment;
            private long typeid;
            private long play;
            private String pic;
            private String subtitle;
            private String description;
            private String copyright;
            private String title;
            private long review;
            private String author;
            private long mid;
            private long created;
            private String length;
            private long video_review;
            private String aid;
            private String bvid;
            private Boolean hide_click;
            private long is_pay;
            private long is_union_video;
            private long is_steins_gate;
            private long is_live_playback;
            private Object meta;
            private long is_avoided;
            private long attribute;

            public long getComment() {
                return comment;
            }

            public void setComment(long comment) {
                this.comment = comment;
            }

            public long getTypeid() {
                return typeid;
            }

            public void setTypeid(long typeid) {
                this.typeid = typeid;
            }

            public long getPlay() {
                return play;
            }

            public void setPlay(long play) {
                this.play = play;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getReview() {
                return review;
            }

            public void setReview(long review) {
                this.review = review;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }

            public long getCreated() {
                return created;
            }

            public void setCreated(long created) {
                this.created = created;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public long getVideo_review() {
                return video_review;
            }

            public void setVideo_review(long video_review) {
                this.video_review = video_review;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getBvid() {
                return bvid;
            }

            public void setBvid(String bvid) {
                this.bvid = bvid;
            }

            public Boolean getHide_click() {
                return hide_click;
            }

            public void setHide_click(Boolean hide_click) {
                this.hide_click = hide_click;
            }

            public long getIs_pay() {
                return is_pay;
            }

            public void setIs_pay(long is_pay) {
                this.is_pay = is_pay;
            }

            public long getIs_union_video() {
                return is_union_video;
            }

            public void setIs_union_video(long is_union_video) {
                this.is_union_video = is_union_video;
            }

            public long getIs_steins_gate() {
                return is_steins_gate;
            }

            public void setIs_steins_gate(long is_steins_gate) {
                this.is_steins_gate = is_steins_gate;
            }

            public long getIs_live_playback() {
                return is_live_playback;
            }

            public void setIs_live_playback(long is_live_playback) {
                this.is_live_playback = is_live_playback;
            }

            public Object getMeta() {
                return meta;
            }

            public void setMeta(Object meta) {
                this.meta = meta;
            }

            public long getIs_avoided() {
                return is_avoided;
            }

            public void setIs_avoided(long is_avoided) {
                this.is_avoided = is_avoided;
            }

            public long getAttribute() {
                return attribute;
            }

            public void setAttribute(long attribute) {
                this.attribute = attribute;
            }
        }
    }

    public static class PageModel {
        private long pn;
        private long ps;
        private long count;

        public long getPn() {
            return pn;
        }

        public void setPn(long pn) {
            this.pn = pn;
        }

        public long getPs() {
            return ps;
        }

        public void setPs(long ps) {
            this.ps = ps;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }
    }

    public static class EpisodicButtonModel {
        private String text;
        private String uri;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
