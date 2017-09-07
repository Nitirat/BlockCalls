package co.oncelog.blockcall.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by son_g on 9/7/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "call_blocker.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_BLACKLIST = "blacklist";
    private static final String TABLE_CREATE = "create table "  + TABLE_BLACKLIST + "( id "
            + " integer primary key autoincrement, phone_number  text not null);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
