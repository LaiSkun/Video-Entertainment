/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.ultils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ChuyenDoi {
    static SimpleDateFormat formater = new SimpleDateFormat();
    public static String DinhDangNgay(Date ngay){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(ngay);
    }
    public static Date toDate(String date, String pattern){
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String toString(Date date, String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
    }
    public static Date addDays(Date date, long days){
        date.setTime(date.getTime() + days *24*60*60*1000);
        return date;
    }
    public static String DinhDangTien(double tien){
        return NumberFormat.getNumberInstance().format(tien);
    }
     public static double ChuyenTien(String tien) {
        try {
            return NumberFormat.getNumberInstance().parse(tien).doubleValue();
        } catch (ParseException ex) {
//            GUI.frmHeThong.ThongBao("Thông báo", "Lỗi chuyển dữ liệu");

        }
        return 0;
    }
}
