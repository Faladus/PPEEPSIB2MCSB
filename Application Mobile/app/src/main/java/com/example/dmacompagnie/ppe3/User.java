package com.example.dmacompagnie.ppe3;

public class User {

    private String matricule;
    private String nom;
    private String prenom;
    private TypeUser type;
    private String adresse;
    private String codePostal;
    private String ville;
    private String dateEmbauche;
    private String refResponsable;
    private String refDelegue;
    private String region;
    private String secteur;

    public User(String matricule, String nom, String prenom, TypeUser type, String adresse, String codePostal, String Ville, String dateEmbauche, String refResponsable, String refDelegue, String Region, String Secteur) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = Ville;
        this.dateEmbauche = dateEmbauche;
        this.refResponsable = refResponsable;
        this.refDelegue = refDelegue;
        this.region = Region;
        this.secteur = Secteur;
    }

    public String toString(){
        return "["+matricule+"]"+nom+" "+prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public TypeUser getType() {
        return type;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public String getRefResponsable() {
        return refResponsable;
    }

    public String getRefDelegue() {
        return refDelegue;
    }

    public String getRegion() {
        return region;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        ville = ville;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public void setRefResponsable(String refResponsable) {
        this.refResponsable = refResponsable;
    }

    public void setRefDelegue(String refDelegue) {
        this.refDelegue = refDelegue;
    }

    public void setRegion(String region) {
        region = region;
    }

    public void setSecteur(String secteur) {
        secteur = secteur;
    }
}
