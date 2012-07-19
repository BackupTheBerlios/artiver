/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.reports;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJQuery;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.entity.MasterData;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.view.ListFrame;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author User
 */
public class MainTest {
    
    public static void main(String[] args)
    {
            configureLogger();
        


            try {
            List<AbstractColumn> cols = new ArrayList<>();
            
            List<TableColumn> tableCols = new ArrayList<TableColumn>();

            AppConfig.init();
            
            for (int i=0; i<tableCols.size(); i++)
            {
                TableColumn tableCol = tableCols.get(i);
                            
                AbstractColumn column = ColumnBuilder.getNew()
                    .setColumnProperty(tableCol.getText(), "java.lang.String")
                    .setTitle(tableCol.getText())
                    .setWidth(85)
                    .build();
                
                cols.add(column);
            }
            
            InvoiceReportTemplate repTemp = new InvoiceReportTemplate(ReportTemplate.INVOICE.toString(), cols, "Rechnung", new MasterData(0, "ChUnKS", "GmbH", "Matthias Hofmann", "Kirchplatz 4", "95152 Selbitz", "", "", "", "222/1235/4566", "123-1565-112", 0.19, "Terms"), new Customer(0, "123432", "Martha Pfahl", "Marthastraße 2, 95152 Selbitz") );
            DynamicReport dr = repTemp.buildReport();
     
            //if(objDS.getData().size()>0 && (query == null || query.equals("")))
            //{
                dr.setQuery(new DJQuery("select * from artiver.Article", DJConstants.QUERY_LANGUAGE_SQL));
           // }
            //else
            //{
               // dr.setQuery(new DJQuery(query, DJConstants.QUERY_LANGUAGE_SQL));
           // }
            
            EntityManager entityManager = AppConfig.createEntityManager();
                
            entityManager.getTransaction().begin();
                
            java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
                
            System.out.println(connection.isValid(0));
                
            entityManager.getTransaction().commit();
            
    
            
            final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), connection, null);
         


                        try {
                            System.out.println("PDF-Export");
                           // FileChooser selFile = new FileChooser();
                           // selFile.setTitle("PDF-Export");
                            
                            //Set extension filter
                          //  FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Dateien (*.pdf)", "*.pdf");
                           // selFile.getExtensionFilters().add(extFilter);

                            File tempFile = new File("test.pdf");
                            
                            if(tempFile != null)
                            {
                                String path = tempFile.getAbsolutePath().endsWith(".pdf") ? tempFile.getAbsolutePath() : tempFile.getAbsolutePath() + ".pdf";
                                
                                JasperExportManager.exportReportToPdfFile(jasperPrint, path); 
                            }
                            else
                            {
                               // throw new Exception();
                            }
                            
                            JasperViewer.viewReport(jasperPrint, false);
                        } catch (JRException ex) {
                            Logger.getLogger(ListFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                    } catch (Exception ex) {
                        Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                }

     /**
	 * Configures the Logger with log4j.properties found at LOG4J_PATH in Analyzer.properties
	 */
	private static void configureLogger(){
		
		BasicConfigurator.configure();
		
		File f = null;
		
		URL url = GuiPrototyp.class.getClassLoader().getResource("/eu/gymnaila/chunks/artiver/libs/log4j.properties");
		
		if(url == null){
			f = new File("dist/data/log4j.properties");
		}
		else{
			f = new File(url.getPath());
		}
		
		
			System.out.println("loggerpath: "+ f.getAbsolutePath());
			PropertyConfigurator.configure(f.getPath());
			
		

	}
    
}
