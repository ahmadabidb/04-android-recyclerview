package com.example.a04androidrecyclerview;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a04androidrecyclerview.databinding.ActivityMainBinding;
import com.example.a04androidrecyclerview.LaptopAdapter;
import com.example.a04androidrecyclerview.LaptopData;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList lWordList = new LinkedList<String>();
    private RecyclerView lRecyclerView;
    private WordListAdapter lAdapter;
    private ArrayList<LaptopData> laptopList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLaptopList();
        @NonNull ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        lRecyclerView = findViewById(R.id.recyclerview);
        LaptopAdapter laptopAdapter = new LaptopAdapter(MainActivity.this, laptopList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lRecyclerView.setLayoutManager(layoutManager);
        lRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        lRecyclerView.setAdapter(laptopAdapter);
        laptopAdapter.setOnItemClickListener(onItemClickListener);
    }

    private void setLaptopList() {
        laptopList = new ArrayList<>();
        LaptopData data;
        data = new LaptopData(getString(R.string.acer_name), getString(R.string.acer_description), R.drawable.acer, getString(R.string.acer_details));
        laptopList.add(data);
        data = new LaptopData(getString(R.string.asus_name), getString(R.string.asus_description), R.drawable.asus, getString(R.string.asus_details));
        laptopList.add(data);
        data = new LaptopData(getString(R.string.lenovo_name), getString(R.string.lenovo_description), R.drawable.lenovo, getString(R.string.lenovo_details));
        laptopList.add(data);
    }

    public void openDetailActivity(int imageId, String details){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("image", imageId);
        intent.putExtra("details", details);
        startActivity(intent);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            LaptopData thisLaptop = laptopList.get(position);
            openDetailActivity(thisLaptop.getImage(), thisLaptop.getDetails());
        }
    };

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