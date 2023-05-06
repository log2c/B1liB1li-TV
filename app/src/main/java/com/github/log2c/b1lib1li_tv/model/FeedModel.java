package com.github.log2c.b1lib1li_tv.model;

import java.util.List;

public class FeedModel {
    private boolean has_more;
    private Integer update_num;
    private List<ItemsBean> items;
    private String offset;
    private String update_baseline;

    public boolean getHas_more() {
        return has_more;
    }

    public void setHas_more(Boolean has_more) {
        this.has_more = has_more;
    }

    public Integer getUpdate_num() {
        return update_num;
    }

    public void setUpdate_num(Integer update_num) {
        this.update_num = update_num;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getUpdate_baseline() {
        return update_baseline;
    }

    public void setUpdate_baseline(String update_baseline) {
        this.update_baseline = update_baseline;
    }

    public static class ItemsBean {
        private String bvid;
        private RightsBean rights;
        private OwnerBean owner;
        private Integer cid;
        private String desc;
        private String pic;
        private String title;
        private long ctime;
        private StatBean stat;
        private long duration;
        private String aid;
        private long pubdate;

        public String getBvid() {
            return bvid;
        }

        public void setBvid(String bvid) {
            this.bvid = bvid;
        }

        public RightsBean getRights() {
            return rights;
        }

        public void setRights(RightsBean rights) {
            this.rights = rights;
        }

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
        }

        public Integer getCid() {
            return cid;
        }

        public void setCid(Integer cid) {
            this.cid = cid;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public StatBean getStat() {
            return stat;
        }

        public void setStat(StatBean stat) {
            this.stat = stat;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public long getPubdate() {
            return pubdate;
        }

        public void setPubdate(long pubdate) {
            this.pubdate = pubdate;
        }

        public static class RightsBean {
            private Integer is_cooperation;
            private Integer autoplay;

            public Integer getIs_cooperation() {
                return is_cooperation;
            }

            public void setIs_cooperation(Integer is_cooperation) {
                this.is_cooperation = is_cooperation;
            }

            public Integer getAutoplay() {
                return autoplay;
            }

            public void setAutoplay(Integer autoplay) {
                this.autoplay = autoplay;
            }
        }

        public static class OwnerBean {
            private String face;
            private String mid;
            private String name;

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class StatBean {
            private long vv;
            private long favorite;
            private String aid;
            private Object coin;
            private long like;
            private long reply;
            private long danmaku;
            private long view;

            public long getVv() {
                return vv;
            }

            public void setVv(long vv) {
                this.vv = vv;
            }

            public long getFavorite() {
                return favorite;
            }

            public void setFavorite(long favorite) {
                this.favorite = favorite;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public Object getCoin() {
                return coin;
            }

            public void setCoin(Object coin) {
                this.coin = coin;
            }

            public long getLike() {
                return like;
            }

            public void setLike(long like) {
                this.like = like;
            }

            public long getReply() {
                return reply;
            }

            public void setReply(long reply) {
                this.reply = reply;
            }

            public long getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(long danmaku) {
                this.danmaku = danmaku;
            }

            public long getView() {
                return view;
            }

            public void setView(long view) {
                this.view = view;
            }
        }
    }
}