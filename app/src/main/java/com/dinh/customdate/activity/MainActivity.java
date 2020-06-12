package com.dinh.customdate.activity;


import android.util.Log;

import com.dinh.customdate.api.CategoryRequest;
import com.dinh.customdate.api.DemoProductRequest;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.DataModel;
import com.dinh.customdate.model.DemoProductModel;
import com.dinh.customdate.ui.activity.BaseMainView;
import com.dinh.customdate.ui.activity.BaseMainViewCallback;
import com.dinh.customdate.ui.activity.BaseMainViewInterface;
import com.dinh.customdate.ui.cateogry.CategoryViewCallback;
import com.dinh.customdate.ui.productdemo.ProductDemoViewCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseActionbarView;
import b.laixuantam.myaarlibrary.base.BaseActivity;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.dependency.AppObjectProvider;
import b.laixuantam.myaarlibrary.dependency.AppProvider;
import b.laixuantam.myaarlibrary.dependency.ObjectProviderInterface;

public class MainActivity extends BaseActivity<BaseMainViewInterface, BaseActionbarView, BaseParameters> implements
        BaseMainViewCallback {

    @Override
    protected void initialize() {
        super.initialize();
        view.init(this, this);
        getDataCategory();
        getDataProduct();

    }

    private void getDataProduct() {
        DemoProductRequest.ApiParams params = new DemoProductRequest.ApiParams();
        AppProvider.getApiManagement().call(DemoProductRequest.class, params, new ApiRequest.ApiCallback<DataModel>() {

            @Override
            public void onSuccess(DataModel body) {
                if (body != null) {
                    view.setProduct(body.getListDataProduct());
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

    private void getDataCategory() {
        CategoryRequest.ApiParams params = new CategoryRequest.ApiParams();
        showProgress();
        AppProvider.getApiManagement().call(CategoryRequest.class, params, new ApiRequest.ApiCallback<List<CategoryModel>>() {
            @Override
            public void onSuccess(List<CategoryModel> body) {
                dismissProgress();
                if (body != null) {
                    List<CategoryModel> models = new ArrayList<>();
                    models.addAll(body);
                    view.setCategory(body);
                }
            }

            @Override
            public void onError(ErrorApiResponse error) {
                dismissProgress();
                Log.e("onError", error.message);
            }

            @Override
            public void onFail(ApiRequest.RequestError error) {
                dismissProgress();
                Log.e("onFail", error.name());
            }
        });
    }

    @Override
    protected BaseMainViewInterface getViewInstance() {
        return new BaseMainView();
    }

    @Override
    protected BaseActionbarView getActionbarInstance() {
        return null;
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }

}