package com.example.incometax;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edIncome;
    TextView tvDisplay;
    Spinner spinner;
    Button button;

    int current_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edIncome = findViewById(R.id.edIncome);
        tvDisplay = findViewById(R.id.tvDisplay);
        button = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.spinner_plan,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edIncome.getText().toString().isEmpty()) {
                    tvDisplay.setText("Please enter income");
                    return;
                }

                int income = Integer.parseInt(edIncome.getText().toString());

                // ✅ Tax logic (example)
                if (income <= 350000 &&
                        (current_position == 0 || current_position == 1 || current_position == 2)) {

                    tvDisplay.setText("Your tax is 0");

                } else if (income <= 400000 &&
                        (current_position == 1 || current_position == 2)) {

                    tvDisplay.setText("Your tax is 0");

                } else if (income <= 500000 &&
                        (current_position == 1 || current_position == 2)) {

                    tvDisplay.setText("Your tax is 5%");
                } else {
                    tvDisplay.setText("Tax calculation needed");
                }
            }
        });
    }
}
