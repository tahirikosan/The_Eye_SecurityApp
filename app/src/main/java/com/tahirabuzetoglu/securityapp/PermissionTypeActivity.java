package com.tahirabuzetoglu.securityapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.pm.PackageManager.GET_META_DATA;

public class PermissionTypeActivity extends AppCompatActivity {

    private TextView tv_camera;
    private TextView tv_internet;
    private TextView tv_read_storage;
    private TextView tv_write_storage;
    private TextView tv_location;
    private TextView tv_answer_calls;
    private TextView tv_microphone;
    private TextView tv_read_messages;
    private TextView tv_access_directory;

    private ImageView iv_help;
    private ImageView iv_camera;
    private ImageView iv_internet;
    private ImageView iv_storage_write;
    private ImageView iv_storage_read;
    private ImageView iv_location;
    private ImageView iv_answer_calls;
    private ImageView iv_microphone;
    private ImageView iv_read_messages;
    private ImageView iv_access_directory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_type);

        tv_camera = findViewById(R.id.tv_camera);
        tv_internet = findViewById(R.id.tv_internet);
        tv_read_storage = findViewById(R.id.tv_read_storage);
        tv_write_storage = findViewById(R.id.tv_write_storage);
        tv_location = findViewById(R.id.tv_location);
        tv_answer_calls = findViewById(R.id.tv_answer_calls);
        tv_microphone = findViewById(R.id.tv_microphone);
        tv_read_messages = findViewById(R.id.tv_read_messages);
        tv_access_directory = findViewById(R.id.tv_access_directory);

        iv_help = findViewById(R.id.iv_help);
        iv_camera = findViewById(R.id.iv_camera);
        iv_internet = findViewById(R.id.iv_internet);
        iv_storage_read = findViewById(R.id.iv_storage_read);
        iv_storage_write = findViewById(R.id.iv_storage_write);
        iv_location = findViewById(R.id.iv_location);
        iv_answer_calls = findViewById(R.id.iv_answer_calls);
        iv_microphone = findViewById(R.id.iv_microphone);
        iv_read_messages = findViewById(R.id.iv_read_messages);
        iv_access_directory = findViewById(R.id.iv_access_directory);


        setImages();


        iv_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               GoToLink(v);
            }
        });


        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.CAMERA");
                startActivity(intent);
            }
        });

        tv_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.INTERNET");
                startActivity(intent);
            }
        });


        tv_read_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.READ_EXTERNAL_STORAGE");
                startActivity(intent);
            }
        });


        tv_write_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.WRITE_EXTERNAL_STORAGE");
                startActivity(intent);
            }
        });



        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.ACCESS_FINE_LOCATION");
                startActivity(intent);
            }
        });

        tv_answer_calls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.ANSWER_PHONE_CALLS");
                startActivity(intent);
            }
        });

        tv_microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.RECORD_AUDIO");
                startActivity(intent);
            }
        });

        tv_read_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.READ_SMS");
                startActivity(intent);
            }
        });

        tv_access_directory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionTypeActivity.this, MainActivity.class);
                intent.putExtra("VALUE","android.permission.READ_PHONE_NUMBERS");
                startActivity(intent);
            }
        });

    }

    private void setImages(){
        iv_camera.setImageResource(R.drawable.camera_logo);
        iv_internet.setImageResource(R.drawable.internet_logo);
        iv_storage_write.setImageResource(R.drawable.storage_logo);
        iv_storage_read.setImageResource(R.drawable.storage_logo);
        iv_location.setImageResource(R.drawable.location_logo);
        iv_answer_calls.setImageResource(R.drawable.call_logo);
        iv_microphone.setImageResource(R.drawable.microphone_logo);
        iv_read_messages.setImageResource(R.drawable.message_logo);
        iv_access_directory.setImageResource(R.drawable.rehber_logo);
    }

    private void GoToLink(View view){
        String url = view.getTag().toString();
        Intent showInBrowser = new Intent(Intent.ACTION_VIEW);
        showInBrowser.addCategory(Intent.CATEGORY_BROWSABLE);

        showInBrowser.setData(Uri.parse(url));
        startActivity(showInBrowser);

    }

}
