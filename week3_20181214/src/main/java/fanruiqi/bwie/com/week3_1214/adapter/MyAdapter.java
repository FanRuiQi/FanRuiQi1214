package fanruiqi.bwie.com.week3_1214.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fanruiqi.bwie.com.week3_1214.R;
import fanruiqi.bwie.com.week3_1214.bean.ResponseBean;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context mContext;
    List<ResponseBean.DataBean> list;
    public MyAdapter(Context context) {
        mContext=context;

        list = new ArrayList<>();
    }

    public void setData(List<ResponseBean.DataBean> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<ResponseBean.DataBean> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        String str = "";
        int length = list.get(position).getImages().length();
        for (int j = 0; j < length; j++) {
            if(list.get(position).getImages().substring(j, j + 1).equals("|")) {
                str = list.get(position).getImages().substring(j + 1, length).trim();
            }
        }

        Glide.with(mContext).load(str).into(holder.mImageView);

        //Log.e("ff",str);

        holder.mTextView01.setText(list.get(position).getTitle());
        holder.mTextView02.setText(String.valueOf(list.get(position).getPrice()));

        final String finalStr = str;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onClickListener(finalStr,list.get(position).getTitle(),list.get(position).getPrice());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnClickListener{
        void onClickListener(String imageView,String title,String price);
    }

    OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        mOnClickListener=onClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView01;
        TextView mTextView02;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_img);
            mTextView01 = itemView.findViewById(R.id.item_text1);
            mTextView02 = itemView.findViewById(R.id.item_text2);
        }
    }
}
