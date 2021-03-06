package com.sati.restaurant_management_system;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter  extends FirebaseRecyclerAdapter<putTable,myadapter.myviewholder> {



    public myadapter(@NonNull FirebaseRecyclerOptions<putTable> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull putTable model) {

        holder.seats.setText(model.getSeats());
        holder.location.setText(model.getLocation());

        holder.book.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent=new Intent(holder.book.getContext(),registration.class);
                                               intent.putExtra("location",holder.location.getText().toString().trim());
                                               holder.book.getContext().startActivity(intent);
                                           }
                                       }
        );

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);

        return new myviewholder(view);
    }

    public  class  myviewholder extends RecyclerView.ViewHolder {


        ImageView img;
        TextView seats,location,noofseat,locationname;
        Button book;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            noofseat=itemView.findViewById(R.id.locationname);
            book=itemView.findViewById(R.id.book);
            img=itemView.findViewById(R.id.img1);
            seats=itemView.findViewById(R.id.seats);
            location=itemView.findViewById(R.id.location);


        }
    }

}
