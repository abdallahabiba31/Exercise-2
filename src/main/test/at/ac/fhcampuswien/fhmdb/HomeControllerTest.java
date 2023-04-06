package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    HomeController controller = new HomeController();
    @Test
    void display_only_movies_with_selected_genre(){

        //we need multiple Lists
        //one for the example input movies
        List<Movie> example = new ArrayList<>();
        //another one for the movies we expect
        List<Movie> expected = new ArrayList<>();
        //and one for the actual movies we get
        List<Movie> actual;

        //Given
        Movie movie1 = new Movie("Film-1", "Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
        Movie movie2 = new Movie("Film-2", "Description of film-2", Arrays.asList(Genres.DRAMA));
        Movie movie3 = new Movie("Film-3", "Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));
        Movie movie4 = new Movie("Film-4", "Description of film-4", Arrays.asList(Genres.BIOGRAPHY, Genres.DRAMA));
        example.add(movie1);
        example.add(movie2);
        example.add(movie3);
        example.add(movie4);

        //When
        actual = controller.filterMoviesGenre(example, Genres.DRAMA);

        //Then
        expected.add(movie2);
        expected.add(movie3);
        expected.add(movie4);

        //checking if true
        assertEquals(expected, actual);

    }
    @Test
    void display_only_movies_with_right_searchQuery_either_title_or_description(){

        //we need multiple Lists
        //one for the example input movies
        List<Movie> example = new ArrayList<>();
        //another one for the movies we expect
        List<Movie> expected = new ArrayList<>();
        //and one for the actual movies we get
        List<Movie> actual;

        //Given
        Movie movie1 = new Movie("Film-1", "Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
        Movie movie2 = new Movie("Film-2", "Description of film-2 yes", Arrays.asList(Genres.DRAMA));
        Movie movie3 = new Movie("Film-3", "Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));
        Movie movie4 = new Movie("Film-4", "Description of film-4 yes", Arrays.asList(Genres.BIOGRAPHY, Genres.DRAMA));
        example.add(movie1);
        example.add(movie2);
        example.add(movie3);
        example.add(movie4);

        //When
        actual = controller.filterMoviesSearchQuery(example, "yes");

        //Then
        expected.add(movie2);
        expected.add(movie4);

        //checking if true
        assertEquals(expected, actual);
    }
    @Test
    void display_only_movies_with_right_searchQuery_either_title_or_description_and_Genre(){

        List<Movie> example = new ArrayList<>();
        List<Movie> actual;
        List<Movie> temp;

        //Given
        Movie movie1 = new Movie("Film-1", "Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
        Movie movie2 = new Movie("Film-2", "Description of film-2 yes", Arrays.asList(Genres.DRAMA));
        Movie movie3 = new Movie("Film-3", "Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));
        Movie movie4 = new Movie("Film-4", "Description of film-4 yes", Arrays.asList(Genres.BIOGRAPHY, Genres.DRAMA));
        example.add(movie1);
        example.add(movie2);
        example.add(movie3);
        example.add(movie4);

        // WHEN
        temp = controller.filterMoviesSearchQuery(example, "yes");
        actual = controller.filterMoviesGenre(temp, Genres.BIOGRAPHY);

        // THEN
        //List or ObservableList contain movies with the search query
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie4);

        assertEquals(expectedMovies, actual);
    }
    //not working --> Btn = null
    /*
    @Test
    void sort_asc(){

        //we need multiple Lists
        ObservableList<Movie> example = FXCollections.observableArrayList();

        //Given
        Movie movie1 = new Movie("Film-1", " A Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
        Movie movie2 = new Movie("Film-2", "Description of film-2 yes", Arrays.asList(Genres.DRAMA));
        Movie movie3 = new Movie("Film-3", " C Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));

        example.add(movie1);
        example.add(movie2);
        example.add(movie3);

        //test
        //String sortBtnText = "Sort (asc)";
        // WHEN
        controller.sortMovies(example, "Sort (asc)"); //actual


        // assert
        assertEquals("Film-1", example.get(0).getTitle());
        assertEquals("Film-2", example.get(2).getTitle());
        assertEquals("Film-3", example.get(1).getTitle());
        //assertEquals("Sort (desc)", sortBtnText);
    }
    */

    /*
    @Test
    void sort_desc(){

        //we need multiple Lists
        ObservableList<Movie> example = FXCollections.observableArrayList();

        //Given
        Movie movie1 = new Movie("Film-1", " A Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
        Movie movie2 = new Movie("Film-2", "Description of film-2 yes", Arrays.asList(Genres.DRAMA));
        Movie movie3 = new Movie("Film-3", " C Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));

        example.add(movie1);
        example.add(movie2);
        example.add(movie3);

        //test
        //String sortBtnText = "Sort (desc)";
        // WHEN
        controller.sortMovies(example, "Sort (desc)"); //actual

        // assert
        assertEquals("Film-1", example.get(2).getTitle());
        assertEquals("Film-2", example.get(0).getTitle());
        assertEquals("Film-3", example.get(1).getTitle());
        //assertEquals("Sort (asc)", sortBtnText);
    }*/
    /*
    @Test
     void testClearFilter() {
        // arrange
        ObservableList<Movie> allMovies = FXCollections.observableArrayList();
        allMovies.add(new Movie("Movie 1", "Action", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY)));
        allMovies.add(new Movie("Movie 2", "Comedy", Arrays.asList(Genres.ACTION)));
        allMovies.add(new Movie("Movie 3", "Drama", Arrays.asList(Genres.DRAMA, Genres.SPORT)));

        ComboBox<String> genreComboBox = new ComboBox<String>();
        genreComboBox.getItems().addAll("Action", "Comedy", "Drama");
        genreComboBox.getSelectionModel().select("Action");
        TextField searchField = new TextField("Movie 1");

        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
        observableMovies.addAll(allMovies);

        // act
        controller.clearFilter();

        // assert
        assertTrue(genreComboBox.getSelectionModel().isEmpty());
        assertEquals("Filter by Genre", genreComboBox.getPromptText());
        assertEquals(allMovies.size(), observableMovies.size());
        assertTrue(observableMovies.containsAll(allMovies));
        assertTrue(searchField.getText().isEmpty());
    }
*/
}
