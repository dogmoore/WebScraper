package com.dogmoore.dialog;

import com.dogmoore.ScraperProgram;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class NewFrontEnd {
    final JTextField t1, t2;
    final JButton b1, b2;
    final JLabel l1, l2;
    public NewFrontEnd() {
        boolean debug = ScraperProgram.debug;
        JFrame f = new JFrame("WebScrape test v1.0-snap");
        String image = "https://scrapingant.com/wp-content/uploads/elementor/thumbs/рпло-onkgmrkfymivnq1x256oik7rhfrj89ognxglwu2o00.png";
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage(new URL(image));
            f.setIconImage(icon);
        } catch (final MalformedURLException ex) {
            ex.printStackTrace();
        }


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("URL");
        l1.setBounds(50, 75, 100, 30);
        l2 = new JLabel("Scrape");
        l2.setBounds(50, 125, 100, 30);

        t1 = new JTextField();
        t1.setBounds(50, 100, 200, 30);
        t2 = new JTextField();
        t2.setBounds(50, 150, 200, 30);

        b1 = new JButton("CLOSE");
        b1.setBounds(50, 200, 100, 50);
        b2 = new JButton("OK");
        b2.setBounds(170, 200, 100, 50);

        b1.addActionListener(e -> f.dispose());
        b2.addActionListener(e -> {
            String URL = t1.getText();
            String scrape = t2.getText();
            if (debug) {
                System.out.println("URL " + URL + " scrape: " + scrape);
            }
            String Content;
            try {
                Document pageToScrape = Jsoup.connect(URL).get();
                JFrame d = new JFrame("OUTPUT");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                if (URL.contains("https://github.com/dogmoore/WebScraper")) {
                    Content = "Nice try, trying to scrape my own github page!";
                }
                else {
                    switch (scrape.toLowerCase()) {
                        case "title" -> Content = pageToScrape.title();
                        case "body" -> Content = pageToScrape.body().text();
                        default -> {
                            if (scrape.isEmpty() || scrape.isBlank()) {
                                Content = "Please fill in what you want to scrape for.";
                            } else {
                                Content = "Something unexpected happened";
                                if (debug) {
                                    System.out.println("Something unexpected occurred");
                                }
                            }
                        }
                    }
                }
                JTextArea ta = new JTextArea(Content);
                ta.setBackground(Color.lightGray);
                ta.setBounds(50, 50, 200, 200);
                ta.setEditable(false);
                if (debug) {
                    System.out.println("Creating output window.....");
                }
                try {
                    Image icon = Toolkit.getDefaultToolkit().getImage(new URL(image));
                    d.setIconImage(icon);
                } catch (final MalformedURLException ex) {
                    ex.printStackTrace();
                }
                d.add(ta);
                d.setSize(400, 400);
                d.setVisible(true);
                d.setLayout(null);
                if (debug) {
                    System.out.println("Output window created saying " + Content);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        f.add(l1);
        f.add(l2);
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(b2);

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}