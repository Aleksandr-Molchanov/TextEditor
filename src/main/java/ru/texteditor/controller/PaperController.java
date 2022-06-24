package ru.texteditor.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.texteditor.model.Paper;
import ru.texteditor.model.User;
import ru.texteditor.service.PaperService;

import javax.servlet.http.HttpSession;
import java.util.Date;

@ThreadSafe
@Controller
public class PaperController {

    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/papers")
    public String papers(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("papers", paperService.findAll());
        return "papers";
    }

    @PostMapping("/savePaper")
    public String savePaper(@ModelAttribute Paper paper, HttpSession session) {
        User user = (User) session.getAttribute("user");
        paper.setCreated(new Date(System.currentTimeMillis()));
        paper.setUser(user);
        paper.setCreateUser(user.getName());
        paper.setModifyUser("");
        paperService.add(paper);
        return "redirect:/papers";
    }

    @GetMapping("/addPaper")
    public String addPaper(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        return "addPaper";
    }

    @GetMapping("/descriptionPaper/{paperId}")
    public String descriptionPaper(Model model, @PathVariable("paperId") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("paper", paperService.findById(id));
        return "descriptionPaper";
    }

    @GetMapping("/formUpdatePaper/{paperId}")
    public String formUpdatePaper(Model model, @PathVariable("paperId") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("paper", paperService.findById(id));
        return "updatePaper";
    }

    @PostMapping("/updatePaper")
    public String updatePaper(@ModelAttribute Paper paper, HttpSession session) {
        User user = (User) session.getAttribute("user");
        paper.setUser(user);
        paper.setCreateUser(user.getName());
        paper.setCreated(new Date(System.currentTimeMillis()));
        paperService.update(paper);
        return "redirect:/papers";
    }

    @GetMapping("/deletePaper/{paperId}")
    public String deletePaper(@PathVariable("paperId") int id) {
        paperService.delete(id);
        return "redirect:/papers";
    }

    @GetMapping("/setDone/{paperId}")
    public String setDone(@PathVariable("paperId") int id) {
        paperService.setDone(id);
        return "redirect:/papers";
    }

    @GetMapping("/newPapers")
    public String newPapers(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("papers", paperService.findByDone(false));
        return "papers";
    }

    @GetMapping("/donePapers")
    public String donePapers(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("papers", paperService.findByDone(true));
        return "papers";
    }

    @GetMapping("/formCorrectionPaper/{paperId}")
    public String formCorrectionPaper(Model model, @PathVariable("paperId") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("paper", paperService.findById(id));
        return "correctionPaper";
    }

    @PostMapping("/correctionPaper")
    public String correctionPaper(@ModelAttribute Paper paper, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Paper tmp = paperService.findById(paper.getId());
        paper.setUser(user);
        paper.setName(tmp.getName());
        paper.setCreateUser(tmp.getCreateUser());
        paper.setOldText(tmp.getOldText());
        paper.setCreated(tmp.getCreated());
        paper.setModifyUser(user.getName());
        paper.setModify(new Date(System.currentTimeMillis()));
        paperService.update(paper);
        return "redirect:/papers";
    }

    @GetMapping("/jsonPapers")
    public String jsonPapers(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("papers", paperService.findAll());
        return "jsonPapers";
    }

    @GetMapping("/jsonPaper/{paperId}")
    public String jsonPaper(Model model, @PathVariable("paperId") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("paper", paperService.findById(id));
        return "jsonPaper";
    }
}
