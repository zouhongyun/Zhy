package com.example.day07.persenter;

import com.example.day07.bean.ListBean;
import com.example.day07.callback.ListCallback;
import com.example.day07.model.ListModel;
import com.example.day07.view.Listview;

public class ListPerimple implements ListPerinter, ListCallback {
    private ListModel listModel;
    private Listview listview;

    public ListPerimple(ListModel listModel, Listview listview) {
        this.listModel = listModel;
        this.listview = listview;
    }

    @Override
    public void getdata() {
        listModel.getdata(this);

    }

    @Override
    public void ListSeccess(ListBean listBean) {
        listview.ListSeccess(listBean);
    }

    @Override
    public void ListFail(String s) {
        listview.ListFail(s);

    }
}
