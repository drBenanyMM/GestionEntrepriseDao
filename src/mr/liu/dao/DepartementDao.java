package mr.liu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mr.liu.dbb.DbConnection;
import mr.liu.metier.Departement;

public class DepartementDao implements Dao<Departement> {
	// Connection
	private Connection connection = null;
	// Conteneur de requettes
	private Statement statement = null;
	// Requette
	private String requette = null;
	// le model
	private Departement departement = null;

	// Constructeur pour initialiser la connection et le conteneur de requettes
	public DepartementDao() {
		try {
			// connection
			connection = DbConnection.getConnection();
			// Conteneur
			statement = connection.createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// La methode getByID prend un long comme id, alors que note model department
	// doit prendre un String, donc on doit surcharger cette methode
	@Override
	public Departement getByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	// Surcharger la méthode getByID(String nomDep) en changeant le type de l'id
	public Departement getByID(String id) {
		// REquette
		requette = "SELECT * FROM Departement WHERE nomDepartment = '" + id + "';";

		try {
			// REsultat
			ResultSet resultSet = statement.executeQuery(requette);
			//
			resultSet.next();
			// Creeer l'objet department
			departement = new Departement(resultSet.getString(1), resultSet.getString(2));

			System.out.println(departement.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departement;
	}

	@Override
	public List<Departement> getAll() {
		// REquette
		requette = "SELECT * FROM Departement;";
		// Creer une liste des departments
		List<Departement> departements = new ArrayList<Departement>();
		try {
			// Executer la requette
			ResultSet resultSet = statement.executeQuery(requette);

			while (resultSet.next()) {
				// Creer un objet de departement
				departement = new Departement(resultSet.getString(1), resultSet.getString(2));
				// Afficher le departmnt recupeté
				System.out.println(departement.toString());
				// Ajouter cette instance à la liste des departments.
				departements.add(departement);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// select all doit retourner la liste de departments
		return departements;
	}

	@Override
	public void save(Departement t) {
		// REquette
		requette = "INSERT INTO Departement	VALUES ('" + t.getNomDepartment() + "','" + t.getLocalisation() + "');";
		try {
			// Executer la requette
			int resultat = statement.executeUpdate(requette);
			if (resultat != 0) {
				System.out.println("Insertion du department (" + t.getNomDepartment() + " , " + t.getLocalisation()
						+ ") est effectuée.");
			} else {
				System.out.println("ERREIR d'Insertion");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Departement t, String[] args) {
		// REquette
		requette = "UPDATE Departement SET nomDepartment = '" + args[0] + "', localisation='" + args[1]
				+ "' WHERE nomDepartment='" + t.getNomDepartment() + "';";

		System.out.println("On Update department (" + t.getNomDepartment() + " , " + t.getLocalisation() + ").");
		try {
			// Executer la requette
			int resultat = statement.executeUpdate(requette);
			if (resultat != 0) {
				System.out.println("Apres Update department (" + args[0] + " , " + args[1] + ").");
			} else {
				System.out.println("ERREIR Update");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Departement t) {
		// REquette
		requette = "DELETE FROM Departement WHERE nomDepartment='" + t.getNomDepartment() + "';";
		System.out.println("On supprime ce department (" + t.getNomDepartment() + " , " + t.getLocalisation() + ").");
		try {
			// Executer la requette
			int resultat = statement.executeUpdate(requette);
			if (resultat != 0) {
				System.out.println(
						"Suppression du Department (" + t.getNomDepartment() + " , " + t.getLocalisation() + ") est effectué.");
			} else {
				System.out.println("ERREIR De Suppression");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// test
	public static void main(String[] args) {
		System.out.println("********************* SELECT BY ID *********************");
		// Tester de select by id : la methode getById
		new DepartementDao().getByID("bio");

		System.out.println("********************* SELECT ALL *********************");
		// Test de select All : getAll()
		new DepartementDao().getAll().toString();

		System.out.println("********************* INSERT *********************");
		// Test d'Insertion: la methode : save()
		new DepartementDao().save(new Departement("Math", "FST"));

		System.out.println("********************* UPDATE *********************");
		// Test Update: la methode : update()
		String[] newDep = { "Bioloby", "FST" };
		new DepartementDao().update(new DepartementDao().getByID("bioinformatic"), newDep);

		String[] newDep2 = { "bioinformatic", "FST" };
		new DepartementDao().update(new DepartementDao().getByID("Bioloby"), newDep2);

		System.out.println("********************* DELETE *********************");
		// Test Update: la methode : update()
		new DepartementDao().delete(new DepartementDao().getByID("Math"));

	}
}
