/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 5;
    int additional = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = createOrderSummary(); //submitOrder -> createOrder Summary -> displayMessage
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(priceMessage);
    }
    private String createOrderSummary(){
        TextView priceLabel = findViewById(R.id.priceLabel);
        priceLabel.setText("ORDER SUMMARY");
        CheckBox cream = findViewById(R.id.cream);
        CheckBox chocolate = findViewById(R.id.chocolate);
        EditText name = findViewById(R.id.name);
        if(!name.getText().toString().equals("Name:"))
        return "Name: " + name.getText() + "\nAdd Whipped Cream? " + cream.isChecked() + "\nAdd Chocolate? "+ chocolate.isChecked() + "\nQuantity: " + quantity + "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice()) + "\nThank You!";
        else
            return "Name: Anonymous" + "\nAdd Whipped Cream? " + cream.isChecked() + "\nAdd Chocolate? "+ chocolate.isChecked() + "\nQuantity: " + quantity + "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice()) + "\nThank You!";

    }
    /**
     * This method is called when plus button is clicked
     */
    private int calculatePrice(){
        return (quantity * price) + additional;
    }

    public void updatePriceCream(View view){//when this checkbox is checked, updatePriceCream() should show the correct price
        CheckBox cream = findViewById(R.id.cream);
        if(cream.isChecked())
            additional += 2;
        else if(!cream.isChecked())
            additional -= 2;

        displayPrice(false);

    }

    public void updatePriceChocolate(View view){
        CheckBox chocolate = findViewById(R.id.chocolate);
        if(chocolate.isChecked())
            additional++;
        else if(!chocolate.isChecked())
            additional--;
        displayPrice(false);
    }
    public void increment(View view){
        CheckBox cream = findViewById(R.id.cream);
        CheckBox chocolate = findViewById(R.id.chocolate);
        quantity++;
        displayQuantity();
        displayPrice(false);
        cream.setChecked(false);
        chocolate.setChecked(false);
    }

    /**
     * This method is called when the minus button is clicked
     */
    public void decrement(View view){
        CheckBox cream = findViewById(R.id.cream);
        CheckBox chocolate = findViewById(R.id.chocolate);
        if(quantity > 0) {
            quantity--;
            displayQuantity();
            cream.setChecked(false);
            chocolate.setChecked(false);
            displayPrice(true);
            additional = 0;
        }
        else quantity = 0;

    }

    public void clearNameField(View view){
        EditText name = findViewById(R.id.name);
        String data = name.getText().toString();
        if(data.equals("Name:"))
            name.setText("");
    }
    public void clearAll(View view){
        CheckBox cream = findViewById(R.id.cream);
        cream.setChecked(false);

        CheckBox chocolate = findViewById(R.id.chocolate);
        chocolate.setChecked(false);

        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("0");
        quantity = 0;
        additional = 0;
        displayPrice(false);
    }
    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity() {//updates quantity as user increments or decrements
        TextView quantityTextView = findViewById(R.id.quantity_text_view);//find quantity_text_view by traversing through root view tree
        quantityTextView.setText("" + quantity);//once found change text
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(boolean returnToPreviousPrice) { //updates price as user increments or decrements
        TextView priceTextView = findViewById(R.id.price_text_view);
        if(returnToPreviousPrice)
        priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance().format(calculatePrice()-(additional)));
        else
            priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance().format(calculatePrice()));

    }
}