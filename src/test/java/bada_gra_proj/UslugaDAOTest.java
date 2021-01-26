package bada_gra_proj;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class UslugaDAOTest {
	
	private UslugaDAO dao;
	
	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL6");
		/* datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf"); */
		datasource.setUsername("system");
		datasource.setPassword("dupa1234");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");

		/* Import JdbcTemplate */
		dao = new UslugaDAO(new JdbcTemplate(datasource));
	}
	
	@Test
	void testList() {
		List<Usluga> listUsluga = dao.list();
		assertFalse(listUsluga.isEmpty());
	}

	@Test
	void testShowUslugi() {
		int nr_klienta = 1;
		List<Usluga> listUsluga = dao.connectKlient(nr_klienta);
		assertFalse(listUsluga.isEmpty());
	}
	
	@Test
	void testUpdate() {
		Usluga usluga = new Usluga();
		usluga.setNr_operatora(123);
		usluga.setNazwa("Maćkowo");
		usluga.setKoszt(500);
		usluga.setNr_uslugi(123);
		dao.update(usluga);
	}

	@Test
	void testSave() {
		Usluga usluga = new Usluga(7, "Jakubowo", 1231, 123);
		dao.save(usluga);
	}

}
