package liebman.met.neo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class MetFrameModule extends AbstractModule {
    @Provides
    static MetService providesNeoService() {
        return new MetFactory().getInstance();
    }
}
