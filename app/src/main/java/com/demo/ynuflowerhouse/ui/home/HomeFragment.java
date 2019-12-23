package com.demo.ynuflowerhouse.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.demo.ynuflowerhouse.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final TextView textView2 = root.findViewById(R.id.text_introduce);
        textView2.setText("康乃馨，原名：香石竹。在希腊神话中，则有许多关于康乃馨的传说，相传希腊有一位以编织康乃馨花冠为生的少女，手艺精巧，深受画家、诗人的欣赏，却因为生意兴隆，招来同业的妒忌，终致被暗杀。太阳神阿波罗为了纪念这位少女，将她变成秀丽的康乃馨，因此在希腊，有人称康乃馨为花冠，王冠，推崇其神圣的地位。");
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}