package com.example.android.udaquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

public class TextIpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_container,container, false);

        if(getArguments()!= null) {
            Bundle bundle = this.getArguments();
            String fragQuestion = bundle.getString("question");
            String fragOption1 = bundle.getString("option1");
            String fragOption2 = bundle.getString("option2");
            String fragOption3 = bundle.getString("option3");
            String fragOption4 = bundle.getString("option4");
            final String fragAnswer = bundle.getString("answer");

            FrameLayout framelayout = rootView.findViewById(R.id.fragmentContainer);
            LinearLayout linearLayout = framelayout.findViewById(R.id.my_linear_layout);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            p.setMargins(10, 10, 10, 10);

            TextView textView = new TextView(getActivity());
            textView.setText(fragQuestion);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(20);
            textView.setPadding(0, 200, 0, 0);
            Log.d("View", "Start");
            try {
                linearLayout.addView(textView, p);
            } catch (Exception e) {
                e.printStackTrace();
            }

            final EditText edtxt1 = new EditText(getActivity());
            edtxt1.setTextColor(Color.WHITE);
            edtxt1.setTextSize(20);
            Log.d("View", "Start");
            try {
                linearLayout.addView(edtxt1, p);
            } catch (Exception e) {
                e.printStackTrace();
            }

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

            //linearLayout.addView(submit,params);
           // linearLayout.addView(nextFrag,paramsb);


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   String text = edtxt1.getText().toString();
                   if(text == fragAnswer)
                   {
                       String answerStmt = "true";
                       Intent intent =new Intent(getActivity().getBaseContext(),FragmentContainerActivity.class);
                       intent.putExtra("stringVal",answerStmt);
                       getActivity().startActivity(intent);
                   }
                }
            });


        }

        return rootView;
    }
}
