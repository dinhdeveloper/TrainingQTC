package com.dinh.customdate.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.dinh.customdate.R;
import com.shawnlin.numberpicker.NumberPicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    private NumberPicker numberPicker, numberPickerMonth;
    LinearLayout buttonLayout;
    DateRangeCalendarView calendar;
    int year = 0;
    int month = 0;

    ImageView imageBack;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        addControls();
//        addEventYear();
//        addEventMonth();
//        addEventDay();
//        onClick();
    }

    private void onClick() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void addControls() {
//        numberPicker = findViewById(R.id.year_picker);
//        numberPickerMonth = findViewById(R.id.month_picker);
        buttonLayout = findViewById(R.id.buttonLayout);
//        calendar = findViewById(R.id.calendar);
        imageBack = findViewById(R.id.imageBack);
//
//        Calendar current = Calendar.getInstance();
//        calendar.setCurrentMonth(current);


    }

    private void addEventMonth() {

        final String[] data = getResources().getStringArray(R.array.month);
        numberPickerMonth.setMinValue(1);
        numberPickerMonth.setMaxValue(data.length);
        numberPickerMonth.setDisplayedValues(data);
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        numberPickerMonth.setValue(Integer.parseInt(dateFormat.format(date)));
        // Set fading edge enabled
        numberPickerMonth.setFadingEdgeEnabled(true);
        // Set scroller enabled
        numberPickerMonth.setScrollerEnabled(true);
        // Set wrap selector wheel
        numberPickerMonth.setWrapSelectorWheel(true);
        // Set accessibility description enabled
        numberPickerMonth.setAccessibilityDescriptionEnabled(true);
        // OnClickListener
        numberPickerMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HAHA", "Click on current value");
            }
        });

        // OnValueChangeListener
        numberPickerMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.e("HAHAH", picker.getValue() + "");
                month = picker.getValue();
                Toast.makeText(DetailActivity.this, "" + picker.getValue(), Toast.LENGTH_SHORT).show();
            }
        });

//// OnScrollListener
//        numberPickerMonth.setOnScrollListener(new NumberPicker.OnScrollListener() {
//            @Override
//            public void onScrollStateChange(NumberPicker picker, int scrollState) {
//                if (scrollState == SCROLL_STATE_IDLE) {
//                    Log.d("HAHA", String.format(Locale.US, "newVal: %d", picker.getValue()));
//                }
//            }
//        });
    }


    public void addEventYear() {

        final String[] data = getResources().getStringArray(R.array.year);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(data.length);
        numberPicker.setDisplayedValues(data);
        numberPicker.setValue(Calendar.getInstance().get(Calendar.YEAR));


// Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);

// Set scroller enabled
        numberPicker.setScrollerEnabled(true);

// Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);

// Set accessibility description enabled
        numberPicker.setAccessibilityDescriptionEnabled(true);

// OnClickListener
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HAHA", "Click on current value");
            }
        });

// OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.e("HAHAHA", data[picker.getValue() - 1] + "");
                Toast.makeText(DetailActivity.this, "" + data[picker.getValue() - 1], Toast.LENGTH_SHORT).show();
            }
        });

// OnScrollListener
        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    Log.d("HAHA", String.format(Locale.US, "newVal: %d", picker.getValue()));
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addEventDay() {
        calendar.setNavLeftImage(ContextCompat.getDrawable(this, R.drawable.calendar_header));
        calendar.setNavRightImage(ContextCompat.getDrawable(this, R.drawable.calendar_header));
//        Calendar current = Calendar.getInstance();
//        calendar.setCurrentMonth(current);

        Calendar startMonth = Calendar.getInstance();
        startMonth.set(2020,6,15);
        startMonth.add(Calendar.MONTH, 0);
        Calendar endMonth = (Calendar) Calendar.getInstance().clone();
        endMonth.add(Calendar.MONTH, 1);
        //startMonth.set(2020,6,11);

        calendar.setVisibleMonthRange(startMonth, endMonth);

        calendar.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onFirstDateSelected(Calendar startDate) {
                Toast.makeText(DetailActivity.this, "Start Date: " + startDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                calendar.setSelectedDateRange(startDate, endDate);
                Toast.makeText(DetailActivity.this, "Start Date: " + startDate.getTime().toString() + " End date: " + endDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}