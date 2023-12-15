package com.stock_test.Controller;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.SignupView;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

public class SignupController {
    private SignupView signupView;

    public SignupController() {
        this(new SignupView());
    }

    public SignupController(SignupView signupView) {
        this.signupView = signupView;
        setListeners();
    }

    public void setListeners() {
        this.signupView.addSubmitButtonListener(e -> submit());
        this.signupView.addCheckDuplicateButtonListener(e -> checkEmailDuplicate());
    }

    public void setCancelListener(ActionListener lis){
        this.signupView.addCancelButtonListener(lis);
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
//        finishListener.finish();
    }

    private void checkEmailDuplicate() {
        String email = signupView.getEmail();
        if (email.isEmpty()) {
            signupView.setDuplicateResultText("Email field is empty");
            return;
        }

        if (!checkEmailValid(email)) {
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

    //check if the input is a valid email address use regular expression
    private boolean checkEmailValid(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        if (!email.matches(regex)) {
            signupView.setDuplicateResultText("Invalid email address");
            return false;
        }
        return true;
    }
}
