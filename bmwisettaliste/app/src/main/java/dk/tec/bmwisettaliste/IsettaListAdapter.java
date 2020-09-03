package dk.tec.bmwisettaliste;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class IsettaListAdapter extends BaseAdapter {

    private final ArrayList<Isetta> isettaCars;
    private final MainActivity mainActivity;

    public IsettaListAdapter(ArrayList<Isetta> isettaCars, MainActivity mainActivity) {
        this.isettaCars = isettaCars;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return isettaCars.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.isetta_list_item, null);

        Isetta isetta = isettaCars.get(i);

        ImageView imageView = v.findViewById(R.id.imageView);
        imageView.setImageResource(isetta.getBidle());

        TextView txtOverskrift = v.findViewById(R.id.txtOverskrift);
        txtOverskrift.setText(isetta.getOvreskrift());

        TextView txtDetaljer = v.findViewById(R.id.txtDetaljer);
        txtDetaljer.setText(isetta.getDetaljer());

        Button btnDelete = v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
                alert.setTitle("Delete Isetta!?!");
                alert.setMessage("Delete Isetta!?!");
                alert.setPositiveButton("Delete Isetta!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        isettaCars.remove(i);
                        //Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself.
                        notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("No Delete Isetta!", null);

                alert.show();
            }
        });

        return v;
    }
}
