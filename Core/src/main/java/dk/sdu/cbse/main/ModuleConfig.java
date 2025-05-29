package dk.sdu.cbse.main;

import dk.sdu.cbse.common.ui.*;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.ISystemService;
import dk.sdu.cbse.common.utility.ISystemComparator;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ModuleConfig {
    public static Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static Collection<? extends ISystemService> getISystemServices() {
        return ServiceLoader.load(ISystemService.class).stream().map(ServiceLoader.Provider::get).sorted(new ISystemComparator()).collect(toList());
    }

    public static Collection<? extends IInputService> getIInputService() {
        return ServiceLoader.load(IInputService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static Collection<? extends IGraphicsService> getIGraphicComponents() {
        return ServiceLoader.load(IGraphicsService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static Collection<? extends IBackgroundService> getIBackgroundComponents() {
        return ServiceLoader.load(IBackgroundService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
