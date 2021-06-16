package com.webec.WGApplication.controller;

import com.webec.WGApplication.model.BillEntry;
import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.service.BillService;
import com.webec.WGApplication.service.ToDoService;
import com.webec.WGApplication.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    private final BillService billservice;
    private final ToDoService todoservice;

    public IndexController(BillService billservice, ToDoService todoservice) {
        this.billservice = billservice;
        this.todoservice = todoservice;
    }

    @RequestMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BillEntry> createdBills = billservice.getBillsByCreator(user.getId());
        List<BillEntry> userBills = billservice.getBillsByUser(user.getId());

        model.addAttribute("currentUser", user.getUsername());
        model.addAttribute("currentUserBillsCreated", createdBills);
        model.addAttribute("currentUserBills", userBills);
        model.addAttribute("debt", userBills.stream().map(b -> ( b.amount / (b.users.size() + 1))).reduce(0.0, Double::sum));
        model.addAttribute("credit", createdBills.stream().map(b -> (b.amount / (b.users.size() + 1) * b.users.size())).reduce(0.0, Double::sum));
        model.addAttribute("currentUserToDos", todoservice.getToDosByAssginee(user.getId()));
        return "index";
    }
}
