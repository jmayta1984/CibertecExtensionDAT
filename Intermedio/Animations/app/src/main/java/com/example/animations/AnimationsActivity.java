package com.example.animations;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationsActivity extends AppCompatActivity {

    ImageView ivImage;
    Button btZoom, btClockwise, btFade, btMove, btTransition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);


        ivImage = findViewById(R.id.ivImage);
        btZoom = findViewById(R.id.btZoom);
        btClockwise = findViewById(R.id.btClockwise);
        btFade = findViewById(R.id.btFade);
        btMove = findViewById(R.id.btMove);
        btTransition = findViewById(R.id.btTransition);

        btZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation =
                        AnimationUtils.loadAnimation(AnimationsActivity.this,
                                R.anim.zoom);
                ivImage.startAnimation(animation);
            }
        });

        btClockwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimationsActivity.this,
                        R.anim.clockwise);
                ivImage.startAnimation(animation);
            }
        });

        btFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimationsActivity.this,
                        R.anim.fade);
                ivImage.startAnimation(animation);
            }
        });

        btMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimationsActivity.this,
                        R.anim.move);

                ivImage.startAnimation(animation);
            }
        });


        btTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimationsActivity.this,
                        TransitionsActivity.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(AnimationsActivity.this,
                                ivImage,"android");
                startActivity(intent, options.toBundle());
            }
        });

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimationsActivity.this,
                        TransitionsActivity.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(AnimationsActivity.this,
                                ivImage,"android");
                startActivity(intent, options.toBundle());
            }
        });

    }
}
