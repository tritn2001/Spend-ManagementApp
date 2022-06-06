package fpoly.edu.vn.qltcda1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.edu.vn.qltcda1.DAO.LoaiKhoanThuDAO;
import fpoly.edu.vn.qltcda1.R;
import fpoly.edu.vn.qltcda1.fragment.LoaiThuFragment;
import fpoly.edu.vn.qltcda1.model.LoaiThu;

public class LoaiThuAdapter extends ArrayAdapter<LoaiThu> {
    private Context context;
    LoaiThuFragment fragment;
    private ArrayList<LoaiThu> lists;
    TextView tvIdLoaiThu,tvNameLoaiThu;
    ImageView imgDel,imgloaithu1;


    public LoaiThuAdapter(@NonNull  Context context, LoaiThuFragment fragment, ArrayList<LoaiThu> lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_thu_item,null);
        }
        final LoaiThu item = lists.get(position);
        if (item != null){
            LoaiKhoanThuDAO loaiKhoanThuDAO = new LoaiKhoanThuDAO(context);
            LoaiThu loaiThu = loaiKhoanThuDAO.getID(String.valueOf(item.maLoaiThu));
            tvNameLoaiThu = v.findViewById(R.id.tvNameLT);
            tvNameLoaiThu.setText(" "+item.tenLoaiThu);
            imgDel = v.findViewById(R.id.imgdeleteLoaiThu);
        }
        imgDel.setOnClickListener(v1 -> fragment.xoa(String.valueOf(item.maLoaiThu)));
        LoaiThu it = lists.get(position);
        imgloaithu1 = v.findViewById(R.id.imgLoaiThu1);
        if (String.valueOf(it.getMaLoaiThu()).equals("1")){
            imgloaithu1.setImageResource(R.drawable.luong);
        }
        if (String.valueOf(it.getMaLoaiThu()).equals("2")){
            imgloaithu1.setImageResource(R.drawable.bitcoin);
        }
        if (String.valueOf(it.getMaLoaiThu()).equals("3")){
            imgloaithu1.setImageResource(R.drawable.ic_tien_thuong);
        }
        if (String.valueOf(it.getMaLoaiThu()).equals("4")){
            imgloaithu1.setImageResource(R.drawable.cotuc);
        }
        if (String.valueOf(it.getMaLoaiThu()).equals("5")){
            imgloaithu1.setImageResource(R.drawable.ic_cho_thue);
        }
        if (String.valueOf(it.getMaLoaiThu()).equals("6")){
            imgloaithu1.setImageResource(R.drawable.banghang);
        }

        if (!String.valueOf(it.getMaLoaiThu()).equals("1") && !String.valueOf(it.getMaLoaiThu()).equals("2") && !String.valueOf(it.getMaLoaiThu()).equals("3")&& !String.valueOf(it.getMaLoaiThu()).equals("4")
                && !String.valueOf(it.getMaLoaiThu()).equals("5")&& !String.valueOf(it.getMaLoaiThu()).equals("6")){
            imgloaithu1.setImageResource(R.drawable.ic_t);
        }

        imgDel.setOnClickListener(v1 -> fragment.xoa(String.valueOf(item.maLoaiThu)));
        return v;
    }

}
