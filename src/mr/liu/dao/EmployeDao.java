package mr.liu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mr.liu.dbb.DbConnection;
import mr.liu.metier.Employe;

public class EmployeDao implements Dao<Employe> {
	// variables de connection
	private Connection connection = null;
	// Conteneur de requette
	private Statement statement = null;
	// Reqette
	private String requette = null;
	// Modele
	private Employe employe = null;

	// initialisation des variables
	public EmployeDao() {
		try {
			// une connection à la base de données
			connection = DbConnection.getConnection();
			// Conteneur de requette
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*****************************************************
	 * Fin de configuration
	 ****************************************************/
	/*
	 * SELECT BY ID
	 */

	public Employe getByID(String id) {
		return null;

	}

	@Override
	public Employe getByID(long id) {
		// 1. Requette
		requette = "SELECT * FROM employe WHERE  numero=" + (int) id + ";";
		try {
			// 2. Recuperer le resulat
			ResultSet resultSet = statement.executeQuery(requette);

			// 3. Stocker ce resultat dans l'objet employe
			resultSet.next();

			int numero = resultSet.getInt(1);
			String nom = resultSet.getString(2);
			String grade = resultSet.getString(3);
			String nomDepartment = resultSet.getString(4);

			employe = new Employe(numero, nom, grade, nomDepartment);

			// 4. Affichage de l'employe
			System.out.println(employe.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5 retour de l'instatnce employe
		return employe;
	}

	/*
	 * SELECT * FROM employe
	 */
	@Override
	public List<Employe> getAll() {
		// 1 Reqette
		requette = "SELECT * FROM employe;";
		// une liste des employes
		List<Employe> employes = new ArrayList<Employe>();

		try {
			// 2. Recuperer le resulat
			ResultSet resultSet = statement.executeQuery(requette);

			// 3. Stocker ce resultat dans la liste des employes

			while (resultSet.next()) {

				// 4 Recuperer l'objet employe
				int numero = resultSet.getInt(1);
				String nom = resultSet.getString(2);
				String grade = resultSet.getString(3);
				String nomDepartment = resultSet.getString(4);

				employe = new Employe(numero, nom, grade, nomDepartment);

				// 5 Ajouter l'objet employe dans la liste employes
				employes.add(employe);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 6 Rerour de la liste des employes
		return employes;
	}

	@Override
	public void save(Employe employe) {
		// 1 REquette
		requette = "INSERT INTO  employe values(" + employe.getNumero() + ",'" + employe.getNom() + "','"
				+ employe.getGrade() + "','" + employe.getNomDepartment() + "');";
		System.out.println(requette);

		try {
			// 2. Recuperer le resulat
			int resulat = statement.executeUpdate(requette);

			if (resulat != 0)
				System.out.println("insertion effectué ");
			else {
				System.out.println("ERREUR d'insertion  ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Employe employe, String[] args) {
		// 1 REquette
		requette = "update employe set  numero= " + Integer.parseInt(args[0]) + ", nom= '" + args[1] + "', grade='"
				+ args[2] + "', nomDepartment='" + args[3] + "' Where numero=" + employe.getNumero() + " ;";
		System.out.println(requette);

		try {
			// 2. Recuperer le resulat
			int resulat = statement.executeUpdate(requette);

			if (resulat != 0)
				System.out.println("Update effectué ");
			else {
				System.out.println("ERREUR d'UPDATE  ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Employe employe) {
		// 1 REquette
		requette = "DELETE FROM employe Where numero=" + employe.getNumero() + " ;";
		System.out.println(requette);

		try {
			// 2. Recuperer le resulat
			int resulat = statement.executeUpdate(requette);

			if (resulat != 0)
				System.out.println("Delete effectué ");
			else {
				System.out.println("ERREUR de DELETE  ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//TestMain
	public static void main(String[] args) {
		// Test de Select By ID
		System.out.println("TEST DE LA METODE getById");
		new EmployeDao().getByID(1212);
		// Test Selcet All : getAll()
		System.out.println("TEST DE LA METODE getALL()");
		System.out.println(new EmployeDao().getAll().toString());

		// Test Insert : save()
		System.out.println("TEST DE LA METODE save()");
		new EmployeDao().save(new Employe(1214, "Baba", "PRof", "info"));

		// Test Insert : update()
		System.out.println("TEST DE LA METODE update()");
		String[] newEmploye = { "1216", "sidi", "PRof", "info" };
		new EmployeDao().update(new EmployeDao().getByID(1214), newEmploye);

		// Test Insert : DELETE()
		System.out.println("TEST DE LA METODE DLETE()");
		new EmployeDao().delete(new EmployeDao().getByID(1216));

	}

}
