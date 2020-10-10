package web.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.app.dtos.ReportEndDateDTO;
import web.app.dtos.ReportIncomeDTO;
import web.app.repos.StudentRepo;
import web.app.service.IStudentService;
import web.app.service.LoginService;
import web.app.service.ReportService;

import java.security.Principal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


@Controller
public class ReportController {

    @Autowired
    IStudentService studentService;

    @Autowired
    LoginService userDetailService;

    @Autowired
    StudentRepo repo;

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/report_income" , method = {RequestMethod.GET, RequestMethod.POST})
    public String reportIncome(@RequestParam(name = "startDate", defaultValue = "") String startDate,
                               @RequestParam(name = "endDate", defaultValue = "") String endDate,
                               @RequestParam(name = "campus", defaultValue = "1", required = false) Integer campus,
                               Model model, Principal principalUser) throws JsonProcessingException {
        System.out.println("report_income");
        List<ReportIncomeDTO> dtos = null;
        double totalIncome = 0;
        try {
            String role = userDetailService.getRoleByUsername(principalUser.getName());

            if(!role.equalsIgnoreCase("admin")){
                campus = Integer.parseInt(role);
            }

            if(!startDate.trim().isEmpty()&& !endDate.trim().isEmpty())
            {
                dtos = reportService.getReportIncome(startDate,endDate,campus);
                totalIncome = reportService.getTotalIncome(dtos);

                model.addAttribute("campus", campus);
            }else{
                model.addAttribute("campus", "");
            }
        }catch (Exception ex){
            return "/500";
        }

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("totalIncome", NumberFormat.getInstance(new Locale("en", "US")).format(totalIncome));
        model.addAttribute("reportDTOs", dtos);
        return "report_income";
    }

    @RequestMapping("/report_end_date")
    public String reportEndDate(@RequestParam(name = "startDate", defaultValue = "") String startDate,
                               @RequestParam(name = "endDate", defaultValue = "") String endDate,
                               @RequestParam(name = "campus", defaultValue = "1", required = false) Integer campus,
                               Model model, Principal principalUser) throws JsonProcessingException {
        List<ReportEndDateDTO> dtos = null;
        System.out.println("report_end_date");
        try {
            String role = userDetailService.getRoleByUsername(principalUser.getName());

            if(!role.equalsIgnoreCase("admin")){
                campus = Integer.parseInt(role);
            }

            if(!startDate.trim().isEmpty()&& !endDate.trim().isEmpty())
            {
                dtos = reportService.getReportEndDate(startDate,endDate,campus);

                model.addAttribute("campus", campus);
            }else{
                model.addAttribute("campus", "");
            }
        }catch (Exception ex){
            return "/500";
        }

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("reportDTOs", dtos);
        return "report_end_date";
    }
}
