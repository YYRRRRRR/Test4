package com.example.hp.test4;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
/**
 * Created by hp on 2019/4/12.
 */

public class PreFragment extends PreferenceFragment{
    public void onCreate(@Nullable Bundle  savedInstanceState){
        super.onCreate(savedInstanceState);
        //从XML文件中加载选项
       addPreferencesFromResource(R.xml.preferencescreen);
    }
}
