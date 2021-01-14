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
		/*datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf");*/
		datasource.setUsername("system");
		datasource.setPassword("dupa1234");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");
		
		/* Import JdbcTemplate */
		dao = new OperatorDAO(new JdbcTemplate(datasource));
	}

	@Test
	void testList() {
		List<Operator> listOperator = dao.list();
		assertTrue(listOperator.isEmpty());
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
