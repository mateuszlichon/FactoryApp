package pl.lichon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.lichon.entity.Factory;
import pl.lichon.repository.FactoryRepository;

@RestController
@RequestMapping("/factory")
public class FactoryController {

	@Autowired
	private FactoryRepository factoryRepository;
	
	@RequestMapping("/checkFactory")
	public Factory checkFactory() {
		return new Factory(1l, "FirstFactory", "Sweets");
	}
}
