package com.simanov;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main
{
    public static void main(String args[])
    {
        if(args.length == 0 )
        {
            System.out.println("Path was not set");
            return;
        }
        String path = args[0];
        List<Integer> data = new ArrayList<>();
        try{
            data = readDataFromExcelFile(path);
        }catch (IOException e){
            e.printStackTrace();
        }
        for(Integer str : data){
            System.out.println(str);
        }
    }

    public static List<Integer> readDataFromExcelFile ( String excelFilePath) throws IOException
    {

        List<Integer> data = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            String str = null;
            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();
                if(columnIndex == 1){
                    str = nextCell.getStringCellValue();
                }

            }

            try{
                int number = Integer.parseInt(str);
                if(number >= 0){
                    data.add(number);
                }
            }catch (NumberFormatException e){

            }


        }
        workbook.close();
        inputStream.close();
        return data;
    }

}

