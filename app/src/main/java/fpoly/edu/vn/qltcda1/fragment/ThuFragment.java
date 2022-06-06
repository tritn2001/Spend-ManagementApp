package fpoly.edu.vn.qltcda1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import fpoly.edu.vn.qltcda1.Adapter.KhoanChiAdapter;
import fpoly.edu.vn.qltcda1.Adapter.KhoanThuAdapter;
import fpoly.edu.vn.qltcda1.Adapter.LoaiThuSpinnerAdapter;
import fpoly.edu.vn.qltcda1.DAO.KhoanThuDAO;
import fpoly.edu.vn.qltcda1.DAO.LoaiKhoanThuDAO;
import fpoly.edu.vn.qltcda1.R;
import fpoly.edu.vn.qltcda1.model.KhoanChi;
import fpoly.edu.vn.qltcda1.model.KhoanThu;
import fpoly.edu.vn.qltcda1.model.LoaiThu;


public class ThuFragment extends  Base {
    ListView lv;
    ArrayList<KhoanThu> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText ed_makhoanthu,ed_tenkhoanthu,ed_giathu;
    TextView tv_ngayThu;
    Spinner spinner;
    Button btnSave,btnCancel;
    static KhoanThuDAO dao;
    KhoanThuAdapter adapter;
    KhoanThu item;
    LoaiThuSpinnerAdapter spinerAdapter;
    ArrayList<LoaiThu>listLoaiThu;
    LoaiKhoanThuDAO loaiKhoanThuDAO;
    LoaiThu loaiThu;
    int maLoaiThu,position;


    private Button btnToDay,btnMonth,btnYear;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_thu, container, false);

        lv = v.findViewById(R.id.lv_Khoan_Thu);
        fab = v.findViewById(R.id.fab_Khoan_Thu_1);
        dao = new KhoanThuDAO(getActivity());
        capnhatlv();
        animation();
        btnToDay= v.findViewById(R.id.btnToDay_T);
        btnMonth= v.findViewById(R.id.btnMonth_T);
        btnYear= v.findViewById(R.id.btnYear_T);
        btnMonth.setText("tháng "+sdf2.format(new Date()));
        btnYear.setText("năm "+sdf3.format(new Date()));
        btnToDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capnhatLvDay();
                if (btnToDay.isClickable()){
                    btnToDay.setTextColor(getResources().getColor(R.color.yellow));
                    btnMonth.setTextColor(getResources().getColor(R.color.white));
                    btnYear.setTextColor(getResources().getColor(R.color.white));

                }}
        });
        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capnhatLvMonth();
                if (btnMonth.isClickable()){
                    btnMonth.setTextColor(getResources().getColor(R.color.yellow));
                    btnToDay.setTextColor(getResources().getColor(R.color.white));
                    btnYear.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capnhatLvYear();
                if (btnYear.isClickable()){
                    btnYear.setTextColor(getResources().getColor(R.color.yellow));
                    btnMonth.setTextColor(getResources().getColor(R.color.white));
                    btnToDay.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
        fab.setOnClickListener(v1 -> openDialog(getActivity(),0));
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            item = list.get(position);
            openDialog(getActivity(),1);
            return false;
        });
        return v;


    }
   public void openDialog(Context context, int type){
       dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_them_khoan_thu);
      /*  Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);*/
        ed_makhoanthu = dialog.findViewById(R.id.edMa_KhoanThu_1);
        ed_tenkhoanthu = dialog.findViewById(R.id.edTen_Khoan_Thu_1);
        ed_giathu = dialog.findViewById(R.id.ed_giaTien_KhoanThu_1);

        tv_ngayThu = dialog.findViewById(R.id.tvNgay_Thu_Khoan_Thu_1);

        spinner=dialog.findViewById(R.id.spner_Khoan_Thu_1);

        btnCancel = dialog.findViewById(R.id.btnCancel_KhoanThu);
        btnSave = dialog.findViewById(R.id.btnSave_KhoanThu);

        tv_ngayThu.setText("Ngày Thu : "+sdf.format(new Date()));


        listLoaiThu = new ArrayList<LoaiThu>();
        loaiKhoanThuDAO = new LoaiKhoanThuDAO(context);
        listLoaiThu= (ArrayList<LoaiThu>)loaiKhoanThuDAO.getAll();
        spinerAdapter = new LoaiThuSpinnerAdapter(context,listLoaiThu);
        spinner.setAdapter(spinerAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiThu = listLoaiThu.get(position).maLoaiThu;
                Toast.makeText(context,"chon " +listLoaiThu.get(position).tenLoaiThu,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ed_makhoanthu.setEnabled(false);
        if (type != 0){
            ed_makhoanthu.setText(String.valueOf(item.maKhoanThu));
            ed_tenkhoanthu.setText(item.tenKhoanThu);
            ed_giathu.setText(String.valueOf(item.soTienKhoanthu));
            for (int i = 0;i<listLoaiThu.size();i++)
                if (item.maLoaiThu == (listLoaiThu.get(i).maLoaiThu)){
                    position = i;
                }

            Log.i("//==========","posKhoanThu"+position);
            spinner.setSelection(position);
        }

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            try {


            item = new KhoanThu();
            item.setTenKhoanThu( ed_tenkhoanthu.getText().toString());
            item.setMaLoaiThu(maLoaiThu);
            item.setNgayThu(new Date());
            item.setSoTienKhoanthu( Integer.parseInt(ed_giathu.getText().toString()));
            if (validate() >0){
                if (type == 0){
                    if (dao.insert(item)>0){
                        showMsgF("Lưu Thành Công");
                    }else {
                        showMsgF("Lưu Thất Bại");

                    }
                }else {
                    item.maKhoanThu =Integer.parseInt(ed_makhoanthu.getText().toString());
                    if (dao.update(item)>0){
                        showMsgF("Sửa Thành Công");
                    }else {
                        showMsgF("Sửa Thất Bại");
                    }
                }
            }

            capnhatlv();
            dialog.dismiss();

        }catch (Exception e){
           Toast.makeText(getContext(), "Bạn chưa nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
       }
        }
            );

        dialog.show();

    }

   public int validate(){
        int check = 1;
        if (ed_tenkhoanthu.getText().length()==0||ed_giathu.getText().length()==0){
            showMsgF("Không Được để Trống");
            check = -1;
        }
        return check;
    }

    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Bạn Có Muốn Xóa Không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", (dialog, which) -> {
            dao.delete(Id);
            capnhatlv();
            dialog.cancel();
        });
        builder.setNegativeButton("Không", (dialog, which) -> dialog.cancel());
        AlertDialog alert = builder.create();
        builder.show();

    }

    public void capnhatlv(){
        list=(ArrayList<KhoanThu>) dao.getAll();
        adapter = new KhoanThuAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        Collections.reverse (list);
    }
    void capnhatLvDay() {
        list = (ArrayList<KhoanThu>)dao.getAllDay();
        adapter = new KhoanThuAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        Collections.reverse (list);
    }
    void capnhatLvMonth() {
        list = (ArrayList<KhoanThu>)dao.getAllMonth();
        adapter = new KhoanThuAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        Collections.reverse (list);
    }
    void capnhatLvYear() {
        list = (ArrayList<KhoanThu>)dao.getAllYear();
        adapter = new KhoanThuAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        Collections.reverse (list);
    }
    public void animation(){
        Animation animationHour2 = AnimationUtils.loadAnimation(getActivity(),R.anim.iconxoaxoay);
        fab.startAnimation(animationHour2);
    }
}