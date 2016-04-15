package com.example.johnwhisker.schoolproject.Activities;

import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.johnwhisker.schoolproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowResult extends AppCompatActivity {
    @Bind(R.id.ivInsideCircle)
    ImageView ivInsideCircle;
    @Bind(R.id.ivOutsideCircle)
    ImageView ivOutsideCircle;
    int valueDraw, valueIntent;
    PhotoViewAttacher mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.inside).into(ivInsideCircle);
        Glide.with(this).load(R.drawable.outside).into(ivOutsideCircle);
        mPhoto = new PhotoViewAttacher(ivInsideCircle);
        setResultCircle(100);
    }

    public void setResultCircle(int percent) {
        //       ivInsideCircle.setX(150);
//        ivInsideCircle.setY(150);


        valueIntent = percent * 6;
        for (valueDraw = 0; valueDraw <= valueIntent; valueDraw++) {
            mPhoto.setZoomable(true);
            mPhoto.setZoomTransitionDuration(1000);
            Matrix m = mPhoto.getDisplayMatrix();
            mPhoto.update();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            },250);
        }
    }
}
