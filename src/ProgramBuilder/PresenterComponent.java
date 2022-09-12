package ProgramBuilder;

import Presenters.ConsolePresenter;
import Presenters.IPresenter;

public class PresenterComponent {
    private IPresenter presenter;
    public PresenterComponent() {
        presenter = new ConsolePresenter();
    }

    public IPresenter getPresenter() {
        return this.presenter;
    }
}
