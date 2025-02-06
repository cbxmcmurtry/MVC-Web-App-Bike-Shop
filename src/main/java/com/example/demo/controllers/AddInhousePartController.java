package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddInhousePartController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousePart=new InhousePart();
        theModel.addAttribute("inhousepart",inhousePart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult bindingResult, Model theModel) {
        theModel.addAttribute("inhousepart", part);
        if (bindingResult.hasErrors()) {
            return "InhousePartForm";
        } else {
            if (part.getInv() < part.getMinInv()) {
                bindingResult.rejectValue("inv", "error.inhousepart", "Inventory cannot be below the minimum inventory");
                return "InhousePartForm";
            } else if (part.getInv() > part.getMaxInv()) {
                bindingResult.rejectValue("inv", "error.inhousepart", "Inventory cannot be above the maximum inventory");
                return "InhousePartForm";
            }
            InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
            InhousePart ip = repo.findById((int) part.getId());
            if (ip != null) part.setProducts(ip.getProducts());
            repo.save(part);
            return "confirmationAddPart";
        }
    }
}
