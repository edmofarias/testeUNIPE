package br.com.unipe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.unipe.domain.Example;
import br.com.unipe.util.LoggerPadrao;

@Service
public class ExampleService {

	private List<Example> lista = new ArrayList<Example>();

	public Example cadastrar(Example example) throws Exception {
		try {
			if(!containsName(example.getNome())) {
				lista.add(example);
				return example;
			}
	
			throw new Exception("Nome " + example.getNome() + " já existe");
		} catch (Exception e) {
			LoggerPadrao.error(e.getMessage(), e);
		}
		
		return null;
	}
	
	private boolean containsName(String name){
	    return lista.stream().filter(o -> o.getNome().equals(name)).findFirst().isPresent();
	}
	
	public List<Example> listar() {
		LoggerPadrao.info("Listagem solicitada!");
		return lista;
	}
	
	public Example pesquisar(String nome) {
		try {
			for (Example example : lista) {
				if(nome.equals(example.getNome())) {
					return example;
				}
			}
			
			throw new Exception("Nome " + nome + " não existe");
		} catch (Exception e) {
			LoggerPadrao.error(e.getMessage(), e);
		}
		
		return null;
	}
	
	public void delete(String nome) throws Exception {
		try {
			boolean del = false;
			
			for (int i = 0; i < lista.size(); i++) {
				if(nome.equals(lista.get(i).getNome())) {
					lista.remove(i);
					del = true;
				}
			}
			
			if(!del) {
				throw new Exception("Não existe Example com nome " + nome);
			}
		} catch (Exception e) {
			LoggerPadrao.error(e.getMessage(), e);
		}
	}
	
}
