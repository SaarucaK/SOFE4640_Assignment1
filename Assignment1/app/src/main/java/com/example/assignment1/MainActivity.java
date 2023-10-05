package com.example.assignment1;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/* Main Activity: Mortgage Calculator
 * This calculator accepts user input of the principal amount of their mortgage,
 * the annual interest rate, and the term in years in order to calculate the
 * equal monthly installments of payment the customer would make.
 * */

public class MainActivity extends AppCompatActivity {


    //user input and calculated result called in second activity
    public static final String PRINCIPAL_AMOUNT = "";
    public static final String INTEREST_RATE = "";
    public static final String TERM = "";
    public static final String MORTGAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create main activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Mortgage Calculator");

        //get user input for principal, interest and term
        EditText getPrincipal = findViewById(R.id.inputPrincipalAmount);
        EditText getInterest = findViewById(R.id.inputInterestRate);
        EditText getTerm = findViewById(R.id.inputTerm);

        /* Calculate Button
        * When the calculate button is submitted by the user, the use input is taken
        * and validate. After confirming that valid input is provided for all the input
        * field (no field is left empty), the user input is used to calculate the
        * monthly payment. The monthly payment, as well as the user input is then
        * placed in a Bundle object to be sent to the second activity, Mortgage Results.
        * The Mortgage Result activity (second page) is then opened.
        * */
        Button btnCalculate = (Button) findViewById(R.id.btnCalculate); //define calculate button obj
        btnCalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            //when button is clicked
            public void onClick(View view) {
                if (confirmInput()){ //check all user input to ensure no empty fields

                    int t = parseInt(getTerm.getText().toString()); //term user input, convert input from string to double to calculate
                    int n = t * 12; //calculate number of payments ie term * 12 (months)

                    double i = parseDouble(getInterest.getText().toString()); //interest user input, convert input from string to double to calculate
                    double r = i/12/100; //get monthly rate and convert from percent to decimal interest / 12(months) / 100

                    double p = parseDouble(getPrincipal.getText().toString());//get principal amount, convert input from string to double to calculate

                    //Calculate monthly mortgage payment, round to 2 decimal places
                    double mortgage = Math.round((   p*(r*Math.pow(1+r,n))/(Math.pow(1+r,n)-1)   ) * 100.0) / 100.0;

                    //String to store use inputs (principal, interest and term) and resultant mortgage payment
                    String mortgage_result = Double.toString(mortgage);
                    String principal = getPrincipal.getText().toString();
                    String interest = getInterest.getText().toString();
                    String term = getTerm.getText().toString();

                    //create Bundle object and store above strings
                    Bundle bundle = new Bundle();
                    bundle.putString("Mortgage", mortgage_result);
                    bundle.putString("Principal", principal);
                    bundle.putString("Interest", interest);
                    bundle.putString("Term", term);

                    //create intent object for seconds activity, which shows Mortgage Calculation Results
                    Intent intent = new Intent(view.getContext(), MortgageResults.class);
                    intent.putExtras(bundle); //send bundle of 4 strings to Mortgage Results Activity
                    startActivity(intent); //open new activity
                }

            }
            /* Validate User Input
             * This checks each user input text field to ensure a value is given to all fields.
             */
            public boolean confirmInput() {
                int inputLength = 1; //minimum input requirement is 1 character long
                //check principal input is less than 1 character
                if (getPrincipal.getText().length() < inputLength) {
                    getPrincipal.setError("Invalid Input"); //error message if no input
                    return false;
                ////check interest input is less than 1 character
                }else if (getInterest.getText().length() < inputLength) {
                    getInterest.setError("Invalid Input"); //error message if no input
                    return false;
                //check term input is less than 1 character
                }else if (getTerm.getText().length() < inputLength) {
                    getTerm.setError("Invalid Input"); //error message if no input
                    return false;
                }
                //return true is all fields have user input
                else{
                    return true;
                }
            }

        });

    }
}