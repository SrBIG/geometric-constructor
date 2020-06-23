package issoft.isk.geometricconstructor.model.converter;

public interface EntityConverter<E, D> {
    D toDto(E entity);

    E toEntity(D dto);
}
