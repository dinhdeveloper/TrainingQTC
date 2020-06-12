package com.dinh.customdate.ui.productdemo;

import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.model.DemoProductModel;

import java.util.List;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;

public interface ProductDemoViewInterface extends BaseViewInterface {

    void init(MainActivity activity,ProductDemoViewCallback callback);

    void initRecyclerview(List<DemoProductModel> body);
}
