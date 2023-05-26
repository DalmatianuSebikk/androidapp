package com.example.clinicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    TextView textFullName;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        textFullName = rootView.findViewById(R.id.textFullName);
        Button logOutButton = rootView.findViewById(R.id.logOutButton);

        LinearLayout medicalReportLayout = rootView.findViewById(R.id.layoutReport);
        LinearLayout clinicLocationLayout = rootView.findViewById(R.id.layoutLocation);
        LinearLayout moreInfoLayout = rootView.findViewById(R.id.layoutInfo);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if( name != null) {
            Log.d("Name:", name);
            textFullName.setText(name);
        }

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getActivity(), "Log out successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), RegisterLoginActiviy.class);
                startActivity(intent);
            }
        });

        clinicLocationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Fragment Home", "clicking");
                Fragment clinicLocationFragment = new ClinicLocationFragment();
                ((MainActivity) getActivity()).replaceFragment(clinicLocationFragment);
            }
        });

        moreInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment moreInfoFragment = new MoreInfoFragment();
                ((MainActivity) getActivity()).replaceFragment(moreInfoFragment);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}