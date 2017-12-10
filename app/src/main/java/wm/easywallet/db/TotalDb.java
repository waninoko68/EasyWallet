package wm.easywallet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/12/10.
 */


public class TotalDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "total.db";
    private static final int DAtABASE_VERSION = 1;

    public static final String TABLE_NAME = "totalMoney";
    public static final String COL_ID = "_id";
    public static final String COL_TOTAL = "money";

    private static final String CREATE_TABLE = "create table "+TABLE_NAME+"(" +
            COL_ID+" integer primary key autoincrement," +
            COL_TOTAL+" integer," +
            ")";

    public TotalDb(Context context){
        super(context,DATABASE_NAME,null,DAtABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        ContentValues cv = new ContentValues();
        cv.put(COL_TOTAL,0);
        db.insert(TABLE_NAME,null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
}
