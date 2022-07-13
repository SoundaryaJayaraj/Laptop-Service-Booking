package com.example.demo.excel;

import java.io.IOException;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.Appointment;

public class ExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Appointment> listAppointments;

	public ExcelExporter(List<Appointment> listAppointments) {
		this.listAppointments = listAppointments;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		CellStyle style1 = workbook.createCellStyle();
		CellStyle style2 = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "name", style);
		createCell(row, 1, "appointmentDate", style1);
		createCell(row, 2, "appointmentTime", style2);
		createCell(row, 3, "centerName", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof LocalDate) {
			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle style1 = workbook.createCellStyle();
			style1.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yyyy"));
			cell.setCellValue((LocalDate) value);
			cell.setCellStyle(style1);
		} else if (value instanceof Time) {
			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle style2 = workbook.createCellStyle();
			style2.setDataFormat(createHelper.createDataFormat().getFormat("hh:mm:ss"));
			cell.setCellValue((Time) value);
			cell.setCellStyle(style2);
		} else {
			cell.setCellValue((String) value);
		}
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Appointment appointment : listAppointments) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			
			createCell(row, columnCount++, appointment.getCustomerId().getUserName(), style);
			createCell(row, columnCount++, appointment.getAppointmentDate(), style);
			createCell(row, columnCount++, appointment.getAppointmentTime(), style);
			createCell(row, columnCount++, appointment.getServiceCenterId().getCenterName(), style);

		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
