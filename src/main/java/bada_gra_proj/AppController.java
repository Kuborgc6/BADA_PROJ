package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private KlientDAO dao_k;
	@Autowired
	private OperatorDAO dao;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		/* Import java.util.List */
		List<Operator> listOperator = dao.list();
		model.addAttribute("listOperator", listOperator);

		return "index";
	}

	@RequestMapping("/new")
	public String showNewForm(Model model) {
		Operator operator = new Operator();
		model.addAttribute("operator", operator);

		return "new_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("operator") Operator operator) {
		dao.save(operator);

		return "redirect:/";
	}

	@RequestMapping("/edit/{nr_operatora}")
	public ModelAndView showEditForm(@PathVariable(name = "nr_operatora") int nr_operatora) {
		ModelAndView mav = new ModelAndView("edit_form");
		Operator operator = dao.get(nr_operatora);
		mav.addObject("operator", operator);
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("operator") Operator operator) {
		dao.update(operator);
		return "redirect:/";
	}

	@RequestMapping(value = "/delete/{nr_operatora}")
	public String delete(@ModelAttribute(name = "nr_operatora") int nr_operatora) {
		dao.delete(nr_operatora);
		return "redirect:/";
	}

	// ---------------------------------------------------
	// ---------------------------------------------------

	// Admin view

	@RequestMapping(value = "/admin")
	public String viewHomeAdminPage(Model model) {
		return "admin";
	}

	// ---------------------------------------------------
	// ---------------------------------------------------

	// Admin operatorzy view

	@RequestMapping("/admin/operatorzy")
	public String showAdminOperator(Model model) {
		List<Operator> listOperator = dao.list();
		model.addAttribute("listOperator", listOperator);

		return "admin_operatorzy";
	}

	@RequestMapping("/admin/operatorzy/new")
	public String showAdminOperatorzyNewForm(Model model) {
		Operator operator = new Operator();
		model.addAttribute("operator", operator);

		return "admin_operatorzy_new_form";
	}

	@RequestMapping(value = "/admin/operatorzy/save", method = RequestMethod.POST)
	public String adminOperatorzySave(@ModelAttribute("operator") Operator operator) {
		dao.save(operator);

		return "redirect:/admin/operatorzy";
	}

	@RequestMapping("/admin/operatorzy/edit/{nr_operatora}")
	public ModelAndView showAdminOperatorzyEditForm(@PathVariable(name = "nr_operatora") int nr_operatora) {
		ModelAndView mav = new ModelAndView("admin_operatorzy_edit_form");
		Operator operator = dao.get(nr_operatora);
		mav.addObject("operator", operator);
		return mav;
	}

	@RequestMapping(value = "/admin/operatorzy/update", method = RequestMethod.POST)
	public String updateAdminOperator(@ModelAttribute("operator") Operator operator) {
		dao.update(operator);
		return "redirect:/admin/operatorzy";
	}

	@RequestMapping(value = "/admin/operatorzy/delete/{nr_operatora}")
	public String deleteAdminOperator(@ModelAttribute(name = "nr_operatora") int nr_operatora) {
		dao.delete(nr_operatora);
		return "redirect:/admin/operatorzy";
	}
	// ---------------------------------------------------
	// Admin operatorzy klienci view

	@RequestMapping("/admin/operatorzy/klienci/{nr_operatora}")
	public String showAdminOperatorKlient(@PathVariable(name = "nr_operatora") int nr_operatora, Model model) {
		List<Klient> listKlient = dao_k.connectOperatorzy(nr_operatora);
		model.addAttribute("listKlient", listKlient);

		return "admin_operatorzy_klienci";
	}

	// ---------------------------------------------------
	// ---------------------------------------------------
	// Admin klienci view

	@RequestMapping("/admin/klienci")
	public String showAdminKlient(Model model) {
		List<Klient> listKlient = dao_k.list();
		model.addAttribute("listKlient", listKlient);

		return "admin_klienci";
	}

}
