//package sjdb;
//
//import java.io.*;
//import java.util.ArrayList;
//import sjdb.DatabaseException;
//
//public class Test {
//	private Catalogue catalogue;
//
//	public Test() {
//	}
//
//	public static void main(String[] args) throws Exception {
//		Catalogue catalogue = createCatalogue();
//		Inspector inspector = new Inspector();
//		Estimator estimator = new Estimator();
//		Operator plan = query(catalogue);
//		plan.accept(estimator);
//		plan.accept(inspector);
//		Optimiser optimiser = new Optimiser(catalogue);
//		Operator planopt = optimiser.optimise(plan);
//		planopt.accept(estimator);
//		planopt.accept(inspector);
//	}
//
//	public static Catalogue createCatalogue() {
//		Catalogue cat = new Catalogue();
//		cat.createRelation("A", 100);
//		cat.createAttribute("A", "a1", 100);
//		cat.createAttribute("A", "a2", 15);
//		cat.createRelation("B", 150);
//		cat.createAttribute("B", "b1", 150);
//		cat.createAttribute("B", "b2", 100);
//		cat.createAttribute("B", "b3", 5);
//		return cat;
//	}
//
//	public static Operator query(Catalogue cat) throws Exception {
//		Scan a = new Scan(cat.getRelation("A"));
//		Scan b = new Scan(cat.getRelation("B"));
//		Product p1 = new Product(a, b);
//		Select s1 = new Select(p1, new Predicate(new Attribute("a2"), new Attribute("b3")));
////		Join j1 = new Join(a,b, new Predicate(new Attribute("a2"), new Attribute("b3")));
//		ArrayList<Attribute> atts = new ArrayList<Attribute>();
//		atts.add(new Attribute("a2"));
//		atts.add(new Attribute("b1"));
////		Project plan = new Project(j1, atts);
//		Project plan = new Project(s1, atts);
//		return plan;
//	}
//}
package sjdb;

import java.io.*;
import java.util.ArrayList;
import sjdb.DatabaseException;

public class Test {
	private Catalogue catalogue;

	public Test() {
	}

	public static void main(String[] args) throws Exception {
		Catalogue catalogue = createCatalogue();
		Inspector inspector = new Inspector();
		Estimator estimator = new Estimator();
		Operator plan = query(catalogue);
		plan.accept(estimator);
		plan.accept(inspector);
		Optimiser optimiser = new Optimiser(catalogue);
		Operator planopt = optimiser.optimise(plan);
		planopt.accept(estimator);
		planopt.accept(inspector);
	}

	public static Catalogue createCatalogue() {
		Catalogue cat = new Catalogue();
		cat.createRelation("A", 100);
		cat.createAttribute("A", "a1", 100);
		cat.createAttribute("A", "a2", 15);
		cat.createRelation("B", 150);
		cat.createAttribute("B", "b1", 150);
		cat.createAttribute("B", "b2", 100);
		cat.createAttribute("B", "b3", 5);
		cat.createRelation("C", 200);
		cat.createAttribute("C", "c1", 200);
		cat.createAttribute("C", "c2", 100);
		cat.createAttribute("C", "c3", 5);
		return cat;
	}

	public static Operator query(Catalogue cat) throws Exception {
		Scan a = new Scan(cat.getRelation("A"));
		Scan b = new Scan(cat.getRelation("B"));
		Scan c = new Scan(cat.getRelation("C"));
		Product p1 = new Product(a, b);
		Product p2 = new Product(p1,c);
		
//		Join j1 = new Join(a,b, new Predicate(new Attribute("a2"), new Attribute("b3")));
			
		Select s1 = new Select(p2 , new Predicate(new Attribute("b2"), new Attribute("c3")));
//		Select s2 = new Select( s1, new Predicate(new Attribute("a1"), "X"));
		Select s3 = new Select(s1, new Predicate(new Attribute("a2"), new Attribute("b3")));
		Select s4 = new Select( s3, new Predicate(new Attribute("b1"), new Attribute("c1")));
		
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(new Attribute("a2"));
		atts.add(new Attribute("b1"));
//		Project plan = new Project(j1, atts);
		Project plan = new Project(s4, atts);
		return plan;
	}
}
//package sjdb;
//
//import java.io.*;
//import java.util.ArrayList;
//import sjdb.DatabaseException;
//
//public class Test {
//	private Catalogue catalogue;
//
//	public Test() {
//	}
//
//	public static void main(String[] args) throws Exception {
//		Catalogue catalogue = createCatalogue();
//		Inspector inspector = new Inspector();
//		Estimator estimator = new Estimator();
//		Operator plan = query(catalogue);
//		plan.accept(estimator);
//		plan.accept(inspector);
//		Optimiser optimiser = new Optimiser(catalogue);
//		Operator planopt = optimiser.optimise(plan);
//		planopt.accept(estimator);
//		planopt.accept(inspector);
//	}
//
//	public static Catalogue createCatalogue() {
//		Catalogue cat = new Catalogue();
//		cat.createRelation("A", 100);
//		cat.createAttribute("A", "a1", 100);
//		cat.createAttribute("A", "a2", 15);
//		cat.createRelation("B", 150);
//		cat.createAttribute("B", "b1", 150);
//		cat.createAttribute("B", "b2", 100);
//		cat.createAttribute("B", "b3", 5);
//		cat.createRelation("C", 200);
//		cat.createAttribute("C", "c1", 150);
//		cat.createAttribute("C", "c2", 100);
//		cat.createAttribute("C", "c3", 5);
//		cat.createRelation("D", 300);
//		cat.createAttribute("D", "d1", 50);
//		cat.createRelation("E", 300);
//		cat.createAttribute("E", "e1", 50);
//		return cat;
//	}
//
//	public static Operator query(Catalogue cat) throws Exception {
//		Scan a = new Scan(cat.getRelation("A"));
//		Scan b = new Scan(cat.getRelation("B"));
//		Scan c = new Scan(cat.getRelation("C"));
//		Scan d = new Scan(cat.getRelation("D"));
//		Scan e = new Scan(cat.getRelation("E"));
//		Product p1 = new Product(a, b);
//		Product p2 = new Product(p1,c);
//		Product p3 = new Product(p2,d);
//		Product p4 = new Product(p3,e);
//		
////		Join j1 = new Join(a,b, new Predicate(new Attribute("a2"), new Attribute("b3")));
//			
//		Select s1 = new Select(p4 , new Predicate(new Attribute("b1"), new Attribute("c2")));
//		Select s2 = new Select( s1, new Predicate(new Attribute("a1"), new Attribute("b1")));
//		Select s3 = new Select( s2, new Predicate(new Attribute("a2"), new Attribute("d1")));
//		Select s4 = new Select( s3, new Predicate(new Attribute("b1"), "456"));
//		Select s5 = new Select( s4, new Predicate(new Attribute("a2"), new Attribute("e1")));
//		ArrayList<Attribute> atts = new ArrayList<Attribute>();
//		atts.add(new Attribute("a2"));
//		atts.add(new Attribute("b1"));
////		Project plan = new Project(j1, atts);
//		Project plan = new Project(s5, atts);
//		return plan;
//	}
//}

//package sjdb;
//
//import java.io.*;
//import java.util.ArrayList;
//import sjdb.DatabaseException;
//
//public class Test {
//	private Catalogue catalogue;
//
//	public Test() {
//	}
//
//	public static void main(String[] args) throws Exception {
//		Catalogue catalogue = createCatalogue();
//		Inspector inspector = new Inspector();
//		Estimator estimator = new Estimator();
//		Operator plan = query(catalogue);
//		plan.accept(estimator);
//		plan.accept(inspector);
//		Optimiser optimiser = new Optimiser(catalogue);
//		Operator planopt = optimiser.optimise(plan);
//		planopt.accept(estimator);
//		planopt.accept(inspector);
//	}
//
//	public static Catalogue createCatalogue() {
//		Catalogue cat = new Catalogue();
//		cat.createRelation("A", 100);
//		cat.createAttribute("A", "a1", 100);
//		cat.createAttribute("A", "a2", 15);
//		cat.createRelation("B", 150);
//		cat.createAttribute("B", "b1", 150);
//		cat.createAttribute("B", "b2", 100);
//		cat.createAttribute("B", "b3", 200);
//		cat.createRelation("C", 200);
//		cat.createAttribute("C", "c1", 150);
//		cat.createAttribute("C", "c2", 100);
//		cat.createAttribute("C", "c3", 5);
//		cat.createRelation("D", 300);
//		cat.createAttribute("D", "d1", 50);
//		cat.createRelation("E", 300);
//		cat.createAttribute("E", "e1", 50);
//		return cat;
//	}
//
//	public static Operator query(Catalogue cat) throws Exception {
//		Scan a = new Scan(cat.getRelation("A"));
//		Scan b = new Scan(cat.getRelation("B"));
//		Scan c = new Scan(cat.getRelation("C"));
//		Scan d = new Scan(cat.getRelation("D"));
//		Scan e = new Scan(cat.getRelation("E"));
//		Product p1 = new Product(a, b);
//		Product p2 = new Product(p1,c);
//		Product p3 = new Product(p2,d);
//		Product p4 = new Product(p3,e);
//		
////		Join j1 = new Join(a,b, new Predicate(new Attribute("a2"), new Attribute("b3")));
//			
//		Select s1 = new Select(p4, new Predicate(new Attribute("a1"), new Attribute("b2")));
//		Select s2 = new Select( s1, new Predicate(new Attribute("a1"), "aaa"));
//		Select s3 = new Select( s2, new Predicate(new Attribute("b1"), "bbb"));
//		Select s4 = new Select(s3, new Predicate(new Attribute("a2"), new Attribute("c1")));
////		Select s4 = new Select( s3, new Predicate(new Attribute("a2"), new Attribute("c1")));
//		Select s5 = new Select( s4, new Predicate(new Attribute("c1"), "ccc"));
//		ArrayList<Attribute> atts = new ArrayList<Attribute>();
//		atts.add(new Attribute("a2"));
//		atts.add(new Attribute("e1"));
//		atts.add(new Attribute("d1"));
////		Project plan = new Project(j1, atts);
//		Project plan = new Project(s5, atts);
//		return plan;
//	}
//}
//
