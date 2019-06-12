package com.example.day07.callback;

import com.example.day07.bean.ListBean;

public interface ListCallback {
    void ListSeccess(ListBean listBean);
    void ListFail(String s);
}
