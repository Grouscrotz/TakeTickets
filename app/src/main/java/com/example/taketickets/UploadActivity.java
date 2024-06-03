package com.example.taketickets;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.MySupportClasses.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class UploadActivity extends AppCompatActivity {
    private static final String TAG = "UploadActivity";

    ImageView uploadImage;
    Button saveButton,next_activity_button;
    EditText uploadMovieName, uploadMovieGenre, uploadMovieAgeLimit,uploadMoviePlot,uploadMovieKinopoisk,uploadMovieYoutube;
    String imageURL;
    Uri uri;

    EditText priceSession,timeSession,movieSession;
    Button buttonAddSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        saveButton = findViewById(R.id.upload_button);
        uploadImage = findViewById(R.id.imageUpload);
        uploadMovieName = findViewById(R.id.edTUpload_movieName);
        uploadMovieGenre = findViewById(R.id.edTUpload_movieGenre);
        uploadMovieAgeLimit = findViewById(R.id.edTUpload_ageLimit);

        priceSession = findViewById(R.id.editTextTextSessionPrice);
        timeSession = findViewById(R.id.editTextTextSessionTime);
        buttonAddSession = findViewById(R.id.btnSessionAdd);
        movieSession = findViewById(R.id.editTextMovieSession);

        // Новые поля
        uploadMoviePlot = findViewById(R.id.edTUpload_plot);
        uploadMovieKinopoisk = findViewById(R.id.edTUpload_kinopoisk);
        uploadMovieYoutube = findViewById(R.id.edTUpload_youtube);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                            Log.d(TAG, "Image selected: " + uri.toString());
                        } else {
                            Toast.makeText(UploadActivity.this, "No image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    saveData();
                } else {
                    Toast.makeText(UploadActivity.this, "Загрузите постер фильма!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadSession();
            }
        });



    }

    public void saveData() {
        if (uri == null) {
            Toast.makeText(UploadActivity.this, "No image selected!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Выводим uri для отладки
        Toast.makeText(this, "Selected image URI: " + uri.toString(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "Selected image URI: " + uri.toString());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("Android Images").child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d(TAG, "Image uploaded successfully");
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                uriTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri urlImage = task.getResult();
                            imageURL = urlImage.toString();
                            Log.d(TAG, "Download URL: " + imageURL);
                            uploadData();
                        } else {
                            Log.e(TAG, "Failed to get download URL", task.getException());
                            Toast.makeText(UploadActivity.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Image upload failed", e);
                Toast.makeText(UploadActivity.this, "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadData() {
        String title = uploadMovieName.getText().toString();
        String genre = uploadMovieGenre.getText().toString();
        String ageLimit = uploadMovieAgeLimit.getText().toString();

        // Новые поля
        String plot = uploadMoviePlot.getText().toString();
        String kinoposk = uploadMovieKinopoisk.getText().toString();
        String youtube = uploadMovieYoutube.getText().toString();

         Movie movieClass = new Movie(title, genre, ageLimit, imageURL,plot,kinoposk,youtube);

        FirebaseDatabase.getInstance().getReference("Movies").child(title)
                .setValue(movieClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Data saved successfully");
                            Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.e(TAG, "Data upload failed", task.getException());
                            Toast.makeText(UploadActivity.this, "Data upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Data upload failed", e);
                        Toast.makeText(UploadActivity.this, "Data upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                 DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Trailers").child(title);
                 HashMap<String,String> trailerInfo = new HashMap<>();
                 trailerInfo.put("uri", youtube);
                 databaseReference.setValue(trailerInfo);



    }

    // Метод для добавления сессий к фильму
    public void uploadSession() {
        String price = priceSession.getText().toString();
        String time = timeSession.getText().toString();
        String nameMovie = movieSession.getText().toString();

        Session session = new Session(time,price);

        FirebaseDatabase.getInstance().getReference("Movies").child(nameMovie).child("sessions").child(time)
                .setValue(session).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Data saved successfully");
                            Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.e(TAG, "Data upload failed", task.getException());
                            Toast.makeText(UploadActivity.this, "Data upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Data upload failed", e);
                        Toast.makeText(UploadActivity.this, "Data upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
   }


}
