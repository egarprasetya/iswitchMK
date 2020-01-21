package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserModel;

@RestController
@CrossOrigin("*")
@RequestMapping("/cek9")

public class CreateUploadFileController
{
	
	static String usingStringSplit (String[] lines)
	{
		// kalimat
		
		String result = "";
		return result;
		
	}
	
	public static String[] readLines (String filename) throws IOException
	{
		FileReader fileReader = new FileReader (filename);
		
		BufferedReader bufferedReader = new BufferedReader (fileReader);
		List<String> lines = new ArrayList<String> ();
		String line = null;
		
		while ((line = bufferedReader.readLine ()) != null)
		{
			lines.add (line);
		}
		
		bufferedReader.close ();
		
		return lines.toArray (new String[lines.size ()]);
	}
	
	public static void createFile (String result) throws IOException
	{
		String path = "/opt/tomcat/apache-tomcat-8.5.49/webapps/iswitch/";
		path = path + "folder_file";
		// Creating a File object
		File file = new File (path);
		// Creating the directory
		boolean bool = file.mkdir ();
		if (bool)
		{
			System.out.println ("Directory created successfully");
		} else
		{
			System.out.println ("Sorry couldn’t create specified directory");
		}
		File file2 = new File (path+"/resultfile.txt");
		
		// Create the file
		if (file2.createNewFile ())
		{
			System.out.println ("File is created!");
		} else
		{
			System.out.println ("File already exists.");
		}
		
		// Write Content
		FileWriter writer = new FileWriter (file2);
		writer.write (result);
		writer.close ();
	}
	
	@PostMapping("/cek10")
	public int updateUserHistory ()
	{
		String filename = "text2.txt";
		String cek = "aaaaaaaaaaaaaaaaaa";
		try
		{
//            String[] lines = readLines(filename);
//            int ab=0;
//            for (String line : lines) 
//            {
//                System.out.println(line);
//                ab++;
//            }
//            System.out.println(ab);
			createFile (cek);
//            String result = usingStringSplit(lines);
			// createFile(result);
			
		} catch (IOException e)
		{
			System.out.println ("Unable to create " + filename + ": " + e.getMessage ());
		}
		return 0;
	}
}