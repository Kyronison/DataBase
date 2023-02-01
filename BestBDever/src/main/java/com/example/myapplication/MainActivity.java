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

        dataBaseHandler.addProd(new Products("Milk","Dairy"));
        dataBaseHandler.addProd(new Products("Apple","Fruits"));
        dataBaseHandler.addProd(new Products("Onion","Vegetables"));

        List<Products> productsList = dataBaseHandler.getAllProd();

        for (Products products : productsList){
            Log.d("Products info: ", "ID" + products.getId() + " , name - " + products.getName() + ", category - " + products.getCategory());
        }
    }
}