package Driver;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

public class ExcelUtils {

	public static final String Path_TestData = "src/test/resources/";
	public static final String Path_TestOutput = "test-output/";
	public static final String File_TestData = "TestData.xlsx";

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {
			FileInputStream ExcelFile = new FileInputStream(Path_TestData + File_TestData);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			ExcelFile.close();
			return CellData;
		} catch (Exception e) {
			return "Excel file is not open.";
		}
	}

	public static void createNewWorkbook(String WorkbookName) throws Exception {
		try {

			ExcelWBook = new XSSFWorkbook();
			ExcelWBook.createSheet("Sheet1");

			FileOutputStream fileOut = new FileOutputStream(Path_TestOutput + WorkbookName);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			throw (e);
		}
	}

	public static void setCellData(String Result, String WorkbookName, int RowNum, int ColNum) throws Exception {

		try {
			FileInputStream ExcelFile = new FileInputStream(Path_TestOutput + WorkbookName);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");
			Row = ExcelWSheet.getRow(RowNum);

			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}

			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			FileOutputStream fileOut = new FileOutputStream(Path_TestOutput + WorkbookName);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			ExcelFile.close();

		} catch (Exception e) {
			throw (e);
		}
	}

	public static void setCellData(Integer Result, String WorkbookName, int RowNum, int ColNum) throws Exception {

		try {
			FileInputStream ExcelFile = new FileInputStream(Path_TestOutput + WorkbookName);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");
			Row = ExcelWSheet.getRow(RowNum);

			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}

			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellType(5);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellType(5);
				Cell.setCellValue(Result);
			}

			FileOutputStream fileOut = new FileOutputStream(Path_TestOutput + WorkbookName);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			ExcelFile.close();

		} catch (Exception e) {
			throw (e);
		}
	}

	public static void addPieChartAsPicture(String WorkbookName, PieDataset dataset) throws Exception {

		try {
			FileInputStream ExcelFile = new FileInputStream(Path_TestOutput + WorkbookName);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");

			JFreeChart PieChartObject = ChartFactory.createPieChart3D("Pie Chart Demo", // chart title
					dataset, // dataset
					true, // include legend
					true, 
					false);
			
			/* Write chart as PNG to Output Stream */
			ByteArrayOutputStream chart_out = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(chart_out, PieChartObject, 640, 480);

			 /* We can now read the byte data from output stream and stamp the chart to Excel worksheet */
            int my_picture_id = ExcelWBook.addPicture(chart_out.toByteArray(), ExcelWBook.PICTURE_TYPE_PNG);
            
            /* we close the output stream as we don't need this anymore */
            chart_out.close();
            
            /* Create the drawing container */
            XSSFDrawing drawing = ExcelWSheet.createDrawingPatriarch();
            
            /* Create an anchor point */
            ClientAnchor my_anchor = new XSSFClientAnchor();
            
            /* Define top left corner, and we can resize picture suitable from there */
            my_anchor.setCol1(6);
            my_anchor.setRow1(6);
            
            /* Invoke createPicture and pass the anchor point and ID */
            XSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
            
            //XSSFChart my_chart = drawing.createChart(my_anchor);
            
            /* Call resize method, which resizes the image */
            my_picture.resize();
            
            /* Close the FileInputStream */
            ExcelFile.close();               

			FileOutputStream fileOut = new FileOutputStream(Path_TestOutput + WorkbookName);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			throw (e);
		}
	}
	
	public static void addLineChart(String WorkbookName, PieDataset dataset) throws Exception {

		try {
			FileInputStream ExcelFile = new FileInputStream(Path_TestOutput + WorkbookName);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");   
			
            XSSFDrawing xlsx_drawing = ExcelWSheet.createDrawingPatriarch();
            XSSFChart my_line_chart = xlsx_drawing.createChart(xlsx_drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15));
            
            XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
            legend.setPosition(LegendPosition.BOTTOM);
            
            /* Create data for the chart */
            LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();    
            
            ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
            ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);     
            
            /* Define Data sources for the chart */
            /* Set the right cell range that contain values for the chart */
            /* Pass the worksheet and cell range address as inputs */
            /* Cell Range Address is defined as First row, last row, first column, last column */
            ChartDataSource<Number> xs = DataSources.fromNumericCellRange(ExcelWSheet, new CellRangeAddress(1, 3, 4, 4));
            ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(ExcelWSheet, new CellRangeAddress(1, 3, 5, 5));
            data.addSeries(xs, ys1);

            
            my_line_chart.plot(data, new ChartAxis[] { bottomAxis, leftAxis });   

			FileOutputStream fileOut = new FileOutputStream(Path_TestOutput + WorkbookName);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			throw (e);
		}
	}
	
	public static void addLineChartOnNewSheet(String WorkbookName) throws Exception {

		try {
			FileInputStream ExcelFile = new FileInputStream(Path_TestOutput + WorkbookName);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWBook.createSheet("Sheet2");
			ExcelWSheet = ExcelWBook.getSheet("Sheet2");

			for (int rowIndex = 0; rowIndex < 4; rowIndex++) {

				XSSFRow my_row = ExcelWSheet.createRow((short) rowIndex);
				for (int colIndex = 0; colIndex < 5; colIndex++) {

					XSSFCell cell = my_row.createCell((short) colIndex);
					cell.setCellValue(colIndex * (rowIndex + 1));
				}
			}     
			
            /* Create a drawing canvas on the worksheet */
            XSSFDrawing xlsx_drawing = ExcelWSheet.createDrawingPatriarch();
            
            /* Define anchor points in the worksheet to position the chart */
            XSSFClientAnchor anchor = xlsx_drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);
            
            /* Create the chart object based on the anchor point */
            XSSFChart my_line_chart = xlsx_drawing.createChart(anchor);
            
            /* Define legends for the line chart and set the position of the legend */
            XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
            legend.setPosition(LegendPosition.BOTTOM);
            
            /* Create data for the chart */
            LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();    
            
            /* Define chart AXIS */
            ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
            ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);     
            
            /* Define Data sources for the chart */
            /* Set the right cell range that contain values for the chart */
            /* Pass the worksheet and cell range address as inputs */
            /* Cell Range Address is defined as First row, last row, first column, last column */
            ChartDataSource<Number> xs = DataSources.fromNumericCellRange(ExcelWSheet, new CellRangeAddress(0, 0, 0, 4));
            ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(ExcelWSheet, new CellRangeAddress(1, 1, 0, 4));
            ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(ExcelWSheet, new CellRangeAddress(2, 2, 0, 4));
            ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(ExcelWSheet, new CellRangeAddress(3, 3, 0, 4));
            
            /* Add chart data sources as data to the chart */
            data.addSeries(xs, ys1);
            data.addSeries(xs, ys2);
            data.addSeries(xs, ys3);
            /* Plot the chart with the inputs from data and chart axis */
            
            my_line_chart.plot(data, new ChartAxis[] { bottomAxis, leftAxis });   

			FileOutputStream fileOut = new FileOutputStream(Path_TestOutput + WorkbookName);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			throw (e);
		}
	}
}
