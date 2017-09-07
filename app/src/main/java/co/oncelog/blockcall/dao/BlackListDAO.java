package co.oncelog.blockcall.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.oncelog.blockcall.manager.DataBaseHelper;

/**
 * Created by son_g on 9/7/2017.
 */

public class BlackListDAO {
    private static SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public BlackListDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
        open();
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BlackList create(final BlackList blackList) {

        final ContentValues values = new ContentValues();
        values.put("phone_number", blackList.getPhoneNumber());
        final long id = database.insert(DataBaseHelper.TABLE_BLACKLIST , null, values);
        blackList.setId(id);

        return blackList;
    }

    public void delete(final BlackList blackList) {
        database.delete(DataBaseHelper.TABLE_BLACKLIST, "phone_number = '" + blackList.getPhoneNumber() + "'", null);
    }

    public void delete(final String blackList) {
        database.delete(DataBaseHelper.TABLE_BLACKLIST, "phone_number = '" + blackList + "'", null);
    }

    public static List<BlackList> getAllBlacklist() {
        final List<BlackList> blacklistNumbers = new ArrayList<BlackList>();
        final Cursor cursor = database.query(DataBaseHelper.TABLE_BLACKLIST, new String[]{"id","phone_number"}, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final BlackList number = new BlackList();
            number.setId(cursor.getLong(0));
            number.setPhoneNumber(cursor.getString(1));
            blacklistNumbers.add(number);
            cursor.moveToNext();
        }
        return blacklistNumbers;
    }
}
