package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.bakingapp.model.Step;

public class VideoActivity extends AppCompatActivity {
    TextView mStepDescriptionTextView;
    Button mNextButton;
    private Step mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mStepDescriptionTextView = findViewById(R.id.tv_step_description);
        mNextButton = findViewById(R.id.btn_next);

        Intent intent = getIntent();
        mStep = intent.getParcelableExtra(Intent.EXTRA_TEXT);
        if (intent != null) {
            if (intent.hasExtra(Intent.EXTRA_TEXT)) {
                populateUI();
            }
        }
    }


    private void populateUI() {
        String rating = mStep.getDescription();
        mStepDescriptionTextView.setText(rating);
    }

}
