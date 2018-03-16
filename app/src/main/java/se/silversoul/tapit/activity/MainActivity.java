package se.silversoul.tapit.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.Map;

import se.silversoul.tapit.R;
import se.silversoul.tapit.score.Score;
import se.silversoul.tapit.screenCapture.ScreenShot;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    ConstraintLayout constraintLayout;
    TextView countView;
    TextView textView;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    final static File IMAGEFILE = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            deleteFile();
            ScreenShot screenShot = new ScreenShot(MainActivity.this);
            screenShot.capture();
            share();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        deleteFile();

        constraintLayout = (ConstraintLayout)findViewById(R.id.layout);
        countView = (TextView) findViewById(R.id.theCounter);
        textView = (TextView)findViewById(R.id.text);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        count = preferences.getInt("COUNT", 0);
        String text = preferences.getString("TEXT","Hi! Thank you for downloading this app." );

        countView.setText(String.valueOf(count));
        textView.setText(text);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count ++;
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("COUNT" ,count);
                editor.commit();
                countView.setText(String.valueOf(count));
                Score score = new Score();
                for (Map.Entry<Integer, String> entry :score.list().entrySet()) {
                    if (entry.getKey().equals(count)){
                        textView.setText(entry.getValue());
                        editor.putString("TEXT", entry.getValue());
                        editor.commit();
                    }
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            requestPermission();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }

    private void share(){
        Log.d("MainActivity", "Share");
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        Uri uri = Uri.fromFile(IMAGEFILE);
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "How bored are you ?";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "My score");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
        Log.d("MainActivity/share", "Send it away");
    }

    private void deleteFile(){
        if (IMAGEFILE.exists()) {
            IMAGEFILE.delete();
            Log.d("MainActivity/Delete","Delete");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        deleteFile();
    }
}
