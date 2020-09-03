package dk.tec.slutopgavehentnavnogfarce;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button btnGetName, btnGetColor;
    TextView txtNameColor;

    String type = "";
    String name = "";

    Intent intentGetName;
    Intent intentGetColor;

    public final static int REQ_GETNAME_ACTIVITY = 0;
    public final static int REQ_GETCOLOR_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNameColor = findViewById(R.id.txtNameColor);

        btnGetName = findViewById(R.id.btnGetName);
        btnGetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentGetName = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intentGetName, REQ_GETNAME_ACTIVITY);
            }
        });

        btnGetColor = findViewById(R.id.btnGetColor);
        btnGetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentGetColor = new Intent(MainActivity.this, ThirdActivity.class);
                intentGetColor.putExtra("Type", type);
                startActivityForResult(intentGetColor, REQ_GETCOLOR_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_GETNAME_ACTIVITY){
            if (resultCode == Activity.RESULT_OK){
                type = data.getStringExtra("Type");
                name = data.getStringExtra("Name");
                txtNameColor.setText( type + ": " + name);
            }
        }

        if (requestCode == REQ_GETCOLOR_ACTIVITY){
            if (resultCode == Activity.RESULT_OK){
                String color = data.getStringExtra("Color");
                txtNameColor.setBackgroundColor(Color.parseColor(color));
            }
        }
    }
}