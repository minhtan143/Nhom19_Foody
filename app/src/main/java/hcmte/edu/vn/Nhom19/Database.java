package hcmte.edu.vn.Nhom19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public List<QuanAn> GetQuanAn(String maTinhThanh) {
        List<QuanAn> listQuanAn = new ArrayList<>();
        return listQuanAn;
    }

    public List<TinhThanh> GetTinhThanh() {
        List<TinhThanh> listQuanAn = new ArrayList<>();
        return listQuanAn;
    }

    public List<ThucDon> GetThucDon(String maQuanAn) {
        List<ThucDon> listQuanAn = new ArrayList<>();
        return listQuanAn;
    }

    public List<Wifi> GetWifi(String maQuanAn) {
        List<Wifi> listQuanAn = new ArrayList<>();
        return listQuanAn;
    }

    public void InsertWifi(String maQuanAn, Wifi wifi) {

    }
}

class QuanAn {
    private String maQuanAn;
    private String tenQuanAn;
    private String diaChi;
    private String gioMoCua;
    private String gioDongCua;
    private Bitmap hinhAnh;
    private String maTinh;

    public QuanAn(String maQuanAn, String tenQuanAn, String diaChi, String gioMoCua, String gioDongCua, Bitmap hinhAnh, String maTinh) {
        this.maQuanAn = maQuanAn;
        this.tenQuanAn = tenQuanAn;
        this.diaChi = diaChi;
        this.gioMoCua = gioMoCua;
        this.gioDongCua = gioDongCua;
        this.hinhAnh = hinhAnh;
        this.maTinh = maTinh;
    }

    public String getMaQuanAn() {
        return maQuanAn;
    }

    public void setMaQuanAn(String maQuanAn) {
        this.maQuanAn = maQuanAn;
    }

    public String getTenQuanAn() {
        return tenQuanAn;
    }

    public void setTenQuanAn(String tenQuanAn) {
        this.tenQuanAn = tenQuanAn;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioMoCua() {
        return gioMoCua;
    }

    public void setGioMoCua(String gioMoCua) {
        this.gioMoCua = gioMoCua;
    }

    public String getGioDongCua() {
        return gioDongCua;
    }

    public void setGioDongCua(String gioDongCua) {
        this.gioDongCua = gioDongCua;
    }

    public Bitmap getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Bitmap hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }
}

class ThucDon {
    private String maThucDon;
    private String tenMonAn;
    private int gia;
    private Bitmap hinhAnh;
    private String maQuanAn;

    public ThucDon(String maThucDon, String tenMonAn, int gia, Bitmap hinhAnh, String maQuanAn) {
        this.maThucDon = maThucDon;
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.maQuanAn = maQuanAn;
    }

    public String getMaThucDon() {
        return maThucDon;
    }

    public void setMaThucDon(String maThucDon) {
        this.maThucDon = maThucDon;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public Bitmap getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Bitmap hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaQuanAn() {
        return maQuanAn;
    }

    public void setMaQuanAn(String maQuanAn) {
        this.maQuanAn = maQuanAn;
    }
}

class TinhThanh {
    private String maTinh;
    private String tenTinh;

    public TinhThanh(String maTinh, String tenTinh) {
        this.maTinh = maTinh;
        this.tenTinh = tenTinh;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
}

class Wifi {
    private String maWifi;
    private String tenWifi;
    private String matKhau;
    private String maQuanAn;

    public Wifi(String maWifi, String tenWifi, String matKhau, String maQuanAn) {
        this.maWifi = maWifi;
        this.tenWifi = tenWifi;
        this.matKhau = matKhau;
        this.maQuanAn = maQuanAn;
    }

    public String getMaWifi() {
        return maWifi;
    }

    public void setMaWifi(String maWifi) {
        this.maWifi = maWifi;
    }

    public String getTenWifi() {
        return tenWifi;
    }

    public void setTenWifi(String tenWifi) {
        this.tenWifi = tenWifi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaQuanAn() {
        return maQuanAn;
    }

    public void setMaQuanAn(String maQuanAn) {
        this.maQuanAn = maQuanAn;
    }
}


