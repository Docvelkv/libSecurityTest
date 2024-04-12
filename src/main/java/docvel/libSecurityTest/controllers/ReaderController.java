package docvel.libSecurityTest.controllers;

import docvel.libSecurityTest.entyties.Reader;
import docvel.libSecurityTest.services.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("addReader")
    public String addReader(Model model){
        model.addAttribute("reader", new Reader());
        return "reader/addReader";
    }

    @PostMapping("saveReader")
    public String addNewReader(@ModelAttribute Reader reader){
        if(!reader.getName().isEmpty())
            readerService.addNewReader(reader);
        return "redirect:allReaders";
    }

    @GetMapping("deleteReader")
    public String deleteReader(@RequestParam Long readerId){
        if(readerId != null){
            readerService.deleteReader(readerId);
        }
        return "redirect:allReaders";
    }

    @GetMapping("updateReader")
    public String updateReader(@RequestParam Long readerId, Model model){
        Reader reader = readerService.getReaderById(readerId);
        model.addAttribute("reader", reader);
        return "reader/addReader";
    }
}
