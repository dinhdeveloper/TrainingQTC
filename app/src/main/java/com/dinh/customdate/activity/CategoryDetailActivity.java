package com.dinh.customdate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.dinh.customdate.R;
import com.dinh.customdate.api.CategoryRequest;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.ui.activity.BaseMainViewCallback;
import com.dinh.customdate.ui.activity.BaseMainViewInterface;
import com.dinh.customdate.ui.categorydetail.CategoryDetailView;
import com.dinh.customdate.ui.categorydetail.CategoryDetailViewCallback;
import com.dinh.customdate.ui.categorydetail.CategoryDetailViewInterface;

import java.util.ArrayList;
import java.util.List;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseActionbarView;
import b.laixuantam.myaarlibrary.base.BaseActivity;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.dependency.AppProvider;

public class CategoryDetailActivity extends BaseActivity<CategoryDetailViewInterface, BaseActionbarView, BaseParameters> implements
        CategoryDetailViewCallback {

    @Override
    protected void initialize() {
        super.initialize();
        view.init(this, this);
        getDataCategory();
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
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return 5000;
    }

    @Override
    protected CategoryDetailViewInterface getViewInstance() {
        return new CategoryDetailView();
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