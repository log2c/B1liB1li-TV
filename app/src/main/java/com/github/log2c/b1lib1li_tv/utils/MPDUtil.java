package com.github.log2c.b1lib1li_tv.utils;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.github.log2c.b1lib1li_tv.R;

import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MPDUtil {
//    private static final String MPD_TEMPLATE_FILE_NAME = "template.mpd";

    public static Observable<String> genMpdText(
            String bvid,
            String mediaPresentationDuration,
            String minBufferTime,
            String video_codecs,
            String video_media,
            String video_duration,
            String video_mimeType,
            String video_id,
            String video_bandwidth,
            String video_width,
            String video_height,
            String video_frameRate,
            String audio_mimeType,
            String audio_codecs,
            String audio_duration,
            String audio_id,
            String audio_bandwidth,
            String audio_media
    ) {
        return Observable.just(1)
                .map(a -> (ResourceUtils.readRaw2String(R.raw.template_mpd)))
                .subscribeOn(Schedulers.io())
                .map((text) -> {
                    final Map<String, String> valuesMap = new HashMap<>();
                    valuesMap.put("mediaPresentationDuration", mediaPresentationDuration);
                    valuesMap.put("minBufferTime", minBufferTime);
                    valuesMap.put("video_codecs", video_codecs);
                    valuesMap.put("video_media", video_media);
                    valuesMap.put("video_duration", video_duration);
                    valuesMap.put("video_mimeType", video_mimeType);
                    valuesMap.put("video_id", video_id);
                    valuesMap.put("video_bandwidth", video_bandwidth);
                    valuesMap.put("video_width", video_width);
                    valuesMap.put("video_height", video_height);
                    valuesMap.put("video_frameRate", video_frameRate);
                    valuesMap.put("audio_mimeType", audio_mimeType);
                    valuesMap.put("audio_codecs", audio_codecs);
                    valuesMap.put("audio_duration", audio_duration);
                    valuesMap.put("audio_id", audio_id);
                    valuesMap.put("audio_bandwidt", audio_bandwidth);
                    valuesMap.put("audio_media", audio_media);
                    return new StringSubstitutor(valuesMap).replace(text);
                })
                .map(mpd_content -> {
                    final String mpdCacheDir = PathUtils.join(PathUtils.getInternalAppCachePath(), "mpd_medias");
                    boolean orExistsDir = FileUtils.createOrExistsDir(mpdCacheDir);
                    String dstPath = PathUtils.join(mpdCacheDir, bvid + ".mpd");
                    FileIOUtils.writeFileFromString(dstPath, mpd_content);
                    return dstPath;
                });
    }
}
