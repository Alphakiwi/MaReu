package com.alphakiwi.mareu.reunion_list;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.R;
import com.alphakiwi.mareu.events.AddReunionEvent;
import com.alphakiwi.mareu.events.DeleteReunionEvent;
import com.alphakiwi.mareu.events.TriReunionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaReuActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListReunionPagerAdapter mPagerAdapter;
    FloatingActionButton fab;
    int id ;
    String subject;
    String image;
    String reunion;
    String heure;
    String dateAnnee;
    String dateMois;
    String dateJour;
    List<String> adressesMail;
    EditText dateText = null ;
    EditText hourText = null ;
    private Spinner dialogSpinner = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma_reu);
        ButterKnife.bind(this);

        mPagerAdapter = new ListReunionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);


        id = 4;
        configureFab();

    }


    private void configureFab() {

        fab = findViewById(R.id.fab);


        fab.setOnClickListener(view -> {

            subject = "Bowser";
            id += 1;
            reunion = "Réunion A";
            image = "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG";
            heure = "14h00";
            dateAnnee = "2019";
            dateMois = "01";
            dateJour = "01";
            adressesMail = Arrays.asList("maxime@lamzone.com","alex@lamzone.com");

            new AlertDialog.Builder(view.getContext())
                    .setView(R.layout.dialog_choix_sujet)
                    .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            configureSujet(dialog);
                            configureLieu(dialog);
                            configureDateHeure(dialog);
                            configureAdresse(dialog);

                            EventBus.getDefault().post(new AddReunionEvent(new Reunion(id,subject,
                                    image,reunion,heure,dateAnnee,dateMois,dateJour, adressesMail)));

                        }
                    })
                    .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        });
    }

    private void configureSujet(DialogInterface dialog){

        EditText etSujet = (EditText) ((AlertDialog) dialog).findViewById(R.id.sujet);

        if (etSujet.getText().toString().length()>0) {
            subject = etSujet.getText().toString();
        }

    }

    private void configureLieu(DialogInterface dialog){
        dialogSpinner = ((AlertDialog) dialog).findViewById(R.id.project_spinner);

        if (dialogSpinner.getSelectedItem() instanceof String) {
            reunion = (String) dialogSpinner.getSelectedItem();
        }
        switch (reunion) {
            case "Réunion A":
                image = "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG";
                break;
            case "Réunion B":
                image = "http://www.materic.fr/images/realisations/salle-reunion-NSG-materic.jpg\"";
                break;
            case "Réunion C":
                image = "http://affaire.terrabotanica.fr/wp-content/uploads/2016/01/IMG_0868.jpg";
                break;
            case "Réunion D":
                image = "https://cdn.beewake.com/default/5a05/ce/thumb_5a05ce7372a2cd6a399b5cab_default_small.jpeg";
                break;
            case "Réunion E":
                image = "http://www.lelavoir-ateliersreunis.fr/wp-content/uploads/2017/09/saller%C3%A9u-1200x795.jpg";
                break;
            case "Réunion F":
                image = "https://blogbizmeeting.files.wordpress.com/2016/12/bagnoles.jpg";
                break;
            case "Réunion G":
                image = "http://www.sonovision.com/images/galleries/_2018_GALERIE/2018_03_02_PETITESALLEREUNION/Guide_SdR_3.2_table%20en%20V%20_Exposia%20reunion-stratifie-vert-anis-Final.jpg";
                break;
            case "Réunion H":
                image = "http://www.videlio.com/upload/images/videlio-iec/cas-client/Auditorium_Salles_Reunion/ERDF_Auditorium%20salle%20reunion/Auditorium-salle-reunion_ERDF_Salle%20du%20Conseil.jpg";
                break;
            case "Réunion I":
                image = "https://www.ahstatic.com/photos/0906_sm_00_p_2048x1536.jpg";
                break;
            case "Réunion J":
                image = "https://www.comet-meetings.com/wp-content/uploads/2019/02/comet-le-club-groupe-a-moyenne-res-87-300x300.jpg";
                break;
        }
    }

    private void configureDateHeure(DialogInterface dialog){
        dateText = (EditText) ((AlertDialog) dialog).findViewById(R.id.dateText);

        if (dateText.getText().toString().length()>0) {
            dateAnnee = dateText.getText().toString().substring(6,10);
            dateMois = dateText.getText().toString().substring(3,5);
            dateJour = dateText.getText().toString().substring(0,2);
        }

        hourText = (EditText) ((AlertDialog) dialog).findViewById(R.id.hourText);

        if (hourText.getText().toString().length()>0) {
            heure = hourText.getText().toString();
        }
    }

    private void configureAdresse(DialogInterface dialog){
        EditText textAdress = (EditText) ((AlertDialog) dialog).findViewById(R.id.textAdress);

        if (textAdress.getText().toString().length()>0) {
            List<String> strings = Arrays.asList(textAdress.getText().toString().split(","));
            List <String> Adresse  = new ArrayList<String>();
            for(int i = 0 ; i < strings.size() ; i++) {
                Adresse.add( strings.get(i).toLowerCase() + "@lamzone.com");
            }
            adressesMail = Adresse;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i1 = item.getItemId();
        switch (i1) {
            case R.id.Tri:
                new AlertDialog.Builder(this)
                        .setView(R.layout.dialog_tri)
                        .setPositiveButton("Trier", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                RadioButton triSalle = (RadioButton) ((AlertDialog) dialog)
                                        .findViewById(R.id.triLieu);
                                boolean salle = false;

                                if (triSalle.isChecked()){
                                    salle = true;
                                }
                                EventBus.getDefault().post(new TriReunionEvent(salle));
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

