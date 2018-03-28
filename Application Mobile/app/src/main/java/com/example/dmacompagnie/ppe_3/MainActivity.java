package com.example.dmacompagnie.ppe_3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.dmacompagnie.ppe3.*;


public class MainActivity extends AppCompatActivity {

    User utilisateur;
    JSONArray praticien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextPsw = findViewById(R.id.txtview_psw);

        editTextPsw.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Button submit = findViewById(R.id.button);
                    submit.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    public void connexion_onClick(View v) {
        TextView user = findViewById(R.id.txtview_user);
        TextView psw = findViewById(R.id.txtview_psw);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://www.web-epsi-lyon.com/tremouille/ok/access?login=" + user.getText()+ "&mdp=" + psw.getText(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject compteRednuJson = new JSONObject(response);
                            if (compteRednuJson.has("info") && !compteRednuJson.has("erreur")){
                                setContentView(R.layout.menu_layout);
                                JSONObject result = compteRednuJson.getJSONObject("info");
                                utilisateur = new User(result.getString("Matricule"),
                                        result.getString("Nom"),
                                        result.getString("Prenom"),
                                        TypeUser.valueOf(result.getString("Type")), //VERIFIER SI C'EST BON
                                        result.getString("Adresse"),
                                        result.getString("CP"),
                                        result.getString("Ville"),
                                        result.getString("Date_Embauche"),
                                        null,
                                        null,
                                        null,
                                        null);
                            }
                            else if (compteRednuJson.has("erreur")){
                                Toast.makeText(getApplicationContext(), compteRednuJson.getString("erreur"), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        queue.add(stringRequest);
    }

    public void goto_profil(View v){
        setContentView(R.layout.profil);

        TextView txtMatricule = findViewById(R.id.textViewMatricule);
        txtMatricule.setText(utilisateur.getMatricule());
        TextView txtNom = findViewById(R.id.textViewNom);
        txtNom.setText(utilisateur.getMatricule());
        TextView txtPrenom = findViewById(R.id.textViewPrenom);
        txtPrenom.setText(utilisateur.getPrenom());
        TextView txtType = findViewById(R.id.textViewType);
        txtType.setText(utilisateur.getType().toString());
        TextView txtAdresse = findViewById(R.id.textViewAdresse);
        txtAdresse.setText(utilisateur.getAdresse());
        TextView txtCP = findViewById(R.id.textViewCP);
        txtCP.setText(utilisateur.getCodePostal());
        TextView txtVille = findViewById(R.id.textViewVille);
        txtVille.setText(utilisateur.getVille());
        TextView txtDateEmbauche = findViewById(R.id.textViewDateEmbauche);
        txtDateEmbauche.setText(utilisateur.getDateEmbauche());
        if (utilisateur.getRefResponsable()!=null){
            TextView txtRefResp = findViewById(R.id.textViewRefResp);
            txtRefResp.setText(utilisateur.getRefResponsable());
        }
        else{
            LinearLayout linearRefResp = findViewById(R.id.linearRefResp);
            linearRefResp.setVisibility(View.GONE);
        }
        if (utilisateur.getRefDelegue() != null){
            TextView txtRefDel = findViewById(R.id.textViewRefDel);
            txtRefDel.setText(utilisateur.getRefDelegue());
        }
        else{
            LinearLayout linearRefDel = findViewById(R.id.linearRefDel);
            linearRefDel.setVisibility(View.GONE);
        }
        if (utilisateur.getRegion() != null){
            TextView txtRegion = findViewById(R.id.textViewRegion);
            txtRegion.setText(utilisateur.getRegion());
        }
        else{
            LinearLayout linearRegion = findViewById(R.id.linearRegion);
            linearRegion.setVisibility(View.GONE);
        }
        if (utilisateur.getSecteur() != null){
            TextView txtSecteur = findViewById(R.id.textViewSecteur);
            txtSecteur.setText(utilisateur.getSecteur());
        }
        else{
            LinearLayout linearSecteur = findViewById(R.id.linearSecteur);
            linearSecteur.setVisibility(View.GONE);
        }
    }

    public void goto_menu(View v){
        setContentView(R.layout.menu_layout);
    }

    public void goto_compterendu(View v){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://www.web-epsi-lyon.com/tremouille/ok/rapport",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            setContentView(R.layout.compte_rendu_1);

                            JSONObject compteRednuJson = new JSONObject(response);
                            List<String> arraySpinner = new ArrayList<>();
                            Spinner spinner = findViewById(R.id.spinner2);
                            for (int i = 0; i < compteRednuJson.getJSONArray("motifs").length(); i++) {
                                JSONArray jsonArray = compteRednuJson.getJSONArray("motifs");
                                arraySpinner.add(jsonArray.getString(i));
                            }
                            ArrayAdapter<String> adp1 = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, arraySpinner);
                            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adp1);

                            //JSONArray medic = compteRednuJson.getJSONArray("medicaments");
                            //JSONArray echantillons = compteRednuJson.getJSONArray("echantillons");
                            praticien = compteRednuJson.getJSONArray("praticiens");

                            AutoCompleteTextView praticienEditText = findViewById(R.id.editTextPraticien);
                            praticienEditText.setCompletionHint("a");
                            praticienEditText.setCompletionHint("b");
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        queue.add(stringRequest);
    }

    public void praticienOnChange(){

    }

    public void goto_documentation(View v){
        setContentView(R.layout.compte_rendu_1);
    }

}
