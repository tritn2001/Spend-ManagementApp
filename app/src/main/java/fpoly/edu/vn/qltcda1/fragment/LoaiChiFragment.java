package fpoly.edu.vn.qltcda1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

import fpoly.edu.vn.qltcda1.Adapter.LoaiChiAdapter;
import fpoly.edu.vn.qltcda1.DAO.LoaiKhoanChiDAO;
import fpoly.edu.vn.qltcda1.R;
import fpoly.edu.vn.qltcda1.model.LoaiChi;


public class LoaiChiFragment extends Base {
    ListView lv;
    ArrayList<LoaiChi> listLC;
    FloatingActionButton fab_LoaiChi;
    Dialog dialog;
    EditText edIdLoaiChi,edNameLoaiChi;
    Button btnSave,btnCancel;
    static LoaiKhoanChiDAO dao;
    LoaiChiAdapter adapter;
    LoaiChi item;
    TextView tvTitle;
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_chi, container, false);
        tvTitle = v.findViewById(R.id.tv_title);
        lv = v.findViewById(R.id.lvLoaiChi);
        fab_LoaiChi = v.findViewById(R.id.fab_LoaiChi);
        fab_LoaiChi.setOnClickListener(v1 -> openDialog(getActivity(), 0));

        dao = new LoaiKhoanChiDAO(getActivity());
        capNhatLv();
        animation();
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            item = listLC.get(position);
            openDialog(getActivity(), 1);
            return false;
        });
        return v;
    }
    private void setTitleToolbar() {
        tvTitle.setText("Lo???i Chi");

    }
    private void openDialog(Context context, int type) {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_loai_chi);
        edIdLoaiChi = dialog.findViewById(R.id.edIdLoaiChi);
        edNameLoaiChi = dialog.findViewById(R.id.edNameLoaiChi);
        btnSave = dialog.findViewById(R.id.btnSaveLC);
        btnCancel = dialog.findViewById(R.id.btnCancelLC);
        edIdLoaiChi.setEnabled(false);


        if (type != 0) {
            edIdLoaiChi.setText(String.valueOf(item.maLoaiChi));
            edNameLoaiChi.setText(item.tenLoaiChi);
        }

        // rs ten loai s??ch
        edIdLoaiChi.setOnClickListener(v -> edIdLoaiChi.setText(""));
        edNameLoaiChi.setOnClickListener(v -> edNameLoaiChi.setText(""));

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            item = new LoaiChi();
            item.tenLoaiChi = edNameLoaiChi.getText().toString();
            if (validate() >0){

                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        showMsgF("L??u Th??nh C??ng");
                    } else {
                        showMsgF("L??u Th???t B???i");
                    }

                } else {
                    item.maLoaiChi = Integer.parseInt(edIdLoaiChi.getText().toString());
                    if (dao.update(item) > 0) {
                        showMsgF("S???a Th??nh C??ng");
                    } else {
                        showMsgF("S???a Th???t B???i");
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
//============================//

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("X??a");
        builder.setMessage("B???n C?? Mu???n X??a Kh??ng ?");
        builder.setCancelable(true);
        builder.setPositiveButton("C??", (dialog, id) -> {
            dao.delete(Id);
            capNhatLv();
            dialog.cancel();
        });
        builder.setNegativeButton("Kh??ng", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv() {
        listLC = (ArrayList<LoaiChi>) dao.getAll();
        adapter = new LoaiChiAdapter(getActivity(),this,listLC);
        lv.setAdapter(adapter);
        Collections.reverse (listLC);
    }

    public int validate() {
        int check = 1;
        if (edNameLoaiChi.getText().length() == 0) {
            showMsgF("Kh??ng ???????c ????? Tr???ng");
            check = -1;
        }
        return check;
    }
    public void animation(){
        Animation animationHour2 = AnimationUtils.loadAnimation(getActivity(),R.anim.iconxoaxoay);
        fab_LoaiChi.startAnimation(animationHour2);
    }
}