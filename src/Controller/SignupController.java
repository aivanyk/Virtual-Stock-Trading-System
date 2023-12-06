package com.stock_test.Controller;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.SignupView;

import javax.swing.JOptionPane;

public class SignupController {
    private SignupView signupView;
    private FinishListener finishListener;

    public SignupController() {
        this(new SignupView());
    }

    public SignupController(SignupView signupView) {
        this.signupView = signupView;
        setListeners();
    }

    public void setListeners() {
        this.signupView.addSubmitButtonListener(e -> submit());
        this.signupView.addCancelButtonListener(e -> cancel());
        this.signupView.addCheckDuplicateButtonListener(e -> checkEmailDuplicate());
    }

    public interface FinishListener {
        void finish();
    }

    public void setFinishListener(FinishListener listener) {
        this.finishListener = listener;
    }

    public SignupView getSignupView() {
        return signupView;
    }

    public void submit() {
        if (!signupView.fullFields() || !signupView.getEmailDisabled()) {
            JOptionPane.showMessageDialog(signupView, "Fill all fields and check duplicate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String email = signupView.getEmail();
        String password = new String(signupView.getPassword());
        String name = signupView.getName();
        String phone = signupView.getPhone();

        Customer customer = new Customer(0, name, password, email, phone, false, true, 0.0, 0.0);
        CustomerDatabase.addCustomer(customer);

        JOptionPane.showMessageDialog(signupView, "Signup Success!", "Signup", JOptionPane.INFORMATION_MESSAGE);

        signupView.reset();
        finishListener.finish();
    }

    private void cancel() {
        signupView.reset();
        finishListener.finish();
    }

    private void checkEmailDuplicate() {
        String email = signupView.getEmail();
        if (email.isEmpty()) {
            signupView.setDuplicateResultText("Email field is empty");
            return;
        }

        Customer existingCustomer = CustomerDatabase.getCustomer(email);
        if (existingCustomer != null) {
            signupView.setDuplicateResultText("Email already exists");
        } else {
            signupView.setDuplicateResultText("Email available");
            signupView.disableEmail();
        }
    }
}
