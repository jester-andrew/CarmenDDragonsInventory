package com.dragons.carmenddragonsinventory;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

/**
 * This class contains all the variables and methods necessary to add an item into the inventory
 */
public class Add_Item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private String location;
    private static String TAG = "Add_Item Activity";

    //inventory variables
    TextView _image;
    TextView itemname;
    TextView item_Color;
    TextView holding_Item;
    TextView _ctp;
    TextView listingprice;
    TextView _stock;
    private String url;

    //upload image variables
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button buttonChoseImage;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Add_Item"); // changes header title
        setContentView(R.layout.activity_add__item);


        Spinner dropdown = findViewById(R.id.spinner1);

        //list of items for dropdown
        String[] items = new String[]{"Fantasy-Creature", "Sea-Creature", "Woodland-Creature"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        //variables grabbing item values in view
        itemname = findViewById(R.id.item_Name);
        item_Color = findViewById(R.id.item_color);
        holding_Item =findViewById(R.id.Item_Item);
        _ctp = findViewById(R.id.cost_to_Produce);
        listingprice = findViewById(R.id.list_Price);
        _stock = findViewById(R.id.inStock);

        //variables grabbing image values in view
        buttonChoseImage = findViewById(R.id.button_chose_image);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);

        //firebase references
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        //event listener that opens the image choosing view
        buttonChoseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }

    //This method opens the file chooser and anly allows images to be found
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*"); //only images
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    //after a file is chosen the file is uploaded to firebase store
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();

            imageView.setImageURI(imageUri);

            uploadFile();
        }


    }

    /**
     * This method adds the item to the inventory by creating an inventory creature and passing it
     * to the insertInventory Creature class and using its insert method
     * @param view
     */
    public void addItem(View view){
        EditText itemName = findViewById(R.id.item_Name);
        EditText itemColor = findViewById(R.id.item_color);
        EditText holdingItem = findViewById(R.id.Item_Item);
        EditText ctp = findViewById(R.id.cost_to_Produce);
        String ctp2 = ctp.getText().toString();
        EditText listPrice = findViewById(R.id.list_Price);
        String listPrice2 = listPrice.getText().toString();
        EditText stock = findViewById(R.id.Stock);
        String stock2 = stock.getText().toString();


        try {

            InventoryCreature ic = new InventoryCreature(itemName.getText().toString(), itemColor.getText().toString(),
                    holdingItem.getText().toString(), Double.parseDouble(ctp2), Double.parseDouble(listPrice2), Integer.parseInt(stock2), url );

            insertInventoryCreature iic = new insertInventoryCreature();
            iic.insertInventoryCreature(ic, location);

            Context context = getApplicationContext();
            CharSequence text = "Item Added Successfully";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }catch (Exception e){
            Log.e(TAG, "Failed to insert new item in: Add_Item");

            Context context = getApplicationContext();
            CharSequence text = "Item NOT Added";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    finish();
    }

    //changes the item location based on what was selected in the dropdown
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                location = "fantasy-creature";
                break;
            case 1:
                location = "sea-creature";
                break;
            case 2:
                location = "woodland-creature";
                break;
        }


        Context context = getApplicationContext();
        CharSequence text = "Location: " + location;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    //If no image is selected this function posts a toast to the screen
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Context context = getApplicationContext();
        CharSequence text = "Nothing Selected";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    //this method grabs the file extenstion
    private String getFileExtenstion(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    //this method uploads the image to fire store
    private void uploadFile(){
        if(imageUri != null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtenstion(imageUri));
            uploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Toast.makeText(Add_Item.this, "Upload Successful!", Toast.LENGTH_SHORT).show();
                                    url = uri.toString();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Add_Item.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = 100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount();
                            progressBar.setProgress((int) progress);
                        }
                    });
        }else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}


