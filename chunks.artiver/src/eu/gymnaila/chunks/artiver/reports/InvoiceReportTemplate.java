/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.reports;

import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.entities.DJGroupVariable;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.entity.MasterData;
import java.awt.Color;
import java.io.File;
import java.util.List;

/**
 *
 * @author Thunderbolt
 */
public class InvoiceReportTemplate extends StandardReportTemplate
{
   private MasterData mMasterData = null;
   private Customer mCust = null;
   
    public InvoiceReportTemplate(String reportTemplate, List<AbstractColumn> cols, String title, MasterData masterData, Customer cust)
    {
        super(reportTemplate, cols, title);
        
        mMasterData = masterData;
        mCust = cust;
    }
    
    @Override
    public DynamicReport buildReport() throws Exception 
    {
        Style detailStyle = new Style();
        Style headerStyle = new Style();

        
        Border border = Border.THIN();
        border.setColor(Color.LIGHT_GRAY);
        border.setLineStyle(Border.BORDER_STYLE_DOTTED);

        
        Font subtitleFont = Font.ARIAL_BIG_BOLD;
        subtitleFont.setFontSize(18);
        
        Font titleFont = Font.ARIAL_MEDIUM;
        titleFont.setFontSize(14);
        
        headerStyle.setBackgroundColor(new Color(230, 230, 230));
        headerStyle.setBorderBottom(Border.THIN());
        headerStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setTransparency(Transparency.OPAQUE);

        detailStyle.setBorderLeft(border);
        detailStyle.setBorderRight(border);

        Style subtitleStyle = new Style();
        subtitleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        subtitleStyle.setFont(subtitleFont);
        subtitleStyle.setTextColor(Color.black);

        Style titleStyle = new Style();
        titleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        titleStyle.setFont(titleFont);
        titleStyle.setTextColor(Color.black);



        /**
         * Creates the DynamicReportBuilder and sets the basic options for the
         * report
         */
        String ls = "\\n";
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(mCust.getCustomer() + ls + mCust.getAddress().replace(", ", ls) + ls + ls + mCust.getCustomerNr() + ls + ls + ls + mMasterData.getCompanyName() + " " + mMasterData.getCorporateForm() + ls + mMasterData.getStreet() + ls + mMasterData.getPostalCode() + ls + ls + mMasterData.getTaxID() + ls + mMasterData.getUstID())
                .setSubtitle(ls + mTitle) //defines the title of the report
                .setDetailHeight(15) //defines the height for each record of the report
                .setMargins(20, 20, 20, 20) //define the margin space for each side (top, bottom, left and right)
                .setDefaultStyles(titleStyle, subtitleStyle, headerStyle, detailStyle)
                .setColumnsPerPage(1);						//defines columns per page (like in the telephone guide)

        System.out.print(mTitle);
        addColumns(drb, mCols);

        /**
         * 
         * add some more options to the report (through the builder)
         */
        drb.setUseFullPageWidth(true);	

        //This look for the resource in the classpath
        File ralPath = new File("/eu/gymnaila/chunks/artiver/reports" + "/" + mReportTemplate + ".jrxml"); 
 
        if(!ralPath.exists())
        {
            ralPath = new File("dist/data/reports/" + mReportTemplate + ".jrxml");
        }
        System.out.println(ralPath.getPath());
 
        drb.setTemplateFile(ralPath.getPath(),false, false, true, false);

        DynamicReport dr = drb.build();	

        
        return dr;
    }
   
}
