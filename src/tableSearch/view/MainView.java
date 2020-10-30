package tableSearch.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tableSearch.listener.mouse.ListClickedListener;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 26351915L;
	private final int VIEW_HEIGHT = 550;
	private final int VIEW_WIDTH  = 520;
	private GridBagConstraints gbc = new GridBagConstraints();

	public MainView() {
		
		//table = new JLabel();
		
		setTitle("Table Search");
		setResizable(false);
		setLocation(800, 450);
		
		// favicon
		String pwd = getClass().getResource(".").toString();
		String fvcPath = pwd.substring(6) + "../img/favicon.png";
		ImageIcon favicon = new ImageIcon(fvcPath);
		setIconImage(favicon.getImage());

		JPanel mainPanel = new JPanel();
		setMainPanel(mainPanel);
		add(mainPanel);

		setSize(VIEW_WIDTH, VIEW_HEIGHT);
		//pack();
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

		JButton save = new JButton("저장");
		save.setBackground(Color.darkGray);
		save.setForeground(Color.getHSBColor(360, 0, 100));
		save.setFocusPainted(false);
		save.setBorderPainted(false);
		save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		save.setSize(15, 10);
		panel.add(save);
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
		result.addMouseListener(new ListClickedListener());

		JScrollPane scroll = new JScrollPane(result);
		scroll.setPreferredSize(new Dimension(90, 390));
		panel.add(scroll);

		JButton newTable = new JButton("새로만들기");
		newTable.setBackground(Color.darkGray);
		newTable.setForeground(Color.getHSBColor(360, 0, 100));
		newTable.setFocusPainted(false);
		newTable.setBorderPainted(false);
		newTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(newTable, BorderLayout.NORTH);

		return panel;
	}
	/**
	 * 선택된 테이블에 대한 세부정보 표시
	 * @return Center Panel
	 */
	private JPanel getCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		panel.setBackground(Color.getHSBColor(55, 22, 91));
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

		JLabel title = new JLabel("-", SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setPreferredSize(new Dimension(15, 30));
		titlePanel.add(title, BorderLayout.CENTER);


		// detail Panel
		JPanel detailPanel = new JPanel();
		setDetailPanel(detailPanel);

		// comments Panel
		JPanel commentPanel = new JPanel();
		commentPanel.setLayout(new BorderLayout());
		commentPanel.setBackground(Color.white);
		commentPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

		JTextArea comment = new JTextArea();
		comment.setLineWrap(true);
		JScrollPane csc = new JScrollPane(comment);
		csc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		csc.setPreferredSize(new Dimension(30, 110));
		commentPanel.add(csc);

		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(detailPanel, BorderLayout.CENTER);
		panel.add(commentPanel, BorderLayout.SOUTH);

		return panel;
	}

	private void setDetailPanel(JPanel panel) {
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(300, 300));
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// 주요정보
		JLabel header = new JLabel("주요정보", SwingConstants.CENTER);
		header.setBackground(Color.LIGHT_GRAY);
		header.setOpaque(true);
		header.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel lb_pk = new JLabel("PK", SwingConstants.CENTER);
		lb_pk.setBackground(Color.LIGHT_GRAY);
		lb_pk.setOpaque(true);
		lb_pk.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel tx_pk = new JLabel();
		tx_pk.setBackground(Color.WHITE);
		tx_pk.setOpaque(true);
		tx_pk.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel lb_ts = new JLabel("Table Space", SwingConstants.CENTER);
		lb_ts.setBackground(Color.LIGHT_GRAY);
		lb_ts.setOpaque(true);
		lb_ts.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel tx_ts = new JLabel();
		tx_ts.setBackground(Color.WHITE);
		tx_ts.setOpaque(true);
		tx_ts.setBorder(BorderFactory.createLineBorder(Color.black));

		// 칼럼
		JLabel column = new JLabel("칼럼", SwingConstants.CENTER);
		column.setBackground(Color.LIGHT_GRAY);
		column.setOpaque(true);
		column.setBorder(BorderFactory.createLineBorder(Color.black));

		// th-컬럼명
		JLabel th_name = new JLabel("이름", SwingConstants.CENTER);
		th_name.setBackground(Color.LIGHT_GRAY);
		th_name.setOpaque(true);
		th_name.setBorder(BorderFactory.createLineBorder(Color.black));

		// th-설명
		JLabel th_cmt = new JLabel("설명", SwingConstants.CENTER);
		th_cmt.setBackground(Color.LIGHT_GRAY);
		th_cmt.setOpaque(true);
		th_cmt.setBorder(BorderFactory.createLineBorder(Color.black));

		// th-타입
		JLabel th_type = new JLabel("타입", SwingConstants.CENTER);
		th_type.setBackground(Color.LIGHT_GRAY);
		th_type.setOpaque(true);
		th_type.setBorder(BorderFactory.createLineBorder(Color.black));

		// th-비고
		JLabel th_oth = new JLabel("비고", SwingConstants.CENTER);
		th_oth.setBackground(Color.LIGHT_GRAY);
		th_oth.setOpaque(true);
		th_oth.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel list = new JPanel();
		list.setBackground(Color.WHITE);
		list.setOpaque(true);
		list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		setColumnList(list);

		// 주요정보
		setGridConst(gbl, header, 0, 0, 5, 1, 1.0f, 0.03f);
		panel.add(header);
		// PK_LABEL
		setGridConst(gbl, lb_pk, 0, 1, 1, 1, 0.1f, 0.03f);
		panel.add(lb_pk);
		// PK_TEXT
		setGridConst(gbl, tx_pk, 1, 1, 4, 1, 0.9f, 0.03f);
		panel.add(tx_pk);
		// TableSpace_LABEL
		setGridConst(gbl, lb_ts, 0, 2, 1, 1, 0.1f, 0.03f);
		panel.add(lb_ts);
		// TableSpace_TEXT
		setGridConst(gbl, tx_ts, 1, 2, 4, 1, 0.9f, 0.03f);
		panel.add(tx_ts);
		// 컬럼정보
		setGridConst(gbl, column, 0, 3, 5, 1, 1.0f, 0.04f);
		panel.add(column);
		// th_name
		setGridConst(gbl, th_name, 0, 4, 1, 1, 0.1f, 0.03f);
		panel.add(th_name);
		// th_cmt
		setGridConst(gbl, th_cmt, 1, 4, 1, 1, 0.25f, 0.03f);
		panel.add(th_cmt);
		// th_type
		setGridConst(gbl, th_type, 2, 4, 1, 1, 0.25f, 0.03f);
		panel.add(th_type);
		// th_oth
		setGridConst(gbl, th_oth, 3, 4, 2, 1, 0.4f, 0.03f);
		panel.add(th_oth);
		// column list
		setGridConst(gbl, list, 0, 5, 5, 1, 1.0f, 0.85f);
		panel.add(list);
	}

	private void setColumnList(JPanel panel) {

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
	private void setGridConst(GridBagLayout gbl, JComponent c, int x, int y, int gw, int gh, float w, float h) {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = gw;
		gbc.gridheight = gh;
		gbc.weightx = w;
		gbc.weighty = h;
		gbl.setConstraints(c, gbc);
	}
}
