package com.example.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // init vars
    double myBill = 0.0;
    double totalCost = 0.0;
    int numGuests = 0;
    double costPerGuest = 0.0;
    String groupTip= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // set toolbar to actionbar to help scrolling
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // show app icon in emulator
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // if an object is accessed from w/in an inner class it must be declared final
        // final variable always contains same value--cannot be reassigned.
        // access controls by using findViewById() method: R-resources, id name in strings.xml
        // cast returned value into associated object reference, here EditText

        // txtBill = restaurant bill
        // spnTip = tip %
        // spnGuests = num guests
        // result = (bil * tip %) / num guests
        final EditText bill = (EditText)findViewById(R.id.txtBill);
        final Spinner group = (Spinner)findViewById(R.id.spnGuests);
        final Spinner tip = (Spinner)findViewById(R.id.spnTip);
        Button cost = (Button)findViewById(R.id.btnCost);

        cost.setOnClickListener(new View.OnClickListener() {

            final TextView result = ((TextView)findViewById(R.id.txtResult));
            @Override
            // OnClickListener onClick() method captures user selection

            //ticket value assigned based upon selection
            public void onClick(View v) {

                //returns string representation of selected item object from spinner control
                groupTip = tip.getSelectedItem().toString();

                //returns string representation from EditText control and converts to double
                myBill = Double.parseDouble(bill.getText().toString());

                if (groupTip.equals("5%")) {
                    totalCost = myBill * 1.05;
                } else if (groupTip.equals("10%")) {
                    totalCost = myBill * 1.10;
                } else if (groupTip.equals("15%")) {
                    totalCost = myBill * 1.15;
                } else if (groupTip.equals("20%")) {
                    totalCost = myBill * 1.20;
                } else if (groupTip.equals("25%")) {
                    totalCost=myBill * 1.25;
                } else {
                    totalCost = myBill;
                }

                //returns string representation from EditText control and converts to int
                numGuests = Integer.parseInt(group.getSelectedItem().toString());
                costPerGuest = totalCost / numGuests;
                NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
                result.setText("Cost for each of " + numGuests + " guests: " + nf.format(costPerGuest));
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}