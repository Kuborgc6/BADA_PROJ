package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private KlientDAO dao_k;
	@Autowired
	private OperatorDAO dao;
	@Autowired
	private UslugaDAO dao_l;

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
	public String showAdminOperatorKlient(@ModelAttribute(name = "nr_operatora") int nr_operatora, Model model) {
		List<Klient> listKlient = dao_k.connectOperatorzy(nr_operatora);
		model.addAttribute("nr_operatora", nr_operatora);
		Operator operator = dao.get(nr_operatora);
		model.addAttribute("operator", operator);
		model.addAttribute("listKlient", listKlient);

		return "admin_operatorzy_klienci";
	}
	
	@RequestMapping("/admin/operatorzy/klienci/new")/**/
	public String showNewAdminOperatorKlienci(@ModelAttribute ("operator") Operator operator, Model model) {
		Klient klient = new Klient();
		/*model.addAttribute("nr_operatora", nr_operatora);
		Operator operator = dao.get(nr_operatora);*/
		model.addAttribute("operator", operator);
		model.addAttribute("klient", klient);
		return "admin_operatorzy_klienci_new";
	}

	@RequestMapping(value = "/admin/operatorzy/klienci/save", method = RequestMethod.POST)
	public String saveAdminOperatorKlienci(@ModelAttribute ("nr_operatora") int nr_operatora, @ModelAttribute("klient") Klient klient, Model model) {
		System.out.print(klient.toString());
		dao_k.save(klient);
		/*int nr_operatora = operator.getNr_operatora();*/
		/*model.addAttribute("nr_operatora", nr_operatora);*/

		return "redirect:/admin/operatorzy/{nr_operatora}";
	}
	
	@RequestMapping("/admin/operatorzy/klienci/edit/{nr_klienta}")
	public ModelAndView editAdminOperatorKlienci(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("admin_operatorzy_klienci_edit");
		Klient klient = dao_k.get(nr_klienta);
		mav.addObject("klient", klient);
		return mav;
	}
	
	@RequestMapping(value = "/admin/operatorzy/klienci/update", method = RequestMethod.POST)
	public String updateAdminOperatorKlient(@ModelAttribute("klient") Klient klient) {
		dao_k.update(klient);
		return "redirect:/admin/operatorzy/klienci";
	}
	
	@RequestMapping(value = "/admin/operatorzy/klienci/delete/{nr_klienta}")
	public String deleteAdminOperatorKlient(@ModelAttribute(name = "nr_klienta") int nr_klienta) {
		dao_k.delete(nr_klienta);
		return "redirect:/admin/operatorzy/klienci";
	}

	// ---------------------------------------------------
	// ---------------------------------------------------
	// Admin uslugi view

	@RequestMapping("/admin/uslugi")
	public String showAdminUslugi(Model model) {
		List<Usluga> listUsluga = dao_l.list();
		model.addAttribute("listUsluga", listUsluga);

		return "admin_uslugi";
	}
	
	@RequestMapping("/admin/uslugi/new")
	public String showNewAdminUslugi(Model model) {
		Usluga usluga = new Usluga();
		model.addAttribute("usluga", usluga);

		return "admin_uslugi_new";
	}
	
	@RequestMapping(value = "/admin/uslugi/save", method = RequestMethod.POST)
	public String saveAdminUslugi(@ModelAttribute("usluga") Usluga usluga) {
		System.out.print(usluga.toString());
		dao_l.save(usluga);

		return "redirect:/admin/uslugi";
	}
	
	@RequestMapping("/admin/uslugi/edit/{nr_uslugi}")
	public ModelAndView editAdminUslugi(@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		ModelAndView mav = new ModelAndView("admin_uslugi_edit");
		Usluga usluga = dao_l.get(nr_uslugi);
		mav.addObject("usluga", usluga);
		return mav;
	}
	
	@RequestMapping(value = "/admin/uslugi/update", method = RequestMethod.POST)
	public String updateAdminUsluga(@ModelAttribute("uslugi") Usluga usluga) {
		System.out.print(usluga.toString());

		dao_l.update(usluga);
		return "redirect:/admin/uslugi";
	}
	
	@RequestMapping(value = "/admin/uslugi/delete/{nr_uslugi}")
	public String deleteAdminUsluga(@ModelAttribute(name = "nr_uslugi") int nr_uslugi) {
		dao_l.delete(nr_uslugi);
		return "redirect:/admin/uslugi";
	}
	
	// ---------------------------------------------------
	// Admin uslugi operator view 
	@RequestMapping("/admin/uslugi/operator/{nr_uslugi}")
	public ModelAndView editAdminUslugiOperator(@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		ModelAndView mav = new ModelAndView("admin_uslugi_operator");
		Operator operator = dao.connectKlient(nr_uslugi);
		mav.addObject("operator", operator);
		return mav;
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

	@RequestMapping("/admin/klienci/new")
	public String showNewAdminKlienci(Model model) {
		Klient klient = new Klient();
		model.addAttribute("klient", klient);

		return "admin_klienci_new";
	}

	@RequestMapping(value = "/admin/klienci/save", method = RequestMethod.POST)
	public String saveAdminKlienci(@ModelAttribute("klient") Klient klient) {
		System.out.print(klient.toString());
		dao_k.save(klient);

		return "redirect:/admin/klienci";
	}
	
	@RequestMapping("/admin/klienci/edit/{nr_klienta}")
	public ModelAndView editAdminKlienci(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("admin_klienci_edit");
		Klient klient = dao_k.get(nr_klienta);
		mav.addObject("klient", klient);
		return mav;
	}
	
	@RequestMapping(value = "/admin/klienci/update", method = RequestMethod.POST)
	public String updateAdminKlient(@ModelAttribute("klient") Klient klient) {
		dao_k.update(klient);
		return "redirect:/admin/klienci";
	}
	
	@RequestMapping(value = "/admin/klienci/delete/{nr_klienta}")
	public String deleteAdminKlient(@ModelAttribute(name = "nr_klienta") int nr_klienta) {
		dao_k.delete(nr_klienta);
		return "redirect:/admin/klienci";
	}
	
	//-------------------------------------
	@RequestMapping("/admin/klienci/operator/{nr_klienta}")
	public ModelAndView editAdminKlienciOperator(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("admin_klienci_operator");
		Operator operator = dao.connectKlient(nr_klienta);
		mav.addObject("operator", operator);
		return mav;
	}

}
