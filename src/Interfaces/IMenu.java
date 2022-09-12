package Interfaces;

import java.io.IOException;

/** The Menu interface ensures that whatever class that implements it has a menu.
 *  This is reserved primarily for the controllers in the system.
 *
 */
public interface IMenu {
    void menu() throws IOException;
}
