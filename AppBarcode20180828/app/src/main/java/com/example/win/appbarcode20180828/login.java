package com.example.win.appbarcode20180828;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {

    private Button btn_signin;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent signin = new Intent(getApplicationContext(),menubar.class);
              startActivity(signin);
              //  if (savedInstanceState == null){
              //      getSupportFragmentManager().beginTransaction()
              //              .add(R.id.container,new summary_goods_receipt()).commit();
              //  }

            }
        });
    }
}
