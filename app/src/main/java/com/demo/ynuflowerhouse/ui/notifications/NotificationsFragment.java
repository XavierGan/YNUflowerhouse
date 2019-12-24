package com.demo.ynuflowerhouse.ui.notifications;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.demo.ynuflowerhouse.R;
import com.demo.ynuflowerhouse.SpUtils;

import org.json.JSONArray;
import org.json.JSONException;

public class NotificationsFragment extends Fragment {

    private JSONArray datas = new JSONArray();

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        try {
            datas = new JSONArray(SpUtils.getString(getActivity(),"good"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView lvOrder = root.findViewById(R.id.lv_order);
        lvOrder.setAdapter(new adapter());

        return root;
    }



    class adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.length();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View root = View.inflate(getActivity(), R.layout.list_item, null);
            TextView textView = root.findViewById(R.id.tv_title);
            TextView name = root.findViewById(R.id.tv_name);
            TextView addr = root.findViewById(R.id.tv_addr);
            TextView phone = root.findViewById(R.id.tv_item_phone);
            TextView count = root.findViewById(R.id.tv_item_count);


            try {
                    String order = datas.getString(i);
                    String[] orders = order.split("----");
                    textView.setText(orders[0]);
                    name.setText(orders[2]);
                    addr.setText(orders[5]);
                    phone.setText(orders[3]);
                    count.setText(orders[4]+"束");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            return root;
        }
    }
}