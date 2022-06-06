package fpoly.edu.vn.qltcda1.model;

import java.util.Date;

public class MucTieuThu {
    public int soTien_muctieu;
    public Date chuKy;

    public MucTieuThu() {
    }

    public MucTieuThu(int soTien_muctieu, Date chuKy) {
        this.soTien_muctieu = soTien_muctieu;
        this.chuKy = chuKy;
    }

    public int getSoTien_muctieu() {
        return soTien_muctieu;
    }

    public void setSoTien_muctieu(int soTien_muctieu) {
        this.soTien_muctieu = soTien_muctieu;
    }

    public Date getChuKy() {
        return chuKy;
    }

    public void setChuKy(Date chuKy) {
        this.chuKy = chuKy;
    }
}
