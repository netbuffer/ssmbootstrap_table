package cn.com.ttblog.ssmbootstrap_table.view;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import cn.com.ttblog.ssmbootstrap_table.util.AbstractIText5PdfView;

public class PdfView extends AbstractIText5PdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Paragraph header = new Paragraph(new Chunk("PDF 输出测试", getChineseFont(24)));
		document.add(header);
		document.add(new Paragraph("测试", getChineseFont(12)));
	}

	private static final Font getChineseFont(float size) {
		Font FontChinese = null;
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			FontChinese = new Font(bfChinese, size, Font.NORMAL);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		return FontChinese;
	}
}