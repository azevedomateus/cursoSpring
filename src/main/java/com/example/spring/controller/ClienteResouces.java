package com.example.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.domain.Cliente;
import com.example.spring.dto.ClienteDTO;
import com.example.spring.service.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value ="/clientes")
public class ClienteResouces {
	
	@Autowired
	private ClienteService service;

		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Cliente> find(@PathVariable Integer id) throws ObjectNotFoundException {
			Cliente obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}
		
	/*
	 * @RequestMapping(method = RequestMethod.POST) public ResponseEntity<Object>
	 * insert(@Valid @RequestBody ClienteDTO objDto) { Cliente obj =
	 * service.fromDTO(objDto); obj = service.insert(obj); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (obj.getId()).toUri(); return ResponseEntity.created(uri).build();
	 * 
	 * }
	 */
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Object> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id)
				throws ObjectNotFoundException {
			Cliente obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();

		}

		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Object> delete(@PathVariable Integer id) throws ObjectNotFoundException {
			service.delete(id);
			return ResponseEntity.noContent().build();

		}

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() throws ObjectNotFoundException {
			List<Cliente> list = service.findAll();
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);

		}
		

	}
