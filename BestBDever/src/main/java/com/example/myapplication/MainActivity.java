package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DataBaseHandler;
import Model.Products;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        /*
        dataBaseHandler.addProd(new Products("Milk","Dairy"));
        dataBaseHandler.addProd(new Products("Apple","Fruits"));
        dataBaseHandler.addProd(new Products("Onion","Vegetables"));
*/
        List<Products> productsList = dataBaseHandler.getAllProd();

//        Products deleteProd = dataBaseHandler.getProd(5);
//        dataBaseHandler.deleteProd(deleteProd);

        for (Products products : productsList){
            Log.d("Products info: ", "ID" + products.getId() + " , Name - " + products.getName() + ", Category - " + products.getCategory());
        }
        /*
        Products products = dataBaseHandler.getProd(2);
        products.setName("Cucumber");
        products.setCategory("Vegetables");
        dataBaseHandler.updateProd(products);
        Log.d("Products info: ", "ID" + products.getId() + " , Name - " + products.getName() + ", Category - " + products.getCategory());
         */
    }
}