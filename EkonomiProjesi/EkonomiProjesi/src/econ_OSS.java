import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class econ_OSS {

	static final String outputFile = "C:\\Users\\Lenovo PC\\Desktop\\priceList.txt";
	
	static int urunSayisi = 0;
	static int anlikUrun = 0;
	public econ_OSS() {
		

		while(true){
			
			System.out.print(">> ");
			getData();
			break;

		}
	}
	
	public static void getData(){
		
		try {
			File file = new File(outputFile);
			Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String link : getLinks()){
			
			getProducts(link + "?count=700", 0); 
			System.out.println("Cekilen Urun: " + anlikUrun + " adet\t(" + link + ")");
			anlikUrun = 0;
		}
		
		  
	}
	
	public static void getProducts(String url, int urlCounter){
		Document doc;
		
		try(FileWriter fw = new FileWriter(outputFile, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw)) {
			
			doc = Jsoup.connect(url).get();
			
			if(urlCounter > 1)
				System.out.println("URL'ye basari ile baglandi: " + url + " Deneme sayisi: " + --urlCounter);
			
			Elements links = doc.getElementsByClass("product");
			for(Element link : links){
				String _title = "";
				String _price = "";
				String _id = link.attr("id");
				boolean isInSell = true;
				
				Elements title = link.getElementsByClass("title");
				Elements price = link.getElementsByClass("price");
				Elements inSell = link.getElementsByClass("temporarynotsell");
				
				
				for(Element e : title)
					_title = e.text().replaceAll("'","-");
				
				for(Element e : price)
					_price =  e.text().replaceAll("[^\\d,]", "").replace(',', '.');
				
				for(Element e : inSell)
					if(e.text().contains("edilememektedir")) isInSell = false;
				
				//********************************************************************** 
				/*
				 * Bilgileri burada cekiyor
				 * Istedigini yapabilirsin...
				 * _title -> urunun adi
				 * _price -> urunun fiyati
				 * _id    -> urunun uniqe ID si
				 * isInSell -> (true) urun satista, (false) urun temin edilemiyor
				 * 
				 */
				//********************************************************************** 
				
				out.println("Fiyat: " + _price + " TL\tUrun: " + _title); // Burda dosyaya yazýyor..
				
				//System.out.println("Fiyat: " + _price + " TL\tUrun: " + _title);
				urunSayisi++;
				anlikUrun++;
				
			}
			
			

		} catch (Exception e) {
			System.err.println("Gecersiz URL! (" + url + ") Tekrar Deneniyor.. (" +urlCounter+")" );
			if(!(urlCounter >= 10))
				getProducts(url, urlCounter+1);
			else
				System.err.println("Baðlantý kurulamadý! (" + url+")");
			
		}
	}
	
	public static void writeToDoc(StringBuffer input){
		
	}
	
	public static String[] getLinks(){
		  String [] returnString = {
				  "https://www.carrefoursa.com/senalmarket/r/meyve-sebze/meyve",
				  "https://www.carrefoursa.com/senalmarket/r/meyve-sebze/sebze",
				  "https://www.carrefoursa.com/senalmarket/r/meyve-sebze/organik-meyve-ve-sebzeler",
				  "https://www.carrefoursa.com/senalmarket/r/meyve-sebze/salata",
				  "https://www.carrefoursa.com/senalmarket/r/et-balik-sarkuteri/dana",
				  "https://www.carrefoursa.com/senalmarket/r/et-balik-sarkuteri/kuzu",
				  "https://www.carrefoursa.com/senalmarket/r/et-balik-sarkuteri/balik-ve-deniz-mahsulleri",
				  "https://www.carrefoursa.com/senalmarket/r/et-balik-sarkuteri/sarkuteri",
				  "https://www.carrefoursa.com/senalmarket/r/et-balik-sarkuteri/tavuk-ve-hindi",
				  "https://www.carrefoursa.com/senalmarket/r/et-balik-sarkuteri/mezeler",
				  "https://www.carrefoursa.com/senalmarket/r/et-balik-sarkuteri/sushi",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/sivi-yaglar",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/seker-tuz-ve-baharat",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/konserveler-ve-tursular",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/ketcap-mayonez-ve-soslar",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/hazir-corbalar-yemekler",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/bebek-mamalari",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/dondurulmus-urunler",
				  "https://www.carrefoursa.com/senalmarket/r/gida-yemek-malzemeleri/borekler-unlu-mamuller-ve-tatlilar",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/sutler",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/peynirler",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/yumurta-tereyag-ve-margarin",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/zeytin",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/bal-ve-recel",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/tahin-pekmez-helva",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/misir-gevregi-musli-hububat",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/krem-cikolata-ezmeler",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/yogurt-krema-kaymak",
				  "https://www.carrefoursa.com/senalmarket/r/sut-kahvaltilik/ekmekler",
				  "https://www.carrefoursa.com/senalmarket/r/sekerleme-kuruyemis/sekerleme",
				  "https://www.carrefoursa.com/senalmarket/r/sekerleme-kuruyemis/cikolata",
				  "https://www.carrefoursa.com/senalmarket/r/sekerleme-kuruyemis/kuruyemis",
				  "https://www.carrefoursa.com/senalmarket/r/sekerleme-kuruyemis/biskuviler",
				  "https://www.carrefoursa.com/senalmarket/r/sekerleme-kuruyemis/cipsler",
				  "https://www.carrefoursa.com/senalmarket/r/sekerleme-kuruyemis/kekler",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/sular",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/gazli-icecekler",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/soda-ve-maden-sulari",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/meyve-sulari",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/cay-ve-buzlu-caylar",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/konsantre-ve-toz-icecekler",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/diger-icecekler",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/kahve",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/ayran-ve-kefir",
				  "https://www.carrefoursa.com/senalmarket/r/icecek/enerji-icecegi",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/camasir-yikama-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/bulasik-yikama-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/ev-temizlik-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/kagit-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/lavabo-ve-tesisat-bakim-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/oda-kokulari-ve-koku-gidericiler",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/sinek-ve-bocek-olduruculer",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/ayakkabi-bakimi",
				  "https://www.carrefoursa.com/senalmarket/r/temizlik/metal-parlaticilar-ve-ates-yakicilar",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/kadin-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/tiras-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/bebek-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/sac-bakim-ve-aksesuar",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/banyo-ve-dus-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/agiz-bakim-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/kokular-ve-deodorantlar",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/kagit-urunleri-ve-pamuk",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/saglik-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/kisisel-bakim/gunes-urunleri",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/beyaz-esya",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/kucuk-ev-aletleri",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/elektronik",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/ev-duzeni-ve-zuccaciye",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/hirdavat-elektrik-aydinlatma",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/spor",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/oto-ve-seyahat",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/oyuncak-ve-eglence",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/petshop-ve-bahce",
				  "https://www.carrefoursa.com/senalmarket/r/elektronik-gida-disi/kirtasiye-kitap-film-muzik-ve-oyun"
		  };
		  return returnString;
	  }

}