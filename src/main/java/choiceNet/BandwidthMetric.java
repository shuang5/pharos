package choiceNet;

public class BandwidthMetric implements Metric{
	int bandwidth;
	private Metric metric;
	BandwidthMetric(Metric m){
		metric=m;
	}
	public void setBandwidth(int d){
		bandwidth=d;
	}
	public int getBandwidth(){
		return bandwidth;
	}
	public int compareTo(Metric m){
		if(m.getClass().equals(metric.getClass())){
			if(this.bandwidth<((BandwidthMetric)m).bandwidth)return -1;
			else if(this.bandwidth==((BandwidthMetric)m).bandwidth)return 0;
			else return 1;
		}
		else throw new ClassCastException("not comparable");
	}
}
