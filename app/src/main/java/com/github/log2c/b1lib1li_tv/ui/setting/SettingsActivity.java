package com.github.log2c.b1lib1li_tv.ui.setting;

import static com.github.log2c.b1lib1li_tv.ui.setting.SettingFragment.PREFERENCE_RESOURCE_ID;
import static com.github.log2c.b1lib1li_tv.ui.setting.SettingFragment.PREFERENCE_SCREEN_KEY;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.github.log2c.b1lib1li_tv.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        SettingFragment fragment = new SettingFragment();
        Bundle bundle = new Bundle();
        if (getIntent() != null) {
            String screenKey = getIntent().getStringExtra(PREFERENCE_SCREEN_KEY);
            int resourceId = getIntent().getIntExtra(PREFERENCE_RESOURCE_ID, 0);
            Parcelable[] data = getIntent().getParcelableArrayExtra("data");
            if (!TextUtils.isEmpty(screenKey)) {
                bundle.putString(PREFERENCE_SCREEN_KEY, screenKey);
            }
            if (resourceId != 0) {
                bundle.putInt(PREFERENCE_RESOURCE_ID, resourceId);
            }
            if (data != null && data.length > 0) {
                bundle.putParcelableArray("data", data);
            }
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, SettingFragment.class.getSimpleName())
                .commit();
    }

    public static void show(Activity activity, @Nullable int resourceId, @Nullable String screenKey, @Nullable Parcelable[] data) {
        Intent intent = new Intent(activity, SettingsActivity.class);
        intent.putExtra(PREFERENCE_RESOURCE_ID, resourceId);
        intent.putExtra(PREFERENCE_SCREEN_KEY, screenKey);
        intent.putExtra("data", data);
        activity.startActivity(intent);
    }
}