package com.example.proj6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/** @author Created by karin on 27/10/2023.
 * @version 0.0
 * @since 27/10/2023
 *On this Activity, there is Text view which presents the counter, an Edit Text to get the name from the user, 3 buttons - increases the counter, resets the counter and saves the data, and a context menu which contains 2 options, stay on this screen or go to the credits activity.
 */

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    EditText et;
    TextView tv;
    Button btn1, btn2, btn3;
    String name;
    int i = 0; // the click's counter
    Intent intent;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        settings = getSharedPreferences("data_saver", MODE_PRIVATE);
        name = settings.getString("name", "");
        i = settings.getInt("counter", 0);
        tv.setText(String.valueOf((i)));
        et.setText(name);
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
        name = et.getText().toString();
        if(str.equals("Main Activity")){
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("name", name);
            editor.putInt("counter", i);
            editor.commit();
        }
        else if(str.equals("Credits Activity")){
            intent = new Intent(this, Credits_Activity.class);
            intent.putExtra("name", name);
            intent.putExtra("counter", i);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * add method
     * <p> Reacting the first button
     * </p>
     *
     * @param view the view that triggered the method
     * The method is increasing the counter
     */
    public void add(View view){
        i++;
        tv.setText(String.valueOf((i)));
    }
    /**
     * zero method
     * <p> Reacting the second button
     * </p>
     *
     * @param view the view that triggered the method
     * The method is resets the counter
     */
    public void zero(View view){
        i=0;
        tv.setText(String.valueOf((i)));
    }
    /**
     * out_save method
     * <p> Reacting the third button
     * </p>
     *
     * @param view the view that triggered the method
     * The method is saving the data and close the application
     */
    public void out_save(View view){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", name);
        editor.putInt("counter", i);
        editor.commit();
        finish();
    }
    /**
     * onActivityForResult method
     * <p> Reacting the third button
     * </p>
     *
     * the method gets the number of the intent, an int number which will be 1 if it is ok and 0 if not, and the intent.
     * The method is saving the data and close the application
     */
    protected void onActivityForResult(int source, int good, @Nullable Intent intent){
        if(intent!=null){
            name = intent.getStringExtra("name");
            i = intent.getIntExtra("counter", 0);
        }
    }
}