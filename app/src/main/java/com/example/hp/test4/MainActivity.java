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
