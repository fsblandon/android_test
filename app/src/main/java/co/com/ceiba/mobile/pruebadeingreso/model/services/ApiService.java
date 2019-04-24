package co.com.ceiba.mobile.pruebadeingreso.model.services;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS;
import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER;

public interface ApiService {
    @GET(GET_USERS)
    Call<Users> getUsers();

    @GET(GET_POST_USER)
    Call<ArrayList<Post>> getPosts();

    @GET(GET_POST_USER)
    Call<Class> getPostPerUser(
            @Query("userId") int idUser
    );
}
