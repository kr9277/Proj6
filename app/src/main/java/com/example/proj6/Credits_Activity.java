package com.example.proj6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Credits_Activity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    TextView tv1, tv2;
    String name;
    int counter;
    Intent gi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        gi = getIntent();
        name = gi.getStringExtra("name");
        counter = gi.getIntExtra("counter", 0);
    }
    /**
     * onCreateOptionsMenu method
     * <p> Creating the options menu
     * </p>
     *
     * @param menu the Menu object to pass to the inflater
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        return true;
    }
    /**
     * onOptionsItemSelected method
     * <p> Reacting the options menu
     * </p>
     *
     * @param item the MenuItem object that triggered by the listener
     * @return super.onOptionsItemSelected(item)
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        String str = item.getTitle().toString();
        if(str.equals("Main Activity")){
            gi.putExtra("name", name);
            gi.putExtra("counter", counter);
            setResult(RESULT_OK, gi);
            finish();
        }
        else if(str.equals("Credits Activity")){
            Intent si = new Intent(this, Credits_Activity.class);
            si.putExtra("name", name);
            si.putExtra("counter", counter);
            startActivity(si);

        }
        return super.onOptionsItemSelected(item);
    }
}