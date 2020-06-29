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

    private void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    private Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public List<QuanAn> GetQuanAn() {
        List<QuanAn> listQuanAn = new ArrayList<>();

        Cursor cursor = GetData("SELECT * FROM QuanAn");
        while (cursor.moveToNext()) {
            listQuanAn.add(new QuanAn(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getBlob(5),
                    cursor.getInt(6)
            ));
        }

        return listQuanAn;
    }

    public List<QuanAn> GetQuanAn(int maTinhThanh) {
        List<QuanAn> listQuanAn = new ArrayList<>();

        Cursor cursor = GetData("SELECT * FROM QuanAn WHERE MaTinh = " + maTinhThanh);
        while (cursor.moveToNext()) {
            listQuanAn.add(new QuanAn(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getBlob(5),
                    cursor.getInt(6)
            ));
        }

        return listQuanAn;
    }

    public QuanAn GetQuanAnByID(int maQuanAn) {

        Cursor cursor = GetData("SELECT * FROM QuanAn WHERE MaQuanAn = " + maQuanAn);
        cursor.moveToNext();
        return new QuanAn(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getBlob(5),
                cursor.getInt(6)
        );
    }

    public List<TinhThanh> GetTinhThanh() {
        List<TinhThanh> listTinhThanh = new ArrayList<>();

        Cursor cursor = GetData("SELECT * FROM TinhThanh");
        while (cursor.moveToNext()) {
            listTinhThanh.add(new TinhThanh(
                    cursor.getInt(0),
                    cursor.getString(1)
            ));
        }

        return listTinhThanh;
    }

    public TinhThanh GetTinhThanhByID(int maTinhThanh) {
        Cursor cursor = GetData("SELECT * FROM TinhThanh WHERE MaTinh = " + maTinhThanh);
        cursor.moveToNext();
        return new TinhThanh(cursor.getInt(0), cursor.getString(1));
    }

    public List<ThucDon> GetThucDon(int maQuanAn) {
        List<ThucDon> listThucDon = new ArrayList<>();

        Cursor cursor = GetData("SELECT * FROM ThucDon WHERE MaQuanAn = " + maQuanAn);
        while (cursor.moveToNext()) {
            listThucDon.add(new ThucDon(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getBlob(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
            ));
        }

        return listThucDon;
    }

    public List<Wifi> GetWifi(int maQuanAn) {
        List<Wifi> listWifi = new ArrayList<>();

        Cursor cursor = GetData("SELECT * FROM Wifi WHERE MaQuanAn = " + maQuanAn);
        while (cursor.moveToNext()) {
            listWifi.add(new Wifi(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            ));
        }

        return listWifi;
    }

    public void InsertWifi(Wifi wifi) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Wifi VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, wifi.getTenWifi());
        statement.bindString(2, wifi.getMatKhau());
        statement.bindString(3, Integer.toString(wifi.getMaQuanAn()));

        statement.executeInsert();
    }
}

class QuanAn {
    private int maQuanAn;
    private String tenQuanAn;
    private String diaChi;
    private String gioMoCua;
    private String gioDongCua;
    private byte[] hinhAnh;
    private int maTinh;

    public QuanAn(int maQuanAn, String tenQuanAn, String diaChi, String gioMoCua, String gioDongCua, byte[] hinhAnh, int maTinh) {
        this.maQuanAn = maQuanAn;
        this.tenQuanAn = tenQuanAn;
        this.diaChi = diaChi;
        this.gioMoCua = gioMoCua;
        this.gioDongCua = gioDongCua;
        this.hinhAnh = hinhAnh;
        this.maTinh = maTinh;
    }

    public int getMaQuanAn() {
        return maQuanAn;
    }

    public void setMaQuanAn(int maQuanAn) {
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

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(int maTinh) {
        this.maTinh = maTinh;
    }
}

class ThucDon {
    private int maThucDon;
    private String tenMonAn;
    private int gia;
    private byte[] hinhAnh;
    private int maLoai;
    private int maQuanAn;

    public ThucDon(int maThucDon, String tenMonAn, int gia, byte[] hinhAnh, int maLoai, int maQuanAn) {
        this.maThucDon = maThucDon;
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.maLoai = maLoai;
        this.maQuanAn = maQuanAn;
    }

    public int getMaThucDon() {
        return maThucDon;
    }

    public void setMaThucDon(int maThucDon) {
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

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaQuanAn() {
        return maQuanAn;
    }

    public void setMaQuanAn(int maQuanAn) {
        this.maQuanAn = maQuanAn;
    }
}

class TinhThanh {
    private int maTinh;
    private String tenTinh;

    public TinhThanh(int maTinh, String tenTinh) {
        this.maTinh = maTinh;
        this.tenTinh = tenTinh;
    }

    public int getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(int maTinh) {
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
    private int maWifi;
    private String tenWifi;
    private String matKhau;
    private int maQuanAn;

    public Wifi(int maWifi, String tenWifi, String matKhau, int maQuanAn) {
        this.maWifi = maWifi;
        this.tenWifi = tenWifi;
        this.matKhau = matKhau;
        this.maQuanAn = maQuanAn;
    }

    public int getMaWifi() {
        return maWifi;
    }

    public void setMaWifi(int maWifi) {
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

    public int getMaQuanAn() {
        return maQuanAn;
    }

    public void setMaQuanAn(int maQuanAn) {
        this.maQuanAn = maQuanAn;
    }
}

class LoaiDoAn {
    private int maLoai;
    private String tenLoai;

    public LoaiDoAn(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}


