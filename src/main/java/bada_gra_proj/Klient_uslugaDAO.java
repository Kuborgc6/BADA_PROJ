package bada_gra_proj;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
@Repository
public class Klient_uslugaDAO {
	
	@Autowired
	/* Import org.springframework.jd..Template */
	private JdbcTemplate jdbcTemplate;

	/* Constructor for jdbcTemplate */
	public Klient_uslugaDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/*Add record to Klient_Usługa table connecting klient and uslugi */
	public void saveKlientUslugi(Klient_usluga klient_usluga) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Klient_Usługa").usingColumns("NR_KLIENTA", "NR_USLUGI");
		/**/

		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient_usluga);
		insertActor.execute(param);
	}

	public void deleteKlient(int nr_klienta) {
		String sql = "DELETE FROM Klient_Usługa WHERE nr_klienta = ?";
		jdbcTemplate.update(sql, nr_klienta);
	}
	
	public void deleteUsluga(int nr_uslugi) {
		String sql = "DELETE FROM Klient_Usługa WHERE nr_klienta = ?";
		jdbcTemplate.update(sql, nr_uslugi);
	}
	
	
	public void deleteKlientUsluga(int nr_uslugi, int nr_klienta) {
		String sql = "DELETE FROM Klient_Usługa WHERE nr_uslugi = " + nr_uslugi + " AND nr_klienta = " + nr_klienta;
		jdbcTemplate.update(sql, nr_uslugi);
	}


	

}
