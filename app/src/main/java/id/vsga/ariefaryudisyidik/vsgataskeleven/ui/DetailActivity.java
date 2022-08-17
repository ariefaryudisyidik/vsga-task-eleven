package id.vsga.ariefaryudisyidik.vsgataskeleven.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.vsga.ariefaryudisyidik.vsgataskeleven.databinding.ActivityDetailBinding;
import id.vsga.ariefaryudisyidik.vsgataskeleven.helper.DBHelper;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper = new DBHelper(this);

        binding.btnSubmit.setOnClickListener(view -> {
            String name = binding.edtName.getText().toString();
            String address = binding.edtAddress.getText().toString();
            if (name.isEmpty() && address.isEmpty()) {
                Toast.makeText(this, "Nama/Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                insert(name, address);
            }
        });
    }

    private void insert(String name, String address) {
        helper.insert(name, address);
        Toast.makeText(this, "Data diproses", Toast.LENGTH_SHORT).show();
        finish();
    }
}