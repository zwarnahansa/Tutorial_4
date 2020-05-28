package com.example.tutorial04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtName, pswd;
    Button btnSingUp, btnAdd, btnUpdate, btnDelete, selectAll;
    DBHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHandler(this);
        txtName= findViewById(R.id.txtName);
        pswd= findViewById(R.id.pswd);
        btnSingUp= findViewById(R.id.btnSignUp);
        btnAdd= findViewById(R.id.btnAdd);
        btnUpdate= findViewById(R.id.btnUpdate);
        btnDelete= findViewById(R.id.btnDelete);
        selectAll= findViewById(R.id.selectAll);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb.addInfo(txtName.getText().toString(), pswd.getText().toString())){
                    Toast.makeText(MainActivity.this,"Inserted successfully",Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(MainActivity.this,"Inserted fail",Toast.LENGTH_LONG).show();

            }
        });


        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor newRowId = myDb.readAllInfo();
                if(newRowId!= null) {
                    while (newRowId.moveToNext()) {
                        Log.d("User record", "User" + newRowId.getString(2) + newRowId.getString(2));
                    }

            }else
            showMessage();

        }

        });


    }

    private void showMessage() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage("No user available");
        builder.show();
    }

}
