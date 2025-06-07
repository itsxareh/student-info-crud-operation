package com.example.finalassraytos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {

    TextView detailName, detailSNumber, detailYear;
    FloatingActionButton deleteButton, editButton;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);


        detailName = findViewById(R.id.detailName);
        detailSNumber = findViewById(R.id.detailSNumber);
        detailYear = findViewById(R.id.detailYear);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailName.setText(bundle.getString("Name"));
            detailSNumber.setText(bundle.getString("Student number"));
            detailYear.setText(bundle.getString("Year"));
            key = bundle.getString("Key");
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
                reference.child(key).removeValue();
                Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("Name", detailName.getText().toString())
                        .putExtra("Student number", detailSNumber.getText().toString())
                        .putExtra("Year", detailYear.getText().toString())
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}