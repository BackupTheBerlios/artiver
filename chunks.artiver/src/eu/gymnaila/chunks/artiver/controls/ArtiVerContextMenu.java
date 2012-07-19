/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controls;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJQuery;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.reports.StandardReportTemplate;
import eu.gymnaila.chunks.artiver.view.ListFrame;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.javafxdata.datasources.protocol.ObjectDataSource;

/**
 *
 * @author User
 */
public class ArtiVerContextMenu extends ContextMenu
{
    public ArtiVerContextMenu(String reportTemplate, String title, ObjectDataSource objDS, String query) throws JRException, SQLException, Exception
    {
        super();
        List<AbstractColumn> cols = new ArrayList<>();
        
        ObservableList<TableColumn> tableCols = objDS.getColumns();
        
       // List ds = objDS.getData();
       // System.out.println(ds.get(0));
        
        //JRDataSource ds = new JRBeanArrayDataSource(objDS.getData().toArray());
        
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
        
        StandardReportTemplate repTemp = new StandardReportTemplate(reportTemplate, cols, title);
        DynamicReport dr = repTemp.buildReport();
 
        if(objDS.getData().size()>0 && (query == null || query.equals("")))
        {
            dr.setQuery(new DJQuery("select * from artiver." + objDS.getData().get(0).getClass().getSimpleName(), DJConstants.QUERY_LANGUAGE_SQL));
        }
        else
        {
            dr.setQuery(new DJQuery(query, DJConstants.QUERY_LANGUAGE_SQL));
        }
        
        EntityManager entityManager = AppConfig.createEntityManager();
            
        entityManager.getTransaction().begin();
            
        java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
            
        System.out.println(connection.isValid(0));
            
        entityManager.getTransaction().commit();
        
        final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), connection, null);
     
       
       
      // final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
   
        
  //---------------- OLD      
        
        //  List<String> ds = objDS.getData().;
   
        
      //  for(TableColumn col : objDS.)
         // We MUST combine compilation (3) and generation (4) in a single line if we want to use a List
  
        // final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
   
            
//	    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); 
//            
//	    jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
//            
//            EntityManager entityManager = AppConfig.createEntityManager();
//            
//            entityManager.getTransaction().begin();
//            
//            java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
//            
//            System.out.println(connection.isValid(0));
//            
//            entityManager.getTransaction().commit();
            
//	    final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), connection);   

           
            
            
            
            
            
            MenuItem item1 = new MenuItem("Drucken");
            item1.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    System.out.println("Drucken");
                    try {
                        JasperPrintManager.printReport(jasperPrint, true);
                        //JasperViewer.viewReport(jasperPrint);
                    } catch (JRException ex) {
                        Logger.getLogger(ListFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            MenuItem item2 = new MenuItem("PDF-Export");
            item2.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    try {
                        System.out.println("PDF-Export");
                        FileChooser selFile = new FileChooser();
                        selFile.setTitle("PDF-Export");
                        
                        //Set extension filter
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Dateien (*.pdf)", "*.pdf");
                        selFile.getExtensionFilters().add(extFilter);

                        File tempFile = selFile.showSaveDialog(GuiPrototyp.getInstance().getStage());
                        
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
            
                }
            });
            
            MenuItem item3 = new MenuItem("Excel-Export");
            item3.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    try {
                        System.out.println("Excel-Export");
                        FileChooser selFile = new FileChooser();
                        selFile.setTitle("Excel-Export");
                        
                        //Set extension filter
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel Dateien (*.xlsx)", "*.xlsx");
                        selFile.getExtensionFilters().add(extFilter);
                        
                        File tempFile = selFile.showSaveDialog(GuiPrototyp.getInstance().getStage());
                        
                        if(tempFile != null)
                        {
                            String path = tempFile.getAbsolutePath().endsWith(".xlsx") ? tempFile.getAbsolutePath() : tempFile.getAbsolutePath() + ".xlsx";
                            JRXlsxExporter xlsxExporter=new JRXlsxExporter();  
                            xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
                            xlsxExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path);  
                            xlsxExporter.exportReport();
                            JasperViewer.viewReport(jasperPrint, false);
                               
                        }
                        else
                        {
                          //  throw new Exception();
                        }
                        
                    } catch (JRException ex) {
                        Logger.getLogger(ListFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            
            
            super.getItems().addAll(item1, item2, item3);
    }
    
}
