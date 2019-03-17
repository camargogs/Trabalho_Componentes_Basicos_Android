package com.example.gomes.trabalho1mobile;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private Button button1;

    private static String VALUE = "myValue";

    ListView listView;

    MediaPlayer mySound;


    private ToggleButton toggleButton1, toggleButton2;
    private Button btnDisplay;

    private RadioGroup radioGroup;


    EditText eText;
    Button btn;

    Spinner spnr;

    String[] celebrities = {
            "Chris Hemsworth",
            "Jennifer Lawrence",
            "Jessica Alba",
            "Brad Pitt",
            "Tom Cruise",
            "Johnny Depp",
            "Megan Fox",
            "Paul Walker",
            "Vin Diesel"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, button1);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                MainActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });

        mySound = MediaPlayer.create(this,R.raw.dog);

        TextView txtView = (TextView) findViewById(R.id.txtView);
        txtView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),
                        "You have pressed it long :)", 2000).show();
                return true;
            }
        });
        txtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Not Long Enough :(",
                        1000).show();
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        spnr = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, celebrities);
        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spnr.getSelectedItemPosition();
                        Toast.makeText(getApplicationContext(),"You have selected "+celebrities[+position],Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );


        String str[] = {"Arun", "Mathev", "Vishnu", "Vishal", "Arjun",
                "Arul", "Balaji", "Babu", "Boopathy", "Godwin", "Nagaraj"};

        AutoCompleteTextView t1 = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, str);

        t1.setThreshold(1);
        t1.setAdapter(adp);

        addListenerOnButton();

        eText = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String str = eText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG);
                msg.show();
            }
        });
    }

    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                Toast.LENGTH_SHORT).show();
    }
    public void playMusic(View view){
        mySound.start();
    }

    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup.clearCheck();
    }

    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }
    private void addListenerOnButton() {
        toggleButton1 = findViewById(R.id.toggleButton1);
        toggleButton2 = findViewById(R.id.toggleButton2);
        btnDisplay = findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(toggleButton1.getText());
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

                Toast.makeText(MainActivity.this, result.toString(),
                        Toast.LENGTH_SHORT).show();

            }

        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        RelativeLayout main_view = findViewById(R.id.main_view);

        switch(item.getItemId()){
            case R.id.menu_red:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.RED);
                return true;
            case R.id.menu_green:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.menu_yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.YELLOW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void startNewActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(VALUE, "My custom string value");
        startActivity(intent);
    }
}



