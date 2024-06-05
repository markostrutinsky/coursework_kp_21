package PawnShop.PawnShop.service.mediator;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MediatorImpl implements Mediator {

    private final Map<Class<?>, Handler<?, ?>> handlers = new HashMap<>();
    public <TRequest, TResponse> void registerHandler(Class<TRequest> requestClass, Handler<TRequest, TResponse> handler) {
        handlers.put(requestClass, handler);
    }

    @SuppressWarnings("unchecked")
    @Override public <TRequest, TResponse> TResponse send(TRequest request) {
        Handler<TRequest, TResponse> handler = (Handler<TRequest, TResponse>) handlers.get(request.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler registered for " + request.getClass());
        }
        return handler.handle(request);
    }
}
