import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private TextField tfGuess = new TextField();
    private TextField tfLettersGuessed = new TextField();
    private Dictionary word = new Dictionary();

    private Text[] text;
    private Label guessesRemaining;
    private Label resultLabel;

    private int left;
    private ArrayList<Shape> body;
    private ObservableList<Node> children;
    private StringBuilder guessedLetters;
    private String theWord;

    private boolean gameEnded = false;

    private void initGallows() {
        Line gallow1 = new Line(25, 25, 200, 25);
        gallow1.setStroke(Color.RED);
        gallow1.setStrokeWidth(3);
        children.add(gallow1);

        Line gallow2 = new Line(25, 25, 25, 300);
        gallow2.setStroke(Color.RED);
        gallow2.setStrokeWidth(3);
        children.add(gallow2);

        Line gallow3 = new Line(300, 300, 25, 300);
        gallow3.setStroke(Color.RED);
        gallow3.setStrokeWidth(3);
        children.add(gallow3);

        Line rope = new Line(200, 25, 200, 75);
        rope.setStroke(Color.RED);
        rope.setStrokeWidth(3);
        children.add(rope);
    }

    private void initBody() {
        body = new ArrayList<>();

        Ellipse head = new Ellipse(200, 112, 35, 35);
        head.setStroke(Color.BLACK);
        head.setFill(Color.WHITE);
        head.setStrokeWidth(5);
        head.setVisible(false);
        children.add(head);
        body.add(head);

        Line tor = new Line(200, 200, 200, 150);
        tor.setStroke(Color.BLACK);
        tor.setStrokeWidth(5);
        tor.setVisible(false);
        children.add(tor);
        body.add(tor);

        Line lftArm = new Line(150, 225, 200, 175);
        lftArm.setStroke(Color.BLACK);
        lftArm.setStrokeWidth(5);
        lftArm.setVisible(false);
        children.add(lftArm);
        body.add(lftArm);

        Line rtArm = new Line(250, 225, 200, 175);
        rtArm.setStroke(Color.BLACK);
        rtArm.setStrokeWidth(5);
        rtArm.setVisible(false);
        children.add(rtArm);
        body.add(rtArm);

        Line lftLeg = new Line(200, 200, 175, 275);
        lftLeg.setStroke(Color.BLACK);
        lftLeg.setStrokeWidth(5);
        lftLeg.setVisible(false);
        children.add(lftLeg);
        body.add(lftLeg);

        Line rtLeg = new Line(200, 200, 225, 275);
        rtLeg.setStroke(Color.BLACK);
        rtLeg.setStrokeWidth(5);
        rtLeg.setVisible(false);
        children.add(rtLeg);
        body.add(rtLeg);
    }

    private void initBlanks(String word) {
        Line[] blanks = new Line[word.length()];
        int xStart = 375;
        int lineLength = 25;
        int lineSpacing = 35;
        for (int i = 0; i < blanks.length; i++) {
            int xcoord = xStart + (lineSpacing * i);
            blanks[i] = new Line(xcoord, 225, xcoord - lineLength, 225);
            blanks[i].setStroke(Color.BLACK);
            blanks[i].setStrokeWidth(3);
            children.add(blanks[i]);
        }
    }

    private Text[] initText(String word) {
        Text[] text = new Text[word.length()];
        int xStartw = 355;
        int lineSpacingw = 35;
        for (int i = 0; i < text.length; i++) {
            int xcoordw = xStartw + (lineSpacingw * i);
            text[i] = new Text(word.substring(i, i + 1));
            text[i].setFont(new Font(30));
            text[i].setX(xcoordw);
            text[i].setY(220);
            text[i].setVisible(false);
            children.add(text[i]);
        }
        return text;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        children = pane.getChildren();

        initGallows();
        initBody();

        theWord = word.getHiddenWord().toUpperCase();
        guessedLetters = new StringBuilder();

        initBlanks(theWord);


        text = initText(theWord);
        left = 6;

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Enter a letter:"), 0, 0);
        gridPane.add(tfGuess, 1, 0);
        gridPane.add(new Label("Letters Guessed:"), 0, 1);
        gridPane.add(tfLettersGuessed, 1, 1);
        gridPane.add(new Label("Guesses Remaining: "), 0, 2);
        guessesRemaining = new Label(String.valueOf(left));
        gridPane.add(guessesRemaining, 0, 3);

        resultLabel = new Label("");
        gridPane.add(resultLabel, 1, 4);

        BorderPane thing = new BorderPane();
        thing.setRight(gridPane);
        thing.setCenter(pane);

        Button resetButton = new Button("Start New Game");
        resetButton.setOnAction(e -> resetGame());
        gridPane.add(resetButton, 1, 5);
        GridPane.setHalignment(resetButton, HPos.RIGHT);

        tfGuess.setOnAction(e -> playGame());

        Scene scene = new Scene(thing, 700, 400);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resetGame() {
        // Reset all game variables
        theWord = word.getHiddenWord().toUpperCase();
        guessedLetters.setLength(0);
        tfGuess.clear();
        tfLettersGuessed.clear();
    
        // Make all body parts invisible again
        for (Shape part : body) {
            part.setVisible(false);
        }
    
        // Clear existing blanks and text elements
        children.removeIf(node -> node instanceof Line || node instanceof Text);
    
        // Redraw gallows and rope
        initGallows();
        initBlanks(theWord);
        text = initText(theWord);
    
        left = 6;
        guessesRemaining.setText(String.valueOf(left));
        resultLabel.setText("");
        gameEnded = false;
    }
    

    private void playGame() {
        if (gameEnded) {
            System.out.println("The game has ended. Please start a new game.");
            return;
        }
        String guess = tfGuess.getText();
        if (guess.length() == 0) {
            return;
        }
        if (guess.length() > 1) {
            guess = guess.substring(0, 1);
        }
        guess = guess.toUpperCase();
        tfGuess.clear();

        if (guessedLetters.length() > 0) {
            if (guessedLetters.indexOf(guess) > -1) {
                return;
            } else {
                guessedLetters.append(guess);
            }
        } else {
            guessedLetters.append(guess);
        }
        tfLettersGuessed.setText(guessedLetters.toString());

        boolean good = false;
        for (int i = 0; i < theWord.length(); i++) {
            if (guess.equalsIgnoreCase(theWord.substring(i, i + 1))) {
                text[i].setVisible(true);
                good = true;
            }
        }

        if (!good) {
            body.get(6 - left).setVisible(true);
            left--;
            guessesRemaining.setText(String.valueOf(left));
        }

        if (left == 0) {
            resultLabel.setText("Sorry, you lost! The word was: " + theWord);
            gameEnded = true;
        }

        boolean solved = true;
        for (int i = 0; i < text.length; i++) {
            if (!text[i].isVisible()) {
                solved = false;
                break;
            }
        }

        if (solved) {
            resultLabel.setText("Congratulations! You guessed the word: " + theWord);
            gameEnded = true;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

class Dictionary {
    public String getHiddenWord() {
        String[] words = {"hangman", "java", "programming", "computer", "developer"};
        return words[(int) (Math.random() * words.length)];
    }
}
