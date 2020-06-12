package com.dinh.customdate.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dinh.customdate.R;
import com.dinh.customdate.helper.Consts;
import com.dinh.customdate.model.DataModel;
import com.dinh.customdate.model.DemoProductModel;
import com.dinh.customdate.ui.productdemo.ProductDemoView;

import java.util.ArrayList;
import java.util.List;

import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;

public class ProductDemoAdapter extends SuperAdapter<DemoProductModel> {

    public ProductDemoAdapter(Context context, List<DemoProductModel> items) {
        super(context, items, R.layout.custom_item_product);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DemoProductModel item) {
        ImageView imagePro = holder.findViewById(R.id.imagePro);
        TextView nameItem = holder.findViewById(R.id.nameItem);
        TextView priceItem = holder.findViewById(R.id.priceItem);
        TextView priceSaleItem = holder.findViewById(R.id.priceSaleItem);
        TextView priceTiki = holder.findViewById(R.id.priceMoreItem);
        TextView priceLazada = holder.findViewById(R.id.priceMoresItem);

        Log.e("HAHA",item.getCategory()+"");

        Glide.with(getContext()).load(Consts.HOST_DEVS + item.getAvatar()).into(imagePro);
        nameItem.setText(item.getCategory().toString());
        priceItem.setText(item.getPrice().toString());
        priceSaleItem.setText(item.getPrice_discount().toString());
        priceTiki.setText(item.getPrice_market_1().toString());
        priceLazada.setText(item.getPrice_market_2().toString());
    }
}
