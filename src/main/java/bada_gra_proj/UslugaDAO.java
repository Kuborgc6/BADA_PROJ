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

public class UslugaDAO {

	@Autowired
	/* Import org.springframework.jd..Template */
	private JdbcTemplate jdbcTemplate;

	/* Constructor for jdbcTemplate */
	public UslugaDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* List with information with data from database */
	public List<Usluga> list() {
		String sql = "SELECT * FROM USLUGI";

		List<Usluga> listUsluga = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Usluga.class));
		return listUsluga;
	}

	/* Insert */
	public void save(Usluga usluga) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("uslugi").usingColumns("nr_uslugi", "nazwa", "koszt", "nr_operatora");
		/**/

		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(usluga);
		insertActor.execute(param);
	}

	/* Read */
	public Usluga get(int nr_uslugi) {
		Object[] args = { nr_uslugi };
		String sql = "SELECT * FROM USLUGI WHERE NR_USLUGI = " + args[0];
		Usluga usluga = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Usluga.class));
		return usluga;
	}

	/* Update */
	public void update(Usluga usluga) {
		String sql = "UPDATE USLUGI SET NAZWA=:nazwa, KOSZT=:koszt, NR_OPERATORA=:nr_operatora WHERE nr_uslugi=:nr_uslugi";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(usluga);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}

	/* Delete */
	public void delete(int nr_uslugi) {
		String sql = "DELETE FROM USLUGI WHERE nr_uslugi = ?";
		jdbcTemplate.update(sql, nr_uslugi);
	}
	
	/* Show all Uslugi in certain Klient */
	public List<Usluga> connectKlient(int nr_klienta) {
		Object[] args = { nr_klienta };
		String sql = "select uslugi.KOSZT, uslugi.NAZWA, uslugi.NR_OPERATORA, uslugi.NR_USLUGI from uslugi \r\n"
				+ "join Klient_Usługa\r\n"
				+ "on Klient_Usługa.nr_uslugi = uslugi.nr_uslugi\r\n"
				+ "join klienci\r\n"
				+ "on klienci.nr_klienta = Klient_Usługa.nr_klienta\r\n"
				+ "where klienci.nr_klienta = " + args[0];

		List<Usluga> listUslugaKlient = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Usluga.class));
		return listUslugaKlient;
	}
	
	public List<Usluga> connectKlientNOT(int nr_uslugi) {
		Object[] args = { nr_uslugi };
		String sql = "select uslugi.NR_USLUGI, uslugi.NAZWA, uslugi.KOSZT, uslugi.NR_OPERATORA from uslugi\r\n"
				+ "join Klient_Usługa\r\n"
				+ "on Klient_Usługa.nr_uslugi = uslugi.nr_uslugi\r\n"
				+ "join klienci\r\n"
				+ "on klienci.nr_klienta = Klient_Usługa.nr_klienta\r\n"
				+ "where NOT klienci.nr_klienta = " + args[0];

		List<Usluga> listUslugaKlient = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Usluga.class));
		return listUslugaKlient;
	}

	
}
