package wm.easywallet.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import wm.easywallet.R;
import wm.easywallet.model.Item;

/**
 * Created by Administrator on 2017/12/10.
 */

public class Adapter extends ArrayAdapter<Item>{

    private Context context;
    private int layoutResId;
    ArrayList<Item> itemList;

    public Adapter(@NonNull Context context, int layoutResId, @NonNull ArrayList<Item> itemList) {
        super(context, layoutResId, itemList);

        this.context = context;
        this.layoutResId = layoutResId;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemLayout = inflater.inflate(layoutResId,null,false);

        Item item = itemList.get(position);

        ImageView imgView = itemLayout.findViewById(R.id.iconPicItem);
        TextView text = itemLayout.findViewById(R.id.textItem);
        TextView money = itemLayout.findViewById(R.id.money);

        text.setText(item.text);
        money.setText(String.valueOf(item.money));

        String picName = item.picture;
        Drawable drawable = item.getPictureDrawable(context);
        imgView.setImageDrawable(drawable);

        return itemLayout;
    }
}
