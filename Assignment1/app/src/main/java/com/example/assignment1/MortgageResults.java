package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
/*
* Second Activity: Mortgage Results
* This takes the user input from the main page and the calculated payment results and
* displays them on the the results page. A back button is also provided to allow the
* user to navigate back to the calculator.
* */
public class MortgageResults extends AppCompatActivity {
    //textview fields to display the user input (from the mortgage calculator main activity, and monthly payment result
    TextView principal;
    TextView mortgage;
    TextView interest;
    TextView term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create second activity, Mortgage Results
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enable back button
        setTitle("My Mortgage Payment"); //set header title

        Bundle bundle = getIntent().getExtras(); //instantiate bundle to retrieve input and result info from calculator

        //check if bundle contains information
        if (bundle != null) {

            //saves each input and result value in their corresponding strings
            String m = bundle.getString("Mortgage");
            String p = bundle.getString("Principal");
            String i = bundle.getString("Interest");
            String t = bundle.getString("Term");

            //retrieve text field where each value will be displayed
            principal = findViewById(R.id.showPrincipal);
            mortgage = findViewById(R.id.showPayment);
            interest = findViewById(R.id.showInterest);
            term = findViewById(R.id.showTerm);

            //display strings on the of text field
            principal.setText(p);
            mortgage.setText(m);
            interest.setText(i);
            term.setText(t);
        }

    }
}