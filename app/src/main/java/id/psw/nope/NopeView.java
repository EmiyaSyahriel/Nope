package id.psw.nope;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

public class NopeView extends View {
    public NopeView(Context context) {
        super(context);
        initPaint();
    }

    protected Paint white;
    protected Paint black;
    protected float sd(){ return getContext().getResources().getDisplayMetrics().scaledDensity; }
    protected float sd(float i){ return sd() * i; }

    protected void initPaint(){
        white = new Paint(Paint.ANTI_ALIAS_FLAG);
        white.setColor(Color.WHITE);
        black = new Paint(Paint.ANTI_ALIAS_FLAG);
        black.setColor(Color.BLACK);
        black.setTextSize(sd(20f));
        black.setTextAlign(Paint.Align.CENTER);
    }

    protected Rect tmpRect = new Rect(0,0,0,0);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(canvas != null){
            canvas.drawPaint(Nope.isNight() ? black : white);

            Paint textPaint = Nope.isNight() ? white : black;
            String text = getContext().getString(R.string.app_name);
            float x = getWidth()/2f, y = getHeight() /2f;

            textPaint.getTextBounds(text, 0, text.length(), tmpRect);
            canvas.drawText(text, x,y + (tmpRect.width() * 0.5f), textPaint);
        }
    }
}
