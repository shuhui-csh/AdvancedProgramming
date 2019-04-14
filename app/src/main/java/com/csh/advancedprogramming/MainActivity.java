package com.csh.advancedprogramming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.csh.inject.annotation.ContentView;
import com.csh.inject.InjectManager;
import com.csh.inject.annotation.InjectView;
import com.csh.inject.annotation.OnClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv)
    public TextView mTextView;
    @InjectView(R.id.button)
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);
    }

    @OnClick(R.id.button)
    public void show() {
        Toast.makeText(this, "show", Toast.LENGTH_LONG).show();
    }
}
