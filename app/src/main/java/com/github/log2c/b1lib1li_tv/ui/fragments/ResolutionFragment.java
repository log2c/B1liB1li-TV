package com.github.log2c.b1lib1li_tv.ui.fragments;

import android.os.Bundle;

import androidx.leanback.preference.LeanbackListPreferenceDialogFragmentCompat;
import androidx.leanback.preference.LeanbackPreferenceDialogFragmentCompat;
import androidx.preference.DialogPreference;
import androidx.preference.ListPreference;

import java.util.ArrayList;
import java.util.List;


public class ResolutionFragment extends LeanbackListPreferenceDialogFragmentCompat {
    public static final String KEY_ITEM = "items";
    public static final String KEY_SELECTED = "position";
    private List<String> items = new ArrayList<>();
    private int selected = -1;

    @Override
    public DialogPreference getPreference() {
        ListPreference listPreference = new ListPreference(requireContext());
        CharSequence[] entries = new CharSequence[items.size()];
        CharSequence[] entriesValues = new CharSequence[items.size()];
        for (int i = 0; i < items.size(); i++) {
            String s = items.get(i);
            entries[i] = s;
            entriesValues[i] = s;
        }
        listPreference.setEntries(entries);
        listPreference.setEntryValues(entriesValues);
        return listPreference;
    }

    //    @Override
//    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//        if (getArguments() == null) {
//            return;
//        }
//        ArrayList<String> list = getArguments().getStringArrayList(KEY_ITEM);
//        if (list != null) {
//            items.addAll(list);
//        }
//        selected = getArguments().getInt(KEY_SELECTED, -1);
//        addItems(items, selected);
//    }


    public static ResolutionFragment newInstance(List<String> items, int selectPosition) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(KEY_ITEM, new ArrayList<>(items));
        bundle.putInt(KEY_SELECTED, selectPosition);
        ResolutionFragment fragment = new ResolutionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
