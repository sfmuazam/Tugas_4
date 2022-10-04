package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    private Button btnOk;
    private TextInputLayout name, email, webpage, about_me, password, confirm_password;
    private ImageView foto_profil;
    private CircleImageView profil, ubah_foto_profil;

    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private Bitmap bitmap;
    private Uri imgUri = null;
    private boolean change_img = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnOk = findViewById(R.id.button_ok);
        name = findViewById(R.id.layout_fullname);
        email = findViewById(R.id.layout_email);
        webpage = findViewById(R.id.layout_homepage);
        about_me = findViewById(R.id.layout_about);
        password = findViewById(R.id.layout_password);
        confirm_password = findViewById(R.id.layout_confirm_password);
        foto_profil = findViewById(R.id.imageView);
        profil = findViewById(R.id.image_profile);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                String value_password = String.valueOf(password.getEditText().getText());
                String value_confirm_password = String.valueOf(confirm_password.getEditText().getText());
                String value_name = String.valueOf(name.getEditText().getText());
                String value_email = String.valueOf(email.getEditText().getText());
                String value_webpage = String.valueOf(webpage.getEditText().getText());
                String value_about_me = String.valueOf(about_me.getEditText().getText());

                if(value_name.isEmpty()){
                    name.setError("Fullname harus diisi");
                }else if(value_email.isEmpty()) {
                    email.setError("Email harus diisi");
                }else if(value_password.isEmpty()) {
                    password.setError("Password harus diisi");
                }else if(value_confirm_password.isEmpty()) {
                    confirm_password.setError("Confirm password harus diisi");
                }else if(value_webpage.isEmpty()) {
                    webpage.setError("Homepage harus diisi");
                }else if(value_about_me.isEmpty()) {
                    about_me.setError("About me harus diisi");
                }else if(!value_password.equals(value_confirm_password)) {
                    Toast.makeText(RegisterActivity.this, "     Confrim password salah     ", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("name", value_name);
                    intent.putExtra("email", value_email);
                    intent.putExtra("webpage", value_webpage);
                    intent.putExtra("about_me", value_about_me);
                    intent.putExtra("foto_profil", imgUri.toString());
                    startActivity(intent);
                }
            }
        });

        foto_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "     Batal mengambil gambar     ", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (requestCode == GALLERY_REQUEST_CODE){
                if (data != null){
                    try {
                        change_img = true;
                        imgUri = data.getData();
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imgUri);
                        profil.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "     Gagal mengambil gambar     ", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }
    }
}
