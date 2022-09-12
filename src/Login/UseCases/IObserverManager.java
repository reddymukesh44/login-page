package Login.UseCases;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface IObserverManager {

    void addObserver(PropertyChangeListener observer);

    void removeObserver(PropertyChangeListener observer);

    void notifyObservers(PropertyChangeEvent newEvent);

    void updateUsers();
}
