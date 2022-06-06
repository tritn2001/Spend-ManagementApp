package fpoly.edu.vn.qltcda1.database;

public class SQLiteData {

    public static final String INSERT_NGUOI_DUNG = "Insert into NguoiDung(maNguoidung,HoTen,MatKhau) values " +
            "('admin','nhóm 2','123'),"+
            "('tri','Trí','123'),"+
            "('nhatanh','HN.Anh','123'),"+
            "('duc','X.Đức','123'),"+
            "('dat','Q.Đạt','123'),"+
            "('ducanh','ND.Anh','123')";
   public static final String  INSERT_LOAI_THU="Insert into LoaiThu(maLoaiThu,tenLoaiThu) values " +
          "('1','Lương'),"+
           "('2','Lãi Bitcoin'),"+
           "('3','Tiền thưởng'),"+
           "('4','Cổ tức'),"+
            "('5','Cho thuê'),"+
            "('6',' bán hàng')";




    public static final String INSERT_LOAI_CHI="Insert into LoaiChi(maLoaiChi,tenLoaiChi) values"+
            "('1','Ăn uống'),"+
            "('2','Di chuyển'),"+
            "('3','Mua sắm'),"+
            "('4','Giáo dục'),"+
            "('5','Thể thao'),"+
            "('6','Thuế'),"+
            "('7','Sức khỏe'),"+
            "('8','Quà'),"+
            "('9','Nhà cửa'),"+
            "('10','Du Lich')";

    public static final String INSERT_KHOAN_CHI="Insert into KhoanChi(maKhoanChi,tenKhoanChi,ngayChi,sotienKhoanChi,maLoaiChi) values"+
            "('1','Ăn Cơm gà','2021-12-08','3000','1'),"+
         "('2','Ăn Cháo','2021-12-08','2000','1'),"+
           "('3','Nhậu sambaidj','2021-11-09','90000','2')";
    public static final String INSERT_KHOAN_THU="Insert into KhoanThu(maKhoanThu,tenKhoanThu,ngayThu,sotienKhoanThu,maLoaiThu) values"+
            "('1','Lương công ty','2021-12-08','3000','1'),"+
            "('2','Lãi bitcoin','2021-12-08','2000','1'),"+
            "('3','Lãi bán hàng','2021-11-09','90000','2')";



        //1 avata
        // 2 useName
        // 3 icon bottom
        //dd-MM-yyyy
        //android:layout_margin="40dp"
}
