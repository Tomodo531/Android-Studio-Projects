package dk.tec.slutopgavehentnavnogfarce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SecondActivity extends AppCompatActivity {

    Button btnSend;
    RadioGroup radioGrpType;
    TextView editTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTxtName = findViewById(R.id.editTxtName);
        radioGrpType = findViewById(R.id.radioGrpType);

        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton radioButtonType = findViewById(radioGrpType.getCheckedRadioButtonId());
                String type = radioButtonType.getText().toString();
                String name = editTxtName.getText().toString();

                Intent intentGetName = getIntent();
                intentGetName.putExtra("Type", type);
                intentGetName.putExtra("Name", name);
                setResult(Activity.RESULT_OK, intentGetName);

                finish();
            }
        });
    }
}