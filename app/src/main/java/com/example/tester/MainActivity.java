package com.example.tester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseClass classDatabase;
    EditText editUser,editPass;
    Button btnSave,btnEdit,btnHapus,b_Lihat,b_login;
    DatabaseOnline dbOnline;
    DatabaseSimpan dbSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classDatabase = new DatabaseClass(this);

        editUser = (EditText)findViewById(R.id.editUser);
        editPass = (EditText)findViewById(R.id.editPass);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        b_Lihat = (Button)findViewById(R.id.btnLihat);
        btnHapus = (Button)findViewById(R.id.btnHapus);
        b_login = (Button)findViewById(R.id.btnLogin);

        dbOnline = new DatabaseOnline(this);
        dbSimpan = new DatabaseSimpan(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean simpanData = classDatabase.insertData(
//                        editUser.getText().toString(),
//                        editPass.getText().toString()
//                );
//                if (simpanData = true)
//                    Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(MainActivity.this, "Data Gagal Disimpan", Toast.LENGTH_LONG).show();
                String user = editUser.getText().toString();
                String pass = editPass.getText().toString();
                dbSimpan.execute(user,pass);
//                finish();
            }
        });

        b_Lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewDataActivity.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean editData = classDatabase.editData(
                        editUser.getText().toString(),
                        editPass.getText().toString()
                );
                if (editData = true)
                    Toast.makeText(MainActivity.this, "Data Berhasil Diubah", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Gagal Diubah", Toast.LENGTH_LONG).show();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Integer hapus = classDatabase.hapusData(editUser.getText().toString());
                if (hapus > 0)
                    Toast.makeText(MainActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Gagal Dihapus", Toast.LENGTH_LONG).show();
            }
        });

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editUser.getText().toString();
                String pass = editPass.getText().toString();
                dbOnline.execute(user,pass);

            }
        });

    }
}
