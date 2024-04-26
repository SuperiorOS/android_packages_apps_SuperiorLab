/*
 * Copyright (C) 2019-2024 The Evolution X Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.superior.lab.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.search.SearchIndexable;

import java.util.List;

import com.superior.support.preferences.SystemPropertySwitchPreference;

@SearchIndexable
public class Spoofing extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String TAG = "Spoofing";

    private static final String KEY_SYSTEM_WIDE_CATEGORY = "spoofing_system_wide_category";
    private static final String KEY_PIXEL_PROPS = "persist.sys.pphooks.enable";

    private PreferenceCategory mSystemWideCategory;
    private SystemPropertySwitchPreference mPixelProps;

    private static final String KEY_FINGERPRINT_CATEGORY = "lock_screen_fingerprint_category";
    private static final String KEY_RIPPLE_EFFECT = "enable_ripple_effect";

    private PreferenceCategory mFingerprintCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.spoofing);

        final Context context = getContext();
        final ContentResolver resolver = context.getContentResolver();
        final PreferenceScreen prefScreen = getPreferenceScreen();
        final Resources resources = context.getResources();

        mSystemWideCategory = (PreferenceCategory) findPreference(KEY_SYSTEM_WIDE_CATEGORY);
        mPixelProps = (SystemPropertySwitchPreference) findPreference(KEY_PIXEL_PROPS);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final Context context = getContext();
        final ContentResolver resolver = context.getContentResolver();
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.SUPERIOR;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
        new BaseSearchIndexProvider(R.xml.spoofing) {

            @Override
            public List<String> getNonIndexableKeys(Context context) {
                List<String> keys = super.getNonIndexableKeys(context);
                final Resources resources = context.getResources();

                FingerprintManager fingerprintManager = (FingerprintManager)
                    context.getSystemService(Context.FINGERPRINT_SERVICE);

                if (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) {
                    keys.add(KEY_RIPPLE_EFFECT);
                }
                return keys;
            }
        };
}
