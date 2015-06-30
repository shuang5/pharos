package choiceNet;

public interface Metric {
	//returns 1 if greater than m, 0 if equal, -1 if less
	int compareTo(Metric m);
}
