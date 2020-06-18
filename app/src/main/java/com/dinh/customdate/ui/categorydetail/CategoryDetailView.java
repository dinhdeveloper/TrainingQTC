package com.dinh.customdate.ui.categorydetail;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinh.customdate.R;
import com.dinh.customdate.activity.CategoryDetailActivity;
import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.adapter.CategoryAdapter;
import com.dinh.customdate.adapter.CategoryDetailAdapter;
import com.dinh.customdate.adapter.ProductDemoAdapter;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.DemoProductModel;
import com.dinh.customdate.ui.activity.BaseMainView;
import com.dinh.customdate.ui.activity.BaseMainViewCallback;
import com.dinh.customdate.ui.activity.BaseMainViewInterface;

import java.util.ArrayList;
import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;

public class CategoryDetailView extends BaseView<CategoryDetailView.UIContainer> implements CategoryDetailViewInterface {

    private CategoryDetailActivity activity;
    private CategoryDetailViewCallback callback;
    private List<CategoryModel> bodyCate = new ArrayList<>();
    CategoryDetailAdapter catogoryAdapter;

    @Override
    public void init(CategoryDetailActivity activity, CategoryDetailViewCallback callback) {
        this.callback = callback;
        this.activity = activity;

        initCategory();
    }

    public void initCategory() {
        catogoryAdapter = new CategoryDetailAdapter(activity, bodyCate);
        ui.rc_list_detail.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        ui.rc_list_detail.setAdapter(catogoryAdapter);
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new CategoryDetailView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.activity_category_detail_two;
    }

    @Override
    public void setCategory(List<CategoryModel> body) {
        if (body != null && catogoryAdapter != null) {
            bodyCate.addAll(body);
            catogoryAdapter.notifyDataSetChanged();
        }
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.rc_list_detail)
        public RecyclerView rc_list_detail;
    }
}
