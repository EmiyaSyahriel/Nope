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

    public NopeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public NopeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    protected Paint white;
    protected Paint black;
    protected Calendar calendar;
    protected float d(){ return getContext().getResources().getDisplayMetrics().density; }
    protected float sd(){ return getContext().getResources().getDisplayMetrics().scaledDensity; }
    protected float d(float i){ return d() * i; }
    protected float sd(float i){ return sd() * i; }
    protected int d( int i){ return Math.round(d() * i) ; }
    protected int sd(int i){ return Math.round(sd() * i) ; }

    protected void initPaint(){
        white = new Paint(Paint.ANTI_ALIAS_FLAG);
        white.setColor(Color.WHITE);
        black = new Paint(Paint.ANTI_ALIAS_FLAG);
        black.setColor(Color.BLACK);
        black.setTextSize(sd(20f));
        black.setTextAlign(Paint.Align.CENTER);
    }

    protected Rect tmpRect = new Rect(0,0,0,0);
    protected void drawText(Canvas canvas, String text, float x, float y, float yOffset, Paint paint){
        paint.getTextBounds(text, 0, text.length(), tmpRect);
        canvas.drawText(text,x,y + (tmpRect.width() * yOffset), paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(canvas != null){
            canvas.drawPaint(Nope.isNight() ? black : white);
            drawText(canvas, "Nope", getWidth()/2f, getHeight()/2f, 0.5f, Nope.isNight() ? white : black);
        }
    }
}
