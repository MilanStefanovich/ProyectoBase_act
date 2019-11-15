package com.example.proyectobase_act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private  ProgressBar pb1;
    private Button btn, btn_ex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        btn = (Button) findViewById(R.id.btn);
        btn_ex = (Button) findViewById(R.id.btn_ex);

        pb1.setVisibility(View.INVISIBLE);

        btn_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute(et1.getText().toString());
            }
        });
    }

    class Task extends AsyncTask<String, Void, String>
    {

        // Configura la tarea inicial.
        @Override
        protected void onPreExecute() {
            pb1.setVisibility(View.VISIBLE);
            btn.setEnabled(false);
        }

        // Aquí va mi proceso o hilo de mayor peso.
        @Override
        protected String doInBackground(String... strings) {
            try
            {
                Thread.sleep(5000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {

            btn.setEnabled(true);
            pb1.setVisibility(View.INVISIBLE);
            Toast.makeText(getBaseContext(), "Acceso Concedido",
                    Toast.LENGTH_LONG).show();

            String user = et1.getText().toString();
            String pass = et2.getText().toString();

            Intent i = new Intent(getBaseContext(), Home_act.class);
            i.putExtra("usuario", user);
            i.putExtra("contrasena", pass);
            startActivity(i);

        }
    }
}
