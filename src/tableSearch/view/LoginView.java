package tableSearch.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tableSearch.Execute;

public class LoginView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7335161521734473271L;

	private Execute execute;
	
	private JButton        btnLogin,
	                       btnInit;
	private JTextField     userText;
	private JPasswordField passText;
	private boolean        bLoginCheck;
	
	public LoginView() {
		setTitle("LOGIN");
		setSize(280, 150);
		setResizable(false);
        setLocation(800, 450);
        		
		// favicon
		String pwd = getClass().getResource(".").toString();
		String fvcPath = pwd.substring(6) + "../img/favicon.png";
		ImageIcon favicon = new ImageIcon(fvcPath);
		setIconImage(favicon.getImage());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		placeLoginPanel(panel);
		
		add(panel);
		
		setVisible(true);
	}
	
	private void placeLoginPanel(JPanel panel){
        panel.setLayout(null);     
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
       
        JLabel passLabel = new JLabel("Pass");
        passLabel.setBounds(10, 40, 80, 25);
        panel.add(passLabel);
       
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
       
        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
       
        btnInit = new JButton("Reset");
        btnInit.setBounds(10, 80, 100, 25);
        panel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
       
        btnLogin = new JButton("Login");
        btnLogin.setBounds(160, 80, 100, 25);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }
	
    public void isLoginCheck(){
        if(userText.getText().equals("1") && new String(passText.getPassword()).equals("1")){
            bLoginCheck = true;
           
            // 로그인 성공
            if(isLogin()){
                execute.showFrameTest(); // 메인화면 불러오기
            }                  
        }else{
            JOptionPane.showMessageDialog(null, "Faild");
        }
    }
    
    // mainProcess 연결하기
    public void setMain(Execute main) {
        this.execute = main;
    }
   
 
    public boolean isLogin() {     
        return bLoginCheck;
    }
}
