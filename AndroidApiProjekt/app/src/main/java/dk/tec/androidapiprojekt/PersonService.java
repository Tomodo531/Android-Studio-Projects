package dk.tec.androidapiprojekt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonService {

    @GET("Person")
    Call<List<TecPerson>> getAllPerson();

    @GET("Person/{id}")
    Call<TecPerson> getPersonById(@Path("id") int id);

    @DELETE("Person/{id}")
    Call<TecPerson> deletePersonById(@Path("id") int id);

    @POST("Person")
    Call<TecPerson> postNewPerson(@Body TecPerson tecPerson);

    @PUT("Person")
    Call<TecPerson> putEditPerson(@Body TecPerson tecPerson);
}
