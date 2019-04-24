package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.model.Users;
import co.com.ceiba.mobile.pruebadeingreso.model.adapters.ApiAdapter;
import co.com.ceiba.mobile.pruebadeingreso.model.database.DataBaseHelper;
import co.com.ceiba.mobile.pruebadeingreso.presenter.UserAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    ArrayList<User> users;
    DataBaseHelper db = new DataBaseHelper(this);

    RecyclerView recList;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = db.getUsers();

        if (users.isEmpty()) {
            Call<Users> call = ApiAdapter.getApiService().getUsers();
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.isSuccessful()) {
                        users = response.body();
                        Log.d("onResponse users:", users.toString());

                        for (User user : users) {
                            db.addUser(user);
                            Log.d("added", user.toString());
                        }



                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }
        else {
            Log.d("users", users.toString());
            recList = findViewById(R.id.recyclerViewSearchResults);
            recList.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            recList.setLayoutManager(layoutManager);

            //adapter = new UserAdapter();
            //recList.setAdapter(adapter);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}