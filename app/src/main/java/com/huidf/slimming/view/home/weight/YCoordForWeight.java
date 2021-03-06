package com.huidf.slimming.view.home.weight;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.huidf.slimming.R;
import com.huidf.slimming.context.ApplicationData;

import java.util.ArrayList;
import java.util.List;

import huitx.libztframework.utils.LayoutUtil;

/**
 * 体重历史记录Y轴坐标
 * @author ZhuTao
 * @date 2018/12/15
 * @params
*/


public class YCoordForWeight extends View{

    private float tb;
    private Paint paint_text;

    private int colorText = ApplicationData.context.getResources().getColor(R.color.text_color_hint);

    /** 换行数 */
    private int cutIndex;
    /** 圆柱圆点距离画布底部的距离 */
    private float marginBottom;
    /** 纵坐标轴的纵向间距 */
    private float marginVertical;
    /** 绘制高度 */
    private float drawHight;
    /** 字体大小 */
    public float textSize = 0;

    /** Y坐标轴原点的位置 */
    public float YPoint = 0;
    public List<String> yCoords;

    public YCoordForWeight(Context context) {
        super(context);
    }
    public YCoordForWeight(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDetectionData(context,attrs);
    }
    public YCoordForWeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDetectionData(context,attrs);
    }

    /**
     * 初始化数据
     * @param context
     * @param attrs
     */
    public void setDetectionData(Context context, AttributeSet attrs) {

        TypedArray attribute = context.obtainStyledAttributes(attrs, R.styleable.chart_ycoords);
        String ycoordinate = attribute.getString(R.styleable.chart_ycoords_ycoordinate);
        cutIndex = attribute.getInteger(R.styleable.chart_ycoords_ycoordcut,2);
        marginBottom = attribute.getFloat(R.styleable.chart_ycoords_ycoormargin_bottom,100);
        drawHight = attribute.getFloat(R.styleable.chart_ycoords_ycoordraw_height,1000);
        yCoords = new ArrayList<String>();
        String[] yCorrdinateArray =  ycoordinate.split(",");
        for (int i = 0; i < yCorrdinateArray.length; i++) {
            yCoords.add(yCorrdinateArray[i]);
        }

        init();
    }

    public void init() {
//		if(percent == null) percent = new ArrayList<LineTableEntity>();

        Resources res = getResources();
        tb = res.getDimension(R.dimen.detection_10);
        textSize = tb * 1f;	//10sp

        marginBottom = LayoutUtil.getInstance().getWidgetHeight(marginBottom);
        drawHight = LayoutUtil.getInstance().getWidgetHeight(drawHight);
        marginVertical = drawHight/(yCoords.size()-1);

        paint_text = new Paint();
        paint_text.setStrokeWidth(textSize);
        paint_text.setTextSize(textSize);
        paint_text.setColor(colorText);
        paint_text.setTextAlign(Paint.Align.CENTER);
        paint_text.setAntiAlias(true);
        paint_text.setStyle(Paint.Style.FILL);	//实心

    }

    protected void onDraw(Canvas canvas) {
//		canvas.drawColor(0xffee03fd);
        drawDate(canvas);
    }


    /**
     *	x轴 y轴文字描述信息
     * @param canvas
     */
    public void drawDate(Canvas canvas) {
        for (int i = 0; i < yCoords.size(); i++) {
            String text;
            if(yCoords.get(i).length()>cutIndex){
                TextPaint textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setColor(ApplicationData.context.getResources().getColor(R.color.text_color_hint));
                textPaint.setStrokeWidth(tb*0.1f);	//画笔宽度1sp
                textPaint.setStyle(Paint.Style.FILL);	//实心
                textPaint.setTextSize(textSize);	//字号10sp
                textPaint.setTextAlign(Paint.Align.CENTER);

                String t1 = yCoords.get(i);
                text = t1.substring(0,cutIndex) + "\n" + t1.substring(cutIndex);

                StaticLayout layout = new StaticLayout(text, textPaint, 10000, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);

                canvas.save();
                canvas.translate( getWidth()/2, getHeight() - marginBottom - i* marginVertical - layout.getHeight()/2);
                layout.draw(canvas);
                canvas.restore();//别忘了restore

            }else{
                text = yCoords.get(i);
                canvas.drawText(text, getWidth()/2, getHeight() - marginBottom - i* marginVertical + textSize/4,paint_text);
            }

        }
    }

    //设置视图的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		setMeasuredDimension(XLength, (int) (0.352f  *screenHeight));
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);//获得控件的高度
        setMeasuredDimension(width, height);
        YPoint = height - marginBottom;
    }

}
