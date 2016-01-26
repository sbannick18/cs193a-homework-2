package com.example.stephen.todolist;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    ArrayList <String> list;
    ArrayAdapter <String> adapter;
    ListView the_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        the_list = (ListView) findViewById(R.id.the_list);
        the_list.setOnItemLongClickListener(this);
        the_list.setAdapter(adapter);

    }

    public void addItem(View view) {
        EditText inputText = (EditText) findViewById(R.id.input_text);
        String input= inputText.getText().toString();
        list.add(input);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "New item added!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long id) {
        ListView the_list = (ListView) findViewById(R.id.the_list);
        String toDelete= the_list.getItemAtPosition(index).toString();
        list.remove(toDelete);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "\"" + toDelete + "\""+ " deleted" , Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list", list);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        list = savedInstanceState.getStringArrayList("list");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        the_list = (ListView) findViewById(R.id.the_list);
        the_list.setOnItemLongClickListener(this);
        the_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
