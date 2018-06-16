package br.com.lucasfogaca.stadsinhoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustumAdapter extends ArrayAdapter<Trabalho> {

    private Context context;

    public CustumAdapter(Context context, int resouce, ArrayList<Trabalho> objects){
        super(context, resouce, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Trabalho t = getItem(position);

        if (convertView == null){
             convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView txtTitulo = convertView.findViewById(R.id.txt_label);
        TextView txtId = convertView.findViewById(R.id.txt_label_id);


        txtTitulo.setText(t.getTitulo());
        txtId.setText(t.getId().toString());

        return convertView;

    }

}
