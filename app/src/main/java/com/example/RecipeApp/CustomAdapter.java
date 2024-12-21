package com.example.RecipeApp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class CustomAdapter extends BaseAdapter {

    private final Context context;
    private final List<Recipe> recipes;

    public CustomAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int i) {
        return recipes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card_item_layout, viewGroup, false);
        }
        TextView nameText = view.findViewById(R.id.nameText);
        TextView qualityText = view.findViewById(R.id.qualityText);
        TextView difficultyText = view.findViewById(R.id.difficultyText);
        MaterialButton deleteButton = view.findViewById(R.id.deleteButton);

        String name = recipes.get(i).getName();
        int quality = recipes.get(i).getQuality();
        int difficulty = recipes.get(i).getDifficulty();

        nameText.setText(name);
        qualityText.setText(String.valueOf(quality));
        difficultyText.setText(String.valueOf(difficulty));

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("quality", String.valueOf(quality));
            intent.putExtra("difficulty", String.valueOf(difficulty));
            intent.putExtra("year", String.valueOf(new Random().nextInt((LocalDate.now().getYear() - 1000) + 1) + 1000));
            context.startActivity(intent);
        });

        deleteButton.setOnClickListener(deleteView -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(R.string.dialog_delete_title);
            alertDialog.setMessage(R.string.dialog_delete_description);
            alertDialog.setPositiveButton(R.string.dialog_delete_positive, (dialog, which) -> {
                recipes.remove(i);
                notifyDataSetChanged();
            });
            alertDialog.setNegativeButton(R.string.dialog_delete_negative, (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });

        return view;
    }
}
