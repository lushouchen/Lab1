//4
package myFrame;
import java.io.File;
import java.util.*;
import java.io.*;
import java.util.HashMap;
public class sy1
{
	private int a=1;

	public static Iterator<String> iterator;
	
	protected static int Cut(String sentences)
	{
    	String x = sentences;
    	String temp = "";
    	String pair = null;
    	List<String> list = new LinkedList<String>();
    	for(String y : x.split("[^A-Za-z]"))
    	{
    		if(y.length()>0)
    			temp = y;
    		if(y.length()>0 && temp.length()>0)
    		{
    			pair = temp.toLowerCase();
    			list.add(pair);
    		}
    		
    		
    	}
    	iterator = list.iterator();
    	return list.size();
    }
	protected static String getSentences(String fileName)
	{
    	String sentences = "";
    	File file = new File(fileName);
    	BufferedReader reader = null;
    	try
    	{
    		reader = new BufferedReader(new FileReader(file));
    		String temp = null;
    		while((temp = reader.readLine()) != null)
    			sentences += temp;
    		reader.close();
    	} 
    	catch(IOException e1) 
    	{
    		e1.printStackTrace();
    	} 
    	finally 
    	{
    		if(reader != null)
    			try
    		{
    				reader.close();
    			} catch(IOException e2)
    		{
    				e2.printStackTrace();
    			}
    	}
	    return sentences;
    }
	protected static int counts(String[] a)
	{
		HashMap<String,String> hm=new HashMap<String,String>();
		int count=0;
		for(int i=0;i<a.length;i++)
		{
			if(hm.get(a[i])==null)
			{
				hm.put(a[i],1+"");
			}
			else
			{
				count++;
			}
		}
		return count;
	}

	protected static void TextToFile(final String strFilename, final String strBuffer)
	  {  
	    try  
	    {      
	      // �����ļ�����  
	      File fileText = new File(strFilename);  
	      // ���ļ�д�����д����Ϣ  
	      FileWriter fileWriter = new FileWriter(fileText);  
	  
	      // д�ļ�        
	      fileWriter.write(strBuffer);  
	      // �ر�  
	      fileWriter.close();  
	    }  
	    catch (IOException e)  
	    {  
	      e.printStackTrace();  
	    }  
	  }  
}



