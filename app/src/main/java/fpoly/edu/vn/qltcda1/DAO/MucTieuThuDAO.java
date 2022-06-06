package fpoly.edu.vn.qltcda1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.edu.vn.qltcda1.database.SQLite;

public class MucTieuThuDAO {
    private SQLiteDatabase db;
    private Context context;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public MucTieuThuDAO(Context context) {
        this.context = context;
        SQLite dbHelper = new SQLite(context);
        db = dbHelper.getWritableDatabase();
}

}
