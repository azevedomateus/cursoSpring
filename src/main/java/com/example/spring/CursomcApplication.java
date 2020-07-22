package com.example.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring.domain.Categoria;
import com.example.spring.domain.Cidade;
import com.example.spring.domain.Cliente;
import com.example.spring.domain.Endereco;
import com.example.spring.domain.Estado;
import com.example.spring.domain.ItemPedido;
import com.example.spring.domain.Pagamento;
import com.example.spring.domain.PagamentoComBoleto;
import com.example.spring.domain.PagamentoComCartao;
import com.example.spring.domain.Pedido;
import com.example.spring.domain.Produto;
import com.example.spring.domain.enums.EstadoPagamento;
import com.example.spring.domain.enums.TipoCliente;
import com.example.spring.repository.CategoriaRepository;
import com.example.spring.repository.CidadeRepository;
import com.example.spring.repository.ClienteRepository;
import com.example.spring.repository.EnderecoRepository;
import com.example.spring.repository.EstadoRepository;
import com.example.spring.repository.ItemPedidoRepository;
import com.example.spring.repository.PagamentoRepository;
import com.example.spring.repository.PedidoRepository;
import com.example.spring.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;	
	@Autowired
	private CidadeRepository cidadeRepository;	
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônico");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli = new Cliente(null, "Maria Silva", "maria@silva.com", "33638939898", TipoCliente.PESSOAFISICA);
	
	    Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "23445678", cli, c1);
	    Endereco end2 = new Endereco(null, "Avenida Matos", "123", "sala 303", "Centro", "34567890", cli, c2);
	    
	    cli.getEnderecos().addAll(Arrays.asList(end1, end2));
	    
	    clienteRepository.saveAll(Arrays.asList(cli));
	    enderecoRepository.saveAll(Arrays.asList(end1, end2));
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    
	    Pedido ped1 = new Pedido(null, sdf.parse("09/10/2019"), cli, end1);
	    Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019"), cli, end2);
	    
	    Pagamento pagato1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	    ped1.setPagamento(pagato1);
	    
	    Pagamento pagato2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, 
	    		sdf.parse("20/10/2019"), null);
	    ped2.setPagamento(pagato2);
	    
	    cli.getPedidos().addAll(Arrays.asList(ped1, ped2));
	    
	    pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	    pagamentoRepository.saveAll(Arrays.asList(pagato1, pagato2));
	    
	    ItemPedido itemP1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
	    ItemPedido itemP2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	    ItemPedido itemP3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	    
	    ped1.getItens().addAll(Arrays.asList(itemP1, itemP2));
	    ped2.getItens().addAll(Arrays.asList(itemP3));
	    
	    p1.getItens().addAll(Arrays.asList(itemP1));
	    p2.getItens().addAll(Arrays.asList(itemP2));
	    p2.getItens().addAll(Arrays.asList(itemP3));
	    
	    itemPedidoRepository.saveAll(Arrays.asList(itemP1, itemP2, itemP3));
	    
	    
	}

}
