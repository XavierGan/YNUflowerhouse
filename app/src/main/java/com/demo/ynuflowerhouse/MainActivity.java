package com.demo.ynuflowerhouse;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        if (Build.VERSION.SDK_INT >= 23) {
            int request = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                }, 123);
                return;//
            } else {
                //权限同意
            }

        } else { //低于23 不需要特殊处理  }

        }

    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==123)    {

            if (grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();

            }else if (grantResults[0]== PackageManager.PERMISSION_DENIED)        {
                Toast.makeText(this, "权限申请失败，用户拒绝权限", Toast.LENGTH_SHORT).show();

            }

        }

    }



}
