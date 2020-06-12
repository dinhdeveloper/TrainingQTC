package com.dinh.customdate.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dinh.customdate.R;
import com.dinh.customdate.model.CategoryModel;


import java.util.List;

import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;

public class CategoryAdapter extends SuperAdapter<CategoryModel> {

    private CategoryAdapterListener listener;

    public CategoryAdapter(Context context, List<CategoryModel> items) {
        super(context, items, R.layout.item_category);
    }

    public interface CategoryAdapterListener {
        void onItemClick(CategoryModel model);
    }

    public void setListener(CategoryAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, CategoryModel item) {
        ImageView imageCate = holder.findViewById(R.id.imageCate);
        TextView txtCate = holder.findViewById(R.id.txtCate);
        LinearLayout layoutIem = holder.findViewById(R.id.layoutIem);

        Glide.with(getContext()).load(item.getCategoryImage()).into(imageCate);
        txtCate.setText(item.getCategoryName().toString());

        if (layoutPosition != 5) {
            Glide.with(getContext()).load(item.getCategoryImage()).into(imageCate);
            txtCate.setText(item.getCategoryName().toString());
        } else {
            imageCate.setVisibility(View.GONE);
            txtCate.setText("Xem Thêm");
            txtCate.setGravity(View.TEXT_ALIGNMENT_CENTER);
            txtCate.setTextColor(Color.parseColor("#EF082A"));
        }


        layoutIem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(item);
            }
        });

    }

}


//public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
//    private static final int TYPE_MORE = 0;
//    private static final int TYPE_ITEM = 1;
//
//    List<CategoryModel> categories;
//    Context context;
//
//    public CategoryAdapter(List<CategoryModel> categories, Context context) {
//        this.categories = categories;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == TYPE_ITEM) {
//            // Here Inflating your recyclerview item layout
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_category, parent, false);
//            return new ViewHolder(itemView);
//        } else if (viewType == TYPE_MORE) {
//            // Here Inflating your header view
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_more_category, parent, false);
//            return new ViewHolder(itemView);
//        }
//        else return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        CategoryModel categoryModel = categories.get(position);
//
//        if (context != null) {
//            if (categories.get(position).getCategoryId() !=6){
//                Glide.with(context).load(categories.get(position).getCategoryImage()).into(holder.imageItem);
//                holder.nameItem.setText(categories.get(position).getCategoryName().toString());
//            }
//            else {
//                holder.nameItem.setText(null);
//            }
//        }
//    }
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 6) {
//            return TYPE_MORE;
//        }
//        return TYPE_ITEM;
//    }
//
//    @Override
//    public int getItemCount() {
//        return categories.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ProgressBar progressBar;
//        ImageView imageItem;
//        TextView nameItem;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageItem = itemView.findViewById(R.id.imageItem);
//            nameItem = itemView.findViewById(R.id.nameItem);
//            progressBar = itemView.findViewById(R.id.progressBar);
//        }
//    }
//}
