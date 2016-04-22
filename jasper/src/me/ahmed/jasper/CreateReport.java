package me.ahmed.jasper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CreateReport {

	   public static void main(String[] args) {
	      String masterReportFileName = "C://Users/FUTUR/Desktop/reports"
	            + "/pageMaster.jrxml";
	      String subReportFileName = "C://Users/FUTUR/Desktop/reports"
	            + "/page1.jrxml";
	      String destFileName = "C://Users/FUTUR/Desktop/reports"
	            + "/pageMaster.JRprint";
	      DataBeanList DataBeanList = new DataBeanList();
	      ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
	      JRBeanCollectionDataSource beanColDataSource =
	            new JRBeanCollectionDataSource(dataList);

	      try {
	         /* Compile the master and sub report */
	         JasperReport jasperMasterReport = JasperCompileManager
	            .compileReport(masterReportFileName);
	         JasperReport jasperSubReport = JasperCompileManager
	            .compileReport(subReportFileName);

	         Map<String, Object> parameters = new HashMap<String, Object>();
	         parameters.put("subreportParameter", jasperSubReport);
	         JasperFillManager.fillReportToFile(jasperMasterReport,
	            destFileName, parameters, beanColDataSource);

	      } catch (JRException e) {

	         e.printStackTrace();
	      }
	      System.out.println("Done filling!!! ...");
	   }
	}