package com.github.log2c.b1lib1li_tv.model;

import com.google.gson.annotations.SerializedName;

public class RelationDetailModel {
    @SerializedName("mid")
    private long mid;
    @SerializedName("uname")
    private String uname;
    @SerializedName("face")
    private String face;
    @SerializedName("sign")
    private String sign;
    @SerializedName("official_verify")
    private OfficialVerifyModel officialVerify;
    @SerializedName("vip")
    private VipModel vip;

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public OfficialVerifyModel getOfficialVerify() {
        return officialVerify;
    }

    public void setOfficialVerify(OfficialVerifyModel officialVerify) {
        this.officialVerify = officialVerify;
    }

    public VipModel getVip() {
        return vip;
    }

    public void setVip(VipModel vip) {
        this.vip = vip;
    }

    public static class OfficialVerifyModel {
        @SerializedName("type")
        private Integer type;
        @SerializedName("desc")
        private String desc;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class VipModel {
        @SerializedName("vipType")
        private Integer vipType;
        @SerializedName("vipDueDate")
        private Long vipDueDate;
        @SerializedName("dueRemark")
        private String dueRemark;
        @SerializedName("accessStatus")
        private Integer accessStatus;
        @SerializedName("vipStatus")
        private Integer vipStatus;
        @SerializedName("vipStatusWarn")
        private String vipStatusWarn;
        @SerializedName("themeType")
        private Integer themeType;
        @SerializedName("label")
        private LabelModel label;

        public Integer getVipType() {
            return vipType;
        }

        public void setVipType(Integer vipType) {
            this.vipType = vipType;
        }

        public Long getVipDueDate() {
            return vipDueDate;
        }

        public void setVipDueDate(Long vipDueDate) {
            this.vipDueDate = vipDueDate;
        }

        public String getDueRemark() {
            return dueRemark;
        }

        public void setDueRemark(String dueRemark) {
            this.dueRemark = dueRemark;
        }

        public Integer getAccessStatus() {
            return accessStatus;
        }

        public void setAccessStatus(Integer accessStatus) {
            this.accessStatus = accessStatus;
        }

        public Integer getVipStatus() {
            return vipStatus;
        }

        public void setVipStatus(Integer vipStatus) {
            this.vipStatus = vipStatus;
        }

        public String getVipStatusWarn() {
            return vipStatusWarn;
        }

        public void setVipStatusWarn(String vipStatusWarn) {
            this.vipStatusWarn = vipStatusWarn;
        }

        public Integer getThemeType() {
            return themeType;
        }

        public void setThemeType(Integer themeType) {
            this.themeType = themeType;
        }

        public LabelModel getLabel() {
            return label;
        }

        public void setLabel(LabelModel label) {
            this.label = label;
        }

        public static class LabelModel {
            @SerializedName("path")
            private String path;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }
}
