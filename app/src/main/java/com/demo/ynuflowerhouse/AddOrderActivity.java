package com.demo.ynuflowerhouse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.json.JSONArray;
import org.json.JSONException;

public class AddOrderActivity extends AppCompatActivity {

    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
//原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    private EditText etAddr,etRoom,etName,etCount,etPhone;

    private String good = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        good = getIntent().getStringExtra("good");

        etAddr = findViewById(R.id.et_addr);
        etRoom = findViewById(R.id.et_room);
        etName = findViewById(R.id.et_name);
        etCount = findViewById(R.id.et_count);
        etPhone = findViewById(R.id.et_phone);

        findViewById(R.id.btn_addr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddOrderActivity.this, "获取位置中", Toast.LENGTH_SHORT).show();
                mLocationClient = new LocationClient(getApplicationContext());
                //声明LocationClient类
                mLocationClient.registerLocationListener(myListener);
                //注册监听函数
                mLocationClient.setLocOption(initClient());
                mLocationClient.start();
            }
        });


        findViewById(R.id.order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray jsonArray;
                String goods = SpUtils.getString(AddOrderActivity.this,"good");
                try {
                    if(goods.equals("")){
                        jsonArray = new JSONArray();
                    }else{
                        jsonArray = new JSONArray(goods);
                    }
                    String order = good+"----"+etRoom.getText()+"----"+etName.getText()+"----"+etPhone.getText()+"----"+etCount.getText()+"----"+etAddr.getText();
                    jsonArray.put(order);
                    Log.d("jsonArray",jsonArray.toString());
                    SpUtils.saveString(AddOrderActivity.this,"good",jsonArray.toString());
                    Toast.makeText(AddOrderActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }


    class  MyLocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location) {

            String addr = location.getAddrStr();    //获取详细地址信息
            etAddr.setText(addr);
        }
    }


    public LocationClientOption initClient(){

        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(30000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setIsNeedAddress(true);
        //需要详细地址

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        return option;
    }



}
