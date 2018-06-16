package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
	
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	private static final NumberFormat percent = NumberFormat.getPercentInstance();
	private BigDecimal tipPercentage = new BigDecimal(0.15);
	
	@FXML
    private TextField tipTextField;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    void initialize() {
         
    	currency.setRoundingMode(RoundingMode.HALF_UP);
    	
    	// listener for changes to tipPercentageSlider's value
    	// valueProperty zwraca jego wartoœæ w tym momencie
    	tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0); // intValue returns int value of this object
				                        // valueOf translates long/double into BigDecimal    
				tipPercentageLabel.setText(percent.format(tipPercentage));				
			}
		});
    }
    
    @FXML
    void calculateButtonPressed(ActionEvent event){
    	try {

    		BigDecimal amount = new BigDecimal(amountTextField.getText());
    		BigDecimal tip = amount.multiply(tipPercentage);
    		BigDecimal total = amount.add(tip);

    		tipTextField.setText(currency.format(tip));
    		totalTextField.setText(currency.format(total));

    	} catch (NumberFormatException e) {

    		tipTextField.clear();
    		totalTextField.clear();
    		amountTextField.setText("Enter amount");
    		amountTextField.selectAll();// selects all text in the text input
    		amountTextField.requestFocus();//request that this node get  the input focus, 
    		//and that this Node's top-level ancestor become the focused window.
    	}    	
    }
	
	
}
