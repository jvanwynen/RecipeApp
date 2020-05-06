package com.example.recipeapp.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;
import com.example.recipeapp.models.RecipeWithIngredients;
import com.example.recipeapp.viewmodel.RecipeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecipeViewModel recipeViewModel;

    RecyclerView recyclerView;
    Button editRecipe, newRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RecipeAdapter adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        editRecipe = findViewById(R.id.button_edit_main);
        newRecipe = findViewById(R.id.button_new_main);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        recipeViewModel.getAllRecipes().observe(this, new Observer<List<RecipeWithIngredients>>() {
            @Override
            public void onChanged(List<RecipeWithIngredients> recipeWithIngredients) {
                adapter.setRecipes(recipeWithIngredients);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
