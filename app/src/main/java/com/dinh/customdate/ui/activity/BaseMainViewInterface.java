package com.dinh.customdate.ui.activity;

import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.DemoProductModel;
import com.dinh.customdate.ui.productdemo.ProductDemoView;

import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;

public interface BaseMainViewInterface extends BaseViewInterface {
    void init(MainActivity activity,BaseMainViewCallback callback);

    void setProduct(List<DemoProductModel> body);
    void setCategory(List<CategoryModel> body);


    
}
