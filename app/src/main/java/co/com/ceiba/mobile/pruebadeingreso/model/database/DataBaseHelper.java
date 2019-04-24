package co.com.ceiba.mobile.pruebadeingreso.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.User;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "usersDB";

    private static final String TABLE_USERS = "users";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS users (" +
                "id int, " +
                "name VARCHAR(100), " +
                "username VARCHAR(100)," +
                "email VARCHAR(100)," +
                "address VARCHAR(100)," +
                "phone int," +
                "website VARCHAR(100)," +
                "company VARCHAR(100))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
        _db.execSQL(
                "DROP TABLE IF EXISTS users"
        );
        this.onCreate(_db);
    }
    
    public void addUser(User user) {
        Log.d("addUser", user.toString());
        
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());
        values.put("address", user.getAddress());
        values.put("phone", user.getPhone());
        values.put("website", user.getWebsite());
        values.put("company", user.getCompany());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                int phone = cursor.getInt(cursor.getColumnIndex("phone"));
                String website = cursor.getString(cursor.getColumnIndex("website"));
                String company = cursor.getString(cursor.getColumnIndex("company"));

                user = new User();
                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setEmail(email);
                user.setAddress(address);
                user.setPhone(phone);
                user.setWebsite(website);
                user.setCompany(company);
                users.add(user);
            } while (cursor.moveToNext());
        }
        Log.d("getAllUsers", users.toString());
        return users;
    }
}
