package com.example.crudproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstname,lastname;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText)findViewById(R.id.frstName);
        lastname = (EditText)findViewById(R.id.lastName);
        textView = (TextView)findViewById(R.id.listView);

        controller = new DB_Controller(this,"",null,1);


    }

    public void btnClick(View view) {
        switch(view.getId()) {
            case R.id.addBtn:
                try {
                    controller.insert_student(firstname.getText().toString(),lastname.getText().toString());
                }catch (SQLiteException e) {
                    Toast.makeText(MainActivity.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dltBtn:
                controller.delete_student(firstname.getText().toString());
                break;
            case R.id.updBtn:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ENTER NEW FIRSTNAME ");
                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student(firstname.getText().toString(),new_firstname.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.listBtn:
                controller.list_all_students(textView);
                break;
        }
    }
}
