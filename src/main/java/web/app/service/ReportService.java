package web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GroovyWebApplicationContext;
import web.app.dtos.ReportEndDateDTO;
import web.app.dtos.ReportIncomeDTO;
import web.app.repos.StudentRepo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ReportService {
    @Autowired
    StudentRepo repo;

    public List<ReportIncomeDTO> getReportIncome(String startDate, String endDate, Integer campus)
    {
        List<List<String>> reportRaws = repo.getReportIncome(startDate,endDate,campus);
        List<ReportIncomeDTO> dtos = new ArrayList<>();
        ReportIncomeDTO dto = null;
        for ( List<String> row: reportRaws) {
            String studentId = row.get(0);
            String studentName = row.get(1);
            String className = row.get(2);
            String nameParent = row.get(3);
            String phone = row.get(4);
            String courseName = row.get(5);
            String courseId = row.get(6);
            String dateRegis = row.get(7);
            String feeStr = row.get(8);

            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            try {
                double fee = Double.parseDouble(feeStr);
                feeStr = nf.format(fee);
            }catch (Exception e)
            {

            }

            dto = new ReportIncomeDTO(studentId,studentName,className,nameParent,phone,courseName,courseId,dateRegis,feeStr);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<ReportEndDateDTO> getReportEndDate(String startDate, String endDate, Integer campus)
    {
        List<List<String>> reportRaws = repo.getReportEndDate(startDate,endDate,campus);
        List<ReportEndDateDTO> dtos = new ArrayList<>();
        ReportEndDateDTO dto = null;
        for ( List<String> row: reportRaws) {
            String studentId = row.get(0);
            String studentName = row.get(1);
            String className = row.get(2);
            String nameParent = row.get(3);
            String phone = row.get(4);
            String courseName = row.get(5);
            String courseId = row.get(6);
            String dateEnd = row.get(7);

            dto = new ReportEndDateDTO(studentId,studentName,className,nameParent,phone,courseName,courseId,dateEnd);
            dtos.add(dto);
        }
        return dtos;
    }

    public double getTotalIncome(List<ReportIncomeDTO> dtos)
    {
        if(dtos.isEmpty()) return 0;

        double total = 0;
        for(ReportIncomeDTO dto : dtos)
        {
            double fee = 0;
            try {
                fee = dto.getFee().trim().isEmpty() ? 0: Double.parseDouble(dto.getFee()) ;
            }catch (Exception e)
            {
                fee = 0;
            }
            total += fee;
        }
        return total;
    }

}
