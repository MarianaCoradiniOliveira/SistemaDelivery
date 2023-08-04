package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;


public class Utils {
	
static NumberFormat numberFormat = new DecimalFormat("R$ #, ##0.00");
	
	public static String doubleToString(Double valor) {
		return numberFormat.format(valor);
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//		
//		File file = new File("/Users/maria/OneDrive/Documentos/TERCEIRO SEMESTRE/POO/Nome dos produtos.txt");
//		
//		Scanner scan = new Scanner(file);
//		
//		while(scan.hasNextLine()) {
//			System.out.println(scan.nextLine());
//		}
//	}
}