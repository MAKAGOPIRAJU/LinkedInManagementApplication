package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.Models.Company;
import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/addCompany")
    public ResponseEntity addCompany(@RequestBody Company company) throws Exception{

        try{
            String response = companyService.addCompany(company);
            return new ResponseEntity(response , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }

    // list out of all the companies who are currently hiring in all the job roles
    @GetMapping("/jobSearch")
    public ResponseEntity hiringCompanies() throws Exception {

        try{
            List<Company> companyList = companyService.hiringCompanies();

            return new ResponseEntity<>(companyList , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
