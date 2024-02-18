/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.Game;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Player {

    private final String[] playerStats = {"Hitpoints", "Attack", "Strength", "Defence", "Magic"};
    public String name;
    public ArrayList<Stat> stats = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        for (String s : this.playerStats) {
            this.stats.add(new Stat(s));
        }
    }

    public Player(String name, ArrayList<Stat> stats) {
        this.name = name;
        this.stats = stats;
    }

    @Override
    public String toString() {
        String result = "\n" + this.name + "\n------------\n";
        for (Stat stat : this.stats) {
            result = result.concat(stat.toString() + "\n");
        }
        return result;
    }

    public void save() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            Document doc = db.newDocument();

            // create the root element
            Element root = doc.createElement("player");

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(this.name));
            root.appendChild(nameElement);

            Element statsElement = doc.createElement("stats");
            Element statElement = null;
            for (Stat stat : this.stats) {
                statElement = doc.createElement(stat.name);
                statElement.appendChild(doc.createTextNode(Integer.toString(stat.getValue())));
                statsElement.appendChild(statElement);

            }

            root.appendChild(statsElement);

            doc.appendChild(root);

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            StreamResult result = new StreamResult(new File("player.xml"));

            tr.transform(new DOMSource(doc), result);

        } catch (TransformerException e) {
            System.out.println(e.getMessage());

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void readPlayer() {
        try {
            File fXmlFile = new File("player.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            String name = "";
            ArrayList<Stat> stats = new ArrayList<>();
            name = doc.getElementsByTagName("name").item(0).getTextContent();
            NodeList statList = doc.getElementsByTagName("stats").item(0).getChildNodes();
            for (int i = 0; i < statList.getLength(); i++) {
                Node stat = statList.item(i);
                if (stat.getNodeType() == Node.ELEMENT_NODE) {
                    stats.add(new Stat(stat.getNodeName(), Integer.parseInt(stat.getTextContent())));
                }
            }
            Game.player = new Player(name, stats);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
