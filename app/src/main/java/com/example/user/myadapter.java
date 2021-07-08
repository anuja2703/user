package com.example.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.adname.setText(model.getName());
        holder.ademail.setText(model.getEmail());
        holder.admbl.setText(model.getMno());
        holder.adlastrechrge.setText(model.getRecharge());
        //holder.adreamainrecharge.setText(model.getUnits());
        holder.adUnitsConsumed.setText(model.getUnitsConsumed());
        holder.adreamainrecharge.setText(model.getUnitsRemaining());
        holder.delbtncustinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.ademail.getContext());
                builder.setTitle("Do you want to delete?");
                builder.setMessage("This will delete customer's account  permanently");

                builder.setPositiveButton("Yes",(dialogInterface, i) -> {
                    FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(getRef(position).getKey()).removeValue();
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }

        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView adname,ademail,admbl,adlastrechrge,adreamainrecharge,adUnitsConsumed;
        Button delbtncustinfo;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            adname=(TextView)itemView.findViewById(R.id.srcustnmtxt);
            ademail=(TextView)itemView.findViewById(R.id.srcustemailtxt);
            admbl=(TextView)itemView.findViewById(R.id.srmbltxt);
            delbtncustinfo=(Button) itemView.findViewById(R.id.srDeleteCustbtn);
            adlastrechrge=(TextView)itemView.findViewById(R.id.srlastrechargetxt);
            adreamainrecharge=(TextView)itemView.findViewById(R.id.srrechargeremainedtxt);
            adUnitsConsumed=(TextView)itemView.findViewById(R.id.srunitsconsumedtxt);
        }
    }
}
