package id.psw.nope;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Calendar;

public class Nope extends Activity {

    private final boolean isDebug = BuildConfig.DEBUG;
    private boolean usesSoftkey = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeNoTitle();
        setContentView(new NopeView(this));
        makeFullScreen();
        if(isDebug) writeLoadTime();
        usesSoftkey = checkSoftkey();
    }

    protected boolean checkSoftkey(){
        if(Build.VERSION.SDK_INT >= 3){
            int id = getResources().getIdentifier("config_showNavigationBar", "bool", "android");
            return id > 0 && getResources().getBoolean(id);
        }else{
            return false;
        }
    }

    protected static Boolean isNight(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return hour < 6 || hour > 17;
    }

    public void writeLoadTime(){
        long loadTime = System.nanoTime();
        Log.d("Nope", "fully created at "+loadTime);
    }

    public void makeNoTitle(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= 29){
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }

    public void makeFullScreen(){
        if(Build.VERSION.SDK_INT >= 19){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }else{
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeFullScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeFullScreen();
    }

    // Exit on back keypress only possible when the device has hardware back key
    // Since you may accidentally pressed soft back key when using this app
    // To exit devices on on-screen navigation bar, you can hold your home button
    // to open task manager / recent app menu, or use dedicated recent key on
    // newer devices
    @Override
    public void onBackPressed() {
        if(!usesSoftkey){
            super.onBackPressed();
        }
    }
}