package com.example.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.adname.setText(model.getName());
        holder.ademail.setText(model.getEmail());
        holder.admbl.setText(model.getMno());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView adname,ademail,admbl;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            adname=(TextView)itemView.findViewById(R.id.srcustnmtxt);
            ademail=(TextView)itemView.findViewById(R.id.srcustemailtxt);
            admbl=(TextView)itemView.findViewById(R.id.srmbltxt);
        }
    }
}
