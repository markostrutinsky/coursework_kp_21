package PawnShop.PawnShop.service.mediator;

public interface Mediator {
    <TRequest,TResponse> TResponse send(TRequest request);
}
