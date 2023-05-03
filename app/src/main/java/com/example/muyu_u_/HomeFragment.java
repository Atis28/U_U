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
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class HomeFragment extends Fragment{

    private ImageView settingImg;
    ImageView woodenImage, resetMerit ;
    TextView animationText, txtMerit ;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private MediaPlayer mediaPlayer;
    private int selectedSound;


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
        txtMerit = view.findViewById(R.id.txt_merit_num);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("text_key", "");
        animationText.setText(text);
        animationText.setVisibility(View.GONE);

        //shrink wooden
        ScaleAnimation shrinkAnim = new ScaleAnimation(1f, 0.97f, 1f, 0.97f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrinkAnim.setDuration(60);
        ScaleAnimation restoreAnim = new ScaleAnimation(0.97f, 1f, 0.97f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        restoreAnim.setDuration(60);

        woodenImage .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                animationText.setVisibility(View.VISIBLE);
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
                // Update the count and set it on the text view
                int meritCount = Integer.parseInt(txtMerit.getText().toString()) + 1;
                txtMerit.setText(String.valueOf(meritCount));
                //shrink wooden
                AnimationSet animSet = new AnimationSet(true);
                animSet.addAnimation(shrinkAnim);
                animSet.addAnimation(restoreAnim);
                woodenImage.startAnimation(animSet);
            }
        });

        //reset merit
        resetMerit = view.findViewById(R.id.reset_merit);
        resetMerit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMerit.setText(String.valueOf(0));
            }
        });

    }

    // update
    @Override
    public void onResume() {
        super.onResume();
        // update edAnimation text cho textview
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("text_key", "");
        animationText.setText(text);

        // update sound
        SharedPreferences pref = getActivity().getSharedPreferences("sound", Context.MODE_PRIVATE);
        selectedSound = pref.getInt("selected_sound", 0);
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        // Create a new media player with the selected sound
        if (selectedSound == 0) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound1);
        } else if (selectedSound == 1) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound2);
        } else if (selectedSound == 2) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound3);
        } else {
            // Use a default sound or handle the situation in some other way
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound1);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Release the media player when the fragment is paused
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
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

}