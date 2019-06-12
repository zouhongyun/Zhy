package com.example.day07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.day07.adapter.List_Adapter;
import com.example.day07.bean.ListBean;
import com.example.day07.callback.Onclicklenter;
import com.example.day07.model.ListModelimple;
import com.example.day07.persenter.ListPerimple;
import com.example.day07.view.Listview;

import java.util.ArrayList;

//邹鸿运 1810B
public class MainActivity extends AppCompatActivity implements Listview {

    private RecyclerView mRv;
    private Toolbar mTl;
    private ArrayList<ListBean.BodyBean.ResultBean.DataBean> list;
    private List_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        initData();
        initView();
        initToolbar();
    }

    private void initData() {
        ListPerimple listPerimple = new ListPerimple(new ListModelimple(), this);
        listPerimple.getdata();

    }

    private void initToolbar() {
        mTl.setTitle("");
        setSupportActionBar(mTl);
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mTl = (Toolbar) findViewById(R.id.tl);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        adapter = new List_Adapter(this,list);
        mRv.setAdapter(adapter);
        adapter.setOnclicklenter(new Onclicklenter() {
            @Override
            public void Onclick(int position, View view) {
                //获取到实体类里的idnnnnn
                ListBean.BodyBean.ResultBean.DataBean dataBean = list.get(position);
                //这个是用于判断
                int classFormat = dataBean.getClassFormat();
                int classTeacherID = dataBean.getClassTeacherID();
                if (classFormat==1){ //如果是1跳转视频页面
                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                    intent.putExtra("id",classTeacherID);
                    startActivity(intent);
                }else{//否则跳音频

                }
            }
        });
    }

    @Override
    public void ListSeccess(ListBean listBean) {
        list.addAll(listBean.getBody().getResult().getData());
        adapter.notifyDataSetChanged();


    }

    @Override
    public void ListFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
