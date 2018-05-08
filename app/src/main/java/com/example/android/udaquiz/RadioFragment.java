package com.example.android.udaquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class RadioFragment extends Fragment  {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceAsColor")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_fragment_container,container, false);
        int j =4;
        System.out.println("I am In FRAGMENT..!!<<<<<<<<<<<<<<<<<<<<<<<<");
        if(getArguments()!= null)
        {
           /* Handler eventHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {

                }
            };*/

            Bundle bundle = this.getArguments();
            String fragQuestion = bundle.getString("question");
            String fragOption1 = bundle.getString("option1");
            String fragOption2 = bundle.getString("option2");
            String fragOption3 = bundle.getString("option3");
            String fragOption4 = bundle.getString("option4");
            final String fragAnswer = bundle.getString("answer");

            System.out.println("IN FRAGMENT...!!!!!!!!!!!!!!!!!!!))))))))"+fragQuestion+fragOption1+fragOption2+fragOption3+fragOption4+fragAnswer);
            final int i = 5;
            FrameLayout framelayout = rootView.findViewById(R.id.fragmentContainer);

            LinearLayout linearLayout = framelayout.findViewById(R.id.my_linear_layout);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            p.setMargins(10,10,10,10);

            TextView textView = new TextView(getActivity());

            textView.setId(i); //Set id to remove in the future.
            textView.setText(fragQuestion);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(20);
            textView.setPadding(0,200,0,0);
            Log.d("View","Start");
            try{
                linearLayout.addView(textView,p);
            }catch(Exception e){
                e.printStackTrace();
            }

            final RadioGroup radioGroup = new RadioGroup(getActivity());

            final int a = 1;
            RadioButton rdBtn1 = new RadioButton(getActivity());
            rdBtn1.setId(a);
            rdBtn1.setText(fragOption1);
            rdBtn1.setTextColor(Color.WHITE);
            radioGroup.addView(rdBtn1,p);

            final int b = 2;
            RadioButton rdBtn2 = new RadioButton(getActivity());
            rdBtn2.setId(b);
            rdBtn2.setText(fragOption2);
            rdBtn2.setTextColor(Color.WHITE);
            radioGroup.addView(rdBtn2,p);

            final int c = 3;
            RadioButton rdBtn3 = new RadioButton(getActivity());
            rdBtn3.setId(c);
            rdBtn3.setText(fragOption3);
            rdBtn3.setTextColor(Color.WHITE);
            radioGroup.addView(rdBtn3,p);

            final int d = 4;
            RadioButton rdBtn4 = new RadioButton(getActivity());
            rdBtn4.setId(d);
            rdBtn4.setText(fragOption4);
            rdBtn4.setTextColor(Color.WHITE);
            radioGroup.addView(rdBtn4,p);


            linearLayout.addView(radioGroup,p);

            //LinearLayout innerLinearLayout = framelayout.findViewById(R.id.inner_layout_1);


            final int ab = 5;
            Button submit = new Button(getActivity());
            submit.setId(ab);
            submit.setText("SUBMIT");
            submit.setBackgroundResource(R.color.colorAccent);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.topMargin = 550;
            params.leftMargin=10;
            params.rightMargin=10;

            submit.setTextColor(Color.WHITE);

            final int bc = 6;
            Button nextFrag = new Button(getActivity());
            nextFrag.setId(ab);
            LinearLayout.LayoutParams paramsb = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            paramsb.topMargin = 20;
            paramsb.leftMargin = 10;
            paramsb.rightMargin = 10;

            nextFrag.setText("NEXT");
            nextFrag.setBackgroundResource(R.color.colorAccent);
            nextFrag.setTextColor(Color.WHITE);

            linearLayout.addView(submit,params);
            linearLayout.addView(nextFrag,paramsb);


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(int i=0; i<radioGroup.getChildCount(); i++) {
                        RadioButton btn = (RadioButton) radioGroup.getChildAt(i);
                        String selectedOption = (String) btn.getText();
                        if(selectedOption == fragAnswer) {
                            // do something with text
                            String answerStmt = "true";
                            Intent intent =new Intent(getActivity().getBaseContext(),FragmentContainerActivity.class);
                            intent.putExtra("stringVal",answerStmt);
                            getActivity().startActivity(intent);
                        }
                    }

                }
            });
            //innerLinearLayout.addView(submit,p);
            //innerLinearLayout.addView(nextFrag,p);


            //System.out.println("QUESTION..===="+fragQuestion+"---"+fragAnswer+"---");

        }
        //return inflater.inflate(R.layout.activity_fragment_container,container,false);
        return rootView;

    }


}
