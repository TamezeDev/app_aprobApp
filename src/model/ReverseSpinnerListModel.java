package model;

import javax.swing.SpinnerListModel;

public class ReverseSpinnerListModel extends SpinnerListModel {

    //load reverse spinner for easier controller
    public ReverseSpinnerListModel(Object[] values) {
        super(values);
    }

    @Override
    public Object getNextValue() {
        return super.getPreviousValue();
    }

    @Override
    public Object getPreviousValue() {
        return super.getNextValue();
    }
}
