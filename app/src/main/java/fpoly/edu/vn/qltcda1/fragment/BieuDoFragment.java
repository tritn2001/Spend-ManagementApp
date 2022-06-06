package fpoly.edu.vn.qltcda1.fragment;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.edu.vn.qltcda1.Adapter.TopChiAdapter;
import fpoly.edu.vn.qltcda1.Adapter.TopThuAdapter;
import fpoly.edu.vn.qltcda1.DAO.KhoanChiDAO;
import fpoly.edu.vn.qltcda1.DAO.KhoanThuDAO;
import fpoly.edu.vn.qltcda1.R;
import fpoly.edu.vn.qltcda1.model.TopKhoanChi;
import fpoly.edu.vn.qltcda1.model.TopKhoanThu;


public class BieuDoFragment extends Fragment    {
    TextView tvtongthungay,tvtongchingay;
    KhoanChiDAO khoanChiDAO;
    KhoanThuDAO khoanThuDAO;
    PieChart pieChart;

    ListView lv1,lv2;
    ArrayList<TopKhoanThu> list;
    TopThuAdapter adapter;

    ArrayList<TopKhoanChi> list1;
    TopChiAdapter adapter1;
    int tongchingay = 0;
    int tongthungay = 0;
    EditText edMonth;
    Button btnSearch;
    Calendar calendar = Calendar.getInstance();
    String Ngayhientai;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bieu_do, container, false);
        khoanChiDAO= new KhoanChiDAO(getActivity());
        khoanThuDAO=new KhoanThuDAO(getActivity());
        edMonth= v.findViewById(R.id.edThang);
        btnSearch=v.findViewById(R.id.Search_month);
        getThu(sdf2.format(new Date()));
        getChi(sdf2.format(new Date()));
        initControls(v);
        initEvents();
        topthu(sdf2.format(new Date()));
        topChi(sdf2.format(new Date()));
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();
                initEvents();


            }
        });
        return v;

    }
    private void Search(){
   try {
       if (Integer.parseInt(edMonth.getText().toString()) > 12 ||
               Integer.parseInt(edMonth.getText().toString()) < 0) {
           Toast.makeText(getContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
       }else{
       String month= edMonth.getText().toString();
       getChi(month);
       getThu(month);
       topChi(month);
       topthu(month);
       }
   }catch (Exception ex){
       Toast.makeText(getContext(), "Lỗi nhập không đúng kí tự", Toast.LENGTH_SHORT).show();

   }
    }

    private void topChi(String month) {
        KhoanChiDAO khoanChiDAO =  new KhoanChiDAO(getActivity());
        list1 = (ArrayList<TopKhoanChi>)khoanChiDAO.getTopKCThang(month);
        adapter1= new TopChiAdapter(getActivity(),this,list1);
        lv1.setAdapter(adapter1);
    }

    private void topthu(String month) {

        KhoanThuDAO khoanThuDAO =  new KhoanThuDAO(getActivity());
        list = (ArrayList<TopKhoanThu>)khoanThuDAO.getTopKTThang(month);
        adapter= new TopThuAdapter(getActivity(),this,list);
        lv2.setAdapter(adapter);
    }

    private void initEvents() {
        Ngayhientai();
        pieChart();
        tvtongchingay.setText("Tổng chi: "+FormatCost(tongchingay)+" VND");
        tvtongthungay.setText("Tổng Thu: "+FormatCost(tongthungay)+" VND");

    }

    private void initControls(View v) {
        lv2=v.findViewById(R.id.lv2);
        lv1=v.findViewById(R.id.lv1);
        tvtongthungay =  v.findViewById(R.id.tvtongthungay);
        tvtongchingay = v .findViewById(R.id.tvtongchingay);
        pieChart = v.findViewById(R.id.piechartNgay);
    }

    public String FormatCost(long cost){
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat  = new DecimalFormat("###,###,###,###",symbols);
            return decimalFormat.format(Integer.parseInt(cost+""));
        }catch (Exception e){
            return cost + "";
        }
    }

    private void getChi(String month) {
        tongchingay = (int) khoanChiDAO.getTongChiThang(month);
    }

    private void getThu(String month) {
        tongthungay = (int) khoanThuDAO.getTongThuThang(month);
    }

    private void pieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,5,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yvalue = new ArrayList<>();
        yvalue.clear();
        yvalue.add(new PieEntry(tongchingay,"Tổng chi"));
        yvalue.add(new PieEntry(tongthungay,"Tổng Thu"));
        PieDataSet dataSet  = new PieDataSet(yvalue," ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data =new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLUE);

        pieChart.setData(data);
    }

    private void Ngayhientai() {
        int mYear  = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        Ngayhientai = mDay+"-"+(mMonth+1)+"-"+mYear;

        Date date = null;
        try {
            date = sdf.parse(Ngayhientai);
            Ngayhientai = sdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}