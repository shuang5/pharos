package choiceNet;

public class DelayMetric implements Metric{
	private int delay;
	public void setDelay(int d){
		delay=d;
	}
	public int getDelay(){
		return delay;
	}
	@Override
	public int compareTo(Metric m) {
		if(m.getClass().equals(getClass())){
			if(this.delay<((DelayMetric)m).delay)return -1;
			else if(this.delay==((DelayMetric)m).delay)return 0;
			else return 1;
		}
		else throw new ClassCastException("not comparable");
	}

}
