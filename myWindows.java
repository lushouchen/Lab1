//2
package myFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class myWindows {
	private int a=1;

	private JFrame frmLab;
	private JTextField fileName;
	private JTextArea output;
	private boolean isRandom;
	private JTextArea Logs;
	private String singlePoint;
	private String newText;
	private String doublePoint1;
	private String doublePoint2;
	private JLabel Photo;
	private String photoPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myWindows window = new myWindows();
					window.frmLab.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public myWindows() {
		initialize();
	}
	
	private ListDG getGraph(String fn){
		String fileName_1 = fn;
		String sentences = sy1.getSentences(fileName_1);
		int h= sy1.Cut(sentences);//字符串中的单词的个数
		String temp[]=new String[h];//保存最后的字符串数组
		int u=0;
		while(sy1.iterator.hasNext())
		{
			temp[u]=sy1.iterator.next();
			u++;
		}
        int j=0;//用于统计字符串中重复的单词的个数
        j=sy1.counts(temp);
        int i=h-j;//数组的大小
        ListDG a=new ListDG(i,temp,i+j);
        return a;
	}
	
	private void runGraphviz(String gvPath){
		try{
			String command = "G:\\graphviz-2.38\\release\\bin\\dot.exe " + gvPath + " -T jpg -o G:\\graphviz-2.38\\WorkSpace\\show.jpg";
			Process process = Runtime.getRuntime().exec(command);
			try{
				process.waitFor();
			} catch(InterruptedException e) {
				e.printStackTrace();
				Logs.append("InterruptedException.\n");
			}
			Logs.append("Run Grapgviz successfully!\n");
		} catch(IOException e) {
			Logs.append("Run Graphviz falsely!\n");
		}
	}
	
	private void setImageLabel(String str){
		Icon icon;
		try{
			icon = new ImageIcon(ImageIO.read(new File(str)));
			Photo.setIcon(icon);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	public class FileNameDialog extends JDialog{

		public FileNameDialog(JFrame owner){
			super(owner, "FileName Dialog", true);
	
			JPanel panel = new JPanel();
			
			JLabel fileNameLabel = new JLabel("File name:");
			fileNameLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			panel.add(fileNameLabel);
			
			JTextField filename = new JTextField();
			filename.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			filename.setBackground(Color.WHITE);
			filename.setColumns(15);
			filename.setEditable(true);
			panel.add(filename);
			
			JButton submit = new JButton("Submit");
			submit.setFocusPainted(false);
			submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					fileName.setText(filename.getText());
					setVisible(false);
				}
			});
			panel.add(submit);
			add(panel, BorderLayout.SOUTH);
			
			setSize(400, 88);
			setLocationByPlatform(true);
		} 
	}
	
	@SuppressWarnings("serial")
	public class SinglePointDialog extends JDialog{

		public SinglePointDialog(JFrame owner){
			super(owner, "SinglePoint Dialog", true);
	
			JPanel panel = new JPanel();
			
			JLabel SinglePointLabel = new JLabel("Single point:");
			SinglePointLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			panel.add(SinglePointLabel);
			
			JTextField pointName = new JTextField();
			pointName.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			pointName.setBackground(Color.WHITE);
			pointName.setColumns(15);
			pointName.setEditable(true);
			panel.add(pointName);
			
			JButton submit = new JButton("Submit");
			submit.setFocusPainted(false);
			submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					singlePoint = pointName.getText();
					setVisible(false);
				}
			});
			panel.add(submit);
			add(panel, BorderLayout.SOUTH);
			
			setSize(400, 88);
			setLocationByPlatform(true);
		} 
	}
	
	@SuppressWarnings("serial")
	public class DoublePointsDialog extends JDialog{

		public DoublePointsDialog(JFrame owner){
			super(owner, "DoublePoints Dialog", true);
	
			JPanel panel = new JPanel();
			
			JLabel DoublePointsLabel = new JLabel("Points:");
			DoublePointsLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			panel.add(DoublePointsLabel);
			
			JTextField pointName = new JTextField();
			pointName.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			pointName.setBackground(Color.WHITE);
			pointName.setColumns(15);
			pointName.setEditable(true);
			panel.add(pointName);
			
			JButton submit = new JButton("Submit");
			submit.setFocusPainted(false);
			submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String[] str = pointName.getText().split(" ");
					doublePoint1 = str[0];
					doublePoint2 = str[1];
					setVisible(false);
				}
			});
			panel.add(submit);
			add(panel, BorderLayout.NORTH);
			
			setSize(400, 88);
			setLocationByPlatform(true);
		} 
	}
	
	@SuppressWarnings("serial")
	public class NewTextDialog extends JDialog{

		public NewTextDialog(JFrame owner){
			super(owner, "NewText Dialog", true);
	
			JPanel panel = new JPanel();
			
			JLabel NewTextLabel = new JLabel("New text:");
			NewTextLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			panel.add(NewTextLabel);
			
			JTextField pointName = new JTextField();
			pointName.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
			pointName.setBackground(Color.WHITE);
			pointName.setColumns(15);
			pointName.setEditable(true);
			panel.add(pointName);
			
			JButton submit = new JButton("Submit");
			submit.setFocusPainted(false);
			submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					newText = pointName.getText();
					setVisible(false);
				}
			});
			panel.add(submit);
			add(panel, BorderLayout.SOUTH);
			
			setSize(400, 88);
			setLocationByPlatform(true);
		} 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frmLab = new JFrame();
		frmLab.getContentPane().setFont(new Font("Segoe Print", Font.BOLD, 16));
		frmLab.getContentPane().setBackground(Color.WHITE);
		frmLab.getContentPane().setLayout(null);
		
		JPanel panel_note = new JPanel();
		panel_note.setBounds(0, 495, 1186, 187);
		panel_note.setBackground(Color.WHITE);
		panel_note.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Note", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_note.setForeground(Color.WHITE);
		frmLab.getContentPane().add(panel_note);
		panel_note.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 79, 1059, 95);
		panel_note.add(scrollPane);
		
		Logs = new JTextArea();
		Logs.setEditable(false);
		Logs.setTabSize(4);
		Logs.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 13));
		Logs.setWrapStyleWord(true);
		scrollPane.setViewportView(Logs);
		frmLab.setIconImage(Toolkit.getDefaultToolkit().getImage(myWindows.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		frmLab.setTitle("Java - Lab1 - version 1.0");
		frmLab.setBounds(100, 100, 1204, 729);
		frmLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(309, 49, 843, 433);
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 3, true));
		frmLab.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File name:");
		lblNewLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		lblNewLabel.setBounds(14, 29, 95, 27);
		panel_note.add(lblNewLabel);
		
		fileName = new JTextField();
		fileName.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		fileName.setBackground(Color.WHITE);
		fileName.setEditable(false);
		fileName.setBounds(102, 32, 1054, 24);
		panel_note.add(fileName);
		fileName.setColumns(10);
		
		JLabel lblOutput = new JLabel("Log:");
		lblOutput.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		lblOutput.setBounds(14, 79, 72, 18);
		panel_note.add(lblOutput);
		
		JButton Clear = new JButton("Clear");
		Clear.setBackground(UIManager.getColor("Button.background"));
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.setText("");
			}
		});
		Clear.setBounds(14, 140, 72, 34);
		panel_note.add(Clear);
		Clear.setForeground(Color.BLACK);
		Clear.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		Clear.setFocusPainted(false);
        
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1186, 32);
		frmLab.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setIcon(new ImageIcon(myWindows.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		mnFile.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		menuBar.add(mnFile);
		
		JMenu mnOpen = new JMenu("Open");
		mnOpen.setIcon(new ImageIcon(myWindows.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeOpen.gif")));
		mnOpen.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnFile.add(mnOpen);
		
		JMenuItem mntmDefault = new JMenuItem("Default");
		mntmDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileName.setText("C:\\Users\\asus\\Desktop\\Recently\\text1.txt");
				Logs.append("Open the default file.\n");
			}
		});
		mntmDefault.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnOpen.add(mntmDefault);
		
		JMenuItem mntmChoose = new JMenuItem("Choose");
		mntmChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logs.append("Open the chosen file.\n");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				int result = chooser.showOpenDialog(panel);
				if(result == 0)
					fileName.setText(chooser.getSelectedFile().getPath());
			}
		});
		mntmChoose.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnOpen.add(mntmChoose);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Open the name-printed file.\n");
				FileNameDialog fnd = new FileNameDialog(frmLab);
				fnd.setVisible(true);
				
			}
		});
		mntmPrint.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnOpen.add(mntmPrint);
		
		JMenu mnNewMenu = new JMenu("Function");
		mnNewMenu.setIcon(new ImageIcon(myWindows.class.getResource("/org/eclipse/jface/dialogs/images/popup_menu.gif")));
		mnNewMenu.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("showDirectedGraph");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Show the directed graph.\n");
				ListDG a = getGraph(fileName.getText());
				output.append(a.showDirectedGraph()+"\n"+"\n");
				sy1.TextToFile("D:\\graph.gv",a.showDirectedGraph());
				photoPath = "D:\\graph.gv";
				isRandom = false;
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmQuerybridgewords = new JMenuItem("queryBridgeWords");
		mntmQuerybridgewords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Query bridge words.\n");
				ListDG a = getGraph(fileName.getText());
				if(doublePoint1!=null && doublePoint2!=null)
					output.append(a.queryBridgeWords(doublePoint1, doublePoint2)+"\n"+"\n");
				isRandom = false;
			}
		});
		mntmQuerybridgewords.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnNewMenu.add(mntmQuerybridgewords);
		
		JMenuItem mntmGeneratenewtext = new JMenuItem("generateNewText");
		mntmGeneratenewtext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Generate new text.\n");
				ListDG a = getGraph(fileName.getText());
				if(newText != null){
					String b=a.generateNewText(newText);//根据新文本插入桥接词后的字符串
					output.append(b+"\n"+"\n");
				}
				isRandom = false;
			}
		});
		mntmGeneratenewtext.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnNewMenu.add(mntmGeneratenewtext);
		
		/*JMenuItem mntmCalcshortestpath = new JMenuItem("calcShortestPath");
		mntmCalcshortestpath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Calculate the shortest path.\n");
				ListDG a = getGraph(fileName.getText());
				output.append(a.calcShortestPath("everyone","their")+"\n");
				isRandom = false;
			}
		});
		mntmCalcshortestpath.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnNewMenu.add(mntmCalcshortestpath);*/
		
		JMenuItem mntmRandomwalk = new JMenuItem("randomWalk");
		mntmRandomwalk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Random walk in graph.\n");
				isRandom = true;
			}
		});
		
		JMenu mnCalcshortestpath = new JMenu("calcShortestPath");
		mnCalcshortestpath.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnNewMenu.add(mnCalcshortestpath);
		
		JMenuItem mntmSingle = new JMenuItem("Single Word");
		mntmSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logs.append("Calculate the shortest pathes of single word to others.\n");
				ListDG a = getGraph(fileName.getText());
				if(singlePoint != null)
					output.append(a.findallshort(singlePoint)+"\n"+"\n");
				isRandom = false;
			}
		});
		mntmSingle.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnCalcshortestpath.add(mntmSingle);
		
		JMenuItem mntmCoupleWords = new JMenuItem("Couple Words");
		mntmCoupleWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logs.append("Calculate the shortest pathes of double words.\n");
				ListDG a = getGraph(fileName.getText());
				if(doublePoint1!=null && doublePoint2!=null){
					output.append(a.calcShortestPath(doublePoint1, doublePoint2)+"\n"+"\n");
					sy1.TextToFile("D:\\short.gv",a.backgv());
					photoPath = "D:\\short.gv";
				}
				isRandom = false;
			}
		});
		mntmCoupleWords.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnCalcshortestpath.add(mntmCoupleWords);
		mntmRandomwalk.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnNewMenu.add(mntmRandomwalk);
		
		JMenu mnRun = new JMenu("Run");
		mnRun.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnRun.setIcon(new ImageIcon(myWindows.class.getResource("/icons/progress/ani/5.png")));
		menuBar.add(mnRun);
		
		JMenuItem mntmRunAutomatically = new JMenuItem("Run automatically");
		mntmRunAutomatically.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isRandom == true){
					Logs.append("Run automatically!\n");
					ListDG a = getGraph(fileName.getText());
					output.append(a.randomWalk()+"\n"+"\n");
					sy1.TextToFile("D:\\graph.gv",a.backgv2());
					photoPath = "D:\\graph.gv";
				}
			}
		});
		mntmRunAutomatically.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnRun.add(mntmRunAutomatically);
		
		JMenuItem mntmShowPhoto = new JMenuItem("Show photo");
		mntmShowPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runGraphviz(photoPath);
				if(Photo != null){
					setImageLabel("G:\\graphviz-2.38\\WorkSpace\\show.jpg");
					Logs.append("Show the photo that you choose.\n");
				}
			}
		});
		mntmShowPhoto.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnRun.add(mntmShowPhoto);
		
		JMenu mnSave = new JMenu("Save");
		mnSave.setIcon(new ImageIcon(myWindows.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif")));
		mnSave.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		menuBar.add(mnSave);
		
		JMenuItem mntmSaveLog = new JMenuItem("Save log");
		mntmSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Save log.\n");
				File file = new File("C:\\Users\\asus\\Desktop\\Recently\\log.txt");
				if(!file.exists())
					try {  
			            file.createNewFile();
			            Logs.append("The file(log.txt) didn't exists and has been created.\n"); 
			        } catch (IOException e1) {  
			            e1.printStackTrace();
			        } 
				String str = Logs.getText();
				byte bt[] = new byte[1024];  
		        bt = str.getBytes();
				try {  
		            FileOutputStream in = new FileOutputStream(file);  
		            try {  
		                in.write(bt, 0, bt.length);  
		                in.close();  
		                Logs.append("Save log successfull!\n"); 
		            } catch (IOException e2) { 
		                e2.printStackTrace();  
		            }  
		        } catch (FileNotFoundException e3) { 
		            e3.printStackTrace();  
		        }
			}
		});
		mntmSaveLog.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnSave.add(mntmSaveLog);
		
		JMenuItem mntmSaveOuputs = new JMenuItem("Save ouputs");
		mntmSaveOuputs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.append("Save outputs.\n");
				File file = new File("C:\\Users\\asus\\Desktop\\Recently\\outputs.txt");
				if(!file.exists())
					try {  
			            file.createNewFile();
			            Logs.append("The file(outputs.txt) didn't exists and has been created.\n"); 
			        } catch (IOException e1) {  
			            e1.printStackTrace();
			        } 
				String str = output.getText();
				byte bt[] = new byte[1024];  
		        bt = str.getBytes();
				try {  
		            FileOutputStream in = new FileOutputStream(file);  
		            try {  
		                in.write(bt, 0, bt.length);  
		                in.close();  
		                Logs.append("Save outputs successfull!\n"); 
		            } catch (IOException e2) { 
		                e2.printStackTrace();  
		            }  
		        } catch (FileNotFoundException e3) { 
		            e3.printStackTrace();  
		        }
			}
		});
		mntmSaveOuputs.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnSave.add(mntmSaveOuputs);
		
		JMenu mnInput = new JMenu("Input");
		mnInput.setIcon(new ImageIcon(myWindows.class.getResource("/javax/swing/plaf/metal/icons/ocean/iconify-pressed.gif")));
		mnInput.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		menuBar.add(mnInput);
		
		JMenu mnShortestpath = new JMenu("Point");
		mnShortestpath.setIcon(new ImageIcon(myWindows.class.getResource("/org/eclipse/jface/images/dots_button.gif")));
		mnShortestpath.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnInput.add(mnShortestpath);
		
		JMenuItem mntmSinglePoint = new JMenuItem("Single point");
		mntmSinglePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logs.append("Single point.\n");
				SinglePointDialog spd = new SinglePointDialog(frmLab);
				spd.setVisible(true);
			}
		});
		mntmSinglePoint.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnShortestpath.add(mntmSinglePoint);
		
		JMenuItem mntmDoublePoints = new JMenuItem("Double points");
		mntmDoublePoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logs.append("Double points\n");
				DoublePointsDialog dpd = new DoublePointsDialog(frmLab);
				dpd.setVisible(true);
			}
		});
		mntmDoublePoints.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnShortestpath.add(mntmDoublePoints);
		
		JMenuItem mntmNewtext = new JMenuItem("newText");
		mntmNewtext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logs.append("You click \"newText\"\n");
				NewTextDialog ntp = new NewTextDialog(frmLab);
				ntp.setVisible(true);
			}
		});
		mntmNewtext.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		mnInput.add(mntmNewtext);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(myWindows.class.getResource("/org/eclipse/jface/dialogs/images/help.png")));
		mnHelp.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		menuBar.add(mnHelp);
		
		JMenuItem mntmReadme = new JMenuItem("README");
		mntmReadme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Logs.getText().length() == 0)
					Logs.append("@author Lushouchen&Likang\n@date 2017/9/14\n\n");
				else
					Logs.append("\n@author Lushouchen&Likang\n@date 2017/9/14\n\n");
			}
		});
		mntmReadme.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		mnHelp.add(mntmReadme);
	
		JLabel lblOutputBox = new JLabel("Output Box");
		lblOutputBox.setForeground(UIManager.getColor("Button.darkShadow"));
		lblOutputBox.setBounds(662, 13, 84, 28);
		panel.add(lblOutputBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(576, 50, 253, 370);
		panel.add(scrollPane_1);
		
		output = new JTextArea();
		output.setEditable(false);
		scrollPane_1.setViewportView(output);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		lblOutputBox.setLabelFor(output);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(14, 50, 548, 370);
		panel.add(scrollPane_2);
		
		Photo = new JLabel("");
		Photo.setIcon(new ImageIcon("G:\\graphviz-2.38\\WorkSpace\\show.jpg"));
		Photo.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(Photo);
		
		JLabel lblPhoto_1 = new JLabel("Photo");
		lblPhoto_1.setForeground(UIManager.getColor("Button.darkShadow"));
		lblPhoto_1.setBackground(Color.WHITE);
		lblPhoto_1.setBounds(246, 18, 72, 18);
		panel.add(lblPhoto_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\Eclipse_WorkPlace\\Lab1Test\\app.png"));
		label.setBounds(37, 49, 146, 153);
		frmLab.getContentPane().add(label);
		
		JTextArea thanks = new JTextArea();
		thanks.setFont(new Font("Mongolian Baiti", Font.ITALIC, 19));
		thanks.setText("    This is the project of lab1 that was written by Lu shouchen and Li kang...\r\n    \r\n    Thanks for your probation!\r\n");
		thanks.setColumns(15);
		thanks.setWrapStyleWord(true);
		thanks.setLineWrap(true);
		thanks.setEditable(false);
		thanks.setTabSize(4);
		thanks.setBounds(37, 241, 240, 136);
		frmLab.getContentPane().add(thanks);

	}
}
