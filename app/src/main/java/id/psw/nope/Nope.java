package id.psw.nope;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.Calendar;

public class Nope extends Activity {

    private boolean isDebug = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeNoTitle();
        setContentView(new NopeView(this));
        makeFullScreen();
        if(isDebug) writeLoadTime();
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
    }

    public void makeFullScreen(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
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
}