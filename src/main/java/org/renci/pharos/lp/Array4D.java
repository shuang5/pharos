package org.renci.pharos.lp;

import java.util.Iterator;

interface Array4D {
	Iterator<String> iterator(String from1, String to1, String from2,String to2);
	Iterator<String> iterator();
	int getDimension();
}