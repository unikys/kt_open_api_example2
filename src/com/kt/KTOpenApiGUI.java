package com.kt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.kt.api.KTOpenApiId;
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
	
	JPanel paneClick2CallInterface;
	JTextField textCallee;
	JTextField textCaller;
	JTextField textClick2CallCallbackUrl;
	
	JPanel paneCIDInterface;
	JTextField textCIDCallbackUrl;
	
	JPanel paneSMSInterface;
	JTextField textSender;
	JTextField textReceiver;
	JTextField textDisplayAddress;
	JTextArea textSMS;

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
		
		
		this.renderClick2CallPane();
		this.renderCIDPane();
		this.renderSMSPane();
		
		contentPane.add(mainPanel , BorderLayout.CENTER);

		this.setVisible(true);
	}
	
	
	private void renderSMSPane() {
		paneSMSInterface = new JPanel(new FlowLayout());
		
		textSender = new JTextField(25);
		textReceiver = new JTextField(25);
		textDisplayAddress = new JTextField(20);
		textSMS = new JTextArea(1,25);
		textSMS.setText("This is an Open-API test message.");
		textSMS.setEditable(false);

		paneSMSInterface.add(new JLabel("Sender"));
		paneSMSInterface.add(textSender);
		paneSMSInterface.add(new JLabel("Receiver"));
		paneSMSInterface.add(textReceiver);
		paneSMSInterface.add(new JLabel("DisplayAddress"));
		paneSMSInterface.add(textDisplayAddress);
		
		paneSMSInterface.add(new JLabel("SMS Text"));
		paneSMSInterface.add(textSMS);
		JButton buttonSendSMS = new JButton("Send SMS");
		paneSMSInterface.add(buttonSendSMS);
		
		buttonSendSMS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, String> params = new HashMap<String, String>();
				String sender = textSender.getText();
				String receiver = textReceiver.getText();
				String text = textSMS.getText();
				String displayAddress = textDisplayAddress.getText();
				if(sender.trim().isEmpty() || receiver.trim().isEmpty() || text.trim().isEmpty() || displayAddress.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "필수 정보를 입력하지 않았습니다.");
					return;
				}
				params.put("sender", sender);
				params.put("receivers", receiver);
				params.put("displayAddress", displayAddress);
				params.put("text", text);
				
				
				apiManager.apiCall(KTOpenApiId.SMS_MAKE , params);
			}
		});
	}


	private void renderCIDPane() {
		paneCIDInterface = new JPanel(new FlowLayout());

		textCIDCallbackUrl = new JTextField(40);
		paneCIDInterface.add(new JLabel("Callback Url"));
		paneCIDInterface.add(textCIDCallbackUrl);
		
		JButton buttonSetCIDNoti = new JButton("Set CID Noti");
		paneCIDInterface.add(buttonSetCIDNoti);
		
		buttonSetCIDNoti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, String> params = new HashMap<String, String>();
				String callbackUrl = textCIDCallbackUrl.getText();
				if(callbackUrl.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "필수 정도를 입력하지 않았습니다.");
					return;
				}
				params.put("callback_url", callbackUrl); // callback���� url�Է�
				
				apiManager.apiCall(KTOpenApiId.CID_SET_NOTI , params);
				
			}
		});

	}


	private void renderClick2CallPane()
	{
		paneClick2CallInterface = new JPanel(new FlowLayout());

		textCallee = new JTextField(20);
		paneClick2CallInterface.add(new JLabel("Callee"));
		paneClick2CallInterface.add(textCallee);

		textCaller = new JTextField(20);
		paneClick2CallInterface.add(new JLabel("Caller"));
		paneClick2CallInterface.add(textCaller);
		JButton buttonClick2Call = new JButton("Click 2 Call");
		paneClick2CallInterface.add(buttonClick2Call);
		textClick2CallCallbackUrl = new JTextField(30);
		paneClick2CallInterface.add(new JLabel("Callback URL"));
		paneClick2CallInterface.add(textClick2CallCallbackUrl);
		
		buttonClick2Call.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, String> params = new HashMap<String, String>();
				String caller = textCaller.getText();
				String callee = textCallee.getText();
				String callbackUrl = textClick2CallCallbackUrl.getText();
				if(caller.trim().isEmpty() || callee.trim().isEmpty() || callbackUrl.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "필수 정보를 입력하지 않았습니다.");
					return;
				}
				params.put("caller", caller);
				params.put("callee", callee);
				params.put("cb_method", "url");
				params.put("cb_trgt_info", callbackUrl); 
				params.put("cb_format", ""); 
				params.put("callee_first",""); 
				apiManager.apiCall(KTOpenApiId.CLICK_2_CALL_MAKE , params);
			}
		});
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
		toolbar.setFloatable(true);
		
		ArrayList<JButton> toolbarButtonList = new ArrayList<JButton>();
		
		
		JButton buttonLogin = new JButton("API 연결");
		toolbarButtonList.add(buttonLogin);
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(apiManager.connect())
				{
					setStatus("연결 됨");
				}else
				{
					setStatus("연결 실패");
				}
			}
		});
		
		
		JButton buttonClick2Call = new JButton("클릭투콜");
		toolbarButtonList.add(buttonClick2Call);
		buttonClick2Call.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.remove(paneSMSInterface);
				mainPanel.remove(paneCIDInterface);
				mainPanel.add(paneClick2CallInterface , BorderLayout.CENTER);
				mainPanel.revalidate();
				repaint();
			}
		});


		JButton buttonSetupNumberNotification = new JButton("발신 번호 설정");
		toolbarButtonList.add(buttonSetupNumberNotification);
		buttonSetupNumberNotification.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.remove(paneSMSInterface);
				mainPanel.remove(paneClick2CallInterface);
				mainPanel.add(paneCIDInterface , BorderLayout.CENTER);
				mainPanel.revalidate();
				repaint();
			}
		});

		
		JButton buttonSendSMS = new JButton("단문 송신");
		toolbarButtonList.add(buttonSendSMS);
		buttonSendSMS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.remove(paneClick2CallInterface);
				mainPanel.remove(paneCIDInterface);
				mainPanel.add(paneSMSInterface , BorderLayout.CENTER);
				mainPanel.revalidate();
				repaint();
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
