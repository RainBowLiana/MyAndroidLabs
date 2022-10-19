package algonquin.cst2335.peze0001;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

        Button loginButton;
        EditText emailEditText, passwordEditText;
        String emailAddress;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //  loads the XML file on Screen
            setContentView(R.layout.activity_main);

            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);


            loginButton = findViewById(R.id.loginButton);
            emailEditText = findViewById(R.id.emailEditText);
            passwordEditText = findViewById(R.id.passwordEditText);
            emailAddress = prefs.getString("LoginName", "");
            emailEditText.setText(emailAddress);



            Log.w("MainActivity", "In onCreate() - Loading Widgets");
            loginButton.setOnClickListener(clk -> {
                Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
                nextPage.putExtra("emailAddress", emailEditText.getText().toString());

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("password", passwordEditText.getText().toString());
                editor.putString("LoginName", emailEditText.getText().toString());
                editor.apply();
                startActivity(nextPage);
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            Log.e("MainActivity", "In onStart() - The application is now visible on screen");
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.e("MainActivity", "In onStop() - The application is No longer visible");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.e("MainActivity", "In onDestroy() - Any memory used by the application is freed");
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.e("MainActivity", "In onPause() - No longer responds to user input");
        }

        @Override
        protected void onResume() {
            super.onResume();
            Log.e("MainActivity", "In Resume() - Responding to user input");
        }


    }