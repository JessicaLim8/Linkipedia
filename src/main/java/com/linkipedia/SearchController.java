package com.linkipedia;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	@GetMapping("/search")
	public String search(@RequestParam(name="firstInput", required=false, defaultValue="") String firstInput, @RequestParam(name="secondInput", required=false, defaultValue="") String secondInput, Model model) {
        ArrayList<Node> a = API.searchNode(ServingWebContentApplication.getGraph(), firstInput, 5);
		ArrayList<Node> b = API.searchNode(ServingWebContentApplication.getGraph(), secondInput, 5);
		model.addAttribute("firstInput", firstInput);
		model.addAttribute("secondInput", secondInput);
		model.addAttribute("result1", a.size() > 0 ? a.get(0).title() : "");
		model.addAttribute("result2", a.size() > 1 ? a.get(1).title() : "");
		model.addAttribute("result3", a.size() > 2 ? a.get(2).title() : "");
		model.addAttribute("result4", a.size() > 3 ? a.get(3).title() : "");
        model.addAttribute("result5", a.size() > 4 ? a.get(4).title() : "");

        model.addAttribute("end1", b.size() > 0 ? b.get(0).title() : "");
		model.addAttribute("end2", b.size() > 1 ? b.get(1).title() : "");
		model.addAttribute("end3", b.size() > 2 ? b.get(2).title() : "");
		model.addAttribute("end4", b.size() > 3 ? b.get(3).title() : "");
        model.addAttribute("end5", b.size() > 4 ? b.get(4).title() : "");
		return "search";
    }
}
