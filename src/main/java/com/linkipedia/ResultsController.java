package com.linkipedia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ResultsController {
	@GetMapping("/results")
	public String results(@RequestParam(name="from", required=false, defaultValue="delta") String from, @RequestParam(name="to", required=false, defaultValue="charlie") String to, Model model) {
		// Create to and from variables from URL
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		Node fromNode = API.searchSingleNode(ServingWebContentApplication.getGraph(), from);
		Node toNode = API.searchSingleNode(ServingWebContentApplication.getGraph(), to);
		ArrayList<String> paths = API.pathToString(ServingWebContentApplication.getGraph(), fromNode, toNode, 5);
		for (int i = 0; i < 5;i++){
			model.addAttribute("path"+(i+1), paths.size() > i ? paths.get(i) : "");
		}

		return "results";
	}

}
