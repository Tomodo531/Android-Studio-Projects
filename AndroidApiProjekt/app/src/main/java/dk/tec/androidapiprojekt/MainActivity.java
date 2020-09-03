package dk.tec.androidapiprojekt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddTecPerson;
    ListView lstTecPersoner;
    List<TecPerson> tecPersoner = new ArrayList<TecPerson>();
    PersonService service;

    TecPersonListAdapter adapter;

    Intent newTecpersonIntent;

    public static final int REQ_NEWPERSON_ACTIVITY = 0;
    public static final int REQ_EDITPERSON_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstTecPersoner = findViewById(R.id.lstTecPersoner);

        btnAddTecPerson = findViewById(R.id.btnAddTecPerson);
        btnAddTecPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTecpersonIntent = new Intent(MainActivity.this, TecPersonForm.class);
                newTecpersonIntent.putExtra("TecPerson", new TecPerson());
                startActivityForResult(newTecpersonIntent, REQ_NEWPERSON_ACTIVITY);
            }
        });

        getTecPersoner();
    }

    private void getTecPersoner(){

        service = ServiceBuilder.buildService(PersonService.class);
        Call<List<TecPerson>> requestList = service.getAllPerson();

        requestList.enqueue(new Callback<List<TecPerson>>() {
            @Override
            public void onResponse(Call<List<TecPerson>> call, Response<List<TecPerson>> response) {

                tecPersoner = response.body();
                adapter = new TecPersonListAdapter(tecPersoner, MainActivity.this);
                lstTecPersoner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TecPerson>> call, Throwable t) {

                Toast toast = Toast.makeText(MainActivity.this, "Unable to get TecPersoner", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_NEWPERSON_ACTIVITY){
            if (resultCode == Activity.RESULT_OK){
                getTecPersoner();
            }
        }

        if (requestCode == REQ_EDITPERSON_ACTIVITY){
            if (resultCode == Activity.RESULT_OK){
                getTecPersoner();
            }
        }
    }
}