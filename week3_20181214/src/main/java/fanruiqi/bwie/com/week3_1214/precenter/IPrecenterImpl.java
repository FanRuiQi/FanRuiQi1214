package fanruiqi.bwie.com.week3_1214.precenter;

import java.util.HashMap;

import fanruiqi.bwie.com.week3_1214.callback.MyCallBack;
import fanruiqi.bwie.com.week3_1214.model.IModelImpl;
import fanruiqi.bwie.com.week3_1214.view.IView;

public class IPrecenterImpl implements IPrecenter{

    IView mIView;
    IModelImpl iModel;

    public IPrecenterImpl(IView IView) {
        mIView = IView;
        iModel = new IModelImpl();
    }

    @Override
    public void startRequestData(String url, HashMap<String, Integer> map, String params) {
        iModel.requestData(url,map, params, new MyCallBack() {
            @Override
            public void setData(Object data) {
                mIView.shouData(data);
            }
        });
    }

    public void close(){
        if (mIView!=null){
            mIView=null;
        }

        if (iModel!=null){
            iModel=null;
        }
    }
}
