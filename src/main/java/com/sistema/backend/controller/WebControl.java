package com.sistema.backend.controller;

import com.sistema.backend.model.Produto;
import com.sistema.backend.repository.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebControl {

    @GetMapping("/")
    public String index() {
        return "redirect:/produtos";
    }


    @Autowired
    private ProdutoDAO produtoDAO;

    @GetMapping("/produtos")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoDAO.findAll());
        return "produtos";
    }

    @GetMapping("/produtos/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "form";
    }

    @GetMapping("/produtos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoDAO.findById(id).get());
        return "form";
    }

    @PostMapping("/produtos/salvar")
    public String salvar(@ModelAttribute Produto produto, RedirectAttributes ra) {
        produtoDAO.save(produto);
        ra.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        produtoDAO.deleteById(id);
        ra.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
        return "redirect:/produtos";
    }
}
