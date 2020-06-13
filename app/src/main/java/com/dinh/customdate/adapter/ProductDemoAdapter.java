package com.dinh.customdate.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dinh.customdate.R;
import com.dinh.customdate.helper.Consts;
import com.dinh.customdate.model.DemoProductModel;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;

import b.laixuantam.myaarlibrary.dependency.AppProvider;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;

public class ProductDemoAdapter extends SuperAdapter<DemoProductModel> {

    Context context;
    List<DemoProductModel> items;

    public ProductDemoAdapter(Context context, List<DemoProductModel> items) {
        super(context, items, R.layout.custom_item_product);
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DemoProductModel item) {
        ImageView imagePro = holder.findViewById(R.id.imagePro);
        TextView nameItem = holder.findViewById(R.id.nameItem);
        TextView priceItem = holder.findViewById(R.id.priceItem);
        TextView priceSaleItem = holder.findViewById(R.id.priceSaleItem);
        TextView priceTiki = holder.findViewById(R.id.priceMoreItem);
        TextView priceLazada = holder.findViewById(R.id.priceMoresItem);
        try {
            nameItem.setText(item.getCategory().toString());
            String pattern = "###,###.###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            priceItem.setText(decimalFormat.format(Integer.parseInt(item.getPrice())) + " đ");
            String sale = "<strike>" + decimalFormat.format(Integer.parseInt(item.getPrice_discount())) + " đ" + "</strike>";
            priceSaleItem.setText(android.text.Html.fromHtml(sale));
            priceTiki.setText("Tiki: " + decimalFormat.format(Integer.parseInt(item.getPrice_market_1())) + " đ");
            priceLazada.setText("Lazada: " + decimalFormat.format(Integer.parseInt(item.getPrice_market_2())) + " đ");
            String imageLink = Consts.HOST_DEVS + item.getPhoto_avatar();

            AppProvider.getImageHelper().displayImage(imageLink, imagePro, null, R.drawable.girl);
        } catch (NumberFormatException ex) {
            Log.e("NumberFormatException", ex.toString());
        }
        //AppProvider.getImageHelper().displayImage(imageLink, imagePro, null, R.drawable.image_background);

    }
}
