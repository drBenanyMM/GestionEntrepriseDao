package mr.liu.metier;

public class Departement {
	// donnees
	private String nomDepartment;
	private String localisation;

	// Les contrtucteurs
	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departement(String nomDepartment, String localisation) {
		super();
		this.nomDepartment = nomDepartment;
		this.localisation = localisation;
	}

	//Getter Setter
	public String getNomDepartment() {
		return nomDepartment;
	}

	public void setNomDepartment(String nomDepartment) {
		this.nomDepartment = nomDepartment;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	//Affichage
	@Override
	public String toString() {
		return "Departement [nomDepartment=" + nomDepartment + ", localisation=" + localisation + "]";
	}

}
