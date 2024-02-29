package th.ac.ku.atm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "information")
	private String information;
	@Column(name = "temps")
	private Date temps;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

}
