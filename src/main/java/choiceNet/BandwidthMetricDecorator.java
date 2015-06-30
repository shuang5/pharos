package choiceNet;

public abstract class BandwidthMetricDecorator implements Metric{
	
	Metric metric;
	int bandwidth;
	BandwidthMetricDecorator(Metric m){
		metric=m;
	}
	
}