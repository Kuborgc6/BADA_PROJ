package bada_gra_proj;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Klient_uslugaDAOTest {

	private Klient_uslugaDAO dao;

	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL6");
		/* datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf"); */
		datasource.setUsername("system");
		datasource.setPassword("dupa1234");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");

		/* Import JdbcTemplate */
		dao = new Klient_uslugaDAO(new JdbcTemplate(datasource));
	}
	
	@Test
	void testDeleteKlientUsluga() {
		int nr_klienta = 1;
		int nr_uslugi = 1;
		dao.deleteKlientUsluga(nr_klienta, nr_uslugi);
	}


	@Test
	void testDeleteKlient() {
		int nr_klienta = 123;
		int nr_uslugi = 2;
		dao.deleteKlient(nr_klienta);
	}
	
	@Test
	void testSELECTKlientUsluga() {
		int nr_klienta = 123;
		int nr_uslugi = 2;
		dao.selectKlientUsluga(nr_klienta,nr_uslugi);
	}


}
