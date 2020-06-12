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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import b.laixuantam.myaarlibrary.dependency.AppProvider;
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


        nameItem.setText(item.getCategory().toString());

        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        priceItem.setText(decimalFormat.format(Integer.parseInt(item.getPrice())) + " đ");
        String sale = "<strike>" + decimalFormat.format(Integer.parseInt(item.getPrice_discount())) + " đ" + "</strike>";

        priceSaleItem.setText(android.text.Html.fromHtml(sale));

        priceTiki.setText(decimalFormat.format(Integer.parseInt(item.getPrice_market_1())) + " đ");
        
        priceLazada.setText(decimalFormat.format(Integer.parseInt(item.getPrice_market_2())) + " đ");
        String imageLink = Consts.HOST_DEVS + item.getPhoto_avatar();
        AppProvider.getImageHelper().displayImage(imageLink, imagePro, null, 0);
    }
}
