package com.example.android.udaquiz;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class FragmentContainerActivity1 extends AppCompatActivity {

    private int i = 1;
    private int j = 0;
    private int count =0;
    private Button submit;
    private Button next;
    private Map<String,String> map;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle bundle = new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        submit = (Button) findViewById(R.id.submit);
        next = (Button) findViewById(R.id.next);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("questionBank");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("INSIDE ONDATACHANGED...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                map = (Map)dataSnapshot.getValue();
                //if(i!=0)
                //{
                String quote1 = "question"+Integer.toString(i);
                String option1 = "option"+Integer.toString(i)+"a";
                String option2 = "option"+Integer.toString(i)+"b";
                String option3 = "option"+Integer.toString(i)+"c";
                String option4 = "option"+Integer.toString(i)+"d";
                String answer = "answer"+Integer.toString(i);

                String question = map.get(quote1);
                String op1 = map.get(option1);
                String op2 = map.get(option2);
                String op3 = map.get(option3);
                String op4 = map.get(option4);
                String ans = map.get(answer);

                System.out.println("@@@@@@@@@@@@@2@@@@"+question+op1+op2+op3+op4+ans);

                bundle.putString("question",question);
                bundle.putString("option1",op1);
                bundle.putString("option2",op2);
                bundle.putString("option3",op3);
                bundle.putString("option4",op4);
                bundle.putString("answer",ans);

                        /*FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        RadioFragment radioFragTwo = new RadioFragment();
                        radioFragTwo.setArguments(bundle);
                        fragmentTransaction.add(R.id.fragmentContainer,radioFragTwo);
                        fragmentTransaction.commit();*/

                //System.out.println("ANSWER OF THE DAY 1: "+question);
                //}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        fragmentManager=getSupportFragmentManager();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                i = fragmentManager.getBackStackEntryCount();
                System.out.println("BACKSTACK ENTRY COUNT..!!"+fragmentManager.getBackStackEntryCount());
                //i = j+1;
            }
        });


        //addFragment();
        Fragment fragment = new WelcomeFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println("I AM CLICKED..!!!!!!!!!!!<<<<<<>>>>>>>>>>>>>>>>>>>>");
                        addFragment();
                    }
                };

                Thread thread = new Thread(r);
                thread.start();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /* @Override
     protected void onResume() {
         super.onResume();

         Intent mainIntent = getIntent();
         String val = mainIntent.getStringExtra("booleanVal");
         if(val == "true")
         {
             count++;
             System.out.println("COUNT_____________________________"+count);
         }

     }
 */
    private void addFragment(){

        Fragment fragment;

        switch (fragmentManager.getBackStackEntryCount()){
            case 0: //System.out.println("In Radio fragment ONE OONEONEONEONEONE");
                fragment = new RadioFragment();
                fragment.setArguments(bundle);
                break;
            case 1: //System.out.println("In Radio fragment 2222222222222222222222");
                fragment = new RadioFragment();
                fragment.setArguments(bundle);
                break;
            case 2: fragment = new CheckBoxFragment();
                fragment.setArguments(bundle);
                break;
            case 3: fragment = new TextIpFragment();
                break;
            case 4: fragment = new RadioFragment();
                fragment.setArguments(bundle);
                break;
            default: fragment = new ResultFragment(); break;
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
