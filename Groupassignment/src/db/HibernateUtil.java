package db;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import entities.Image;
import entities.User;

public class HibernateUtil {
	
	private static Session session = HibernateConnection.getSession();
	
	/**
	 * Get the `src` for each <img> in a List
	 * 
	 * @return
	 */
	public static String allImages(Integer userId) {
		String images = "";
		
		User theUser = session.get(User.class, userId);
		if (theUser == null) {
			System.err.println("Why are we getting images for nobody?");
			return images;
		}
		
		List<Image> imageList = theUser.getImages();
		if (imageList == null)
			return images;
		
		Iterator<Image> it = imageList.iterator();
		List<String> filenames = new ArrayList<String>();
		
		while (it.hasNext()) {
			filenames.add(it.next().getReference());
		}
		
		Iterator<String> imageCursor = filenames.iterator();
		
		while (imageCursor.hasNext()) {
			images += "<div class='thumbnail_container resizable'><img class='thumbnail' src='";
			images += imageCursor.next();
			images += "'></div> \n";
		}
		
		return images;
	}
	
	public static String searchImages(Integer userId, String search) {
		String images = "";
		
		User theUser = session.get(User.class, userId);
		if (theUser == null) {
			System.err.println("Why are we getting images for nobody?");
			return images;
		}
		
		List<Image> imageList = theUser.getImages();
		if (imageList == null)
			return images;
		
		Iterator<Image> it = imageList.iterator();
		List<String> filenames = new ArrayList<String>();
		
		while (it.hasNext()) {
			Image img = it.next();
			if (img.getFilename().contains(search)) {
				filenames.add(img.getReference());
			}
		}
		
		Iterator<String> imageCursor = filenames.iterator();
		
		while (imageCursor.hasNext()) {
			images += "<div class='thumbnail_container resizable'><img class='thumbnail' src='";
			images += imageCursor.next();
			images += "'></div> \n";
		}
		
		return images;
	}
	
	public static String sortImages(Integer userId, String sort) {
		String images = "";
		
		User theUser = session.get(User.class, userId);
		if (theUser == null) {
			System.err.println("Why are we getting images for nobody?");
			return images;
		}
		
		List<Image> imageList = theUser.getImages();
		if (imageList == null)
			return images;
		
		Iterator<Image> it = imageList.iterator();
		List<String> filenames = new ArrayList<String>();
		
		while (it.hasNext()) {
			Image img = it.next();
			if (img.getCategory().equals(sort)) {
				filenames.add(img.getReference());
			}
		}
		
		Iterator<String> imageCursor = filenames.iterator();
		
		while (imageCursor.hasNext()) {
			images += "<div class='thumbnail_container resizable'><img class='thumbnail' src='";
			images += imageCursor.next();
			images += "'></div> \n";
		}
		
		return images;
	}
	
	public static String sha256(String base) { // from https://stackoverflow.com/questions/3103652/hash-string-via-sha-256-in-java/3103727#3103727
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		} catch (Exception e) {
			System.err.println("FAILED TO HASH");
			e.printStackTrace();
		}
		return hexString.toString();
	}
}
