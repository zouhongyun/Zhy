package com.example.day07;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.day07.api.MyServer;
import com.example.day07.bean.Bean;
import com.example.day07.bean.Main_Adapte;
import com.example.day07.bean.MyServers;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoActivity extends AppCompatActivity {

    private List<Bean.BodyBean.ResultBean.ChildrenBean> mResult;
    private int a = 519;
    private int b = 519;
    private List<Bean.BodyBean.ResultBean.ChildrenBean> mChildren;
    private ExpandableListView mEv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        loadData();
        initListener();
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServers.Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServers myServer = retrofit.create(MyServers.class);

        Observable<Bean> data = myServer.bean(a, b);

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean tabBean) {
                        mChildren = tabBean.getBody().getResult().get(0).getChildren();
                        Log.d("TAg", "initView: " + mChildren.toString());
                        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
                        jzVideoPlayerStandard.setUp(mChildren.get(0).getUrl(),
                                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                                mChildren.get(0).getName());

                        ArrayList<String> group = new ArrayList<>();
                        ArrayList<ArrayList<String>> child = new ArrayList<>();

                        List<Bean.BodyBean.ResultBean> result = tabBean.getBody().getResult();
                        for (int i = 0; i < result.size(); i++) {
                            group.add(result.get(i).getName());
                            List<Bean.BodyBean.ResultBean.ChildrenBean> children = result.get(i).getChildren();
                            ArrayList<String> list = new ArrayList<>();
                            for (int i1 = 0; i1 < children.size(); i1++) {
                                list.add(children.get(i1).getName());
                            }
                            child.add(list);
                        }
                        Main_Adapte main_adapter = new Main_Adapte(VideoActivity.this,group,child);
                        mEv.setAdapter(main_adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(VideoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initListener() {
    }


    private void initView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 519);
        a=id;
        b=id;
        mEv = (ExpandableListView) findViewById(R.id.ev);
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
