package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedModel {
    @SerializedName("has_more")
    private boolean hasMore;
    @SerializedName("offset")
    private String offset;
    @SerializedName("update_baseline")
    private String updateBaseline;
    @SerializedName("update_num")
    private int updateNum;
    @SerializedName("items")
    private List<ItemsModel> items;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getUpdateBaseline() {
        return updateBaseline;
    }

    public void setUpdateBaseline(String updateBaseline) {
        this.updateBaseline = updateBaseline;
    }

    public int getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(int updateNum) {
        this.updateNum = updateNum;
    }

    public List<ItemsModel> getItems() {
        return items;
    }

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }

    public static class ItemsModel {
        @SerializedName("basic")
        private BasicModel basic;
        @SerializedName("id_str")
        private String idStr;
        @SerializedName("modules")
        private ModulesModel modules;
        @SerializedName("type")
        private String type;
        @SerializedName("visible")
        private boolean visible;
        @SerializedName("orig")
        private OrigModel orig;

        public BasicModel getBasic() {
            return basic;
        }

        public void setBasic(BasicModel basic) {
            this.basic = basic;
        }

        public String getIdStr() {
            return idStr;
        }

        public void setIdStr(String idStr) {
            this.idStr = idStr;
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

        public static class BasicModel {
            /**
             * comment_id_str : 738758534291783744
             * comment_type : 17
             * like_icon : {"action_url":"","end_url":"","id":0,"start_url":""}
             * rid_str : 318739826664064719
             */

            @SerializedName("comment_id_str")
            private String commentIdStr;
            @SerializedName("comment_type")
            private int commentType;
            @SerializedName("like_icon")
            private LikeIconModel likeIcon;
            @SerializedName("rid_str")
            private String ridStr;

            public String getCommentIdStr() {
                return commentIdStr;
            }

            public void setCommentIdStr(String commentIdStr) {
                this.commentIdStr = commentIdStr;
            }

            public int getCommentType() {
                return commentType;
            }

            public void setCommentType(int commentType) {
                this.commentType = commentType;
            }

            public LikeIconModel getLikeIcon() {
                return likeIcon;
            }

            public void setLikeIcon(LikeIconModel likeIcon) {
                this.likeIcon = likeIcon;
            }

            public String getRidStr() {
                return ridStr;
            }

            public void setRidStr(String ridStr) {
                this.ridStr = ridStr;
            }

            public static class LikeIconModel {
                /**
                 * action_url :
                 * end_url :
                 * id : 0
                 * start_url :
                 */

                @SerializedName("action_url")
                private String actionUrl;
                @SerializedName("end_url")
                private String endUrl;
                @SerializedName("id")
                private int id;
                @SerializedName("start_url")
                private String startUrl;

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public String getEndUrl() {
                    return endUrl;
                }

                public void setEndUrl(String endUrl) {
                    this.endUrl = endUrl;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStartUrl() {
                    return startUrl;
                }

                public void setStartUrl(String startUrl) {
                    this.startUrl = startUrl;
                }
            }
        }

        public static class ModulesModel {
            @SerializedName("module_author")
            private ModuleAuthorModel moduleAuthor;
            @SerializedName("module_dynamic")
            private ModuleDynamicModel moduleDynamic;
            @SerializedName("module_more")
            private ModuleMoreModel moduleMore;
            @SerializedName("module_stat")
            private ModuleStatModel moduleStat;

            public ModuleAuthorModel getModuleAuthor() {
                return moduleAuthor;
            }

            public void setModuleAuthor(ModuleAuthorModel moduleAuthor) {
                this.moduleAuthor = moduleAuthor;
            }

            public ModuleDynamicModel getModuleDynamic() {
                return moduleDynamic;
            }

            public void setModuleDynamic(ModuleDynamicModel moduleDynamic) {
                this.moduleDynamic = moduleDynamic;
            }

            public ModuleMoreModel getModuleMore() {
                return moduleMore;
            }

            public void setModuleMore(ModuleMoreModel moduleMore) {
                this.moduleMore = moduleMore;
            }

            public ModuleStatModel getModuleStat() {
                return moduleStat;
            }

            public void setModuleStat(ModuleStatModel moduleStat) {
                this.moduleStat = moduleStat;
            }

            public static class ModuleAuthorModel {
                /**
                 * decorate : {"card_url":"https://i0.hdslb.com/bfs/vip/a9e3d993c7a15e88ce0bf714a142f7d2b44121e2.png","fan":{"color":"","is_fan":false,"num_str":"","number":0},"id":28,"jump_url":"https://www.bilibili.com/h5/mall/equity-link/home?navhide=1&item_id=28&part=card&f_source=garb&from=post&isdiy=0","name":"2233娘","type":2}
                 * face : http://i1.hdslb.com/bfs/face/ec008e32064705c576f3ffd73d20288e441d945f.jpg
                 * face_nft : false
                 * following : true
                 * jump_url : //space.bilibili.com/35359510/dynamic
                 * label :
                 * mid : 35359510
                 * name : 远古时代装机猿
                 * official_verify : {"desc":"","type":0}
                 * pendant : {"expire":0,"image":"","image_enhance":"","image_enhance_frame":"","name":"","pid":0}
                 * pub_action : 直播了
                 * pub_location_text :
                 * pub_time :
                 * pub_ts : 1670844025
                 * type : AUTHOR_TYPE_NORMAL
                 * vip : {"avatar_subscript":1,"avatar_subscript_url":"","due_date":1678204800000,"label":{"bg_color":"#FB7299","bg_style":1,"border_color":"","img_label_uri_hans":"","img_label_uri_hans_static":"https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png","img_label_uri_hant":"","img_label_uri_hant_static":"https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png","label_theme":"annual_vip","path":"","text":"年度大会员","text_color":"#FFFFFF","use_img_label":true},"nickname_color":"#FB7299","status":1,"theme_type":0,"type":2}
                 */

                @SerializedName("decorate")
                private DecorateModel decorate;
                @SerializedName("face")
                private String face;
                @SerializedName("face_nft")
                private boolean faceNft;
                @SerializedName("following")
                private boolean following;
                @SerializedName("jump_url")
                private String jumpUrl;
                @SerializedName("label")
                private String label;
                @SerializedName("mid")
                private int mid;
                @SerializedName("name")
                private String name;
                @SerializedName("official_verify")
                private OfficialVerifyModel officialVerify;
                @SerializedName("pendant")
                private PendantModel pendant;
                @SerializedName("pub_action")
                private String pubAction;
                @SerializedName("pub_location_text")
                private String pubLocationText;
                @SerializedName("pub_time")
                private String pubTime;
                @SerializedName("pub_ts")
                private int pubTs;
                @SerializedName("type")
                private String type;
                @SerializedName("vip")
                private VipModel vip;

                public DecorateModel getDecorate() {
                    return decorate;
                }

                public void setDecorate(DecorateModel decorate) {
                    this.decorate = decorate;
                }

                public String getFace() {
                    return face;
                }

                public void setFace(String face) {
                    this.face = face;
                }

                public boolean isFaceNft() {
                    return faceNft;
                }

                public void setFaceNft(boolean faceNft) {
                    this.faceNft = faceNft;
                }

                public boolean isFollowing() {
                    return following;
                }

                public void setFollowing(boolean following) {
                    this.following = following;
                }

                public String getJumpUrl() {
                    return jumpUrl;
                }

                public void setJumpUrl(String jumpUrl) {
                    this.jumpUrl = jumpUrl;
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

                public OfficialVerifyModel getOfficialVerify() {
                    return officialVerify;
                }

                public void setOfficialVerify(OfficialVerifyModel officialVerify) {
                    this.officialVerify = officialVerify;
                }

                public PendantModel getPendant() {
                    return pendant;
                }

                public void setPendant(PendantModel pendant) {
                    this.pendant = pendant;
                }

                public String getPubAction() {
                    return pubAction;
                }

                public void setPubAction(String pubAction) {
                    this.pubAction = pubAction;
                }

                public String getPubLocationText() {
                    return pubLocationText;
                }

                public void setPubLocationText(String pubLocationText) {
                    this.pubLocationText = pubLocationText;
                }

                public String getPubTime() {
                    return pubTime;
                }

                public void setPubTime(String pubTime) {
                    this.pubTime = pubTime;
                }

                public int getPubTs() {
                    return pubTs;
                }

                public void setPubTs(int pubTs) {
                    this.pubTs = pubTs;
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

                public static class DecorateModel {
                    /**
                     * card_url : https://i0.hdslb.com/bfs/vip/a9e3d993c7a15e88ce0bf714a142f7d2b44121e2.png
                     * fan : {"color":"","is_fan":false,"num_str":"","number":0}
                     * id : 28
                     * jump_url : https://www.bilibili.com/h5/mall/equity-link/home?navhide=1&item_id=28&part=card&f_source=garb&from=post&isdiy=0
                     * name : 2233娘
                     * type : 2
                     */

                    @SerializedName("card_url")
                    private String cardUrl;
                    @SerializedName("fan")
                    private FanModel fan;
                    @SerializedName("id")
                    private int id;
                    @SerializedName("jump_url")
                    private String jumpUrl;
                    @SerializedName("name")
                    private String name;
                    @SerializedName("type")
                    private int type;

                    public String getCardUrl() {
                        return cardUrl;
                    }

                    public void setCardUrl(String cardUrl) {
                        this.cardUrl = cardUrl;
                    }

                    public FanModel getFan() {
                        return fan;
                    }

                    public void setFan(FanModel fan) {
                        this.fan = fan;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getJumpUrl() {
                        return jumpUrl;
                    }

                    public void setJumpUrl(String jumpUrl) {
                        this.jumpUrl = jumpUrl;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public static class FanModel {
                        /**
                         * color :
                         * is_fan : false
                         * num_str :
                         * number : 0
                         */

                        @SerializedName("color")
                        private String color;
                        @SerializedName("is_fan")
                        private boolean isFan;
                        @SerializedName("num_str")
                        private String numStr;
                        @SerializedName("number")
                        private int number;

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }

                        public boolean isIsFan() {
                            return isFan;
                        }

                        public void setIsFan(boolean isFan) {
                            this.isFan = isFan;
                        }

                        public String getNumStr() {
                            return numStr;
                        }

                        public void setNumStr(String numStr) {
                            this.numStr = numStr;
                        }

                        public int getNumber() {
                            return number;
                        }

                        public void setNumber(int number) {
                            this.number = number;
                        }
                    }
                }

                public static class OfficialVerifyModel {
                    /**
                     * desc :
                     * type : 0
                     */

                    @SerializedName("desc")
                    private String desc;
                    @SerializedName("type")
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

                public static class PendantModel {
                    /**
                     * expire : 0
                     * image :
                     * image_enhance :
                     * image_enhance_frame :
                     * name :
                     * pid : 0
                     */

                    @SerializedName("expire")
                    private int expire;
                    @SerializedName("image")
                    private String image;
                    @SerializedName("image_enhance")
                    private String imageEnhance;
                    @SerializedName("image_enhance_frame")
                    private String imageEnhanceFrame;
                    @SerializedName("name")
                    private String name;
                    @SerializedName("pid")
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

                    public String getImageEnhance() {
                        return imageEnhance;
                    }

                    public void setImageEnhance(String imageEnhance) {
                        this.imageEnhance = imageEnhance;
                    }

                    public String getImageEnhanceFrame() {
                        return imageEnhanceFrame;
                    }

                    public void setImageEnhanceFrame(String imageEnhanceFrame) {
                        this.imageEnhanceFrame = imageEnhanceFrame;
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

                public static class VipModel {
                    /**
                     * avatar_subscript : 1
                     * avatar_subscript_url :
                     * due_date : 1678204800000
                     * label : {"bg_color":"#FB7299","bg_style":1,"border_color":"","img_label_uri_hans":"","img_label_uri_hans_static":"https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png","img_label_uri_hant":"","img_label_uri_hant_static":"https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png","label_theme":"annual_vip","path":"","text":"年度大会员","text_color":"#FFFFFF","use_img_label":true}
                     * nickname_color : #FB7299
                     * status : 1
                     * theme_type : 0
                     * type : 2
                     */

                    @SerializedName("avatar_subscript")
                    private int avatarSubscript;
                    @SerializedName("avatar_subscript_url")
                    private String avatarSubscriptUrl;
                    @SerializedName("due_date")
                    private long dueDate;
                    @SerializedName("label")
                    private LabelModel label;
                    @SerializedName("nickname_color")
                    private String nicknameColor;
                    @SerializedName("status")
                    private int status;
                    @SerializedName("theme_type")
                    private int themeType;
                    @SerializedName("type")
                    private int type;

                    public int getAvatarSubscript() {
                        return avatarSubscript;
                    }

                    public void setAvatarSubscript(int avatarSubscript) {
                        this.avatarSubscript = avatarSubscript;
                    }

                    public String getAvatarSubscriptUrl() {
                        return avatarSubscriptUrl;
                    }

                    public void setAvatarSubscriptUrl(String avatarSubscriptUrl) {
                        this.avatarSubscriptUrl = avatarSubscriptUrl;
                    }

                    public long getDueDate() {
                        return dueDate;
                    }

                    public void setDueDate(long dueDate) {
                        this.dueDate = dueDate;
                    }

                    public LabelModel getLabel() {
                        return label;
                    }

                    public void setLabel(LabelModel label) {
                        this.label = label;
                    }

                    public String getNicknameColor() {
                        return nicknameColor;
                    }

                    public void setNicknameColor(String nicknameColor) {
                        this.nicknameColor = nicknameColor;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getThemeType() {
                        return themeType;
                    }

                    public void setThemeType(int themeType) {
                        this.themeType = themeType;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public static class LabelModel {
                        /**
                         * bg_color : #FB7299
                         * bg_style : 1
                         * border_color :
                         * img_label_uri_hans :
                         * img_label_uri_hans_static : https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png
                         * img_label_uri_hant :
                         * img_label_uri_hant_static : https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png
                         * label_theme : annual_vip
                         * path :
                         * text : 年度大会员
                         * text_color : #FFFFFF
                         * use_img_label : true
                         */

                        @SerializedName("bg_color")
                        private String bgColor;
                        @SerializedName("bg_style")
                        private int bgStyle;
                        @SerializedName("border_color")
                        private String borderColor;
                        @SerializedName("img_label_uri_hans")
                        private String imgLabelUriHans;
                        @SerializedName("img_label_uri_hans_static")
                        private String imgLabelUriHansStatic;
                        @SerializedName("img_label_uri_hant")
                        private String imgLabelUriHant;
                        @SerializedName("img_label_uri_hant_static")
                        private String imgLabelUriHantStatic;
                        @SerializedName("label_theme")
                        private String labelTheme;
                        @SerializedName("path")
                        private String path;
                        @SerializedName("text")
                        private String text;
                        @SerializedName("text_color")
                        private String textColor;
                        @SerializedName("use_img_label")
                        private boolean useImgLabel;

                        public String getBgColor() {
                            return bgColor;
                        }

                        public void setBgColor(String bgColor) {
                            this.bgColor = bgColor;
                        }

                        public int getBgStyle() {
                            return bgStyle;
                        }

                        public void setBgStyle(int bgStyle) {
                            this.bgStyle = bgStyle;
                        }

                        public String getBorderColor() {
                            return borderColor;
                        }

                        public void setBorderColor(String borderColor) {
                            this.borderColor = borderColor;
                        }

                        public String getImgLabelUriHans() {
                            return imgLabelUriHans;
                        }

                        public void setImgLabelUriHans(String imgLabelUriHans) {
                            this.imgLabelUriHans = imgLabelUriHans;
                        }

                        public String getImgLabelUriHansStatic() {
                            return imgLabelUriHansStatic;
                        }

                        public void setImgLabelUriHansStatic(String imgLabelUriHansStatic) {
                            this.imgLabelUriHansStatic = imgLabelUriHansStatic;
                        }

                        public String getImgLabelUriHant() {
                            return imgLabelUriHant;
                        }

                        public void setImgLabelUriHant(String imgLabelUriHant) {
                            this.imgLabelUriHant = imgLabelUriHant;
                        }

                        public String getImgLabelUriHantStatic() {
                            return imgLabelUriHantStatic;
                        }

                        public void setImgLabelUriHantStatic(String imgLabelUriHantStatic) {
                            this.imgLabelUriHantStatic = imgLabelUriHantStatic;
                        }

                        public String getLabelTheme() {
                            return labelTheme;
                        }

                        public void setLabelTheme(String labelTheme) {
                            this.labelTheme = labelTheme;
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

                        public String getTextColor() {
                            return textColor;
                        }

                        public void setTextColor(String textColor) {
                            this.textColor = textColor;
                        }

                        public boolean isUseImgLabel() {
                            return useImgLabel;
                        }

                        public void setUseImgLabel(boolean useImgLabel) {
                            this.useImgLabel = useImgLabel;
                        }
                    }
                }
            }

            public static class ModuleDynamicModel {
                /**
                 * additional : null
                 * desc : null
                 * major : {"live_rcmd":{"content":"{\"type\":1,\"live_play_info\":{\"cover\":\"http://i0.hdslb.com/bfs/live/new_room_cover/510dc08d3296bcd91ee2503d821e609602c4bfc5.jpg\",\"area_id\":80,\"live_start_time\":1670843423,\"link\":\"https://live.bilibili.com/1361615\",\"title\":\"今晚十点A卡视频上线\",\"pendants\":{\"list\":null},\"live_id\":\"318739826664064719\",\"uid\":35359510,\"online\":194992,\"area_name\":\"吃鸡行动\",\"parent_area_name\":\"网游\",\"room_paid_type\":0,\"room_id\":1361615,\"room_type\":0,\"play_type\":0,\"parent_area_id\":2,\"live_screen_type\":0,\"watched_show\":{\"icon_web\":\"https://i0.hdslb.com/bfs/live/8d9d0f33ef8bf6f308742752d13dd0df731df19c.png\",\"switch\":true,\"num\":33140,\"text_small\":\"3.3万\",\"text_large\":\"3.3万人看过\",\"icon\":\"https://i0.hdslb.com/bfs/live/a725a9e61242ef44d764ac911691a7ce07f36c1d.png\",\"icon_location\":\"\"},\"live_status\":1},\"live_record_info\":null}","reserve_type":0},"type":"MAJOR_TYPE_LIVE_RCMD"}
                 * topic : null
                 */

                @SerializedName("additional")
                private Object additional;
                @SerializedName("desc")
                private Object desc;
                @SerializedName("major")
                private MajorModel major;
                @SerializedName("topic")
                private Object topic;

                public Object getAdditional() {
                    return additional;
                }

                public void setAdditional(Object additional) {
                    this.additional = additional;
                }

                public Object getDesc() {
                    return desc;
                }

                public void setDesc(Object desc) {
                    this.desc = desc;
                }

                public MajorModel getMajor() {
                    return major;
                }

                public void setMajor(MajorModel major) {
                    this.major = major;
                }

                public Object getTopic() {
                    return topic;
                }

                public void setTopic(Object topic) {
                    this.topic = topic;
                }

                public static class MajorModel {
                    /**
                     * live_rcmd : {"content":"{\"type\":1,\"live_play_info\":{\"cover\":\"http://i0.hdslb.com/bfs/live/new_room_cover/510dc08d3296bcd91ee2503d821e609602c4bfc5.jpg\",\"area_id\":80,\"live_start_time\":1670843423,\"link\":\"https://live.bilibili.com/1361615\",\"title\":\"今晚十点A卡视频上线\",\"pendants\":{\"list\":null},\"live_id\":\"318739826664064719\",\"uid\":35359510,\"online\":194992,\"area_name\":\"吃鸡行动\",\"parent_area_name\":\"网游\",\"room_paid_type\":0,\"room_id\":1361615,\"room_type\":0,\"play_type\":0,\"parent_area_id\":2,\"live_screen_type\":0,\"watched_show\":{\"icon_web\":\"https://i0.hdslb.com/bfs/live/8d9d0f33ef8bf6f308742752d13dd0df731df19c.png\",\"switch\":true,\"num\":33140,\"text_small\":\"3.3万\",\"text_large\":\"3.3万人看过\",\"icon\":\"https://i0.hdslb.com/bfs/live/a725a9e61242ef44d764ac911691a7ce07f36c1d.png\",\"icon_location\":\"\"},\"live_status\":1},\"live_record_info\":null}","reserve_type":0}
                     * type : MAJOR_TYPE_LIVE_RCMD
                     */

                    @SerializedName("live_rcmd")
                    private LiveRcmdModel liveRcmd;
                    @SerializedName("type")
                    private String type;

                    public LiveRcmdModel getLiveRcmd() {
                        return liveRcmd;
                    }

                    public void setLiveRcmd(LiveRcmdModel liveRcmd) {
                        this.liveRcmd = liveRcmd;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public static class LiveRcmdModel {
                        /**
                         * content : {"type":1,"live_play_info":{"cover":"http://i0.hdslb.com/bfs/live/new_room_cover/510dc08d3296bcd91ee2503d821e609602c4bfc5.jpg","area_id":80,"live_start_time":1670843423,"link":"https://live.bilibili.com/1361615","title":"今晚十点A卡视频上线","pendants":{"list":null},"live_id":"318739826664064719","uid":35359510,"online":194992,"area_name":"吃鸡行动","parent_area_name":"网游","room_paid_type":0,"room_id":1361615,"room_type":0,"play_type":0,"parent_area_id":2,"live_screen_type":0,"watched_show":{"icon_web":"https://i0.hdslb.com/bfs/live/8d9d0f33ef8bf6f308742752d13dd0df731df19c.png","switch":true,"num":33140,"text_small":"3.3万","text_large":"3.3万人看过","icon":"https://i0.hdslb.com/bfs/live/a725a9e61242ef44d764ac911691a7ce07f36c1d.png","icon_location":""},"live_status":1},"live_record_info":null}
                         * reserve_type : 0
                         */

                        @SerializedName("content")
                        private String content;
                        @SerializedName("reserve_type")
                        private int reserveType;

                        public String getContent() {
                            return content;
                        }

                        public void setContent(String content) {
                            this.content = content;
                        }

                        public int getReserveType() {
                            return reserveType;
                        }

                        public void setReserveType(int reserveType) {
                            this.reserveType = reserveType;
                        }
                    }
                }
            }

            public static class ModuleMoreModel {
                @SerializedName("three_point_items")
                private List<ThreePointItemsModel> threePointItems;

                public List<ThreePointItemsModel> getThreePointItems() {
                    return threePointItems;
                }

                public void setThreePointItems(List<ThreePointItemsModel> threePointItems) {
                    this.threePointItems = threePointItems;
                }

                public static class ThreePointItemsModel {
                    /**
                     * label : 取消关注
                     * type : THREE_POINT_FOLLOWING
                     */

                    @SerializedName("label")
                    private String label;
                    @SerializedName("type")
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

            public static class ModuleStatModel {
                /**
                 * comment : {"count":1,"forbidden":false,"hidden":true}
                 * forward : {"count":0,"forbidden":false}
                 * like : {"count":284,"forbidden":false,"status":false}
                 */

                @SerializedName("comment")
                private CommentModel comment;
                @SerializedName("forward")
                private ForwardModel forward;
                @SerializedName("like")
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

                public static class CommentModel {
                    /**
                     * count : 1
                     * forbidden : false
                     * hidden : true
                     */

                    @SerializedName("count")
                    private int count;
                    @SerializedName("forbidden")
                    private boolean forbidden;
                    @SerializedName("hidden")
                    private boolean hidden;

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

                    public boolean isHidden() {
                        return hidden;
                    }

                    public void setHidden(boolean hidden) {
                        this.hidden = hidden;
                    }
                }

                public static class ForwardModel {
                    /**
                     * count : 0
                     * forbidden : false
                     */

                    @SerializedName("count")
                    private int count;
                    @SerializedName("forbidden")
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

                public static class LikeModel {
                    /**
                     * count : 284
                     * forbidden : false
                     * status : false
                     */

                    @SerializedName("count")
                    private int count;
                    @SerializedName("forbidden")
                    private boolean forbidden;
                    @SerializedName("status")
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

        public static class OrigModel {
            /**
             * basic : {"comment_id_str":"","comment_type":0,"like_icon":{"action_url":"","end_url":"","id":0,"start_url":""},"rid_str":""}
             * id_str : 738026577357439095
             * modules : {"module_author":{"decorate":{"card_url":"https://i0.hdslb.com/bfs/garb/item/023378f832cc070fd900eca65f26d75164d61df1.png","fan":{"color":"#fe7491","is_fan":true,"num_str":"162142","number":162142},"id":4018,"jump_url":"https://www.bilibili.com/h5/mall/equity-link/home?navhide=1&item_id=4019&part=card&f_source=garb&from=post&isdiy=0","name":"不问天粉丝专属","type":3},"face":"https://i2.hdslb.com/bfs/face/dfb3deb851933de35aef8975857e9d6c662ca834.jpg","face_nft":false,"following":null,"jump_url":"//space.bilibili.com/17466521/dynamic","label":"","mid":17466521,"name":"风羽酱-sdk","official_verify":{"desc":"","type":0},"pendant":{"expire":0,"image":"https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png","image_enhance":"https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png","image_enhance_frame":"","name":"citrus","pid":217},"pub_action":"与他人联合创作","pub_time":"","pub_ts":1670673603,"type":"AUTHOR_TYPE_NORMAL","vip":{"avatar_subscript":1,"avatar_subscript_url":"","due_date":1794153600000,"label":{"bg_color":"#FB7299","bg_style":1,"border_color":"","img_label_uri_hans":"","img_label_uri_hans_static":"https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png","img_label_uri_hant":"","img_label_uri_hant_static":"https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png","label_theme":"annual_vip","path":"","text":"年度大会员","text_color":"#FFFFFF","use_img_label":true},"nickname_color":"#FB7299","status":1,"theme_type":0,"type":2}},"module_dynamic":{"additional":null,"desc":{"rich_text_nodes":[{"orig_text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","type":"RICH_TEXT_NODE_TYPE_TEXT"}],"text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！"},"major":{"archive":{"aid":"221080274","badge":{"bg_color":"#FB7299","color":"#FFFFFF","text":"合作视频"},"bvid":"BV198411G7fa","cover":"http://i2.hdslb.com/bfs/archive/d6ab6491045b3138e5afb1caa0936aa1723f3f0b.jpg","desc":"这是一部献给风云自然的纪录电影\u2026\u2026\n请开启【HDR真彩】【杜比全景声】，以获得最佳视听体验\n\n----\n作品简介：\n风暴之下，是一部以第一视角展现激烈天气以及灾害天气后果的参与型自然纪录片。全片视角来自于导演作为追风者的第一视点，并与一群天气追逐者一起走遍华夏大地，感受不同的风暴，理解风暴与人类社会的关系。最终，\u201c风暴之下\u201d将是我们对于\u201c天与地\u201d\u201c气候变化与人\u201d的思考。风暴之下由导演耗时约5年完成，在华夏大地奔走数万公里，记录到这些特殊的天气现象。\n\nSoul of Storm is a particip","disable_preview":0,"duration_text":"29:05","jump_url":"//www.bilibili.com/video/BV198411G7fa/","stat":{"danmaku":"885","play":"10.6万"},"title":"风暴之下 - 中国首部风暴纪录电影","type":1},"type":"MAJOR_TYPE_ARCHIVE"},"topic":null}}
             * type : DYNAMIC_TYPE_AV
             * visible : true
             */

            @SerializedName("basic")
            private BasicModelX basic;
            @SerializedName("id_str")
            private String idStr;
            @SerializedName("modules")
            private ModulesModelX modules;
            @SerializedName("type")
            private String type;
            @SerializedName("visible")
            private boolean visible;

            public BasicModelX getBasic() {
                return basic;
            }

            public void setBasic(BasicModelX basic) {
                this.basic = basic;
            }

            public String getIdStr() {
                return idStr;
            }

            public void setIdStr(String idStr) {
                this.idStr = idStr;
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

            public static class BasicModelX {
                /**
                 * comment_id_str :
                 * comment_type : 0
                 * like_icon : {"action_url":"","end_url":"","id":0,"start_url":""}
                 * rid_str :
                 */

                @SerializedName("comment_id_str")
                private String commentIdStr;
                @SerializedName("comment_type")
                private int commentType;
                @SerializedName("like_icon")
                private LikeIconModelX likeIcon;
                @SerializedName("rid_str")
                private String ridStr;

                public String getCommentIdStr() {
                    return commentIdStr;
                }

                public void setCommentIdStr(String commentIdStr) {
                    this.commentIdStr = commentIdStr;
                }

                public int getCommentType() {
                    return commentType;
                }

                public void setCommentType(int commentType) {
                    this.commentType = commentType;
                }

                public LikeIconModelX getLikeIcon() {
                    return likeIcon;
                }

                public void setLikeIcon(LikeIconModelX likeIcon) {
                    this.likeIcon = likeIcon;
                }

                public String getRidStr() {
                    return ridStr;
                }

                public void setRidStr(String ridStr) {
                    this.ridStr = ridStr;
                }

                public static class LikeIconModelX {
                    /**
                     * action_url :
                     * end_url :
                     * id : 0
                     * start_url :
                     */

                    @SerializedName("action_url")
                    private String actionUrl;
                    @SerializedName("end_url")
                    private String endUrl;
                    @SerializedName("id")
                    private int id;
                    @SerializedName("start_url")
                    private String startUrl;

                    public String getActionUrl() {
                        return actionUrl;
                    }

                    public void setActionUrl(String actionUrl) {
                        this.actionUrl = actionUrl;
                    }

                    public String getEndUrl() {
                        return endUrl;
                    }

                    public void setEndUrl(String endUrl) {
                        this.endUrl = endUrl;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getStartUrl() {
                        return startUrl;
                    }

                    public void setStartUrl(String startUrl) {
                        this.startUrl = startUrl;
                    }
                }
            }

            public static class ModulesModelX {
                /**
                 * module_author : {"decorate":{"card_url":"https://i0.hdslb.com/bfs/garb/item/023378f832cc070fd900eca65f26d75164d61df1.png","fan":{"color":"#fe7491","is_fan":true,"num_str":"162142","number":162142},"id":4018,"jump_url":"https://www.bilibili.com/h5/mall/equity-link/home?navhide=1&item_id=4019&part=card&f_source=garb&from=post&isdiy=0","name":"不问天粉丝专属","type":3},"face":"https://i2.hdslb.com/bfs/face/dfb3deb851933de35aef8975857e9d6c662ca834.jpg","face_nft":false,"following":null,"jump_url":"//space.bilibili.com/17466521/dynamic","label":"","mid":17466521,"name":"风羽酱-sdk","official_verify":{"desc":"","type":0},"pendant":{"expire":0,"image":"https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png","image_enhance":"https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png","image_enhance_frame":"","name":"citrus","pid":217},"pub_action":"与他人联合创作","pub_time":"","pub_ts":1670673603,"type":"AUTHOR_TYPE_NORMAL","vip":{"avatar_subscript":1,"avatar_subscript_url":"","due_date":1794153600000,"label":{"bg_color":"#FB7299","bg_style":1,"border_color":"","img_label_uri_hans":"","img_label_uri_hans_static":"https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png","img_label_uri_hant":"","img_label_uri_hant_static":"https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png","label_theme":"annual_vip","path":"","text":"年度大会员","text_color":"#FFFFFF","use_img_label":true},"nickname_color":"#FB7299","status":1,"theme_type":0,"type":2}}
                 * module_dynamic : {"additional":null,"desc":{"rich_text_nodes":[{"orig_text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","type":"RICH_TEXT_NODE_TYPE_TEXT"}],"text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！"},"major":{"archive":{"aid":"221080274","badge":{"bg_color":"#FB7299","color":"#FFFFFF","text":"合作视频"},"bvid":"BV198411G7fa","cover":"http://i2.hdslb.com/bfs/archive/d6ab6491045b3138e5afb1caa0936aa1723f3f0b.jpg","desc":"这是一部献给风云自然的纪录电影\u2026\u2026\n请开启【HDR真彩】【杜比全景声】，以获得最佳视听体验\n\n----\n作品简介：\n风暴之下，是一部以第一视角展现激烈天气以及灾害天气后果的参与型自然纪录片。全片视角来自于导演作为追风者的第一视点，并与一群天气追逐者一起走遍华夏大地，感受不同的风暴，理解风暴与人类社会的关系。最终，\u201c风暴之下\u201d将是我们对于\u201c天与地\u201d\u201c气候变化与人\u201d的思考。风暴之下由导演耗时约5年完成，在华夏大地奔走数万公里，记录到这些特殊的天气现象。\n\nSoul of Storm is a particip","disable_preview":0,"duration_text":"29:05","jump_url":"//www.bilibili.com/video/BV198411G7fa/","stat":{"danmaku":"885","play":"10.6万"},"title":"风暴之下 - 中国首部风暴纪录电影","type":1},"type":"MAJOR_TYPE_ARCHIVE"},"topic":null}
                 */

                @SerializedName("module_author")
                private ModuleAuthorModelX moduleAuthor;
                @SerializedName("module_dynamic")
                private ModuleDynamicModelX moduleDynamic;

                public ModuleAuthorModelX getModuleAuthor() {
                    return moduleAuthor;
                }

                public void setModuleAuthor(ModuleAuthorModelX moduleAuthor) {
                    this.moduleAuthor = moduleAuthor;
                }

                public ModuleDynamicModelX getModuleDynamic() {
                    return moduleDynamic;
                }

                public void setModuleDynamic(ModuleDynamicModelX moduleDynamic) {
                    this.moduleDynamic = moduleDynamic;
                }

                public static class ModuleAuthorModelX {
                    /**
                     * decorate : {"card_url":"https://i0.hdslb.com/bfs/garb/item/023378f832cc070fd900eca65f26d75164d61df1.png","fan":{"color":"#fe7491","is_fan":true,"num_str":"162142","number":162142},"id":4018,"jump_url":"https://www.bilibili.com/h5/mall/equity-link/home?navhide=1&item_id=4019&part=card&f_source=garb&from=post&isdiy=0","name":"不问天粉丝专属","type":3}
                     * face : https://i2.hdslb.com/bfs/face/dfb3deb851933de35aef8975857e9d6c662ca834.jpg
                     * face_nft : false
                     * following : null
                     * jump_url : //space.bilibili.com/17466521/dynamic
                     * label :
                     * mid : 17466521
                     * name : 风羽酱-sdk
                     * official_verify : {"desc":"","type":0}
                     * pendant : {"expire":0,"image":"https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png","image_enhance":"https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png","image_enhance_frame":"","name":"citrus","pid":217}
                     * pub_action : 与他人联合创作
                     * pub_time :
                     * pub_ts : 1670673603
                     * type : AUTHOR_TYPE_NORMAL
                     * vip : {"avatar_subscript":1,"avatar_subscript_url":"","due_date":1794153600000,"label":{"bg_color":"#FB7299","bg_style":1,"border_color":"","img_label_uri_hans":"","img_label_uri_hans_static":"https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png","img_label_uri_hant":"","img_label_uri_hant_static":"https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png","label_theme":"annual_vip","path":"","text":"年度大会员","text_color":"#FFFFFF","use_img_label":true},"nickname_color":"#FB7299","status":1,"theme_type":0,"type":2}
                     */

                    @SerializedName("decorate")
                    private DecorateModelX decorate;
                    @SerializedName("face")
                    private String face;
                    @SerializedName("face_nft")
                    private boolean faceNft;
                    @SerializedName("following")
                    private Object following;
                    @SerializedName("jump_url")
                    private String jumpUrl;
                    @SerializedName("label")
                    private String label;
                    @SerializedName("mid")
                    private int mid;
                    @SerializedName("name")
                    private String name;
                    @SerializedName("official_verify")
                    private OfficialVerifyModelX officialVerify;
                    @SerializedName("pendant")
                    private PendantModelX pendant;
                    @SerializedName("pub_action")
                    private String pubAction;
                    @SerializedName("pub_time")
                    private String pubTime;
                    @SerializedName("pub_ts")
                    private int pubTs;
                    @SerializedName("type")
                    private String type;
                    @SerializedName("vip")
                    private VipModelX vip;

                    public DecorateModelX getDecorate() {
                        return decorate;
                    }

                    public void setDecorate(DecorateModelX decorate) {
                        this.decorate = decorate;
                    }

                    public String getFace() {
                        return face;
                    }

                    public void setFace(String face) {
                        this.face = face;
                    }

                    public boolean isFaceNft() {
                        return faceNft;
                    }

                    public void setFaceNft(boolean faceNft) {
                        this.faceNft = faceNft;
                    }

                    public Object getFollowing() {
                        return following;
                    }

                    public void setFollowing(Object following) {
                        this.following = following;
                    }

                    public String getJumpUrl() {
                        return jumpUrl;
                    }

                    public void setJumpUrl(String jumpUrl) {
                        this.jumpUrl = jumpUrl;
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

                    public OfficialVerifyModelX getOfficialVerify() {
                        return officialVerify;
                    }

                    public void setOfficialVerify(OfficialVerifyModelX officialVerify) {
                        this.officialVerify = officialVerify;
                    }

                    public PendantModelX getPendant() {
                        return pendant;
                    }

                    public void setPendant(PendantModelX pendant) {
                        this.pendant = pendant;
                    }

                    public String getPubAction() {
                        return pubAction;
                    }

                    public void setPubAction(String pubAction) {
                        this.pubAction = pubAction;
                    }

                    public String getPubTime() {
                        return pubTime;
                    }

                    public void setPubTime(String pubTime) {
                        this.pubTime = pubTime;
                    }

                    public int getPubTs() {
                        return pubTs;
                    }

                    public void setPubTs(int pubTs) {
                        this.pubTs = pubTs;
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

                    public static class DecorateModelX {
                        /**
                         * card_url : https://i0.hdslb.com/bfs/garb/item/023378f832cc070fd900eca65f26d75164d61df1.png
                         * fan : {"color":"#fe7491","is_fan":true,"num_str":"162142","number":162142}
                         * id : 4018
                         * jump_url : https://www.bilibili.com/h5/mall/equity-link/home?navhide=1&item_id=4019&part=card&f_source=garb&from=post&isdiy=0
                         * name : 不问天粉丝专属
                         * type : 3
                         */

                        @SerializedName("card_url")
                        private String cardUrl;
                        @SerializedName("fan")
                        private FanModelX fan;
                        @SerializedName("id")
                        private int id;
                        @SerializedName("jump_url")
                        private String jumpUrl;
                        @SerializedName("name")
                        private String name;
                        @SerializedName("type")
                        private int type;

                        public String getCardUrl() {
                            return cardUrl;
                        }

                        public void setCardUrl(String cardUrl) {
                            this.cardUrl = cardUrl;
                        }

                        public FanModelX getFan() {
                            return fan;
                        }

                        public void setFan(FanModelX fan) {
                            this.fan = fan;
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getJumpUrl() {
                            return jumpUrl;
                        }

                        public void setJumpUrl(String jumpUrl) {
                            this.jumpUrl = jumpUrl;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }

                        public static class FanModelX {
                            /**
                             * color : #fe7491
                             * is_fan : true
                             * num_str : 162142
                             * number : 162142
                             */

                            @SerializedName("color")
                            private String color;
                            @SerializedName("is_fan")
                            private boolean isFan;
                            @SerializedName("num_str")
                            private String numStr;
                            @SerializedName("number")
                            private int number;

                            public String getColor() {
                                return color;
                            }

                            public void setColor(String color) {
                                this.color = color;
                            }

                            public boolean isIsFan() {
                                return isFan;
                            }

                            public void setIsFan(boolean isFan) {
                                this.isFan = isFan;
                            }

                            public String getNumStr() {
                                return numStr;
                            }

                            public void setNumStr(String numStr) {
                                this.numStr = numStr;
                            }

                            public int getNumber() {
                                return number;
                            }

                            public void setNumber(int number) {
                                this.number = number;
                            }
                        }
                    }

                    public static class OfficialVerifyModelX {
                        /**
                         * desc :
                         * type : 0
                         */

                        @SerializedName("desc")
                        private String desc;
                        @SerializedName("type")
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

                    public static class PendantModelX {
                        /**
                         * expire : 0
                         * image : https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png
                         * image_enhance : https://i2.hdslb.com/bfs/face/f2d3a85b611eb2ef13cb64415187e3e9fd487ec8.png
                         * image_enhance_frame :
                         * name : citrus
                         * pid : 217
                         */

                        @SerializedName("expire")
                        private int expire;
                        @SerializedName("image")
                        private String image;
                        @SerializedName("image_enhance")
                        private String imageEnhance;
                        @SerializedName("image_enhance_frame")
                        private String imageEnhanceFrame;
                        @SerializedName("name")
                        private String name;
                        @SerializedName("pid")
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

                        public String getImageEnhance() {
                            return imageEnhance;
                        }

                        public void setImageEnhance(String imageEnhance) {
                            this.imageEnhance = imageEnhance;
                        }

                        public String getImageEnhanceFrame() {
                            return imageEnhanceFrame;
                        }

                        public void setImageEnhanceFrame(String imageEnhanceFrame) {
                            this.imageEnhanceFrame = imageEnhanceFrame;
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

                    public static class VipModelX {
                        /**
                         * avatar_subscript : 1
                         * avatar_subscript_url :
                         * due_date : 1794153600000
                         * label : {"bg_color":"#FB7299","bg_style":1,"border_color":"","img_label_uri_hans":"","img_label_uri_hans_static":"https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png","img_label_uri_hant":"","img_label_uri_hant_static":"https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png","label_theme":"annual_vip","path":"","text":"年度大会员","text_color":"#FFFFFF","use_img_label":true}
                         * nickname_color : #FB7299
                         * status : 1
                         * theme_type : 0
                         * type : 2
                         */

                        @SerializedName("avatar_subscript")
                        private int avatarSubscript;
                        @SerializedName("avatar_subscript_url")
                        private String avatarSubscriptUrl;
                        @SerializedName("due_date")
                        private long dueDate;
                        @SerializedName("label")
                        private LabelModelX label;
                        @SerializedName("nickname_color")
                        private String nicknameColor;
                        @SerializedName("status")
                        private int status;
                        @SerializedName("theme_type")
                        private int themeType;
                        @SerializedName("type")
                        private int type;

                        public int getAvatarSubscript() {
                            return avatarSubscript;
                        }

                        public void setAvatarSubscript(int avatarSubscript) {
                            this.avatarSubscript = avatarSubscript;
                        }

                        public String getAvatarSubscriptUrl() {
                            return avatarSubscriptUrl;
                        }

                        public void setAvatarSubscriptUrl(String avatarSubscriptUrl) {
                            this.avatarSubscriptUrl = avatarSubscriptUrl;
                        }

                        public long getDueDate() {
                            return dueDate;
                        }

                        public void setDueDate(long dueDate) {
                            this.dueDate = dueDate;
                        }

                        public LabelModelX getLabel() {
                            return label;
                        }

                        public void setLabel(LabelModelX label) {
                            this.label = label;
                        }

                        public String getNicknameColor() {
                            return nicknameColor;
                        }

                        public void setNicknameColor(String nicknameColor) {
                            this.nicknameColor = nicknameColor;
                        }

                        public int getStatus() {
                            return status;
                        }

                        public void setStatus(int status) {
                            this.status = status;
                        }

                        public int getThemeType() {
                            return themeType;
                        }

                        public void setThemeType(int themeType) {
                            this.themeType = themeType;
                        }

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }

                        public static class LabelModelX {
                            /**
                             * bg_color : #FB7299
                             * bg_style : 1
                             * border_color :
                             * img_label_uri_hans :
                             * img_label_uri_hans_static : https://i0.hdslb.com/bfs/vip/8d4f8bfc713826a5412a0a27eaaac4d6b9ede1d9.png
                             * img_label_uri_hant :
                             * img_label_uri_hant_static : https://i0.hdslb.com/bfs/activity-plat/static/20220614/e369244d0b14644f5e1a06431e22a4d5/VEW8fCC0hg.png
                             * label_theme : annual_vip
                             * path :
                             * text : 年度大会员
                             * text_color : #FFFFFF
                             * use_img_label : true
                             */

                            @SerializedName("bg_color")
                            private String bgColor;
                            @SerializedName("bg_style")
                            private int bgStyle;
                            @SerializedName("border_color")
                            private String borderColor;
                            @SerializedName("img_label_uri_hans")
                            private String imgLabelUriHans;
                            @SerializedName("img_label_uri_hans_static")
                            private String imgLabelUriHansStatic;
                            @SerializedName("img_label_uri_hant")
                            private String imgLabelUriHant;
                            @SerializedName("img_label_uri_hant_static")
                            private String imgLabelUriHantStatic;
                            @SerializedName("label_theme")
                            private String labelTheme;
                            @SerializedName("path")
                            private String path;
                            @SerializedName("text")
                            private String text;
                            @SerializedName("text_color")
                            private String textColor;
                            @SerializedName("use_img_label")
                            private boolean useImgLabel;

                            public String getBgColor() {
                                return bgColor;
                            }

                            public void setBgColor(String bgColor) {
                                this.bgColor = bgColor;
                            }

                            public int getBgStyle() {
                                return bgStyle;
                            }

                            public void setBgStyle(int bgStyle) {
                                this.bgStyle = bgStyle;
                            }

                            public String getBorderColor() {
                                return borderColor;
                            }

                            public void setBorderColor(String borderColor) {
                                this.borderColor = borderColor;
                            }

                            public String getImgLabelUriHans() {
                                return imgLabelUriHans;
                            }

                            public void setImgLabelUriHans(String imgLabelUriHans) {
                                this.imgLabelUriHans = imgLabelUriHans;
                            }

                            public String getImgLabelUriHansStatic() {
                                return imgLabelUriHansStatic;
                            }

                            public void setImgLabelUriHansStatic(String imgLabelUriHansStatic) {
                                this.imgLabelUriHansStatic = imgLabelUriHansStatic;
                            }

                            public String getImgLabelUriHant() {
                                return imgLabelUriHant;
                            }

                            public void setImgLabelUriHant(String imgLabelUriHant) {
                                this.imgLabelUriHant = imgLabelUriHant;
                            }

                            public String getImgLabelUriHantStatic() {
                                return imgLabelUriHantStatic;
                            }

                            public void setImgLabelUriHantStatic(String imgLabelUriHantStatic) {
                                this.imgLabelUriHantStatic = imgLabelUriHantStatic;
                            }

                            public String getLabelTheme() {
                                return labelTheme;
                            }

                            public void setLabelTheme(String labelTheme) {
                                this.labelTheme = labelTheme;
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

                            public String getTextColor() {
                                return textColor;
                            }

                            public void setTextColor(String textColor) {
                                this.textColor = textColor;
                            }

                            public boolean isUseImgLabel() {
                                return useImgLabel;
                            }

                            public void setUseImgLabel(boolean useImgLabel) {
                                this.useImgLabel = useImgLabel;
                            }
                        }
                    }
                }

                public static class ModuleDynamicModelX {
                    /**
                     * additional : null
                     * desc : {"rich_text_nodes":[{"orig_text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","type":"RICH_TEXT_NODE_TYPE_TEXT"}],"text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！"}
                     * major : {"archive":{"aid":"221080274","badge":{"bg_color":"#FB7299","color":"#FFFFFF","text":"合作视频"},"bvid":"BV198411G7fa","cover":"http://i2.hdslb.com/bfs/archive/d6ab6491045b3138e5afb1caa0936aa1723f3f0b.jpg","desc":"这是一部献给风云自然的纪录电影\u2026\u2026\n请开启【HDR真彩】【杜比全景声】，以获得最佳视听体验\n\n----\n作品简介：\n风暴之下，是一部以第一视角展现激烈天气以及灾害天气后果的参与型自然纪录片。全片视角来自于导演作为追风者的第一视点，并与一群天气追逐者一起走遍华夏大地，感受不同的风暴，理解风暴与人类社会的关系。最终，\u201c风暴之下\u201d将是我们对于\u201c天与地\u201d\u201c气候变化与人\u201d的思考。风暴之下由导演耗时约5年完成，在华夏大地奔走数万公里，记录到这些特殊的天气现象。\n\nSoul of Storm is a particip","disable_preview":0,"duration_text":"29:05","jump_url":"//www.bilibili.com/video/BV198411G7fa/","stat":{"danmaku":"885","play":"10.6万"},"title":"风暴之下 - 中国首部风暴纪录电影","type":1},"type":"MAJOR_TYPE_ARCHIVE"}
                     * topic : null
                     */

                    @SerializedName("additional")
                    private Object additional;
                    @SerializedName("desc")
                    private DescModel desc;
                    @SerializedName("major")
                    private MajorModelX major;
                    @SerializedName("topic")
                    private Object topic;

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

                    public static class DescModel {
                        /**
                         * rich_text_nodes : [{"orig_text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","text":"这是一部献给天空的纪录电影\u2026\u2026为期5年，这次一定！","type":"RICH_TEXT_NODE_TYPE_TEXT"}]
                         * text : 这是一部献给天空的纪录电影……为期5年，这次一定！
                         */

                        @SerializedName("text")
                        private String text;
                        @SerializedName("rich_text_nodes")
                        private List<RichTextNodesModel> richTextNodes;

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                        public List<RichTextNodesModel> getRichTextNodes() {
                            return richTextNodes;
                        }

                        public void setRichTextNodes(List<RichTextNodesModel> richTextNodes) {
                            this.richTextNodes = richTextNodes;
                        }

                        public static class RichTextNodesModel {
                            /**
                             * orig_text : 这是一部献给天空的纪录电影……为期5年，这次一定！
                             * text : 这是一部献给天空的纪录电影……为期5年，这次一定！
                             * type : RICH_TEXT_NODE_TYPE_TEXT
                             */

                            @SerializedName("orig_text")
                            private String origText;
                            @SerializedName("text")
                            private String text;
                            @SerializedName("type")
                            private String type;

                            public String getOrigText() {
                                return origText;
                            }

                            public void setOrigText(String origText) {
                                this.origText = origText;
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

                    public static class MajorModelX {
                        /**
                         * archive : {"aid":"221080274","badge":{"bg_color":"#FB7299","color":"#FFFFFF","text":"合作视频"},"bvid":"BV198411G7fa","cover":"http://i2.hdslb.com/bfs/archive/d6ab6491045b3138e5afb1caa0936aa1723f3f0b.jpg","desc":"这是一部献给风云自然的纪录电影\u2026\u2026\n请开启【HDR真彩】【杜比全景声】，以获得最佳视听体验\n\n----\n作品简介：\n风暴之下，是一部以第一视角展现激烈天气以及灾害天气后果的参与型自然纪录片。全片视角来自于导演作为追风者的第一视点，并与一群天气追逐者一起走遍华夏大地，感受不同的风暴，理解风暴与人类社会的关系。最终，\u201c风暴之下\u201d将是我们对于\u201c天与地\u201d\u201c气候变化与人\u201d的思考。风暴之下由导演耗时约5年完成，在华夏大地奔走数万公里，记录到这些特殊的天气现象。\n\nSoul of Storm is a particip","disable_preview":0,"duration_text":"29:05","jump_url":"//www.bilibili.com/video/BV198411G7fa/","stat":{"danmaku":"885","play":"10.6万"},"title":"风暴之下 - 中国首部风暴纪录电影","type":1}
                         * type : MAJOR_TYPE_ARCHIVE
                         */

                        @SerializedName("archive")
                        private ArchiveModel archive;
                        @SerializedName("type")
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

                        public static class ArchiveModel {
                            /**
                             * aid : 221080274
                             * badge : {"bg_color":"#FB7299","color":"#FFFFFF","text":"合作视频"}
                             * bvid : BV198411G7fa
                             * cover : http://i2.hdslb.com/bfs/archive/d6ab6491045b3138e5afb1caa0936aa1723f3f0b.jpg
                             * desc : 这是一部献给风云自然的纪录电影……
                             * 请开启【HDR真彩】【杜比全景声】，以获得最佳视听体验
                             * <p>
                             * ----
                             * 作品简介：
                             * 风暴之下，是一部以第一视角展现激烈天气以及灾害天气后果的参与型自然纪录片。全片视角来自于导演作为追风者的第一视点，并与一群天气追逐者一起走遍华夏大地，感受不同的风暴，理解风暴与人类社会的关系。最终，“风暴之下”将是我们对于“天与地”“气候变化与人”的思考。风暴之下由导演耗时约5年完成，在华夏大地奔走数万公里，记录到这些特殊的天气现象。
                             * <p>
                             * Soul of Storm is a particip
                             * disable_preview : 0
                             * duration_text : 29:05
                             * jump_url : //www.bilibili.com/video/BV198411G7fa/
                             * stat : {"danmaku":"885","play":"10.6万"}
                             * title : 风暴之下 - 中国首部风暴纪录电影
                             * type : 1
                             */

                            @SerializedName("aid")
                            private String aid;
                            @SerializedName("badge")
                            private BadgeModel badge;
                            @SerializedName("bvid")
                            private String bvid;
                            @SerializedName("cover")
                            private String cover;
                            @SerializedName("desc")
                            private String desc;
                            @SerializedName("disable_preview")
                            private int disablePreview;
                            @SerializedName("duration_text")
                            private String durationText;
                            @SerializedName("jump_url")
                            private String jumpUrl;
                            @SerializedName("stat")
                            private StatModel stat;
                            @SerializedName("title")
                            private String title;
                            @SerializedName("type")
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

                            public int getDisablePreview() {
                                return disablePreview;
                            }

                            public void setDisablePreview(int disablePreview) {
                                this.disablePreview = disablePreview;
                            }

                            public String getDurationText() {
                                return durationText;
                            }

                            public void setDurationText(String durationText) {
                                this.durationText = durationText;
                            }

                            public String getJumpUrl() {
                                return jumpUrl;
                            }

                            public void setJumpUrl(String jumpUrl) {
                                this.jumpUrl = jumpUrl;
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

                            public static class BadgeModel {
                                /**
                                 * bg_color : #FB7299
                                 * color : #FFFFFF
                                 * text : 合作视频
                                 */

                                @SerializedName("bg_color")
                                private String bgColor;
                                @SerializedName("color")
                                private String color;
                                @SerializedName("text")
                                private String text;

                                public String getBgColor() {
                                    return bgColor;
                                }

                                public void setBgColor(String bgColor) {
                                    this.bgColor = bgColor;
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

                            public static class StatModel {
                                /**
                                 * danmaku : 885
                                 * play : 10.6万
                                 */

                                @SerializedName("danmaku")
                                private String danmaku;
                                @SerializedName("play")
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
