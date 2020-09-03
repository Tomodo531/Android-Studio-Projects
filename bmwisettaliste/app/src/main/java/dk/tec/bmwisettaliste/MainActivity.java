package dk.tec.bmwisettaliste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Isetta> isettaCars = new ArrayList<Isetta>();

        isettaCars.add(new Isetta(R.drawable.bmw_isetta1, "Isetta 1", "Det er en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta2, "Isetta 2", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta3, "Isetta 3", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta4, "Isetta 4", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta5, "Isetta 5", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta6, "Isetta 6", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta7, "Isetta 7", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta8, "Isetta 8", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta9, "Isetta 9", "Det er også en bil... Så det"));
        isettaCars.add(new Isetta(R.drawable.bmw_isetta10, "Isetta 10", "Det er også en bil... Så det"));

        IsettaListAdapter adapter = new IsettaListAdapter(isettaCars, this);
        ListView lstIsettaCars = findViewById(R.id.lstIsettaCars);
        lstIsettaCars.setAdapter(adapter);
    }
}