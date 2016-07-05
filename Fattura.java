/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattura;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Giulia Evangelisti
 */
public class Fattura {
    String cliente;
    String data;
    AtomicInteger numerofattura;
    ArrayMaglia arraymaglia; 
    private static String FILE = "C:/Workspace/Fattura.pdf";
    
    /*incerti*/
    private static Font font  = new Font(Font.FontFamily.HELVETICA, 8);
    boolean controllo; //serve per controllare se sono presenti alcune categorie di capi ed eseguire o no quella parte di codice (da rivedere)
    
    
public Fattura(){
    
}

public Fattura (String cli, String d){
    this.cliente=cli;
    this.data=d;
    numerofattura = new AtomicInteger(1); 
}

public static void main (String args[]) throws SQLException, IOException, DocumentException{
    String cliente="Mario Rossi";
    String data="2016-06-01";
    AtomicInteger numerofattura = new AtomicInteger(1); //COME INCREMENTARLO?
    Fattura fattura = new Fattura(cliente, data);
    fattura.setIntestazione(cliente, data, numerofattura);
    

}

public void setIntestazione(String cliente, String data, AtomicInteger numerofattura) throws SQLException{ //Ho messo che il numero della fattura va passato come parametro, voglio capire se si può fare altrimenti (con un contatore)
    try
 {
    PdfReader pdfReader = new PdfReader("C:/Workspace/TemplateInvoice.pdf");
    PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(FILE));
    pdfStamper.getAcroFields().setField("Num", numerofattura.toString());
    pdfStamper.getAcroFields().setField("Data", data);
    pdfStamper.getAcroFields().setField("Nome", cliente);   
    PdfContentByte content = pdfStamper.getUnderContent(1);//1 for the first page
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
    content.beginText();
    content.setFontAndSize(bf, 18);
    content.showTextAligned(PdfContentByte.ALIGN_CENTER, "Da togliere-appunto del codice per inserire testo", 200,200,0);
    content.endText();
    inserisciDati(cliente, data);
    pdfStamper.close();
    pdfReader.close();
  }
   catch (IOException | DocumentException e)
  {
      }
}
//IDEA: creare una mega casella di testo dove inserire questo metodo
public void inserisciDati(String cliente, String data) throws SQLException, IOException, DocumentException{
    try{
        PdfReader pdfReader = new PdfReader("C:/Workspace/TemplateInvoice.pdf");
    
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(FILE));
        arraymaglia = new ArrayMaglia();
        Integer j;
 //tutti gli altri array -- inserire qui -- //
 
        arraymaglia.ArrayIDmaglia(cliente, data);
//aggiungi gli altri //

        for (j=0; j<arraymaglia.IDmaglie.length; j++){
            arraymaglia.accessoMaglie(j);
        }}catch (IOException | DocumentException e){
        
        }
}


} 

