package pl.lichon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.lichon.entity.Factory;
import pl.lichon.entity.Supervisor;
import pl.lichon.repository.FactoryRepository;
import pl.lichon.repository.SupervisorRepository;

//@SpringBootApplication

@CrossOrigin
@RestController
@RequestMapping("/factory")
public class FactoryController {

	@Autowired
	private FactoryRepository factoryRepository;
	
	@Autowired
	private SupervisorRepository supervisorRepository;
	
	@RequestMapping("/checkFactory")
	public Factory checkFactory() {
		return new Factory(1l, "FirstFactory", "Sweets");
	}
	
	@GetMapping("/list")
	public List<Factory> getList() {
		return factoryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Factory getFactory(@PathVariable long id) {
		return factoryRepository.getOne(id);
	}
	
	@RequestMapping("/testFactory")
	@ResponseBody
	public String testFactory() {
		factoryRepository.deleteAll();
		supervisorRepository.deleteAll();
		Supervisor supervisor1 = new Supervisor("Joey", "Tribbiani");
		supervisorRepository.save(supervisor1);
		Supervisor supervisor2 = new Supervisor("Chandler", "Bing");
		supervisorRepository.save(supervisor2);
		Supervisor supervisor3 = new Supervisor("Ross", "Geller");
		supervisorRepository.save(supervisor3);
		Factory factory1 = new Factory("Choclate Factory", "Candy");
		factory1.setSupervisor(supervisor1);
		factoryRepository.save(factory1);
		Factory factory2 = new Factory("Dream Factory", "Movies");
		factory2.setSupervisor(supervisor2);
		factoryRepository.save(factory2);
		Factory factory3 = new Factory("Brick Factory", "Homes");
		factory3.setSupervisor(supervisor3);
		factoryRepository.save(factory3);
		return "factories saved";
	}
}
