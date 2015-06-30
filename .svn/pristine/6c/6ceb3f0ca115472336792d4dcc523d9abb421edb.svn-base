package choiceNet;

public interface Service<Input,Output> {
	void setFunction(Function<Input,Output> f);
	Function<Input,Output> getFunction();
	void setMetric(Metric m);
	Metric getMetric();
	boolean isEquivalent(Service<Input,Output> s);
	boolean isEquivalent(Service<Input,Output> s, Predicate<Input,Output> p);
	boolean canFollow(Service<Input,Output> s);
	void setService(Service<?,?> s);
	Service<?,?> getService();
}