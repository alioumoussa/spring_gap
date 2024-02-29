package th.ac.ku.atm.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.ku.atm.model.Carte;
import th.ac.ku.atm.model.Compte;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.CarteRepository;
import th.ac.ku.atm.repository.CompteRepository;
@Service
public class CarteDao {

	@Autowired
	CarteRepository repository;
	@Autowired
	CompteRepository cmptRepo;

	public Carte getById(int id) {
		return repository.getOne(id);
	}

	public Carte generateCarte(Compte compte) {
		String numeroCarte = generateRandomNumber(16);

		String codePin = generateRandomNumber(4);

		LocalDate localdate = LocalDate.now().plus(2, ChronoUnit.YEARS);
		Date expirationDate = Date.valueOf(localdate);

		Carte carte = new Carte(numeroCarte, codePin, expirationDate, compte);

		save(carte);

		return carte;
	}

	public boolean verfiPin(int pin) {
		Carte crt = getCarteByPin(pin);
		if (crt == null) {
			return false;
		} else {
			return true;
		}
	}

	public Carte getCarteByPin(int pin) {

		try {
			Carte carte = repository.getCartByPin(pin);
			return carte;
		} catch (Exception w) {

		}
		return null;

	}

	public Compte getCompteByCart(Carte crt) {

		try {
			Compte cmpt = cmptRepo.getOne(crt.getId());
			return cmpt;
		} catch (Exception w) {

		}
		return null;

	}

	public User getUserByCompte(Compte cpt) {
		UserDao user = new UserDao();
		return user.getById(cpt.getId());

	}

	// public Carte getCarteByCompteId(Long compteId) {
	// try (Session session = sessionFactory.openSession()) {
	// // Recherche de la carte associée au compte
	// return session.createQuery("FROM Carte WHERE compte.id = :compteId",
	// Carte.class)
	// .setParameter("compteId", compteId).uniqueResult();
	// }
	// }

	// public Carte getCarteByNumber(String numeroCarte) {
	// repository.
	// }

	public void save(Carte carte) {
		repository.save(carte);
	}

	public void delete(Carte carte) {
		repository.delete(carte);
	}

	// Méthode utilitaire pour générer un nombre aléatoire avec la longueur
	// spécifiée
	public String generateRandomNumber(int length) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			stringBuilder.append(random.nextInt(10));
		}
		return stringBuilder.toString();
	}

	public List<Carte> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
}
