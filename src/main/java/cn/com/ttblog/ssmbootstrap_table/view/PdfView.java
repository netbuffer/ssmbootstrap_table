package cn.com.ttblog.ssmbootstrap_table.view;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import cn.com.ttblog.ssmbootstrap_table.model.User;

public class PdfView extends AbstractIText5PdfView {
	
	private static final Logger LOG=LoggerFactory.getLogger(PdfView.class);
	
	//可以通过反射来封装成公用的~
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		Font pfont=getChineseFont();
		pfont.setSize(24);
		Paragraph header = new Paragraph(new Chunk("PDF 输出测试", pfont));
		document.add(header);
		document.addTitle("title");
		document.addSubject("subject");
		document.addHeader("heaer-name","header-content");
		 document.add(new Paragraph("测试", getChineseFont()));
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 3.0f, 2.0f, 2.0f, 3.0f});
		table.setSpacingBefore(10);
		// define font for table header row
//		FontFactory.getFont(FontFactory.HELVETICA)
		Font font = getChineseFont();
		font.setColor(BaseColor.CYAN);
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(new BaseColor(66,133,244));
		cell.setBorderColor(BaseColor.CYAN);
		cell.setPadding(5);
		List<String> columns = (List<String>) model.get("columns");
		if (columns == null || columns.size() <= 0) {
			throw new IllegalArgumentException("请设置列名columns");
		}
		LOG.debug("[PdfView]-columns：{}",columns);
		// write table header
		for (String column : columns) {
			LOG.debug("添加column:{}",column);
			cell.setPhrase(new Phrase(column, font));
			table.addCell(cell);
		}
		
		// write table row data
		List<User> users = (List<User>) model.get("users");
		if (users == null || users.size() <= 0) {
			throw new IllegalArgumentException("请添加users数据");
		}
		LOG.debug("[PdfView]-users：{}",users);
		for (User u : users) {
			table.addCell(u.getName());
			PdfPCell sexCell = new PdfPCell();
			sexCell.setPhrase(new Phrase(u.getSex(), font));
			table.addCell(sexCell);
			table.addCell(String.valueOf(u.getAge()));
			table.addCell(u.getPhone());
		}
		document.add(table);
	}

	private static final Font getChineseFont() {
		Font FontChinese = null;
		try {
			BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			FontChinese = new Font(baseFont);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		return FontChinese;
	}
}