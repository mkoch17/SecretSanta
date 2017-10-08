import java.util.ArrayList;

public class Family{

	private String name;
	private ArrayList<FamilyMember> members;
	public int availableMembers;

	public Family(String name){
		this.name = name;
		this.members = new ArrayList<FamilyMember>();
		this.availableMembers = 0;
	}

	public void addFamilyMember(FamilyMember member){
		this.members.add(member);
		this.availableMembers++;
	}

	public String getName(){
		return this.name;
	}

	public FamilyMember getMember(int i){
		return this.members.get(i);
	}

	public void chooseMember(){
		this.availableMembers--;
	}

	public int getAvailableMembers(){
		return this.availableMembers;
	}

	public int size(){
		return this.members.size();
	}

	public void reset(){
		this.availableMembers = members.size();
	}


}