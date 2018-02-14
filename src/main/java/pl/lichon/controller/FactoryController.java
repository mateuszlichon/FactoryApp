package pl.lichon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping("/testFactory")
	@ResponseBody
	public String testFactory() {
		Factory factory1 = new Factory("Choclate Factory", "Candy");
		factoryRepository.save(factory1);
		Factory factory2 = new Factory("Dream Factory", "Movies");
		factoryRepository.save(factory2);
		Factory factory3 = new Factory("Brick Factory", "Homes");
		factoryRepository.save(factory3);
		return "factories saved";
	}
}
