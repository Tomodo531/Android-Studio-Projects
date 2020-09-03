package dk.tec.androidapiprojekt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TecPersonListAdapter extends BaseAdapter {

    private final List<TecPerson> tecPersoner;
    private final MainActivity mainActivity;

    Intent editTecPersonIntent;

    public TecPersonListAdapter(List<TecPerson> tecPersoner, MainActivity mainActivity) {
        this.tecPersoner = tecPersoner;
        this.mainActivity = mainActivity;
    }
    @Override
    public int getCount() {
        return tecPersoner.size();
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
        View v = inflater.inflate(R.layout.tectperson_list_item, null);

        final TecPerson tecPerson = tecPersoner.get(i);

        TextView txtName = v.findViewById(R.id.txtName);
        txtName.setText(tecPerson.getName() + ", ");

        TextView txtJob = v.findViewById(R.id.txtJob);
        txtJob.setText(tecPerson.getJob());

        Button btnEdit = v.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTecPersonIntent = new Intent(mainActivity, TecPersonForm.class);
                editTecPersonIntent.putExtra("TecPerson", tecPerson);
                mainActivity.startActivityForResult(editTecPersonIntent, mainActivity.REQ_EDITPERSON_ACTIVITY);
            }
        });

        final PersonService service = ServiceBuilder.buildService(PersonService.class);
        final Call<TecPerson> deleteRequest = service.deletePersonById(tecPersoner.get(i).getId());

        Button btnDelete = v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
                alert.setTitle("Delete TecPerson!?!");
                alert.setMessage("Delete TecPerson!?!");
                alert.setPositiveButton("Delete TecPerson!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {

                        deleteRequest.enqueue(new Callback<TecPerson>() {
                            @Override
                            public void onResponse(Call<TecPerson> call, Response<TecPerson> response) {
                                if(response.isSuccessful()) {
                                    tecPersoner.remove(i);
                                    //Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself.
                                    TecPersonListAdapter.this.notifyDataSetChanged();

                                    Toast toast = Toast.makeText(mainActivity, "TecPerson deleted", Toast.LENGTH_LONG);
                                    toast.show();
                                }else {
                                    Toast toast = Toast.makeText(mainActivity, "Unable to delete TecPersoner", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<TecPerson> call, Throwable t) {
                                System.out.println("Delete Fail: " + t);

                                Toast toast = Toast.makeText(mainActivity, "Unable to delete TecPersoner", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }
                });
                alert.setNegativeButton("No Delete TecPerson!", null);

                alert.show();
            }
        });

        return v;
    }
}
