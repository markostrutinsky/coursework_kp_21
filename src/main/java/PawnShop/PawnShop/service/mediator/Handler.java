package PawnShop.PawnShop.service.mediator;

import java.sql.SQLException;

public interface Handler<TRequest, TResponse> {
    TResponse handle(TRequest request);
}
