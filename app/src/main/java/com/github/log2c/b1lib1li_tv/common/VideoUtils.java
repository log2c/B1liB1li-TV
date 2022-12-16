package com.github.log2c.b1lib1li_tv.common;

import com.blankj.utilcode.util.StringUtils;
import com.google.common.base.Ascii;

public class VideoUtils {

    public static boolean isH264(String codec) {
        if (StringUtils.isTrimEmpty(codec)) {
            return false;
        }
        codec = Ascii.toLowerCase(codec.trim());
        return codec.startsWith("avc1") || codec.startsWith("avc3");
    }

    public static boolean isH265(String codec) {
        if (StringUtils.isTrimEmpty(codec)) {
            return false;
        }
        codec = Ascii.toLowerCase(codec.trim());
        return codec.startsWith("hev1") || codec.startsWith("hvc1");
    }
}
