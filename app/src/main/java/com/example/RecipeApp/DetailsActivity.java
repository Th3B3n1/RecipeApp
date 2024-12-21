package com.example.RecipeApp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView nameText = findViewById(R.id.nameText);
        TextView qualityText = findViewById(R.id.qualityText);
        TextView difficultyText = findViewById(R.id.difficultyText);
        TextView yearText = findViewById(R.id.yearText);
        MaterialButton backButton = findViewById(R.id.backButton);
        nameText.setText(getIntent().getStringExtra("name"));
        qualityText.setText(getIntent().getStringExtra("quality"));
        difficultyText.setText(getIntent().getStringExtra("difficulty"));
        yearText.setText(getIntent().getStringExtra("year"));
        backButton.setOnClickListener(view -> finish());
    }
}