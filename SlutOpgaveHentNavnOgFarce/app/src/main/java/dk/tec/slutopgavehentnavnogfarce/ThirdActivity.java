package dk.tec.slutopgavehentnavnogfarce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ThirdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Intent intentGetColor;

    TextView txtTypeColor;
    View viewColorBlock;
    Spinner spinRed, spinGreen, spinBlue;
    Button btnSendColor;

    String red = "00";
    String green = "00";
    String blue = "00";

    String[] colorCode = {"00", "10", "20", "30", "40", "50", "60", "70", "80", "90", "A0", "B0", "C0", "D0", "E0", "F0", "FF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        intentGetColor = getIntent();

        txtTypeColor = findViewById(R.id.txtTypeColor);
        txtTypeColor.setText(intentGetColor.getStringExtra("Type") + "s Color");

        viewColorBlock = findViewById(R.id.viewColorBlock);

        spinRed = findViewById(R.id.spinRed);
        spinRed.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterRed = new ArrayAdapter<String>(ThirdActivity.this,  android.R.layout.simple_spinner_item, colorCode);
        adapterRed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRed.setAdapter(adapterRed);

        spinGreen = findViewById(R.id.spinGreen);
        spinGreen.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterGreen = new ArrayAdapter<String>(ThirdActivity.this,  android.R.layout.simple_spinner_item, colorCode);
        adapterGreen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinGreen.setAdapter(adapterGreen);

        spinBlue = findViewById(R.id.spinBlue);
        spinBlue.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterBlue = new ArrayAdapter<String>(ThirdActivity.this,  android.R.layout.simple_spinner_item, colorCode);
        adapterBlue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBlue.setAdapter(adapterBlue);

        btnSendColor = findViewById(R.id.btnSendColor);
        btnSendColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentGetColor.putExtra("Color", "#" + red + green + blue);
                setResult(Activity.RESULT_OK, intentGetColor);

                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch(adapterView.getId()){
            case R.id.spinRed:
                red = spinRed.getItemAtPosition(i).toString();
                break;
            case R.id.spinGreen:
                green = spinGreen.getItemAtPosition(i).toString();
                break;
            case R.id.spinBlue:
                blue = spinBlue.getItemAtPosition(i).toString();
                break;
            default:
        }

        viewColorBlock.setBackgroundColor(Color.parseColor("#" + red + green + blue));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}