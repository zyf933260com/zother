package com.example.zk1.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zk1.R;
import com.example.zk1.bean.SBean;
import com.example.zk1.bean.XBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends XRecyclerView.Adapter<MAdapter.MViewHolder> {

    private Context context;
    private List<SBean.ResultBean> list;

    /*public MAdapter(Context context, List<SBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }*/

    public MAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<SBean.ResultBean> list) {
       if (list!=null){
           this.list = list;
       }
       notifyDataSetChanged();
    }

    public void addList(List<SBean.ResultBean> list) {
        if (list!=null){
            this.list = list;
            list.addAll(list);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        MViewHolder mViewHolder=new MViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder mViewHolder, int i) {
        Uri parse = Uri.parse(list.get(i).getMasterPic());
        mViewHolder.img.setImageURI(parse);
        mViewHolder.title.setText(list.get(i).getCommodityName());
        mViewHolder.price.setText("￥:"+list.get(i).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MViewHolder extends RecyclerView.ViewHolder {
         private SimpleDraweeView img;
         private TextView title,price;
        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
    }
}
