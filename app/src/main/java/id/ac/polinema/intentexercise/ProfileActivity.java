package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvName, tvEmail, tvWebpage, tvAbout_me;
    private CircleImageView foto_profil;
    private Button btnVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.label_fullname);
        tvEmail = findViewById(R.id.label_email);
        tvWebpage = findViewById(R.id.label_homepage);
        tvAbout_me = findViewById(R.id.label_about);
        foto_profil = findViewById(R.id.image_profile);
        btnVisit = findViewById(R.id.button_homepage);

        String name = getIntent().getExtras().getString("name");
        tvName.setText(name);
        String email = getIntent().getExtras().getString("email");
        tvEmail.setText(email);
        String webpage = getIntent().getExtras().getString("webpage");
        tvWebpage.setText(webpage);
        String about_me = getIntent().getExtras().getString("about_me");
        tvAbout_me.setText(about_me);
        String uri = getIntent().getExtras().getString("foto_profil");
        foto_profil.setImageURI(Uri.parse(uri));

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(webpage);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
    }
}
