module linfo2266 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;



    exports uclouvain.linfo2266.localsearch.minils.examples;
    exports uclouvain.linfo2266.visu;
    exports uclouvain.linfo2266.localsearch;
    opens uclouvain.linfo2266.localsearch.minils.examples to javafx.fxml;
}