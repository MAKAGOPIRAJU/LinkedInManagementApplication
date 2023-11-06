package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Enums.JobStatus;
import LinkedInManagementSystem.example.LinkedInManagement.Enums.Status;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.*;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Company;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Job;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.CompanyRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.JobRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class JobService {


    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;



    public String jobAddByUser(Job job) throws Exception {

        // get the user

        Optional<User> userOptional = userRepository.findById(job.getRecruiterId());

        if (!userOptional.isPresent()) {
            throw new UserIdIsInvalid(" there is no user with " + job.getRecruiterId() + " Id");
        }

        User user = userOptional.get();
        user.setStatus(Status.HIRING); // there is chance of the user doest not have the hiring tag
        job.setUser(user);
        user.getJobList().add(job);
        jobRepository.save(job);
        userRepository.save(user);


        // Email Integration
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        List<User> followersList = userService.allUsers(user.getUserId());

        if(!followersList.isEmpty()) {
            for (User follower : followersList) {

                String body = "Hi " + follower.getUserName() + " !" + user.getUserName() + " add a job " +
                        "you got this email because of you followed him";

                mailMessage.setFrom("makasrinivasulu01@gmail.com"); // from which mail u want to send
                mailMessage.setTo(follower.getEmail());//to which one send to mail
                mailMessage.setSubject("New Job Added !!");//subject
                mailMessage.setText(body);//message in the box

                mailSender.send(mailMessage);
            }
        }
        return " Thank you so much for using linkedIn job is added successfully !";
    }

    public List<Job> allJobs(){

        return jobRepository.findAll();
    }


    public List<Job> allJobsPostedInLast24Hours() throws Exception{

        LocalDate localDate = LocalDate.now().minusDays(1);

        List<Job> allJobs = jobRepository.findAll();

        if(allJobs.isEmpty()){

            throw new NoJobsAddedInTheLastTwentyFourHours("no jobs are posted from the last 24 hours");
        }

        List<Job> jobList = new ArrayList<>();

        for(Job job: allJobs) {

            if(job.getPostingDate().compareTo(localDate) >= 0) jobList.add(job);
        }

        if(jobList.isEmpty()){

            throw new NoJobsAddedInTheLastTwentyFourHours("no jobs are posted from the last 24 hours");
        }

        return jobList;
    }

  public List<Company> allHiringCompanies(JobStatus jobStatus) throws Exception {

        List<Job> jobList =  jobRepository.findAll();

        if(jobList.isEmpty()){
            throw new DidNotPostedAnyJobTillNow("did not posted any jobs till now");
        }

        List<Company> companies = new ArrayList<>();

        for(Job job : jobList){

            if(job.getJobStatus().equals(jobStatus)) {

                Company company = companyRepository.findCompanyByName(job.getCompanyName());
                companies.add(company);
            }
        }

        if(companies.isEmpty()){
            throw new DidNotPostedAnyJobTillNow("did not posted any jobs till now");
        }

        return companies;
  }
}
