package br.com.ejtc.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ejtc.model.Cidade;
import br.com.ejtc.model.Cliente;
import br.com.ejtc.model.Endereco;
import br.com.ejtc.model.Entrega;
import br.com.ejtc.model.Estado;
import br.com.ejtc.model.Rota;
import br.com.ejtc.repositories.CidadeRepository;
import br.com.ejtc.repositories.ClienteRepository;
import br.com.ejtc.repositories.EnderecoRepository;
import br.com.ejtc.repositories.EntregaRepository;
import br.com.ejtc.repositories.EstadoRepository;
import br.com.ejtc.repositories.RotaRepository;

@Service
public class _DBService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private RotaRepository rotaRepository;

	public void instantiateTestDatabase() throws ParseException, IOException {
		
		Estado estado1 = new Estado(null, "Espírito Santo", "ES");
		Estado estado2 = new Estado(null, "Minas Gerais", "MG");
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		
		Cidade c1 = new Cidade(null, "Cachoeiro de Itapemirim", estado1);
		Cidade c2 = new Cidade(null, "Belo Horizonte", estado2);
		
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		
		Cliente cliente1 = new Cliente(null, "Leidiane", "111.222.333-44");
		Cliente cliente2 = new Cliente(null, "Empresa 1", "XX. XXX. XXX/0001-XX");
		Cliente cliente3 = new Cliente(null, "Outra Empresa", "XX. XXX. XXX/0001-XX");
		Cliente cliente4 = new Cliente(null, "Outra Empresa 2", "XX. XXX. XXX/0001-XX");
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4));
		
		Endereco e1 = new Endereco(null, "29301-040", "R. Dirceu Alves de Medeiros", "Vl Rica", 49, false, c1, cliente1);
		Endereco e2 = new Endereco(null, "29300-120", "R. 25 de Março", "Centro", 28, false, c1, cliente2);
		Endereco e3 = new Endereco(null, "29300-500", "Av. Jones dos Santos Neves", "Santo Antônio", 306, false, c1, cliente3);
		Endereco e4 = new Endereco(null, "29300-345", "Av. Pinheiro Júnior", "Aquidaban", 24, false, c1, cliente4);

		
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Rota r1 = new Rota(null, sdf.parse("2022-11-01"), "Entrega 1");
		
		rotaRepository.saveAll(Arrays.asList(r1));
		
		Entrega entrega1 = new Entrega(null, false, -20.8545876, -41.1069344, r1, e1); // lembrar de remover essa parada de coordenadas
		Entrega entrega2 = new Entrega(null, false, -20.8331093, -41.1241824, r1, e3);
		Entrega entrega3 = new Entrega(null, false, -20.8331093, -41.1241824, r1, e2);
		Entrega entrega4 = new Entrega(null, false, -20.8331093, -41.1241824, r1, e4);
		
		entregaRepository.saveAll(Arrays.asList(entrega1, entrega2, entrega3, entrega4));
		
 	}
}
