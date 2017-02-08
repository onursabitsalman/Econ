import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.SystemColor;

public class Econ extends JFrame {

	private JPanel contentPane;
	private final int SCREENHEIGHT= 800;
	private final int SCREENWIDTH= 498;
	
	private final int interval = 100;
	int i;
	Timer t;
	JProgressBar prg;
	
	JLabel lblNewLabel_1 = new JLabel("CLICK : Show Products");
	JLabel lblNewLabel_2 = new JLabel("CLICK : Print Txt");
	JLabel lblNewLabel_3 = new JLabel("CARREFOUR PRODUCTS");
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Econ frame = new Econ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Econ() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,SCREENWIDTH,SCREENHEIGHT);		
		contentPane = new JPanel();
		Color aColor = new Color(0x4352A3);
		contentPane.setBackground(aColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		
		ImageIcon icon = new ImageIcon("Pictures/cm.png");		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 103, 498, 498);
		lblNewLabel.setIcon(icon);		
		contentPane.add(lblNewLabel);
		
		Color bColor = new Color(0xb40000);
		
		lblNewLabel_1.setBounds(10, 612, 472, 38);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.window);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  
				  if(Desktop.isDesktopSupported())
				  {
				    try {
						Desktop.getDesktop().browse(new URI("https://www.carrefoursa.com/"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				  
			  }
			});
		contentPane.add(lblNewLabel_1);
		
		
		lblNewLabel_2.setBounds(0, 661, 472, 38);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(SystemColor.window);
		lblNewLabel_2.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				i = 0;
				t.start();
				new econ_OSS();
		
			}
		});
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setBounds(10, 54, 472, 38);
		lblNewLabel_3.setFont(new Font("Trajan Pro", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_3);
		
		prg = new JProgressBar(0,100);
		prg.setBounds(53, 710, 380, 31);
		prg.setValue(0);
		prg.setStringPainted(true);
		contentPane.add(prg);
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
			  
			  if(Desktop.isDesktopSupported())
			  {
			    try {
					Desktop.getDesktop().browse(new URI("https://www.carrefoursa.com/"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			  
		  }
		});
		
		
		
		
		t = new Timer(interval,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(i == 100){
					t.stop();
				
				}
				
				else{
					
					System.out.println(i);
					i++;
					prg.setValue(i);
						
				}
				repaint();
			}
		});

	}
}
