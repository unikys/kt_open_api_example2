package com.kt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.kt.api.KTOpenApiManager;

public class KTOpenApiGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2102462212149383119L;

	KTOpenApiManager apiManager;
	JPanel mainPanel;
	JLabel labelStatusBar;
	JLabel labelUser;

	public KTOpenApiGUI(KTOpenApiManager apiManager)
	{
		super("KT Open API Example");
		
		this.apiManager = apiManager;
		
		setBounds(100,100,700,500);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = this.getContentPane();
		mainPanel = new JPanel(new BorderLayout());

		this.renderStatusBar();
		this.renderToolBar();
		
		contentPane.add(mainPanel , BorderLayout.CENTER);

		this.setVisible(true);
	}
	
	
	private void renderStatusBar() {
		JPanel paneStatusBar = new JPanel(new BorderLayout());
		JLabel labelStatus = new JLabel("Status : ");
		
		labelStatusBar = new JLabel("");
		labelUser = new JLabel("Logged out");
		paneStatusBar.add(labelStatus , BorderLayout.WEST);
		paneStatusBar.add(labelStatusBar , BorderLayout.CENTER);
		paneStatusBar.add(labelUser, BorderLayout.EAST);
		paneStatusBar.setBackground(Color.LIGHT_GRAY);
		
		mainPanel.add(paneStatusBar , BorderLayout.SOUTH);		
	}
	

	private void renderToolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		
		ArrayList<JButton> toolbarButtonList = new ArrayList<JButton>();
		
		
		JButton buttonLogin = new JButton("API 연결");
		toolbarButtonList.add(buttonLogin);
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "구현 필요!");
			}
		});
		
		
		JButton buttonClick2Call = new JButton("클릭투콜");
		toolbarButtonList.add(buttonClick2Call);
		buttonClick2Call.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "구현 필요!");
			}
		});


		JButton buttonClick2CallResult = new JButton("클릭투콜 결과 조회");
		toolbarButtonList.add(buttonClick2CallResult);
		buttonClick2CallResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "구현 필요!");
			}
		});


		
		for(JButton button : toolbarButtonList)
			toolbar.add(button);
		
		mainPanel.add(toolbar , BorderLayout.NORTH);
	}
	
	public void setStatus(String status)
	{
		this.labelStatusBar.setText(status);
	}
	
	public void setUser(String user)
	{
		this.labelUser.setText("Login(" + user + ")");
	}

}
