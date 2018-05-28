package com.barclays.compare;

import java.io.*;
import java.sql.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class GetData {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", "root");
            PreparedStatement psmnt = null;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from student");

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Excel Sheet");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("Roll No");
            rowhead.createCell((short) 1).setCellValue("Name");
            rowhead.createCell((short) 2).setCellValue("Class");
            rowhead.createCell((short) 3).setCellValue("Marks");
            rowhead.createCell((short) 4).setCellValue("Grade");

            int index = 1;
            while (rs.next()) {

                HSSFRow row = sheet.createRow((short) index);
                row.createCell((short) 0).setCellValue(rs.getInt(1));
                row.createCell((short) 1).setCellValue(rs.getString(2));
                row.createCell((short) 2).setCellValue(rs.getString(3));
                row.createCell((short) 3).setCellValue(rs.getInt(4));
                row.createCell((short) 4).setCellValue(rs.getString(5));
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("c:\\excelFile.xls");
            wb.write(fileOut);
            fileOut.close();
            System.out.println("Data is saved in excel file.");
            rs.close();
            connection.close();
        } catch (Exception e) {
        }
    }
}