//1
package myFrame;

import java.util.Iterator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class ListDG 
{
	private int a=1;

	private String[] name;
	private int weight[][];//权值数组
	private int dist[][];
	private int path[][];
	private int record[][];
	private int changearray[][];//只保存有效边的二维数组
	private int pathword[][];
	private int count;
	public static Iterator<String> iterator;
	private String shortpath;
	public ListDG(int n, String temp[],int m)//n是字符串中不重复的字符的个数，m是输入的文本中有效的字符的个数
	{
		name=new String[n];
		count=n;
		int judge=0;
		for(int i=0,k=0;k<n;i++)
		{
			for(int j=0;j<k;j++)
			{
				if(temp[i].equals(name[j]))
				{
					judge=1;
					break;
				}
			}
			if(judge!=1)
			{
				name[k]=temp[i];
				k++;
			}
			judge=0;
		}
		weight=new int [n][n];
		dist=new int [n][n];
		path=new int [n][n];
		for(int i=0;i<m-1;i++)
		{
			int a=this.search(temp[i]);
			int b=this.search(temp[i+1]);
			weight[a][b]=weight[a][b]+1;
		}
	}
	
	public String showDirectedGraph()//对邻接矩阵进行遍历
	{
		String back="";
		back=back+"digraph g{"+"\r\n";
		for (int h=0;h<count;h++)
		{
			back=back+name[h]+";"+"\r\n";
		}
		for (int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				if(weight[i][j]!=0)
				{
					back=back+name[i]+"->"+name[j]+"[label="+weight[i][j]+"];"+"\r\n";
				}
			
			}
			
		}
		back=back+"}";
		return back;
	}
	public int search(String a)//返回字符串在字符串数组中的下标
	{
		for(int i=0;i<count;i++)
		{
			if(a.equals(name[i]))
			{
				return i;
			}
			
		}
			return -1;
	}
   public String queryBridgeWords(String a,String b)//返回两个词之间的桥接词
   {
	   int m=this.search(a);
	   int n=this.search(b);
	   int i=0;//桥的数量
	   String answer ="";
	   if(m==-1&&n==-1)
	   {
		   answer=answer+"No "+a+" and "+b+" in the graph!";
		   return answer;
	   }
	   if(m==-1)
	   {
		   answer=answer+"No "+a+" in the graph!";
		   return answer;
	   }
	   if(n==-1)
	   {
		   answer=answer+"No "+b+" in the graph!";
		   return answer;
	   }
	   
	   for(int j=0;j<count;j++)
	   {
		   if(j!=m && j!=n && weight[m][j]!=0)
		   {
			   if(weight[j][n]!=0)
			   {
				   if(i==0)
				   {
					   answer=answer+"The bridge words from "+a+" to "+b+" are:";
				   }
				   answer=answer+" "+name[j];
				   i++;
			   }
		   }
	   }
	   if(i==0)
	   {
		   answer=answer+"No bridge words from "+a+" to "+b+"!";
	   }
	   return answer;
   }
   
   public String calcShortestPath(String word1, String word2)//返回两个单词的最短路径
   {
	   pathword=new int [count][count];
	   int a=this.search(word1);
	   int b=this.search(word2);
	   if(a==-1||b==-1)
	   {
		   return("输入错误");
	   }
	   this.findspath(a,b);
	   return shortpath;
   }
  public String backgv()
   {
	   String back="";
		back=back+"digraph g{"+"\r\n";
		for (int h=0;h<count;h++)
		{
			back=back+name[h]+";"+"\r\n";
		}
		for (int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				if(weight[i][j]!=0)
				{
					if(pathword[i][j]!=1)
						back=back+name[i]+"->"+name[j]+"[label="+weight[i][j]+"];"+"\r\n";
					else
						back=back+name[i]+"->"+name[j]+"[label="+weight[i][j]+","+"color=red];"+"\r\n";
				}
			
			}
			
		}
		back=back+"}";
		return back;
   }
   
   private String calcallShortestPath(String word1, String word2)
   {
	   int a=this.search(word1);
	   int b=this.search(word2);
	   this.findallspath(a,b);
	   return shortpath;
   }
   
   private void findallspath(int a,int b)
   {
	   if(path[a][b]==-1 && dist[a][b]==0)
	   {
		   shortpath="不可达";
		   return;
	   }
	   if(path[a][b]==-1 && dist[a][b]!=0)
	   {
		   shortpath=name[a]+"->"+name[b];
	   }
	   else
	   {
		   shortpath=name[a]+"->";
		   this.findpath(a,b);
		   shortpath=shortpath+name[b];
	   }
   }
   
   private void findspath(int a,int b)
   {
	   this.floyd();
	   if(path[a][b]==-1 && dist[a][b]==0)
	   {
		   shortpath="不可达";
		   return;
	   }
	   if(path[a][b]==-1 && dist[a][b]!=0)
	   {
		   shortpath=name[a]+"->"+name[b];
		   pathword[a][b]=1;
	   }
	   else
	   {
		   shortpath=name[a]+"->";
		   this.findpath(a,b);
		   shortpath=shortpath+name[b];
	   }
   }
   private void findpath(int a,int b)
   {
	   int k=path[a][b];
	   if(k==-1)
	   {
		   pathword[a][b]=1;
		   return;
	   }
	   findpath(a,k);
	   shortpath=shortpath+name[k]+"->";
	   findpath(k,b);
	   
   }
   private void floyd()
   {
	   for(int i=0;i<count;i++)
	   {  
           for(int j=0;j<count;j++)
           {  
               path[i][j]=-1;  
               dist[i][j]=weight[i][j];  
           }  
       } 
	   for(int k=0;k<count;k++)
	   {  
           for(int i=0;i<count;i++)
           {  
               for(int j=0;j<count;j++)
               {  
                   if(dist[i][k]!=0 && dist[k][j]!=0 )
                   {
                	   if(dist[i][j]!=0 && dist[i][k]+dist[k][j]<dist[i][j])
                	   {
                		   dist[i][j]=dist[i][k]+dist[k][j];  
                           path[i][j]=k; 
                	   }
                	   else if(dist[i][j]==0)
                	   {
                		   dist[i][j]=dist[i][k]+dist[k][j];  
                           path[i][j]=k;  
                	   }
                   }  
               }  
           }  
       }  
   }
   private static int Cut(String sentences)
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
   			pair = temp;
   			list.add(pair);
   		}
   		
   		
   	}
   	iterator = list.iterator();
   	return list.size();
   }
   
   private String Findbridge(String a,String b)
   {
	   int m=this.search(a);
	   int n=this.search(b);
	   String answer = null;
	   if(m==-1|| n==-1)
	   {
		   return answer;
	   }
	   
	   for(int j=0;j<count;j++)
	   {
		   if(j!=m && j!=n && weight[m][j]!=0)
		   {
			   if(weight[j][n]!=0)
			   {
				   answer=name[j];
			   }
		   }
	   }
	   return answer;
   }
   
   public String generateNewText(String inputText)
   {
	   int y=Cut(inputText);
	   String a=null;
       String temp1[]=new String[y];
       String temp2[]=new String[y];
       int m=0;
       while(iterator.hasNext())
       {
			temp1[m]=iterator.next();
			m++;
       }
       for(int j=0;j<m-1;j++)
       {
    	   a=Findbridge(temp1[j].toLowerCase(),temp1[j+1].toLowerCase());
    	   if(a!=null)
    	   {
    		   temp2[j]=a;
    	   }
    	   else
    	   {
    		   temp2[j]="";
    	   }
       }
       String back="";
       for(int j=0;j<m;j++)
       {
    	   back=back+temp1[j]+" ";
    	   if(temp2[j]!=""&& j!=m-1)
    	   {
    		   back=back+temp2[j]+" ";
    	   }
       }
       return back; 
   }

   public String findallshort(String a)
   {
	   pathword = new int [count][count];
	   String h[]=new String [count];
	   if(this.search(a)==-1)
	   {
		   return "输入的字符串不存在";
	   }
	   this.floyd();
	   for(int i=0;i<count;i++)
	   {
		   if(!name[i].equals(a))
		   {
			   h[i]=this.calcallShortestPath(a,name[i]);
		   }
	   }
	   String back="";
	   for(int i=0;i<count;i++)
	   {
		   if(!name[i].equals(a))
		   back=back+a+"->"+name[i]+":   "+h[i]+"\n";
	   }
	   return back;
   }
   
   public String randomWalk()
   {
	   long t = System.currentTimeMillis();
	   Random rd = new Random(t);
	   int k=rd.nextInt(count);
	   record=new int [count][count];
	   String answer="";
	   answer=answer+name[k]+" ";
	   this.arraychange();
	   if(changearray[k].length==0)
	   {
		   answer=answer+"随机漫步完成";
		   return answer;
	   }
	   int j=rd.nextInt(changearray[k].length);
       int y;
       y=changearray[k][j];
	   while(changearray[k].length>0 && record[k][y]!=-1)
	   {
		 
		   answer=answer+name[y]+" ";
		   record[k][y]=-1;
		   k=y;
		   if(changearray[k].length==0)
		   {
			   break;
		   }
		   j=rd.nextInt(changearray[k].length);
		   y=changearray[k][j];
	   }
	   if(record[k][y]==-1)
	   {
		   answer=answer+name[y];
	   }
	   return answer;
   }
   
   private int[] arraychange(int i)
   {
	   int t[]=new int [count];
	   int k=0;
	   for(int j=0;j<count;j++)
	   {
		   if(weight[i][j]!=0)
		   {
			   t[k]=j;
			   k++;
		   }
	   }
	   int back[]=Arrays.copyOf(t, k);
	   return back;
   }
   
   private void arraychange()
   {
	  changearray=new int [count][];
	   for(int i=0;i<count;i++)
	   {
		  changearray[i]=Arrays.copyOf(this.arraychange(i), this.arraychange(i).length);
	   }
   }
   
   public String backgv2()
   {
   	   String back="";
   		back=back+"digraph g{"+"\r\n";
   		for (int h=0;h<count;h++)
   		{
   			back=back+name[h]+";"+"\r\n";
   		}
   		for (int i=0;i<count;i++)
   		{
   			for(int j=0;j<count;j++)
   			{
   				if(weight[i][j]!=0)
   				{
   					if(record[i][j]!=-1)
   						back=back+name[i]+"->"+name[j]+"[label="+weight[i][j]+"];"+"\r\n";
   					else
   						back=back+name[i]+"->"+name[j]+"[label="+weight[i][j]+","+"color=red];"+"\r\n";
   				}
   			
   			}
   			
   		}
   		back=back+"}";
   		return back;
   }
}
