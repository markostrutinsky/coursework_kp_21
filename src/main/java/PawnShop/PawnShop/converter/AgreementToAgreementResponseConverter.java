package PawnShop.PawnShop.converter;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.response.AgreementResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgreementToAgreementResponseConverter implements Converter<Agreement, AgreementResponse> {

    @Override
    public AgreementResponse convert(Agreement source) {
        return AgreementResponse.builder()
                .id(source.getId())
                .email(source.getEmail())
                .build();
    }
}