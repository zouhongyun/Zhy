package com.example.day07.bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyServers {
    String Url="https://api.yunxuekeji.cn/yunxue_app_api/course/";
    @GET("getCourseChapterById/{a}/{b}")
    Observable<Bean> bean(@Path("a") int a, @Path("b") int b);
}
