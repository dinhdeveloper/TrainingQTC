package com.dinh.customdate.ui.productdemo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinh.customdate.R;
import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.adapter.ProductDemoAdapter;
import com.dinh.customdate.model.DemoProductModel;

import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;

public class ProductDemoView extends BaseView<ProductDemoView.UIContainer> implements ProductDemoViewInterface {

    private MainActivity activity;
    private ProductDemoViewCallback callback;

    @Override
    public BaseUiContainer getUiContainer() {
        return new UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(MainActivity activity, ProductDemoViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    @Override
    public void initRecyclerview(List<DemoProductModel> body) {
        ProductDemoAdapter demoAdapter = new ProductDemoAdapter(activity,body);
        ui.rc_product.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ui.rc_product.setAdapter(demoAdapter);
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.rc_product)
        public RecyclerView rc_product;
    }
}
