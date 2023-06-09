package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genres> genres;

    //TODO
    private String id;
    private String imgUrl;
    private int releaseYear;
    private int lengthInMinutes;
    private double rating;
    private List<String> directors;
    private List<String> mainCast;
    private List<String> writers;

    //den "alten" Konstruktor so gelassen und einen weiteren hinzugefügt
    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        //TODO
        this.id = null;
        this.releaseYear = 0;
        this.imgUrl = "";
        this.lengthInMinutes = 0;
        this.directors = null;
        this.writers = null;
        this.mainCast = null;
        this.rating = 0;
    }

    //TODO
    //weiteren Konstruktor mit allen Attributen
    public Movie(String title, String description, List<Genres> genres,String id, int releaseYear, String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, float rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.id = id;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }
    //Ich weiß nicht genau, was das macht, aber hat der Tutor gezeigt gehabt
    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        if(object == this){
            return true;
        }
        if(!(object instanceof Movie other)){
            return false;
        }
        return this.title.equals(other.title) && this.description.equals(other.description) && this.genres.equals(other.genres);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genres> getGenre() {
        return genres;
    }
    //TODO variablen hinzufügen
    public String getId() {
        return id;
    }
    public List<String> getDirectors() {
        return directors;
    }
    public List<String> getMaincast() {
        return mainCast;
    }
    public List<String> getWriters() {
        return writers;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
    public int getLengthInMinutes(){
        return lengthInMinutes;
    }
    public double getRating() {
        return rating;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Fast & Furious 10",
                "Es handelt sich um den elften Spielfilm innerhalb der Fast-&-Furious-Filmreihe und um eine Fortsetzung zu Fast & Furious 9 aus dem Jahr 2021.",
                Arrays.asList(Genres.ACTION)));
        movies.add(new Movie("Creed 3 - Rockys Legacy",
                "Dritter Teil der erfolgreichen „Creed\"-Filmreihe, in der Michael B. Jordan einem alten Freund im Ring gegenübersteht, der nichts zu verlieren hat.",
                Arrays.asList(Genres.DRAMA, Genres.SPORT)));
        movies.add(new Movie("Missing",
                "Missing: Mystery-Thriller mit Storm Reid, die versucht, ihre Mutter aufzuspüren, welche seit einem Urlaub mit ihrem neuen Freund in Kolumbien verschwunden ist.",
                Arrays.asList(Genres.DRAMA)));
        movies.add(new Movie("Barbie",
                "In ihrem ersten Live-Action-Film verwandelt sich die berühmte Plastikpuppe Barbie in einen echten Menschen - und lernt, das wahre Schönheit nur von innen kommt.",
                Arrays.asList(Genres.COMEDY)));
        movies.add(new Movie("JOHN WICK - KAPITEL 4",
                "Im vierten Teil der Reihe nimmt es John Wick mit seinen bisher tödlichsten Widersachern auf. Während das Kopfgeld auf ihn immer höher wird, zieht Wick in einen weltweiten Kampf gegen die mächtigsten Akteure der Unterwelt - von New York über Paris und Osaka bis nach Berlin.",
                Arrays.asList(Genres.ACTION, Genres.THRILLER, Genres.CRIME)));
        movies.add(new Movie("Beau is afraid",
                "Ein Jahrzehnte umfassendes Porträt von einem der mächtigsten Unternehmer aller Zeiten.",
                Arrays.asList(Genres.HORROR, Genres.MYSTERY, Genres.DRAMA, Genres.COMEDY)));
        movies.add(new Movie("OPERATION FORTUNE: Ruse de guerre",
                "Operation Fortune ist ein Agentenfilm von Guy Ritchie mit Jason Statham und Aubrey Plaza. In Guy Ritchies Operation Fortune schlüpft Jason Statham in die Rolle des MI6-Agenten Orson Fortune, der zusammen mit einem gefeierten Filmstar die Gefahr einer neuen Waffentechnologie bannen will.",
                Arrays.asList(Genres.ACTION)));
        movies.add(new Movie("SHOTGUN WEDDING - EIN KNALLHARTES TEAM",
                "Shotgun Wedding - Ein knallhartes Team ist ein Actionfilm von Jason Moore mit Jennifer Lopez und Josh Duhamel. In der romantischen Actionkomödie Shotgun Wedding bekommen Jennifer Lopez und Josh Duhamel kalte Füße bei ihrer eigenen Hochzeit ... doch dann wird die gesamte Festgesellschaft zum Ziel einer Geiselnahme.",
                Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY)));
        //Vorlage, um weitere Filme zu adden
        /*movies.add(new Movie("",
                "",
                Arrays.asList(Genres.ACTION)));*/
        return movies;
    }

}
