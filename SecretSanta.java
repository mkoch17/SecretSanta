import java.util.ArrayList;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class SecretSanta {

	private ArrayList<Family> families;
	private Random rand;
	public static void main(String args[]){
		SecretSanta santa = new SecretSanta();
		santa.pair();

	}

	public void pair(){
		families = new ArrayList<Family>();
		rand = new Random();

		//Initialize Families
		Family taylorFamily = new Family("Taylor");
		FamilyMember sam = new FamilyMember("Sam", "sataylor1017@gmail.com");
		FamilyMember james = new FamilyMember("James", "");
		FamilyMember john = new FamilyMember("John", "");
		FamilyMember dan = new FamilyMember("Dan", "");
		FamilyMember rob = new FamilyMember("Rob", "");
		FamilyMember johnny = new FamilyMember("Johnny", "");
		FamilyMember emily = new FamilyMember("Emily", "");
		FamilyMember morgan = new FamilyMember("Morgan", "");
		taylorFamily.addFamilyMember(sam);
		taylorFamily.addFamilyMember(james);
		taylorFamily.addFamilyMember(john);
		taylorFamily.addFamilyMember(dan);
		taylorFamily.addFamilyMember(rob);
		taylorFamily.addFamilyMember(johnny);
		taylorFamily.addFamilyMember(emily);
		taylorFamily.addFamilyMember(morgan);
		
		Family kochFamily = new Family("Koch");
		FamilyMember mason = new FamilyMember("Mason", "koch.382@osu.edu");
		kochFamily.addFamilyMember(mason);
		kochFamily.addFamilyMember(new FamilyMember("Tyler",""));
		kochFamily.addFamilyMember(new FamilyMember("Donna", ""));
		kochFamily.addFamilyMember(new FamilyMember("Bob", ""));
		
		Family almeidaFamily = new Family("Almeida");
		FamilyMember jason = new FamilyMember("Jason", "illidan94@gmail.com");
		almeidaFamily.addFamilyMember(jason);
		almeidaFamily.addFamilyMember(new FamilyMember("Kevin", ""));
		almeidaFamily.addFamilyMember(new FamilyMember("Dad", ""));
		almeidaFamily.addFamilyMember(new FamilyMember("Mom", ""));
		
		Family graalFamily = new Family("Graal");
		graalFamily.addFamilyMember(new FamilyMember("Rob", ""));
		graalFamily.addFamilyMember(new FamilyMember("Kaycee", ""));

		Family scalesFamily = new Family("Scales");
		scalesFamily.addFamilyMember(new FamilyMember("Brian", ""));
		scalesFamily.addFamilyMember(new FamilyMember("David", ""));
		scalesFamily.addFamilyMember(new FamilyMember("Kelly", ""));
		scalesFamily.addFamilyMember(new FamilyMember("Rachel", ""));
		scalesFamily.addFamilyMember(new FamilyMember("Becca", ""));
		scalesFamily.addFamilyMember(new FamilyMember("Dustin",""));
		scalesFamily.addFamilyMember(new FamilyMember("Darren", ""));
		scalesFamily.addFamilyMember(new FamilyMember("Kim", ""));
		scalesFamily.addFamilyMember(new FamilyMember("Lili", ""));
		scalesFamily.addFamilyMember(new FamilyMember("Jeremiah", ""));

		families.add(taylorFamily);
		families.add(kochFamily);
		families.add(almeidaFamily);
		families.add(graalFamily);
		families.add(scalesFamily);
		//Create Pairings
		boolean succeed = createPairings();
		int iterations = 1;
		while (!succeed){
			clear();
			iterations++;
			System.out.println("Trying again. Iteration: " + iterations);
			succeed = createPairings();
		}

		//Email Family Members
		for (int i = 0; i < families.size(); i++){
			Family temp = families.get(i);
			for (int j = 0; j < temp.size(); j++){
				FamilyMember member = temp.getMember(j);
				System.out.println(member.getName() + " has " + member.getPartner().getName());
			}
		}

	}

	public boolean createPairings(){
		int person = 0;
		for (int i = 0; i < families.size(); i++){
			Family tempFamily = families.get(i);
			for (int j = 0; j < tempFamily.size(); j++){
				boolean otherAvailable = checkAvailableFamilies(i);
				person ++;
				if (!otherAvailable){
					return false;
				}
				FamilyMember tempMember = tempFamily.getMember(j);
				int familyNumber = rand.nextInt(families.size());
				while (familyNumber == i || families.get(familyNumber).getAvailableMembers() == 0){
					familyNumber = rand.nextInt(families.size());
				}
				int avail = families.get(familyNumber).getAvailableMembers();
				Family partnerFamily = families.get(familyNumber);
				int memberNumber = rand.nextInt(partnerFamily.size());
				while (partnerFamily.getMember(memberNumber).getChosen()){
					memberNumber = rand.nextInt(partnerFamily.size());
				}
				FamilyMember partner = partnerFamily.getMember(memberNumber);
				tempMember.setPartner(partner);
				partner.choose();
				partnerFamily.chooseMember();
			}
		}
		return true;
	}

	public boolean checkAvailableFamilies(int index){
		for (int i = 0; i < families.size(); i++){
			if (i != index){
				Family tempFamily = families.get(i);
				if (tempFamily.getAvailableMembers() != 0){
					return true;
				}
			}
		}
		return false;
	}

	public void clear(){
		for (int i = 0; i < families.size(); i++){
			Family tempFamily = families.get(i);
			tempFamily.reset();
			for (int j = 0; j < tempFamily.size(); j++){
				FamilyMember member = tempFamily.getMember(j);
				member.clear();
			}
		}
	}

}