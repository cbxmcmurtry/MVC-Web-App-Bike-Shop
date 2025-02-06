package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
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
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        OutsourcedPart outsourcedPart = new OutsourcedPart();
        theModel.addAttribute("outsourcedpart", outsourcedPart);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel){
        theModel.addAttribute("outsourcedpart",part);
        if(bindingResult.hasErrors()){
            return "OutsourcedPartForm";
        }else{
            if (part.getInv() < part.getMinInv()) {
                bindingResult.rejectValue("inv", "error.outsourcedpart", "Inventory cannot be below the minimum inventory");
                return "OutsourcedPartForm";
            } else if (part.getInv() > part.getMaxInv()) {
                bindingResult.rejectValue("inv", "error.outsourcedpart", "Inventory cannot be above the maximum inventory");
                return "OutsourcedPartForm";
            }
        OutsourcedPartService repo=context.getBean(OutsourcedPartServiceImpl.class);
        OutsourcedPart op=repo.findById((int)part.getId());
        if(op!=null)part.setProducts(op.getProducts());
        repo.save(part);
        return "confirmationAddPart";
        }
    }
}
