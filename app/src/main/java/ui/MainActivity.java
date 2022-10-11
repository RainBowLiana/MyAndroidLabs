package ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.peze0001.R;
import algonquin.cst2335.peze0001.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    private String selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());


       //  variableBinding.textview.setText(model.editString);
         variableBinding.mybutton.setOnClickListener(click ->
         {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            variableBinding.textview.setText("Your edit text has : " +model.editString);
         });
       variableBinding.mybutton.setOnClickListener(click ->
       {
           model.editString.postValue(variableBinding.myedittext.getText().toString());
        });
        model.editString.observe(this, new Observer<String>() {
          @Override
          public void onChanged(String s) {

            }
       });
       model.editString.observe( this, s -> {

               variableBinding.textview.setText("Your edit text has "+ s);


     });
    model.variable.observe(this, selected -> {
                variableBinding.checkbox.setChecked(selected);
                variableBinding.RadioButton.setChecked(selected);
                variableBinding.Switch.setChecked(selected);
                CharSequence text1 = "The value is now: " + selected;
                int duration1 = Toast.LENGTH_SHORT;
                Context context = getApplicationContext();
                Toast toast1 = Toast.makeText(context, text1, duration1);
                toast1.show();

            });

        variableBinding.checkbox.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.variable.postValue(isChecked);
        } );
        variableBinding.Switch.setOnCheckedChangeListener( (sw, isChecked) -> {
            model.variable.postValue(isChecked);
       } );
        variableBinding.RadioButton.setOnCheckedChangeListener( (rdb, isChecked) -> {
            model.variable.postValue(isChecked);
        } );








        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        ib.setOnClickListener(v -> {

            CharSequence text1 = "The width :" + v.getWidth() + "the height :" + v.getHeight();
            int duration1 = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            Toast toast1 = Toast.makeText(context, text1, duration1);
            toast1.show();
        });



    }

}
