package observer;

import model.Item;
import model.Loadable;
import model.Saveable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Observer {

    void addSubject(Subject s);

    void update(Item i);
}
