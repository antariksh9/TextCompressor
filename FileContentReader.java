package Compressor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileContentReader {
	private String path;
	public FileContentReader(String file_path){
		path=file_path;
	}
	public String[] openFile() throws IOException{
		FileReader fr= new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numLines=readLines();
		String[] textData = new String[numLines];

		int i;
		for( i=0;i<numLines;i++){
			textData[i]= textReader.readLine();
		}
		textReader.close();
		return textData;
	}
	public int readLines() throws IOException{
		FileReader file_to_read=new FileReader(path);
		BufferedReader bf= new BufferedReader(file_to_read);
		String aline;
		int numLines=0;
		while((aline=bf.readLine())!= null){
			numLines++;

		}
		bf.close();
		return numLines;
	}
}


