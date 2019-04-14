在XML文件中声明的Preference类的各种子类构建而成,Preference对象是单个设置的构建基块,每个Preference均作为项目显示在列表中,并提供适当的UI用户修改设置。
CheckBoxPreference 显示复选框
EditTextPreference 打开一个包含EditText小部件的对话框
ListPreference 打开包含选择列表的对话框
<PreferenceScreen>是xml布局文件的根元素
<PreferenceCategory> 用于组合一组Preference
title是大标题,summary是小标题
```
preferencescreen.xml

<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <PreferenceCategory android:title="In-line preferences">
        <CheckBoxPreference
            android:key="checkbox_preference"
            android:summary="This a checkbox"
            android:title="Checkbox_preference"/>
    </PreferenceCategory>
    <PreferenceCategory android:title="Dialog-based preferences">
        <EditTextPreference
            android:dialogTitle="Enter your favorite animal"
            android:title="EditTextPreference"
            android:key="edittext_preference"
            android:summary="An example that uses an edit text dialog"/>
        <ListPreference
            android:entries="@array/options"
            android:entryValues="@array/options"
            android:dialogTitle="Choose one"
            android:summary="An example that use a list dialog"
            android:title="List prefernce"
            android:key="list_preference"/>
        <!--需要补充列表项的数据来源-->
    </PreferenceCategory>
    <PreferenceCategory android:title="Launch preferences">
        <PreferenceScreen
            android:key="screen_preference"
            android:summary="Shows another screen of preferences"
            android:title="Screen preference">
            <CheckBoxPreference
                android:key="next_scrren_checkbox_preference"
                android:summary="Preference that is on the next screen but same hierarchy"
                android:title="Toggle preference"/>
        </PreferenceScreen>
        <PreferenceScreen
            android:summary="Launches an Activity from an intent"
            android:title="Intent preference"/>
        <intent
            android:action="android.intent.action.VIEW"
            android:data="http://www.google.com"/>
    </PreferenceCategory>
    <!--设置项关联，选中父选项时，子选项才显示。使用dependency属性-->
    <PreferenceCategory android:title="Preference attributes">
        <CheckBoxPreference
            android:key="parent_checkbox_preference"
            android:summary="This is visually parent"
            android:title="Parent checkbox preference" />
        <!-- 子类的可见类型是由样式属性定义的 -->
        <CheckBoxPreference
            android:dependency="parent_checkbox_preference"
            android:key="child_checkbox_preference"
            android:summary="This is visually a child"
            android:title="Child checkbox preference" />
    </PreferenceCategory>
</PreferenceScreen>
```
```
arrays.xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string-array name="options">
       <item>Alpha Option01</item>
        <item>Beta Option02</item>
        <item>Charlie Option03</item>
    </string-array>
</resources>
```
```
PreFragment.xml
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

```
```
MainActivity.java
package com.example.hp.test4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载Fragment
        FragmentManager fragmentManager=getFragmentManager();
        //开启一个新事物
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        PreFragment preFragment=new PreFragment();
        transaction.add(R.id.layout,preFragment);
        transaction.commit();
    }
}
```
```
activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.hp.test4.MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>

```
结果截图:
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190413162601816.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwOTQ2MDUz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190413162628457.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwOTQ2MDUz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190413162654200.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwOTQ2MDUz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190413162713660.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwOTQ2MDUz,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/20190414212548733.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwOTQ2MDUz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190413162730396.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwOTQ2MDUz,size_16,color_FFFFFF,t_70)
