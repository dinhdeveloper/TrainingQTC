package com.dinh.customdate.ui.cateogry;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinh.customdate.R;
import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.adapter.CategoryAdapter;
import com.dinh.customdate.model.CategoryModel;

import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;

public class CategoryView extends BaseView<CategoryView.UIContainer> implements CategoryViewInterface {

    private MainActivity activity;
    CategoryViewCallback callback;

    @Override
    public void init(MainActivity activity, CategoryViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
        //ui.rcView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void initRc(List<CategoryModel> body) {

        CategoryAdapter catogoryAdapter = new CategoryAdapter(activity, body);
        catogoryAdapter.setListener(new CategoryAdapter.CategoryAdapterListener() {
            @Override
            public void onItemClick(CategoryModel model) {
                if (callback != null) {
                    callback.onChangToCategoryDetail(model);
                }
            }
        });
        ui.rc_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ui.rc_view.setAdapter(catogoryAdapter);
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
        @UiElement(R.id.rc_category)
        public RecyclerView rc_view;
    }
}
