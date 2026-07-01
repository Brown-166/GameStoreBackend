package com.sistema.backend.controller;

import com.sistema.backend.model.Produto;
import com.sistema.backend.repository.ProdutoDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WebControl {

    @GetMapping("/")
    public String index() {
        return "redirect:/produtos";
    }


    @Autowired
    private ProdutoDAO produtoDAO;

    @GetMapping("/produtos")
    public String listar(@RequestParam(value = "busca", required = false) String busca, Model model) {
        List<Produto> produtos;
        if (busca != null && !busca.isBlank()) {
            produtos = produtoDAO.buscar(busca.trim());
        } else {
            produtos = produtoDAO.findAll();
        }
        model.addAttribute("produtos", produtos);
        model.addAttribute("busca", busca);
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
    public String salvar(@Valid @ModelAttribute("produto") Produto produto, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            // Retorna ao formulário mantendo os dados digitados e exibindo as mensagens de erro
            return "form";
        }
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
