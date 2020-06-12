package com.dinh.customdate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dinh.customdate.R;
import com.dinh.customdate.helper.ILoadMore;
import com.dinh.customdate.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    ILoadMore loadMore;
    boolean isLoading;

    Context context;
    List<Product> mItemList;

    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public ProductAdapter(Context context, List<Product> products,RecyclerView recyclerView) {
        this.context = context;
        this.mItemList = products;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount(); // Lấy tổng số lượng item đang có
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition(); // Lấy vị trí của item cuối cùng
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) // Nếu không phải trạng thái loading và tổng số lượng item bé hơn hoặc bằng vị trí item cuối + số lượng item tối đa hiển thị
                {
                    if (loadMore != null)
                        loadMore.onLoadMore(); // Gọi callback interface Loadmore
                    isLoading = true;
                }

            }
        });
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_product, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imagePro;
        TextView nameItem;
        TextView priceItem;
        TextView priceSaleItem;
        TextView priceTiki;
        TextView priceLazada;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePro = itemView.findViewById(R.id.imagePro);
            nameItem = itemView.findViewById(R.id.nameItem);
            priceItem = itemView.findViewById(R.id.priceItem);
            priceSaleItem = itemView.findViewById(R.id.priceSaleItem);
            priceTiki = itemView.findViewById(R.id.priceMoreItem);
            priceLazada = itemView.findViewById(R.id.priceMoresItem);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder holder, int position) {
        if (context != null) {
            Glide.with(context).load(mItemList.get(position).getProductImage()).into(holder.imagePro);
            holder.nameItem.setText(mItemList.get(position).getProductName().toString());

            String pattern = "###,###.###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            holder.priceItem.setText(decimalFormat.format(Integer.parseInt(mItemList.get(position).getProductPrice())) + " đ");
            String htmlcontent = "<strike>"+decimalFormat.format(Integer.parseInt(mItemList.get(position).getPriceSale())) + " đ"+"</strike>";

            holder.priceSaleItem.setText(android.text.Html.fromHtml(htmlcontent));

            //holder.priceSaleItem.setText(decimalFormat.format(Integer.parseInt(products.get(position).getPriceSale())) + " đ");
            holder.priceTiki.setText("Tiki: "+decimalFormat.format(Integer.parseInt(mItemList.get(position).getPriceSale())) + " đ");
            holder.priceLazada.setText("Lazada: "+decimalFormat.format(Integer.parseInt(mItemList.get(position).getPriceSale())) + " đ");
        }

    }

}
