package com.example.spiritus;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<DataAdapter> dataAdapters;

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();

        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        DataAdapter dataAdapter =  dataAdapters.get(position);

        viewHolder.TextViewID.setText(dataAdapter.getTitle());

        viewHolder.TextViewName.setText(String.valueOf(dataAdapter.getId()));

        viewHolder.TextViewPhoneNumber.setText(dataAdapter.getDate());

        viewHolder.TextViewSubject.setText(dataAdapter.getDescription());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TextViewName;
        public TextView TextViewID;
        public TextView TextViewPhoneNumber;
        public TextView TextViewSubject;


        public ViewHolder(View itemView) {

            super(itemView);

            TextViewName = (TextView) itemView.findViewById(R.id.textView2) ;
            TextViewID = (TextView) itemView.findViewById(R.id.textView4) ;
            TextViewPhoneNumber = (TextView) itemView.findViewById(R.id.textView6) ;
            TextViewSubject = (TextView) itemView.findViewById(R.id.textView8) ;


        }
    }
}
