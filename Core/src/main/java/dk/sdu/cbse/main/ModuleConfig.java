package dk.sdu.cbse.main;

import dk.sdu.cbse.common.graphics.IBackgroundComponent;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.input.spi.IInputSPI;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ModuleConfig {
    public static Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static Collection<? extends IInputSPI> getIInputService() {
        return ServiceLoader.load(IInputSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static Collection<? extends IGraphicsComponent> getIGraphicComponents() {
        return ServiceLoader.load(IGraphicsComponent.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static Collection<? extends IBackgroundComponent> getIBackgroundComponents() {
        return ServiceLoader.load(IBackgroundComponent.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
