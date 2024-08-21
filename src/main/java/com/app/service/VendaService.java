package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Cliente;
import com.app.entity.Funcionario;
import com.app.entity.Item;
import com.app.entity.Venda;
import com.app.repository.ClienteRepository;
import com.app.repository.FuncionarioRepository;
import com.app.repository.ItemRepository;
import com.app.repository.VendaRepository;

@Service
public class VendaService {

        @Autowired
        private  VendaRepository vendaRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private FuncionarioRepository funcionarioRepository;

        @Autowired
        private ItemRepository itemRepository;


        public String save(Venda venda) {
            if (venda.getFuncionario() != null && venda.getFuncionario().getId() != null) {
                Funcionario funcionario = funcionarioRepository.findById(venda.getFuncionario().getId())
                        .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o funcionario"));
                venda.setFuncionario(funcionario);
            }

            if (venda.getCliente() != null && venda.getCliente().getId() != null) {
                Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                        .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o cliente"));
                venda.setCliente(cliente);
            }

            if (venda.getItens() != null) {
                List<Item> items = venda.getItens().stream()
                        .map(item -> itemRepository.findById(item.getId())
                                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o item")))
                        .collect(Collectors.toList());
                venda.setItens(items);
            }

            vendaRepository.save(venda);
            return "Venda realizada com sucesso!";
        }

        public void saveAll(List<Venda> vendas) {
            for (Venda venda : vendas) {
                if (venda.getFuncionario() != null && venda.getFuncionario().getId() != null) {
                    Funcionario funcionario = funcionarioRepository.findById(venda.getFuncionario().getId())
                            .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o funcionario"));
                    venda.setFuncionario(funcionario);
                }

                if (venda.getCliente() != null && venda.getCliente().getId() != null) {
                    Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                            .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o cliente"));
                    venda.setCliente(cliente);
                }

                if (venda.getItens() != null && !venda.getItens().isEmpty()) {
                    List<Item> itens = venda.getItens().stream()
                            .map(item -> itemRepository.findById(item.getId())
                                    .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o autor")))
                            .collect(Collectors.toList());
                    venda.setItens(itens);
                }
            }
            vendaRepository.saveAll(vendas);
        }

        public List<Venda> findAll() {
            return vendaRepository.findAll();
        }

        public Venda findById(long id) {
            return vendaRepository.findById(id).orElse(null);
        }

        public String update(Venda venda, long id) {
            if (vendaRepository.existsById(id)) {
                venda.setId(id);
                vendaRepository.save(venda);
                return "Atualizado!";
            } else {
                return "Não foi possivel encontrar a venda";
            }
        }

        public String delete(long id) {
            if (vendaRepository.existsById(id)) {
                vendaRepository.deleteById(id);
                return "Deletado";
            } else {
                return "Não foi impossivel encontrar a venda";
            }
        }

        public void deleteAll() {
            vendaRepository.deleteAll();
        }

        public List<Venda> findByValorAcima(Double total_valor){
            return  this.vendaRepository.findByValorAcima(total_valor);
        }

        public List<Venda> findByValorAbaixo(Double total_valor){
            return  this.vendaRepository.findByValorAbaixo(total_valor);
        }

    }
    
    

