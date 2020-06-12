package com.dinh.customdate.api;

import com.dinh.customdate.helper.Consts;
import com.dinh.customdate.model.CategoryModel;

import java.util.List;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.BaseApiParams;
import retrofit2.Call;
import retrofit2.http.GET;

@ApiRequest.ApiName("list")
public class CategoryRequest extends ApiRequest<CategoryRequest.Service, List<CategoryModel>, CategoryRequest.ApiParams>{
    public CategoryRequest() {
        super(CategoryRequest.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(List<CategoryModel> result) throws Exception {

    }

    @Override
    protected Call<List<CategoryModel>> call(ApiParams params) {
        return getService().getCategoryList();
    }


    interface Service {
        @GET("category/list")
        Call<List<CategoryModel>> getCategoryList();
    }

    public static class ApiParams extends BaseApiParams {
    }
}
