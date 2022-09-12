package Login.UseCases;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObserverManager implements IObserverManager {
    private UpdateUser uu;
    private IUserBase ub;
    private PropertyChangeSupport observable;

    public ObserverManager(IUserBase ub) {
        this.ub = ub;
        this.uu = new UpdateUser(ub);
        this.observable = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer){
        observable.addPropertyChangeListener(observer);
    }

    public void removeObserver(PropertyChangeListener observer){
        observable.removePropertyChangeListener(observer);
    }

    public void notifyObservers(PropertyChangeEvent newEvent){
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()){
            observer.propertyChange(newEvent);
        }
    }

    // This update method should be called whenever pets are updated
    public void updateUsers(){
        // Naive implementation
        PropertyChangeEvent p = uu.newEvent();
        notifyObservers(p);
    }

}
