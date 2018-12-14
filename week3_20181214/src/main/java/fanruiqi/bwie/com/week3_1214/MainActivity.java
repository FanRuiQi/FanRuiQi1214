package fanruiqi.bwie.com.week3_1214;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import fanruiqi.bwie.com.week3_1214.adapter.MyAdapter;
import fanruiqi.bwie.com.week3_1214.bean.ResponseBean;
import fanruiqi.bwie.com.week3_1214.precenter.IPrecenterImpl;
import fanruiqi.bwie.com.week3_1214.util.OkHttps;
import fanruiqi.bwie.com.week3_1214.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView{

    private XRecyclerView mXRecyclerView;
    private ImageView mImageView;
    private TextView mTextView01,mTextView02,mTextView03,mTextView04;
    private int page=1;
    private int sort=0;
    private boolean p=true;
    IPrecenterImpl iPrecenter;
    private String urlString="http://www.zhaoapi.cn/product/searchProducts?keywords=手机";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        iPrecenter = new IPrecenterImpl(this);

        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLoadingMoreEnabled(true);

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initData();
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                page++;
                initData();
                mXRecyclerView.refreshComplete();
            }
        });
        initData();
    }

    private void initData() {

        HashMap<String, Integer> map = new HashMap<>();  //POST
        map.put("page",page);
        map.put("sort",sort);

        iPrecenter.startRequestData(urlString,map,null);
        /*OkHttps.getInstance().doPost(urlString, map, new OkHttps.OnCallBack() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String json) {

                ResponseBean responseBean = new Gson().fromJson(json, ResponseBean.class);
                List<ResponseBean.DataBean> data = responseBean.getData();

                MyAdapter myAdapter = new MyAdapter(getApplicationContext());
                if (page==0){

                    myAdapter.setData(data);

                }else {

                    myAdapter.addData(data);
                }
                mXRecyclerView.setAdapter(myAdapter);

                mXRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

                myAdapter.setOnClickListener(new MyAdapter.OnClickListener() {
                    @Override
                    public void onClickListener(String imageView, String title, String price) {
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        intent.putExtra("image",imageView);
                        intent.putExtra("title",title);
                        intent.putExtra("price",price);

                        startActivity(intent);
                    }

                });

            }
        });*/

    }

    private void findViews() {
         mXRecyclerView = findViewById(R.id.mian_xrv);
         mImageView= findViewById(R.id.main_img);
         mTextView01 = findViewById(R.id.main_text1);
        mTextView02 = findViewById(R.id.main_text2);
        mTextView03 = findViewById(R.id.main_text3);
        mTextView04 = findViewById(R.id.main_text4);
        mImageView.setOnClickListener(this);
        mTextView01.setOnClickListener(this);
        mTextView02.setOnClickListener(this);
        mTextView03.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_img: //Listener与GridView切换

                if (p==true){
                    mXRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    p=false;
                }else {
                    mXRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                    p=true;
                }
                break;
            case R.id.main_text1: //综合
                sort=0;
                initData();
                break;
            case R.id.main_text2:  //销量
                sort=1;
                initData();
                break;
            case R.id.main_text3:  //价格
                sort=2;
                initData();
                break;
        }
    }

    @Override
    public void shouData(Object data) {
        ResponseBean responseBean= (ResponseBean) data;
        List<ResponseBean.DataBean> data1 = responseBean.getData();

        MyAdapter myAdapter = new MyAdapter(getApplicationContext());
        if (page==0){

            myAdapter.setData(data1);

        }else {

            myAdapter.addData(data1);
        }
        mXRecyclerView.setAdapter(myAdapter);

        mXRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

        myAdapter.setOnClickListener(new MyAdapter.OnClickListener() {
            @Override
            public void onClickListener(String imageView, String title, String price) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("image",imageView);
                intent.putExtra("title",title);
                intent.putExtra("price",price);

                startActivity(intent);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPrecenter.close();
    }
}
