package com.dinh.customdate.ui.activity;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinh.customdate.R;
import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.adapter.CategoryAdapter;
import com.dinh.customdate.adapter.ProductDemoAdapter;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.DemoProductModel;
import com.dinh.customdate.ui.productdemo.ProductDemoView;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;

public class BaseMainView extends BaseView<BaseMainView.UIContainer> implements BaseMainViewInterface {

    private MainActivity activity;
    private BaseMainViewCallback callback;
    private List<CategoryModel> bodyCate = new ArrayList<>();
    private List<DemoProductModel> bodyPro = new ArrayList<>();
    ProductDemoAdapter productDemoAdapter;
    CategoryAdapter catogoryAdapter;

    @Override
    public void init(MainActivity activity, BaseMainViewCallback callback) {
        this.callback = callback;
        this.activity = activity;
        initCategory();
        initProduct();
    }

    public void initProduct() {
        productDemoAdapter = new ProductDemoAdapter(activity, bodyPro);
        ui.rc_product.setLayoutManager(new GridLayoutManager(getContext(),2));
        ui.rc_product.setAdapter(productDemoAdapter);
    }

    public void initCategory() {
        catogoryAdapter = new CategoryAdapter(activity, bodyCate);
        ui.rc_category.setLayoutManager(new GridLayoutManager(getContext(),3));
        ui.rc_category.setAdapter(catogoryAdapter);
    }

    @Override
    public void setProduct(List<DemoProductModel> body) {
        if (body != null && productDemoAdapter != null) {
            bodyPro.addAll(body);
            productDemoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setCategory(List<CategoryModel> body) {
        if (body != null && catogoryAdapter != null) {
            bodyCate.addAll(body);
            catogoryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.activity_main;
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.rc_product)
        public RecyclerView rc_product;

        @UiElement(R.id.rc_category)
        public RecyclerView rc_category;

        @UiElement(R.id.imageSlider)
        public SliderView imageSlider;


    }
}
