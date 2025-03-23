package com.example.interestcalculator;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler(); // used to return to input screen

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // get views loaded for computing
        EditText txtPrincipal = findViewById(R.id.txtPrincipal);
        EditText txtRate = findViewById(R.id.txtRate);
        EditText txtYears = findViewById(R.id.txtYears);
        Button btnConvert = findViewById(R.id.btnConvert);

        TextView txtOutputPrincipal = findViewById(R.id.txtOutputPrincipal);
        TextView txtOutputRate = findViewById(R.id.txtOutputRate);
        TextView txtOutputYears = findViewById(R.id.txtOutputYears);
        TextView txtOutputTotalPaid = findViewById(R.id.txtOutputTotalPaid);
        TextView txtOutputTotalInterest = findViewById(R.id.txtOutputTotalInterest);

        ImageView house = findViewById(R.id.imgHouse);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // get values
                    double principal = Double.parseDouble(txtPrincipal.getText().toString());
                    double rate = Double.parseDouble(txtRate.getText().toString());
                    int years = Integer.parseInt(txtYears.getText().toString());

                    // calculate interest
                    double totalInterest = (principal * rate * years) / 100;
                    double totalPaid = principal + totalInterest;

                    DecimalFormat df = new DecimalFormat("#,##0.00");

                    txtOutputPrincipal.setText("Principal: $" + df.format(principal));
                    txtOutputRate.setText("Interest Rate: " + rate + "%");
                    txtOutputYears.setText("Years: " + years);
                    txtOutputTotalPaid.setText("Total Paid: $" + df.format(totalPaid));
                    txtOutputTotalInterest.setText("Total Interest: $" + df.format(totalInterest));

                    // show output
                    txtOutputPrincipal.setVisibility(View.VISIBLE);
                    txtOutputRate.setVisibility(View.VISIBLE);
                    txtOutputYears.setVisibility(View.VISIBLE);
                    txtOutputTotalPaid.setVisibility(View.VISIBLE);
                    txtOutputTotalInterest.setVisibility(View.VISIBLE);

                    // hide input fields
                    txtPrincipal.setVisibility(View.GONE);
                    txtRate.setVisibility(View.GONE);
                    txtYears.setVisibility(View.GONE);
                    btnConvert.setVisibility(View.GONE);

                    // change image based on the year # chosen
                    if (years == 10) {
                        house.setImageResource(R.drawable.ten);
                    } else if (years == 20) {
                        house.setImageResource(R.drawable.twenty);
                    } else if (years == 30) {
                        house.setImageResource(R.drawable.thirty);
                    } else {
                        // show invalid message in the result screen
                        txtOutputPrincipal.setText("Principal: $" + df.format(principal));
                        txtOutputRate.setText("Interest Rate: " + rate + "%");
                        txtOutputYears.setText("Years: " + years);
                        txtOutputTotalPaid.setText("Only 10, 20, or 30 yrs. permitted.");
                        txtOutputTotalInterest.setVisibility(View.GONE);

                        // show error output
                        txtOutputPrincipal.setVisibility(View.VISIBLE);
                        txtOutputRate.setVisibility(View.VISIBLE);
                        txtOutputYears.setVisibility(View.VISIBLE);
                        txtOutputTotalPaid.setVisibility(View.VISIBLE);
                        house.setVisibility(View.VISIBLE);

                        // hide input fields
                        txtPrincipal.setVisibility(View.GONE);
                        txtRate.setVisibility(View.GONE);
                        txtYears.setVisibility(View.GONE);
                        btnConvert.setVisibility(View.GONE);

                        // set error image
                        house.setImageResource(R.drawable.invalid);

                        // return to input screen after 5 seconds
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                resetToInputScreen(txtPrincipal, txtRate, txtYears, btnConvert,
                                        txtOutputPrincipal, txtOutputRate, txtOutputYears,
                                        txtOutputTotalPaid, txtOutputTotalInterest, house);
                            }
                        }, 5000); // 5 second delay
                        return;
                    }

                    // return to input screen after 5 seconds
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            resetToInputScreen(txtPrincipal, txtRate, txtYears, btnConvert,
                                    txtOutputPrincipal, txtOutputRate, txtOutputYears,
                                    txtOutputTotalPaid, txtOutputTotalInterest, house);
                        }
                    }, 5000); // 5 second delay

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // method to reset to the original input screen
    private void resetToInputScreen(EditText txtPrincipal, EditText txtRate, EditText txtYears, Button btnConvert,
                                    TextView txtOutputPrincipal, TextView txtOutputRate, TextView txtOutputYears,
                                    TextView txtOutputTotalPaid, TextView txtOutputTotalInterest, ImageView house) {

        // hide output views
        txtOutputPrincipal.setVisibility(View.GONE);
        txtOutputRate.setVisibility(View.GONE);
        txtOutputYears.setVisibility(View.GONE);
        txtOutputTotalPaid.setVisibility(View.GONE);
        txtOutputTotalInterest.setVisibility(View.GONE);

        // reset the house image to default house.jpg
        house.setImageResource(R.drawable.house);
        house.setVisibility(View.VISIBLE);

        // reset input fields
        txtPrincipal.setText("");
        txtRate.setText("");
        txtYears.setText("");

        // show input views again
        txtPrincipal.setVisibility(View.VISIBLE);
        txtRate.setVisibility(View.VISIBLE);
        txtYears.setVisibility(View.VISIBLE);
        btnConvert.setVisibility(View.VISIBLE);
    }
}
