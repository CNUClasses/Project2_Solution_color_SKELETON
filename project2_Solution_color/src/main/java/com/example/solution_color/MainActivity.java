package com.example.solution_color;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.library.bitmap_utilities.BitMap_Helpers;

public class MainActivity extends AppCompatActivity {

    //permission request stuff for 6.0 and above
    private String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private static final int PERMS_REQ_CODE = 200;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         ActionBar actionBar = getSupportActionBar();
         getSupportActionBar().setDisplayShowTitleEnabled(false);
         getSupportActionBar().setDisplayShowHomeEnabled(false);


        //if running on a version > 22 then need runtime permissions
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1)
            requestPermissions(perms, PERMS_REQ_CODE);
        else
            postPermissionSetup();

     }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        boolean writeAccepted = false;
        boolean readAccepted = false;

        switch (permsRequestCode) {
            case PERMS_REQ_CODE:
                writeAccepted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                readAccepted = grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED;
                 break;
        }

        if (writeAccepted&& readAccepted)
            postPermissionSetup();
        else
            Toast.makeText(this,"YOU DONT HAVE PROPER PERMISSIONS",Toast.LENGTH_SHORT).show();
    }

    //use this function if you want, I use it after permissions are organized
    private void postPermissionSetup(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_settings:
                Intent myIntent = new Intent(this, SettingsActivity.class);
                startActivity(myIntent);
                break;
            default:
                break;
        }
        return true;
    }
}

