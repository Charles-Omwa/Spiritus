package com.example.spiritus;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestActivity extends AppCompatActivity {

    // Creating EditText.
    EditText FirstName, LastName, Email ;

    // Creating button;
    Button InsertButton;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String FirstNameHolder, LastNameHolder, EmailHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "https://christian-project.herokuapp.com/api/prayer/create/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Prayer Request");
        setContentView(R.layout.prayer);

        // Assigning ID's to EditText.
        FirstName = (EditText) findViewById(R.id.editTextFirstName);
        LastName = (EditText) findViewById(R.id.editTextLastName);
        Email = (EditText) findViewById(R.id.editTextEmail);

        // Assigning ID's to Button.
        InsertButton = (Button) findViewById(R.id.ButtonInsert);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(RequestActivity.this);

        progressDialog = new ProgressDialog(RequestActivity.this);

        // Adding click listener to button.
        InsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait as your prayer request is being sent");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText(RequestActivity.this,"Prayer Request sent successfully", Toast.LENGTH_LONG).show();

                                FirstName.setText(null);
                                LastName.setText(null);
                                Email.setText(null);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(RequestActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("title", FirstNameHolder);
                        params.put("name", LastNameHolder);
                        params.put("description", EmailHolder);

                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(RequestActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }
        });

    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText(){

        FirstNameHolder = FirstName.getText().toString().trim();
        LastNameHolder = LastName.getText().toString().trim();
        EmailHolder = Email.getText().toString().trim();

    }

}
