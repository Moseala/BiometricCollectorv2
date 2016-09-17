/*
 * ‘******************************************************
 * ‘***  BiometicCollector
 * ‘***  Author: Erik Clary
 * ‘******************************************************
 * ‘*** Purpose: This is the main class for the program.
 * ‘******************************************************
 * ‘*** October 14, 2015
 * ‘******************************************************
 * ‘*** Oct 14: Initial code written
 * ‘*** Mar 28: Updated UI, added multiple questions that are randomized, added functions to abandon test and clear data in case subject wants to.
 * ‘******************************************************
 * ‘*** Look at this!
 * ‘*** List all the places in the code where you did something interesting,
 * ‘*** clever or elegant.  If you did good work in this program and you want
 * ‘*** me to consider it in your grade, point it out here.
 * ‘*******************************************************
 */
package biometriccollector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Erik
 */
public class MainBMGUI extends javax.swing.JFrame{
    /*
    ‘******************************************************
    ‘***  updateAndOrgainze
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method updates and orgainizes the gui.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    private void updateAndOrgainize(){
        HashMap<Integer, Integer> keyCount = new HashMap<>();
        if(true){
            keystrokesAL.sort(null); //orgainizes inputs so to avoid reconstrucing what the user types.
            for(KeyPress x : keystrokesAL){ //this loop goes over all entries in keystrokesAL (the user's inputs) and re-calculates the 
                try{
                    Integer added = keyCount.get(x.getUnicodeValue())+1;//literally: for every key pressed, add its unicode value to the hashmap (null returned from get), if found, add 1 to its hash value.
                    keyCount.put(x.getUnicodeValue(), added);
                }
                catch(NullPointerException npex){
                    keyCount.put(x.getUnicodeValue(), 1);
                }
            }
            updateProgressBars(getTop10(keyCount));
            
            if(testFinished(keyCount)){
                //flush to file and end the program NOT IMPLEMENTED YET
                JOptionPane.showMessageDialog(null, "Test is finished.");
                outFile.flushBuffer();
            }
            
        }
    }
    /*
    ‘******************************************************
    ‘***  updateProgressBars
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method updates the progress bars on the gui.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    private void updateProgressBars(ArrayList<KeyCountWrapper> aList){
        try{
            pb0.setMaximum(MINNUMACCEPTED+1);
            pb0.setValue(aList.get(0).getCount());
        }
        catch(IndexOutOfBoundsException iooEx){
            
        }
    }
    
    /*
    ‘******************************************************
    ‘***  testFinished
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method tests to see if the test is finished or not.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    private boolean testFinished(HashMap keyCount){
        int totalOverMIN = 0;
        Iterator<Map.Entry<Integer, Integer>> iMap = keyCount.entrySet().iterator();
        while(iMap.hasNext()){
            if(iMap.next().getValue()>=MINNUMACCEPTED)//i hope that works >> next returns each key?
                totalOverMIN++;
        } //code adapted from http://examples.javacodegeeks.com/core-java/util/hashmap/java-hashmap-example/
        return totalOverMIN >= MINDIFFERENT;
    }
    
    /*
    ‘******************************************************
    ‘***  getTop10
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method gets only the top 10 key presses and puts them into a list for the gui to use.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    public ArrayList getTop10(HashMap keyCount){
        ArrayList<KeyCountWrapper> top10  = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> iMap = keyCount.entrySet().iterator();
        while(iMap.hasNext()){
            Map.Entry<Integer, Integer> x = iMap.next();
            top10.add(new KeyCountWrapper(x.getKey(), x.getValue()));
        }
        top10.sort(null);
        return top10;
    }

    /**
     * Creates new form MainBMGUI
     */
    public MainBMGUI() {
        initComponents();
        UserInput request = new UserInput(this, false);
        request.run();
        String questionType;
        
        Random n = new Random();
        int rand = n.nextInt(40);
        if(rand > 20){
            questionType = "dictative";
            if((25 >= rand) && (rand > 20)){
                jTextArea1.setText("Asset freezing is a legal process which prevents a defendant (usually an apparent fraudster) to an action from dissipating their assets from beyond the jurisdiction of a court so as to frustrate a potential judgment. It is widely recognised in other common law jurisdictions and such orders can be made to have world-wide effect. It is variously construed as part of a court's inherent jurisdiction to restrain breaches of its process.\n" +
"\n" +
"The jurisdiction arises in part from the Judicature Act 1873, which provided that \"A mandamus or an injunction may be granted or a receiver appointed by an interlocutory Order of the Court in all cases in which it shall appear to the Court to be just or convenient...\" Relying on this, Jessel MR in 1878 declared, \"I have unlimited power to grant an injunction in any case where it would be right or just to do so...\""); //taken from https://en.wikipedia.org/wiki/Asset_freezing
            }
            if((30 >= rand) && (rand > 25)){
                jTextArea1.setText("Historians do not fully agree on the dates, but 1947–91 is common. The term \"cold\" is used because there was no large-scale fighting directly between the two sides, although there were major regional wars, known as proxy wars, supported by the two sides. The Cold War split the temporary wartime alliance against Nazi Germany, leaving the USSR and the US as two superpowers with profound economic and political differences: the former being a single-party Marxist–Leninist state operating a planned economy and controlled press and owning exclusively the right to establish and govern communities, and the latter being a capitalist state with generally free elections and press, which also granted freedom of expression and freedom of association to its citizens. A self-proclaimed neutral bloc arose with the Non-Aligned Movement founded by Egypt, India, Indonesia and Yugoslavia; this faction rejected association with either the US-led West or the Soviet-led East. The two superpowers never engaged directly in full-scale armed combat, but they were heavily armed in preparation for a possible all-out nuclear world war. Each side had a nuclear deterrent that deterred an attack by the other side, on the basis that such an attack would lead to total destruction of the attacker: the doctrine of mutually assured destruction (MAD). Aside from the development of the two sides' nuclear arsenals, and deployment of conventional military forces, the struggle for dominance was expressed via proxy wars around the globe, psychological warfare, massive propaganda campaigns and espionage, rivalry at sports events, and technological competitions such as the Space Race."); //taken from https://en.wikipedia.org/wiki/Cold_War
            }
            
            if((35 >= rand) && (rand > 30)){
                jTextArea1.setText("The first phase of the Cold War began in the first two years after the end of the Second World War in 1945. The USSR consolidated its control over the states of the Eastern Bloc, while the United States began a strategy of global containment to challenge Soviet power, extending military and financial aid to the countries of Western Europe (for example, supporting the anti-communist side in the Greek Civil War) and creating the NATO alliance. The Berlin Blockade (1948–49) was the first major crisis of the Cold War. With victory of the communist side in the Chinese Civil War and the outbreak of the Korean War (1950–53), the conflict expanded. The USSR and USA competed for influence in Latin America, and the decolonizing states of Africa, the Middle East and Southeast Asia. Meanwhile, the Hungarian Revolution of 1956 was stopped by the Soviets. The expansion and escalation sparked more crises, such as the Suez Crisis (1956), the Berlin Crisis of 1961, and the Cuban Missile Crisis of 1962. Following the Cuban Missile Crisis, a new phase began that saw the Sino-Soviet split complicate relations within the communist sphere, while US allies, particularly France, demonstrated greater independence of action. The USSR crushed the 1968 Prague Spring liberalization program in Czechoslovakia, and the Vietnam War (1955–75) ended with a defeat of the US-backed Republic of South Vietnam, prompting further adjustments.\n" +
"\n" +
"By the 1970s, both sides had become interested in accommodations to create a more stable and predictable international system, inaugurating a period of détente that saw Strategic Arms Limitation Talks and the US opening relations with the People's Republic of China as a strategic counterweight to the Soviet Union. Détente collapsed at the end of the decade with the Soviet war in Afghanistan beginning in 1979. The early 1980s were another period of elevated tension, with the Soviet downing of Korean Air Lines Flight 007 (1983), and the \"Able Archer\" NATO military exercises (1983). "); //taken from https://en.wikipedia.org/wiki/Cold_War
            }
            if((40 >= rand) && (rand > 35)){
                jTextArea1.setText("By the 1970s, both sides had become interested in accommodations to create a more stable and predictable international system, inaugurating a period of détente that saw Strategic Arms Limitation Talks and the US opening relations with the People's Republic of China as a strategic counterweight to the Soviet Union. Détente collapsed at the end of the decade with the Soviet war in Afghanistan beginning in 1979. The early 1980s were another period of elevated tension, with the Soviet downing of Korean Air Lines Flight 007 (1983), and the \"Able Archer\" NATO military exercises (1983). The United States increased diplomatic, military, and economic pressures on the Soviet Union, at a time when the communist state was already suffering from economic stagnation. In the mid-1980s, the new Soviet leader Mikhail Gorbachev introduced the liberalizing reforms of perestroika (\"reorganization\", 1987) and glasnost (\"openness\", c. 1985) and ended Soviet involvement in Afghanistan. Pressures for national independence grew stronger in Eastern Europe, especially Poland. Gorbachev meanwhile refused to use Soviet troops to bolster the faltering Warsaw Pact regimes as had occurred in the past. The result in 1989 was a wave of revolutions that peacefully (with the exception of the Romanian Revolution) overthrew all of the communist regimes of Central and Eastern Europe. The Communist Party of the Soviet Union itself lost control and was banned following an abortive coup attempt in August 1991. This in turn led to the formal dissolution of the USSR in December 1991 and the collapse of communist regimes in other countries such as Mongolia, Cambodia and South Yemen. The United States remained as the world's only superpower.\n" +
"\n" +
"The Cold War and its events have left a significant legacy. It is often referred to in popular culture, especially in media featuring themes of espionage (e.g. the internationally successful James Bond movie franchise) and the threat of nuclear warfare."); //taken from https://en.wikipedia.org/wiki/Cold_War
            }
            
        }
        else
        {
            questionType = "creative";
            //these will be the creative prompts, where the user is asked to describe something they have done 
            //these prompts must be expansive enough for the user to be able to hit their minimum difference.
            //maybe these prompts will have to end after a time period or with a completion button.
            jTextArea1.setText("Please write a short paragraph about what has happened today.");
        }
        try {
            //this.wait();
            while(!request.hasUserAccepted()){
                
            }
            outFile = new BFileWriter(request.getFile(), questionType);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(this,io.getMessage());
        }/* catch (InterruptedException ex) {
            Logger.getLogger(MainBMGUI.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }   

    private void abandonTest(){
        keystrokesAL.clear();
        taMain.setText("");
        jTextArea1.setText("");
        System.exit(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar12 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMain = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        pb0 = new javax.swing.JProgressBar();
        lbl0 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taMain.setColumns(20);
        taMain.setLineWrap(true);
        taMain.setRows(5);
        taMain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taMainKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                taMainKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taMainKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(taMain);

        lbl0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setText("Exit Test");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbl3)
                            .addComponent(lbl4)
                            .addComponent(lbl5)
                            .addComponent(lbl6)
                            .addComponent(lbl7)
                            .addComponent(lbl8)
                            .addComponent(lbl9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl0)
                            .addComponent(lbl1)
                            .addComponent(lbl2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pb0, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl0, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl1)
                        .addGap(21, 21, 21)
                        .addComponent(lbl2)
                        .addGap(36, 36, 36)
                        .addComponent(lbl3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl4)
                        .addGap(20, 20, 20)
                        .addComponent(lbl5)
                        .addGap(36, 36, 36)
                        .addComponent(lbl6)
                        .addGap(22, 22, 22)
                        .addComponent(lbl7)
                        .addGap(24, 24, 24)
                        .addComponent(lbl8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl9)
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                        .addGap(28, 28, 28)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pb0, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void taMainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taMainKeyPressed
        startTime = System.nanoTime();
    }//GEN-LAST:event_taMainKeyPressed

    private void taMainKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taMainKeyReleased
        endTime = System.nanoTime();
        keystrokesAL.add(new KeyPress(evt.getKeyCode(),(endTime-startTime)));
        outFile.addToBuffer(new KeyPress(evt.getKeyCode(),(endTime-startTime)));
    }//GEN-LAST:event_taMainKeyReleased

    private void taMainKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taMainKeyTyped
        updateAndOrgainize();
    }//GEN-LAST:event_taMainKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        abandonTest();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainBMGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainBMGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainBMGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainBMGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainBMGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JProgressBar jProgressBar12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl0;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JProgressBar pb0;
    private javax.swing.JTextArea taMain;
    // End of variables declaration//GEN-END:variables
        
    private final int MINNUMACCEPTED = 40; //100
    private final int MINDIFFERENT = 10;
    private ArrayList<KeyPress> keystrokesAL = new ArrayList<>();
    private long startTime, endTime = 0;
    private BFileWriter outFile;
}