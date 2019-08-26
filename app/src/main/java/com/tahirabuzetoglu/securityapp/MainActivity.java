package com.tahirabuzetoglu.securityapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private String[] dangerousPermissions = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.CAMERA",
            "android.permission.CALL_PHONE",
            "android.permission.ANSWER_PHONE_CALLS",
            "android.permission.ACCESS_FINE_LOCATION",};


    private ArrayAdapter<String> myArrayAdapter;
    private ListView lv_for_apps;
    private List<String> myList;
    private String[] packageNames;
    private String[][] appsPermissions;
    private String activePermission;

    String[][] appNameAndPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_for_apps = findViewById(R.id.lv_for_apps);
        myList = new ArrayList<>();
        //dangerousPermissions = new String[5];

        Intent getValues = getIntent();
        activePermission = getValues.getStringExtra("VALUE");

        getNameAndPermissions();


        myArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, myList);
        lv_for_apps.setAdapter(myArrayAdapter);

        packageNames = clearMyArray(packageNames);


        lv_for_apps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", packageNames[position], null);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        // appsPermissions = clearMyArray(appsPermissions);

        /*for (int a = 0; a < appsPermissions.length; a++) {
            for (int b = 0; b < appsPermissions[a].length; b++) {
                    System.out.println(appsPermissions[a][b] + "\n");
            }
            System.out.println("****************************\n");
        }*/

    }

    private String[] clearMyArray(@NonNull String[] myArray) {


        int length = 0;

        for (int a = 0; a < myArray.length; a++) {
            if (myArray[a] != null) {
                length++;
            }
        }

        String[] newArray = new String[length + 1];
        System.out.println("Uzunluk " + length + " 2.zunluk" + myArray.length);

        int i = 0;
        for (int b = 0; b < myArray.length; b++) {
            if (myArray[b] != null) {
                newArray[i] = myArray[b];
                i++;
            }
        }


        return newArray;
    }


    private void getNameAndPermissions() {
        PackageManager packageManager = getPackageManager();
        //List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        appsPermissions = new String[packages.size()][300];
        packageNames = new String[packages.size()];

        int i = 0;
        for (ApplicationInfo applicationInfo : packages) {

            if ((applicationInfo.flags & (ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) > 0) {
                //System apps\\
            } else {
                //User apps\\
                //String appName = applicationInfo.loadLabel(getPackageManager()).toString();

                //Get Permissions
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, packageManager.GET_PERMISSIONS);

                    if (packageInfo.requestedPermissions != null) {
                        String[] permissions = packageInfo.requestedPermissions;

                        int permissionCount = permissions.length;

                        for (int x = 0; x < permissionCount; x++) {
                            if (activePermission.equals(permissions[x])) {

                                String appName = applicationInfo.loadLabel(getPackageManager()).toString();
                                packageNames[i] = applicationInfo.packageName;
                                System.out.println(appName + "  and package " + packageNames[i] + "\n");

                                myList.add(appName);
                                break;
                            }
                        }

                        i++;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private String checkForPermissions(String[] myArray, String appName) {
        int bound = 0;
        if (myArray.length > dangerousPermissions.length) {
            bound = dangerousPermissions.length;
        } else {
            bound = myArray.length;
        }

        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < bound; j++) {
                if (myArray[i].equals(dangerousPermissions[j])) {
                    appName += " Tehlikeli";
                }
            }
        }
        return appName;
    }
}
