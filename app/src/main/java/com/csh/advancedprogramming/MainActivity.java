package com.csh.advancedprogramming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.csh.inject.InjectManager;
import com.csh.inject.annotation.ContentView;
import com.csh.inject.annotation.InjectView;
import com.csh.inject.annotation.OnClick;
import com.csh.inject.annotation.onLongClick;

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
        mTextView.setText(NDKTest.getStringFromC());
    }

    @OnClick({R.id.button, R.id.tv})
    public void click(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_LONG).show();
    }

    @onLongClick(R.id.tv)
    public boolean longClick(View view) {
        Toast.makeText(this, "longClick", Toast.LENGTH_LONG).show();
        return false;
    }
}
