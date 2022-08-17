package id.vsga.ariefaryudisyidik.vsgataskeleven.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import id.vsga.ariefaryudisyidik.vsgataskeleven.adapter.PersonAdapter;
import id.vsga.ariefaryudisyidik.vsgataskeleven.databinding.ActivityMainBinding;
import id.vsga.ariefaryudisyidik.vsgataskeleven.helper.DBHelper;
import id.vsga.ariefaryudisyidik.vsgataskeleven.model.Person;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Person> persons;
    private DBHelper helper;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addData();
        deleteData();
        setupListView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    private void addData() {
        binding.fabAdd.setOnClickListener(view -> navigateToDetail());
    }

    private void deleteData() {
        binding.lvPerson.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Person person = persons.get(i);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Hapus data " + person.getName() + "?");
            builder.setPositiveButton("Cancel", null);
            builder.setNegativeButton("Hapus", (dialogInterface, i1) -> {
                helper.delete(person.getId());
                persons.remove(person);
                adapter.notifyDataSetChanged();
            });
            builder.create().show();
            return false;
        });
    }

    private void setupListView() {
        helper = new DBHelper(this);
        persons = helper.getAllData();
        adapter = new PersonAdapter(this, persons);
        binding.lvPerson.setAdapter(adapter);
    }

    private void navigateToDetail() {
        startActivity(new Intent(this, DetailActivity.class));
    }
}