package com.github.log2c.b1lib1li_tv.model;

import java.io.Serializable;
import java.util.List;

public class UpFeedModel implements Serializable {
    private boolean has_more;
    private String offset;
    private String update_baseline;
    private int update_num;
    private List<ItemsModel> items;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
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

    public int getUpdate_num() {
        return update_num;
    }

    public void setUpdate_num(int update_num) {
        this.update_num = update_num;
    }

    public List<ItemsModel> getItems() {
        return items;
    }

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }

    public static class ItemsModel implements Serializable {
        private BasicModel basic;
        private String id_str;
        private ModulesModel modules;
        private String type;
        private boolean visible;
        private OrigModel orig;

        public BasicModel getBasic() {
            return basic;
        }

        public void setBasic(BasicModel basic) {
            this.basic = basic;
        }

        public String getId_str() {
            return id_str;
        }

        public void setId_str(String id_str) {
            this.id_str = id_str;
        }

        public ModulesModel getModules() {
            return modules;
        }

        public void setModules(ModulesModel modules) {
            this.modules = modules;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public OrigModel getOrig() {
            return orig;
        }

        public void setOrig(OrigModel orig) {
            this.orig = orig;
        }

        public static class BasicModel implements Serializable {
            private String comment_id_str;
            private int comment_type;
            private LikeIconModel like_icon;
            private String rid_str;

            public String getComment_id_str() {
                return comment_id_str;
            }

            public void setComment_id_str(String comment_id_str) {
                this.comment_id_str = comment_id_str;
            }

            public int getComment_type() {
                return comment_type;
            }

            public void setComment_type(int comment_type) {
                this.comment_type = comment_type;
            }

            public LikeIconModel getLike_icon() {
                return like_icon;
            }

            public void setLike_icon(LikeIconModel like_icon) {
                this.like_icon = like_icon;
            }

            public String getRid_str() {
                return rid_str;
            }

            public void setRid_str(String rid_str) {
                this.rid_str = rid_str;
            }

            public static class LikeIconModel implements Serializable {
                private String action_url;
                private String end_url;
                private int id;
                private String start_url;

                public String getAction_url() {
                    return action_url;
                }

                public void setAction_url(String action_url) {
                    this.action_url = action_url;
                }

                public String getEnd_url() {
                    return end_url;
                }

                public void setEnd_url(String end_url) {
                    this.end_url = end_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_url() {
                    return start_url;
                }

                public void setStart_url(String start_url) {
                    this.start_url = start_url;
                }
            }
        }

        public static class ModulesModel implements Serializable {
            private ModuleAuthorModel module_author;
            private ModuleDynamicModel module_dynamic;
            private ModuleMoreModel module_more;
            private ModuleStatModel module_stat;

            public ModuleAuthorModel getModule_author() {
                return module_author;
            }

            public void setModule_author(ModuleAuthorModel module_author) {
                this.module_author = module_author;
            }

            public ModuleDynamicModel getModule_dynamic() {
                return module_dynamic;
            }

            public void setModule_dynamic(ModuleDynamicModel module_dynamic) {
                this.module_dynamic = module_dynamic;
            }

            public ModuleMoreModel getModule_more() {
                return module_more;
            }

            public void setModule_more(ModuleMoreModel module_more) {
                this.module_more = module_more;
            }

            public ModuleStatModel getModule_stat() {
                return module_stat;
            }

            public void setModule_stat(ModuleStatModel module_stat) {
                this.module_stat = module_stat;
            }

            public static class ModuleAuthorModel implements Serializable {
                private String face;
                private boolean face_nft;
                private boolean following;
                private String jump_url;
                private String label;
                private int mid;
                private String name;
                private OfficialVerifyModel official_verify;
                private PendantModel pendant;
                private String pub_action;
                private String pub_location_text;
                private String pub_time;
                private int pub_ts;
                private String type;
                private VipModel vip;

                public String getFace() {
                    return face;
                }

                public void setFace(String face) {
                    this.face = face;
                }

                public boolean isFace_nft() {
                    return face_nft;
                }

                public void setFace_nft(boolean face_nft) {
                    this.face_nft = face_nft;
                }

                public boolean isFollowing() {
                    return following;
                }

                public void setFollowing(boolean following) {
                    this.following = following;
                }

                public String getJump_url() {
                    return jump_url;
                }

                public void setJump_url(String jump_url) {
                    this.jump_url = jump_url;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

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

                public OfficialVerifyModel getOfficial_verify() {
                    return official_verify;
                }

                public void setOfficial_verify(OfficialVerifyModel official_verify) {
                    this.official_verify = official_verify;
                }

                public PendantModel getPendant() {
                    return pendant;
                }

                public void setPendant(PendantModel pendant) {
                    this.pendant = pendant;
                }

                public String getPub_action() {
                    return pub_action;
                }

                public void setPub_action(String pub_action) {
                    this.pub_action = pub_action;
                }

                public String getPub_location_text() {
                    return pub_location_text;
                }

                public void setPub_location_text(String pub_location_text) {
                    this.pub_location_text = pub_location_text;
                }

                public String getPub_time() {
                    return pub_time;
                }

                public void setPub_time(String pub_time) {
                    this.pub_time = pub_time;
                }

                public int getPub_ts() {
                    return pub_ts;
                }

                public void setPub_ts(int pub_ts) {
                    this.pub_ts = pub_ts;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public VipModel getVip() {
                    return vip;
                }

                public void setVip(VipModel vip) {
                    this.vip = vip;
                }

                public static class OfficialVerifyModel implements Serializable {
                    private String desc;
                    private int type;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }

                public static class PendantModel implements Serializable {
                    private int expire;
                    private String image;
                    private String image_enhance;
                    private String image_enhance_frame;
                    private String name;
                    private int pid;

                    public int getExpire() {
                        return expire;
                    }

                    public void setExpire(int expire) {
                        this.expire = expire;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getImage_enhance() {
                        return image_enhance;
                    }

                    public void setImage_enhance(String image_enhance) {
                        this.image_enhance = image_enhance;
                    }

                    public String getImage_enhance_frame() {
                        return image_enhance_frame;
                    }

                    public void setImage_enhance_frame(String image_enhance_frame) {
                        this.image_enhance_frame = image_enhance_frame;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getPid() {
                        return pid;
                    }

                    public void setPid(int pid) {
                        this.pid = pid;
                    }
                }

                public static class VipModel implements Serializable {
                    private int avatar_subscript;
                    private String avatar_subscript_url;
                    private long due_date;
                    private LabelModel label;
                    private String nickname_color;
                    private int status;
                    private int theme_type;
                    private int type;

                    public int getAvatar_subscript() {
                        return avatar_subscript;
                    }

                    public void setAvatar_subscript(int avatar_subscript) {
                        this.avatar_subscript = avatar_subscript;
                    }

                    public String getAvatar_subscript_url() {
                        return avatar_subscript_url;
                    }

                    public void setAvatar_subscript_url(String avatar_subscript_url) {
                        this.avatar_subscript_url = avatar_subscript_url;
                    }

                    public long getDue_date() {
                        return due_date;
                    }

                    public void setDue_date(long due_date) {
                        this.due_date = due_date;
                    }

                    public LabelModel getLabel() {
                        return label;
                    }

                    public void setLabel(LabelModel label) {
                        this.label = label;
                    }

                    public String getNickname_color() {
                        return nickname_color;
                    }

                    public void setNickname_color(String nickname_color) {
                        this.nickname_color = nickname_color;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getTheme_type() {
                        return theme_type;
                    }

                    public void setTheme_type(int theme_type) {
                        this.theme_type = theme_type;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public static class LabelModel implements Serializable {
                        private String bg_color;
                        private int bg_style;
                        private String border_color;
                        private String img_label_uri_hans;
                        private String img_label_uri_hans_static;
                        private String img_label_uri_hant;
                        private String img_label_uri_hant_static;
                        private String label_theme;
                        private String path;
                        private String text;
                        private String text_color;
                        private boolean use_img_label;

                        public String getBg_color() {
                            return bg_color;
                        }

                        public void setBg_color(String bg_color) {
                            this.bg_color = bg_color;
                        }

                        public int getBg_style() {
                            return bg_style;
                        }

                        public void setBg_style(int bg_style) {
                            this.bg_style = bg_style;
                        }

                        public String getBorder_color() {
                            return border_color;
                        }

                        public void setBorder_color(String border_color) {
                            this.border_color = border_color;
                        }

                        public String getImg_label_uri_hans() {
                            return img_label_uri_hans;
                        }

                        public void setImg_label_uri_hans(String img_label_uri_hans) {
                            this.img_label_uri_hans = img_label_uri_hans;
                        }

                        public String getImg_label_uri_hans_static() {
                            return img_label_uri_hans_static;
                        }

                        public void setImg_label_uri_hans_static(String img_label_uri_hans_static) {
                            this.img_label_uri_hans_static = img_label_uri_hans_static;
                        }

                        public String getImg_label_uri_hant() {
                            return img_label_uri_hant;
                        }

                        public void setImg_label_uri_hant(String img_label_uri_hant) {
                            this.img_label_uri_hant = img_label_uri_hant;
                        }

                        public String getImg_label_uri_hant_static() {
                            return img_label_uri_hant_static;
                        }

                        public void setImg_label_uri_hant_static(String img_label_uri_hant_static) {
                            this.img_label_uri_hant_static = img_label_uri_hant_static;
                        }

                        public String getLabel_theme() {
                            return label_theme;
                        }

                        public void setLabel_theme(String label_theme) {
                            this.label_theme = label_theme;
                        }

                        public String getPath() {
                            return path;
                        }

                        public void setPath(String path) {
                            this.path = path;
                        }

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                        public String getText_color() {
                            return text_color;
                        }

                        public void setText_color(String text_color) {
                            this.text_color = text_color;
                        }

                        public boolean isUse_img_label() {
                            return use_img_label;
                        }

                        public void setUse_img_label(boolean use_img_label) {
                            this.use_img_label = use_img_label;
                        }
                    }
                }
            }

            public static class ModuleDynamicModel implements Serializable {
                private Object additional;
                private DescModel desc;
                private MajorModel major;
                private TopicModel topic;

                public Object getAdditional() {
                    return additional;
                }

                public void setAdditional(Object additional) {
                    this.additional = additional;
                }

                public DescModel getDesc() {
                    return desc;
                }

                public void setDesc(DescModel desc) {
                    this.desc = desc;
                }

                public MajorModel getMajor() {
                    return major;
                }

                public void setMajor(MajorModel major) {
                    this.major = major;
                }

                public TopicModel getTopic() {
                    return topic;
                }

                public void setTopic(TopicModel topic) {
                    this.topic = topic;
                }

                public static class DescModel implements Serializable {
                    private String text;
                    private List<RichTextNodesModel> rich_text_nodes;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public List<RichTextNodesModel> getRich_text_nodes() {
                        return rich_text_nodes;
                    }

                    public void setRich_text_nodes(List<RichTextNodesModel> rich_text_nodes) {
                        this.rich_text_nodes = rich_text_nodes;
                    }

                    public static class RichTextNodesModel implements Serializable {
                        private String orig_text;
                        private String text;
                        private String type;

                        public String getOrig_text() {
                            return orig_text;
                        }

                        public void setOrig_text(String orig_text) {
                            this.orig_text = orig_text;
                        }

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }
                    }
                }

                public static class MajorModel implements Serializable {
                    private ArchiveModel archive;
                    private String type;

                    public ArchiveModel getArchive() {
                        return archive;
                    }

                    public void setArchive(ArchiveModel archive) {
                        this.archive = archive;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public static class ArchiveModel implements Serializable {
                        private String aid;
                        private BadgeModel badge;
                        private String bvid;
                        private String cover;
                        private String desc;
                        private int disable_preview;
                        private String duration_text;
                        private String jump_url;
                        private StatModel stat;
                        private String title;
                        private int type;

                        public String getAid() {
                            return aid;
                        }

                        public void setAid(String aid) {
                            this.aid = aid;
                        }

                        public BadgeModel getBadge() {
                            return badge;
                        }

                        public void setBadge(BadgeModel badge) {
                            this.badge = badge;
                        }

                        public String getBvid() {
                            return bvid;
                        }

                        public void setBvid(String bvid) {
                            this.bvid = bvid;
                        }

                        public String getCover() {
                            return cover;
                        }

                        public void setCover(String cover) {
                            this.cover = cover;
                        }

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }

                        public int getDisable_preview() {
                            return disable_preview;
                        }

                        public void setDisable_preview(int disable_preview) {
                            this.disable_preview = disable_preview;
                        }

                        public String getDuration_text() {
                            return duration_text;
                        }

                        public void setDuration_text(String duration_text) {
                            this.duration_text = duration_text;
                        }

                        public String getJump_url() {
                            return jump_url;
                        }

                        public void setJump_url(String jump_url) {
                            this.jump_url = jump_url;
                        }

                        public StatModel getStat() {
                            return stat;
                        }

                        public void setStat(StatModel stat) {
                            this.stat = stat;
                        }

                        public String getTitle() {
                            return title;
                        }

                        public void setTitle(String title) {
                            this.title = title;
                        }

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }

                        public static class BadgeModel implements Serializable {
                            private String bg_color;
                            private String color;
                            private String text;

                            public String getBg_color() {
                                return bg_color;
                            }

                            public void setBg_color(String bg_color) {
                                this.bg_color = bg_color;
                            }

                            public String getColor() {
                                return color;
                            }

                            public void setColor(String color) {
                                this.color = color;
                            }

                            public String getText() {
                                return text;
                            }

                            public void setText(String text) {
                                this.text = text;
                            }
                        }

                        public static class StatModel implements Serializable {
                            private String danmaku;
                            private String play;

                            public String getDanmaku() {
                                return danmaku;
                            }

                            public void setDanmaku(String danmaku) {
                                this.danmaku = danmaku;
                            }

                            public String getPlay() {
                                return play;
                            }

                            public void setPlay(String play) {
                                this.play = play;
                            }
                        }
                    }
                }

                public static class TopicModel implements Serializable {
                    private int id;
                    private String jump_url;
                    private String name;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getJump_url() {
                        return jump_url;
                    }

                    public void setJump_url(String jump_url) {
                        this.jump_url = jump_url;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }

            public static class ModuleMoreModel implements Serializable {
                private List<ThreePointItemsModel> three_point_items;

                public List<ThreePointItemsModel> getThree_point_items() {
                    return three_point_items;
                }

                public void setThree_point_items(List<ThreePointItemsModel> three_point_items) {
                    this.three_point_items = three_point_items;
                }

                public static class ThreePointItemsModel implements Serializable {
                    private String label;
                    private String type;

                    public String getLabel() {
                        return label;
                    }

                    public void setLabel(String label) {
                        this.label = label;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }

            public static class ModuleStatModel implements Serializable {
                private CommentModel comment;
                private ForwardModel forward;
                private LikeModel like;

                public CommentModel getComment() {
                    return comment;
                }

                public void setComment(CommentModel comment) {
                    this.comment = comment;
                }

                public ForwardModel getForward() {
                    return forward;
                }

                public void setForward(ForwardModel forward) {
                    this.forward = forward;
                }

                public LikeModel getLike() {
                    return like;
                }

                public void setLike(LikeModel like) {
                    this.like = like;
                }

                public static class CommentModel implements Serializable {
                    private int count;
                    private boolean forbidden;

                    public int getCount() {
                        return count;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }

                    public boolean isForbidden() {
                        return forbidden;
                    }

                    public void setForbidden(boolean forbidden) {
                        this.forbidden = forbidden;
                    }
                }

                public static class ForwardModel implements Serializable {
                    private int count;
                    private boolean forbidden;

                    public int getCount() {
                        return count;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }

                    public boolean isForbidden() {
                        return forbidden;
                    }

                    public void setForbidden(boolean forbidden) {
                        this.forbidden = forbidden;
                    }
                }

                public static class LikeModel implements Serializable {
                    private int count;
                    private boolean forbidden;
                    private boolean status;

                    public int getCount() {
                        return count;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }

                    public boolean isForbidden() {
                        return forbidden;
                    }

                    public void setForbidden(boolean forbidden) {
                        this.forbidden = forbidden;
                    }

                    public boolean isStatus() {
                        return status;
                    }

                    public void setStatus(boolean status) {
                        this.status = status;
                    }
                }
            }
        }

        public static class OrigModel implements Serializable {
            private BasicModelX basic;
            private String id_str;
            private ModulesModelX modules;
            private String type;
            private boolean visible;

            public BasicModelX getBasic() {
                return basic;
            }

            public void setBasic(BasicModelX basic) {
                this.basic = basic;
            }

            public String getId_str() {
                return id_str;
            }

            public void setId_str(String id_str) {
                this.id_str = id_str;
            }

            public ModulesModelX getModules() {
                return modules;
            }

            public void setModules(ModulesModelX modules) {
                this.modules = modules;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isVisible() {
                return visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }

            public static class BasicModelX implements Serializable {
                private String comment_id_str;
                private int comment_type;
                private LikeIconModelX like_icon;
                private String rid_str;

                public String getComment_id_str() {
                    return comment_id_str;
                }

                public void setComment_id_str(String comment_id_str) {
                    this.comment_id_str = comment_id_str;
                }

                public int getComment_type() {
                    return comment_type;
                }

                public void setComment_type(int comment_type) {
                    this.comment_type = comment_type;
                }

                public LikeIconModelX getLike_icon() {
                    return like_icon;
                }

                public void setLike_icon(LikeIconModelX like_icon) {
                    this.like_icon = like_icon;
                }

                public String getRid_str() {
                    return rid_str;
                }

                public void setRid_str(String rid_str) {
                    this.rid_str = rid_str;
                }

                public static class LikeIconModelX implements Serializable {
                    private String action_url;
                    private String end_url;
                    private int id;
                    private String start_url;

                    public String getAction_url() {
                        return action_url;
                    }

                    public void setAction_url(String action_url) {
                        this.action_url = action_url;
                    }

                    public String getEnd_url() {
                        return end_url;
                    }

                    public void setEnd_url(String end_url) {
                        this.end_url = end_url;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getStart_url() {
                        return start_url;
                    }

                    public void setStart_url(String start_url) {
                        this.start_url = start_url;
                    }
                }
            }

            public static class ModulesModelX implements Serializable {
                private ModuleAuthorModelX module_author;
                private ModuleDynamicModelX module_dynamic;

                public ModuleAuthorModelX getModule_author() {
                    return module_author;
                }

                public void setModule_author(ModuleAuthorModelX module_author) {
                    this.module_author = module_author;
                }

                public ModuleDynamicModelX getModule_dynamic() {
                    return module_dynamic;
                }

                public void setModule_dynamic(ModuleDynamicModelX module_dynamic) {
                    this.module_dynamic = module_dynamic;
                }

                public static class ModuleAuthorModelX implements Serializable {
                    private String face;
                    private boolean face_nft;
                    private boolean following;
                    private String jump_url;
                    private String label;
                    private int mid;
                    private String name;
                    private OfficialVerifyModelX official_verify;
                    private PendantModelX pendant;
                    private String pub_action;
                    private String pub_time;
                    private int pub_ts;
                    private String type;
                    private VipModelX vip;

                    public String getFace() {
                        return face;
                    }

                    public void setFace(String face) {
                        this.face = face;
                    }

                    public boolean isFace_nft() {
                        return face_nft;
                    }

                    public void setFace_nft(boolean face_nft) {
                        this.face_nft = face_nft;
                    }

                    public boolean isFollowing() {
                        return following;
                    }

                    public void setFollowing(boolean following) {
                        this.following = following;
                    }

                    public String getJump_url() {
                        return jump_url;
                    }

                    public void setJump_url(String jump_url) {
                        this.jump_url = jump_url;
                    }

                    public String getLabel() {
                        return label;
                    }

                    public void setLabel(String label) {
                        this.label = label;
                    }

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

                    public OfficialVerifyModelX getOfficial_verify() {
                        return official_verify;
                    }

                    public void setOfficial_verify(OfficialVerifyModelX official_verify) {
                        this.official_verify = official_verify;
                    }

                    public PendantModelX getPendant() {
                        return pendant;
                    }

                    public void setPendant(PendantModelX pendant) {
                        this.pendant = pendant;
                    }

                    public String getPub_action() {
                        return pub_action;
                    }

                    public void setPub_action(String pub_action) {
                        this.pub_action = pub_action;
                    }

                    public String getPub_time() {
                        return pub_time;
                    }

                    public void setPub_time(String pub_time) {
                        this.pub_time = pub_time;
                    }

                    public int getPub_ts() {
                        return pub_ts;
                    }

                    public void setPub_ts(int pub_ts) {
                        this.pub_ts = pub_ts;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public VipModelX getVip() {
                        return vip;
                    }

                    public void setVip(VipModelX vip) {
                        this.vip = vip;
                    }

                    public static class OfficialVerifyModelX implements Serializable {
                        private String desc;
                        private int type;

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }
                    }

                    public static class PendantModelX implements Serializable {
                        private int expire;
                        private String image;
                        private String image_enhance;
                        private String image_enhance_frame;
                        private String name;
                        private int pid;

                        public int getExpire() {
                            return expire;
                        }

                        public void setExpire(int expire) {
                            this.expire = expire;
                        }

                        public String getImage() {
                            return image;
                        }

                        public void setImage(String image) {
                            this.image = image;
                        }

                        public String getImage_enhance() {
                            return image_enhance;
                        }

                        public void setImage_enhance(String image_enhance) {
                            this.image_enhance = image_enhance;
                        }

                        public String getImage_enhance_frame() {
                            return image_enhance_frame;
                        }

                        public void setImage_enhance_frame(String image_enhance_frame) {
                            this.image_enhance_frame = image_enhance_frame;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public int getPid() {
                            return pid;
                        }

                        public void setPid(int pid) {
                            this.pid = pid;
                        }
                    }

                    public static class VipModelX implements Serializable {
                        private int avatar_subscript;
                        private String avatar_subscript_url;
                        private long due_date;
                        private LabelModelX label;
                        private String nickname_color;
                        private int status;
                        private int theme_type;
                        private int type;

                        public int getAvatar_subscript() {
                            return avatar_subscript;
                        }

                        public void setAvatar_subscript(int avatar_subscript) {
                            this.avatar_subscript = avatar_subscript;
                        }

                        public String getAvatar_subscript_url() {
                            return avatar_subscript_url;
                        }

                        public void setAvatar_subscript_url(String avatar_subscript_url) {
                            this.avatar_subscript_url = avatar_subscript_url;
                        }

                        public long getDue_date() {
                            return due_date;
                        }

                        public void setDue_date(long due_date) {
                            this.due_date = due_date;
                        }

                        public LabelModelX getLabel() {
                            return label;
                        }

                        public void setLabel(LabelModelX label) {
                            this.label = label;
                        }

                        public String getNickname_color() {
                            return nickname_color;
                        }

                        public void setNickname_color(String nickname_color) {
                            this.nickname_color = nickname_color;
                        }

                        public int getStatus() {
                            return status;
                        }

                        public void setStatus(int status) {
                            this.status = status;
                        }

                        public int getTheme_type() {
                            return theme_type;
                        }

                        public void setTheme_type(int theme_type) {
                            this.theme_type = theme_type;
                        }

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }

                        public static class LabelModelX implements Serializable {
                            private String bg_color;
                            private int bg_style;
                            private String border_color;
                            private String img_label_uri_hans;
                            private String img_label_uri_hans_static;
                            private String img_label_uri_hant;
                            private String img_label_uri_hant_static;
                            private String label_theme;
                            private String path;
                            private String text;
                            private String text_color;
                            private boolean use_img_label;

                            public String getBg_color() {
                                return bg_color;
                            }

                            public void setBg_color(String bg_color) {
                                this.bg_color = bg_color;
                            }

                            public int getBg_style() {
                                return bg_style;
                            }

                            public void setBg_style(int bg_style) {
                                this.bg_style = bg_style;
                            }

                            public String getBorder_color() {
                                return border_color;
                            }

                            public void setBorder_color(String border_color) {
                                this.border_color = border_color;
                            }

                            public String getImg_label_uri_hans() {
                                return img_label_uri_hans;
                            }

                            public void setImg_label_uri_hans(String img_label_uri_hans) {
                                this.img_label_uri_hans = img_label_uri_hans;
                            }

                            public String getImg_label_uri_hans_static() {
                                return img_label_uri_hans_static;
                            }

                            public void setImg_label_uri_hans_static(String img_label_uri_hans_static) {
                                this.img_label_uri_hans_static = img_label_uri_hans_static;
                            }

                            public String getImg_label_uri_hant() {
                                return img_label_uri_hant;
                            }

                            public void setImg_label_uri_hant(String img_label_uri_hant) {
                                this.img_label_uri_hant = img_label_uri_hant;
                            }

                            public String getImg_label_uri_hant_static() {
                                return img_label_uri_hant_static;
                            }

                            public void setImg_label_uri_hant_static(String img_label_uri_hant_static) {
                                this.img_label_uri_hant_static = img_label_uri_hant_static;
                            }

                            public String getLabel_theme() {
                                return label_theme;
                            }

                            public void setLabel_theme(String label_theme) {
                                this.label_theme = label_theme;
                            }

                            public String getPath() {
                                return path;
                            }

                            public void setPath(String path) {
                                this.path = path;
                            }

                            public String getText() {
                                return text;
                            }

                            public void setText(String text) {
                                this.text = text;
                            }

                            public String getText_color() {
                                return text_color;
                            }

                            public void setText_color(String text_color) {
                                this.text_color = text_color;
                            }

                            public boolean isUse_img_label() {
                                return use_img_label;
                            }

                            public void setUse_img_label(boolean use_img_label) {
                                this.use_img_label = use_img_label;
                            }
                        }
                    }
                }

                public static class ModuleDynamicModelX implements Serializable {
                    private Object additional;
                    private DescModelX desc;
                    private MajorModelX major;
                    private Object topic;

                    public Object getAdditional() {
                        return additional;
                    }

                    public void setAdditional(Object additional) {
                        this.additional = additional;
                    }

                    public DescModelX getDesc() {
                        return desc;
                    }

                    public void setDesc(DescModelX desc) {
                        this.desc = desc;
                    }

                    public MajorModelX getMajor() {
                        return major;
                    }

                    public void setMajor(MajorModelX major) {
                        this.major = major;
                    }

                    public Object getTopic() {
                        return topic;
                    }

                    public void setTopic(Object topic) {
                        this.topic = topic;
                    }

                    public static class DescModelX implements Serializable {
                        private String text;
                        private List<RichTextNodesModelX> rich_text_nodes;

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                        public List<RichTextNodesModelX> getRich_text_nodes() {
                            return rich_text_nodes;
                        }

                        public void setRich_text_nodes(List<RichTextNodesModelX> rich_text_nodes) {
                            this.rich_text_nodes = rich_text_nodes;
                        }

                        public static class RichTextNodesModelX implements Serializable {
                            private String orig_text;
                            private String text;
                            private String type;

                            public String getOrig_text() {
                                return orig_text;
                            }

                            public void setOrig_text(String orig_text) {
                                this.orig_text = orig_text;
                            }

                            public String getText() {
                                return text;
                            }

                            public void setText(String text) {
                                this.text = text;
                            }

                            public String getType() {
                                return type;
                            }

                            public void setType(String type) {
                                this.type = type;
                            }
                        }
                    }

                    public static class MajorModelX implements Serializable {
                        private ArchiveModelX archive;
                        private String type;

                        public ArchiveModelX getArchive() {
                            return archive;
                        }

                        public void setArchive(ArchiveModelX archive) {
                            this.archive = archive;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public static class ArchiveModelX implements Serializable {
                            private String aid;
                            private BadgeModelX badge;
                            private String bvid;
                            private String cover;
                            private String desc;
                            private int disable_preview;
                            private String duration_text;
                            private String jump_url;
                            private StatModelX stat;
                            private String title;
                            private int type;

                            public String getAid() {
                                return aid;
                            }

                            public void setAid(String aid) {
                                this.aid = aid;
                            }

                            public BadgeModelX getBadge() {
                                return badge;
                            }

                            public void setBadge(BadgeModelX badge) {
                                this.badge = badge;
                            }

                            public String getBvid() {
                                return bvid;
                            }

                            public void setBvid(String bvid) {
                                this.bvid = bvid;
                            }

                            public String getCover() {
                                return cover;
                            }

                            public void setCover(String cover) {
                                this.cover = cover;
                            }

                            public String getDesc() {
                                return desc;
                            }

                            public void setDesc(String desc) {
                                this.desc = desc;
                            }

                            public int getDisable_preview() {
                                return disable_preview;
                            }

                            public void setDisable_preview(int disable_preview) {
                                this.disable_preview = disable_preview;
                            }

                            public String getDuration_text() {
                                return duration_text;
                            }

                            public void setDuration_text(String duration_text) {
                                this.duration_text = duration_text;
                            }

                            public String getJump_url() {
                                return jump_url;
                            }

                            public void setJump_url(String jump_url) {
                                this.jump_url = jump_url;
                            }

                            public StatModelX getStat() {
                                return stat;
                            }

                            public void setStat(StatModelX stat) {
                                this.stat = stat;
                            }

                            public String getTitle() {
                                return title;
                            }

                            public void setTitle(String title) {
                                this.title = title;
                            }

                            public int getType() {
                                return type;
                            }

                            public void setType(int type) {
                                this.type = type;
                            }

                            public static class BadgeModelX implements Serializable {
                                private String bg_color;
                                private String color;
                                private String text;

                                public String getBg_color() {
                                    return bg_color;
                                }

                                public void setBg_color(String bg_color) {
                                    this.bg_color = bg_color;
                                }

                                public String getColor() {
                                    return color;
                                }

                                public void setColor(String color) {
                                    this.color = color;
                                }

                                public String getText() {
                                    return text;
                                }

                                public void setText(String text) {
                                    this.text = text;
                                }
                            }

                            public static class StatModelX implements Serializable {
                                private String danmaku;
                                private String play;

                                public String getDanmaku() {
                                    return danmaku;
                                }

                                public void setDanmaku(String danmaku) {
                                    this.danmaku = danmaku;
                                }

                                public String getPlay() {
                                    return play;
                                }

                                public void setPlay(String play) {
                                    this.play = play;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
