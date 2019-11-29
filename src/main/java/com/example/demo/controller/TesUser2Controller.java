package com.example.demo.controller;
import java.util.List;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.*;

@RestController
@RequestMapping("/cuic")
public class TesUser2Controller {
	@Autowired
	private TesService TesService;
	
	@GetMapping("/durationHold")
	public List<TesUserModel> testDh() {
		return TesService.getDurationHold();
	}
}
