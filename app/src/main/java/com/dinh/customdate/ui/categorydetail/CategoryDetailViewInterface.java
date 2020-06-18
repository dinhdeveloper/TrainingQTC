package com.dinh.customdate.ui.categorydetail;

import com.dinh.customdate.activity.CategoryDetailActivity;
import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.DemoProductModel;
import com.dinh.customdate.ui.activity.BaseMainViewCallback;

import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;

public interface CategoryDetailViewInterface extends BaseViewInterface {
    void init(CategoryDetailActivity activity, CategoryDetailViewCallback callback);
    void setCategory(List<CategoryModel> body);
}
