package com.company;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraMemory implements ActionListener
{
    Random losowanie = new Random();

    JFrame graMemory = new JFrame("Gra Memory");

    JPanel plansza = new JPanel();
    JPanel iloscObrazow = new JPanel();
    JPanel startKoniec = new JPanel();
    JPanel trudnoscZasady = new JPanel();
    JPanel rankingowy = new JPanel();
    JPanel wyswietlPoczatek = new JPanel();
    JPanel wyswietlKoniec = new JPanel();
    JPanel wyswietlZasady = new JPanel();

    //



    JPanel ranking = new JPanel();



    //

    JButton zacznij = new JButton("Zacznij Gre");
    JButton wyjdz = new JButton("Wyjdz");
    //
    JButton ranking1 = new JButton("Ranking");
    JButton zapisz = new JButton("Zapisz Wynik");
    //
    JButton zasady = new JButton("Zasady Gry");
    JButton cofnij = new JButton("Cofnij");
    JButton[] obrazy = new JButton[20];
    ImageIcon question_1 = new ImageIcon("C:\\Users\\kowal\\Downloads\\question1.png");
    ImageIcon tester = new ImageIcon("C:\\Users\\kowal\\Downloads\\default.png");
    ImageIcon[] icons_buff={


            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),



    };


    ImageIcon[] icons = {

            new ImageIcon("C:\\Users\\kowal\\Downloads\\text1.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text2.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text3.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text4.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text5.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text6.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text7.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text8.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text9.png"),
            new ImageIcon("C:\\Users\\kowal\\Downloads\\text10.png")
    };



    private boolean czyszczenie = false;
    int poziom = 0;
    int wynik = 0;

    Boolean pokazane = true;
    int a = 30, b=30;

    private final JTextField poleTekstowe = new JTextField("10",10);
    private final JTextArea zasadyGry = new JTextArea("Ekran zapelni sie parami slow.\nZapamietaj ich miejsce.\nPo nacisnieciu na ktorykolwiek z nich znikna.\nTwoim zadaniem jest klikniecie na pary znakow.\nKiedy dopasujesz wszystkie pary wygrasz.\nKazde zle dopasowanie daje negatywny punkt.\nPowodzenia!\n");
    JTextArea ranked = new JTextArea(30,15);
    JTextField pseudonim = new JTextField("Wpisz pseudonim");
    JScrollPane sp = new JScrollPane(ranked);

    public GraMemory()
    {
        graMemory.setSize(1000,600);
        graMemory.setLocation(700,400);
        graMemory.setLayout(new BorderLayout());
        graMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wyswietlPoczatek.setLayout(new BorderLayout());
        iloscObrazow.setLayout(new FlowLayout(FlowLayout.CENTER));
        startKoniec.setLayout(new FlowLayout(FlowLayout.CENTER));
        //
        ranking.setLayout(new FlowLayout(FlowLayout.CENTER));
        //
        trudnoscZasady.setLayout(new FlowLayout(FlowLayout.CENTER));
        wyswietlPoczatek.add(iloscObrazow, BorderLayout.NORTH);
        wyswietlPoczatek.add(trudnoscZasady, BorderLayout.CENTER);
        wyswietlPoczatek.add(startKoniec, BorderLayout.SOUTH);
        //
        sp.setBounds(10,60,780,500);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ranked.setEditable(false);
        zasadyGry.setEditable(false);
        //
        JLabel label = new JLabel("Wprowadz ilosc obrazow do dopasowania (1-10):");
        iloscObrazow.add(new JLabel(new ImageIcon("C:\\Users\\kowal\\OneDrive\\Pulpit\\Java\\Memory\\src\\com\\company\\1.png")), BorderLayout.NORTH);
        trudnoscZasady.add(label, BorderLayout.NORTH);
        trudnoscZasady.add(poleTekstowe, BorderLayout.NORTH);
        startKoniec.add(zasady, BorderLayout.SOUTH);


        zacznij.addActionListener(this);
        zacznij.setEnabled(true);
        startKoniec.add(zacznij);
        wyjdz.addActionListener(this);
        wyjdz.setEnabled(true);
        //
        ranking1.addActionListener(this);
        ranking1.setEnabled(true);
        startKoniec.add(ranking1);
        //
        startKoniec.add(wyjdz);
        zasady.addActionListener(this);
        zasady.setEnabled(true);

        graMemory.add(wyswietlPoczatek, BorderLayout.CENTER);
        graMemory.setVisible(true);
    }

    public void powrotDoMenu()
    {
        new GraMemory();
    }

    public void resetEkranuGlownego()
    {
        wyswietlPoczatek.remove(iloscObrazow);
        wyswietlPoczatek.remove(startKoniec);
        wyswietlPoczatek.remove(trudnoscZasady);
        wyswietlPoczatek.revalidate();
        wyswietlPoczatek.repaint();
    }

    public void stworzPlansze()
    {
        plansza.setLayout(new BorderLayout());
        wyswietlPoczatek.add(plansza, BorderLayout.CENTER);
        plansza.setLayout(new GridLayout(5,4,2,2));
        plansza.setBackground(Color.white);
        plansza.requestFocus();
    }

    public void ukryjPole(int x)
    {
        for(int i = 0;i < (x * 2);i++)
        {
            obrazy[i].setIcon(question_1);

        }
        pokazane = false;
    }

    public void sprawdzCzyKoniec()
    {
        for(int i = 0; i<(poziom * 2); i++)
        {
            if (!icons_buff[i].equals(tester))
            {
                return;
            }
        }
        zwyciezca();
    }

    ////// obraz 3

    public void zamienMiejsca(int i)
    {
        if(!icons_buff[i].equals(tester))
        {
            if(obrazy[i].getIcon().equals(question_1))
            {
                obrazy[i].setIcon(icons_buff[i]);
            }
            else
            {
                obrazy[i].setIcon(question_1);

            }
        }
    }

    public void zwyciezca()
    {
        resetEkranuGlownego();
        System.out.println("test1");
        wyswietlPoczatek.remove(plansza);
        System.out.println("test2");
        wyswietlPoczatek.add(wyswietlKoniec, BorderLayout.NORTH);
        System.out.println("test3");
        wyswietlKoniec.add(new TextField("Wygrana"), BorderLayout.NORTH);
        System.out.println("test4");
        wyswietlKoniec.add(new TextField("Błędy: " + wynik), BorderLayout.NORTH);
        //
        wyswietlKoniec.add(pseudonim, BorderLayout.SOUTH);
        zapisz.addActionListener(this);
        zapisz.setEnabled(true);
        wyswietlKoniec.add(zapisz);
        //
        System.out.println("test5");
        wyswietlKoniec.add(cofnij);
        System.out.println("test6");
        cofnij.setEnabled(true);
        System.out.println("test7");
        cofnij.addActionListener(this);
        System.out.println("test8");
    }

    public void rozpocznijGre(int x)
    {
        poziom = x;
        resetEkranuGlownego();

        icons_buff = new ImageIcon[2 * x];
        for(int i = 0;i < (x * 2);i++)
        {
            obrazy[i] = new JButton(question_1);
            obrazy[i].addActionListener(this);
            obrazy[i].setEnabled(true);
            plansza.add(obrazy[i]);
        }

        for(int i = 0;i < x;i++)
        {
            for(int z = 0;z < 2;z++)
            {
                while(true)
                {
                    int y = losowanie.nextInt(x * 2);
                    if(icons_buff[y] == null)
                    {
                        obrazy[y].setIcon(icons[i]);
                        icons_buff[y]=icons[i];
                        break;
                    }
                }
            }
        }
        stworzPlansze();
    }


    public void actionPerformed(ActionEvent click)
    {
        Object klikniecie = click.getSource();
        if(czyszczenie)
        {
            zamienMiejsca(b);
            zamienMiejsca(a);
            wynik++;
            a = (poziom * 2);
            b = 30;
            czyszczenie = false;
        }
        if(klikniecie == zacznij)
        {
            try
            {
                poziom = Integer.parseInt(poleTekstowe.getText());
            }
            catch (Exception e)
            {
                poziom = 1;
            }
            rozpocznijGre(poziom);
        }

        if(klikniecie == wyjdz)
        {
            System.exit(0);
        }

        if(klikniecie == zasady)
        {
            resetEkranuGlownego();

            wyswietlPoczatek.add(wyswietlZasady, BorderLayout.NORTH);

            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel jPanel2 = new JPanel();
            jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
            wyswietlZasady.setLayout(new BorderLayout());
            wyswietlZasady.add(jPanel1, BorderLayout.NORTH);
            wyswietlZasady.add(jPanel2, BorderLayout.SOUTH);

            jPanel1.add(zasadyGry);
            jPanel2.add(cofnij);
            cofnij.addActionListener(this);
            cofnij.setEnabled(true);
        }

        if(klikniecie == ranking1)
        {
            resetEkranuGlownego();

            wyswietlPoczatek.add(wyswietlZasady, BorderLayout.NORTH);

            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel jPanel2 = new JPanel();
            jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
            wyswietlZasady.setLayout(new BorderLayout());
            wyswietlZasady.add(jPanel1, BorderLayout.NORTH);
            wyswietlZasady.add(jPanel2, BorderLayout.SOUTH);
            ranked.setText(streamInput());
            jPanel1.add(sp);
            jPanel2.add(cofnij);
            cofnij.addActionListener(this);
            cofnij.setEnabled(true);
        }

        if(klikniecie == zapisz)
        {
            streamOutput();
            streamInput();
            graMemory.dispose();
            powrotDoMenu();
            resetEkranuGlownego();
        }

        if(klikniecie == cofnij)
        {
            graMemory.dispose();
            powrotDoMenu();
        }

        for(int i = 0; i < (poziom * 2); i++)
        {
            if(klikniecie == obrazy[i])
            {
                if(pokazane)
                {
                    ukryjPole(poziom);
                }
                else
                {
                    zamienMiejsca(i);
                    if(a >= (poziom * 2))
                    {
                        a = i;
                    }
                    else
                    {
                        if((!icons_buff[a].equals(icons_buff[i]))||(a == i))
                        {
                            b = i;
                            czyszczenie = true;
                        }
                        else
                        {
                            icons_buff[i] = tester;
                            icons_buff[a] = tester;
                            sprawdzCzyKoniec();
                            a = (poziom * 2);
                        }
                    }
                }
            }
        }
    }

    public void streamOutput()
    {
        String s = pseudonim.getText() + " Wynik: " + wynik + "\n" + "\n";
        String nazwa = "highscores.txt";
        try {
            File myObj = new File(nazwa);
            if (myObj.createNewFile()) {
                System.out.println("Plik utworzony: " + myObj.getName());
                System.out.println("Sciezka: " + myObj.getAbsolutePath());
            } else {
                System.out.println("Plik juz istnieje");
            }
        } catch (IOException e)
        {
            System.out.println("Wystapil blad przy tworzeniu pliku");
            e.printStackTrace();
        }
        try
        {
            FileOutputStream fout=new FileOutputStream(nazwa,true);
            byte[] b =s.getBytes();
            fout.write(b);
            System.out.println("Wpisano do pliku: "+ s);
            fout.close();
        }
        catch (IOException e)
        {
            System.out.println("Wystapil blad przy wpisywaniu do pliku");
            e.printStackTrace();
        }
    }

    public String streamInput()
    {
        String nazwa = "highscores.txt";
        StringBuilder buffer = new StringBuilder();
        try {
            FileInputStream fin=new FileInputStream(nazwa);
            int i;
            while((i=fin.read())!=-1)
            {
                buffer.append(Character.toString(i));
            }
        } catch (IOException e)
        {
            System.out.println("Wystapil blad przy odczytywaniu pliku");
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] args)
    {
        new GraMemory();
    }
}