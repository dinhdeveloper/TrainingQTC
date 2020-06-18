package com.dinh.customdate.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dinh.customdate.R;
import com.dinh.customdate.model.CategoryModel;

import java.util.List;

import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;

public class CategoryDetailAdapter extends SuperAdapter<CategoryModel> {

    private CategoryAdapter.CategoryAdapterListener listener;

    public CategoryDetailAdapter(Context context, List<CategoryModel> items) {
        super(context, items, R.layout.custom_item_detail);
    }

    public interface CategoryAdapterListener {
        void onItemClick(CategoryModel model);
    }

    public void setListener(CategoryAdapter.CategoryAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, CategoryModel item) {
        ImageView imageCate = holder.findViewById(R.id.imageCate);
        TextView txtCate = holder.findViewById(R.id.nameItem);
        TextView priceItem = holder.findViewById(R.id.priceItem);
        Glide.with(getContext()).load(item.getCategoryImage()).into(imageCate);
        txtCate.setText(item.getCategoryName().toString());

        Glide.with(getContext()).load(item.getCategoryImage()).into(imageCate);
        txtCate.setText(item.getCategoryName().toString());
        priceItem.setText(item.getPromotionId().toString());

    }

}
