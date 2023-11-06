package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Enums.Status;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.CompanyAlreadyExist;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.NoCompaniesAreHiringNow;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Company;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public String addCompany(Company company)throws Exception {

      Company company1 = companyRepository.findCompanyByName(company.getName());

      if(company1 != null) {
          throw  new CompanyAlreadyExist("This company is already registered");
      }

      companyRepository.save(company);

      return company.getName() + " registered successfully!";
    }

    public List<Company> hiringCompanies() throws Exception{

        List<Company> companyList = companyRepository.findAll();

        List<Company> list = new ArrayList<>();
       Status status = Status.HIRING;
        for(Company company : companyList) {

            if(company.getStatus().equals(status)) list.add(company);
        }

        if(list.isEmpty()){
            throw new NoCompaniesAreHiringNow("no companies is hiring now");
        }
        return list;
    }
}
