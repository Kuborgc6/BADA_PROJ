package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
	
	@Autowired
	private OperatorDAO dao;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		/* Import java.util.List*/
		List<Operator> listOperator = dao.list();
		model.addAttribute("listOperator", listOperator);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewForm(Model model) {
		Operator operator = new Operator();
		model.addAttribute("operator",operator);
		
		return "new_form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("operator") Operator operator) {
		dao.save(operator);
		
		return "redirect:/";
	}
}
