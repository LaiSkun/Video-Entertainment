/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.entity.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDAO extends QuanLiCFDAO<NhanVien, String >{
    final String Insert_SQL= "insert into NhanVien (MaNV,TenNV,MatKhau,GioiTinh,DiaChi,SDT,VaiTro,Hinh) values (?,?,?,?,?,?,?,?)";
    final String Update_SQL="update NhanVien set TenNV=?,MatKhau=?,GioiTinh=?,SDT=?,DiaChi=?,VaiTro=?,Hinh=? where MaNV = ? ";
    final String Delete_SQL="delete from NhanVien where MaNV = ?";
    final String SelectAll_SQL ="select *from NhanVien";
    final String SelectByID_SQL ="select *from NhanVien where MaNV=?";
    @Override
    public void insert(NhanVien entity) {
       DatabaseConnection.update(Insert_SQL,entity.getMaNV(),entity.getTenNV(),entity.getMatKhau(),entity.getGioiTinh(),entity.getDiaChi(),entity.getSDT(),entity.getVaiTro(),entity.getHinh() );
    }

    @Override
    public void update(NhanVien entity) {
        DatabaseConnection.update(Update_SQL, entity.getTenNV(),entity.getMatKhau(),entity.getGioiTinh(),entity.getSDT(),entity.getDiaChi(),entity.getVaiTro(),entity.getHinh(),entity.getMaNV());
    }

    @Override
    public void delete(String key) {
        DatabaseConnection.update(Delete_SQL,key );
    }

    @Override
    public List<NhanVien> selectAll() {
       return selectBySQL(SelectAll_SQL);
    }
    @Override
    public NhanVien selectById(String key) {
      List<NhanVien> list = selectBySQL(SelectByID_SQL, key);
      if(list.isEmpty()){
          return null;
      }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
       List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setTenNV(rs.getString("TenNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setSDT(rs.getString("SDT"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setHinh(rs.getString("Hinh"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }    
}
    

