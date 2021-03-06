package org.renci.pharos.lp;

import java.util.HashSet;
import java.util.Iterator;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Sets;

class RemoveDuplicatesFilterDecorator implements Iterator<String>{
	private HashSet<String> hs=Sets.newHashSet();
	private PeekingIterator<String> itr;
	RemoveDuplicatesFilterDecorator(Iterator<String> it){
		itr=Iterators.peekingIterator(it);
	}
	@Override
	public boolean hasNext() {
		while(itr.hasNext()){
			if(!hs.contains(itr.peek())){
				return true;
			}
			else {
				itr.next();
			}
		}
		return false;
	}

	@Override
	public String next() {
		String next=itr.next();
		hs.add(next);
		return next;
	}

}
