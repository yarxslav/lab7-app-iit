package ua.kpi;

import org.fluentd.logger.FluentLogger;
import ua.kpi.controller.FrontController;

public class Main {

    public static void main(String[] args) {

        new FrontController().init();

    }


}