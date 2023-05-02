package com.example.muyu_u_;

import static android.app.Activity.RESULT_CANCELED;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteQuery;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class HomeFragment extends Fragment{

    private ImageView settingImg;
    ImageView woodenImage ;
    TextView animationText ;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private static final int REQUEST_CODE_SETTING = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settingImg = view.findViewById(R.id.setting);
        settingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), SettingActivity.class), REQUEST_CODE_SELECT_IMAGE);
            }
        });

        woodenImage  = view.findViewById(R.id.pic_wooden);
        animationText  = view.findViewById(R.id.txtAnimation);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("text_key", "");
        animationText.setText(text);
//        updateAnimationText("hhh");
        animationText.setVisibility(View.GONE);
        final MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound1);

        woodenImage .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationText.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce_in_up);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // do nothing
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationText.setVisibility(View.GONE);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // do nothing
                    }
                });
                animationText.startAnimation(animation);
            }
        });
    }

    // update edAnimation text cho textview
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("text_key", "");
        animationText.setText(text);
    }

    // image wooden
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            int imageID = data.getIntExtra("imageID", R.drawable.wooden);
            // Update the ImageView in your HomeFragment with the selected image
            woodenImage .setImageResource(imageID);
        }
    }

    private void updateAnimationText(String value) {
        if (animationText != null) {
            animationText.setText(value);
        }
    }
}