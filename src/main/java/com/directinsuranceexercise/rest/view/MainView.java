package com.directinsuranceexercise.rest.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("Shiri's awesome spring webapp")
public class MainView extends VerticalLayout {

    public MainView(){

        this.setAlignItems(Alignment.BASELINE);
        var header = new H2("Shiri's Awesome Spring Webapp!!");
        var textfield = new TextField("Enter your name please");
        var button = new Button("Update Name!");
        var img = new Image("sample.png","A flower");
        var paragraph = new Paragraph("");
        var hLayout = new HorizontalLayout(textfield, button);
        add(new VerticalLayout(header,hLayout, paragraph, img));
        button.addClickListener(e->{
            paragraph.setText("Hello,"+textfield.getValue()+"!!");
            textfield.clear();
        });
    }

}


