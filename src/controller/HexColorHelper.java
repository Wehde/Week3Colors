/**
 * @author Kilian Wehde - kewehde
 * CIS175 - Fall 2022
 * Sep 10, 2022
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.HexColor;

public class HexColorHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Week3Colors");
	
	public void insertColor(HexColor hex) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(hex);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<HexColor> getAllColors(){
		EntityManager em = emfactory.createEntityManager();
		List<HexColor> allColors = em.createQuery("SELECT color FROM HexColor color", HexColor.class).getResultList();
		em.close();
		return allColors;
	}
	
	public void deleteColor(HexColor toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<HexColor> typedQuery = em.createQuery("SELECT hex FROM HexColor hex WHERE hex.hexColor = :selectedColor", HexColor.class);
		
		typedQuery.setParameter("selectedColor", toDelete.getHexColor());
		typedQuery.setMaxResults(1);
		HexColor result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateColor(HexColor oldColor, HexColor newColor) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<HexColor> typedQuery = em.createQuery("SELECT hex FROM HexColor hex WHERE hex.hexColor = :selectedColor", HexColor.class);
		
		typedQuery.setParameter("selectedColor", oldColor.getHexColor());
		typedQuery.setMaxResults(1);
		HexColor result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.persist(newColor);
		em.getTransaction().commit();
		em.close();
	}
	
	public HexColor searchForItemByHexColor(String hexColor) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		HexColor found = em.find(HexColor.class, hexColor);
		em.close();
		return found;
	}
	
	public List<HexColor> searchForItemByHexGray(String hexGray) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<HexColor> typedQuery = em.createQuery("SELECT hex FROM HexColor hex WHERE hex.hexGray = :selectedGray", HexColor.class);
		typedQuery.setParameter("selectedGray", hexGray);
		
		List<HexColor> foundColors = typedQuery.getResultList();
		em.close();
		return foundColors;
	}
	
	public List<HexColor> getAllGrays() {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<HexColor> typedQuery = em.createQuery("SELECT DISTINCT(hex.hexGray) FROM HexColor hex", HexColor.class);
		
		List<HexColor> grayColors = typedQuery.getResultList();
		em.close();
		return grayColors;
	}
	
	public static void addAllColors() {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
		for (char a : HEX_CHARS) {
			for (char b : HEX_CHARS) {
				System.out.println(""+ a + b);
				for (char c : HEX_CHARS) {
					for (char d : HEX_CHARS) {
						for (char e : HEX_CHARS) {
							for (char f : HEX_CHARS) {
								em.persist(new HexColor("" + a + b + c + d + e + f));
							}
							em.flush();
							em.clear();
							em.getTransaction().commit();
							em.getTransaction().begin();
						}
					}
				}
			}
		}
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
