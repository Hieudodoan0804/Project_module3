package com.ra.service.admin;

public interface ReportService {


   void CostByDate();
   void CostByTimeRange(String startDate, String endDate);
   void RevenueByDate();
   void RevenueByTimeRange(String startDate,String endDate);
   void EmployeeStatusCount();
   void MostImportedProduct(String startDate,String endDate);
   void LeastImportedProduct(String startDate,String endDate);
   void MostExportedProduct(String startDate,String endDate);
   void LeastExportedProduct(String startDate,String endDate);

}
