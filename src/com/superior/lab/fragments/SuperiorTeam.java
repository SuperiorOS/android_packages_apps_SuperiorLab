/*
 * Copyright (C) 2017 Cardinal-AOSP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.superior.lab.fragments;

import android.os.Bundle;
import com.android.settings.SettingsPreferenceFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.R;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;

public class SuperiorTeam extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.superior_lab_team);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.SUPERIOR;
    }
}
