package docvel.libSecurityTest.controllers;

import docvel.libSecurityTest.entyties.Issue;
import docvel.libSecurityTest.services.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("addIssue")
    public String addIssue(Model model){
        Issue issue = new Issue();
        model.addAttribute("issue", issue);
        return "issue/addIssue";
    }

    @PostMapping("saveIssue")
    public String addNewIssue(@ModelAttribute Issue issue){
        if(issue.getReader() != null && issue.getBook() !=null)
            issueService.addNewIssue(issue);
        return "redirect:allIssues";
    }

    @GetMapping("returnIssue")
    public String returnIssue(@RequestParam Long issueId, Model model){
        if(issueId != null && issueService.showIssueById(issueId).getDateOfReturn() == null){
            Issue issue = issueService.returnOfBook(issueId);
            model.addAttribute("issue", issue);
        }
        return "redirect:allIssues";
    }
}