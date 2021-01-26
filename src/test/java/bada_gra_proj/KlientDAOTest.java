package bada_gra_proj;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class KlientDAOTest {
	
private KlientDAO dao;
	
	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL6");
		/* datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf"); */
		datasource.setUsername("system");
		datasource.setPassword("dupa1234");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");

		/* Import JdbcTemplate */
		dao = new KlientDAO(new JdbcTemplate(datasource));
	}
	
	@Test
	void testList() {
		List<Klient> listKlient = dao.list();
		assertFalse(listKlient.isEmpty());
	}
	
	@Test
	void testSave() {
		Klient klient = new Klient(16, "Jakubowo", "Polska","1997-06-05", 1);
		dao.save(klient);
	}

	@Test
	void testUpdate() {
		Klient klient = new Klient();
		klient.setNr_klienta(7);
		klient.setNumer_kontaktu("Maćkowo");
		klient.setAdres_email("Niemcy");
		klient.setData_zalozenia_konta("2020-12-12");
		klient.setNr_adresu(1);
		dao.update(klient);
	}
	
	@Test
	void testShowUslugi() {
		int nr_uslugi = 0;
		List<Klient> listKlient = dao.connectUslugi(nr_uslugi);
		assertFalse(listKlient.isEmpty());
	}

	
	/*@Test
	void testShowOperatorzy() {
		int nr_operatora = 1;
		List<Klient> listKlient = dao.connectOperatorzy(nr_operatora);
		assertFalse(listKlient.isEmpty());
	}*/

}
