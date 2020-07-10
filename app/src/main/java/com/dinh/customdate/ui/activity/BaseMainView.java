package com.dinh.customdate.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinh.customdate.R;
import com.dinh.customdate.activity.DetailActivity;
import com.dinh.customdate.activity.ListLevelDetailActivity;
import com.dinh.customdate.activity.MainActivity;
import com.dinh.customdate.activity.CategoryDetailActivity;
import com.dinh.customdate.activity.ProfileActivity;
import com.dinh.customdate.adapter.CategoryAdapter;
import com.dinh.customdate.adapter.DemoCateAdapter;
import com.dinh.customdate.adapter.ProductDemoAdapter;
import com.dinh.customdate.adapter.SlideAdvertiseAdapter;
import com.dinh.customdate.model.CategoryModel;
import com.dinh.customdate.model.DemoProductModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;

public class BaseMainView extends BaseView<BaseMainView.UIContainer> implements BaseMainViewInterface {

    private MainActivity activity;
    private BaseMainViewCallback callback;
    private List<CategoryModel> bodyCate = new ArrayList<>();
    private List<DemoProductModel> bodyPro = new ArrayList<>();
    ProductDemoAdapter productDemoAdapter;
    CategoryAdapter catogoryAdapter;
    DemoCateAdapter demoCateAdapter;
    SlideAdvertiseAdapter sliderAdapter;

    boolean isLoading = true;

    @Override
    public void init(MainActivity activity, BaseMainViewCallback callback) {
        this.callback = callback;
        this.activity = activity;

        initSlider();
        initCategory();
        initProduct();
        initScrollListener();
        eventOnClick();
    }

    private void initSlider() {
        sliderAdapter = new SlideAdvertiseAdapter(activity);
        ui.imageSlider.setSliderAdapter(sliderAdapter);
        ui.imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        ui.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        ui.imageSlider.setAutoCycleDirection(SliderView.AUTOFILL_TYPE_NONE);
        ui.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        ui.imageSlider.setIndicatorUnselectedColor(Color.argb(1, 248, 249, 249));
        //sliderView.setScrollTimeInSec(2); //set scroll delay in seconds :
        ui.imageSlider.startAutoCycle();
    }

    private void eventOnClick() {
        ui.iconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                ((Activity) getContext()).startActivity(intent);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    try {
//                        Date myDate = Date.from(Instant.parse("2020-07-01T22:25:50Z"));
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd/MM/YYYY ");
//                        //20:29:13, 9/7/2020
//                        String formattedDate = dateFormat.format(myDate);
//                        Timestamp timestamp = new java.sql.Timestamp(dateFormat.getTimeZone());
//                        long ts = dateFormat.parse(formattedDate).getTime()/1000;
//                        Log.e("AAAAA",ts+"");
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });

        ui.giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategoryDetailActivity.class);
                ((Activity) getContext()).startActivity(intent);
            }
        });

        ui.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListLevelDetailActivity.class);
                ((Activity) getContext()).startActivity(intent);
            }
        });

        ui.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                ((Activity) getContext()).startActivity(intent);
            }
        });
    }

    public void initProduct() {
        ViewCompat.setNestedScrollingEnabled(ui.rc_product, false);
        productDemoAdapter = new ProductDemoAdapter(activity, bodyPro);
        ui.rc_product.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ui.rc_product.setAdapter(productDemoAdapter);
    }


    @Override
    public void setProduct(List<DemoProductModel> body) {
        if (body != null && productDemoAdapter != null) {
            bodyPro.addAll(body);
            productDemoAdapter.notifyDataSetChanged();
            ui.rc_product.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (isLoading == true) {
                        if (callback != null) {
                            ui.progressBar.setVisibility(View.GONE);
                            isLoading = false;
                        }
                    }
                    ui.rc_product.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    public void initCategory() {
        ViewCompat.setNestedScrollingEnabled(ui.rc_category, false);
        demoCateAdapter = new DemoCateAdapter(activity, bodyCate);
        ui.rc_category.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ui.rc_category.setHasFixedSize(true);
        ui.rc_category.setAdapter(demoCateAdapter);
    }

    @Override
    public void setCategory(List<CategoryModel> body) {
        if (body != null && demoCateAdapter != null) {
            bodyCate.addAll(body);
            demoCateAdapter.notifyDataSetChanged();
        }
    }

    public void initScrollListener() {
        ui.nestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    if (!isLoading) {
                        ui.progressBar.setVisibility(View.VISIBLE);
                        callback.onRequestLoadMoreProduct();
                        isLoading = true;
                    }
                }
            }
        });
    }


    @Override
    public BaseUiContainer getUiContainer() {
        return new UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.activity_main;
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.rc_product)
        public RecyclerView rc_product;

        @UiElement(R.id.rc_category)
        public RecyclerView rc_category;

        @UiElement(R.id.layout_search)
        public LinearLayout layout_search;

        @UiElement(R.id.imageSlider)
        public SliderView imageSlider;

        @UiElement(R.id.progressBar)
        public ProgressBar progressBar;

        @UiElement(R.id.nestedScroll)
        public NestedScrollView nestedScroll;

        @UiElement(R.id.iconHome)
        public ImageView iconHome;

        @UiElement(R.id.giohang)
        public ImageView giohang;

        @UiElement(R.id.imageBack)
        public ImageView imageBack;

        @UiElement(R.id.image)
        public ImageView image;
    }
}
