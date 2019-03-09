package com.example.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STATE_RESULT = "state_result";

    EditText etPjng, etLbr, etTinggi;
    Button btnHitung;
    TextView tvHasil;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvHasil.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPjng = findViewById(R.id.et_pjng);
        etLbr = findViewById(R.id.et_lbr);
        etTinggi = findViewById(R.id.et_tinggi);
        btnHitung = findViewById(R.id.btn_hitung);
        tvHasil = findViewById(R.id.tv_hasil);

        btnHitung.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvHasil.setText(result);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hitung){
            String inputPanjang = etPjng.getText().toString().trim();
            String inputLebar = etLbr.getText().toString().trim();
            String inputTinggi = etTinggi.getText().toString().trim();

            boolean isEmptyField = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputPanjang)) {
                isEmptyField = true;
                etPjng.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLebar)) {
                isEmptyField = true;
                etLbr.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputTinggi)) {
                isEmptyField = true;
                etTinggi.setError("Field ini tidak boleh kosong");
            }

            Double panjang = toDouble(inputPanjang);
            Double lebar = toDouble(inputLebar);
            Double tinggi = toDouble(inputTinggi);

            if (panjang == null) {
                isInvalidDouble = true;
                etPjng.setError("Field ini harus berupa nomer yang valid");
            }

            if (lebar == null) {
                isInvalidDouble = true;
                etLbr.setError("Field ini harus berupa nomer yang valid");
            }

            if (tinggi == null) {
                isInvalidDouble = true;
                etTinggi.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmptyField && !isInvalidDouble) {
                double volume = panjang * lebar * tinggi;

                tvHasil.setText(String.valueOf(volume));
            }
        }

    }

    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
