package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import com.example.myapplication.Data.DataBaseHandler;
import com.example.myapplication.Model.Products;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

        //Добавляем продукты в базу данных

        dataBaseHandler.addProd(new Products("Milk","Dairy"));
        dataBaseHandler.addProd(new Products("Apple","Fruits"));
        dataBaseHandler.addProd(new Products("Onion","Vegetables"));

        //Список всех продуктов за раз
        List<Products> productsList = dataBaseHandler.getAllProd();

        //Удаляем какой-либо продукт по его id
        Products deleteProd = dataBaseHandler.getProd(5);
        dataBaseHandler.deleteProd(deleteProd);

        //Вывод списка продуктов в консоль
        for (Products products : productsList){
            Log.d("Products info: ", "ID" + products.getId() + " , Name - " + products.getName() + ", Category - " + products.getCategory());
        }

        //Смена каких-либо характеристик продукта
        Products products = dataBaseHandler.getProd(2);
        products.setName("Cucumber");
        products.setCategory("Vegetables");
        dataBaseHandler.updateProd(products);
        Log.d("Products info: ", "ID" + products.getId() + " , Name - " + products.getName() + ", Category - " + products.getCategory());
    }
}