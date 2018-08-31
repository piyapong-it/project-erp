package com.example.win.appbarcode20180828;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class menubar extends AppCompatActivity {

    private BottomNavigationView navigation ;
    private FrameLayout content;

    private homeFragment homeFragment;
    private main_goods_receipt_Fragment main_goods_receipt_Fragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    setFragment(homeFragment);
                    return true;
                case R.id.Goods_Receipt:
                   setFragment(main_goods_receipt_Fragment);
                    return true;
                case R.id.Goods_Return:

                    return true;
                case R.id.Goods_Packaging:

                    return true;
                case R.id.Inventory_Count:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubar);

        content = (FrameLayout) findViewById(R.id.content);
        homeFragment = new homeFragment();
        main_goods_receipt_Fragment = new main_goods_receipt_Fragment();
        setFragment(homeFragment);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content , fragment);
        fragmentTransaction.commit();
    }



}
