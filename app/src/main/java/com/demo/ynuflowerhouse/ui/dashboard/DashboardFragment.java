package com.demo.ynuflowerhouse.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.demo.ynuflowerhouse.AddOrderActivity;
import com.demo.ynuflowerhouse.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView1 = root.findViewById(R.id.textflower1);
        final TextView textView2 = root.findViewById(R.id.textflower2);
        textView1.setText("粉色康乃馨 \n10元 每支大花");
        textView2.setText("红玫瑰花束 \n88元  含11朵大花");


        root.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddOrderActivity.class);
                intent.putExtra("good","粉色康乃馨 10元 每支大花");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.btn_add2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddOrderActivity.class);
                intent.putExtra("good","红玫瑰花束 88元  含11朵大花");
                startActivity(intent);
            }
        });

        return root;
    }
}