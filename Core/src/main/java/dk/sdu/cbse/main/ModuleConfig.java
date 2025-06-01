package dk.sdu.cbse.main;

import dk.sdu.cbse.common.ui.*;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.ISystemService;
import dk.sdu.cbse.common.utility.ISystemComparator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class ModuleConfig {
    @Bean
    public static Game game() {
        return new Game();
    }

    @Bean
    public static List<IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<ISystemService> getISystemServices() {
        return ServiceLoader.load(ISystemService.class).stream().map(ServiceLoader.Provider::get).sorted(new ISystemComparator()).collect(toList());
    }

    @Bean
    public static List<IInputService> getIInputService() {
        return ServiceLoader.load(IInputService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<IGraphicsService> getIGraphicsServices() {
        return ServiceLoader.load(IGraphicsService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<IBackgroundService> getIBackgroundServices() {
        return ServiceLoader.load(IBackgroundService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
