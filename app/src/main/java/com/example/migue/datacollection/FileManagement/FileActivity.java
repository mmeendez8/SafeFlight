package com.example.migue.datacollection.FileManagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.migue.datacollection.R;
import com.example.migue.datacollection.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {
    private String TAG = "FileActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView fileView = (TextView) findViewById(R.id.file_view);

        Intent intent = getIntent();
        String file = intent.getStringExtra("filename");

        plotFile(fileView,file);
    }

    private void plotFile (TextView fileView, String filename) {
        File f = Utils.getDocumentsDirectory(this, filename);
        FileInputStream iStr = null;
        try {
            iStr = new FileInputStream (f);
        } catch (FileNotFoundException e) {
            Log.e(TAG,"File does not exist");
            Toast.makeText(getApplicationContext(), "File does not exist !", Toast.LENGTH_LONG).show();
            finish();
        }
        InputStreamReader isr = new InputStreamReader(iStr);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            fileView.setText(sb);
            bufferedReader.close();
            iStr.close();
        } catch (IOException e) {
            Log.e(TAG,"Impossible operation with this file");
        }

    }
}
