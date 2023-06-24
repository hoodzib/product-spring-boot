package com.example.carlendar.controller;


import com.example.carlendar.entity.Amount;
import com.example.carlendar.entity.ProductEntity;
import com.example.carlendar.entity.RecordEntity;
import com.example.carlendar.entity.TypeEntity;
import com.example.carlendar.repository.ProductRepository;
import com.example.carlendar.repository.RecordRepository;
import com.example.carlendar.repository.TypeRepository;
import com.example.carlendar.service.impl.SearchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class CalController {

    @Autowired
    private SearchService service;

    @Autowired
    private ProductRepository pr;

    @Autowired
    private RecordRepository rr;

    @Autowired
    private TypeRepository tr;


    @GetMapping("/")
    public String Main(ModelMap model) {
        List<RecordEntity> products = rr.findAll();
        Integer amount = products.stream()
                .map(RecordEntity -> RecordEntity.getAmount())
                .reduce(0, Integer::sum);
        Float price = products.stream()
                .map(RecordEntity -> RecordEntity.getPrice())
                .reduce(0f, Float::sum);
        model.addAttribute("main", rr.findAll());
        model.addAttribute("TotalPrice", price);
        model.addAttribute("TotalAmount", amount);
        return "main";
    }

    @GetMapping("/product")
    public String product(ModelMap model) {
        model.addAttribute("product", pr.findAll());
        return "product";
    }

    @GetMapping("/product/add")
    public String saveTable(Model model) {
        ProductEntity pe = new ProductEntity();
        LocalDate localDate = LocalDate.now();
        pe.setDate(localDate);
        model.addAttribute("formPD", pe);
        return "add";
    }

    @PostMapping("/product/save")
    public String save(@Valid @ModelAttribute("formPD") ProductEntity pe, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        Optional<TypeEntity> type = tr.findById(pe.getFromTypeId());
        ProductEntity e = new ProductEntity();
        LocalDate localDate = LocalDate.now();
        e.setId(pe.getId());
        e.setName(pe.getName());
        e.setPrice(pe.getPrice());
        e.setTypeEntity(type.get());
        e.setAmount(pe.getAmount());
        e.setDate(localDate);
        pr.save(e);
        return "redirect:/product";
    }

    @GetMapping("/product/updatePD/{id}")
    public String updatePD(@PathVariable("id") long id, Model model) {
        Optional<ProductEntity> temp = pr.findById(id);
        ProductEntity pe = temp.get();
        LocalDate localDate = LocalDate.now();
        pe.setDate(localDate);
        pe.setFromTypeId(pe.getTypeEntity().getType_id());
        model.addAttribute("formPD", pe);
        return "updatePD";
    }

    @GetMapping("/product/deletePD/{id}")
    public String deletePage(@PathVariable("id") long id) {
        pr.deleteById(id);
        return "redirect:/product";
    }

    @RequestMapping(path = {"product/search"})
    public String home(ProductEntity shop, Model model, String keyword) {
        if (keyword != null) {
            List<ProductEntity> list = service.getByKeywordPD(keyword);
            model.addAttribute("product", list);
        } else {
            List<ProductEntity> list = service.getAllPD();
            model.addAttribute("product", list);
        }
        return "product";
    }


    @GetMapping("/customer")
    public String customer(ModelMap model) {
        model.addAttribute("customer", pr.findAll());
        model.addAttribute("formBuy", new Amount());
        return "customer";
    }

    @PostMapping("/customer/buy")
    public String buy(Model model, @ModelAttribute("formBuy") Amount em) {
        LocalDate localDate = LocalDate.now();
        Optional<ProductEntity> temp = pr.findById(em.getProductId());
        ProductEntity pe = temp.get();
        RecordEntity e = new RecordEntity();
        e.setName(temp.get().getName());
        e.setId(temp.get().getId());
        e.setPrice(temp.get().getPrice());
        e.setAmount(em.getId());
        e.setDate(localDate);
        e.setTypeEntity(pe.getTypeEntity());
        rr.save(e);
        pe.setAmount(pe.getAmount() - em.getId());
        if (pe.getAmount() == 0) {
            pr.deleteById(pe.getId());
        } else {
            pr.save(pe);
        }
        return "redirect:/customer";
    }

    @RequestMapping(path = {"/customer/search"})
    public String customer( Model model, String keyword) {
        if (keyword != null) {
            List<ProductEntity> list = service.getByKeywordPD(keyword);
            model.addAttribute("customer", list);
            model.addAttribute("formBuy", new Amount());
        } else {
            List<ProductEntity> list = service.getAllPD();
            model.addAttribute("customer", list);
            model.addAttribute("formBuy", new Amount());
        }
        return "customer";
    }

    @RequestMapping(path = {"/product/main/search"})
    public String main(RecordEntity shop, Model model, String keyword) {
        if (keyword != null) {
            List<RecordEntity> list = service.getByKeywordRC(keyword);
            Integer amount = list.stream()
                    .map(RecordEntity -> RecordEntity.getAmount())
                    .reduce(0, Integer::sum);
            Float price = list.stream()
                    .map(RecordEntity -> RecordEntity.getPrice())
                    .reduce(0f, Float::sum);
            model.addAttribute("main", list);
            model.addAttribute("TotalPrice", price);
            model.addAttribute("TotalAmount", amount);
        } else {
            List<RecordEntity> list = service.getAllRC();
            Integer amount = list.stream()
                    .map(RecordEntity -> RecordEntity.getAmount())
                    .reduce(0, Integer::sum);
            Float price = list.stream()
                    .map(RecordEntity -> RecordEntity.getPrice())
                    .reduce(0f, Float::sum);
            model.addAttribute("main", list);
            model.addAttribute("TotalPrice", price);
            model.addAttribute("TotalAmount", amount);

        }
        return "main";
    }
}
