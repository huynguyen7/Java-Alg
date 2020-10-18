import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

// Constraints:
// Each team in the sequence has beaten the next team in the sequence.

// element-prog bootcamp 20
public class SequenceMatchResults {
	public static void main(String[] args) {
		List<MatchResult> matchResults1 = new ArrayList<>();
		matchResults1.add(new MatchResult("B","D"));
		matchResults1.add(new MatchResult("A","C"));
		matchResults1.add(new MatchResult("C","E"));
		matchResults1.add(new MatchResult("E","B"));
		showResults(matchResults1, "A", "B"); // true, GOOD EXAMPLE TO DRAW RECURSION TREE.
		// explain: A win C, C win E, E win B -> A can win B.

		List<MatchResult> matchResults2 = new ArrayList<>();
		matchResults2.add(new MatchResult("B","D"));
		matchResults2.add(new MatchResult("A","C"));
		matchResults2.add(new MatchResult("A","E"));
		matchResults2.add(new MatchResult("D","A"));
		matchResults2.add(new MatchResult("D","C"));
		showResults(matchResults2, "A", "B"); // false
	}

	private static void showResults(List<MatchResult> matches, String teamA, String teamB) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT: " + matches.toString());
		Map<String, Set<String>> graph  = buildGraph(matches);
		System.out.println("\nGraph:\n" + graph.toString());
		boolean rs = canTeamABeatTeamB(graph, teamA, teamB);
		System.out.printf("\nCan team %s win team %s ? %b\n\n", teamA, teamB, rs);
	}

	// E = matches.size().
	// Time: O(E), space: O(E)
	public static boolean canTeamABeatTeamB(Map<String, Set<String>> graph, String teamA, String teamB) {
		if(graph.get(teamA) == null) return false;
		Set<String> visited = new HashSet<>();
		return isReachableDFS(graph, teamA, teamB, visited);
	}

	private static boolean isReachableDFS(Map<String, Set<String>> graph, String curr, String dest, Set<String> visited) {
		if(curr.equals(dest)) return true;
		else if(visited.contains(curr) || graph.get(curr) == null) return false;
		else {
			visited.add(curr);
			for(String team: graph.get(curr)) {
				if(isReachableDFS(graph, team, dest, visited))
					return true;
			}
			return false;
		}
	}

	// directed graph
	private static Map<String, Set<String>> buildGraph(List<MatchResult> matches) {
		Map<String, Set<String>> graph = new HashMap<>(); // <winningTeam, set of losingTeams>
		for(MatchResult match: matches) {
			Set<String> edges = graph.get(match.winningTeam);
			if(edges == null) {
				edges = new HashSet<>();
				graph.put(match.winningTeam, edges);
			}
			edges.add(match.losingTeam);
		}

		return graph;
	}

	public static class MatchResult {
		public String winningTeam;
		public String losingTeam;
		
		public MatchResult(String winningTeam, String losingTeam) {
			this.winningTeam = winningTeam;
			this.losingTeam = losingTeam;
		}

		@Override
		public String toString() {
			return String.format("[%s,%s]", winningTeam, losingTeam);
		}
	}
}
