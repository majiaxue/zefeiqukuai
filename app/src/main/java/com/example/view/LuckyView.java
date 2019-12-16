package com.example.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.zefeiqukuai.R;

import java.util.ArrayList;
import java.util.List;

public class LuckyView extends View {

    private Paint mPaint;
    private float mStrokeWidth = 5;
    private int mRepeatCount = 5; // 转的圈数
    private int mRectSize; // 矩形的宽和高（矩形为正方形）
    private boolean mShouldStartFlag;
    private boolean mShouldStartNextTurn = true; // 标记是否应该开启下一轮抽奖
    private int mStartLuckPosition = 0; // 开始抽奖的位置
    private int mCurrentPosition = -1; // 当前转圈所在的位置

    private OnLuckAnimationEndListener mLuckAnimationEndListener;

    /**
     * 可以通过对 mLuckNum 设置计算策略，来控制用户 中哪些奖 以及 中大奖 的概率
     */
    private int mLuckNum = 3; // 默认最终中奖位置
    private List<RectF> mRectFs; // 存储矩形的集合
    private int mItemColor = Color.parseColor("#00ffffff"); // 矩形的颜色
    private String[] mPrizeDescription = {};
    private int[] mLuckyPrizes = {R.drawable.yanshi, R.drawable.yanshi, R.drawable.yanshi, R.drawable.yanshi, R.drawable.yanshi, R.drawable.yanshi, R.drawable.yanshi, R.drawable.yanshi, R.drawable.lijichoujiang};
    private List<String> lettersBeans;
    private float left;
    private float top;
    private Bitmap[] bitmaps;
    private String[] luckyName;
    private String[] id;
    private int selectPos;


    public LuckyView(Context context) {
        this(context, null);
    }

    public LuckyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 抗锯齿
        mPaint.setStyle(Paint.Style.FILL);
        // mPaint.setStyle(Paint.Style.STROKE); // 设置样式为描边
        mPaint.setStrokeWidth(mStrokeWidth); // 设置描边的宽度
        mRectFs = new ArrayList<>();
    }

    public void setLuckAnimationEndListener(OnLuckAnimationEndListener luckAnimationEndListener) {
        mLuckAnimationEndListener = luckAnimationEndListener;
    }

    public int getLuckNum() {
        return mLuckNum;
    }

    public void setLuckNum(int luckNum) {
        mLuckNum = luckNum;
    }

    public int[] getLuckyPrizes() {
        return mLuckyPrizes;
    }

    public void setLuckyPrizes(int[] luckyPrizes) {
        mLuckyPrizes = luckyPrizes;
    }

    public String[] getPrizeDescription() {
        return mPrizeDescription;
    }

    public void setPrizeDescription(String[] prizeDescription) {
        mPrizeDescription = prizeDescription;
        invalidate();
    }

    //设置图片，文字数据
    public void setData(List<String> lettersBeans) {
        this.lettersBeans = lettersBeans;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 矩形的宽高
        mRectSize = (Math.min(w, h) - 60) / 3;
        // 当控件大小改变的时候清空数据
        mRectFs.clear();
        initNineRect();
    }

    /**
     * 初始化 9 个矩形（正方形）的位置信息
     */
    private void initNineRect() {
        final float width = getWidth();

        // 加载前三个矩形
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                float left = 0;
                float right = mRectSize;
                float top = 0;
                float bottom = mRectSize;
                RectF rectF = new RectF(left, top, right, bottom);
                mRectFs.add(rectF);
            } else if (i == 1) {
                float left = mRectSize + 30;
                float right = 2 * mRectSize + 30;
                float top = 0;
                float bottom = mRectSize;
                RectF rectF = new RectF(left, top, right, bottom);
                mRectFs.add(rectF);
            } else if (i == 2) {
                float left = 2 * mRectSize + 60;
                float right = 3 * mRectSize + 60;
                float top = 0;
                float bottom = mRectSize;
                RectF rectF = new RectF(left, top, right, bottom);
                mRectFs.add(rectF);
            }
        }

        // 加载第 4 个矩形
        mRectFs.add(new RectF(width - mRectSize, mRectSize + 30, width, 2 * mRectSize + 30));

        // 加载第 5~7 个矩形
        for (int j = 3; j > 0; j--) {
            if (j == 3) {
                float left = width - mRectSize;
                float right = width;
                float top = 2 * mRectSize + 60;
                float bottom = 3 * mRectSize + 60;
                RectF rectF = new RectF(left, top, right, bottom);
                mRectFs.add(rectF);
            } else if (j == 2) {
                float left = mRectSize + 30;
                float right = 2 * mRectSize + 30;
                float top = 2 * mRectSize + 60;
                float bottom = 3 * mRectSize + 60;
                RectF rectF = new RectF(left, top, right, bottom);
                mRectFs.add(rectF);
            } else if (j == 1) {
                float left = 0;
                float right = mRectSize;
                float top = 2 * mRectSize + 60;
                float bottom = 3 * mRectSize + 60;
                RectF rectF = new RectF(left, top, right, bottom);
                mRectFs.add(rectF);
            }
        }

        // 加载第 8 个矩形
        mRectFs.add(new RectF(0, mRectSize + 30, mRectSize, 2 * mRectSize + 30));

        // 加载中心第 9 个矩形
        mRectFs.add(new RectF(mRectSize + 30, mRectSize + 30, 2 * mRectSize + 30, 2 * mRectSize + 30));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //执行真正的绘制矩形操作
        drawNineRect(canvas);
        if (mPrizeDescription.length == 8) {
            // 填充奖品图片
            drawNineBitmaps(canvas);
            // 填充奖品文字
            drawNineText(canvas);
        }

    }

    /**
     * 在每个矩形中填充奖品图片
     * left：The position of the left side of the bitmap being drawn
     * top：The position of the top side of the bitmap being drawn
     */
    private void drawNineBitmaps(final Canvas canvas) {

        for (int i = 0; i < mRectFs.size(); i++) {
            RectF rectF = mRectFs.get(i);
            // 将图片设置在每个矩形中央
            left = rectF.left;
            top = rectF.top;
            canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), mLuckyPrizes[i]), mRectSize - 10, mRectSize - 10, false), left + 5, top + 5, null);
        }
    }


    /**
     * 在每个矩形中央填充文字，代替抽奖图片
     * x：he x-coordinate of the origin of the text being drawn
     * y：The y-coordinate of the baseline of the text being drawn
     */
    private void drawNineText(Canvas canvas) {
        float x = 0, y = 0;
        mPaint.setColor(Color.parseColor("#222222"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(40); // unit px
        for (int i = 0; i < mRectFs.size(); i++) {
            if (i == mRectFs.size() - 1) {
                RectF rectF = mRectFs.get(i);
                x = rectF.left + 20; // 将文字设置在每个矩形中央
                y = rectF.top + mRectSize / 2;
                canvas.drawText("", x, y, mPaint);
            } else {
                if ("谢谢惠顾".equals(mPrizeDescription[i])) {
                    RectF rectF = mRectFs.get(i);
                    x = rectF.left + mRectSize / 6; // 将文字设置在每个矩形中央
                    y = rectF.top + mRectSize / 2 + 20;
                } else if (mPrizeDescription[i].length() == 2) {
                    RectF rectF = mRectFs.get(i);
                    x = rectF.left + mRectSize / 3; // 将文字设置在每个矩形中央
                    y = rectF.top + mRectSize / 2 + 20;
                } else {
                    RectF rectF = mRectFs.get(i);
                    x = rectF.left + mRectSize / 4; // 将文字设置在每个矩形中央
                    y = rectF.top + mRectSize / 2 + 20;
                }
                canvas.drawText(mPrizeDescription[i], x, y, mPaint);
            }
        }
    }

    /**
     * 执行真正的绘制矩形操作
     */
    private void drawNineRect(Canvas canvas) {
        for (int x = 0; x < mRectFs.size(); x++) {
            RectF rectF = mRectFs.get(x);
            if (x == 8) {
                mPaint.setColor(Color.WHITE);
            } else {
                if (mCurrentPosition == x) {
                    mPaint.setColor(Color.parseColor("#FFFFE600"));
                } else {
                    mPaint.setColor(mItemColor); // 标记当前转盘经过的位置
                }
            }
            canvas.drawRect(rectF, mPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mShouldStartFlag = mRectFs.get(8).contains(event.getX(), event.getY());
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mShouldStartFlag) {
                if (mRectFs.get(8).contains(event.getX(), event.getY())) {
                    // mLuckAnimationEndListener.onClickLuck();
//                    startAnim(); // 判断只有手指落下和抬起都在中间的矩形内时才开始执行动画抽奖
                    mLuckAnimationEndListener.onClick();
                }
                mShouldStartFlag = false;
            }
        }
        return super.onTouchEvent(event);
    }

    public void startAnim(int index) {
        if (!mShouldStartNextTurn) {
            return;
        }
//        Random random = new Random();
//        setLuckNum(random.nextInt(8)); // 生成 [0,8) 的随机整数
        setLuckNum(index);

        ValueAnimator animator = ValueAnimator.ofInt(mStartLuckPosition, mRepeatCount * 8 + mLuckNum)
                .setDuration(5000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final int position = (int) animation.getAnimatedValue();
                setCurrentPosition(position % 8);
                mShouldStartNextTurn = false;
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mShouldStartNextTurn = true;
                mStartLuckPosition = mLuckNum;
                //最终选中的位置
                if (mLuckAnimationEndListener != null) {
                    mLuckAnimationEndListener.onLuckAnimationEnd(mCurrentPosition,
                            mPrizeDescription[mCurrentPosition]);
                }
            }
        });

        animator.start();
    }

    private void setCurrentPosition(int position) {
        mCurrentPosition = position;
        invalidate(); // 强制刷新，在 UI 线程回调 onDraw()
    }

    public void setBitmap(Bitmap[] bitmaps1, String[] name, String[] strings) {
        bitmaps = bitmaps1;
        luckyName = name;
        id = strings;
        invalidate();
    }

    /**
     * 选中id
     *
     * @param datas
     */
    public void setSelectId(int datas) {
        String selectId = datas + "";

        if (id != null && id.length != 0) {
            for (int i = 0; i < id.length; i++) {
                if (id[i].equals(selectId)) {
                    selectPos = i;
                }
            }
        }

//        startAnim();
    }

    /**
     * 用于抽奖结果回调
     */
    public interface OnLuckAnimationEndListener {
        void onLuckAnimationEnd(int pos, String msg);

        void onClick();
    }
}
