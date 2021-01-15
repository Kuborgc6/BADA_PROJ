package bada_gra_proj;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class OperatorDAOTest {

	private OperatorDAO dao;

	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL6");
		/* datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf"); */
		datasource.setUsername("system");
		datasource.setPassword("dupa1234");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");

		/* Import JdbcTemplate */
		dao = new OperatorDAO(new JdbcTemplate(datasource));
	}

	@Test
	void testList() {
		List<Operator> listOperator = dao.list();
		assertFalse(listOperator.isEmpty());
	}

	@Test
	void testSave() {
		Operator operator = new Operator(7, "Jakubowo", "1997-06-05", "Polska", "135353568");
		dao.save(operator);
	}

	@Test
	void testGet() {
		int nr_operatora = 10;
		Operator operator = dao.get(nr_operatora);
		assertNotNull(operator);
	}

	@Test
	void testUpdate() {
		Operator operator = new Operator();
		operator.setNr_operatora(7);
		operator.setNazwa("Maćkowo");
		operator.setKraj_centrali("Niemcy");
		operator.setNIP("12");
		operator.setData_zalozenia("2020-12-12");
		dao.update(operator);
	}

	@Test
	void testDelete() {
		int nr_operatora = 11;
		dao.delete(nr_operatora);
	}

}
