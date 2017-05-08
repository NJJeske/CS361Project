package sprint4;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.*;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class ChronoGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int WIDTH = 850;
	private static final int HEIGHT = 650;	
	private chronotimer t;
	
	private JRadioButton[] channelSelection = new JRadioButton[8];
	//private JRadioButton[] chPorts = new JRadioButton[8];

	private JRadioButton[] channelState = new JRadioButton[8];
	private JButton[] channelButtons = new JButton[8];
	private JButton[] keyPad = new JButton[13];
	JTextArea mainArea, numPad, printerArea;
	
	private ButtonGroup chSelect = new ButtonGroup();
	private ButtonGroup sensors = new ButtonGroup();
	private ButtonGroup event = new ButtonGroup();
	
	private JPanel Interface = new JPanel();
	private JPanel ports = new JPanel();
	private JPanel numPadGrid = new JPanel();
	
	JPanel frame2 = new JPanel();
	
	
	
    JPanel pane2= new JPanel() ,pane3 = new JPanel() ;
	
	private JPanel functions = new JPanel();
	
	
	/**
	 * Create the frame.
	 */
	public ChronoGUI(chronotimer timer) {
		t = timer;
        createContent();
	}
	
	private void createContent( )
	{
		frame2.setBounds(1400, 400, 600, 200);
		frame2.setLayout(new GridLayout(3,1));
		setTitle("Chronotimer");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		setResizable(true);
	    setLocationRelativeTo(null);
	    Interface.setLayout(null);
	    
	        numPadGrid.setLayout(new GridLayout(4,3));
	        
	        createMainPanel();
	        createSideButtons();
	        createBottomPanel();        
	        
	        Interface.setBorder(BorderFactory.createLineBorder(Color.black));
	        ports.setBorder(BorderFactory.createLineBorder(Color.black));
	        //ports.setLayout(new BorderLayout());
	        numPadGrid.setBounds(500, 300, 150, 150);
	        Interface.add(numPadGrid);
	        
	        getContentPane().add(Interface, BorderLayout.CENTER);
	        getContentPane().add(functions, BorderLayout.LINE_START);

	        getContentPane().add(frame2, BorderLayout.SOUTH);

	        frame2.add(ports);
	        frame2.add(pane3);
	        frame2.add(pane2);

			frame2.setVisible(true);

	        setVisible(true);
		
	}
	private void createSideButtons()
	{
		functions.setLayout(new BoxLayout(functions, BoxLayout.PAGE_AXIS));

        
        JButton btnPower = new JButton("Power");
        btnPower.setFont(new Font("Tahoma", Font.BOLD, 13));
        functions.add(btnPower);
        btnPower.addActionListener(new powerListener());
		
        JButton btnExport = new JButton("Export");
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnExport.setBounds(42, 115, 97, 38);
        functions.add(btnExport);
        btnExport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(t.powerState()){
        			if(t.powerState() && !numPad.getText().isEmpty() && numPad.getText().matches(("[0-9]+") ) && t.getRun() != null){
        			 t.export(Integer.parseInt(numPad.getText()));
        			 System.out.println("export successful");
        			}
        			numPad.setText("");
        		}
        	}
        });
        
        JButton btnPrint = new JButton("print");
        btnPrint.setFont(new Font("Tahoma", Font.BOLD, 13));
        functions.add(btnPrint);
        btnPrint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(t.powerState()){
        			if(t.getPrinter().state())
        			{
        				mainArea.setText(mainArea.getText().concat(t.getRun().print() + '\n'));
        			}
        		}
        	}
        });

        JButton btnSwap = new JButton("Swap");
        btnSwap.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSwap.setBounds(50, 456, 75, 38);
        btnSwap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(t.getRun() != null)
        			if(t.getRun() instanceof IND)
        				((IND)t.getRun()).swap();

        	}
        });
        functions.add(btnSwap);
        
        JButton btnTime = new JButton("time");
        btnSwap.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSwap.setBounds(50, 456, 75, 38);
        btnSwap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		t.getTime().SetTime(System.currentTimeMillis());

        	}
        });
        functions.add(btnTime);
        

        
        JButton btnNewRun = new JButton("New Run");
        btnNewRun.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(t.powerState()){
        			 if(t.getRun() == null){
		        		t.createRun();
						printerArea.setText(printerArea.getText().concat("creating a new run" + '\n'));
        			 }else
        				 printerArea.setText(printerArea.getText().concat("run already exists. end it first" + '\n'));
        		}


        	}
        });
        btnNewRun.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewRun.setBounds(450, 602, 150, 38);
        functions.add(btnNewRun);
        
        JButton btnEndRun = new JButton("End Run");
        btnEndRun.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnEndRun.setBounds(597, 602, 150, 38);
        functions.add(btnEndRun);
        btnEndRun.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(t.powerState()){
       			 if(t.getRun() != null){
		        		t.endRun();
		        		printerArea.setText(printerArea.getText().concat("ending current run" + '\n'));
       			 }else
       				printerArea.setText(printerArea.getText().concat("no run to end. create one" + '\n'));
        		}
        	}
        });
		
        JRadioButton rdbtnIND = new JRadioButton("IND");
        rdbtnIND.setFont(new Font("Tahoma", Font.BOLD, 13));
        functions.add(rdbtnIND);
        rdbtnIND.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED && t.powerState())
				printerArea.setText(printerArea.getText().concat("Event Type: IND" + '\n'));
				t.setEvent("IND");
			}
        	
        });
        
        JRadioButton rdbtnPAIRIND = new JRadioButton("PAIRIND");
        rdbtnPAIRIND.setFont(new Font("Tahoma", Font.BOLD, 13));
        functions.add(rdbtnPAIRIND);
        rdbtnPAIRIND.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED && t.powerState())
					printerArea.setText(printerArea.getText().concat("Event Type: PARAIND" + '\n'));
				t.setEvent("PARAIND");
			}
        	
        });
        
        JRadioButton rdbtnGROUP = new JRadioButton("GROUP");
        rdbtnGROUP.setFont(new Font("Tahoma", Font.BOLD, 13));
        functions.add(rdbtnGROUP);
        rdbtnGROUP.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED && t.powerState())
					printerArea.setText(printerArea.getText().concat("Event Type: GROUP" + '\n'));
				t.setEvent("GRP");
				
			}
        	
        });
        
        JRadioButton rdbtnPARAGRP = new JRadioButton("PARAGRP");
        rdbtnPARAGRP.setFont(new Font("Tahoma", Font.BOLD, 13));
        functions.add(rdbtnPARAGRP);
        rdbtnPARAGRP.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED && t.powerState())
					printerArea.setText(printerArea.getText().concat("Event Type: PARAGRP" + '\n'));
				t.setEvent("PARAGRP");
				
			}
        	
        });
        event.add(rdbtnIND);
        event.add(rdbtnPAIRIND);
        event.add(rdbtnGROUP);
        event.add(rdbtnPARAGRP);
	}
	private void createBottomPanel()
	{

        
        JRadioButton rdbtnGate = new JRadioButton("GATE");
        rdbtnGate.setFont(new Font("Tahoma", Font.BOLD, 13));
        rdbtnGate.setBounds(160, 487, 63, 25);
        rdbtnGate.setActionCommand("Gate");
        pane3.add(rdbtnGate);
        
        JRadioButton rdbtnEye = new JRadioButton("EYE");
        rdbtnEye.setFont(new Font("Tahoma", Font.BOLD, 13));
        rdbtnEye.setBounds(225, 487, 63, 25);
        rdbtnEye.setActionCommand("Eye");

        pane3.add(rdbtnEye);
        
        JRadioButton rdbtnPad = new JRadioButton("PAD");
        rdbtnPad.setFont(new Font("Tahoma", Font.BOLD, 13));
        rdbtnPad.setBounds(297, 487, 63, 25);
        rdbtnPad.setActionCommand("Pad");

        pane3.add(rdbtnPad);

        
        sensors.add(rdbtnGate);
        sensors.add(rdbtnEye);
        sensors.add(rdbtnPad);
        
        
        
        JButton btnConnect = new JButton("Connect");
        btnConnect.setFont(new Font("Tahoma", Font.BOLD, 13));
        ports.add(btnConnect);
        btnConnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int channelNum = 0;
				if(sensors.getSelection() != null)
				channelNum = Integer.parseInt(chSelect.getSelection().getActionCommand());
				if(t.powerState())
				switch(sensors.getSelection().getActionCommand())
				{
				case "Gate":
					if(!t.ch[channelNum].hasSensor()) 
						t.CONN(channelNum, new gate());
					else
						printerArea.setText(printerArea.getText().concat("Channel " + channelNum + " already has a sensor !" + '\n'));
				
					break;
				case "Eye": 
					if(!t.ch[channelNum].hasSensor()) 
						t.CONN(channelNum, new electric_eye());
					else
						printerArea.setText(printerArea.getText().concat("Channel " + channelNum + " already has a sensor !" + '\n'));
					break;
				case "Pad": 
					if(!t.ch[channelNum].hasSensor()) 
						t.CONN(channelNum, new pressurePad());
					else
						printerArea.setText(printerArea.getText().concat("Channel " + channelNum + " already has a sensor !" + '\n'));
					break;
				case "None": System.out.println("error??????");
					break;
				default:
					
				}
			}
        });
        
        JButton btnDisconnect = new JButton("Disconnect");
        btnDisconnect.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnDisconnect.setBounds(297, 602, 110, 38);
        Interface.add(btnDisconnect);
        btnDisconnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int channelNum = 0;
				if(sensors.getSelection() != null){
					channelNum = Integer.parseInt(chSelect.getSelection().getActionCommand());
						switch(sensors.getSelection().getActionCommand())
						{
						case "Gate": 
							if(t.ch[channelNum].hasSensor()) 
								t.ch[channelNum].disconnectSensor();
							else
								printerArea.setText(printerArea.getText().concat("no sensor attached !" + '\n'));
							break;
						case "Eye":
							if(t.ch[channelNum].hasSensor()) 
								t.ch[channelNum].disconnectSensor();
							else
								printerArea.setText(printerArea.getText().concat("no sensor attached !" + '\n'));
							break;
						case "Pad":  
							if(t.ch[channelNum].hasSensor()) 
								t.ch[channelNum].disconnectSensor();
							else
								printerArea.setText(printerArea.getText().concat("no sensor attached !" + '\n'));
							break;
						case "None": System.out.println("error??????");
							break;
						default:
							
						}
				}
			}
        });
        
        JButton btnTrigger = new JButton("Trigger");
        btnTrigger.setFont(new Font("Tahoma", Font.BOLD, 13));
        Interface.add(btnTrigger);
        btnTrigger.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int channelNum = 0;
				if(sensors.getSelection() != null){
					channelNum = Integer.parseInt(chSelect.getSelection().getActionCommand());
					if(t.ch[channelNum].getSensor() != null)
						t.ch[channelNum].getSensor().trigger();
				}
			}
        });
        
        pane2 = new JPanel();
        
        pane2.add(btnTrigger);
        pane2.add(btnConnect);
        pane2.add(btnDisconnect);
	}
	private void createMainPanel()
	{
        //---------------JTextArea
        
        printerArea = new JTextArea("");
        printerArea.setEditable(false);
        
        printerArea = new JTextArea();
        printerArea.setEditable(false);


    //------------JScrollPanes
    
        JScrollPane mainBar = new JScrollPane(printerArea);
        mainBar.setBounds(175, 270, 239, 150);
        mainBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Interface.add(mainBar);
        
        JScrollPane printBar = new JScrollPane(printerArea);
        printBar.setBounds(458, 82, 239, 150);
        printBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Interface.add(printBar);
    
    //--------------JLabel
    
        JLabel chPortlbl = new JLabel("channel Ports");
        ports.add(chPortlbl);
        
        JLabel lblNewLabel = new JLabel("Enable/Disable");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(110, 126, 97, 16);
        Interface.add(lblNewLabel);
        
        JLabel label = new JLabel("Enable/Disable");
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBounds(110, 207, 97, 16);
        Interface.add(label);
        
//        JLabel lblStart = new JLabel("Start");
//        lblStart.setFont(new Font("Tahoma", Font.BOLD, 12));
//        lblStart.setBounds(160, 86, 50, 16);
//        Interface.add(lblStart);
//        
//        JLabel lblFinish = new JLabel("Finish");
//        lblFinish.setFont(new Font("Tahoma", Font.BOLD, 12));
//        lblFinish.setBounds(160, 171, 50, 16);
//        Interface.add(lblFinish);
        
        JLabel lblTimer = new JLabel("ChronoTimer 1009");
        lblTimer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTimer.setBounds(297, 26, 140, 16);
        Interface.add(lblTimer);
    
   //--------------JButtons
    
        
        int x=190, y=530;
        
        x=202;y=75;
        
        for(int i = 0 ; i < 8; i++){
        	
        	channelButtons[i] = new JButton(Integer.toString(i+1));
        	channelButtons[i].setFont(new Font("Tahoma", Font.BOLD, 8));
        	channelButtons[i].setBounds(x, y, 39, 38);
        	channelButtons[i].setActionCommand(Integer.toString(i+1));
        	channelButtons[i].addActionListener(new channelPushTrigger());
        	 Interface.add(channelButtons[i]);
        	 
         	x+= 51;
        	if(i == 3) {x= 202; y = 160;}
        }
        
        JButton btnPrint = new JButton("printer pwr");
        btnPrint.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnPrint.setBounds(530, 38, 97, 38);
        Interface.add(btnPrint);
        btnPrint.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!t.getPrinter().state())
					t.getPrinter().powerOn();
				else
					t.getPrinter().powerOff();

			};
        
        });

        x = 580; y = 302;
        for(int i = 0 ; i< 13 ; i++){
        	if(i < 9){
        	keyPad[i] = new JButton(Integer.toString(i+1));
        	keyPad[i].setFont(new Font("Tahoma", Font.BOLD, 10));
            keyPad[i].setActionCommand(Integer.toString(i+1));
            keyPad[i].addActionListener(new numPad());
            numPadGrid.add(keyPad[i]);
            
            x+= 35;
        	if(i == 2) {x= 580; y = 339;}
        	if(i == 5) {x= 580; y = 375;}
        	}
        }
        
        
        JButton btnNumpad11 = new JButton("0");
        btnNumpad11.setFont(new Font("Tahoma", Font.BOLD,10));
        numPadGrid.add(btnNumpad11);
        btnNumpad11.setActionCommand(Integer.toString(0));
        btnNumpad11.addActionListener(new numPad());

        
        JButton btnNumpad12 = new JButton("#");
        btnNumpad12.setFont(new Font("Tahoma", Font.BOLD, 10));
        numPadGrid.add(btnNumpad12);
        btnNumpad12.setActionCommand("#");
        btnNumpad12.addActionListener(new ActionListener(){
        	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(t.powerState() && !numPad.getText().isEmpty() && numPad.getText().matches(("[0-9]+") ) && t.getRun() != null){
					t.getRun().addCompetitor(Integer.parseInt(numPad.getText()));
					printerArea.setText(printerArea.getText().concat(numPad.getText() + "<" + t.getTime().toString()) + ">" + '\n');
					//clear numPad
				}
				numPad.setText("");
			}
        });

        
        JButton btnNumpad10 = new JButton("*");
        btnNumpad10.setFont(new Font("Tahoma", Font.BOLD, 10));
        numPadGrid.add(btnNumpad10);
        btnNumpad10.setActionCommand("*");
        
        numPad = new JTextArea();


    //JRadioButtons
    
    
    for(int i = 0 ; i < 8; i++){
    	
    	channelSelection[i] = new JRadioButton(Integer.toString(i+1));
    	channelSelection[i].setFont(new Font("Tahoma", Font.BOLD, 13));
    	ports.add(channelSelection[i]);
    	chSelect.add(channelSelection[i]);
    	channelSelection[i].setActionCommand(Integer.toString(i+1));
    }
    
    x=202;y=126;
    
    for(int i = 0 ; i < 8; i++){
    	
    	channelState[i] = new JRadioButton(Integer.toString(i+1));
    	channelState[i].setBounds(x, y, 39, 25);
    	channelState[i].addItemListener(new channelState());
    	 Interface.add(channelState[i]);
    	 
     	x+= 51;
    	if(i == 3) {x= 202; y = 207;}
    }
	}
	private class powerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!t.powerState()){
				t.turnOn();
				printerArea.setText(printerArea.getText().concat("Initializing... Ready ! " + '\n'
				));
			}
			else{
				t.turnOff();
				printerArea.setText(printerArea.getText().concat("Good-Bye... ! " + '\n'));
				}
		}
	}
	private class channelState implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			int index=0;
			for(int i =0; i <= 7 ; i++)
				if(e.getItemSelectable() == channelState[i])
					index = i+1;
			
				if(e.getStateChange() == ItemEvent.SELECTED && t.powerState()){
					//printerArea.setText(printerArea.getText().concat("channel " + index + " enabled" + '\n'));
					t.channelPads[1][index].push();
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED && t.powerState()){
					//printerArea.setText(printerArea.getText().concat("channel " + index + " disabled" + '\n'));
					t.channelPads[1][index].push();
				}
		}
	}
	private class channelPushTrigger implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int x  =Integer.parseInt(e.getActionCommand());
			if(t.powerState() && t.ch[x].state())
			{
				t.channelPads[0][x].push();
//				printerArea.setText(printerArea.getText().concat("triggered  ! " + '\n'
//				));
			}
		}
	}
	
	private class numPad implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			numPad.setText(numPad.getText().concat(e.getActionCommand()));
		}
	}

	public void displayText(String str){
		printerArea.setText(printerArea.getText().concat(str + '\n'));
	}
}
