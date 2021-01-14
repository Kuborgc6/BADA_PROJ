package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
