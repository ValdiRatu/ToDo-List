package observer;

import model.Item;
import model.ListMap;
import model.Loadable;
import model.Saveable;


import java.io.*;

public class Subject implements Serializable {
    protected Observer observer;

    public void setObserver(Observer o) {
        this.observer = o;
    }

    public void notifyObserver(Item i) {
        observer.update(i);
    }


//    // https://stackoverflow.com/questions/10281370/see-if-file-is-empty
//    @Override
//    // MODIFIES: this
//    // EFFECTS: loads the previous state of ListMap
//    public void load() throws IOException, ClassNotFoundException {
//        File file = new File(
//                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\save.txt");
//        if (file.length() > 0) {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
//                    "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\save.txt"));
//            Subject subject = (Subject) in.readObject();
//            this.observer = subject.observer;
//        }
//    }
//
//
//    // Found this save and load code on youtube
//    // https://www.youtube.com/watch?v=bRuxXAF-Ysk
//
//    @Override
//    // EFFECTS: saves the current state of ListMap
//    public void save() throws IOException {
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
//                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\save.txt"));
//        out.writeObject(this);
//    }
}
