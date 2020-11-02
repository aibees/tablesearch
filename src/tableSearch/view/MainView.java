package tableSearch.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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
import javax.swing.event.MouseInputAdapter;

import tableSearch.listener.mouse.ClickListener;

public class MainView extends JFrame {
	
	// Frame Variable
	private static final long serialVersionUID = 26351915L;
	private final int VIEW_HEIGHT = 550;
	private final int VIEW_WIDTH  = 520;

	// Left Panel Variable
	private JList<String> result;
	
	// Center Panel Variable
	GridBagLayout gbl_c = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints gbc_c = new GridBagConstraints();
	
	private JLabel title = new JLabel("-", SwingConstants.CENTER);
	private JLabel tx_pk = new JLabel();
	private JLabel tx_ts = new JLabel();
	private JPanel list = new JPanel();
	private JTextArea comment;
	
	
	// for test
	private boolean f = false;
	
	
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
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
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
				String[] retList = {"CM101T", "CM102T", "CM103T", 
						 "CM104T", "NAP101T", "NAP102T",
						 "NAP103T", "NAP601T","CM101T", "CM102T", "CM103T", 
						 "CM104T", "NAP101T", "NAP102T",
						 "NAP103T", "NAP601T"};
				
				String[] retList1 = {"CM101T", "CM102T", "CM103T"};
				if(f) {
					setLeftList(retList);
					f = false;
				} else {
					setLeftList(retList1);
					f = true;
				}
			}
		});

		JButton save = new JButton("수정");
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
		result = new JList<String>();
		result.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
		    	
		        JList list = (JList)e.getSource();

		        if(e.getClickCount() == 2) {
		            int index = list.locationToIndex(e.getPoint());
		            String item = list.getModel().getElementAt(index).toString();
		            
		            HashMap<String, Object> data = new HashMap<String, Object>();
		            data.put("title", item);
		            data.put("pk", "Primary Key");
		            data.put("ts", "Table Space");
		            data.put("comment", item + "은 지불에 쓰이는 테이블\r\n01:기준정보\r\n02:지불정보\r\n02:지불정보\r\n02:지불정보\r\n스크롤테스트\r\n스크롤이\r\n생\r\n길\r\n까");
		            
		            ArrayList<String> columns = new ArrayList<String>();
		            columns.add("COM,회사,VARCHAR2, ");
		            columns.add("ACNT,회계,VARCHAR2, ");
		            columns.add("CYSLCD,전표,NUMBER,개인정보비대상");
		            columns.add("COM,회사,VARCHAR2, ");
		            columns.add("ACNT,회계,VARCHAR2, ");
		            columns.add("CYSLCD,전표,NUMBER,개인정보비대상");
		            data.put("columns", columns);
		            setTableData(data);
		        }
		    }
		});

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

		comment = new JTextArea();
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

		
		tx_pk.setBackground(Color.WHITE);
		tx_pk.setOpaque(true);
		tx_pk.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel lb_ts = new JLabel("Table Space", SwingConstants.CENTER);
		lb_ts.setBackground(Color.LIGHT_GRAY);
		lb_ts.setOpaque(true);
		lb_ts.setBorder(BorderFactory.createLineBorder(Color.black));

		
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
		
		list.setLayout(gbl_c);
		list.setBackground(Color.WHITE);
		list.setOpaque(true);
		list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		
		JScrollPane clist = new JScrollPane(list);
		clist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		// 주요정보
		setGridConst(gbl, gbc, header,  0, 0, 5, 1,  1.0f, 0.03f);
		panel.add(header);
		// PK_LABEL
		setGridConst(gbl, gbc, lb_pk,   0, 1, 1, 1,  0.1f, 0.03f);
		panel.add(lb_pk);
		// PK_TEXT
		setGridConst(gbl, gbc, tx_pk,   1, 1, 4, 1,  0.9f, 0.03f);
		panel.add(tx_pk);
		// TableSpace_LABEL
		setGridConst(gbl, gbc, lb_ts,   0, 2, 1, 1,  0.1f, 0.03f);
		panel.add(lb_ts);
		// TableSpace_TEXT
		setGridConst(gbl, gbc, tx_ts,   1, 2, 4, 1,  0.9f, 0.03f);
		panel.add(tx_ts);
		// 컬럼정보
		setGridConst(gbl, gbc, column,  0, 3, 5, 1,  1.0f, 0.04f);
		panel.add(column);
		// th_name
		setGridConst(gbl, gbc, th_name, 0, 4, 1, 1,  0.1f, 0.03f);
		panel.add(th_name);
		// th_cmt
		setGridConst(gbl, gbc, th_cmt,  1, 4, 1, 1, 0.25f, 0.03f);
		panel.add(th_cmt);
		// th_type
		setGridConst(gbl, gbc, th_type, 2, 4, 1, 1, 0.25f, 0.03f);
		panel.add(th_type);
		// th_oth
		setGridConst(gbl, gbc, th_oth,  3, 4, 2, 1,  0.4f, 0.03f);
		panel.add(th_oth);
		// column list
		setGridConst(gbl, gbc, clist,   0, 5, 5, 1,  1.0f, 0.85f);
		panel.add(clist);
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

	/********************************************************************
	 ****                      Setter, Getter Method                 ****
	 ********************************************************************/
	// List Setting
	private void setLeftList(String[] retList) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(String t : retList) {
			model.addElement(t);
		}
		result.setModel(model);
	}
	
	// Table Data Setting
	private void setTableData(HashMap<String, Object> data) {
		this.title.setText((String)data.get("title"));
		this.tx_pk.setText((String)data.get("pk"));
		this.tx_ts.setText((String)data.get("ts"));
		this.comment.setText((String)data.get("comment"));
		
		ArrayList<String> columns = (ArrayList<String>)data.get("columns");
		this.list.removeAll();
		int cnt = 0;
		
		gbc_c.weightx = 1.0;
		gbc_c.weighty = 1.0;
		
		for(String column : columns) {
			String[] c = column.split(",");
			
			JLabel jl0 = new JLabel(c[0]);
			setGridConst(gbl_c, gbc_c, jl0, 0, cnt++, 1, 1,  0.1f, 0.03f);
			this.list.add(jl0);

			JLabel jl1 = new JLabel(c[1]);
			setGridConst(gbl_c, gbc_c, jl1,  1, cnt++, 1, 1, 0.25f, 0.03f);
			this.list.add(jl1);

			JLabel jl2 = new JLabel(c[2]);
			setGridConst(gbl_c, gbc_c, jl2, 2, cnt++, 1, 1, 0.25f, 0.03f);
			this.list.add(jl2);

			JLabel jl3 = new JLabel(c[3]);
			setGridConst(gbl_c, gbc_c, jl3,  3, cnt++, 2, 1,  0.4f, 0.03f);
			this.list.add(jl3);
		}
	}
	
	/********************************************************************
	 ****                       Utilization Method                   ****
	 ********************************************************************/
	private void setGridConst(GridBagLayout gbl, GridBagConstraints gbc, JComponent c, int x, int y, int gw, int gh, float w, float h) {
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
