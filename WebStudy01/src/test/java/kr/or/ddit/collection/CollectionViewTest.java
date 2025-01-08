package kr.or.ddit.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.junit.jupiter.api.Test;

/**
 * 집합 객체 
 * 1. 배열 
 * 2. List 
 * 3. Set 
 * 4. Map 
 * 5. Vector 
 * 6. Properties 
 * 7. Queue
 * 8. stack
 * 
 * CollectionView: 집합객체의 요소에 대한 순차접근을 지원하는 인터페이스 
 * Iterator / Enumeration
 * 
 */
class CollectionViewTest {
	
	@Test
	void textEnumration() {
		Vector<String> sampleVector = new Vector<>();
		sampleVector.addElement("javascript");
		sampleVector.addElement("java");
		sampleVector.addElement("python");
		
		Enumeration<String> en = sampleVector.elements();
		while(en.hasMoreElements()) {
			String element = en.nextElement();
			System.out.println(element);
		}
	}
	
	@Test
	void testIterator() {
		Set<String> sampleSet = new HashSet<>();
		sampleSet.add("javascript");
		sampleSet.add("java");
		sampleSet.add("python");
		
		Iterator<String> it = sampleSet.iterator();
		while (it.hasNext()) {
			String element = (String) it.next();
			System.out.println(element);
		}
	}

}
