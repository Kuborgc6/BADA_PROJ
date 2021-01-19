package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class KlientDAO {
	@Autowired
	/* Import org.springframework.jd..Template */
	private JdbcTemplate jdbcTemplate;

	/* Constructor for jdbcTemplate */
	public KlientDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* List with information with data from database */
	public List<Klient> list() {
		String sql = "SELECT * FROM KLIENCI";

		List<Klient> listKlient = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klient.class));
		return listKlient;
	}

	/* Insert */
	public void save(Klient klient) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("klienci").usingColumns("nr_klienta", "numer_kontaktu", "adres_email",
				"data_zalozenia_konta", "nr_adresu");
		/**/

		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
		insertActor.execute(param);
	}

	/* Read */
	public Operator get(int nr_klienta) {
		Object[] args = { nr_klienta };
		String sql = "SELECT * FROM KLIENCI WHERE NR_KLIENTA = " + args[0];
		Operator klient = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Operator.class));
		return klient;
	}

	/* Update */
	public void update(Klient klient) {
		String sql = "UPDATE KLIENCI SET nr_adresu=:nr_adresu, numer_kontaktu=:numer_kontaktu, adres_email=:adres_email, data_zalozenia_konta=:data_zalozenia_konta WHERE nr_klienta=:nr_klienta";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}

	/* Delete */
	public void delete(int nr_klienta) {
		String sql = "DELETE FROM KLIENCI WHERE nr_klienta = ?";
		jdbcTemplate.update(sql, nr_klienta);
	}

}
