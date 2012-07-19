package eu.gymnaila.chunks.artiver.reports;

import java.awt.Color;

import net.sf.jasperreports.view.JasperViewer;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StandardReportTemplate 
{
    private List<AbstractColumn> mCols = null;

    private String mTitle = "";

    
    private String mReportTemplate = "";
    
    
    public StandardReportTemplate(String reportTemplate, List<AbstractColumn> cols, String title)
    {
        mCols = cols;
        mTitle = title;
        mReportTemplate = reportTemplate;
    }
    
    public DynamicReport buildReport() throws Exception 
    {
        Style detailStyle = new Style();
        Style headerStyle = new Style();

        
        Border border = Border.THIN();
        border.setColor(Color.LIGHT_GRAY);
        border.setLineStyle(Border.BORDER_STYLE_DOTTED);

        
        Font titleFont = Font.ARIAL_BIG_BOLD;
        titleFont.setFontSize(34);
        
        headerStyle.setBackgroundColor(new Color(230, 230, 230));
        headerStyle.setBorderBottom(Border.THIN());
        headerStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setTransparency(Transparency.OPAQUE);

        detailStyle.setBorderLeft(border);
        detailStyle.setBorderRight(border);

        Style titleStyle = new Style();
        titleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        titleStyle.setFont(titleFont);
        titleStyle.setTextColor(Color.white);

        Style subtitleStyle = new Style();
        subtitleStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        subtitleStyle.setTextColor(Color.white);

        Style amountStyle = new Style();
        amountStyle.setHorizontalAlign(HorizontalAlign.LEFT);

        /**
         * Creates the DynamicReportBuilder and sets the basic options for the
         * report
         */

        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(mTitle) //defines the title of the report
                .setSubtitle("generiert mit ArtiVer")
                .setDetailHeight(15) //defines the height for each record of the report
                .setMargins(30, 20, 30, 15) //define the margin space for each side (top, bottom, left and right)
                .setDefaultStyles(titleStyle, subtitleStyle, headerStyle, detailStyle)
                .setColumnsPerPage(1);						//defines columns per page (like in the telephone guide)

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
 
        drb.setTemplateFile(ralPath.getPath());

        DynamicReport dr = drb.build();	

        return dr;
    }

    
    
    private void addColumns(DynamicReportBuilder reportBuilder, List<AbstractColumn> cols) 
    {

        for(AbstractColumn col : cols)
        {
            System.out.println(col.toString());

            reportBuilder.addColumn(col); 
        }
    }

}
