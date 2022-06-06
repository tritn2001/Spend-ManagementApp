package fpoly.edu.vn.qltcda1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import fpoly.edu.vn.qltcda1.Adapter.KhoanChiAdapter;
import fpoly.edu.vn.qltcda1.Adapter.LoaiChiSpinnerAdapter;
import fpoly.edu.vn.qltcda1.DAO.KhoanChiDAO;
import fpoly.edu.vn.qltcda1.DAO.KhoanThuDAO;
import fpoly.edu.vn.qltcda1.DAO.LoaiKhoanChiDAO;
import fpoly.edu.vn.qltcda1.DAO.LoaiKhoanThuDAO;
import fpoly.edu.vn.qltcda1.R;
import fpoly.edu.vn.qltcda1.model.KhoanChi;
import fpoly.edu.vn.qltcda1.model.LoaiChi;


public class ChiFragment extends Base {

    ListView lv;
    ArrayList<KhoanChi> listkhoanchi;
    FloatingActionButton fabKhoanChi;
    Dialog dialog;
    EditText edIdKhoanchi, edNameKhoanChi,edTienKhoanChi;
    TextView tvNgaychi;
    Spinner spinner;
    Button btnSave, btnCancel;
    KhoanChiDAO dao;
    KhoanChiAdapter adapter;
    KhoanChi item;
    LoaiChiSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiChi>listLoaiChi;
    LoaiKhoanChiDAO loaiKhoanChiDAO;
    LoaiChi loaiChi;
    KhoanThuDAO khoanThuDAO;
    int maLoaiChi;
    int position;
    private Button btnToDay,btnMonth,btnYear;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            // Inflate the layout for this fragment
            View v= inflater.inflate(R.layout.fragment_chi, container, false);
            lv = v.findViewById(R.id.lvKhoanChi);
            fabKhoanChi = v.findViewById(R.id.fabKhoanChi);
            dao= new KhoanChiDAO(getActivity());
            capnhatLv();
            animation();
            btnToDay= v.findViewById(R.id.btnToDay);
            btnMonth= v.findViewById(R.id.btnMonth);
            btnYear= v.findViewById(R.id.btnYear);
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
            fabKhoanChi.setOnClickListener(v1 -> {
                        dao = new KhoanChiDAO(getContext());
                        khoanThuDAO = new KhoanThuDAO(getContext());
                        if(dao.getTongChi()>=khoanThuDAO.getTongThu()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Cảnh báo khoản chi vượt quá khoản thu");
                            builder.setMessage("Bạn Có Muốn Tiếp Tục Không ? ");
                            builder.setCancelable(true);
                            builder.setPositiveButton("Có", (dialog, which) -> {
                                openDialog(getActivity(), 0);
                            });
                            builder.setNegativeButton("Không", (dialog, which) -> dialog.cancel());
                            AlertDialog alert = builder.create();
                            builder.show();
                        } else{
                            openDialog(getActivity(), 0);
                        }
                    });

            lv.setOnItemLongClickListener((parent, view, position, id) -> {
                item = listkhoanchi.get(position);
                openDialog(getActivity(),1);
                return false;
            });
            return v;
        }

        public void openDialog( final Context context, final int type){
            dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            dialog.setContentView(R.layout.dialog_them_khoan_chi);
            edIdKhoanchi = dialog.findViewById(R.id.edIdKhoanChi);
            spinner=dialog.findViewById(R.id.spnerKhoanChi);
            tvNgaychi=dialog.findViewById(R.id.tvngayKhoanChi);

            edNameKhoanChi= dialog.findViewById(R.id.edNameKhoanChi);
            edTienKhoanChi = dialog.findViewById(R.id.edTienKhoanChi);

            btnSave = dialog.findViewById(R.id.btnSaveKhoanChi);
            btnCancel = dialog.findViewById(R.id.btnCancelKhoanChi);

            tvNgaychi.setText("Ngày chi : "+sdf.format(new Date()));

            listLoaiChi = new ArrayList<LoaiChi>();
            loaiKhoanChiDAO = new LoaiKhoanChiDAO(context);
            listLoaiChi= (ArrayList<LoaiChi>)loaiKhoanChiDAO.getAll();
            spinnerAdapter = new LoaiChiSpinnerAdapter(context,listLoaiChi);
            spinner.setAdapter(spinnerAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    maLoaiChi = listLoaiChi.get(position).maLoaiChi;
                    Toast.makeText(context,"chon " +listLoaiChi.get(position).tenLoaiChi,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            edIdKhoanchi.setEnabled(false);

            if (type != 0){
               edIdKhoanchi.setText(String.valueOf(item.maKhoanChi));
               edNameKhoanChi.setText(item.tenKhoanChi);
               edTienKhoanChi.setText(String.valueOf(item.soTienKhoanchi));
                for (int i = 0; i < listLoaiChi.size();i++)
                    if (item.maLoaiChi == (listLoaiChi.get(i).maLoaiChi)){
                        position = i;
                    }

                Log.i("demo","posLoaichi"+position);
                spinner.setSelection(position);
            }

            btnCancel.setOnClickListener(v -> dialog.dismiss());
            btnSave.setOnClickListener(v -> {
                try {

                item = new KhoanChi();
                item.setTenKhoanChi(edNameKhoanChi.getText().toString());
                item.setMaLoaiChi(maLoaiChi);
                item.setNgayChi(new Date());
                item.setSoTienKhoanchi(Integer.parseInt(edTienKhoanChi.getText().toString()));

                if (validate()>0){
                    if (type == 0){

                        if (dao.insert(item)>0){
                            showMsgF("Lưu Thành Công");
                        }else {
                            showMsgF("Lưu Thất Bại");

                        }

                    }else {
                        item.maKhoanChi =Integer.parseInt(edIdKhoanchi.getText().toString());
                        if (dao.update(item)>0){
                            showMsgF("Sửa Thành Công");
                        }else {
                            showMsgF("Sửa Thất Bại");
                        }

                    }
                    capnhatLvDay();
                    dialog.dismiss();
                }


            }catch (Exception e){
                    Toast.makeText(getContext(), "Bạn chưa nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                }
                    }
                );
            dialog.show();
        }

        //==================================//

        void capnhatLv() {
            listkhoanchi = (ArrayList<KhoanChi>)dao.getAll();
            adapter = new KhoanChiAdapter(getActivity(),this,listkhoanchi);
            lv.setAdapter(adapter);
            Collections.reverse (listkhoanchi);
        }
    void capnhatLvDay() {
        listkhoanchi = (ArrayList<KhoanChi>)dao.getAllDay();
        adapter = new KhoanChiAdapter(getActivity(),this,listkhoanchi);
        lv.setAdapter(adapter);
        Collections.reverse (listkhoanchi);
    }
    void capnhatLvMonth() {
        listkhoanchi = (ArrayList<KhoanChi>)dao.getAllMonth();
        adapter = new KhoanChiAdapter(getActivity(),this,listkhoanchi);
        lv.setAdapter(adapter);
        Collections.reverse (listkhoanchi);
    }
    void capnhatLvYear() {
        listkhoanchi = (ArrayList<KhoanChi>)dao.getAllYear();
        adapter = new KhoanChiAdapter(getActivity(),this,listkhoanchi);
        lv.setAdapter(adapter);
        Collections.reverse (listkhoanchi);
    }

        public void xoa( final String Id){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Xóa");
            builder.setMessage("Bạn Có Muốn Xóa Không ? ");
            builder.setCancelable(true);
            builder.setPositiveButton("Có", (dialog, which) -> {
                dao.delete(Id);
                capnhatLv();
                dialog.cancel();
            });
            builder.setNegativeButton("Không", (dialog, which) -> dialog.cancel());
            AlertDialog alert = builder.create();
            builder.show();

        }

        public int validate() {
            int check = 1;
            if (edNameKhoanChi.getText().length() == 0||edTienKhoanChi.getText().length()==0) {
                showMsgF("Không Được Để Trống");
                check = -1;
            }
            return check;
        }
    public void animation(){
        Animation animationHour2 = AnimationUtils.loadAnimation(getActivity(),R.anim.iconxoaxoay);
        fabKhoanChi.startAnimation(animationHour2);
    }
}