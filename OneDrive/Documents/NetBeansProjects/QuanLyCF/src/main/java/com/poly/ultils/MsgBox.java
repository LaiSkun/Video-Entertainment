/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.ultils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MsgBox {
     //Hiển thị thông báo cho người dùng , parent là cửa sổ thông báo , message là thông báo 
    public static void alert(Component parent , String message){
        JOptionPane.showMessageDialog(parent, message,"Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
    //Hiển thị thông báo và yêu cầu người dùng xác nhận
    //parent là cửa sổ thông báo 
    //message là câu hỏi yes no
    //return true hoặc false
    public static boolean confirm(Component parent , String message){
        int result = JOptionPane.showConfirmDialog(parent, message,"Hệ thống quản lý đào tạo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    //Hiển thị thông báo yêu cầu nhập dữ liệu 
    //parent là cửa sổ thông báo
    //message là thông báo nhắc nhở 
    // return là kết quả nhận được từ người sử dụng nhập vào
     public static String prompt(Component parent , String message){
         return JOptionPane.showInputDialog(parent, message, "Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
     }
}
