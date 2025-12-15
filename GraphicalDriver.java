import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class GraphicalDriver{
    private JComboBox<String> methodsBox;
    private JComboBox<String> currStageBox;
    private JComboBox<String> nextStageBox;
    private JLabel [] waitingLabels;
    private JLabel [] machineLabels;
    private JLabel [] finishedLabels;
    private JPanel bottomPanel;
    private JPanel consolePanel;
    private JLabel totalEarningsLabel;    
    private JLabel consoleLabel;
    private JLabel outputLabel;
    private JPanel holdPeople;
    private SpeedQueen sq;
    private int ruidEntered;
    private double balanceEntered;

    //Creates the visual of the machines
    private class WasherPanel extends JPanel {
        private WasherPanel() {
            super();
            this.setBackground(new Color(180,240,210));
        }
        @Override
        public void paintComponent(Graphics g) {
            Graphics g2 = (Graphics) g;
            super.paintComponent(g);
            int[] washerDim = { 70, 90 };
            int tabY = (this.getHeight() / 2) - 65;
            int tabX = 30;
           
            g.setColor(new Color(22, 139, 247));
            g.fillRect(tabX, tabY+30, washerDim[0], washerDim[1]);
            g.setColor(new Color(164, 206, 245));
            g.fillRect(40, 35, 50, 5);
            g2.fillOval(40,45,50,50);
            tabX += washerDim[0] + 15;
        }
    }

    private class DryerPanel extends JPanel {
        private DryerPanel() {
            super();
            this.setBackground(new Color(180,240,210));
        }
        @Override
        public void paintComponent(Graphics g) {
            Graphics g2 = (Graphics) g;
            super.paintComponent(g);
            int[] washerDim = { 70, 90 };
            int tabY = (this.getHeight() / 2) - 65;
            int tabX = 30;
           
            g.setColor(new Color(230,230,230));
            g.fillRect(tabX, tabY+30, washerDim[0], washerDim[1]);
            g.setColor(new Color(100, 101, 102));
            g.fillRect(40, 35, 50, 5);
            g2.fillOval(40,45,50,50);
            tabX += washerDim[0] + 15;
        }
    }

    public GraphicalDriver (){
        //Setting up the JFrame window that everything will be displayed on
        JFrame disp = new JFrame("SpeedQueen Simulation");
        disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp.setSize(1400, 800);
        disp. setResizable(false);
        disp.setLocationRelativeTo(null);

        //Create the SpeedQueen object
        sq = new SpeedQueen();

        //JFrame panel that the students will input the textfile name  
        JFrame enterField = new JFrame("Welcome to SpeedQueen");
        JPanel enterPanel = new JPanel();        
        JPanel titlePanel = new JPanel();

        JLabel welcomeTo = new JLabel("Welcome to");
        JLabel speed = new JLabel("Speed");
        speed.setFont(new Font("Consolas", Font.BOLD, 18));
        speed.setForeground(new Color(22, 139, 247));
        JLabel queen = new JLabel("Queen");
        queen.setFont(new Font("Consolas", Font.BOLD, 18));
        queen.setForeground(new Color(160,205,185));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
        titlePanel.add(welcomeTo);
        titlePanel.add(speed);
        titlePanel.add(queen);
        welcomeTo.setFont(new Font("Consolas", Font.PLAIN, 18));

        JLabel fileLabel = new JLabel("Enter File Name: ");
        fileLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
        JTextField fileField = new JTextField(10);
        JButton enterButton = new JButton("Enter");
        JPanel holdsPanel = new JPanel();
        enterField.add(titlePanel, BorderLayout.NORTH);
        holdsPanel.add(fileLabel);
        holdsPanel.add(fileField);
        holdsPanel.add(enterButton);
        enterPanel.add(holdsPanel, BorderLayout.CENTER);
        enterPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
        enterField.add(enterPanel);
        enterField.setSize(400, 200);
        enterField.setLocationRelativeTo(null);

        enterButton.addActionListener((ActionEvent e) -> {
            if(fileField.getText().equals("") || !Files.exists(Paths.get(fileField.getText()))){
                JOptionPane.showMessageDialog(null, "Invalid File Name");
            }
            else{
                sq.createSimulation(fileField.getText());
                enterField.dispose();
                printWaiting(); //adds people to the Waiting once csv file is entered
            }
        });
        //if the button is pressed "ENTER" then close the field and 
        JPanel panel = new JPanel();
        panel.setBackground(new Color(180,240,210));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20)); 

        //CenterPanel ARRAY STRUCTURE WILL BE VISUALIZED here    
        JPanel centerPanel = new JPanel(); 
        centerPanel.setBackground(new Color(180,240,210));
        centerPanel.setPreferredSize(new Dimension(200, 100));

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200)); 

        JPanel waitingPanel = new JPanel();
        waitingPanel.setBackground(new Color(180,240,210));

        waitingPanel.setLayout(new BoxLayout(waitingPanel, BoxLayout.X_AXIS));
        JLabel waitingLabel = new JLabel("Waiting");
        waitingLabel.setFont(new Font("Consolas", Font.PLAIN,18));
        //THIS IS THE BOX THAT WILL HOLD THE PEOPLE THAT ARE CURRENTLY WAITING
       

        holdPeople = new JPanel(); //this is the RECTANGLE
        holdPeople.setBackground(new Color(164, 206, 245));
        holdPeople.setMaximumSize(new Dimension(900, 85));
        holdPeople.setPreferredSize(new Dimension(900,85)); 
        JScrollPane otherScroll = new JScrollPane(holdPeople, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        otherScroll.setBackground(new Color(180,240,210));
        otherScroll.setPreferredSize(new Dimension(900, 85));
        otherScroll.setMaximumSize(new Dimension(900, 85));
        otherScroll.setBorder(null);
        waitingPanel.add(waitingLabel);
        waitingPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        waitingPanel.add(otherScroll);

        JPanel machineGridPanel = new JPanel(); //HOLDS THE WASHERS AND DRYERS ONLY
        machineGridPanel.setBackground(new Color(180,240,210));
        machineGridPanel.setLayout(new GridLayout(2, 7)); 
        
        for(int i = 0; i < 6; i++){
            if(i == 0){
                JLabel washerLabel = new JLabel("Washers");
                washerLabel.setFont(new Font("Consolas", Font.PLAIN,18));
                machineGridPanel.add(washerLabel);
            }
            machineGridPanel.add(new WasherPanel());
        }
        for(int j = 0; j < 6; j++){
            if(j == 0){
                JLabel dryerLabel = new JLabel(" Dryers");
                dryerLabel.setFont(new Font("Consolas", Font.PLAIN,18));
                machineGridPanel.add(dryerLabel);
            }
            machineGridPanel.add(new DryerPanel());
        }

        waitingLabels = new JLabel[20];
        for(int i = 0; i < waitingLabels.length; i++){
            waitingLabels[i] = new JLabel("");
            waitingLabels[i].setFont(new Font("Consolas", Font.PLAIN,15));

            holdPeople.add(waitingLabels[i]);
        }
        
        Component[] cells = machineGridPanel.getComponents(); 
        machineLabels = new JLabel[cells.length];
        for(int i = 1 ; i < cells.length; i++){
            if(i != 7){
                machineLabels[i] = new JLabel("Empty");   
                machineLabels[i].setFont(new Font("Consolas", Font.PLAIN, 15));                   
                JPanel theCell = (JPanel) cells[i]; 
                theCell.add(machineLabels[i]);
            }
        }

        //SOUTH of BorderLayout: this will display the outputs in the form of a JLabel instead of the terminal
        bottomPanel = new JPanel();
        consolePanel = new JPanel();     
        consolePanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30)); 
   
        consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));
        consolePanel.setBackground(Color.BLACK);
        bottomPanel.setBackground(Color.BLACK);
        JScrollPane bottomScroll = new JScrollPane(consolePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bottomScroll.setPreferredSize(new Dimension(1350, 250));
        bottomScroll.setMaximumSize(new Dimension(1350, 250));
        bottomScroll.setBorder(null);
        bottomPanel.add(bottomScroll);

        consoleLabel = new JLabel("Console");
        consoleLabel.setFont(new Font("Courier New", Font.PLAIN,16));
        consoleLabel.setForeground(Color.WHITE);
        consoleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        totalEarningsLabel = new JLabel("Total Earnings: $" + sq.getTotalEarnings());
        totalEarningsLabel.setFont(new Font("Courier New", Font.PLAIN,16));
        totalEarningsLabel.setForeground(Color.WHITE);
        totalEarningsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        consolePanel.add(consoleLabel);
        consolePanel.add(totalEarningsLabel);

        //NORTH of BorderLayout: Holds all the parameters for methods
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(180,240,210));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        String[] methods = {"Calculate Cost", "Check Balance", "Update Balance", "Find Available Spot", "Move Customers", "Scarlet Cashback", "Next Turn"};
        methodsBox = new JComboBox<>(methods);
        JLabel methodsLabel = new JLabel("Method: ");
        methodsLabel.setFont(new Font("Consolas", Font.PLAIN,15));
        topPanel.add(methodsLabel);
        topPanel.add(methodsBox);

       //Balance
        JTextField balanceField = new JTextField();
        JLabel balance = new JLabel("Amount: ");
        balance.setFont(new Font("Consolas", Font.PLAIN,15));
        topPanel.add(balance);
        topPanel.add(balanceField);
        
        //RUID Field
        JTextField idField = new JTextField();
        JLabel idLabel = new JLabel("RUID: ");
        idLabel.setFont(new Font("Consolas", Font.PLAIN,15));
        topPanel.add(idLabel);
        topPanel.add(idField);
        
        //CurrentStage
        String[] currStage = {"Waiting", "Washers", "Dryers", "Finished"};
        currStageBox = new JComboBox<>(currStage);
        JLabel currLabel = new JLabel("Current Stage: ");
        currLabel.setFont(new Font("Consolas", Font.PLAIN,15));
        topPanel.add(currLabel);
        topPanel.add(currStageBox);

        //NextStage
        String[] nextStage = {"Waiting", "Washers", "Dryers", "Finished"};
        nextStageBox = new JComboBox<>(nextStage);
        JLabel nextLabel = new JLabel("Next Stage: ");
        nextLabel.setFont(new Font("Consolas", Font.PLAIN,15));
        topPanel.add(nextLabel);
        topPanel.add(nextStageBox);

        if(getSelectedMethod().equals("Calculate Cost")){ //DEFAULT MUST BE HERE AND DUPLICATED
                balance.setVisible(false);
                balanceField.setVisible(false);
                topPanel.revalidate();
        }
        methodsBox.addActionListener(e -> {
            if(getSelectedMethod().equals("Calculate Cost") || getSelectedMethod().equals("Check Balance") || getSelectedMethod().equals("Move Customers")){ //DEFAULT
                idLabel.setVisible(true);
                idField.setVisible(true);
                currLabel.setVisible(true);
                currStageBox.setVisible(true);
                nextLabel.setVisible(true);
                nextStageBox.setVisible(true);
                balance.setVisible(false);
                balanceField.setVisible(false);
                topPanel.revalidate();
            }
            if(getSelectedMethod().equals("Update Balance")){
                balance.setVisible(true);          
                balanceField.setVisible(true);
                idLabel.setVisible(true);
                idField.setVisible(true);
                currLabel.setVisible(true);
                currStageBox.setVisible(true);
                nextLabel.setVisible(false);
                nextStageBox.setVisible(false);
                topPanel.revalidate();
            }
            if(getSelectedMethod().equals("Find Available Spot")){
                currLabel.setVisible(true);
                currStageBox.setVisible(true);  
                idLabel.setVisible(false);
                idField.setVisible(false);
                balance.setVisible(false);          
                balanceField.setVisible(false);
                nextLabel.setVisible(false);
                nextStageBox.setVisible(false);
                topPanel.revalidate();
            }
            if(getSelectedMethod().equals("Scarlet Cashback") || getSelectedMethod().equals("Next Turn")){ //MAY NEED TO CHANGE
                currLabel.setVisible(false);
                currStageBox.setVisible(false);  
                idLabel.setVisible(false);
                idField.setVisible(false);
                balance.setVisible(false);          
                balanceField.setVisible(false);
                nextLabel.setVisible(false);
                nextStageBox.setVisible(false);
                topPanel.revalidate();
            }
        });
        currStageBox.addActionListener(e -> updateStatus());
        nextStageBox.addActionListener(e -> updateStatus());
      
        bottomPanel.setPreferredSize(new Dimension(450, 260));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        
        JPanel finishedPanel = new JPanel();
        finishedPanel.setBackground(new Color(180,240,210));
        finishedPanel.setLayout(new BoxLayout(finishedPanel, BoxLayout.X_AXIS));
        JLabel finishedLabel = new JLabel("Finished");
        finishedLabel.setFont(new Font("Consolas", Font.PLAIN,18));
        //THIS IS THE BOX THAT WILL HOLD THE PEOPLE THAT ARE ALL FINISHED, EVERYONE WILL END UP HERE
        JPanel otherHoldsPeople = new JPanel();
        otherHoldsPeople.setBackground(new Color(164, 206, 245));
        otherHoldsPeople.setMaximumSize(new Dimension(900, 80));
        otherHoldsPeople.setPreferredSize(new Dimension(900,80));
        finishedLabels = new JLabel[20];
        for(int i = 0; i < finishedLabels.length; i++){
            finishedLabels[i] = new JLabel("");
            finishedLabels[i].setFont(new Font("Consolas", Font.PLAIN,15));
            otherHoldsPeople.add(finishedLabels[i]);
        }

        JScrollPane scroll = new JScrollPane(otherHoldsPeople, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(new Color(180,240,210));
        scroll.setPreferredSize(new Dimension(900, 85));
        scroll.setMaximumSize(new Dimension(900, 85));
        scroll.setBorder(null);
        finishedPanel.add(finishedLabel);
        finishedPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        finishedPanel.add(scroll);

        //Execute Button
        JButton executeButton = new JButton("Execute");
        topPanel.add(executeButton);
        executeButton.addActionListener(e -> {
            consolePanel.removeAll();
            consoleLabel = new JLabel("Console");
            consoleLabel.setFont(new Font("Courier New", Font.PLAIN,16));
            consoleLabel.setForeground(Color.WHITE);
            consoleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            outputLabel = new JLabel(); //this will be changed based off of what method is called
            outputLabel.setFont(new Font("Courier New", Font.PLAIN,16));
            outputLabel.setForeground(Color.WHITE);
            outputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            totalEarningsLabel = new JLabel("Total Earnings: $" + sq.getTotalEarnings());
            totalEarningsLabel.setFont(new Font("Courier New", Font.PLAIN,16));
            totalEarningsLabel.setForeground(Color.WHITE);
            totalEarningsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            consolePanel.add(consoleLabel);
            consolePanel.add(totalEarningsLabel);
            consolePanel.add(outputLabel);

            if(!(idField.getText().equals(""))) {
                ruidEntered = Integer.parseInt(idField.getText());
            }
            if(!(balanceField.getText().equals(""))) {
                balanceEntered = Double.parseDouble(balanceField.getText());
            }
            if(getSelectedMethod().equals("Calculate Cost")){ 
                if(idField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Method is missing information.");
                }
                else {
                    double cost = sq.calculateCost(ruidEntered, getSelectedCurrentStage(), getSelectedNextStage());
                    JLabel costLabel = new JLabel("Calculated Cost to Move to " + getSelectedNextStage() + ": $" + cost );
                    consolePanel.add(costLabel);
                    costLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                    costLabel.setForeground(Color.WHITE);
                    costLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    idField.setText("");
                }    
            }
            else if(getSelectedMethod().equals("Check Balance")){
                if(idField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Method is missing information.");
                }
                else {
                    boolean check = sq.checkBalance(ruidEntered, getSelectedCurrentStage(), getSelectedNextStage());
                    JLabel checkLabel = (new JLabel("Balance Sufficiency to Move to " + getSelectedNextStage() + ": " + check ));
                    consolePanel.add(checkLabel);
                    checkLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                    checkLabel.setForeground(Color.WHITE);
                    checkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    idField.setText("");
                }
            }
            else if(getSelectedMethod().equals("Update Balance")){

                if(idField.getText().equals("") || balanceField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Method is missing information.");
                }
                else {
                    double beforeUpdate = sq.getTotalBalance(ruidEntered);
                    double deBefore = sq.getTotalEarnings();
                    sq.updateBalance(ruidEntered, balanceEntered, getSelectedCurrentStage());
                    double afterUpdate = sq.getTotalBalance(ruidEntered);
                    double deAfter = sq.getTotalEarnings(); 
                    idField.setText("");
                    balanceField.setText("");
                    JLabel updateLabel = (new JLabel("Total Earnings: $" + deBefore + " then $" + deAfter + ", Balance: $" + beforeUpdate + " then $" + afterUpdate));
                    consolePanel.add(updateLabel);
                    updateLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                    updateLabel.setForeground(Color.WHITE);
                    updateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                }

            }
            else if(getSelectedMethod().equals("Find Available Spot")){
                int spot = sq.findAvailableSpot(getSelectedCurrentStage());
                if(spot == -1){
                    JLabel availLabel = new JLabel("No available spots in " + getSelectedCurrentStage() + ".");
                    consolePanel.add(availLabel);
                    availLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                    availLabel.setForeground(Color.WHITE);
                    availLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                }
                else{
                    JLabel availLabelOther = new JLabel("Available Spot in " + getSelectedCurrentStage() + ": index " + spot);
                    consolePanel.add(availLabelOther);
                    availLabelOther.setFont(new Font("Courier New", Font.PLAIN,16));
                    availLabelOther.setForeground(Color.WHITE);
                    availLabelOther.setAlignmentX(Component.LEFT_ALIGNMENT);
                }
            }
            else if(getSelectedMethod().equals("Move Customers")){

                if(idField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Method is missing information.");
                } else if (getSelectedCurrentStage() == Stage.WAITING && !(getSelectedNextStage() == Stage.WASHER || getSelectedNextStage() == Stage.DRYER)) {
                    JOptionPane.showMessageDialog(null, "Invalid stage transiton from Waiting.");
                } else if (getSelectedCurrentStage() == Stage.WASHER && !(getSelectedNextStage() == Stage.WAITING || getSelectedNextStage() == Stage.DRYER)) {
                    JOptionPane.showMessageDialog(null, "Invalid stage transiton from Washer.");
                } else if (getSelectedCurrentStage() == Stage.DRYER && !(getSelectedNextStage() == Stage.FINISHED)) {
                    JOptionPane.showMessageDialog(null, "Invalid stage transiton from Dryer. Can only move to Finished.");
                } else if (getSelectedCurrentStage() == Stage.FINISHED) {
                    JOptionPane.showMessageDialog(null, "Customer is in Finished stage, cannot be moved.");
                }
                else {
                    sq.moveCustomers(ruidEntered, getSelectedCurrentStage(), getSelectedNextStage());
                    printWaiting(); 
                    int index = 1;
                    for(Customer c : sq.getWashersArray()){
                        if(c!= null){
                            machineLabels[index].setText(c.getName());
                            machineLabels[index].revalidate(); 
                            index++;                        
                        }              
                        if(c == null){                        
                            machineLabels[index].setText("Empty");
                            machineLabels[index].revalidate(); 
                            index++;                        
                        }           
                    }
                    index = 8;
                    for(Customer c : sq.getDryersArray()){
                        if(c!= null){
                            machineLabels[index].setText(c.getName());
                            machineLabels[index].revalidate(); 
                            index++;
                        }    
                        if(c == null){                        
                            machineLabels[index].setText("Empty");
                            machineLabels[index].revalidate(); 
                            index++;                        
                        }                        
                    }
                    printFinished();
                    JLabel moveLabel = new JLabel("Move Customers has executed. If it does not look correct, check your input parameters and method implementation.");
                    consolePanel.add(moveLabel);
                    moveLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                    moveLabel.setForeground(Color.WHITE);
                    moveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                }
            }
            else if(getSelectedMethod().equals("Scarlet Cashback")){
                JLabel cashLabel;
                boolean cashback = sq.scarletCashback();
                if (cashback){
                    cashLabel = new JLabel("Threshold met. Cashback has been given :)");
                } else {
                    cashLabel = new JLabel("Threshold not met. No cashback :(");
                }
                
                consolePanel.add(cashLabel);
                cashLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                cashLabel.setForeground(Color.WHITE);
                cashLabel.setAlignmentX(Component.LEFT_ALIGNMENT);                
                printLaundry(sq); //printLaundry() when scarletCashback() is called
            }
            else if(getSelectedMethod().equals("Next Turn")){ 
                nextTurn(sq);
                printWaiting(); 
                int index = 1;
                for(Customer c : sq.getWashersArray()){
                    if(c!= null){
                        machineLabels[index].setText(c.getName());
                        machineLabels[index].revalidate(); 
                        index++;                        
                    }              
                    if(c == null){                        
                        machineLabels[index].setText("Empty");
                        machineLabels[index].revalidate(); 
                        index++;                        
                    }           
                }
                index = 8;
                for(Customer c : sq.getDryersArray()){
                    if(c!= null){
                        machineLabels[index].setText(c.getName());
                        machineLabels[index].revalidate(); 
                        index++;
                    }    
                    if(c == null){                        
                        machineLabels[index].setText("Empty");
                        machineLabels[index].revalidate(); 
                        index++;                        
                    }                        
                }
                printFinished();
                totalEarningsLabel.setText("Total Earnings: $"+ sq.getTotalEarnings());
            }
            consolePanel.revalidate(); 
            consolePanel.repaint();    
        } );
        
        centerPanel.add(waitingPanel);
        centerPanel.add(machineGridPanel);
        centerPanel.add(finishedPanel);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        panel.add(topPanel, BorderLayout.NORTH);
        disp.add(panel);
        disp.setResizable(false);
        enterField.setResizable(false);
        disp.setVisible(true);
        enterField.setVisible(true);

    }

    private void printLaundry(SpeedQueen sq){
        JLabel totalPpl = new JLabel ("Total People: " + 20);
        consolePanel.add(totalPpl);
        totalPpl.setFont(new Font("Courier New", Font.PLAIN,16));
        totalPpl.setForeground(Color.WHITE);
        totalPpl.setAlignmentX(Component.LEFT_ALIGNMENT);        
            
        JLabel waitingCustom = new JLabel("Waiting Customers:" + "\t");
        consolePanel.add(waitingCustom);
        waitingCustom.setFont(new Font("Courier New", Font.PLAIN,16));
        waitingCustom.setForeground(Color.WHITE);
        waitingCustom.setAlignmentX(Component.LEFT_ALIGNMENT);     

        for (Customer customer: sq.getWaitingArray()) {
            if (customer != null) {
                JLabel waitingArr = new JLabel(customer.getName() + "  RUID:" + customer.getRUID() + "  $" + customer.getBalance());
                consolePanel.add(waitingArr);
                waitingArr.setFont(new Font("Courier New", Font.PLAIN,16));
                waitingArr.setForeground(Color.WHITE);
                waitingArr.setAlignmentX(Component.LEFT_ALIGNMENT); 
            }
        } 
        JLabel washersCustom = new JLabel("Customer Using Washers:" + "\t");
        consolePanel.add(washersCustom);
        washersCustom.setFont(new Font("Courier New", Font.PLAIN,16));
        washersCustom.setForeground(Color.WHITE);
        washersCustom.setAlignmentX(Component.LEFT_ALIGNMENT);  
        for (Customer customer: sq.getWashersArray()) {
            if (customer != null) {
                JLabel washerArr = new JLabel(customer.getName() + "  RUID:" + customer.getRUID() + "  $" + customer.getBalance());
                consolePanel.add(washerArr);
                washerArr.setFont(new Font("Courier New", Font.PLAIN,16));
                washerArr.setForeground(Color.WHITE);
                washerArr.setAlignmentX(Component.LEFT_ALIGNMENT); 
            }
        }
        JLabel dryersCustom = new JLabel("Customer Using Dryers:" + "\t");
        consolePanel.add(dryersCustom);
        dryersCustom.setFont(new Font("Courier New", Font.PLAIN,16));
        dryersCustom.setForeground(Color.WHITE);
        dryersCustom.setAlignmentX(Component.LEFT_ALIGNMENT);  
        for (Customer customer: sq.getDryersArray()) {
            if (customer != null) {
                JLabel dryerArr = new JLabel(customer.getName() + "  RUID:" + customer.getRUID() + "  $" + customer.getBalance());
                consolePanel.add(dryerArr);
                dryerArr.setFont(new Font("Courier New", Font.PLAIN,16));
                dryerArr.setForeground(Color.WHITE);
                dryerArr.setAlignmentX(Component.LEFT_ALIGNMENT); 
            }
        }
        JLabel finishCustom = new JLabel("Finished Customers:" + "\t");
        consolePanel.add(finishCustom);
        finishCustom.setFont(new Font("Courier New", Font.PLAIN,16));
        finishCustom.setForeground(Color.WHITE);
        finishCustom.setAlignmentX(Component.LEFT_ALIGNMENT);  
        for (Customer customer: sq.getFinishedArray()) {
            if (customer != null) {
                JLabel finishedArr = new JLabel(customer.getName() + "  RUID:" + customer.getRUID() + "  $" + customer.getBalance());
                consolePanel.add(finishedArr);
                finishedArr.setFont(new Font("Courier New", Font.PLAIN,16));
                finishedArr.setForeground(Color.WHITE);
                finishedArr.setAlignmentX(Component.LEFT_ALIGNMENT); 
            }
        }
    }

    private boolean nextTurn(SpeedQueen sq) {
        // Stop condition: finished is full OR (everyone in waiting's checkBalance is false AND washers is empty AND dryers is empty)
        boolean canPay = false;
        for(int i = 0; i < sq.getWaitingArray().length; i++){
            //if the person want to go to washer
            if(sq.getWaitingArray()[i] != null) {

                if(sq.getWaitingArray()[i].hasWashed() == false){
                    if(sq.checkBalance(sq.getWaitingArray()[i].getRUID(), Stage.WAITING, Stage.WASHER) ) {
                        canPay = true;
                    }
                } 
                else { //if the person wants to go to dryer
                    if( sq.checkBalance(sq.getWaitingArray()[i].getRUID(), Stage.WAITING, Stage.DRYER) ) {
                        canPay = true;
                    }
                }
            }      
        }

        boolean washersInUse = false;
        for(int i = 0; i < sq.getWashersArray().length; i++){
            //there shouldn't be anyone using the washer
            if(sq.getWashersArray()[i] != null) {
                washersInUse = true;
            }
                
        }

        boolean dryersInUse = false;
        for(int i = 0; i < sq.getDryersArray().length; i++){
            //there shouldn't be anyone using the washer
            if(sq.getDryersArray()[i] != null) {
                dryersInUse = true;
            }
                
        }

        if (sq.findAvailableSpot(Stage.FINISHED) == -1 || (canPay == false && washersInUse == false && dryersInUse == false)) { // Check to see if the game is "over" (no more possible player movement) and return false if so
            // sq.scarletCashback(); // Check if daily earnings reached $25 and reimburse customers if so
            // JLabel firstLabel = (new JLabel("All customer's have completed their laundry! The day is over."));
            // consolePanel.add(firstLabel);
            // firstLabel.setFont(new Font("Courier New", Font.PLAIN,16));
            // firstLabel.setForeground(Color.WHITE);
            // firstLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            // return false;

            JLabel firstLabel = (new JLabel("All customers have completed their laundry! The day is over."));
            consolePanel.add(firstLabel);
            firstLabel.setFont(new Font("Courier New", Font.PLAIN,16));
            firstLabel.setForeground(Color.WHITE);
            firstLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
         
                JLabel cashLabel;
                boolean cashback = sq.scarletCashback();
                if (cashback){
                    cashLabel = new JLabel("Threshold met. Cashback has been given :)");
                } else {
                    cashLabel = new JLabel("Threshold not met. No cashback :(");
                }
                
                consolePanel.add(cashLabel);
                cashLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                cashLabel.setForeground(Color.WHITE);
                cashLabel.setAlignmentX(Component.LEFT_ALIGNMENT);                
                printLaundry(sq); //printLaundry() when scarletCashback() is called

                return false;


        } else {
            for (Customer customer : sq.getDryersArray()) {
                if (customer != null) {
                    sq.moveCustomers(customer.getRUID(), Stage.DRYER, Stage.FINISHED);
                }
            }
            for (Customer customer : sq.getWashersArray()) {
                if (customer != null) {
                    if(sq.findAvailableSpot(Stage.DRYER) != -1) {
                        if(sq.checkBalance(customer.getRUID(), Stage.WASHER, Stage.DRYER)) {
                            sq.moveCustomers(customer.getRUID(), Stage.WASHER, Stage.DRYER);
                        } else {
                            JLabel secondLabel = (new JLabel("Insufficient funds for: "  + customer.getName() + ". Moving to waiting."));
                            consolePanel.add(secondLabel);
                            secondLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                            secondLabel.setForeground(Color.WHITE);
                            secondLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                            sq.moveCustomers(customer.getRUID(), Stage.WASHER, Stage.WAITING);
                        }
                    }else {
                        JLabel thirdLabel = (new JLabel("No available dryers. " + customer.getName() + " moving to waiting."));
                        consolePanel.add(thirdLabel);
                        thirdLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                        thirdLabel.setForeground(Color.WHITE);
                        thirdLabel.setAlignmentX(Component.LEFT_ALIGNMENT);   

                        sq.moveCustomers(customer.getRUID(), Stage.WASHER, Stage.WAITING);
                    }
                }
            }
            for (Customer customer : sq.getWaitingArray()) {
                if (customer != null) {
                    if(customer.hasWashed() == false) {
                        if(sq.findAvailableSpot(Stage.WASHER) != -1) {
                            if(sq.checkBalance(customer.getRUID(), Stage.WAITING, Stage.WASHER)) {
                                sq.moveCustomers(customer.getRUID(), Stage.WAITING, Stage.WASHER);
                            } else {
                                JLabel fourthLabel = (new JLabel("Insufficient funds for: " + customer.getName() + ". Remains in waiting."));
                                consolePanel.add(fourthLabel);
                                fourthLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                                fourthLabel.setForeground(Color.WHITE);
                                fourthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);   
                            }
                        }
                        else{
                            JLabel fifthLabel = (new JLabel("No available washers. " + customer.getName() + ". Remains in waiting."));
                            consolePanel.add(fifthLabel);
                            fifthLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                            fifthLabel.setForeground(Color.WHITE);
                            fifthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);  

                    }       
                        
                    } else if(customer.hasDried() == false) {
                        if(sq.findAvailableSpot(Stage.DRYER) != -1) {
                            if(sq.checkBalance(customer.getRUID(), Stage.WAITING, Stage.DRYER)) {
                                sq.moveCustomers(customer.getRUID(), Stage.WAITING, Stage.DRYER);
                            } 
                            JLabel sixthLabel = (new JLabel("Insufficient funds for: " + customer.getName() + ". Remains in waiting."));
                            consolePanel.add(sixthLabel);
                            sixthLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                            sixthLabel.setForeground(Color.WHITE);
                            sixthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);              
                        } 
                        else{
                             JLabel seventhLabel = (new JLabel("No available dryers. " + customer.getName() + " remains in waiting."));
                            consolePanel.add(seventhLabel);
                            seventhLabel.setFont(new Font("Courier New", Font.PLAIN,16));
                            seventhLabel.setForeground(Color.WHITE);
                            seventhLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
                        }
                                                         
                    } 
                }
            }
        }
        
        return true;
    }

    private void printWaiting() {
        int index = 0;
        for (Customer c : sq.getWaitingArray()) {
            if(c != null){
                waitingLabels[index].setText(c.getName() + "   ");
                index++;
            }
            if(c == null){                        
                waitingLabels[index].setText("");
                waitingLabels[index].revalidate(); 
                index++;                        
            }    
        }
    }

    private void printFinished() {
        int index = 0;
        for (Customer c : sq.getFinishedArray()) {
            if(c != null){
                finishedLabels[index].setText(c.getName() + "   ");
                index++;
            }
        }
    }
    
    private void updateStatus() {
        String methodStr = (String) methodsBox.getSelectedItem();
        String currStr = (String) currStageBox.getSelectedItem();
        String nextStr = (String) nextStageBox.getSelectedItem();
    }
     
    public String getSelectedMethod() {
        return (String) methodsBox.getSelectedItem();
    }
    
    public Stage getSelectedCurrentStage() {
        //it must return an int because our stage numbers are represented by enums
        String currStr = (String) currStageBox.getSelectedItem();
        if(currStr.equals("Waiting")){
            return Stage.WAITING;
        }
        else if(currStr.equals("Washers")){
            return Stage.WASHER;
        }
        else if(currStr.equals("Dryers")){
            return Stage.DRYER;
        }
        else{
            return Stage.FINISHED;
        }
    }

    public Stage getSelectedNextStage() {
        String nextStr = (String) nextStageBox.getSelectedItem();
        if(nextStr.equals("Waiting")){
            return Stage.WAITING;
        }
        else if(nextStr.equals("Washers")){
            return Stage.WASHER;
        }
        else if(nextStr.equals("Dryers")){
            return Stage.DRYER;
        }
        else{
            return Stage.FINISHED;
        }    
    }
    
    public void setSelectedMethod(String theMethod) {
        SwingUtilities.invokeLater(() -> {
            methodsBox.setSelectedItem(theMethod);
            updateStatus();
        });
    }

    public void setSelectedCurrent(String current) {
        SwingUtilities.invokeLater(() -> {
            currStageBox.setSelectedItem(current);
            updateStatus();
        });
    }

    public void setSelectedNext(String next) {
        SwingUtilities.invokeLater(() -> {
            nextStageBox.setSelectedItem(next);
            updateStatus();
        });
    }
    public static void main(String [] args){
        new GraphicalDriver();
    }
}