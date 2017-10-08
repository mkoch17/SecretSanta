import java.util.ArrayList;

public class FamilyMember{

	private String name;
	private String email;
	private FamilyMember partner;
	private boolean chosen;

	public FamilyMember(String name, String email){
		this.name = name;
		this.email = email;
		this.chosen = false;
	}

	public void setPartner(FamilyMember partner){
		this.partner = partner;
	}

	public FamilyMember getPartner(){
		return this.partner;
	}

	public String getEmail(){
		return this.email;
	}

	public String getName(){
		return this.name;
	}

	public void choose(){
		this.chosen = true;
	}

	public boolean getChosen(){
		return this.chosen;
	}

	public void clear(){
		this.chosen = false;
		this.partner = null;
	}


}