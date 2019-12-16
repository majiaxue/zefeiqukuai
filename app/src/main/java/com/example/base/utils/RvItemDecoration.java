package com.example.base.utils;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;


public class RvItemDecoration extends RecyclerView.ItemDecoration {
    private int leftRight;
    private int top;

    public RvItemDecoration(int leftRight, int top) {
        this.leftRight = leftRight;
        this.top = top;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = leftRight;
        } else if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.right = leftRight;
        }
        outRect.top = top;
    }
}
