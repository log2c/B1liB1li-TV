package com.github.log2c.b1lib1li_tv.model;

import java.io.Serializable;
import java.util.List;

public class PlayUrlModel {

    private String from;
    private String result;
    private String message;
    private int quality;
    private String format;
    private int timelength;
    private String accept_format;
    private int video_codecid;
    private String seek_param;
    private String seek_type;
    private List<DUrlModel> durl;
    private DashModel dash;
    private Object high_format;
    private int last_play_time;
    private int last_play_cid;
    private List<String> accept_description;
    private List<Integer> accept_quality;
    private List<SupportFormatsModel> support_formats;

    public List<DUrlModel> getDurl() {
        return durl;
    }

    public void setDurl(List<DUrlModel> durl) {
        this.durl = durl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }

    public String getAccept_format() {
        return accept_format;
    }

    public void setAccept_format(String accept_format) {
        this.accept_format = accept_format;
    }

    public int getVideo_codecid() {
        return video_codecid;
    }

    public void setVideo_codecid(int video_codecid) {
        this.video_codecid = video_codecid;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public void setSeek_param(String seek_param) {
        this.seek_param = seek_param;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public void setSeek_type(String seek_type) {
        this.seek_type = seek_type;
    }

    public DashModel getDash() {
        return dash;
    }

    public void setDash(DashModel dash) {
        this.dash = dash;
    }

    public Object getHigh_format() {
        return high_format;
    }

    public void setHigh_format(Object high_format) {
        this.high_format = high_format;
    }

    public int getLast_play_time() {
        return last_play_time;
    }

    public void setLast_play_time(int last_play_time) {
        this.last_play_time = last_play_time;
    }

    public int getLast_play_cid() {
        return last_play_cid;
    }

    public void setLast_play_cid(int last_play_cid) {
        this.last_play_cid = last_play_cid;
    }

    public List<String> getAccept_description() {
        return accept_description;
    }

    public void setAccept_description(List<String> accept_description) {
        this.accept_description = accept_description;
    }

    public List<Integer> getAccept_quality() {
        return accept_quality;
    }

    public void setAccept_quality(List<Integer> accept_quality) {
        this.accept_quality = accept_quality;
    }

    public List<SupportFormatsModel> getSupport_formats() {
        return support_formats;
    }

    public void setSupport_formats(List<SupportFormatsModel> support_formats) {
        this.support_formats = support_formats;
    }

    public static class DashModel {
        private int duration;
        private double minBufferTime;
        private double min_buffer_time;
        private DolbyModel dolby;
        private Object flac;
        private List<VideoModel> video;
        private List<AudioModel> audio;

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public double getMinBufferTime() {
            return minBufferTime;
        }

        public void setMinBufferTime(double minBufferTime) {
            this.minBufferTime = minBufferTime;
        }

        public double getMin_buffer_time() {
            return min_buffer_time;
        }

        public void setMin_buffer_time(double min_buffer_time) {
            this.min_buffer_time = min_buffer_time;
        }

        public DolbyModel getDolby() {
            return dolby;
        }

        public void setDolby(DolbyModel dolby) {
            this.dolby = dolby;
        }

        public Object getFlac() {
            return flac;
        }

        public void setFlac(Object flac) {
            this.flac = flac;
        }

        public List<VideoModel> getVideo() {
            return video;
        }

        public void setVideo(List<VideoModel> video) {
            this.video = video;
        }

        public List<AudioModel> getAudio() {
            return audio;
        }

        public void setAudio(List<AudioModel> audio) {
            this.audio = audio;
        }

        public static class DolbyModel {
            private int type;
            private Object audio;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getAudio() {
                return audio;
            }

            public void setAudio(Object audio) {
                this.audio = audio;
            }
        }

        public static class VideoModel {
            private int id;
            private String baseUrl;
            private String base_url;
            private int bandwidth;
            private String mimeType;
            private String mime_type;
            private String codecs;
            private int width;
            private int height;
            private String frameRate;
            private String frame_rate;
            private String sar;
            private int startWithSap;
            private int start_with_sap;
            private SegmentBaseModel SegmentBase;
            private SegmentBaseModelX segment_base;
            private int codecid;
            private List<String> backupUrl;
            private List<String> backup_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            public String getBase_url() {
                return base_url;
            }

            public void setBase_url(String base_url) {
                this.base_url = base_url;
            }

            public int getBandwidth() {
                return bandwidth;
            }

            public void setBandwidth(int bandwidth) {
                this.bandwidth = bandwidth;
            }

            public String getMimeType() {
                return mimeType;
            }

            public void setMimeType(String mimeType) {
                this.mimeType = mimeType;
            }

            public String getMime_type() {
                return mime_type;
            }

            public void setMime_type(String mime_type) {
                this.mime_type = mime_type;
            }

            public String getCodecs() {
                return codecs;
            }

            public void setCodecs(String codecs) {
                this.codecs = codecs;
            }

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

            public String getFrameRate() {
                return frameRate;
            }

            public void setFrameRate(String frameRate) {
                this.frameRate = frameRate;
            }

            public String getFrame_rate() {
                return frame_rate;
            }

            public void setFrame_rate(String frame_rate) {
                this.frame_rate = frame_rate;
            }

            public String getSar() {
                return sar;
            }

            public void setSar(String sar) {
                this.sar = sar;
            }

            public int getStartWithSap() {
                return startWithSap;
            }

            public void setStartWithSap(int startWithSap) {
                this.startWithSap = startWithSap;
            }

            public int getStart_with_sap() {
                return start_with_sap;
            }

            public void setStart_with_sap(int start_with_sap) {
                this.start_with_sap = start_with_sap;
            }

            public SegmentBaseModel getSegmentBase() {
                return SegmentBase;
            }

            public void setSegmentBase(SegmentBaseModel SegmentBase) {
                this.SegmentBase = SegmentBase;
            }

            public SegmentBaseModelX getSegment_base() {
                return segment_base;
            }

            public void setSegment_base(SegmentBaseModelX segment_base) {
                this.segment_base = segment_base;
            }

            public int getCodecid() {
                return codecid;
            }

            public void setCodecid(int codecid) {
                this.codecid = codecid;
            }

            public List<String> getBackupUrl() {
                return backupUrl;
            }

            public void setBackupUrl(List<String> backupUrl) {
                this.backupUrl = backupUrl;
            }

            public List<String> getBackup_url() {
                return backup_url;
            }

            public void setBackup_url(List<String> backup_url) {
                this.backup_url = backup_url;
            }

            public static class SegmentBaseModel {
                private String Initialization;
                private String indexRange;

                public String getInitialization() {
                    return Initialization;
                }

                public void setInitialization(String Initialization) {
                    this.Initialization = Initialization;
                }

                public String getIndexRange() {
                    return indexRange;
                }

                public void setIndexRange(String indexRange) {
                    this.indexRange = indexRange;
                }
            }

            public static class SegmentBaseModelX {
                private String initialization;
                private String index_range;

                public String getInitialization() {
                    return initialization;
                }

                public void setInitialization(String initialization) {
                    this.initialization = initialization;
                }

                public String getIndex_range() {
                    return index_range;
                }

                public void setIndex_range(String index_range) {
                    this.index_range = index_range;
                }
            }
        }

        public static class AudioModel {
            private int id;
            private String baseUrl;
            private String base_url;
            private int bandwidth;
            private String mimeType;
            private String mime_type;
            private String codecs;
            private int width;
            private int height;
            private String frameRate;
            private String frame_rate;
            private String sar;
            private int startWithSap;
            private int start_with_sap;
            private SegmentBaseModelXX SegmentBase;
            private SegmentBaseModelXXX segment_base;
            private int codecid;
            private List<String> backupUrl;
            private List<String> backup_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            public String getBase_url() {
                return base_url;
            }

            public void setBase_url(String base_url) {
                this.base_url = base_url;
            }

            public int getBandwidth() {
                return bandwidth;
            }

            public void setBandwidth(int bandwidth) {
                this.bandwidth = bandwidth;
            }

            public String getMimeType() {
                return mimeType;
            }

            public void setMimeType(String mimeType) {
                this.mimeType = mimeType;
            }

            public String getMime_type() {
                return mime_type;
            }

            public void setMime_type(String mime_type) {
                this.mime_type = mime_type;
            }

            public String getCodecs() {
                return codecs;
            }

            public void setCodecs(String codecs) {
                this.codecs = codecs;
            }

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

            public String getFrameRate() {
                return frameRate;
            }

            public void setFrameRate(String frameRate) {
                this.frameRate = frameRate;
            }

            public String getFrame_rate() {
                return frame_rate;
            }

            public void setFrame_rate(String frame_rate) {
                this.frame_rate = frame_rate;
            }

            public String getSar() {
                return sar;
            }

            public void setSar(String sar) {
                this.sar = sar;
            }

            public int getStartWithSap() {
                return startWithSap;
            }

            public void setStartWithSap(int startWithSap) {
                this.startWithSap = startWithSap;
            }

            public int getStart_with_sap() {
                return start_with_sap;
            }

            public void setStart_with_sap(int start_with_sap) {
                this.start_with_sap = start_with_sap;
            }

            public SegmentBaseModelXX getSegmentBase() {
                return SegmentBase;
            }

            public void setSegmentBase(SegmentBaseModelXX SegmentBase) {
                this.SegmentBase = SegmentBase;
            }

            public SegmentBaseModelXXX getSegment_base() {
                return segment_base;
            }

            public void setSegment_base(SegmentBaseModelXXX segment_base) {
                this.segment_base = segment_base;
            }

            public int getCodecid() {
                return codecid;
            }

            public void setCodecid(int codecid) {
                this.codecid = codecid;
            }

            public List<String> getBackupUrl() {
                return backupUrl;
            }

            public void setBackupUrl(List<String> backupUrl) {
                this.backupUrl = backupUrl;
            }

            public List<String> getBackup_url() {
                return backup_url;
            }

            public void setBackup_url(List<String> backup_url) {
                this.backup_url = backup_url;
            }

            public static class SegmentBaseModelXX {
                private String Initialization;
                private String indexRange;

                public String getInitialization() {
                    return Initialization;
                }

                public void setInitialization(String Initialization) {
                    this.Initialization = Initialization;
                }

                public String getIndexRange() {
                    return indexRange;
                }

                public void setIndexRange(String indexRange) {
                    this.indexRange = indexRange;
                }
            }

            public static class SegmentBaseModelXXX {
                private String initialization;
                private String index_range;

                public String getInitialization() {
                    return initialization;
                }

                public void setInitialization(String initialization) {
                    this.initialization = initialization;
                }

                public String getIndex_range() {
                    return index_range;
                }

                public void setIndex_range(String index_range) {
                    this.index_range = index_range;
                }
            }
        }
    }

    public static class DUrlModel implements Serializable {
        private int order;
        private int length;
        private int size;
        private String ahead;
        private String vhead;
        private String url;
        private List<String> backup_url;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getAhead() {
            return ahead;
        }

        public void setAhead(String ahead) {
            this.ahead = ahead;
        }

        public String getVhead() {
            return vhead;
        }

        public void setVhead(String vhead) {
            this.vhead = vhead;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackup_url() {
            return backup_url;
        }

        public void setBackup_url(List<String> backup_url) {
            this.backup_url = backup_url;
        }
    }

    public static class SupportFormatsModel {
        private int quality;
        private String format;
        private String new_description;
        private String display_desc;
        private String superscript;
        private List<String> codecs;

        public int getQuality() {
            return quality;
        }

        public void setQuality(int quality) {
            this.quality = quality;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getNew_description() {
            return new_description;
        }

        public void setNew_description(String new_description) {
            this.new_description = new_description;
        }

        public String getDisplay_desc() {
            return display_desc;
        }

        public void setDisplay_desc(String display_desc) {
            this.display_desc = display_desc;
        }

        public String getSuperscript() {
            return superscript;
        }

        public void setSuperscript(String superscript) {
            this.superscript = superscript;
        }

        public List<String> getCodecs() {
            return codecs;
        }

        public void setCodecs(List<String> codecs) {
            this.codecs = codecs;
        }
    }
}
