package com.campusunfamiliar;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener
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
        setContentView(R.layout.activity_main);
        initView1();
        initView2();
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
                initFragment1();
                textView.setText("消息");
                break;
            case R.id.home_radioButton2:
                initFragment2();
                textView.setText("联系人");
                break;
            case R.id.home_radioButton3:
                initFragment3();
                textView.setText("我的");
                break;
        }
    }

    private void initFragment1()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(newsFragment == null){
            newsFragment = new NewsFragment();
            transaction.add(R.id.home_fragment,newsFragment);
        }
        hideFragment(transaction);
        transaction.show(newsFragment);
        transaction.commit();
    }

    private void initFragment2()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(contactsFragment == null){
            contactsFragment = new ContactsFragment();
            transaction.add(R.id.home_fragment,contactsFragment);
        }
        hideFragment(transaction);
        transaction.show(contactsFragment);
        transaction.commit();
    }

    private void initFragment3()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mineFragment == null){
            mineFragment = new MineFragment();
            transaction.add(R.id.home_fragment,mineFragment);
        }
        hideFragment(transaction);
        transaction.show(mineFragment);
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
