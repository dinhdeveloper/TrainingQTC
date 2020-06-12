package com.dinh.customdate.fragment;

import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.api.DemoProductRequest;
import com.dinh.customdate.model.DemoProductModel;
import com.dinh.customdate.ui.productdemo.ProductDemoViewCallback;
import com.dinh.customdate.ui.productdemo.ProductDemoViewInterface;

import java.util.ArrayList;
import java.util.List;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.dependency.AppProvider;

public class FragmentDemoProduct extends BaseFragment<ProductDemoViewInterface, BaseParameters> implements ProductDemoViewCallback {
    @Override
    protected void initialize() {
        MainActivity activity = (MainActivity) getActivity();
        view.init(activity,this);
        requestData();
    }

    private void requestData() {
        DemoProductRequest.ApiParams params =new DemoProductRequest.ApiParams();
        AppProvider.getApiManagement().call(DemoProductRequest.class, params, new ApiRequest.ApiCallback<List<DemoProductModel>>() {

            @Override
            public void onSuccess(List<DemoProductModel> body) {
                if (body != null) {
                    List<DemoProductModel> models = new ArrayList<>();
                    models.addAll(body);
                    view.initRecyclerview(body);
                }
            }

            @Override
            public void onError(ErrorApiResponse error) {

            }

            @Override
            public void onFail(ApiRequest.RequestError error) {

            }
        });
    }

    @Override
    protected ProductDemoViewInterface getViewInstance() {
        return null;
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
