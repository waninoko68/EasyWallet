package wm.easywallet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import wm.easywallet.db.Db;

public class MoneyIncre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_incre);
    }

    public void totalIncre(View v)
    {
        Db helper = new Db(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        EditText text = findViewById(R.id.detail);
        EditText in = findViewById(R.id.income);
        cv.put(Db.COL_TEXT,text.getText().toString());
        cv.put(Db.COL_PICTURE,"ic_expense.png");
        cv.put(Db.COL_TOTAL,in.getText().toString());
        cv.put(Db.COL_EXPRESS,"+");
        db.insert(Db.TABLE_NAME,null,cv);

        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();

    }


}
