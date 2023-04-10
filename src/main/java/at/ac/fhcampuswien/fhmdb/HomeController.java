package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
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
import java.util.*;
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

    public List<Movie> allMovies;


    protected ObservableList<Movie> observableMovies = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();
    }

    public void initializeState() {
        allMovies = MovieAPI.getAllMovies();
        observableMovies.clear();
        observableMovies.addAll(allMovies);

    }


    public void initializeLayout() {
        movieListView.setItems(observableMovies);   // set the items of the listview to the observable list
        movieListView.setCellFactory(movieListView -> new MovieCell()); // apply custom cells to the listview

        // Add genres
        //genreComboBox.getItems().add("No filter");  // add "no filter" to the ComboBox
        genreComboBox.setPromptText("Filter by Genre");
        //probably they are more efficient ways to do this but :)
        genreComboBox.getItems().addAll(Genres.ACTION, Genres.DRAMA, Genres.ADVENTURE, Genres.ANIMATION, Genres.BIOGRAPHY,
                Genres.COMEDY, Genres.CRIME, Genres.DOCUMENTARY, Genres.FAMILY, Genres.FANTASY, Genres.HISTORY, Genres.HORROR, Genres.MUSICAL,
                Genres.MYSTERY, Genres.ROMANCE, Genres.SCIENCE_FICTION, Genres.WESTERN, Genres.WAR, Genres.SPORT, Genres.THRILLER);

        // Add release years
        Integer[] releaseYears = new Integer[78];
        for (int i = 0; i < 78; i++) {
            releaseYears[i] = 2023 - i;
        }
        //releaseYearComboBox.getItems().add("No filter");
        releaseYearComboBox.getItems().addAll(releaseYears);
        releaseYearComboBox.setPromptText("Filter by release Year");

        // Add rating
        Double[] rating = new Double[]{1.00, 2.00, 3.00, 4.00, 5.00, 6.00, 7.00, 8.00, 9.00};
        ratingComboBox.getItems().addAll(rating);
        ratingComboBox.setPromptText("Filter by rating: selected or higher");

        // Sort button
        sortBtn.setOnAction(actionEvent -> {
            sortMovies(observableMovies, sortBtn.getText());
        });
        //clear button
        clearBtn.setOnAction(actionEvent -> {
            clearFilter();
        });
        //search button
        searchBtn.setOnAction(actionEvent -> {
            String searchQuery = searchField.getText().trim().toLowerCase();
            Object genre = genreComboBox.getSelectionModel().getSelectedItem();
            Integer releaseYear = (Integer) releaseYearComboBox.getSelectionModel().getSelectedItem();
            double rating2 = (Double) ratingComboBox.getSelectionModel().getSelectedItem();

            applyAllFilters(searchQuery, genre, releaseYear, rating2);
        });
    }


    public List<Movie> filterByReleaseYear(List<Movie> movies, Integer releaseYear) {
        if (releaseYear == null){
            return movies;
        }

        if (movies == null) {
            throw new IllegalArgumentException("movies must not be null");
        }

        return movies.stream()
                .filter(Objects::nonNull)
                .filter(movie -> movie.getReleaseYear() == releaseYear)
                .toList();
    }



    public void sortMovies(ObservableList<Movie> movies, String sortBtnText) {
        if (sortBtnText.equals("Sort (asc)")) {
            sortBtn.setText("Sort (desc)");
            //specifies that the items in the list should be sorted based on the title of the movie
            //"Movie::getTitle" is a method that returns the title of the movie like a "shortcut"
            movies.sort(Comparator.comparing(Movie::getTitle));

        } else {
            sortBtn.setText("Sort (desc)");
            movies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortBtn.setText("Sort (asc)");
        }
    }
    public void clearFilter() {
        genreComboBox.getSelectionModel().clearSelection();
        releaseYearComboBox.getSelectionModel().clearSelection();
        ratingComboBox.getSelectionModel().clearSelection();
        searchField.clear();

        initializeState();
    }




    public List<Movie> filterByRating(List<Movie> movies, String rating) {
        if (rating == null) return movies;

        if (movies == null) {
            throw new IllegalArgumentException("movies must not be null");
        }

        if (movies == null) {
            throw new IllegalArgumentException("movies must not be null");
        }

        double minRating = Double.parseDouble(rating.replace("+", ""));
        return movies.stream()
                .filter(Objects::nonNull)
                .filter(movie -> movie.getRating() >= minRating)
                .toList();
    }


    public List<Movie> filterByGenre(List<Movie> movies, Genres genre) {
        if (genre == null) return movies;

        if (movies == null) {
            throw new IllegalArgumentException("movies must not be null");
        }

        return movies.stream()
                .filter(Objects::nonNull)
                .filter(movie -> movie.getGenre().contains(genre))
                .toList();
    }


    public void applyAllFilters(String searchQuery, Object genre, Integer releaseYear, Double rating) {
        List<Movie> filteredMovies = allMovies;

        if (!searchQuery.isEmpty()) {
            filteredMovies = filterByQuery(filteredMovies, searchQuery);
        }

        if (genre != null && !genre.toString().equals("No filter")) {
            filteredMovies = filterByGenre(filteredMovies, Genres.valueOf(genre.toString()));
        }

        if (releaseYear != null) {
            filteredMovies = filterByReleaseYear(filteredMovies, Integer.valueOf(releaseYear.toString()));
        }

        if (rating != null) {
            filteredMovies = filterByRating(filteredMovies, rating.toString());
        }

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
    }

    public void searchBtnClicked(ActionEvent actionEvent) {
        String searchQuery = searchField.getText().trim().toLowerCase();
        Object genre = genreComboBox.getSelectionModel().getSelectedItem();
        Integer releaseYear = (Integer) releaseYearComboBox.getSelectionModel().getSelectedItem();
        Double rating = (Double) ratingComboBox.getSelectionModel().getSelectedItem();

        applyAllFilters(searchQuery, genre, releaseYear, rating);


    }


    // RESET BUTTON
    public void clearBtnClicked(ActionEvent actionEvent) {
        genreComboBox.getSelectionModel().clearSelection();
        releaseYearComboBox.getSelectionModel().clearSelection();
        ratingComboBox.getSelectionModel().clearSelection();
        searchField.clear();

        initializeState();
    }


    public void sortBtnClicked(ActionEvent actionEvent) {
        sortMovies();
    }




    String getMostPopularActor(List<Movie> movies) {
        List<String> newList = new ArrayList<>();
        movies.stream().map(m -> newList.addAll(Arrays.asList(m.mainCast))).collect(Collectors.toList());
        Map<String, Long> newMap = newList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return newMap.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }



}