package com.github.log2c.b1lib1li_tv.ui.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.leanback.preference.LeanbackPreferenceFragmentCompat;
import androidx.leanback.preference.LeanbackSettingsFragmentCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import com.blankj.utilcode.util.AppUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.update.UpdateManager;

public class SettingFragment extends LeanbackSettingsFragmentCompat {
    private final static String TAG = SettingFragment.class.getSimpleName();
    public final static String PREFERENCE_RESOURCE_ID = "preferenceResource";
    public final static String PREFERENCE_SCREEN_KEY = "screen_key";
    private PreferenceFragmentCompat mPreferenceFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getContext().getTheme().applyStyle(R.style.Leanback, true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPreferenceStartInitialScreen() {
        Log.i(TAG, "onPreferenceStartInitialScreen");
        if (getArguments() != null) {
            mPreferenceFragment = buildPreferenceFragment(getArguments().getInt(PREFERENCE_RESOURCE_ID), getArguments().getString(PREFERENCE_SCREEN_KEY));
        } else {
            mPreferenceFragment = buildPreferenceFragment(R.xml.settings, null);
        }
        startPreferenceFragment(mPreferenceFragment);
    }

    @Override
    public boolean onPreferenceStartFragment(@NonNull PreferenceFragmentCompat caller, @NonNull Preference preferenceScreen) {
        return true;
    }

    @Override
    public boolean onPreferenceStartScreen(@NonNull PreferenceFragmentCompat preferenceFragment,
                                           @NonNull PreferenceScreen preferenceScreen) {
        Log.i(TAG, "onPreferenceStartScreen");
        return true;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    private PreferenceFragmentCompat buildPreferenceFragment(int preferenceResId, String screenKey) {
        PreferenceFragmentCompat fragment;
        Bundle args = new Bundle();
        if (TextUtils.isEmpty(screenKey)) {
            fragment = new PrefFragment();
        } else {
            switch (screenKey) {
                default:
                    fragment = new PrefFragment();
                    break;
            }
        }
        args.putInt(PREFERENCE_RESOURCE_ID, preferenceResId);
        args.putString(PREFERENCE_SCREEN_KEY, screenKey);
        fragment.setArguments(args);
        return fragment;
    }

    public static class PrefFragment extends LeanbackPreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            getPreferenceManager().setSharedPreferencesName(Constants.SP_NAME_CONFIG);

            String screenKey = getArguments().getString(PREFERENCE_SCREEN_KEY, null);
            int prefResId = getArguments().getInt(PREFERENCE_RESOURCE_ID);
            if (screenKey == null) {
                addPreferencesFromResource(R.xml.settings);
            } else {
                setPreferencesFromResource(prefResId, screenKey);
            }
        }

        @Override
        public boolean onPreferenceTreeClick(@NonNull Preference preference) {
            if (preference.getKey().equals(getString(R.string.pre_key_relaunch))) {
                AppUtils.relaunchApp(true);
            } else if (preference.getKey().equals(getString(R.string.pre_key_check_update))) {
                UpdateManager.getInstance().checkUpdate();
            }
            return super.onPreferenceTreeClick(preference);
        }
    }
}
