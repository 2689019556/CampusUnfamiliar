package com.campusunfamiliar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campusunfamiliar.R;

public class NewsFragment extends Fragment
{
    private View mView;
    private String name;

    public NewsFragment(String name)
    {
        this.name = name;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_news,container,false);
        }
        return mView;
    }
    public String getName()
    {
        return name;
    }
}
