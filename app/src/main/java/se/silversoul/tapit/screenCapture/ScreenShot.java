package se.silversoul.tapit.screenCapture;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import se.silversoul.tapit.activity.MainActivity;

/**
 * Created by hechi1 on 2018-03-15.
 */

public class ScreenShot {

    final static File IMAGEFILE = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
    private Context context;

    public ScreenShot(Context context){
        this.context= context;
    }

    private Bitmap takeScreenshot() {
        if(IMAGEFILE.getAbsoluteFile().exists()){
            IMAGEFILE.getAbsoluteFile().delete();
            Log.d("MainActivity", "Delete a File");
        }
        View rootView = (View)((MainActivity)context).findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        Log.d("MainActivity", "Take screen shot");
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private void saveBitmap(Bitmap bitmap) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(IMAGEFILE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Log.d("MainActivity", "Save Screen Shot");
        } catch (FileNotFoundException e) {
            Log.e("MainActivity", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
    }

    public void capture(){
        Bitmap bitmap = takeScreenshot();
        saveBitmap(bitmap);
    }

}

