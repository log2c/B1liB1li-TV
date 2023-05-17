package com.github.log2c.b1lib1li_tv.leanback;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Checkable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.fragment.app.DialogFragment;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ResourceUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.google.android.exoplayer2.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectDialogFragment extends DialogFragment {
    private static final String TAG = SelectDialogFragment.class.getSimpleName();
    public static final String SAVE_STATE_IS_MULTI =
            "LeanbackListPreferenceDialogFragment.isMulti";
    public static final String SAVE_STATE_ENTRIES = "LeanbackListPreferenceDialogFragment.entries";
    public static final String SAVE_STATE_ENTRY_VALUES =
            "LeanbackListPreferenceDialogFragment.entryValues";
    public static final String SAVE_STATE_TITLE = "LeanbackListPreferenceDialogFragment.title";
    public static final String SAVE_STATE_MESSAGE = "LeanbackListPreferenceDialogFragment.message";
    public static final String SAVE_STATE_INITIAL_SELECTIONS =
            "LeanbackListPreferenceDialogFragment.initialSelections";
    public static final String SAVE_STATE_INITIAL_SELECTION =
            "LeanbackListPreferenceDialogFragment.initialSelection";
    private VerticalGridView mVerticalGridView;
    private boolean mMulti;
    private CharSequence mInitialSelection;
    private Set<String> mInitialSelections;
    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private CharSequence mDialogTitle;
    private CharSequence mDialogMessage;

    @Override
    public int getTheme() {
        return R.style.AlignEndDialogTheme;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.END;
        wlp.verticalMargin = 0;
        wlp.horizontalMargin = 0;
        wlp.dimAmount = 0;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        window.setAttributes(wlp);
        return dialog;
    }

    public static SelectDialogFragment newInstance(boolean multi, String title, String message, CharSequence[] entries, CharSequence[] entryValues, CharSequence initialSelection, Set<String> initialSelections) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(SAVE_STATE_TITLE, title);
        bundle.putCharSequence(SAVE_STATE_MESSAGE, message);
        bundle.putBoolean(SAVE_STATE_IS_MULTI, multi);
        bundle.putCharSequenceArray(SAVE_STATE_ENTRIES, entries);
        bundle.putCharSequenceArray(SAVE_STATE_ENTRY_VALUES, entryValues);
        if (multi) {
            if (initialSelections == null) {
                initialSelections = new HashSet<>();
            }
            bundle.putStringArray(SAVE_STATE_INITIAL_SELECTIONS,
                    initialSelections.toArray(new String[initialSelections.size()]));
        } else {
            if (TextUtils.isEmpty(initialSelection)) {
                initialSelection = "";
            }
            bundle.putCharSequence(SAVE_STATE_INITIAL_SELECTION, initialSelection);
        }
        SelectDialogFragment fragment = new SelectDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static SelectDialogFragment newSingleInstance(String title, String message, CharSequence[] entries, CharSequence[] entryValues, CharSequence initialSelection) {
        return newInstance(false, title, message, entries, entryValues, initialSelection, null);
    }

    public static SelectDialogFragment newMultiInstance(String title, String message, CharSequence[] entries, CharSequence[] entryValues, Set<String> initialSelections) {
        return newInstance(true, title, message, entries, entryValues, null, initialSelections);
    }

    private static int getResourceIdByName(String name) {
        return ResourceUtils.getIdByName(name);
    }

    private static int getLayoutIdByName(String name) {
        return ResourceUtils.getLayoutIdByName(name);
    }

    protected void onItemClick(int position) {
        if (getParentFragment() instanceof SelectDialogListener) {
            ((SelectDialogListener) getParentFragment()).onSingleClick(this, position);
        } else if (requireActivity() instanceof SelectDialogListener) {
            ((SelectDialogListener) requireActivity()).onSingleClick(this, position);
        } else {
            throw new IllegalArgumentException("请将宿主 implements SelectDialogListener.");
        }
    }

    private void onItemClick(int[] selected) {
        if (getParentFragment() instanceof SelectDialogListener) {
            ((SelectDialogListener) getParentFragment()).onMultiClick(this, selected);
        } else if (requireActivity() instanceof SelectDialogListener) {
            ((SelectDialogListener) requireActivity()).onMultiClick(this, selected);
        } else {
            throw new IllegalArgumentException("请将宿主 implements SelectDialogListener.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(STYLE_NORMAL, R.style.Leanback);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mDialogTitle = bundle.getCharSequence(SAVE_STATE_TITLE);
        mDialogMessage = bundle.getCharSequence(SAVE_STATE_MESSAGE);
        mMulti = bundle.getBoolean(SAVE_STATE_IS_MULTI);
        mEntries = bundle.getCharSequenceArray(SAVE_STATE_ENTRIES);
        mEntryValues = bundle.getCharSequenceArray(SAVE_STATE_ENTRY_VALUES);

        if (mMulti) {
            final String[] initialSelections = bundle.getStringArray(
                    SAVE_STATE_INITIAL_SELECTIONS);
            mInitialSelections = new ArraySet<>(
                    initialSelections != null ? initialSelections.length : 0);
            if (initialSelections != null) {
                Collections.addAll(mInitialSelections, initialSelections);
            }
        } else {
            mInitialSelection = bundle.getString(SAVE_STATE_INITIAL_SELECTION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_select, container);
        mVerticalGridView = view.findViewById(android.R.id.list);
        mVerticalGridView.setWindowAlignment(VerticalGridView.WINDOW_ALIGN_BOTH_EDGE);
//        mVerticalGridView.setFocusScrollStrategy(VerticalGridView.FOCUS_SCROLL_ALIGNED);
        mVerticalGridView.setAdapter(onCreateAdapter());
        mVerticalGridView.requestFocus();

        final CharSequence title = mDialogTitle;
        if (!TextUtils.isEmpty(title)) {
            final TextView titleView = (TextView) view.findViewById(getResourceIdByName("decor_title"));
            titleView.setText(title);
        }

        final CharSequence message = mDialogMessage;
        if (!TextUtils.isEmpty(message)) {
            final TextView messageView = (TextView) view.findViewById(android.R.id.message);
            messageView.setVisibility(View.VISIBLE);
            messageView.setText(message);
        }
        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (getParentFragment() instanceof SelectDialogListener) {
            ((SelectDialogListener) getParentFragment()).onSelectDialogDismiss(this);
        } else if (requireActivity() instanceof SelectDialogListener) {
            ((SelectDialogListener) requireActivity()).onSelectDialogDismiss(this);
        } else {
            throw new IllegalArgumentException("请将宿主 implements SelectDialogListener.");
        }
    }

    @SuppressWarnings("rawtypes")
    protected RecyclerView.Adapter onCreateAdapter() {
        //final DialogPreference preference = getPreference();
        if (mMulti) {
            return new SelectDialogFragment.AdapterMulti(mEntries, mEntryValues, mInitialSelections);
        } else {
            return new SelectDialogFragment.AdapterSingle(mEntries, mEntryValues, mInitialSelection);
        }
    }

    final class AdapterSingle extends RecyclerView.Adapter<SelectDialogFragment.ViewHolder>
            implements SelectDialogFragment.OnItemClickListener {

        private final CharSequence[] mEntries;
        private final CharSequence[] mEntryValues;
        private CharSequence mSelectedValue;

        AdapterSingle(CharSequence[] entries, CharSequence[] entryValues,
                      CharSequence selectedValue) {
            mEntries = entries;
            mEntryValues = entryValues;
            mSelectedValue = selectedValue;
        }

        @Override
        public SelectDialogFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View view = inflater.inflate(getLayoutIdByName("leanback_list_preference_item_single"),
                    parent, false);
            return new SelectDialogFragment.ViewHolder(view, this);
        }

        @Override
        public void onBindViewHolder(SelectDialogFragment.ViewHolder holder, int position) {
            holder.getWidgetView().setChecked(
                    TextUtils.equals(mEntryValues[position].toString(), mSelectedValue));
            holder.getTitleView().setText(mEntries[position]);
        }

        @Override
        public int getItemCount() {
            return mEntries.length;
        }

        @Override
        public void onItemClick(SelectDialogFragment.ViewHolder viewHolder) {
            final int index = viewHolder.getAbsoluteAdapterPosition();
            if (index == RecyclerView.NO_POSITION) {
                return;
            }
            viewHolder.mWidgetView.setChecked(!viewHolder.mWidgetView.isChecked());
            mSelectedValue = mEntryValues[index];
            notifyDataSetChanged();

            SelectDialogFragment.this.onItemClick(index);
        }
    }

    final class AdapterMulti extends RecyclerView.Adapter<SelectDialogFragment.ViewHolder>
            implements SelectDialogFragment.OnItemClickListener {

        private final CharSequence[] mEntries;
        private final CharSequence[] mEntryValues;
        private final Set<String> mSelections;

        AdapterMulti(CharSequence[] entries, CharSequence[] entryValues,
                     Set<String> initialSelections) {
            mEntries = entries;
            mEntryValues = entryValues;
            mSelections = new HashSet<>(initialSelections);
        }

        @Override
        public SelectDialogFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View view = inflater.inflate(getLayoutIdByName("leanback_list_preference_item_multi"), parent,
                    false);
            return new SelectDialogFragment.ViewHolder(view, this);
        }

        @Override
        public void onBindViewHolder(SelectDialogFragment.ViewHolder holder, int position) {
            holder.getWidgetView().setChecked(
                    mSelections.contains(mEntryValues[position].toString()));
            holder.getTitleView().setText(mEntries[position]);
        }

        @Override
        public int getItemCount() {
            return mEntries.length;
        }

        @Override
        public void onItemClick(SelectDialogFragment.ViewHolder viewHolder) {
            final int index = viewHolder.getAbsoluteAdapterPosition();
            if (index == RecyclerView.NO_POSITION) {
                return;
            }
            final String entry = mEntryValues[index].toString();
            if (mSelections.contains(entry)) {
                mSelections.remove(entry);
            } else {
                mSelections.add(entry);
            }
            int[] selected = new int[mSelections.size()];
            List<CharSequence> list = Arrays.asList(mEntryValues);

            int i = 0;
            for (String selection : mSelections) {
                selected[i] = list.indexOf(selection);
                i++;
            }

            SelectDialogFragment.this.onItemClick(selected);
            notifyDataSetChanged();
        }
    }


    private interface OnItemClickListener {
        void onItemClick(SelectDialogFragment.ViewHolder viewHolder);
    }

    /**
     * ViewHolder for each Item in the List.
     */
    public static final class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final Checkable mWidgetView;
        private final TextView mTitleView;
        private final ViewGroup mContainer;
        private final SelectDialogFragment.OnItemClickListener mListener;

        ViewHolder(@NonNull View view, @NonNull SelectDialogFragment.OnItemClickListener listener) {
            super(view);
            mWidgetView = (Checkable) view.findViewById(getResourceIdByName("button"));
            mContainer = (ViewGroup) view.findViewById(R.id.container);
            mTitleView = (TextView) view.findViewById(android.R.id.title);
            mContainer.setOnClickListener(this);
            mListener = listener;
        }

        public Checkable getWidgetView() {
            return mWidgetView;
        }

        public TextView getTitleView() {
            return mTitleView;
        }

        public ViewGroup getContainer() {
            return mContainer;
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(this);
        }
    }

    public interface SelectDialogListener {
        default void onSingleClick(SelectDialogFragment fragment, int position) {
        }

        default void onMultiClick(SelectDialogFragment fragment, int[] positions) {
        }

        default void onSelectDialogDismiss(SelectDialogFragment fragment) {
        }
    }
}
