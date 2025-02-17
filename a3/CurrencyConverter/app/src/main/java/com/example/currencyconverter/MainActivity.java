package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    double convertUSToEuros = .83;
    double convertUSToPesos = 19.94;
    double convertUSToCanadian = 1.27;
    double USDollarsEntered = 0.0;
    double convertedCurrency = 0.0;

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

        final EditText currency = (EditText) findViewById(R.id.txtCurrency);
        final RadioButton usToEuros = (RadioButton) findViewById(R.id.radUSToEuros);
        final RadioButton usToPesos = (RadioButton) findViewById(R.id.radUSToPesos);
        final RadioButton usToCanadian = (RadioButton) findViewById(R.id.radUSToCanadian);
        final TextView result = (TextView) findViewById(R.id.txtResult);
        final Button convert = (Button) findViewById(R.id.btnConvert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USDollarsEntered = Double.parseDouble(currency.getText().toString());

                DecimalFormat df = new DecimalFormat("#.##");

                if (usToEuros.isChecked()) {
                    if (USDollarsEntered <= 100000 && USDollarsEntered > 0) {
                        convertedCurrency = USDollarsEntered * convertUSToEuros;
                        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                        result.setText(nf.format(convertedCurrency) + " EUR");
                    } else {
                        Toast.makeText(MainActivity.this, "US Dollars must be <= 100,000 and > 0", Toast.LENGTH_LONG).show();
                    }
                }
                if (usToPesos.isChecked()) {
                    if (USDollarsEntered <= 100000 && USDollarsEntered > 0) {
                        convertedCurrency = USDollarsEntered * convertUSToPesos;

                        String COUNTRY = "MX";
                        String LANGUAGE = "es";

                        String str = NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(convertedCurrency);

                        result.setText(str + " MXN");
                    } else {
                        Toast.makeText(MainActivity.this, "US Dollars must be <= 100,000 and > 0", Toast.LENGTH_LONG).show();
                    }
                }
                if (usToCanadian.isChecked()) {
                    if (USDollarsEntered <= 100000 && USDollarsEntered > 0) {
                        convertedCurrency = USDollarsEntered * convertUSToCanadian;
                        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CANADA);
                        result.setText(nf.format(convertedCurrency) + " CAD");
                    } else {
                        Toast.makeText(MainActivity.this, "US Dollars must be < 100,000 and > 0", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}