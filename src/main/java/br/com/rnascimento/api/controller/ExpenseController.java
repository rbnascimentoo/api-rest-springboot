package br.com.rnascimento.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rnascimento.api.dtos.ExpenseDTO;
import br.com.rnascimento.api.response.Response;
import br.com.rnascimento.api.services.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "expense")
@RestController
@RequestMapping(value = "/expense")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;

	@ApiOperation(value = "Find all expense.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<ExpenseDTO>>> findAll() {
		Response<List<ExpenseDTO>> response = new Response<List<ExpenseDTO>>();
		response.setData(this.expenseService.findAll());
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Create a new expense.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<ExpenseDTO>> create(@Valid @RequestBody ExpenseDTO expenseDTO, BindingResult result){
		Response<ExpenseDTO> response = new Response<ExpenseDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.expenseService.saveOrUpdate(expenseDTO));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Update an existing expense.")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<ExpenseDTO>> update(@Valid @RequestBody ExpenseDTO expenseDTO, BindingResult result){
		Response<ExpenseDTO> response = new Response<ExpenseDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.expenseService.saveOrUpdate(expenseDTO));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Find expense by id.")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<ExpenseDTO>> findById(@PathVariable(value = "id") Long id) {
		Response<ExpenseDTO> response = new Response<ExpenseDTO>();
		response.setData(this.expenseService.findById(id));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Delete an existing expense.")
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteById(@PathVariable(value = "id") Long id) {
		this.expenseService.deleteById(id);
	}
}