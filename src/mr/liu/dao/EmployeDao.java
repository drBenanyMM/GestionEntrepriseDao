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
       
		//6 Rerour de la liste des employes
		return employes;
	}

	@Override
	public void save(Employe t) {

	}

	@Override
	public void update(Employe t, String[] args) {

	}

	@Override
	public void delete(Employe t) {

	}

//TestMain
	public static void main(String[] args) {
		// Test de Select By ID
		System.out.println("TEST DE LA METODE getById");
		new EmployeDao().getByID(1212);		
		//Test Selcet All : getAll()
		System.out.println("TEST DE LA METODE getALL()");
		System.out.println(new EmployeDao().getAll().toString());

	}

}
