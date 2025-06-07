package com.example.finalassraytos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {
    Button updateButton;
    EditText updateName, updateSNumber, updateYear;
    String name, snumber, year;
    String key;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateButton);
        updateName = findViewById(R.id.updateName);
        updateYear = findViewById(R.id.updateYear);
        updateSNumber = findViewById(R.id.updateSNumber);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            updateName.setText(bundle.getString("Name"));
            updateSNumber.setText(bundle.getString("Student number"));
            updateYear.setText(bundle.getString("Year"));
            key = bundle.getString("Key");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Students").child(key);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void updateData(){
        name = updateName.getText().toString().trim();
        snumber = updateSNumber.getText().toString().trim();
        year = updateYear.getText().toString();

        if (snumber.isEmpty() || name.isEmpty() || year.isEmpty()){
            Toast.makeText(UpdateActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        DataClass dataClass = new DataClass(name, snumber, year);
        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
