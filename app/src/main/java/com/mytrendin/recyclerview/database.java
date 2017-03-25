package com.mytrendin.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shree on 3/25/2017.
 */

public class database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Contacts.database";
    private static final String TABLE_NAME = "Contacts";
    private static final String COLUMN_NAME = "contact_name";
    private static final String COLUMN_NUMBER = "contact_number";
    private Boolean exists = false;
    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //execute the create table query
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " TEXT " +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        onCreate(sqLiteDatabase);
    }

    //add row to database
    public void addContact( String ContactNumber, String ContactName){
        ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, ContactName);
            values.put(COLUMN_NUMBER, ContactNumber);
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_NAME, null, values);
            db.close();
            exists = true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase(); //create/open db for reading/writing
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);  //runs SQL query and returns cursor over result
        /*the second paramenter to rawQuery is cancellableSignal which signifies that what
        needs to be done when the operation is cancelled...
        in this case null signifies nothing...*/

        /*Cursor: A Cursor implementation exposes results from a query on a SQLiteDatabase*/
        return res;
    }

    public int tableExists(){
      //  boolean isTableExists(SQLiteDatabase db, String tableName)
       SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_NAME,null);
            int count = cursor.getCount();
            cursor.close();
            return count;

    }
}

