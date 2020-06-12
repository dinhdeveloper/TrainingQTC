package com.dinh.customdate.api;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.dinh.customdate.helper.Consts;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.DataModel;
import com.dinh.customdate.model.DemoProductModel;

import java.util.List;
import java.util.Objects;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.BaseApiParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@ApiRequest.ApiName("data")
public class DemoProductRequest extends ApiRequest<DemoProductRequest.Service, DataModel, DemoProductRequest.ApiParams>{
    public DemoProductRequest() {
        super(DemoProductRequest.Service.class, RequestOrigin.NONE, Consts.HOST_DEVS, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(DataModel result) throws Exception {

    }

    @Override
    protected Call<DataModel> call(DemoProductRequest.ApiParams params) {
        params.detect ="list_product";
        return getService().call(params);
    }


    interface Service {
        @Headers(Consts.HEADES)
        @POST(Consts.REST_ENDPOINT)
        Call<DataModel> call(@Body DemoProductRequest.ApiParams params);
    }

    public static class ApiParams extends BaseApiParams {
        public String detect;
    }
}
