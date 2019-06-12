package com.example.day07.api;

import com.example.day07.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyServer {
    String URL="https://api.yunxuekeji.cn/yunxue_app_api/course/";
    @POST("getCourseByTypeAndMore?orderOn=&classtype=031001004&forPeopleType=&format=&price=&pageIndex=1&pageSize=10&classTag=")
    Observable<ListBean> post();
    //https://api.yunxuekeji.cn/yunxue_app_api/course/
//    @GET("getCourseChapterById/{a}/{b}")
//    Observable<VideoBean> data(@Path("a") int a,@Path("b") int b);

}
