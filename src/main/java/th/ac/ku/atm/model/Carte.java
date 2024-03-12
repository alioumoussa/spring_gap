package th.ac.ku.atm.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.sql.Date;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Entity
@Table(name = "cartes")
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_carte")
    private String numeroCarte;
    @Column(unique = true)
    private String pin;
    
    @Column(name = "date_expiration")
    private Date dateExpiration;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compte;

    // Constructeurs
    public Carte() {

    }

    public Carte(String numeroCarte, String pin, Date dateExpiration, Compte compte) {
        this.numeroCarte = numeroCarte;
        this.pin = pin;
        this.dateExpiration = dateExpiration;
        this.compte = compte;
    }

  
}
