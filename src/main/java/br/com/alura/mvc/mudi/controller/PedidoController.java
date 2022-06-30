package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
       return "pedido/formulario";
    }

    //Colocamos o @Valid do Bean Validation e um BindingResult que traz o relatorio de validação.
    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {

        //testamos se há erros no formulário
        if (result.hasErrors()) return "pedido/formulario";

        Pedido pedido = requisicaoNovoPedido.toPedido();
        pedidoRepository.save(pedido);
        return "";
    }
}
