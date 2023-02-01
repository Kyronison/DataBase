package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Products;
import Utils.Util;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + "INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + "TEXT, "
                + Util.KEY_CATEGORY + "TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void addProd(Products products){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, products.getName());
        contentValues.put(Util.KEY_CATEGORY, products.getCategory());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }
    public Products getProd(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID,Util.KEY_NAME,Util.KEY_CATEGORY},
                Util.KEY_ID + "=?", new String[] {String.valueOf(id)},null,null,null);
        if (cursor!= null){
            cursor.moveToFirst();
        }
        Products products = new Products(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return products;
    }
    public List<Products> getAllProd () {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Products> prodList = new ArrayList<>();
        String selectALlProd = "Select * from " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectALlProd,null);
        if (cursor.moveToFirst()){
            do {
                Products products = new Products();
                products.setId(Integer.parseInt(cursor.getString(0)));
                products.setName(cursor.getString(1));
                products.setCategory(cursor.getString(2));

                prodList.add(products);
            } while (cursor.moveToNext());
        }
        return prodList;
    }
}
