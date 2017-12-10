package wm.easywallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import wm.easywallet.adapter.Adapter;
import wm.easywallet.db.Db;
import wm.easywallet.model.Item;

public class MainActivity extends AppCompatActivity {

    private Db helper;
    //private TotalDb totalHelper;
    private SQLiteDatabase db;
    //private SQLiteDatabase totalDb;
    private ArrayList<Item> ItemList = new ArrayList<>();
    Adapter adapter;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new Db(this);
        db = helper.getWritableDatabase();
        //totalHelper = new TotalDb(this);
        //totalDb = totalHelper.getWritableDatabase();
       

        loadDb();
        adapter = new Adapter(this,R.layout.item,ItemList);
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                Item item = ItemList.get(position);
                String text = item.text;
                String money = String.valueOf(item.money);
                dialog.setMessage("ยืนยันลบรายการ\""+text+money+"บาท\"?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Item item = ItemList.get(position);
                        int id = item.id;
                        db.delete(Db.TABLE_NAME,Db.COL_ID+"=?",new String[]{String.valueOf(id)});
                        loadDb();
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                return true;
            }
        });

        TextView totalMoney = findViewById(R.id.totalMoney);
        Cursor cursor = db.query(
                Db.TABLE_NAME,
                null, //crt+p to find what null is mean
                null,
                null,
                null,
                null,
                null
        );
        int total = 0;
        while(cursor.moveToNext())
        {
            String operand = cursor.getString(cursor.getColumnIndex(Db.COL_EXPRESS));
            if(operand.equals("+"))
            {
                total+=cursor.getInt(cursor.getColumnIndex(Db.COL_TOTAL));
            }
            else if(operand.equals("-"))
            {
                total-=cursor.getInt(cursor.getColumnIndex(Db.COL_TOTAL));
            }
        }
        totalMoney.setText(String.valueOf("คงเหลือ "+total+" บาท"));
    }

    public void loadDb() {
        Cursor cursor = db.query(
                Db.TABLE_NAME,
                null, //crt+p to find what null is mean
                null,
                null,
                null,
                null,
                null
        );

        ItemList.clear();

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(Db.COL_ID));
            String text = cursor.getString(cursor.getColumnIndex(Db.COL_TEXT));
            int total = cursor.getInt(cursor.getColumnIndex(Db.COL_TOTAL));
            String picture = cursor.getString(cursor.getColumnIndex(Db.COL_PICTURE));
            String operand = cursor.getString(cursor.getColumnIndex(Db.COL_EXPRESS));
            Item item = new Item(id,text,total,picture, operand);
            ItemList.add(item);
        }
    }

    public void moneyIncre(View v)
    {
        Intent in = new Intent(this,MoneyIncre.class);
        startActivityForResult(in,123);
    }

    public void moneyDecre(View v)
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123)
        {
            if(resultCode==RESULT_OK)
            {
                loadDb();
                adapter.notifyDataSetChanged();
                TextView totalMoney = findViewById(R.id.totalMoney);
                Cursor cursor = db.query(
                        Db.TABLE_NAME,
                        null, //crt+p to find what null is mean
                        null,
                        null,
                        null,
                        null,
                        null
                );
                int total = 0;
                while(cursor.moveToNext())
                {
                    String operand = cursor.getString(cursor.getColumnIndex(Db.COL_EXPRESS));
                    if(operand.equals("+"))
                    {
                        total+=cursor.getInt(cursor.getColumnIndex(Db.COL_TOTAL));
                    }
                    else if(operand.equals("-"))
                    {
                        total-=cursor.getInt(cursor.getColumnIndex(Db.COL_TOTAL));
                    }
                }
                totalMoney.setText(String.valueOf("คงเหลือ "+total+" บาท"));
            }
        }
    }
}
