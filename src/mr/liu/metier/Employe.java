package mr.liu.metier;

public class Employe {
	// données
	private int numero;
	private String nom;
	private String grade;
	private String nomDepartment;

	// Constructeurs
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employe(int numero, String nom, String grade, String nomDepartment) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.grade = grade;
		this.nomDepartment = nomDepartment;
	}

	// getters et Setters

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNomDepartment() {
		return nomDepartment;
	}

	public void setNomDepartment(String nomDepartment) {
		this.nomDepartment = nomDepartment;
	}

	// Affichage
	@Override
	public String toString() {
		return "Employe [numero=" + numero + ", nom=" + nom + ", grade=" + grade + ", nomDepartment=" + nomDepartment
				+ "]";
	}

}
