package com.sati.restaurant_management_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class registration extends AppCompatActivity {

    int status=1;
    String location;
    TextInputLayout name,city,contact_number,time;
    Button submit;
    Toolbar toolbar;
    ProgressDialog progressBar;

    private int already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        location = getIntent().getStringExtra("location");

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setTitle("Saving Data");
        progressBar.setMessage("Please Wait...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        time = findViewById(R.id.hours);
        city = findViewById(R.id.city);
        contact_number = findViewById(R.id.contact_number);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (name.getEditText().getText().toString().trim().length() == 0) {
                    name.setError("Name is Required... Cant be Empty");
                    city.setError(null);
                    contact_number.setError(null);
                    Toast.makeText(registration.this, "Please Enter the name", Toast.LENGTH_SHORT).show();
                } else if (city.getEditText().getText().toString().trim().length() == 0) {
                    city.setError("City is Required... Cant be Empty");
                    name.setError(null);
                    contact_number.setError(null);
                    Toast.makeText(registration.this, "Please Enter the City", Toast.LENGTH_SHORT).show();
                } else if (contact_number.getEditText().getText().toString().trim().length() == 0) {
                    city.setError(null);
                    name.setError(null);
                    contact_number.setError("Contact number is Required... Cant be Empty");
                    Toast.makeText(registration.this, "Please Enter the contact number", Toast.LENGTH_SHORT).show();
                } else if (contact_number.getEditText().getText().toString().trim().length() < 10) {
                    city.setError(null);
                    name.setError(null);
                    contact_number.setError("Contact Length too short... 10 digits are required");
                    Toast.makeText(registration.this, "Please Enter correct  Phone number", Toast.LENGTH_SHORT).show();
                } else if (contact_number.getEditText().getText().toString().trim().length() > 10) {
                    city.setError(null);
                    name.setError(null);
                    contact_number.setError("Contact Length too long... 10 digits are required");
                    Toast.makeText(registration.this, "Please Enter the correct Phone number", Toast.LENGTH_SHORT).show();
                } else if (time.getEditText().getText().toString().trim().length() == 0) {
                    Toast.makeText(registration.this, "please enter number of hours", Toast.LENGTH_SHORT).show();
                } else {

                    progressBar.show();
                    progressBar.create();

                    String n = name.getEditText().getText().toString().trim().substring(0, 1).toUpperCase() + name.getEditText().getText().toString().trim().substring(1).toLowerCase();
                    String c = city.getEditText().getText().toString().trim().substring(0, 1).toUpperCase() + city.getEditText().getText().toString().trim().substring(1).toLowerCase();
                    String co = contact_number.getEditText().getText().toString().trim().substring(0, 1).toUpperCase() + contact_number.getEditText().getText().toString().substring(1).toLowerCase();
                    String t = time.getEditText().getText().toString().trim().substring(0, 1).toUpperCase() + time.getEditText().getText().toString().substring(1).toLowerCase();


                    HashMap<Object, String> map = new HashMap<>();
                    map.put("name", n);
                    map.put("city", c);
                    map.put("number", co);
                    map.put("time", t);
                    map.put("Tablelocation", location);


                    FirebaseDatabase.getInstance().getReference().child("booking").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            already=0;
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                                putBooking user=snapshot1.getValue(putBooking.class);

                                if (user.getTablelocation().equals(location)) {
                                    already = 1;

                                    break;
                                }
                            }

                            if(already==1)
                            {
                                progressBar.dismiss();
                                progressBar.cancel();

                                Toast.makeText(registration.this, "this table not available", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                FirebaseDatabase.getInstance().getReference().child("booking").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        progressBar.cancel();
                                        progressBar.dismiss();

                                        Toast.makeText(registration.this, "table booked", Toast.LENGTH_SHORT).show();

                                        name.getEditText().setText("");
                                        city.getEditText().setText("");
                                        contact_number.getEditText().setText("");
                                        time.getEditText().setText("");

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        progressBar.dismiss();
                                        progressBar.cancel();
                                    }
                                });
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);



    }
}