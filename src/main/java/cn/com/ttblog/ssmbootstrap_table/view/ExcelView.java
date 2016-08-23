package cn.com.ttblog.ssmbootstrap_table.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import cn.com.ttblog.ssmbootstrap_table.model.User;

public class ExcelView extends AbstractExcelView {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelView.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		Map<String, String> revenueData = (Map<String, String>) model.get("revenueData");
//		// create a wordsheet
//		HSSFSheet sheet = workbook.createSheet("Revenue Report");
//		HSSFRow header = sheet.createRow(0);
//		header.createCell(0).setCellValue("Month");
//		header.createCell(1).setCellValue("Revenue");
//		int rowNum = 1;
////		for (Map.Entry<String, String> entry : revenueData.entrySet()) {
////			// create the row data
////			HSSFRow row = sheet.createRow(rowNum++);
////			row.createCell(0).setCellValue(entry.getKey());
////			row.createCell(1).setCellValue(entry.getValue());
////		}
		List<String> columns=(List<String>) model.get("columns");
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("sheet");
		// 冻结该行，使其无法移动
		sheet.createFreezePane(0, 1, 0, 1);
		Row row = sheet.createRow((short) 0);
		row.setHeightInPoints(30);
		int titleCount=columns.size();
		Font titleFont=wb.createFont();
		titleFont.setBold(true);
		titleFont.setColor(IndexedColors.AQUA.getIndex());
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setFont(titleFont);
		for(int i=0;i<titleCount;i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(columns.get(i));
			cell.setCellStyle(cellStyle);
		}
		CellStyle contentStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//		List<User> datas=(List<User>) model.get("users");
//		int dataCount=datas.size();
//		for(int i=1;i<dataCount;i++){
//			Row rowIndex = sheet.createRow((short) i);
//			for(int k=0;k<4;k++){
//				Cell cell = rowIndex.createCell(k);
//				cell.setCellStyle(contentStyle);
//				cell.setCellValue(datas.get(i).getName());
//			}
//		}
	}
}