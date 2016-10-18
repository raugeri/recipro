import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Dillon Fagan on 9/13/16.
 */
public class Recipro extends Application {

    private TabPane tabs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Primary layout for the window
        VBox rootLayout = new VBox();

        // Set up the window
        primaryStage.setTitle("Recipro");
        primaryStage.setScene(new Scene(rootLayout, 860, 660));

        // Initialize the tab manager and add a HomeTab
        tabs = new TabPane();
        tabs.setPrefSize(860, 660);
        tabs.getTabs().add(new HomeTab());

        // Add all items to the root layout
        rootLayout.getChildren().addAll(createMenuBar(), tabs);

        // Show the window
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar  = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem newRecipeCommand = new MenuItem("New Recipe...");
        newRecipeCommand.setOnAction(a -> {
          tabs.getTabs().add(new RecipeTab());
        });

        fileMenu.getItems().add(newRecipeCommand);

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }
}
