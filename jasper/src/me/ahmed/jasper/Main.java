package me.ahmed.jasper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class Main {

	public static void main(String[] args) throws JRException {
		
		JRBeanCollectionDataSource beanColDataSource1 =
			      new JRBeanCollectionDataSource(new ArrayList<>());
		JRBeanCollectionDataSource beanColDataSource2 =
			      new JRBeanCollectionDataSource(new ArrayList<>());

		String file1 = "C://Users/FUTUR/Desktop/reports/report1.jrxml" ;
		String file2 = "C://Users/FUTUR/Desktop/reports/report2.jrxml" ;
		
		
		JasperReport jreport1 = JasperCompileManager.compileReport(file1);
		JasperPrint jprint1 = JasperFillManager.fillReport(jreport1, new HashMap(),
				new JREmptyDataSource());

		JasperReport jreport2 = JasperCompileManager
				.compileReport(file2);
		JasperPrint jprint2 = JasperFillManager.fillReport(jreport2, new HashMap(),
				new JREmptyDataSource());

		List<JasperPrint> jprintlist = new ArrayList<JasperPrint>();

		jprintlist.add(jprint1);
		jprintlist.add(jprint2);

		String fileName = "C://Users/FUTUR/Desktop/reports/TESTReport.pdf";
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST, jprintlist);
		exporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, fileName);

		exporter.exportReport();
		System.out.println("done");

	}

}
