package com.example.springproject.exceling;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class ExcelExporter {

    public void export() {
        String url = "jdbc:postgresql://localhost:5432/Practice";
        String username = "postgres";
        String password = "admin";

        String excelFile = "employees.xlsx";

        try(Connection connection = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM public.employees";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Reviews");
            sheet.setColumnWidth(4, 3000);
            sheet.setColumnWidth(8, 3000);

            writeHeaderLine(sheet);
            writeDataLines(result, workbook, sheet);


            FileOutputStream outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            workbook.close();

            statement.close();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("lastname");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("firstname");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("farthername");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("birthday");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("id_position");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("id_rank");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("id_division");

        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("contract_conclusion");

        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("contract_term");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            int id = result.getInt("id");
            String lastname = result.getString("lastname");
            String firstname = result.getString("firstname");
            String fathername = result.getString("fathername");
            java.util.Date birthday = result.getDate("birthday");
            String id_position = result.getString("id_position");
            String id_rank = result.getString("id_rank");
            String id_division = result.getString("id_division");
            java.util.Date contract_conclusion = result.getDate("contract_conclusion");
            int contract_term = result.getInt("contract_term");

            Row row = sheet.createRow(rowCount++);

            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            cellStyle.setWrapText(true);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id);

            cell = row.createCell(columnCount++);
            cell.setCellValue(lastname);

            cell = row.createCell(columnCount++);
            cell.setCellValue(firstname);

            cell = row.createCell(columnCount++);
            cell.setCellValue(fathername);

            cell = row.createCell(columnCount++);
            cell.setCellValue(birthday);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(columnCount++);
            cell.setCellValue(id_position);

            cell = row.createCell(columnCount++);
            cell.setCellValue(id_rank);

            cell = row.createCell(columnCount++);
            cell.setCellValue(id_division);

            cell = row.createCell(columnCount++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(contract_conclusion);

            cell = row.createCell(columnCount++);
            cell.setCellValue(contract_term);
        }
    }
}
