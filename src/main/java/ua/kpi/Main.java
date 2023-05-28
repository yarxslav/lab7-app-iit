package ua.kpi;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.binder.jvm.*;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import org.fluentd.logger.FluentLogger;
import spark.Spark;
import ua.kpi.controller.FrontController;
import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {
        configureMetrics();
        new FrontController().init();
    }

    private static void configureMetrics () {
        PrometheusMeterRegistry meterRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT,
                CollectorRegistry.defaultRegistry, Clock.SYSTEM);

        new ClassLoaderMetrics().bindTo(meterRegistry);
        new JvmMemoryMetrics().bindTo(meterRegistry);
        new JvmGcMetrics().bindTo(meterRegistry);
        new ProcessorMetrics().bindTo(meterRegistry);
        new JvmThreadMetrics().bindTo(meterRegistry);
        new UptimeMetrics().bindTo(meterRegistry);
        new JvmCompilationMetrics().bindTo(meterRegistry);
        new JvmInfoMetrics().bindTo(meterRegistry);

        Spark.port(9091);

        get("/metrics", (request, response) -> meterRegistry.scrape());
    }

}