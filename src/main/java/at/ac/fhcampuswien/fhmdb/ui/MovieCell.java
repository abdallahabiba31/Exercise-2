package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detailDesc = new Label();
    private final Label detailGenre = new Label();
    //TODO
    private final Label detailRating = new Label();
    private final Label detailReleaseYear = new Label();


    private final VBox layout = new VBox(title, detailDesc, detailGenre, detailRating, detailReleaseYear);

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            ///////
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detailDesc.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            detailGenre.setText(
                    movie.getGenre() != null
                            ? movie.getGenre().toString()
                            : "No Genre available"
            );
            //TODO
            detailRating.setText(
                    movie.getRating() != 0
                            ? String.format("%.2f", movie.getRating())
                            : "No Rating available"
            );
            detailReleaseYear.setText(
                    movie.getReleaseYear() != 0
                            ? Integer.toString(movie.getReleaseYear())
                            : "No ReleaseYear available"
            );


            // color scheme
            title.getStyleClass().add("text-yellow");
            detailDesc.getStyleClass().add("text-white");
            //*style auch für genre hinzugefügt --> ohne dem, wäre die Textfarbe grau
            detailGenre.getStyleClass().add("text-white");
            //TODO
            detailReleaseYear.getStyleClass().add("text-white");
            detailRating.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detailDesc.setMaxWidth(this.getScene().getWidth() - 30);
            detailDesc.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

