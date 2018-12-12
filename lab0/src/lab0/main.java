package lab0;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class main {
	public main(String[] args) throws IOException {
		URL url = new URL("http://venus.cs.qc.cuny.edu/~aabreu/cs212/project1/Sudoku1.txt");
		Scanner scan = new Scanner(url.openStream());
		
		System.out.println(scan);

	}

}
