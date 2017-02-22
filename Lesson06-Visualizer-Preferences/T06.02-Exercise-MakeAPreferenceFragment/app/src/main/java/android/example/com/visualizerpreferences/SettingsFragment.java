package android.example.com.visualizerpreferences;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

// TODO (x2) Create a class called SettingsFragment that extends PreferenceFragmentCompat
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // TODO (x5) In SettingsFragment's onCreatePreferences method add the preference file using the
        // addPreferencesFromResource method
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
