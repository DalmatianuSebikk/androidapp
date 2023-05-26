package com.example.clinicapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ClinicLocationFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap  clinicMap;
    private LatLng clinicLocation = new LatLng(44.4355117, 26.1017417);

    public ClinicLocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_clinic_location, container, false);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        clinicMap = googleMap;
        clinicMap.addMarker(new MarkerOptions()
                .position(clinicLocation)
                .title("Medical Clinic Location"));
        clinicMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clinicLocation, 15f));
    }
}