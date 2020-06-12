package com.dinh.customdate.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataModel {
    private String total;
    private String total_page;
    private String limit;
    private String page;
    private String success;
    private DemoProductModel[] data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_page() {
        return total_page;
    }

    public void setTotal_page(String total_page) {
        this.total_page = total_page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public DemoProductModel[] getData() {
        return data;
    }

    public void setData(DemoProductModel[] data) {
        this.data = data;
    }


    public List<DemoProductModel> getListDataProduct() {
        if (data == null) {
            return null;
        }
        else {
            List<DemoProductModel> list = new ArrayList<>();
            list.addAll(Arrays.asList(data));
            return list;
        }
    }
}
