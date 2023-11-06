package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.Enums.JobStatus;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Company;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Job;
import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.JobService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

// job add by user when ever the job is added by user all his followers will get the job notification through  the mail
    @PostMapping("/addJobByUser")
    public ResponseEntity jobAddByUser(@RequestBody Job job) throws Exception{

          try{
              String response = jobService.jobAddByUser(job);

              return new ResponseEntity(response , HttpStatus.OK);
          }catch (Exception e) {
              return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
          }
    }

    @GetMapping("/allJobs")
    public List<Job> allJobs() {

        return jobService.allJobs();
    }

    // find all jobs posted from the last 24 hours
    @GetMapping("/before-24-hours")
    public ResponseEntity allJobsPostedInLast24Hours() throws Exception{

        try{
            List<Job> jobList = jobService.allJobsPostedInLast24Hours();
            return new ResponseEntity(jobList, HttpStatus.OK);
        }catch (Exception  e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // find all companies who are hiring now
    @GetMapping("/all-hiring-companies")
    public ResponseEntity allHiringComapanies(@RequestParam ("jobStatus")JobStatus jobStatus) throws Exception {

        try{
            List<Company> companies = jobService.allHiringCompanies(jobStatus);
            return new ResponseEntity(companies , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

}
