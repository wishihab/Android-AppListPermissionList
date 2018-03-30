package com.wishihab.widefend;


import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AndroidException;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class GetAppPermission extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                
                PackageManager p = getPackageManager();
                StringBuffer appNameAndPermissions=new StringBuffer();
                PackageManager pm = getPackageManager();
                List<ApplicationInfo> pka = pm.getInstalledApplications(PackageManager.GET_META_DATA);

                for (ApplicationInfo applicationInfo : pka) {

                    try {
                        PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                        boolean bc = isSystemPackage(packageInfo);
                        
							Log.d("-== PROCESS ===-", "-====== GET APP LIST =======-");
                            Log.d("-==========-", "NAME APP: " + applicationInfo.name + " PACKAGENAME: " + applicationInfo.packageName);

                            String[] requestedPermissions = packageInfo.requestedPermissions;
                            if(requestedPermissions != null) {
                                for (int i = 0; i < requestedPermissions.length; i++) {
									Log.d("-== PROCESS ===-", "-====== GET PERMISSION =======-");
									Log.d("-== PERMISSIONNAME : ", requestedPermissions[i]);
                                    
                                }
                            }
                            Log.d("-== ENDPROCESS ===-", "-====== GETALLPERMISSION IN 1APP =======-");

                        } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
	
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_device) {
            Intent intent = new Intent(this, Device.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_scan) {
            Intent intent = new Intent(this, Scan.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_listapp) {
            Intent intent = new Intent(this, Listapp.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_network) {

        }else if (id == R.id.nav_findingip) {
            Intent intent = new Intent(this, FindingIP.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_folderlock) {
            Intent intent = new Intent(this, Encryption.class);
            startActivity(intent);
            finish();

        }else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareDes = "WIDefend - App to monitoring your device from trojan and unknown connection. Download github.com/wishihab";
            intent.putExtra(Intent.EXTRA_TEXT,shareDes);
            startActivity(Intent.createChooser(intent, "Share with"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
