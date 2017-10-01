package io.techministry.android.fellowshipmissionchurch.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Random;

import io.techministry.android.fellowshipmissionchurch.R;
import io.techministry.android.fellowshipmissionchurch.utils.Utilities;

public class PostActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText content;
    EditText title;
    //@BindView(R.id.toolbar) Toolbar toolbar;
    boolean isFileSelected = false;
    Context context;
    final int PICK_PHOTO = 100;
    //private StorageReference storageRef;
    Uri selectedFileURI;
    String content_type = "text";
    ProgressDialog progressDialog;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        context = this;

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        content = (EditText)findViewById(R.id.content);
        title = (EditText)findViewById(R.id.title);


        mDatabase = FirebaseDatabase.getInstance();
        setupToolBar();

       // FirebaseStorage storage = FirebaseStorage.getInstance();
    }

    private void setupToolBar() {
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btn_post_clicked(View view) {
        if(!isFileSelected && (content.getText().toString().isEmpty())){
            Utilities.showAlert(context,"Please enter content to post.",false);
        }else{

            String newKey =  mDatabase.getReference("announcements").push().getKey();

            String contentText = content.getText().toString();

            HashMap<String,Object> map = new HashMap<>();
            map.put("content",contentText);
            map.put("content_type",content_type);
            map.put(".priority",0 - Utilities.unixTimeStamp());
            map.put("created_at",System.currentTimeMillis() / 1000);
            map.put("title",title.getText().toString());
            map.put("description",contentText);
            map.put("likes",generateRandomLikeValue());


            mDatabase.getReference("announcements").child(newKey).setValue(map);

            if (isFileSelected) {
                progressDialog = ProgressDialog.show(context,null,"Please wait...",false,false);
                uploadMediaContent(newKey);
            }else{
                Utilities.showToast(context,"Upload was successful!");
                finish();
            }
        }
    }

    private int generateRandomLikeValue(){
        int maximum  = 12;
        int minimum = 4;
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum =  rn.nextInt(range) + minimum;

        return randomNum;
    }

    private void uploadMediaContent(final String newKey) {
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        if(selectedFileURI != null){
            StorageReference photoRef = mStorageRef.child("announcements/photos").child(selectedFileURI.getLastPathSegment());
            photoRef.putFile(selectedFileURI).addOnCompleteListener(this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        String url = task.getResult().getDownloadUrl().toString();
                        if(content_type == "image"){
                            mDatabase.getReference("announcements").child(newKey).child("photo").setValue(url);
                        }else{
                            mDatabase.getReference("announcements").child(newKey).child("video_url").setValue(url);
                        }

                        cancelDialog();
                        Utilities.showToast(context,"Upload was successful!!!");
                        finish();


                    }else{
                        Utilities.showToast(context,"Failed!");
                        cancelDialog();
                    }
                }
            });
        }else{
            cancelDialog();
            Utilities.showToast(context,"Failed!");
        }

    }

    public void btnSelectAttachment(View view) {
        getPermissionToReadStorage(PICK_PHOTO,android.Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void getPermissionToReadStorage(int action_type, String permission_to_request) {
        if (ContextCompat.checkSelfPermission(this, permission_to_request) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{permission_to_request},action_type);
            Utilities.showToast(context,"Permission not granted");
        } else {
            //Utilities.toast(context,"Permission granted");

            switch (action_type){
                case PICK_PHOTO:
                    showPhotoSelectOption();
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Read Storage permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Read Storage permission denied", Toast.LENGTH_SHORT).show();
        }

        switch (requestCode){
            case PICK_PHOTO:
                showPhotoSelectOption();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showPhotoSelectOption() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String[] items = new String[]{
                "Select photo from gallery",
                "Select video to upload"
        };
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        content_type = "image";
                    selectPhoto();
                    break;
                    case 1:
                        takePhoto();
                        break;
                    case 2:
                        content_type = "video";
                        selectVideo();
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK){return;}

        if(requestCode == 0){

            if(data.getData() != null){
                isFileSelected = true;
                selectedFileURI = data.getData();
                //String path = ImageFilePath.getPath(context,data.getData());
            }
        }
    }

    private void selectPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        try{
            intent.putExtra("return-data",true);
            startActivityForResult(intent,0);
        }catch (Exception d){

        }
    }

    private void takePhoto(){

    }

    private void selectVideo(){
        Intent intent = new Intent();
        intent.setType("file/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        try{
            intent.putExtra("return-data",true);
            startActivityForResult(intent,0);
        }catch (Exception d){

        }
    }



    private void cancelDialog(){
        if(progressDialog != null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
}
