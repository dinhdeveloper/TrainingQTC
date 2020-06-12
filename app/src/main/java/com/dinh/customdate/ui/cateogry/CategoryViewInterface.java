package com.dinh.customdate.ui.cateogry;

import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.model.CategoryModel;

import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;

public interface CategoryViewInterface extends BaseViewInterface {
    void init(MainActivity activity, CategoryViewCallback callback);
    void initRc(List<CategoryModel> body);
}
