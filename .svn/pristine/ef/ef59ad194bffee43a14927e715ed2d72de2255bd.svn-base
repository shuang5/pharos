package choiceNet;

import org.renci.pharos.flow.FlowSpace;

public abstract class LabelTranslationService implements Service<FlowSpace,FlowSpace>{
	private Service<?,?> service;
	LabelTranslationService(Service<?,?> s){
		this.setService(s);
	}
	@Override
	public void setService(Service<?,?> s){
		service=s;
	}
	@Override
	public Service<?,?> getService(){
		return service;
	}
	@Override
	public void setFunction(Function<FlowSpace,FlowSpace> f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Function<FlowSpace,FlowSpace> getFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMetric(Metric m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Metric getMetric() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEquivalent(Service<FlowSpace,FlowSpace> s) {
		return this.getFunction().isEquivalentTo(s.getFunction());
	}

	@Override
	public boolean isEquivalent(Service<FlowSpace,FlowSpace> s, Predicate<FlowSpace,FlowSpace> p) {
		return p.test(this, s);
	}

	@Override
	public boolean canFollow(Service<FlowSpace,FlowSpace> s) {
		// TODO Auto-generated method stub
		return false;
	}

}
