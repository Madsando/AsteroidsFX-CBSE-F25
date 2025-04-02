package dk.sdu.cbse.main;

import dk.sdu.cbse.common.graphics.IBackgroundComponent;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.input.spi.IInputSPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class ModuleConfig {

    public ModuleConfig() {}

    @Bean
    public Game game() {
        return new Game();
    }

    @Bean
    public static List<IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<IInputSPI> getIInputService() {
        return ServiceLoader.load(IInputSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<IGraphicsComponent> getIGraphicComponents() {
        return ServiceLoader.load(IGraphicsComponent.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public static List<IBackgroundComponent> getIBackgroundComponents() {
        return ServiceLoader.load(IBackgroundComponent.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
