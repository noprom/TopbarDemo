package com.noprom.mytopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by noprom on 2014/12/17.
 */
public class Topbar extends RelativeLayout {
    // 左右button
    private Button leftButton, rightButton;
    // 中间的textView
    private TextView tvTitle;

    // 左边Button的自定义属性
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    // 右边Button的自定义属性
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    // 中间Title的属性
    private float titleTextSize;
    private int titleTextColor;
    private String mytitle;

    // LayoutParams
    private LayoutParams leftParams, rightParams, titleParams;

    private topbarClickListener listener;

    // 回调接口
    public interface topbarClickListener {
        public void leftClick();    //左边按钮的点击事件

        public void rightClick();   //右边按钮的点击事件
    }

    //设置点击事件
    public void setOnTopbarClickListener(topbarClickListener listener) {
        this.listener = listener;
    }

    public Topbar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        // 左边Button的自定义属性
        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);

        // 右边Button的自定义属性
        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);

        // 中间Title的自定义属性
        titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);
        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 0);
        mytitle = ta.getString(R.styleable.Topbar_mytitle);

        ta.recycle();

        // 创建所需要的控件
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        // 设置左边按钮的属性
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        // 设置右边按钮的属性
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        //设置标题的属性
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(mytitle);
        tvTitle.setGravity(Gravity.CENTER);

        // 设置ViewGroup的背景
        setBackgroundColor(0xFFF59563);

        // 定义左边的属性
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftParams);

        // 定义右边的属性
        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, rightParams);

        // 定义标题的属性
        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(tvTitle, titleParams);


        // 为左边的按钮添加点击事件
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.leftClick();
            }
        });

        // 为右边的按钮添加点击事件
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    public void setLeftVisibility(boolean flag){
        if(flag){
            leftButton.setVisibility(View.VISIBLE);
        }else{
            leftButton.setVisibility(View.INVISIBLE);
        }

    }
}
