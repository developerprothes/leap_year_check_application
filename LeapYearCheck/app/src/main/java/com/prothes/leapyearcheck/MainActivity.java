package com.prothes.leapyearcheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText oneYearInput,fromYearInput,toYearInput;
    private AppCompatButton oneYearBtn,multipleChekBtn;
    private TextView display1,display2;
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getColor(R.color.sky));
        this.getWindow().setNavigationBarColor(getColor(R.color.sky));
        setContentView(R.layout.activity_main);

        oneYearInput = findViewById(R.id.oneYearInput);
        fromYearInput = findViewById(R.id.fromYearInput);
        toYearInput = findViewById(R.id.toYearInput);
        oneYearBtn = findViewById(R.id.oneYearBtn);
        multipleChekBtn = findViewById(R.id.multipleChekBtn);
        display1 = findViewById(R.id.display1);
        display2 = findViewById(R.id.display2);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        oneYearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getData = oneYearInput.getText().toString();
                if (getData.length()>0){
                    int year = Integer.parseInt(getData);
                    if ((year%400 == 0) || (year%4 == 0 && year%100 !=0)){
                        display1.setText("This "+year +" is Leap Year.");
                        display1.setVisibility(View.VISIBLE);
                    }else{
                        display1.setText("This "+year +" is not Leap Year.");
                        display1.setVisibility(View.VISIBLE);
                    }
                }else{
                    myToast("Empty");
                    oneYearInput.setError("Empty");
                    display1.setText(null);
                    display1.setVisibility(View.GONE);
                }

            }
        });


        multipleChekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getFromData = fromYearInput.getText().toString();
                String getToData = toYearInput.getText().toString();

                if (getFromData.length()>0 && getToData.length()>0){
                    int toYear = Integer.parseInt(getToData);
                    int fromYear = Integer.parseInt(getFromData);
                    if (fromYear<toYear || fromYear == toYear){
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = fromYear; i<= toYear; i++){
                            if ((i%400==0) || (i%4==0 && i%100 != 0)){
                                stringBuilder.append(i+" ");
                            }
                        }
                        if (stringBuilder.length()>0){
                            display2.setText("From "+fromYear+" to "+toYear+" - Leap Year are : \n\n"+stringBuilder);
                            nestedScrollView.setVisibility(View.VISIBLE);
                        }else{
                            display2.setText("From "+fromYear+" to "+toYear+" - Leap Year are : \n\n"+"No Leap Year");
                            nestedScrollView.setVisibility(View.VISIBLE);
                        }

                    }else{
                        myToast("Must be 1st Input small than 2nd Input");
                        display2.setText(null);
                        nestedScrollView.setVisibility(View.GONE);
                    }
                } else if (getFromData.length()>0) {
                    fromYearInput.setError(null);
                    if (getToData.length()>0){
                        toYearInput.setError(null);
                    }else{
                        toYearInput.setError("Empty");
                        display2.setText(null);
                        nestedScrollView.setVisibility(View.GONE);
                    }
                } else if (getToData.length()>0) {
                    toYearInput.setError(null);
                    if (getFromData.length()>0){
                        fromYearInput.setError(null);
                    }else{
                        display2.setText(null);
                        nestedScrollView.setVisibility(View.GONE);
                        fromYearInput.setError("Empty");
                    }
                } else{
                    fromYearInput.setError("Empty");
                    toYearInput.setError("Empty");
                    nestedScrollView.setVisibility(View.GONE);
                    display2.setText(null);
                }

            }
        });





    }

    // ===========================================================================================================================================
    public void myToast(String toastData){
        Toast.makeText(this, ""+toastData, Toast.LENGTH_SHORT).show();
    }











    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        if (isTaskRoot()){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Warning !!!")
                    .setMessage("Do you want to exit ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getColor(R.color.sky));
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getColor(R.color.sky));
        }else {
            super.onBackPressed();
        }
    }
}