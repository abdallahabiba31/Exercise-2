package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;
    @FXML
    public JFXComboBox releaseYearComboBox;
    @FXML
    public JFXComboBox ratingComboBox;

    @FXML
    public JFXButton sortBtn;
    @FXML
    public JFXButton clearBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private final ObservableList<Movie> moviesByGenre = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.setPromptText("Filter by Genre");
        //probably they are more efficient ways to do this but :)
        genreComboBox.getItems().addAll(Genres.ACTION, Genres.DRAMA, Genres.ADVENTURE, Genres.ANIMATION, Genres.BIOGRAPHY,
                Genres.COMEDY, Genres.CRIME, Genres.DOCUMENTARY, Genres.FAMILY, Genres.FANTASY, Genres.HISTORY, Genres.HORROR, Genres.MUSICAL,
                Genres.MYSTERY, Genres.ROMANCE, Genres.SCIENCE_FICTION, Genres.WESTERN, Genres.WAR, Genres.SPORT, Genres.THRILLER);

        // TODO
        releaseYearComboBox.setPromptText("Filter by Release Year");

        //TODO
        ratingComboBox.setPromptText("Filter by Rating");




        // Sort button
        sortBtn.setOnAction(actionEvent -> {
            //call method
            sortMovies(observableMovies, sortBtn.getText());
        });
        //clear button
        clearBtn.setOnAction(actionEvent -> {
            clearFilter();
        });
        //search button
        searchBtn.setOnAction(actionEvent -> {
            //cast genreComboBox or convert to string
            filterMoviesGenre(allMovies, (Genres) genreComboBox.getValue());
            filterMoviesSearchQuery(moviesByGenre, searchField.getText().toLowerCase());
            sortMovies(moviesByGenre,sortBtn.getText());
        });

    }
    public void sortMovies(ObservableList<Movie> movies, String sortBtnText) {
        if (sortBtnText.equals("Sort (asc)")) {
            // TODO sort observableMovies ascending
            sortBtn.setText("Sort (desc)");
            //specifies that the items in the list should be sorted based on the title of the movie
            //"Movie::getTitle" is a method that returns the title of the movie like a "shortcut"
            movies.sort(Comparator.comparing(Movie::getTitle));

        } else {
            // TODO sort observableMovies descending
            sortBtn.setText("Sort (desc)");
            movies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortBtn.setText("Sort (asc)");
        }
    }
    public void clearFilter() {
        //clear the combobox and then set the text of it
        genreComboBox.getSelectionModel().clearSelection();
        genreComboBox.setPromptText("Filter by Genre");

        //showing all the movies
        observableMovies.clear();
        observableMovies.setAll(allMovies);
        searchField.clear();
    }
    public List<Movie> filterMoviesGenre(List<Movie> allMovies, Genres genreSelection) {
        //clear list before adding movies
        //so that the movies won't be added multiple times
        moviesByGenre.clear();

        if (genreSelection != null) {
            for (Movie movies : allMovies) {
                List<Genres> genres = movies.getGenre();

                if (genres.contains(genreSelection)) {
                    moviesByGenre.add(movies);
                }
            }
            observableMovies.setAll(moviesByGenre);
        } else {
            //if nothing is selected
            //show all movies
            moviesByGenre.setAll(allMovies);
        }
        return moviesByGenre;
    }


    public List<Movie> filterMoviesSearchQuery(List<Movie> moviesByGenre, String searchQuery) {

        ObservableList<Movie> movieList = FXCollections.observableArrayList();
        movieList.clear();
        //searchField never empty so the if statement
        if(searchQuery != " ") {
            //go through already filtered (by genre) movies
            for (Movie searchInMovies : moviesByGenre) {
                //getting the titles and descriptions of the filtered movies
                String title = searchInMovies.getTitle().toLowerCase();
                String description = searchInMovies.getDescription().toLowerCase();


                // Check if the title or description of the movie contains in the search query
                if (title.contains(searchQuery) || description.contains(searchQuery)) {
                    //if == yes --> adding them to the list
                    movieList.add(searchInMovies);
                }
                // Set the observableMovies list to the filtered list
                observableMovies.setAll(movieList);
            }
        }
        return movieList;
    }

}