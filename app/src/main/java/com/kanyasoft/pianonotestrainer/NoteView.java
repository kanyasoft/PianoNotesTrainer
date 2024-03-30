package com.kanyasoft.pianonotestrainer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class NoteView extends View {
    private String currentNote;

    public NoteView(Context context) {
        super(context);
        currentNote = "";
    }

    public NoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        currentNote = "";
    }

    public NoteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        currentNote = "";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);

        paint.setColor(Color.BLACK);
        canvas.drawText(currentNote, width / 2f, height / 2f, paint);
    }

    public void setCurrentNote(String note) {
        this.currentNote = note;
        invalidate();
    }
}
