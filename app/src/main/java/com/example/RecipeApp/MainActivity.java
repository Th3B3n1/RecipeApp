package com.example.RecipeApp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText qualityInput;
    private EditText difficultyInput;
    private final List<Recipe> recipes = new ArrayList<>();
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.nameInput = findViewById(R.id.nameInput);
        this.qualityInput = findViewById(R.id.qualityInput);
        this.difficultyInput = findViewById(R.id.difficultyInput);
        ListView recipeList = findViewById(R.id.recipeList);
        this.adapter = new CustomAdapter(this, recipes);
        recipeList.setAdapter(adapter);
        MaterialButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> addToListView());
    }

    private void addToListView()
    {
        try {
            String name = nameInput.getText().toString();
            int quality = Integer.parseInt(qualityInput.getText().toString());
            int difficulty = Integer.parseInt(difficultyInput.getText().toString());
            if (!name.isEmpty())
            {
                if (quality > 0 && quality <= 100)
                {
                    if (difficulty >= 0 && difficulty <= 3)
                    {
                        recipes.add(new Recipe(name, quality, difficulty));
                        this.adapter.notifyDataSetChanged();
                        Reset();
                    }
                    else
                    {
                        Toast.makeText(this, R.string.input_difficulty_error, Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(this, R.string.input_quality_error, Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this, R.string.input_name_error, Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, R.string.input_exception, Toast.LENGTH_LONG).show();
        }
    }
    private void Reset()
    {
        nameInput.setText("");
        qualityInput.setText("");
        difficultyInput.setText("");
    }
}