package id.vsga.ariefaryudisyidik.vsgataskeleven.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import id.vsga.ariefaryudisyidik.vsgataskeleven.model.Person;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "digitaltalent.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE data_person (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama TEXT NOT NULL, " +
                "alamat TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS data_person");
        onCreate(sqLiteDatabase);
    }

    public void insert(String name, String address) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO data_person (nama, alamat)" +
                "VALUES ('" + name + "', '" + address + "')";
        database.execSQL(queryValues);
        database.close();
    }

    public ArrayList<Person> getAllData() {
        ArrayList<Person> persons = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM data_person";
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            persons.add(new Person(id, name, address));
        }
        database.close();
        return persons;
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        String deleteQuery = "DELETE FROM data_person WHERE id = " + id;
        database.execSQL(deleteQuery);
        database.close();
    }
}
