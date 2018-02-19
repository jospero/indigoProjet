package projet.control.entite;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.DocumentException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
public class Test {
	public static final String chemin="C:\\Users\\MABSON\\Documents\\GitHub\\document\\essai7.pdf";
	public static final String chemin1 ="C:\\Users\\MABSON\\Documents\\GitHub\\document\\img.png";
 
	public static void main(String[] args) throws DocumentException,IOException {
	
		Document document=new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(chemin));
			document.open();
			Image image=Image.getInstance(chemin1);
			Paragraph p=new Paragraph();
		    Phrase pr=new Phrase();
		    pr.add("bonjour");
		    p.add(image);
		    p.add(pr);
		  //  pr.add(" tous savoir sur les danger de la pornographie");
			document.add(p);
			//document.add(image);
			document.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
	}

}











































