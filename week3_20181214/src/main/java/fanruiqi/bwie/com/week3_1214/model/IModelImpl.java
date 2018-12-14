package fanruiqi.bwie.com.week3_1214.model;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import fanruiqi.bwie.com.week3_1214.MainActivity;
import fanruiqi.bwie.com.week3_1214.SecondActivity;
import fanruiqi.bwie.com.week3_1214.adapter.MyAdapter;
import fanruiqi.bwie.com.week3_1214.bean.ResponseBean;
import fanruiqi.bwie.com.week3_1214.callback.MyCallBack;
import fanruiqi.bwie.com.week3_1214.util.OkHttps;

public class IModelImpl implements IModel {
    @Override
    public void requestData(String url, HashMap<String, Integer> map, String params, final MyCallBack myCallBack) {
        OkHttps.getInstance().doPost(url, map, new OkHttps.OnCallBack() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String json) {

                ResponseBean responseBean = new Gson().fromJson(json, ResponseBean.class);

                myCallBack.setData(responseBean);

            }
        });
    }

}
