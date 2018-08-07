package com.example.a17010233.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView etCategory;
    TextView etSubCategory;
    Spinner spn1;
    Spinner spn2;
    Button btnGo;
    ArrayList<String> alWeb;
    ArrayAdapter<String> aaWeb;


    @Override
    protected void onPause() {
        super.onPause();

        int p1 = spn1.getSelectedItemPosition();
        int p2 = spn2.getSelectedItemPosition();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = pref.edit();

        prefEdit.putInt("first", p1);
        prefEdit.putInt("second", p2);

        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        int thePos = pref.getInt("first", 0);
        int thePos2 = pref.getInt("second", 0);

        spn1.setSelection(thePos);
        spn2.setSelection(thePos2);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCategory = findViewById(R.id.textViewCategory);
        etSubCategory = findViewById(R.id.textViewSub);
        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);


        alWeb = new ArrayList<>();

        aaWeb = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, alWeb);

        String[] strCat = getResources().getStringArray(R.array.subCategory);
        alWeb.addAll(Arrays.asList(strCat));

        spn2.setAdapter(aaWeb);


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = spn1.getSelectedItemPosition();
                int pos2 = spn2.getSelectedItemPosition();
                alWeb.clear();

                String[][] sites = {
                        {
                            "https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life",
                        },
                        {
                            "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",
                        }
                };
                String theURL = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];

                Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                intent.putExtra("URL", theURL);
                startActivity(intent);


            }
        });

                        /*
                if (pos == 0 && pos2 == 0) {
                    Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                    intent.putExtra("URL", "https://www.rp.edu.sg/");
                    startActivity(intent);

                } else if (pos == 0 && pos2 == 1) {
                    Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                    intent.putExtra("URL", "https://www.rp.edu.sg/student-life");
                    startActivity(intent);

                } else if (pos == 1 && pos2 == 0) {
                    Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                    intent.putExtra("URL", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47");
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                    intent.putExtra("URL", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12");
                    startActivity(intent);

                }
                */

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        alWeb.clear();
                        String[] rpCategory = getResources().getStringArray(R.array.subCategory);
                        alWeb.addAll(Arrays.asList(rpCategory));
                        break;

                    case 1:
                        alWeb.clear();
                        String[] soiName = getResources().getStringArray(R.array.subCategory2);
                        alWeb.addAll(Arrays.asList(soiName));
                        break;
                }
                aaWeb.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }
}
