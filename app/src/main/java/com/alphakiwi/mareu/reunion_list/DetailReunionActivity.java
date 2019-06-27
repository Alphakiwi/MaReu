package com.alphakiwi.mareu.reunion_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.R;

import com.alphakiwi.mareu.di.DI;
import com.alphakiwi.mareu.service.ReunionApiService;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.alphakiwi.mareu.reunion_list.MyReunionRecyclerViewAdapter.REUNION;


public class DetailReunionActivity extends AppCompatActivity {

    private TextView text = null;
    private TextView salle = null;
    private TextView presentation = null;
    private TextView dateEtHeure = null;
    private TextView adresseMailTextView = null;
    private ImageView imageImageView = null;
    private Button back;
    private String adresseMail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reunion);


        text = (TextView) findViewById(R.id.text);
        salle = (TextView) findViewById(R.id.reunion);
        presentation = (TextView) findViewById(R.id.description);
        dateEtHeure = (TextView) findViewById(R.id.dateEtHeure);
        adresseMailTextView = (TextView) findViewById(R.id.adresseMail);
        imageImageView = (ImageView) findViewById(R.id.imageAvatar);

        Intent i = getIntent();

        Reunion reunion = (Reunion) i.getSerializableExtra(REUNION);

        for(int j = 0 ; j <  reunion.getAdressesMail().size() ; j++) {

            adresseMail = adresseMail + reunion.getAdressesMail().get(j) + ", ";

        }


        configureBack();


        Glide.with(this)
                .load(reunion.getImageUrl())
                .centerCrop()
                .into(imageImageView);



        text.setText(reunion.getSubject() );
        salle.setText("Salle : " + reunion.getLieu() );
        presentation.setText("Nous allons aborder plus en détail les diffèrentes étapes du projet sur "
                + reunion.getSubject());
        dateEtHeure.setText("le " + reunion.getDateJour() + "/"
                + reunion.getDateMois() + "/" + reunion.getDateAnnee()+ " à " + reunion.getHour());
        adresseMailTextView.setText(adresseMail);
        presentation.setMovementMethod(new ScrollingMovementMethod());


    }




    private void configureBack() {
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

}
