package com.github.log2c.b1lib1li_tv.update;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.widget.Toast;

import com.azhon.appupdate.manager.DownloadManager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.model.UpdateModel;
import com.github.log2c.base.toast.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rxhttp.network.RxHttp;

public class UpdateManager {
    private static final String CHECK_URL = "https://api.github.com/repos/log2c/B1liB1li-TV/releases/latest";
    private static UpdateManager mInstance;

    private UpdateManager() {
    }

    public static UpdateManager getInstance() {
        if (mInstance == null) {
            mInstance = new UpdateManager();
        }
        return mInstance;
    }

    public void checkUpdate() {
        checkUpdate(true);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void checkUpdate(boolean silent) {
        check(silent).subscribe(updateModel -> {
            String downloadUrl = getApkDownloadUrl(updateModel);
            if (TextUtils.isEmpty(downloadUrl) && !silent) {
                Toast.makeText(Utils.getApp(), "下载地址错误.", Toast.LENGTH_LONG).show();
                return;
            }
            DownloadManager.Builder builder = new DownloadManager.Builder(ActivityUtils.getTopActivity());
            builder.apkUrl(downloadUrl);
            builder.apkName(getApkDownloadName(updateModel));
            builder.smallIcon(R.mipmap.ic_launcher);
            builder.apkVersionName(updateModel.getName());
            builder.apkSize(getApkDownloadSize(updateModel));
            builder.apkDescription(updateModel.getBody());
            builder.build().download();
        }, e -> {
            e.printStackTrace();
            if (!silent) {
                Toast.makeText(Utils.getApp(), "检查更新时发生错误, 请检查网络连接或手动更新.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getApkDownloadSize(UpdateModel updateModel) {
        for (UpdateModel.AssetsModel model : updateModel.getAssets()) {
            if (model.getName().endsWith(".apk")) {
                return model.getSize() / 1024 / 1024 + "MB";
            }
        }
        return "-1";
    }

    private String getApkDownloadName(UpdateModel updateModel) {
        for (UpdateModel.AssetsModel model : updateModel.getAssets()) {
            if (model.getName().endsWith(".apk")) {
                return model.getName();
            }
        }
        return "release.apk";
    }

    private String getApkDownloadUrl(UpdateModel updateModel) {
        for (UpdateModel.AssetsModel model : updateModel.getAssets()) {
            if (model.getName().endsWith(".apk")) {
                return model.getBrowserDownloadUrl();
            }
        }
        return null;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public Observable<UpdateModel> check(boolean silent) {
        return RxHttp.get(CHECK_URL)
                .toObservable(UpdateModel.class)
                .filter(updateModel -> {
                    String[] latestVerStr = updateModel.getName().split("\\.");
                    String[] currentVerStr = AppUtils.getAppVersionName().split("\\.");
                    for (int i = 0; i < latestVerStr.length; i++) {
                        int latest = Integer.parseInt(latestVerStr[i]);
                        int current = Integer.parseInt(currentVerStr[i]);
                        if (latest > current) { // 有更新
                            return true;
                        }
                    }
                    if (!silent) {
                        Observable.just("").observeOn(AndroidSchedulers.mainThread()).subscribe(e -> ToastUtils.success(R.string.no_upgrade_info));
                    }
                    return false;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
