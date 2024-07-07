package web.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.dtos.FullyCourseStudentDTO;
import web.app.dtos.ReportIncomeDTO;
import web.app.entity.FullyCourseStudent;
import web.app.service.IFullyCourseStudentService;
import web.app.service.LoginService;

import java.security.Principal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class FullyCourseStudentController {
    @Autowired
    IFullyCourseStudentService service;
    @Autowired
    LoginService userDetailService;

    @GetMapping("/full-courses-student")
    String getFullyCourseStudent(Model model, @RequestParam(required = false, name = "campus", defaultValue = "1") Integer campus, Principal principalUser)
    {
        try {
            String role = userDetailService.getRoleByUsername(principalUser.getName());

            if(!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("manager")){
                campus = Integer.parseInt(role);
            }
        }catch (Exception ex){
            return "/500";
        }
        List<FullyCourseStudent> students = service.getStudents(campus);
        double totalIncome = students.stream().mapToDouble(FullyCourseStudent::getFee).sum();
        model.addAttribute("students", students);
        model.addAttribute("totalIncome", NumberFormat.getInstance(new Locale("en", "US")).format(totalIncome));
        model.addAttribute("campus", campus);
        return "students-full-courses";
    }

    @PostMapping(value = "/full-courses-student")
    public String reportIncome(@RequestParam(name = "startDate", defaultValue = "") String startDate,
                               @RequestParam(name = "endDate", defaultValue = "") String endDate,
                               @RequestParam(name = "campus", defaultValue = "1", required = false) Integer campus,
                               Model model, Principal principalUser) throws JsonProcessingException {
        System.out.println("Report full course student ");
        List<FullyCourseStudent> results = null;
        double totalIncome = 0;
        try {
            String role = userDetailService.getRoleByUsername(principalUser.getName());

            if(!role.equalsIgnoreCase("admin")){
                campus = Integer.parseInt(role);
            }
            System.out.println("start - end: "+startDate+ " - "+endDate);
            if(!startDate.trim().isEmpty()&& !endDate.trim().isEmpty())
            {
                results = service.getStudentsDateRegisBetween(campus, startDate,endDate);

                totalIncome = results.stream().mapToDouble(FullyCourseStudent::getFee).sum();
                System.out.println("Result: "+results.size());
                System.out.println("Result: "+results.toString());
                model.addAttribute("campus", campus);
            }else{
                model.addAttribute("campus", "");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return "/500";
        }

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("totalIncome", NumberFormat.getInstance(new Locale("en", "US")).format(totalIncome));
        model.addAttribute("students", results);
        model.addAttribute("campus", campus);
        return "students-full-courses";
    }
}
