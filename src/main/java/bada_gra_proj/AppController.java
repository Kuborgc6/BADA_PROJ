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
	private Klient_uslugaDAO dao_ku;
	@Autowired
	private UslugaDAO dao_l;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		/* Import java.util.List , @ModelAttribute("adres_email") int adres_email */
		Login login = new Login();
		login.setNr_klienta(1);
		model.addAttribute("login", login);
		// model.addAttribute("adres_email", adres_email);
		return "index";
	}

	@RequestMapping("/klient_view/{nr_klienta}")
	public ModelAndView showKlientView(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("klient_view");
		Klient klient = dao_k.get(nr_klienta);
		mav.addObject(klient);
		List<Usluga> listUsluga = dao_l.connectKlient(klient.getNr_klienta());
		mav.addObject("listUsluga", listUsluga);
		
		return mav;
	}
	
	@RequestMapping("/klient_view/{nr_klienta}/edit")
	public ModelAndView editKlient(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("klient_edit");
		Klient klient = dao_k.get(nr_klienta);
		mav.addObject("klient", klient);
		return mav;
	}
	
	@RequestMapping(value = "/klient/update/{nr_klienta}", method = RequestMethod.POST)
	public String updateKlient(@PathVariable(name = "nr_klienta") int nr_klienta, @ModelAttribute("klient") Klient klient) {
		dao_k.update(klient);
		return "redirect:/klient_view/{nr_klienta}";
	}
		
	@RequestMapping("/klient_view/{nr_klienta}/uslugi/new")
	public ModelAndView newKlientUsluga(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("klient_usluga_new");
		List<Usluga> listUsluga = dao_l.connectKlientNOT(nr_klienta);
		mav.addObject("listUsluga", listUsluga);
		Klient klient = dao_k.get(nr_klienta);
		mav.addObject(klient);
		return mav;
	}
	
	@RequestMapping("/klient_view/uslugi/{nr_klienta}/add/{nr_uslugi}")
	public String addKlientUsluga(@PathVariable(name = "nr_klienta") int nr_klienta,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		Klient_usluga klient_usluga = new Klient_usluga(nr_klienta, nr_uslugi);
		dao_ku.saveKlientUslugi(klient_usluga);
		return "redirect:/klient_view/{nr_klienta}";
	}
	
	@RequestMapping("/klient_view/{nr_klienta}/uslugi/delete/{nr_uslugi}")
	public String deleteKlientUsluga(@PathVariable(name = "nr_klienta") int nr_klienta,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		dao_ku.deleteKlientUsluga(nr_klienta, nr_uslugi);
		return "redirect:/klient_view/{nr_klienta}";
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
		dao_ku.deleteUsluga(nr_uslugi);
		return "redirect:/admin/uslugi";
	}

	// ---------------------------------------------------
	// Admin uslugi klient view //,@ModelAttribute(name = "usluga") Usluga usluga
	@RequestMapping("/admin/uslugi/klienci/{nr_uslugi}")
	public ModelAndView editAdminUslugiKlient(@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		ModelAndView mav = new ModelAndView("admin_uslugi_klienci");
		List<Klient> listKlient = dao_k.connectUslugi(nr_uslugi);
		Usluga usluga = dao_l.get(nr_uslugi);
		mav.addObject("usluga", usluga);
		mav.addObject("listKlient", listKlient);
		System.out.print(usluga.toString());
		System.out.print(mav.toString());
		return mav;
	}

	@RequestMapping("/admin/uslugi/klienci/new/{nr_uslugi}")
	public String showNewAdminUslugiKlienci(Model model, @PathVariable(name = "nr_uslugi") int nr_uslugi) {
		Klient klient = new Klient();
		model.addAttribute("klient", klient);

		// model.addAttribute("nr_uslugi", nr_uslugi);
		Usluga usluga = dao_l.get(nr_uslugi);
		model.addAttribute("usluga", usluga);
		System.out.print(model.toString());
		System.out.print(usluga.toString());
		return "admin_uslugi_klienci_new";
	}

	@RequestMapping(value = "/admin/uslugi/klienci/save/{nr_uslugi}", method = RequestMethod.POST)
	public String saveAdminUslugiKlienci(@ModelAttribute("klient") Klient klient,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		System.out.print(klient.toString());
		Usluga usluga = dao_l.get(nr_uslugi);
		dao_k.save(klient);

		Klient_usluga klient_usluga = new Klient_usluga(klient.getNr_klienta(), usluga.getNr_uslugi());
		dao_ku.saveKlientUslugi(klient_usluga);

		return "redirect:/admin/uslugi/klienci/{nr_uslugi}";// klienci/{nr_uslugi}
	}

	@RequestMapping("/admin/uslugi/klienci/{nr_uslugi}/edit/{nr_klienta}")
	public ModelAndView editAdminUslugiKlienci(@PathVariable(name = "nr_klienta") int nr_klienta,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		ModelAndView mav = new ModelAndView("admin_uslugi_klienci_edit");
		Klient klient = dao_k.get(nr_klienta);
		Usluga usluga = dao_l.get(nr_uslugi);
		mav.addObject("usluga", usluga);

		mav.addObject("klient", klient);
		return mav;
	}

	@RequestMapping(value = "/admin/uslugi/klienci/{nr_uslugi}/update", method = RequestMethod.POST)
	public String updateAdminUslugiKlienci(@ModelAttribute("klient") Klient klient,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		dao_k.update(klient);
		return "redirect:/admin/uslugi/klienci/{nr_uslugi}";
	}

	@RequestMapping(value = "/admin/klienci/uslugi/klienci/{nr_uslugi}/delete/{nr_klienta}")
	public String deleteAdminUslugiKlient(@ModelAttribute(name = "nr_klienta") int nr_klienta,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		dao_k.delete(nr_klienta);
		dao_ku.deleteKlient(nr_klienta);
		return "redirect:/admin/uslugi/klienci/{nr_uslugi}";
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
		dao_ku.deleteKlient(nr_klienta);
		return "redirect:/admin/klienci";
	}

	// -------------------------------------
	@RequestMapping("/admin/klienci/uslugi/{nr_klienta}")
	public ModelAndView editAdminKlienciUslugi(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("admin_klienci_uslugi");
		List<Usluga> listUsluga = dao_l.connectKlient(nr_klienta);
		mav.addObject("listUsluga", listUsluga);
		Klient klient = dao_k.get(nr_klienta);
		mav.addObject(klient);
		return mav;
	}

	@RequestMapping("/admin/klienci/uslugi/{nr_klienta}/new")
	public ModelAndView newKlienciUsluga(@PathVariable(name = "nr_klienta") int nr_klienta) {
		ModelAndView mav = new ModelAndView("admin_klienci_uslugi_new");
		List<Usluga> listUsluga = dao_l.connectKlientNOT(nr_klienta);
		mav.addObject("listUsluga", listUsluga);
		Klient klient = dao_k.get(nr_klienta);
		mav.addObject(klient);
		return mav;
	}

	@RequestMapping("/admin/klienci/uslugi/{nr_klienta}/add/{nr_uslugi}")
	public String addKlienciUsluga(@PathVariable(name = "nr_klienta") int nr_klienta,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		Klient_usluga klient_usluga = new Klient_usluga(nr_klienta, nr_uslugi);
		dao_ku.saveKlientUslugi(klient_usluga);
		return "redirect:/admin/klienci/uslugi/{nr_klienta}";
	}

	@RequestMapping("/admin/klienci/uslugi/{nr_klienta}/delete/{nr_uslugi}")
	public String deleteKlienciUsluga(@PathVariable(name = "nr_klienta") int nr_klienta,
			@PathVariable(name = "nr_uslugi") int nr_uslugi) {
		dao_ku.deleteKlientUsluga(nr_klienta, nr_uslugi);
		return "redirect:/admin/klienci/uslugi/{nr_klienta}";
	}

}
