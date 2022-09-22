package Avaliacao2_SO;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class main extends Thread {

    public static void main(String[] args) throws InterruptedException {
        Vector<Integer> matriculaVetor = new Vector<>();
        int j;
        String matriculaString = JOptionPane.showInputDialog(null, "Insira sua matricula", "Matricula", JOptionPane.DEFAULT_OPTION);
        for (int i = 0; matriculaString.length() > i; i++) {
            char c = matriculaString.charAt(i);
            j = Character.getNumericValue(c);
            matriculaVetor.add(i, j);
        }
        
        JOptionPane.showMessageDialog(null, matriculaVetor.toString(), "Matricula", JOptionPane.INFORMATION_MESSAGE);
        Thread[] produtor_consumidor = new Thread[(matriculaVetor.size())];
        for (int i = 0; i < matriculaVetor.size(); i++) {
            if (i % 2 == 0) {
                produtor_consumidor[i] = new produtor(matriculaVetor.get(i));
            } else {
                produtor_consumidor[i] = new consumidor(matriculaVetor.get(i));
            }
        }
        for (Thread item : produtor_consumidor) {
            item.start();
        }
        main.sleep(10000);
        for (Thread item : produtor_consumidor) {
            item.stop();
        }
        JOptionPane.showMessageDialog(null, pilha.getValue(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    public static class produtor extends Thread {
        int valor;
        
        public produtor(int valor) {
            this.valor = valor;
        }

        @Override
        public void run() {
            pilha.inc(valor);
            if (valor == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    Thread.sleep(valor * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static class consumidor extends Thread {
        int valor;
        public consumidor(int valor) {
            this.valor = valor;
        }

        @Override
        public void run() {
            pilha.dec(valor);
            if (valor == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    Thread.sleep(valor * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static class pilha {
        static int valor;
        public synchronized static void inc(int valorMatricula) {
            valor += valorMatricula;
        }
        public synchronized static void dec(int valorMatricula) {
            valor -= valorMatricula;
        }
        public synchronized static int getValue() {
            return valor;
        }
    }
}
