package Entities;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

public class Cancion {
    private String spotify_id;
    private  String name;
    private MyList<String> artist;
    private  int Daily_rank;
    private  int daily_movement;
    private  int weekly_movement;
    private  String country;
    private  LocalDate snapshot_date;
    private  int pupulariry;
    private  boolean is_explicit;
    private  int duration_ms;
    private  String album_name;
    private  String album_release_date;
    private  float danceability;
    private  float energy;
    private  int key;
    private  float loudness;
    private  float mode;
    private  float speechiness;
    private  float acousticness;
    private  float instrumentalness;
    private  float liveness;
    private  float valence;
    private  float tempo;
    private  int time_signature;



    public Cancion(){
        this.artist = new MyLinkedListImpl<>();
    }


    @Override
    public String toString() {
        return "Cancion: " + name +
                " - Por: " + artist
                ;
    }

    //Getters y Setters
    public String getSpotify_id() {
        return spotify_id;
    }

    public void setSpotify_id(String spotify_id) {
        this.spotify_id = spotify_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyList<String> getArtist() {
        return artist;
    }

    public void setArtist(MyList<String> artist) {
        this.artist = artist;
    }

    public int getDaily_rank() {
        return Daily_rank;
    }

    public void setDaily_rank(int daily_rank) {
        this.Daily_rank = daily_rank;
    }

    public int getDaily_movement() {
        return daily_movement;
    }

    public void setDaily_movement(int daily_movement) {
        this.daily_movement = daily_movement;
    }

    public int getWeekly_movement() {
        return weekly_movement;
    }

    public void setWeekly_movement(int weekly_movement) {
        this.weekly_movement = weekly_movement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getSnapshot_date() {
        return snapshot_date;
    }

    public void setSnapshot_date(LocalDate snapshot_date) {
        this.snapshot_date = snapshot_date;
    }

    public int getPupulariry() {
        return pupulariry;
    }

    public void setPupulariry(int pupulariry) {
        this.pupulariry = pupulariry;
    }

    public boolean isIs_explicit() {
        return is_explicit;
    }

    public void setIs_explicit(boolean is_explicit) {
        this.is_explicit = is_explicit;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_release_date() {
        return album_release_date;
    }

    public void setAlbum_release_date(String album_release_date) {
        this.album_release_date = album_release_date;
    }

    public float getDanceability() {
        return danceability;
    }

    public void setDanceability(float danceability) {
        this.danceability = danceability;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public float getLoudness() {
        return loudness;
    }

    public void setLoudness(float loudness) {
        this.loudness = loudness;
    }

    public float getMode() {
        return mode;
    }

    public void setMode(float mode) {
        this.mode = mode;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(float speechiness) {
        this.speechiness = speechiness;
    }

    public float getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(float acousticness) {
        this.acousticness = acousticness;
    }

    public float getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(float instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public float getLiveness() {
        return liveness;
    }

    public void setLiveness(float liveness) {
        this.liveness = liveness;
    }

    public float getValence() {
        return valence;
    }

    public void setValence(float valence) {
        this.valence = valence;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public int getTime_signature() {
        return time_signature;
    }

    public void setTime_signature(int time_signature) {
        this.time_signature = time_signature;
    }


}