package com.github.log2c.b1lib1li_tv.model;

import java.io.Serializable;
import java.util.List;

public class UpFeedModel implements Serializable {
    private java.util.List<ArchivesBean> archives;
    private PageBean page;

    public static class PageBean {
        private long count;
        private long pn;
        private long ps;

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

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
    }

    public static class ArchivesBean {
        private AuthorBean author;
        private String bvid;
        private String pic;
        private long pubdate;
        private String title;
        private String aid;
        private long duration;
        private StatBean stat;

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getBvid() {
            return bvid;
        }

        public void setBvid(String bvid) {
            this.bvid = bvid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public long getPubdate() {
            return pubdate;
        }

        public void setPubdate(long pubdate) {
            this.pubdate = pubdate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public StatBean getStat() {
            return stat;
        }

        public void setStat(StatBean stat) {
            this.stat = stat;
        }

        public static class AuthorBean {
            private String mid;
            private String name;
            private String face;

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

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }
        }

        public static class StatBean {
            private long favorite;
            private long view;
            private long coin;
            private long share;
            private long reply;
            private long danmaku;
            private long like;

            public long getFavorite() {
                return favorite;
            }

            public void setFavorite(long favorite) {
                this.favorite = favorite;
            }

            public long getView() {
                return view;
            }

            public void setView(long view) {
                this.view = view;
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

            public long getLike() {
                return like;
            }

            public void setLike(long like) {
                this.like = like;
            }
        }
    }

    public List<ArchivesBean> getArchives() {
        return archives;
    }

    public void setArchives(List<ArchivesBean> archives) {
        this.archives = archives;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }
}
