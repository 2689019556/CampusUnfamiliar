package com.campusunfamiliar.adapters;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by jjjj on 2017/7/10.
 */

public class NewsAdapter extends BaseAdapter{
    private String nickname;
    private Context context;
    private List<String>list;
    private LayoutInflater inflater;

    public void NewsAdapter(Context context, List<String>list){
        this.nickname = nickname;
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return nickname.length();
    }

    @Override
    public Object getItem(int age) {
        return age;
    }



    @Override
    public long getItemId(int age) {
        return age;
    }

    @Override
    public View getView(int age, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view ==null){
            holder = new Holder();
        }
        return null;
    }
     class  Holder{

     }
}
