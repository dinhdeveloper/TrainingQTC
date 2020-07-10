package com.dinh.customdate.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dinh.customdate.R;
import com.dinh.customdate.model.CategoryModel;

import java.util.List;

public class DemoCateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<CategoryModel> list;
    private Context context;

    public DemoCateAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_demo, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_demo, parent, false);
            return new HeaderViewHolder(itemView);
        } else return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

            headerViewHolder.id_stt.setText("STT");
            headerViewHolder.name_product.setText("Tên SP");
            headerViewHolder.nhap.setText("Nhập");
            headerViewHolder.tonkho.setText("Tồn");
            headerViewHolder.xuat.setText("Xuất");

            headerViewHolder.id_stt.setTextColor(Color.parseColor("#726A95"));
            headerViewHolder.name_product.setTextColor(Color.parseColor("#726A95"));
            headerViewHolder.nhap.setTextColor(Color.parseColor("#726A95"));
            headerViewHolder.tonkho.setTextColor(Color.parseColor("#726A95"));
            headerViewHolder.xuat.setTextColor(Color.parseColor("#726A95"));

        } else if (holder instanceof ItemViewHolder) {

            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

                itemViewHolder.id_stt.setText(String.valueOf(list.get(position));
                itemViewHolder.name_product.setText(String.valueOf(list.get(position - 1).getCategoryName()));
                itemViewHolder.nhap.setText(String.valueOf(12134));
                itemViewHolder.tonkho.setText(String.valueOf(12134));
                itemViewHolder.xuat.setText(String.valueOf(12134));

            // Following code give a row of header and decrease the one position from listview items
            //final LatestTabModel.ViewItemsModel data = latestlists.get(position-1);

            // You have to set your listview items values with the help of model class and you can modify as per your needs

            //itemViewHolder.title.setText(data.getTitle());

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }


    // getItemCount increasing the position to 1. This will be the row of header
    @Override
    public int getItemCount() {
        return list.size();
    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView id_stt;
        TextView name_product;
        TextView tonkho;
        TextView nhap;
        TextView xuat;

        public HeaderViewHolder(View holder) {
            super(holder);
            id_stt = holder.findViewById(R.id.id_stt);
            name_product = holder.findViewById(R.id.name_product);
            tonkho = holder.findViewById(R.id.tonkho);
            nhap = holder.findViewById(R.id.nhap);
            xuat = holder.findViewById(R.id.xuat);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView id_stt;
        TextView name_product;
        TextView tonkho;
        TextView nhap;
        TextView xuat;

        public ItemViewHolder(View holder) {
            super(holder);

            id_stt = holder.findViewById(R.id.id_stt);
            name_product = holder.findViewById(R.id.name_product);
            tonkho = holder.findViewById(R.id.tonkho);
            nhap = holder.findViewById(R.id.nhap);
            xuat = holder.findViewById(R.id.xuat);
        }
    }
}