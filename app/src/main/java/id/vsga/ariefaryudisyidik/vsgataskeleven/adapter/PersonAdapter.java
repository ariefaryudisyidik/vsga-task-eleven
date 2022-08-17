package id.vsga.ariefaryudisyidik.vsgataskeleven.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import id.vsga.ariefaryudisyidik.vsgataskeleven.R;
import id.vsga.ariefaryudisyidik.vsgataskeleven.databinding.ItemListBinding;
import id.vsga.ariefaryudisyidik.vsgataskeleven.model.Person;

public class PersonAdapter extends ArrayAdapter<Person> {

    private final List<Person> persons;
    private final Context context;

    public PersonAdapter(@NonNull Context context, @NonNull List<Person> objects) {
        super(context, R.layout.item_list, objects);
        this.persons = objects;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        ItemListBinding binding = ItemListBinding.inflate(inflater, parent, false);

        binding.tvName.setText(persons.get(position).getName());
        binding.tvAddress.setText(persons.get(position).getAddress());

        return binding.getRoot();
    }
}
