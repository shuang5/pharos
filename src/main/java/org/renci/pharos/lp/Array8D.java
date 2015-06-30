package org.renci.pharos.lp;

import java.util.Iterator;

public interface Array8D {
	Iterator<String> iterator(String from1, String to1, String from2,String to2, 
			String from3,String to3,String from4,String to4);
	Iterator<String> iterator();
	int getDimension();
}
