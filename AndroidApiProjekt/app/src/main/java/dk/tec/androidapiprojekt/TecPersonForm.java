package dk.tec.androidapiprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TecPersonForm extends AppCompatActivity {

    EditText edTxtName, edTxtJob, edTxtAge, edTxtHeight, edTxtShoe;
    CheckBox checkboxAdult;
    Button btnSubmit;

    PersonService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tec_person_form);

        final Intent intent = getIntent();
        final TecPerson tecPerson = (TecPerson) intent.getSerializableExtra("TecPerson");

        edTxtName = findViewById(R.id.edTxtName);
        edTxtJob = findViewById(R.id.edTxtJob);
        edTxtAge = findViewById(R.id.edTxtAge);
        edTxtHeight = findViewById(R.id.edTxtHeight);
        edTxtShoe = findViewById(R.id.edTxtShoe);
        checkboxAdult = findViewById(R.id.checkboxAdult);

        if(tecPerson.getId() != -1) {
            edTxtName.setText(tecPerson.getName());
            edTxtJob.setText(tecPerson.getJob());
            edTxtAge.setText(tecPerson.getAge() + "");
            edTxtHeight.setText(tecPerson.getHeight() + "");
            edTxtShoe.setText(tecPerson.getShoes() + "");
            checkboxAdult.setChecked(tecPerson.isAdult());
        }

        service = ServiceBuilder.buildService(PersonService.class);
        final Call<TecPerson> postNewPerson = service.postNewPerson(tecPerson);
        final Call<TecPerson> putEditPerson = service.putEditPerson(tecPerson);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tecPerson.setName(edTxtName.getText().toString());
                tecPerson.setJob(edTxtJob.getText().toString());
                tecPerson.setAge(Integer.parseInt(edTxtAge.getText().toString()));
                tecPerson.setHeight(Double.parseDouble(edTxtHeight.getText().toString()));
                tecPerson.setShoes(Integer.parseInt(edTxtShoe.getText().toString()));
                tecPerson.setAdult(checkboxAdult.isChecked());

                if (tecPerson.getId() == -1) {

                    postNewPerson.enqueue(new Callback<TecPerson>() {
                        @Override
                        public void onResponse(Call<TecPerson> call, Response<TecPerson> response) {
                            if(response.isSuccessful()) {
                                setResult(Activity.RESULT_OK, intent);
                                finish();

                                Toast toast = Toast.makeText(TecPersonForm.this, "TecPerson created", Toast.LENGTH_LONG);
                                toast.show();
                            }else {
                                Toast toast = Toast.makeText(TecPersonForm.this, "Unable to create TecPerson Please check input", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<TecPerson> call, Throwable t) {

                            Toast toast = Toast.makeText(TecPersonForm.this, "Unable to create TecPerson Please check input", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });

                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }else {
                    putEditPerson.enqueue(new Callback<TecPerson>() {
                        @Override
                        public void onResponse(Call<TecPerson> call, Response<TecPerson> response) {
                            if(response.isSuccessful()) {
                                setResult(Activity.RESULT_OK, intent);
                                finish();

                                Toast toast = Toast.makeText(TecPersonForm.this, "TecPerson updateted", Toast.LENGTH_LONG);
                                toast.show();
                            }else {
                                Toast toast = Toast.makeText(TecPersonForm.this, "Unable to Edit TecPerson Please check input", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<TecPerson> call, Throwable t) {
                            System.out.println("Edit Fail: " + t);

                            Toast toast = Toast.makeText(TecPersonForm.this, "Unable to Edit TecPerson Please check input", Toast.LENGTH_LONG);
                            toast.show();

                        }
                    });
                }



            }
        });


    }
}