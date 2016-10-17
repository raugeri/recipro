import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by Dillon Fagan on 9/29/16.
 */
class HomeTab extends Tab {

    /**
     * Serves as a comma-delimited entry for ingredient search.
     */
    private TextField searchField;

    HomeTab() {
        super("Home");

        setClosable(false);

        // Primary layout for the tab
        VBox primaryLayout = new VBox();
        primaryLayout.setPadding(new Insets(16, 16, 16, 16));
        primaryLayout.setSpacing(20);
        primaryLayout.setAlignment(Pos.CENTER);
        primaryLayout.setBackground(new Background(new BackgroundFill(Color.rgb(220, 60, 60), CornerRadii.EMPTY, Insets.EMPTY)));

        // Title of the application, shown in large font
        Label titleLabel = new Label("FoodFinder");
        titleLabel.setFont(new Font("Verdana", 32));
        titleLabel.setTextFill(Color.WHITE);

        // Secondary layout to house input and submit button
        HBox secondaryLayout = new HBox();
        secondaryLayout.setSpacing(10);
        secondaryLayout.setAlignment(Pos.CENTER);

        // Text field for inputting the ingredients or recipe name
        searchField = new TextField();
        searchField.setPromptText("Enter ingredients...");
        searchField.setPrefSize(250, TextField.USE_COMPUTED_SIZE);
        searchField.setFocusTraversable(false);

        // Button to submit the query
        Button searchButton = new Button("Search");
        searchButton.setOnAction(a -> {
                        try {
				fetchIngredients();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            searchField.setText("");
        });

        // Button to open a "New Recipe" window
        Button newRecipeButton = new Button("New Recipe...");
        newRecipeButton.setOnAction(a -> {
            createNewRecipe();
        });

        // Add all items to the secondary layout
        secondaryLayout.getChildren().addAll(searchField, searchButton);

        // Add all items to the primary layout
        primaryLayout.getChildren().addAll(titleLabel, secondaryLayout, newRecipeButton);

        // Set primary layout as the content of the tab
        setContent(primaryLayout);
    }

    /**
     * Fetches the comma-separated ingredients in the ingredient field
     * and initiates a search.
     */
    private void fetchIngredients() throws SQLException {
        for (String s : searchField.getText().split(", ")) {
            Connect newConnection = new Connect();
            newConnection.query(s);
            //Temporarily print ingredients to the console
        	newConnection.getResults();
        }

        getTabPane().getTabs().add(new SearchTab());
    }

    /**
     * Opens a new RecipeTab to write a new recipe.
     */
    private void createNewRecipe() {
        getTabPane().getTabs().add(new RecipeTab());
    }
}
