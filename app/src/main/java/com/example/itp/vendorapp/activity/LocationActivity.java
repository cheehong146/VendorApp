package com.example.itp.vendorapp.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.itp.vendorapp.R;
import com.example.itp.vendorapp.base.BaseActivity;
import com.example.itp.vendorapp.databinding.ActivityLocationBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends BaseActivity implements View.OnClickListener {


    private GoogleMap mMap;


    //toolbar
    TextView tvToolbarTitle;
    ImageButton ibBackBtn;
    //footer, vendor related
    TextView tvVendorName;
    TextView tvVendorAddress;
    TextView tvVendorPhoneNum;
    //footer, vendor bottom section
    GridLayout gridVendorDateTimeOperation;
    Button btnNavigate;
    //footer, map related
    TextView tvDistance;

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
        GridLayout gridLayout = binding.locationBottomMasterSheet.locationFooterSecondary.gridLocationFooterOperationValues;
        String[] days = {"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String time = "11am-0.20pm";
        gridLayout.setColumnCount(2);
        gridLayout.setRowCount(days.length);
        for (int i = 0; i < days.length; i++) {
            gridLayout.addChild//TODO ADD CHILDREN HERE
        }
    }

    @Override
    public void initComponents() {
        bindComponents();
        setupListener();
    }

    @Override
    public void bindComponents() {
        ibBackBtn = binding.toolbarLocation.ibBack;
        btnNavigate = binding.locationBottomMasterSheet.locationFooterSecondary.btnLocationNavigate;
        bottomSheetBehavior = BottomSheetBehavior.from(binding.locationBottomSheet);
    }

    @Override
    public void setupListener() {
        //toolbar
        ibBackBtn.setOnClickListener(this);
        //footer
        btnNavigate.setOnClickListener(this);

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
