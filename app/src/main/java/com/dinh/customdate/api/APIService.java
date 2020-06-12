package com.dinh.customdate.api;

import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIService {
    @GET("category/list")
    Call<List<CategoryModel>> getAllCategory();

    @GET("product/list")
    Call<List<Product>> getAllProduct();
}
