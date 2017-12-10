package wm.easywallet.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/12/10.
 */

public class Item {
    public final int id;
    public final String picture;
    public final int money;
    public final String text;
    public final String operand;

    public Item(int id, String picture, int money, String text, String operand) {
        this.id = id;
        this.picture = picture;
        this.money = money;
        this.text = text;
        this.operand = operand;
    }

    public Drawable getPictureDrawable(Context context)
    {
        AssetManager am = context.getAssets();
        try {
            InputStream stream = am.open(picture);
            Drawable drawable = Drawable.createFromStream(stream,"");
            return drawable;
        } catch (IOException e) {
            Log.e("Animal ListAdapter","Error opening file "+picture);
            e.printStackTrace();
            return null;
        }
    }
}
