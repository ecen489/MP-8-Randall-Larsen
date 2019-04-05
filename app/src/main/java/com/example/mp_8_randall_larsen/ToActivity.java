package com.example.mp_8_randall_larsen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ToActivity extends Activity implements MyRecyclerViewAdapter.ItemClickListener {
  Button createButton;
    Button loginButton;
    EditText emailText;
    EditText passwordText;
    EditText IDText;

    FirebaseDatabase fbdb;
    DatabaseReference dbrf;

    FirebaseAuth mAuth;
    FirebaseUser user = null;

    private static final int REQ_CODE_ANS = 2468;
    ArrayList<String> animalNames = new ArrayList<>();
    String Class_Data = "Blank";
    String Class_Data2 = "Blank";
    public int studIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        setContentView(R.layout.to_activity);
        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();

        createButton=findViewById(R.id.button1);
        loginButton=findViewById(R.id.button2);
        emailText=findViewById(R.id.email);
        passwordText=findViewById(R.id.password);
        IDText=findViewById(R.id.userID);

        mAuth = FirebaseAuth.getInstance();



        // data to populate the RecyclerView with


        // set up the RecyclerView

    }





    public void gradeClick(View view) {
            int studID = Integer.parseInt(IDText.getText().toString());
            DatabaseReference gradeKey = dbrf.child("simpsons/grades/");
            DatabaseReference studentKey = dbrf.child("simpsons/students/");

            Query query_student = studentKey.orderByChild("id").equalTo(studID);
            query_student.addListenerForSingleValueEvent(valueEventListener2);

            Query query = gradeKey.orderByChild("student_id").equalTo(studID);
            query.addListenerForSingleValueEvent(valueEventListener);
    }


    public void Query2Click(View view) {
       int studIDs = Integer.parseInt(IDText.getText().toString());
        DatabaseReference gradeKey = dbrf.child("simpsons/grades/");
        DatabaseReference studentKey = dbrf.child("simpsons/students/");
        while(studIDs < 900) {
           if(studIDs ==123){
                Query query_student = studentKey.orderByChild("id").equalTo(studIDs);
                query_student.addListenerForSingleValueEvent(valueEventListener4);
              //Toast.makeText(getApplicationContext(),"Bart",Toast.LENGTH_SHORT).show();
               Query query = gradeKey.orderByChild("student_id").equalTo(studIDs);
               query.addListenerForSingleValueEvent(valueEventListener3);
               studIDs = 404;
            }
            else if(studIDs ==404 ){
                Query query_student = studentKey.orderByChild("id").equalTo(studIDs);
                query_student.addListenerForSingleValueEvent(valueEventListener5);
               // Toast.makeText(getApplicationContext(),"Ralph",Toast.LENGTH_SHORT).show();
               Query query = gradeKey.orderByChild("student_id").equalTo(studIDs);
               query.addListenerForSingleValueEvent(valueEventListener8);
               studIDs = 456;
            }
            else if(studIDs ==456 ){
                Query query_student = studentKey.orderByChild("id").equalTo(studIDs);
                query_student.addListenerForSingleValueEvent(valueEventListener6);
            //   Toast.makeText(getApplicationContext(),"Mill",Toast.LENGTH_SHORT).show();
               Query query = gradeKey.orderByChild("student_id").equalTo(studIDs);
               query.addListenerForSingleValueEvent(valueEventListener9);
               studIDs = 888;
            }
            else {
                Query query_student = studentKey.orderByChild("id").equalTo(studIDs);
                query_student.addListenerForSingleValueEvent(valueEventListener7);
             //  Toast.makeText(getApplicationContext(),"Lisa",Toast.LENGTH_SHORT).show();
               Query query = gradeKey.orderByChild("student_id").equalTo(studIDs);
               query.addListenerForSingleValueEvent(valueEventListener10);
               studIDs = 999;

            }



           // studIDs++;
        }
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);

                    int studID = Integer.parseInt(IDText.getText().toString());


                    if(grade.getcourse_name().equals("Math")) {
                        animalNames.add(Class_Data+", "+grade.course_name+", "+grade.getgrade());
                    }
                   else if(grade.getcourse_name().equals("Physics")) {
                        animalNames.add(Class_Data+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Chemistry")) {
                        animalNames.add(Class_Data+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Biology")) {
                        animalNames.add(Class_Data+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else{
                        animalNames.add(Class_Data+", "+grade.course_name+", "+grade.getgrade());
                    }
                    RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ToActivity.this));
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(ToActivity.this, animalNames);
                    adapter.setClickListener(ToActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };

    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);
                    Student student = snapshot.getValue(Student.class);

                    int studID = Integer.parseInt(IDText.getText().toString());
                   if(student.getstudent_id()==studID) {
                        Class_Data = student.getstudent_name();

                    }
                    RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ToActivity.this));
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(ToActivity.this, animalNames);
                    adapter.setClickListener(ToActivity.this);
                    recyclerView.setAdapter(adapter);

                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };



    ValueEventListener valueEventListener3 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);

                    Class_Data2 = "Bart";

                    if(grade.getcourse_name().equals("Math")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Physics")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Chemistry")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Biology")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else{
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ToActivity.this));
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(ToActivity.this, animalNames);
                    adapter.setClickListener(ToActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };


    ValueEventListener valueEventListener8 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);

                    Class_Data2 = "Ralph";

                    if(grade.getcourse_name().equals("Math")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Physics")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Chemistry")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Biology")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else{
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ToActivity.this));
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(ToActivity.this, animalNames);
                    adapter.setClickListener(ToActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };

    ValueEventListener valueEventListener9 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);

                    Class_Data2 = "Milhouse";

                    if(grade.getcourse_name().equals("Math")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Physics")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Chemistry")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Biology")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else{
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ToActivity.this));
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(ToActivity.this, animalNames);
                    adapter.setClickListener(ToActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };

    ValueEventListener valueEventListener10 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);

                    Class_Data2 = "Lisa";

                    if(grade.getcourse_name().equals("Math")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Physics")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Chemistry")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else if(grade.getcourse_name().equals("Biology")) {
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    else{
                        animalNames.add(Class_Data2+", "+grade.course_name+", "+grade.getgrade());
                    }
                    RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ToActivity.this));
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(ToActivity.this, animalNames);
                    adapter.setClickListener(ToActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };
    ValueEventListener valueEventListener4 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                int studID = Integer.parseInt(IDText.getText().toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);

                    if(student.getstudent_id()==123) {
                       // Class_Data2 = student.getstudent_name();
                      //  Class_Data2 = "Bart";

                    }
                    Class_Data2 = "Bart";
                 }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };
    ValueEventListener valueEventListener5 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                int studID = Integer.parseInt(IDText.getText().toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);

                    if(student.getstudent_id()==404) {
                      //  Class_Data2 = student.getstudent_name();
                       // Class_Data2 = "Raulfz";

                    }
                    Class_Data2 = "Ralph";
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };
    ValueEventListener valueEventListener6 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                int studID = Integer.parseInt(IDText.getText().toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);

                    if(student.getstudent_id()==456) {
                      //  Class_Data2 = student.getstudent_name();
                     //   Class_Data2 = "Mill";
                        Toast.makeText(getApplicationContext(),"Mill",Toast.LENGTH_SHORT).show();

                    }
                    Class_Data2 = "Milhouse";
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };
    ValueEventListener valueEventListener7 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                int studID = Integer.parseInt(IDText.getText().toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);

                    if(student.getstudent_id()==888) {
                       // Class_Data2 = student.getstudent_name();
                       // Class_Data2 = "Lisa";

                    }
                    Class_Data2 = "Lisa";
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };
    @Override
    public void onItemClick(View view, int position) {

    }


    public void outClick(View view) {
        Intent backIntent = new Intent();
        setResult(RESULT_OK, backIntent);
        finish();
    }

    public void pushClick(View view) {
        Intent intent = new Intent(ToActivity.this, PushActivity.class);
        startActivity(intent);
    }


}
