package com.campusunfamiliar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campusunfamiliar.R;

/**
 * Created by ASUSS on 2017/7/9.
 */

public class ContactsFragment extends Fragment
{
    private View mView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_contacts,container,false);
        }
        return mView;
    }
}
