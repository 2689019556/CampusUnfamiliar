package com.campusunfamiliar;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.campusunfamiliar.beans.ItemBean;
import com.campusunfamiliar.fragments.ContactsFragment;
import com.campusunfamiliar.fragments.MineFragment;
import com.campusunfamiliar.fragments.NewsFragment;
import com.campusunfamiliar.utils.SideslipItem;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.lang.reflect.Field;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener
{
    private ListView lv;
    private ImageView ivIcon, ivBottom;
    private QuickAdapter<ItemBean> quickAdapter;

    private RadioGroup radioGroup;
    private TextView textView;

    private NewsFragment newsFragment;
    private ContactsFragment contactsFragment;
    private MineFragment mineFragment;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
        setStatusBar();
        initView1();
        initView2();
    }
    /**
     * 设置沉浸式状态栏
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final ViewGroup linear_bar = (ViewGroup) findViewById(R.id.rl_title);
            final int statusHeight = getStatusBarHeight();
            linear_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = linear_bar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) linear_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    linear_bar.setLayoutParams(params);
                }
            });
        }
    }
    /**
     * 获取状态栏的高度
     * @return
     */
    protected int getStatusBarHeight(){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private void initView1()
    {
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        ivBottom = (ImageView) findViewById(R.id.iv_bottom);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(quickAdapter=new QuickAdapter<ItemBean>(this,R.layout.item_sideslip_layout, SideslipItem.getItemBeans())
        {
            protected void convert(BaseAdapterHelper helper, ItemBean item)
            {
                helper.setImageResource(R.id.item_img,item.getImg()).setText(R.id.item_tv,item.getTitle());
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Toast.makeText(MainActivity.this,"Click Item "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView2()
    {
        textView = (TextView) findViewById(R.id.main_text);
        radioGroup = (RadioGroup) findViewById(R.id.home_radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.home_radioButton1);
    }

    public void onCheckedChanged(RadioGroup group,int checkedId)
    {
        switch (checkedId)
        {
            case R.id.home_radioButton1:
                initFragment(1);
                break;
            case R.id.home_radioButton2:
                initFragment(2);
                break;
            case R.id.home_radioButton3:
                initFragment(3);
                break;
        }
    }

    private void initFragment(int i)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch (i)
        {
            case 1:
                if(newsFragment == null)
                {
                    newsFragment = new NewsFragment("消息");
                    transaction.add(R.id.home_fragment,newsFragment);
                }
                fragment = newsFragment;
                textView.setText(newsFragment.getName());
                break;
            case 2:
                if(contactsFragment == null)
                {
                    contactsFragment = new ContactsFragment("联系人");
                    transaction.add(R.id.home_fragment,contactsFragment);
                }
                fragment = contactsFragment;
                textView.setText(contactsFragment.getName());
                break;
            case 3:
                if(mineFragment == null)
                {
                    mineFragment = new MineFragment("我的");
                    transaction.add(R.id.home_fragment,mineFragment);
                }
                fragment = mineFragment;
                textView.setText(mineFragment.getName());
                break;
        }
        hideFragment(transaction);
        transaction.show(fragment);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if(newsFragment != null){
            transaction.hide(newsFragment);
        }
        if(contactsFragment != null){
            transaction.hide(contactsFragment);
        }
        if(mineFragment != null){
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void finish()
    {
        super.finish();
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.removeAllViews();
    }
}
