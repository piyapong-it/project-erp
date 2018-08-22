package com.example.win.simple_barcode;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private  static  final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission()) {
                Toast.makeText(MainActivity.this , "Permission is granted" , Toast.LENGTH_LONG).show();
            } else {
                requestPermissions();
            }
        }
    }

    private  boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(this , new String[]{CAMERA_SERVICE},REQUEST_CAMERA);
    }
    public  void onRequestPermissionsResult(int requestCode , String permission[] ,int grantResults[]){
        switch (requestCode){
            case REQUEST_CAMERA:
                if (grantResults.length > 0){
                    boolean camataAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (camataAccepted){
                        Toast.makeText(MainActivity.this , "Permission Granted" ,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this , "Permission Denied" ,Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if (shouldShowRequestPermissionRationale(CAMERA_SERVICE)){
                            displayAlerMessage("You need to allow access for both permission",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{CAMERA_SERVICE},REQUEST_CAMERA);
                                            }
                                        }
                                    });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume(){

        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkPermission()){
                if (scannerView == null){
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();

    }
    public void displayAlerMessage (String message , DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK" , listener)
                .setNegativeButton("cancel" , null)
                .create()
                .show();
    }
    @Override
    public void handleResult(Result result) {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(MainActivity.this);
            }
        });
        builder.setNegativeButton("visit" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                startActivity(intent);
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();

    }
}
