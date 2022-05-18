package controller;

import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Ingredients;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class BakeryController {

    private int cash = 250;
    private int item;

    private ObservableList<Ingredients> observableList = FXCollections.observableArrayList();

    @FXML
    private TableView display;
    @FXML
    private TableColumn<Ingredients, String> name;

    @FXML
    private TableColumn<Ingredients, Integer> cost;

    @FXML
    private TableColumn<Ingredients, Integer> amount;

    @FXML
    private Label result;

    @FXML
    private Label money;

    @FXML
    private void initialize() throws IOException {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        List<Ingredients> ingredients = new ObjectMapper()
                .readValue(BakeryController.class.getResourceAsStream("/json/ingredients.json"), new TypeReference<List<Ingredients>>() {});

        observableList.addAll(ingredients);
        display.setItems(observableList);
    }


    public void increaseAmountOfIngredient(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        item = getIngredientsIdByName(button.getId());
        if(multiplyByFive(observableList.get(item).getCost()) > cash) {
            result.setText("Not enough money");
        }
        else {
            observableList.get(item).setAmount(increaseAmountOfIngredientByFive(observableList.get(item).getAmount()));
            result.setText("Amount of " + observableList.get(item).getName().toLowerCase() + " has increased");
            display.refresh();
            spendMoneyOnIngredients(item);
        }
    }

    public int multiplyByFive(int n) {return n * 5; };

    public int multiplyByTwo(int n) {return n * 2;}

    public int increaseAmountOfIngredientByFive(int n) {
        return n + 5;
    }

    public void spendMoneyOnIngredients(int item) {
        cash = cash - multiplyByFive(observableList.get(item).getCost());
        money.setText(Integer.toString(cash));
    }

    public void bakingBread() {
        if(isThereEnoughAmountOfIngredient()) {
            cash = cash + multiplyByTwo(getPriceOfEveryIngredient(observableList));
            result.setText("Bread baked and sold");
            money.setText(Integer.toString(cash));
            for(int i = 0; i < observableList.size(); i++) {
                observableList.get(i).setAmount(observableList.get(i).getAmount() - 1);
            }
        }
        else {
            for(int i = 0; i < observableList.size(); i++ ) {
                if(observableList.get(i).getAmount() == 0) {
                    result.setText(observableList.get(i).getName() + " is out of stock");
                }
            }
        }
        display.refresh();
    }

    public int getPriceOfEveryIngredient(ObservableList<Ingredients> observableList) {
        int priceOfEveryIngredient = 0;

        for( int i = 0; i < observableList.size(); i++) {
            priceOfEveryIngredient = priceOfEveryIngredient + observableList.get(i).getCost();
        }
        return priceOfEveryIngredient;
    }

    public boolean isThereEnoughAmountOfIngredient() {
        for( int i = 0; i < observableList.size(); i++) {
            if(observableList.get(i).getAmount() == 0) {
                return false;
            }
        }
        return true;
    }


    public int getIngredientsIdByName(String name){
        for( int i = 0; i < observableList.size(); i++) {
            if(name.equals(observableList.get(i).getName())) {
                return i;
            }
        }
        return 0;
    }

    public void writeJson() throws IOException {

        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try (var writer = new FileWriter("ingredients.json")) {
            objectMapper.writeValue(writer, observableList);
        }

        observableList.forEach(System.out::println);
    }

}
