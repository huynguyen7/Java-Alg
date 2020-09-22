import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// element-prog 14.3
public class RemoveFirstNameDuplicates {
	public static void main(String[] args) {
		String[] names1 = {"Ian Botham", "David Gower", "Ian Bell", "Ian Chappell", "Ian Botham"};
		showResults(names1); // expect {"Ian Bell", "David Gower"}
	}

	private static void showResults(String[] names) {
		System.out.println("----ShowResults----");
		List<Name> namesList = transformToNamesList(names);
		System.out.println(namesList.toString());
		
		List<Name> rs = removeFirstNameDuplicates(namesList);
		System.out.println(rs.toString());
	}

	// Time: O(nlogn), space: O(1)
	// n is names.length, m is total of UNIQUE first name
	public static List<Name> removeFirstNameDuplicates(List<Name> names) {
		Collections.sort(names);

		int count = 0;
		for(int i = 0; i < names.size(); ++i) { // start at index 1
			if(!names.get(i).firstName.equals(names.get(count).firstName))
				names.set(++count, names.get(i));
		}
		
		names.subList(++count, names.size()).clear(); // remove the rest element after ++count index
		return names;
	}

	private static List<Name> transformToNamesList(String[] names) {
		List<Name> rs = new ArrayList<>();
		for(int i = 0; i < names.length; ++i) {
			String[] splitString = names[i].trim().split("\\s+");
			rs.add(new Name(splitString[0], splitString[1]));
		}

		return rs;
	}

	static class Name implements Comparable<Name> {
		String firstName;
		String lastName;
		
		public Name(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		@Override
		public int compareTo(Name another) {
			int cmpFirstName = firstName.compareTo(another.firstName);
			if(cmpFirstName != 0) return cmpFirstName;
			else return lastName.compareTo(another.lastName);
		}

		@Override
		public String toString() {
			return String.format("(%s, %s)", firstName, lastName);
		}
	}
}

// Constraints:
// Names only have 2 words
