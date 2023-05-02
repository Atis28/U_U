package com.example.muyu_u_;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class SettingActivity extends AppCompatActivity {
    ImageView w1, w2, w3, w4, w5, w6;
    int selectedImageResourceId;
    EditText edAnimation;
    AppCompatButton btnAnimation;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();

        selectedImageResourceId = R.drawable.wooden; // Default image
        w1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageResourceId = R.drawable.wooden;
                setProfilePicture(v);
            }
        });
        w2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageResourceId = R.drawable.wooden2;
                setProfilePicture(v);
            }
        });
        w3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageResourceId = R.drawable.wooden3;
                setProfilePicture(v);
            }
        });
        w4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageResourceId = R.drawable.wooden4;
                setProfilePicture(v);
            }
        });
        w5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageResourceId = R.drawable.wooden5;
                setProfilePicture(v);
            }
        });
        w6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageResourceId = R.drawable.wooden6;
                setProfilePicture(v);
            }
        });

        //edittext
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final String[] savedText = {sharedPreferences.getString("text_key", "")};
        edAnimation.setText(savedText[0]); // set saved text to EditText

        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edAnimation.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("text_key", text);
                editor.apply();
                savedText[0] = text; // update savedText with the new value of text
                Toast.makeText(SettingActivity.this, "oke", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void setProfilePicture(View view) {
        // Creating a Return intent to pass to the Main Activity
        Intent returnIntent = new Intent();
        // Figuring out which image was clicked
        ImageView selectedImage = (ImageView) view;
        // Adding stuff to the return intent
        returnIntent.putExtra("imageID", selectedImageResourceId);
        setResult(RESULT_OK, returnIntent);
        // Finishing Activity and return to main screen!
        finish();
    }

    private void initView(){
        w1 = findViewById(R.id.pic_wooden_1);
        w2 = findViewById(R.id.pic_wooden_2);
        w3 = findViewById(R.id.pic_wooden_3);
        w4 = findViewById(R.id.pic_wooden_4);
        w5 = findViewById(R.id.pic_wooden_5);
        w6 = findViewById(R.id.pic_wooden_6);

        edAnimation = findViewById(R.id.edit_txt_sub);
        btnAnimation = findViewById(R.id.btn_animation);
    }
}