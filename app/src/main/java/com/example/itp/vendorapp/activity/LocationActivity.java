package com.example.itp.vendorapp.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivityLocationBinding;
import com.example.itp.vendorapp.utils.UIDimensionUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends BaseActivity implements View.OnClickListener {
    private GoogleMap mMap;

    BottomSheetBehavior bottomSheetBehavior;

    ActivityLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location);

        initComponents();

        setupBottomSheetBehavior();

        setupVendorDateTimeOperation();

        initMapFrag();
    }

    private void initMapFrag() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                // Add a marker in Sydney and move the camera
                LatLng lekkersCafe = new LatLng(3.075040, 101.586190);
                mMap.addMarker(new MarkerOptions().position(lekkersCafe).title("Lekkers Cafe"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(lekkersCafe));
            }
        });
    }

    private void setupBottomSheetBehavior() {
        //disable the bottom sheet from being hidden
        bottomSheetBehavior.setHideable(false);
        //set the height of the peek height to the height of the first sheet
    }

    private void setupVendorDateTimeOperation() {

        TableLayout tableLayout = binding.locationBottomMasterSheet.locationFooterSecondary.tableLocationFooterOperationValues;
        String[] days = {"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String time = "11am-9.20pm";

        for (int row = 0; row < days.length; row++) {
            TableRow tableRow = getTableRow();
            //add the textview here
            tableRow.addView(getDateTimeOperationTextView(days[row]), 0);

            TextView tvTime = getDateTimeOperationTextView(time);
            tvTime.setPadding(UIDimensionUtils.dpToPx(18), 0, 0, 0);
            tableRow.addView(tvTime, 1);

            tableLayout.addView(tableRow);
        }
    }

    private TextView getDateTimeOperationTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(getResources().getColor(R.color.color_text_white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        return textView;
    }

    private TableRow getTableRow() {
        TableRow tableRow = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        //set styling/spacing for the table row
        int paddingPixel = UIDimensionUtils.dpToPx(12);
        layoutParams.setMargins(paddingPixel, paddingPixel, paddingPixel, paddingPixel);
        tableRow.setLayoutParams(layoutParams);
        return tableRow;
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void setupListener() {
        //toolbar
        binding.toolbarLocation.ibBack.setOnClickListener(this);
        //footer
        binding.locationBottomMasterSheet.locationFooterSecondary.btnLocationNavigate.setOnClickListener(this);

        bottomSheetBehavior = BottomSheetBehavior.from(binding.locationBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                startActivityClearTop(LocationActivity.this, MainActivity.class);
                break;
            case R.id.btn_location_navigate:
                //TODO select waze or google map to navigate
                break;
            case R.id.expandable_footer_main:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //set the peek height to the first top section of the bottom sheet
        bottomSheetBehavior.setPeekHeight(binding.locationBottomMasterSheet.locationFooterMain.getRoot().getHeight());
    }
}
