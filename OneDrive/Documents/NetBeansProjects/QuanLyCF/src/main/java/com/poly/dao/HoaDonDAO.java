/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.entity.HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonDAO extends QuanLiCFDAO<HoaDon, String>{
    final String Insert_SQL= "insert into HoaDon(MaHD,MaNV,SoHoaDon,NgayTaoHD,TongTien,GhiChu) values(?,?,?,?,?,?)";
    final String Update_SQL="update HoaDon set MaHD= 'HD00' +CAST((select Count(*)+1 from HoaDon where SoHoaDon like SoHoaDon) as nvarchar) where MaHD = ?";
    final String Delete_SQL="delete from HoaDon where MaHD = ?";
    final String SelectAll_SQL ="select *from HoaDon";
    final String SelectByID_SQL ="select *from HoaDon where MaHD=?";
    
    @Override
    public void insert(HoaDon entity) {
        DatabaseConnection.update(Insert_SQL,entity.getMaHD(),entity.getMaNV(),entity.getMaNV()+"HD"+entity.getSoHD() ,entity.getNgayLapHD(),entity.getTongTien(),entity.getGhiChu());
    }

    @Override
    public void update(HoaDon entity) {
        DatabaseConnection.update(Update_SQL,entity.getMaHD());
    }

    @Override
    public void delete(String key) {
        DatabaseConnection.update(Delete_SQL,key);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySQL(SelectAll_SQL);
    }

    @Override
    public HoaDon selectById(String key) {
        List<HoaDon> list = selectBySQL(SelectByID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
         List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql, args);
            while(rs.next()){
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setSoHD(rs.getString("SoHoaDon"));
                entity.setNgayLapHD(rs.getDate("NgayTaoHD"));
                entity.setTongTien(rs.getDouble("TongTien"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     protected List<HoaDon> selectBy(String sql, Object... args) {
         List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql, args);
            while(rs.next()){
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<HoaDon> selectByKeytWord(String keyword){
        String sql = "Select *from HoaDon where MaHD like ?";
        return this.selectBySQL(sql,"%" + keyword + "%");
    }
    public List<HoaDon> CountSoHoaDon(String keyword){
        String sql = "select MaHD,Count(*) from HoaDon where MaHD like ? group by MaHD";
         return this.selectBy(sql,"%" + keyword + "%");
    }
    public List<Integer> selectYears(){
        String sql = "Select DISTINCT year(NgayTaoHD) Year from HoaDon order by year desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
       public List<Integer> selectDay(){
        String sql = "Select DISTINCT day(NgayTaoHD) day from HoaDon order by day desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
          public List<Integer> selectMonth(){
        String sql = "Select DISTINCT month(NgayTaoHD) month from HoaDon order by month desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
//    
//    
//      public List<HoaDon>  GetBySoHoaDon(String SoHoaDon) {
//        String sql = "select * from hoadon where SoHoaDon = N'" + SoHoaDon + "'";
//        return this.selectBySQL(sql, SoHoaDon);
//    }
}
