package wm.easywallet.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/12/10.
 */

public class Db extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "money.db";
    private static final int DAtABASE_VERSION = 1;

    public static final String TABLE_NAME = "money";
    public static final String COL_ID = "_id";
    public static final String COL_TEXT = "text";
    public static final String COL_TOTAL = "total";
    public static final String COL_PICTURE = "picture";
    public static final String COL_EXPRESS = "operand";

    private static final String CREATE_TABLE = "create table "+TABLE_NAME+"(" +
            COL_ID+" integer primary key autoincrement," +
            COL_TEXT+" text," +
            COL_TOTAL+" integer," +
            COL_PICTURE+" text"+
            COL_EXPRESS+" text"+
            ")";

    public Db(Context context){
        super(context,DATABASE_NAME,null,DAtABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
}
