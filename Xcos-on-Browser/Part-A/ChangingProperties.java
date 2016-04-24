/*


  Instruction: Compile and Run the code as given in the Readme. First a window would open with Changing Properites label in the center.
               Right clicking that label would show three menu items. Selecting the First or the Third option, shows a message.
               On clicking the Properties menu item a new window would open up with three values to be entered by the user.
               The Background Color and Font Color fields must be entered in valid Hex code preceeded by '#', an error would show
               if the format is incorrect. A hint is provided in the Change Text field, which is the current text of the main label.
               On clicking Done or pressing Enter key, the changes would be seen on the main label.

  Resources : https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
              http://stackoverflow.com/questions/2339994/css-style-hex-strings-and-color-decode
              http://www.codejava.net/java-se/swing/jtextfield-basic-tutorial-and-examples
              http://www.java2s.com/Code/Java/Swing-JFC/SwingDefaultButton.htm

  */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class ChangingProperties {

    //Global Color variable for background, font, and hint.
    Color back = Color.WHITE;
    Color font = Color.BLACK;
    Color hint = Color.GRAY;

    //Private class variables which would be initialised in the constructor.
    private JLabel mainlabel;
    private JFrame frame;
    private JPanel panel;

    class showList extends JPopupMenu {

        //Menu items in are declared.
        private JMenuItem First;
        private JMenuItem Properties;
        private JMenuItem Third;

        //Method to show the list/menu on right click of the main label.
        public showList() {

            //Initialising the three menu items.
            First = new JMenuItem("First");
            Properties = new JMenuItem("Properties");
            Third = new JMenuItem("Third");
            //Adding the menu items to the menu.
            add(First);
            add(Properties);
            add(Third);

            //Action listener for the First menu item that displays a message.
            First.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(null, "You have clicked First, which has no task assigned!");
                }
            });

            //Action listener for the Properties menu item that open up a new window.
            Properties.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    propertiesClick();
                }
            });

            //Action listener for the Third menu item that displays a message.
            Third.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(null, "You have clicked Third, which has no task assigned!");
                }
            });
        }

    }

    //Method called on clicking the Properties menu item, that open a new window.
    public void propertiesClick() {

        //Initialising the JFrame of the new window.
        final JFrame userinput = new JFrame("User Input");
        JPanel uipanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Initialising the text field to change the background color of the main label.
        JTextField bgcolor = new JTextField(7);
        JLabel bglabel = new JLabel("Background Color: ");
        
        //Initialising the text field to change the color of the main label.
        JTextField ftcolor = new JTextField(7);
        JLabel ftlabel = new JLabel("Color: ");
  
        //Initialising the text field to change the text of the main label(Hint is being provided).
        JTextField ctext = new JTextField(mainlabel.getText(), 20);
        ctext.setForeground(hint);
        JLabel ctextlabel = new JLabel("Change Text: ");

        //Bring the Change Text field on focus when clicked by removing the hint.
        ctext.addFocusListener(new FocusListener() {

            //When focus is gained set the textfield blank and the foreground color to black.
            public void focusGained(FocusEvent e) {
                ctext.setText("");
                ctext.setForeground(Color.BLACK);
            }

            //When focus is lost and textfield is empty, show the hint.
            public void focusLost(FocusEvent e) {
                if(ctext.getText().length() == 0) {
                  ctext.setText(mainlabel.getText());
                  ctext.setForeground(hint);
                }
            }
        });

        //Button to submit and see the changes made to the main label.
        JButton done = new JButton("DONE");

        //To allow Enter key as an alternative to click Done button.
        userinput.getRootPane().setDefaultButton(done);

        //Action listener for the DONE button.
        done.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                //Temporary variable to check if the NumberFormatException has occured
                int exception = 0;

                //try to see if the background color or the font color have beeen entered correctly.
                try {
                    
                    //If the user has entered the background color, set the back variable to that.
                    if(bgcolor.getText().length() > 0) {
                  
                        back = Color.decode(bgcolor.getText());
                    }

                    //If the user has entered the font color, set the font variable to that.
                    if(ftcolor.getText().length() > 0) {
                   
                        font = Color.decode(ftcolor.getText());
                    } 
               //If the user has enterd an incorrect color format, show an error.
               } catch (NumberFormatException ne) {

                     exception = 1;
                     JOptionPane.showMessageDialog(null, "Enter the color in the Hex code preceeded with #", "Error", JOptionPane.ERROR_MESSAGE);
                     bgcolor.setText("");
                     ftcolor.setText("");
                 }

               //Variable to change the text of main label.
               String changeText = ctext.getText();   

               //If there is no exception, proceed.
               if(exception == 0) {
                 
                 //Set the given properties to the main label.
                   mainlabel.setBackground(back);
                   mainlabel.setForeground(font);   
                   mainlabel.setOpaque(true);
                 
                   //Change the text of the main label, if the user has entered one.
                   if (changeText.length() > 0) { 
                  
                       mainlabel.setText(changeText);
                   }
                   
                   //After all the properties are set, hide the userinput window.
                   userinput.setVisible(false);
               }
            }
        });

        //Set the coordinates of the various items in the window.
        c.insets = new Insets(0, 0, 0, 0);
        //Grid coordinates origin.
        c.gridx = 0;
        c.gridy = 0;
        //Anchor the contents towards left.
        c.anchor = GridBagConstraints.WEST;
        //Add the background color label.
        uipanel.add(bglabel,c);
        c.gridy += 1;
        //Add the font color label.
        uipanel.add(ftlabel,c);
        c.gridy += 1;
        //Add the change text label.
        uipanel.add(ctextlabel,c);
        c.gridy -= 2;
        c.gridx += 1;
        //Add the background color textfield.
        uipanel.add(bgcolor,c);
        c.gridy += 1;
        //Add the font color text field.
        uipanel.add(ftcolor,c);
        c.gridy += 1;
        //Add the text change field.
        uipanel.add(ctext,c);
        c.gridy += 1;
        //Add the done button.
        uipanel.add(done,c);

        //Set the userinput frame properties.
        userinput.setSize(400, 200);
        userinput.setLocationRelativeTo(null);
        userinput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add the uipanel to the userinput window.
        userinput.add(uipanel);
        userinput.setVisible(true);
    }

    //Right mouse click listener.
    class RightClickListener extends MouseAdapter {

        //On right click show the menu list.
        public void pulseList(MouseEvent e) {

            showList list = new showList();
            list.show(e.getComponent(), e.getX(), e.getY());
        }

        public void mousePressed(MouseEvent e) {

            if(e.isPopupTrigger()) {
        
                pulseList(e);
            }
        }

        public void mouseReleased(MouseEvent e) {

            if(e.isPopupTrigger()) {

                pulseList(e);
            }
        }
    }

  
    //Default contructor.
    public ChangingProperties() {

        //Add the main JFrame and set its properties.
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setTitle("Part - A(1)");
        frame.setSize(300, 300);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add JPanel that contains the main label.
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        //Add the main label whose  properties can be changed.
        mainlabel = new JLabel("Changing Properties");
        mainlabel.addMouseListener(new RightClickListener());

        //Add main label to the panel.
        panel.add(mainlabel);

        //Add panel to frame.
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args){

        //New object ChangingProperties.
        new ChangingProperties();
    }
}

