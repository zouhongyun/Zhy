package com.example.day07.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day07.MainActivity;
import com.example.day07.R;
import com.example.day07.bean.ListBean;
import com.example.day07.callback.Onclicklenter;

import java.util.ArrayList;

public class List_Adapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<ListBean.BodyBean.ResultBean.DataBean> list;

    public void setOnclicklenter(Onclicklenter onclicklenter) {
        this.onclicklenter = onclicklenter;
    }

    private Onclicklenter onclicklenter;

    public List_Adapter(Context context, ArrayList<ListBean.BodyBean.ResultBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.list_item, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;


        viewHolder1.content.setText(list.get(i).getClassFormat()+"");
        viewHolder1.title.setText(list.get(i).getClassTag()+"");
        viewHolder1.charge.setText(list.get(i).getClassRecommend());
        Glide.with(context).load(list.get(i).getClassCoverPic()).into(viewHolder1.img);
        viewHolder1.book.setText(list.get(i).getClassPrice()+"");
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclicklenter!=null){
                    onclicklenter.Onclick(i,v);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView content;
        private final TextView title;
        private final TextView book;
        private final ImageView img;
        private final TextView charge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.list_content);
            title = itemView.findViewById(R.id.list_title);
            book = itemView.findViewById(R.id.list_book);
            img = itemView.findViewById(R.id.list_img);
            charge = itemView.findViewById(R.id.list_charge);

        }
    }

}
