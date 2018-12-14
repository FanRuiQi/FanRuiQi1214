package fanruiqi.bwie.com.week3_1214.model;

import java.util.HashMap;

import fanruiqi.bwie.com.week3_1214.callback.MyCallBack;

public interface IModel {
    void requestData(String url, HashMap<String,Integer> map, String params, MyCallBack myCallBack);
}
