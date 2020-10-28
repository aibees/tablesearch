package tableSearch.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 26351915L;
	private final int VIEW_HEIGHT = 550;
	private final int VIEW_WIDTH  = 500;
	
	public MainView() {
		
		//table = new JLabel();
		
		setTitle("Table Search");
		setResizable(false);
		setLocation(800, 450);
		
		JPanel mainPanel = new JPanel();
		setMainPanel(mainPanel);
		add(mainPanel);

		setSize(VIEW_WIDTH, VIEW_HEIGHT);
		pack();
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setMainPanel(JPanel panel) {
		JButton[] bt = new JButton[5];
		
		  for (int i = 0; i < bt.length; i++) {
	            bt[i] = new JButton("Button" + i);
	        }
		
		// set Layout
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		
        panel.add(getTopPanel(), BorderLayout.NORTH);
        panel.add(getLeftPanel(), BorderLayout.WEST);
        panel.add(getCenterPanel(), BorderLayout.CENTER);
        //panel.add(bt[0], BorderLayout.CENTER);
        panel.add(getBottomPanel(), BorderLayout.SOUTH);
	}
	
	/**
	 * 프로그램 상단 검색 입력 및 버튼이 들어갈 Panel 정의
	 * @return Header Panel
	 */
	private JPanel getTopPanel() {
		// Panel Setting
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
		panel.setBackground(Color.lightGray);
		// combo box
		String set[] = {"Table", "View", "Procedure"};
		JComboBox<String> combo = new JComboBox<String>(set);
		combo.setSize(40, 10);
		panel.add(combo);
		
		// Text Field
		JTextField search = new JTextField(20);
		search.setSize(50, 11);
		panel.add(search);
		
		// Button
		JButton query = new JButton("검색");
		query.setBackground(Color.darkGray);
		query.setForeground(Color.getHSBColor(360, 0, 100));
		query.setFocusPainted(false);
		query.setBorderPainted(false);
		query.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		query.setSize(15, 10);
		panel.add(query);
		query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("범위 : " + combo.getSelectedItem().toString());
				System.out.println("검색어 : " + search.getText().toUpperCase());
			}
		});
		return panel;
	}
	
	/**
	 * 검색 결과 리스트 출력하는 Panel 정의
	 * @return Left List Panel
	 */
	private JPanel getLeftPanel() {
		// Panel Setting
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//panel.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
		
		// Scroll Menu List
		String dset[] = {"CM101T", "CM102T", "CM103T", 
						 "CM104T", "NAP101T", "NAP102T",
						 "NAP103T", "NAP601T", "NAP602T",
						 "NAP603T", "NAP610T","CM101T", 
						 "CM102T", "CM103T", "TX202T",
						 "CM104T", "NAP101T", "NAP102T",
						 "NAP103T", "NAP601T", "NAP602T",
						 "NAP603T", "NAP610T"};
		JList<String> result = new JList<String>(dset);
		JScrollPane scroll = new JScrollPane(result);
		scroll.setPreferredSize(new Dimension(90, 390));
		panel.add(scroll);

		JButton newTable = new JButton("새로만들기");
		newTable.setBackground(Color.darkGray);
		newTable.setForeground(Color.getHSBColor(360, 0, 100));
		newTable.setFocusPainted(false);
		newTable.setBorderPainted(false);
		newTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(newTable, BorderLayout.SOUTH);

		return panel;
	}
	/**
	 * 선택된 테이블에 대한 세부정보 표시
	 * @return Center Panel
	 */
	private JPanel getCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		panel.setBackground(Color.white);
		// add inner panel
		panel.add(getInnerPanel(), BorderLayout.CENTER);
		
		return panel;
	}

	private JPanel getInnerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
						BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
						));

		// set Inner Panel
		// title Panel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(Color.white);

		JLabel title = new JLabel("테이블 이름이 들어갈 자리", SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setPreferredSize(new Dimension(15, 30));
		titlePanel.add(title, BorderLayout.CENTER);



		// detail Panel
		JPanel detailPanel = new JPanel();
		// comments Panel
		JPanel commentPanel = new JPanel();
		commentPanel.setLayout(new BorderLayout());
		commentPanel.setBackground(Color.white);

		JTextArea comment = new JTextArea();
		comment.setLineWrap(true);
		JScrollPane csc = new JScrollPane(comment);
		csc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		csc.setPreferredSize(new Dimension(30, 110));
		commentPanel.add(csc);

		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(commentPanel, BorderLayout.SOUTH);

		return panel;
	}
	
	/**
	 * 프로그램 하단에 위치할 Panel 정의
	 * @return Footer Panel
	 */
	private JPanel getBottomPanel() {
		// Panel Setting
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
		panel.setBackground(Color.BLACK);
		
		// Footer(Copyright)
		JLabel footer = new JLabel("© Copyright 2020 Shinsegae I&C. HR & Finance IT Team");
		footer.setSize(VIEW_WIDTH, 10);
		footer.setForeground(Color.getHSBColor(360, 0, 100));
		panel.add(footer);
		
		return panel;
	}

	// util method
	private GridBagConstraints setGridConst(int x, int y, int w, int h) {
		GridBagConstraints c = new GridBagConstraints();	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		return c;
	}
}
