package fr.upem.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import sun.corba.OutputStreamFactory;
import fr.upem.library.Book;
import fr.upem.library.BookIntegral;
import fr.upem.library.Comic;
import fr.upem.library.Library;
import fr.upem.library.Version;

public class ShoppingBasketIO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9007305348465969352L;
	protected Library m_library;
	
	public ShoppingBasketIO(Library currentLibrary){
		m_library = currentLibrary;
	}
	
	public void saveBasket(ShoppingBasket<AbstractMediaBuyable> basket, OutputStream out) throws IOException{
		Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8 );
		
		for(Map.Entry<AbstractMediaBuyable, Integer> entry: basket.getData().entrySet()){
			writer.write(entry.getKey().title()+"\n");
			writer.write(entry.getKey().author()+"\n");
			writer.write(entry.getValue()+"\n");
			writer.write("\n");
		}
		writer.flush();
		writer.close();
		out.close();
	}
	
	public ShoppingBasket<AbstractMediaBuyable> loadBasket(InputStream in) throws IOException{
		ShoppingBasket<AbstractMediaBuyable> res = new ShoppingBasket<AbstractMediaBuyable>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		java.util.LinkedList<String> infos = new java.util.LinkedList<String>();
		String line;
		try {
			while( (line = reader.readLine()) != null){
				infos.add(line);
				if(line.isEmpty()){
					LinkedList<AbstractMediaBuyable> search = m_library.search(infos.get(0));
					for(Link<AbstractMediaBuyable> m: search){
						if(m.data().author().equals(infos.get(1))){
							res.add(m.data(), Integer.parseInt(infos.get(2)));
						}
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(res.size());
		return res;
	}
	
	public static void main(String[] args) {
		Book b1 = new Book("L'écume des jours", "Boris Vian", "AEZD01", 9.99 );
		Book b2 = new Book("James Bond", "Lian Fleming", "AEZ161", 18.49);
		Book b3 = new Book("Les thanatonautes", "Bernard Werber", "AEZDEFD01", 5.99);
		Book b4 = new Book("Les Fourmis", "Bernard Werber", "DSCXZD01", 26.24);
		Book b5 = new Book("La pierre et le Sabre", "Mistoruri Hisoky", "DCSC821", 8.99);
		Book b6 = new Book("Le ciel et mon arbre", "Alain Jones", "SCBRG", 14.99);
		Book b7 = new Book("Le Mossad", "Patrick Smith", "SSCCCV87", 50.99);
		Book b8 = new Book("Le hacking", "Linus Torvald", "SCCCCS", 220.99);
		Comic c1 = new Comic("Histoire du futur", "Robert Heinelen", 6, true);
		BookIntegral bi1 = new BookIntegral("Philip K.Dick", "Go home martians", Version.Physic);
		
		ShoppingBasket<AbstractMediaBuyable> shop = new ShoppingBasket<AbstractMediaBuyable>();
		shop.add(b2);
		shop.add(b1);
		shop.add(b3);
		shop.add(b4);
		shop.add(b5);
		shop.add(b6);
		shop.add(b8);
		shop.add(b7);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(bi1);
		
		Library lib = new Library("MyLib");
		lib.add(b2);
		lib.add(b1);
		lib.add(b5);
		lib.add(b6);
		lib.add(b7);
		lib.add(b8);
		lib.add(b1);
		lib.add(c1);
		
		ShoppingBasketIO shopSave = new ShoppingBasketIO(lib);
		try {
			shopSave.saveBasket(shop, new FileOutputStream("/home/alex/Bureau/test.txt"));
			System.out.println(shopSave.loadBasket(new FileInputStream("/home/alex/Bureau/test.txt")).getSampleNumber());
			//serialization
			FileOutputStream outSerial = new FileOutputStream("/home/alex/Bureau/serial.txt");
			ObjectOutputStream oos = new ObjectOutputStream(outSerial);
			oos.writeObject(shopSave);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
