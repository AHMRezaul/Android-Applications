package com.example.medkit;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.medkit.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Prescription extends AppCompatActivity implements View.OnClickListener {
    private Button chooseButton,saveButton,displaybutton,nextbutton;
    private ImageView imageView;
    private EditText imagenameditText;
    private ProgressBar progressBar;
    private Uri imageUri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageReference mStorage;
    StorageTask uploadtask;
    String mCurrentPhotoPath;
    File photoFile = null;
    int flag=0,flag1=0;


    private static final int IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);


        chooseButton=findViewById(R.id.chooseimagebutton);
        saveButton=findViewById(R.id.saveimagebuttonid);
        displaybutton=findViewById(R.id.displayimage);
        nextbutton=findViewById(R.id.nextimagebuttonid);

        progressBar=findViewById(R.id.progressbarid);
        imagenameditText=findViewById(R.id.imagenameedittextid);
        imageView=findViewById(R.id.imageviewid);
        databaseReference= FirebaseDatabase.getInstance().getReference("Upload");
        storageReference= FirebaseStorage.getInstance().getReference("Upload");


        saveButton.setOnClickListener(this);
        chooseButton.setOnClickListener(this);
        nextbutton.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {

        switch(v.getId())
        {

            case  R.id.chooseimagebutton:
                openfilechooser();

                break;
            case R.id.nextimagebuttonid:
                if(flag==1){
                    Intent intent1 = new Intent(Prescription.this,Purchase.class);
                    startActivity(intent1);}
                else
                {
                    imagenameditText.setError("Please Add an Image");
                }
                break;

            case R.id.saveimagebuttonid:
                if(uploadtask!=null&&uploadtask.isInProgress())
                {
                    Toast.makeText(getApplicationContext(),"Uploading is Processing",Toast.LENGTH_LONG).show();
                }
                else
                {
                    savedata();


                }


                break;
            case R.id.displayimage:
                Intent intent =new Intent(Prescription.this,ImageActivity.class);
                startActivity(intent);

                break;
        }

    }
    private void openfilechooser() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            imageUri=data.getData();
            //Picasso.with(this).load(imageUri).into(imageView);
            try {

                InputStream inputStream = getContentResolver().openInputStream(imageUri);

                Bitmap image = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(image);

                flag1=1;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }

    }

    public String getFileExtension(Uri imageUri)
    {
        ContentResolver contentResolver =getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }
    private void savedata() {

        final String imageName =imagenameditText.getText().toString().trim();
        if(imageName.isEmpty())
        {
            imagenameditText.setError("Image Name Empty");
            imagenameditText.requestFocus();
            return;
        }
        if(flag1==0)
        {
            imagenameditText.setError("Image Empty");
            imagenameditText.requestFocus();
            return;
        }

        StorageReference ref=storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(getApplicationContext(),"Image Uploaded Successfully",Toast.LENGTH_LONG).show();
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while ((!((Task) uriTask).isSuccessful()));
                        Uri downloadUri=uriTask.getResult();
                        Upload upload = new Upload(imageName,downloadUri.toString());
                        String uploadid=databaseReference.push().getKey();
                        databaseReference.child(uploadid).setValue(upload);
                        flag=1;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getApplicationContext(),"Image Not Uploaded Successfully",Toast.LENGTH_LONG).show();
                    }
                });




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //User clicked home, do whatever you want
                finish();
                startActivity(new Intent(this, NavigationView.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, NavigationView.class);
        startActivity(intent);
    }
}
